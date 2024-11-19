package py.com.mabit.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import py.com.mabit.entidades.Proveedores;
import py.com.mabit.repositorios.ProveedoresRepositorio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
@RequestMapping("/proveedores")
public class ProveedoresControlador {
	@Autowired
	ProveedoresRepositorio repositorio;
	
	@GetMapping({"","/editar/{id}"})
	public String formulario(Model html,@PathVariable(required = false)Long id,
			@RequestParam(name="buscar",defaultValue = "")String buscar,@ModelAttribute("proveedores")Proveedores proovedores){
		html.addAttribute("entidad","Proveedores");
		
		if (id!=null) {
			html.addAttribute("proveedores",repositorio.findById(id).get());
			html.addAttribute("formColapsado", true);
		}else {	
			html.addAttribute("formColapsado", false);
			
		}
		if (buscar.isEmpty()) {
			html.addAttribute("lista", repositorio.findAll());
		}else {
			html.addAttribute("lista", repositorio.findByEmailIgnoreCaseContaining(buscar));
		}
		
		return "form_proveedores";
	}
	@PostMapping
	public String guardar(@ModelAttribute("proveedores")Proveedores p) {
		repositorio.save(p);
		
		return "redirect:/proveedores";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		Proveedores pro = new Proveedores();
		pro.setId(id);
		repositorio.delete(pro);
		return "redirect:/proveedores";
	}
	
	

}
