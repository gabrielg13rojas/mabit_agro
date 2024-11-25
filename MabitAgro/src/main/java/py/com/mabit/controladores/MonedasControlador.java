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

import py.com.mabit.entidades.Monedas;
import py.com.mabit.repositorios.MonedasRepositorio;

@Controller
@RequestMapping("/monedas")
public class MonedasControlador {
	@Autowired
	MonedasRepositorio repositorio;

	@GetMapping({ "", "/editar/{id}" })
	public String formulario(Model html, @PathVariable(required = false) Long id,
			@RequestParam(defaultValue = "") String buscar, @ModelAttribute("moneda") Monedas moneda) {
		html.addAttribute("entidad", "monedas");
		if (id != null) {
			html.addAttribute("moneda", repositorio.findById(id).get());
			html.addAttribute("formColapsado", true);
		} else {
			html.addAttribute("formColapsado", false);
		}

		if (buscar.isEmpty()) {
			html.addAttribute("lista", repositorio.findAll());
		} else {
			html.addAttribute("lista", repositorio.findByDescripcionIgnoreCaseContaining(buscar));
		}
		return "form_monedas";
	}

	@PostMapping
	public String guardar(@ModelAttribute("moneda") Monedas mon) {
		repositorio.save(mon);
		return "redirect:/monedas";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		Monedas mon = new Monedas();
		mon.setId(id);
		repositorio.delete(mon);
		return "redirect:/monedas";
	}

}
