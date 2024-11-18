package py.com.mabit.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import py.com.mabit.entidades.Usuarios;
import py.com.mabit.repositorios.UsuarioRepositorio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/usuarios")
public class UsuariosControlador {
	@Autowired
	UsuarioRepositorio repositorio;

	@GetMapping({ "", "/editar/{id}" })
	public String formulario(Model html, @PathVariable(required = false) Long id,
			@RequestParam(defaultValue = "") String buscar, @ModelAttribute("usuario") Usuarios usu) {
		html.addAttribute("entidad", "Usuarios");
		if (id != null) {
			html.addAttribute("usuario", repositorio.findById(id).get());
			html.addAttribute("formColapsado", true);
		} else {
			html.addAttribute("formColapsado", false);
		}
		if (buscar.isEmpty()) {
			html.addAttribute("lista", repositorio.findAll());
		} else {
			html.addAttribute("lista", repositorio.findByCorreoIgnoreCaseContaining(buscar));
		}

		return "form_usuarios";
	}

	@PostMapping
	public String guardar(@ModelAttribute("usuario") Usuarios u) {
		repositorio.save(u);
		return "redirect:/usuarios";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		Usuarios us = new Usuarios();
		us.setId(id);
		System.out.println(id+"**********************");
		repositorio.delete(us);
		return "redirect:/usuarios";
	}

}
