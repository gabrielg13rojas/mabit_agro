package py.com.mabit.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import py.com.mabit.entidades.Nacimientos;
import py.com.mabit.entidades.Trabajadores;
import py.com.mabit.repositorios.NacimientosRepositorio;

@Controller
@RequestMapping("/nacimientos")
public class NacimientosControlador {
@Autowired
NacimientosRepositorio repositorio;

@GetMapping({ "", "/editar/{id}" })
public String formularioT(Model html, @PathVariable(required = false) Long id,
		@RequestParam(defaultValue = "") String buscar, @ModelAttribute("nacimientos") Nacimientos nac) {

	html.addAttribute("entidad", "Nacimientos");
	if (id != null) {
		html.addAttribute("nacimientos", repositorio.findById(id).get());
		html.addAttribute("formColapsado", true);
	} else {
		html.addAttribute("formColapsado", false);
	}

	if (buscar.isEmpty()) {
		html.addAttribute("lista", repositorio.findAll());
	} else {
		html.addAttribute("lista", repositorio
				.findByObservacionesIgnoreCaseContaining(buscar));
	}

	return ("form_nacimientos");
}
}

