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

import py.com.mabit.entidades.Razas;
import py.com.mabit.enums.EspecieAnimal;
import py.com.mabit.repositorios.RazasRepositorio;

@Controller
@RequestMapping("/razas")
public class RazasControlador {
	@Autowired
	RazasRepositorio repositorio;

	@GetMapping({ "", "/editar/{id}" })
	public String formulario(Model html, @PathVariable(required = false) Long id,
			@RequestParam(defaultValue = "") String buscar, @ModelAttribute("raza") Razas raza) {
		html.addAttribute("entidad", "razas");
		html.addAttribute("especies", EspecieAnimal.values());
		if (id != null) {
			html.addAttribute("raza", repositorio.findById(id).get());
			html.addAttribute("formColapsado", true);
		} else {
			html.addAttribute("formColapsado", false);
		}

		if (buscar.isEmpty()) {
			html.addAttribute("lista", repositorio.findAll());
		} else {
			html.addAttribute("lista", repositorio.findByDescripcionIgnoreCaseContaining(buscar));
		}
		return "form_razas";
	}

	@PostMapping
	public String guardar(@ModelAttribute("raza") Razas raz) {
		repositorio.save(raz);
		return "redirect:/razas";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		Razas raz = new Razas();
		raz.setId(id);
		repositorio.delete(raz);
		return "redirect:/razas";
	}

}
