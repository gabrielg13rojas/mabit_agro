package py.com.mabit.controladores;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import py.com.mabit.entidades.Usuarios;
import py.com.mabit.repositorios.PrivilegiosRepositorio;
import py.com.mabit.repositorios.UsuarioRepositorio;

@Controller
@RequestMapping("/usuarios")
public class UsuariosControlador {
	@Autowired
	UsuarioRepositorio repositorio;
	@Autowired
	PrivilegiosRepositorio privilegiosRep;

	@GetMapping({ "", "/editar/{id}" })
	public String formulario(Model html, @PathVariable(required = false) Long id,
			@RequestParam(defaultValue = "") String buscar, @ModelAttribute("usuario") Usuarios usu) {
		html.addAttribute("entidad", "Usuarios");
		html.addAttribute("privilegios", privilegiosRep.findAll());
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
		Optional<Usuarios> opt = repositorio.findByCorreo(u.getCorreo());
		if (opt.isPresent()) {
			u.setFoto(opt.get().getFoto());
		}
		repositorio.save(u);
		return "redirect:/usuarios";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		Usuarios us = new Usuarios();
		us.setId(id);
		repositorio.delete(us);
		return "redirect:/usuarios";
	}

	@GetMapping("/perfil/{id}")
	public String perfilUsuario(@PathVariable Long id, Principal sesion, Model modelo) {
		String usuario = sesion.getName();
		Optional<Usuarios> op = repositorio.findByCorreo(usuario);
		if (op.isPresent()) {
			if (id == op.get().getId()) {
				modelo.addAttribute("usu", op.get());
				return "form_perfil_usuario";
			} else {
				return "redirect:/" + id;
			}
		} else {
			return "redirect:/" + id;
		}
	}

	@PostMapping("/perfil/actualizar")
	public String actualizarPerfil(@ModelAttribute("usu") Usuarios usua) {
		Optional<Usuarios> u = repositorio.findById(usua.getId());
		if (u.isPresent()) {
			Usuarios objaisl = u.get();
			objaisl.setCorreo(usua.getCorreo());
			objaisl.setFoto(usua.getFoto());
			if (!usua.getContrasenha().isEmpty()) {
				objaisl.setContrasenha(usua.getContrasenha());
			}
			objaisl.setNombre(usua.getNombre());
			repositorio.save(objaisl);
		}

		return "redirect:/";
	}

}
