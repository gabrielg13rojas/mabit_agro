package py.com.mabit.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


import py.com.mabit.entidades.Alimentos;
import py.com.mabit.repositorios.AlimentosRepositorio;
import py.com.mabit.repositorios.ProveedorRepositorio;
import py.com.mabit.repositorios.UnidadMedidaRepositorio;

import org.springframework.web.bind.annotation.PostMapping;




@Controller
@RequestMapping("/alimentos")
public class AlimentosControlador {
	@Autowired
	AlimentosRepositorio repositorio;
	@Autowired
	ProveedorRepositorio proveedorRepositorio;
	@Autowired
	UnidadMedidaRepositorio unidadMedidaRepositorio;

		@GetMapping({"","/editar/{id}"})
	public String formulario(Model html,@PathVariable(required = false)Long id,@RequestParam( defaultValue = "")String buscar,@ModelAttribute("alimento")Alimentos ali) {
	
		html.addAttribute("entidad","alimentos");
		
		if (id!=null) {
			html.addAttribute("alimento", repositorio.findById(id).get());
			html.addAttribute("formColapsado", true);
		}else {
			html.addAttribute("formColapsado", false);
		}
		 
		if (buscar.isEmpty()) {
			html.addAttribute("lista", repositorio.findAll());
			html.addAttribute("proveedores", proveedorRepositorio.findAll()); 
			html.addAttribute("unidadesMedida", unidadMedidaRepositorio.findAll());
		}else {
			html.addAttribute("lista", repositorio.findByDescripcionIgnoreCaseContaining(buscar));
			 html.addAttribute("proveedores", proveedorRepositorio.findByNombreIgnoreCaseContaining(buscar));
			html.addAttribute("unidadesMedida", unidadMedidaRepositorio.findByDescripcionIgnoreCaseContaining(buscar));  
		}
		return "form_alimentos";
	}
	
	@PostMapping
	public String guardar(@ModelAttribute("alimento") Alimentos ali) {
		repositorio.save(ali);
		
		return "redirect:/alimentos";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		Alimentos ali= new Alimentos();
		ali.setId(id);		
		repositorio.delete(ali);
		return "redirect:/alimentos";
	}


	
	
		
	
	

}
