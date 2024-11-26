package py.com.mabit.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import py.com.mabit.entidades.Gastos;

import py.com.mabit.repositorios.GastosRepositorio;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/gastos")
public class GastosControlador {
	@Autowired
	GastosRepositorio repositorio;

	@GetMapping({ "", "/editar/{id}" })
	public String formulario(Model html, @PathVariable(required = false) Long id,
			@RequestParam(defaultValue = "") String buscar, @ModelAttribute("gasto") Gastos gas) {

		html.addAttribute("entidad", "gasto");

		if (id != null) {
			html.addAttribute("gasto", repositorio.findById(id).get());
			html.addAttribute("formColapsado", true);
		} else {
			html.addAttribute("formColapsado", false);
		}

		if (buscar.isEmpty()) {
			html.addAttribute("lista", repositorio.findAll());
			
		} else {
			html.addAttribute("lista", repositorio.findByBeneficiarioIgnoreCaseContaining(buscar));
	
		}
		return "form_gastos";
	}

	@PostMapping
	public String guardar(@ModelAttribute("gastos") Gastos gas) {
		repositorio.save(gas);

		return "redirect:/gastos";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		Gastos gas = new Gastos();
		gas.setId(id);
		repositorio.delete(gas);
		return "redirect:/gastos";
	}
}