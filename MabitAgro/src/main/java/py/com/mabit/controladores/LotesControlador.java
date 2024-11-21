package py.com.mabit.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import py.com.mabit.entidades.Lotes;
import py.com.mabit.repositorios.LotesRepositorio;

@Controller
@RequestMapping("/lotes")
public class LotesControlador {

	@Autowired
	LotesRepositorio repositorio;

	@GetMapping({"","/editar/{id}"})
	public String formulario(Model html,@PathVariable(required = false)Long id,@RequestParam( defaultValue = "")String buscar,@ModelAttribute("lote")Lotes lot) {
	
		html.addAttribute("entidad","lotes");
		
		if (id!=null) {
			html.addAttribute("lote", repositorio.findById(id).get());
			html.addAttribute("formColapsado", true);
		}else {
			html.addAttribute("formColapsado", false);
		}
		 
		if (buscar.isEmpty()) {
			html.addAttribute("lista", repositorio.findAll());
			
		}else {
			html.addAttribute("lista", repositorio.findByNombreIgnoreCaseContainingOrCodigoIgnoreCaseContaining(buscar,buscar));
			 
		}
		return "form_lotes";
	}
	

	@PostMapping
	public String guardar(@ModelAttribute("lote") Lotes lot) {

		repositorio.save(lot);
		return "redirect:/lotes";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		Lotes lot = new Lotes();
		lot.setId(id);
		repositorio.delete(lot);
		return "redirect:/lotes";
	}
}
