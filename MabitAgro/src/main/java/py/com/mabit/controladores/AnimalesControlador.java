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

import py.com.mabit.entidades.Animales;
import py.com.mabit.enums.EspecieAnimal;
import py.com.mabit.enums.EstadoAnimal;
import py.com.mabit.enums.SexoAnimal;
import py.com.mabit.repositorios.AnimalesRepositorio;

@Controller
@RequestMapping("/animales")
public class AnimalesControlador {
	@Autowired
	AnimalesRepositorio repositorio;

	@GetMapping({ "", "/editar/{id}" })
	public String formulario(Model html, @PathVariable(required = false) Long id,
			@RequestParam(defaultValue = "") String buscar, @ModelAttribute("animal") Animales animal) {
		html.addAttribute("entidad", "animales");
		html.addAttribute("estados", EstadoAnimal.values());
		html.addAttribute("especies", EspecieAnimal.values());
		html.addAttribute("sexos", SexoAnimal.values());
		if (id != null) {
			html.addAttribute("animal", repositorio.findById(id).get());
			html.addAttribute("formColapsado", true);
		} else {
			html.addAttribute("formColapsado", false);
		}

		if (buscar.isEmpty()) {
			html.addAttribute("lista", repositorio.findAll());
		} else {
			html.addAttribute("lista",
					repositorio.findByIdentificadorIgnoreCaseContainingOrAliasIgnoreCaseContaining(buscar, buscar));
		}
		return "form_animales";
	}

	@PostMapping
	public String guardar(@ModelAttribute("animal") Animales ani) {
		repositorio.save(ani);
		return "redirect:/animales";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		Animales ani = new Animales();
		ani.setId(id);
		repositorio.delete(ani);
		return "redirect:/animales";
	}

}
