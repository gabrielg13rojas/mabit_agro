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

import py.com.mabit.entidades.Privilegios;
import py.com.mabit.entidades.Servicios;

import py.com.mabit.repositorios.ServiciosRepositorio;

@Controller
@RequestMapping("/servicios")
public class ServiciosControlador {
@Autowired
ServiciosRepositorio repositorioServicios;

@GetMapping({ "", "/editar/{id}" })
public String formularioT(Model html, @PathVariable(required = false) Long id,
		@RequestParam(defaultValue = "") String buscar, @ModelAttribute("servicios") Servicios serv) {

	html.addAttribute("entidad", "Servicios");

	if (id != null) {
		html.addAttribute("servicios", repositorioServicios.findById(id).get());
		html.addAttribute("formColapsado", true);
	} else {
		html.addAttribute("formColapsado", false);
	}

	if (buscar.isEmpty()) {
		html.addAttribute("lista", repositorioServicios.findAll());
	} else {
		html.addAttribute("lista", repositorioServicios
				.findByDescripcionIgnoreCaseContaining(buscar));
	}

	return ("form_servicios");
}

@PostMapping
public String guardar(@ModelAttribute("privilegios") Servicios serv) {
	repositorioServicios.save(serv);

	return "redirect:/servicios";
}

@GetMapping("/eliminar/{id}")
public String eliminar(@PathVariable Long id) {
	Servicios serv = new Servicios();
	serv.setId(id);
	repositorioServicios.delete(serv);
	return "redirect:/servicios";
}
}
