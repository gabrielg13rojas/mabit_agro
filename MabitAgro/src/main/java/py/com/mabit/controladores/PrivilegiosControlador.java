package py.com.mabit.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import py.com.mabit.entidades.Privilegios;
import py.com.mabit.entidades.Usuarios;
import py.com.mabit.repositorios.PrivilegiosRepositorio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/privilegios")
public class PrivilegiosControlador {
	@Autowired
	PrivilegiosRepositorio repositorio;
	
	@GetMapping({"", "/editar/{id}"})
	public String formularioPri(Model html, 
			@PathVariable(required = false) Long id, @RequestParam(defaultValue = "") String buscar,
			 @ModelAttribute("privilegios") Privilegios priv) {
		
		
		html.addAttribute("entidad", "Privilegios");
		
		if (id!=null) {
			html.addAttribute("privilegios", repositorio.findById(id).get());
			html.addAttribute("formColapsado", true);
		}else {
			html.addAttribute("formColapsado", false);
		}
		
		if (buscar.isEmpty()) {
			html.addAttribute("lista", repositorio.findAll());
		}else {
			html.addAttribute("lista", repositorio.findByDescripcionIgnoreCaseContaining(buscar));
		}
		
		return ("form_privilegios");
	}
	@PostMapping
	public String guardar(@ModelAttribute ("privilegios") Privilegios priv) {
		repositorio.save(priv);
		
		return "redirect:/privilegios";
	}
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		Privilegios priv = new Privilegios();
		priv.setId(id);
		repositorio.delete(priv);
		return "redirect:/privilegios";
	}
	
	
}
