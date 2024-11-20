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

import py.com.mabit.entidades.Trabajadores;
import py.com.mabit.repositorios.TrabajadoresRepositorio;

@Controller
@RequestMapping("/trabajadores")
public class TrabajadoresControlador {
	@Autowired
	TrabajadoresRepositorio repositorio;

	@GetMapping({ "", "/editar/{id}" })
	public String formularioT(Model html, @PathVariable(required = false) Long id,
			@RequestParam(defaultValue = "") String buscar, @ModelAttribute("trabajadores") Trabajadores trab) {

		html.addAttribute("entidad", "Trabajadores");

		if (id != null) {
			html.addAttribute("trabajadores", repositorio.findById(id).get());
			html.addAttribute("formColapsado", true);
		} else {
			html.addAttribute("formColapsado", false);
		}

		if (buscar.isEmpty()) {
			html.addAttribute("lista", repositorio.findAll());
		} else {
			html.addAttribute("lista", repositorio
					.findByNombreIgnoreCaseContainingOrApellidoIgnoreCaseContainingOrCedula(buscar, buscar, buscar));
		}

		return ("form_trabajadores");
	}

	@PostMapping
	public String guardar(@ModelAttribute("trabajadores") Trabajadores trab) {
		repositorio.save(trab);

		return "redirect:/trabajadores";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		Trabajadores trab = new Trabajadores();
		trab.setId(id);
		repositorio.delete(trab);
		return "redirect:/trabajadores";
	}
}