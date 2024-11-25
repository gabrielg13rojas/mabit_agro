package py.com.mabit.controladores;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


import py.com.mabit.entidades.Compradores;
import py.com.mabit.repositorios.CompradoresRepositorio;
import py.com.mabit.repositorios.TiposDocumentoCompradorRepositorio;

import org.springframework.web.bind.annotation.PostMapping;




@Controller
@RequestMapping("/compradores")
public class CompradoresControlador {
	@Autowired
	CompradoresRepositorio repositorio;
	@Autowired
	TiposDocumentoCompradorRepositorio tipoDocumentoCompradorRepositorio;

		@GetMapping({"","/editar/{id}"})
	public String formulario(Model html,@PathVariable(required = false)Long id,@RequestParam( defaultValue = "")String buscar,@ModelAttribute("comprador")Compradores com) {
	
		html.addAttribute("entidad","compradores");
		
		if (id!=null) {
			html.addAttribute("compradores", repositorio.findById(id).get());
			html.addAttribute("formColapsado", true);
		}else {
			html.addAttribute("formColapsado", false);
		}
		 
		if (buscar.isEmpty()) {
			html.addAttribute("lista", repositorio.findAll());
			html.addAttribute("tiposDocumentoComprador", tipoDocumentoCompradorRepositorio.findAll());
			
		}else {
			html.addAttribute("lista", repositorio.findBydocumentoIgnoreCaseContaining(buscar));
			html.addAttribute("tiposDocumentoComprador", tipoDocumentoCompradorRepositorio.findByDescripcionIgnoreCaseContaining(buscar));
			
		}
		return "form_compradores";
	}
	
	@PostMapping
	public String guardar(@ModelAttribute("comprador") Compradores com) {
		repositorio.save(com);
		
		return "redirect:/compradores";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		Compradores com= new Compradores();
		com.setId(id);		
		repositorio.delete(com);
		return "redirect:/compradores";
	}


	
	
		
	
	

}
