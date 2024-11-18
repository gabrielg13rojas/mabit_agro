package py.com.mabit.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import py.com.mabit.entidades.UnidadesMedida;
import py.com.mabit.repositorios.UnidadesMedidasRepositorio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/unidades-medidas")
public class UnidadesMedidasControlador {
	@Autowired
	UnidadesMedidasRepositorio repositorio;
	
	@GetMapping({"","/editar/{id}"})
	public String formulario(Model html, @PathVariable(required = false) Long id, 
			@RequestParam(defaultValue = "") String buscar
			,@ModelAttribute("unidades_medida") UnidadesMedida um) {
		
		html.addAttribute("entidad","Unidades de Medidas");
	
		if (id!=null) {
			html.addAttribute("unidades_medida", repositorio.findById(id).get());
			html.addAttribute("formColapsado",true);
		}else {
			html.addAttribute("formColapsado",false);
		}
		
		if (buscar.isEmpty()) {
			html.addAttribute("lista", repositorio.findAll());
		}else {
			html.addAttribute("lista", repositorio.findByDescripcionIgnoreCaseContaining(buscar));
		}
		
		return "form_unidades_medidas";	
	}
	@PostMapping
	public String guardar(@ModelAttribute("unidades_medida") UnidadesMedida um) {
		repositorio.save(um);
		
		return "redirect:/unidades-medidas";
	}
	
@GetMapping("/eliminar/{id}")
public String eliminar(@PathVariable Long id) {
	UnidadesMedida ums = new UnidadesMedida();
	ums.setId(id);
	repositorio.delete(ums);
    return "redirect:/unidades-medidas";
}

	
}
