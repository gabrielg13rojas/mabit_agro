package py.com.mabit.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import py.com.mabit.entidades.Usuarios;
import py.com.mabit.enums.EstadoAnimal;
import py.com.mabit.repositorios.AlimentosRepositorio;
import py.com.mabit.repositorios.AnimalesRepositorio;
import py.com.mabit.repositorios.UsuarioRepositorio;

@Controller
@RequestMapping({ "/dashboard", "/" })
public class DashboardControlador {
	@Autowired
	UsuarioRepositorio usuariosRep;
	@Autowired
	AlimentosRepositorio alimentosRep;
	@Autowired
	AnimalesRepositorio animalesRep;

	@GetMapping
	public String dashboard(Authentication authentication, Model model) {
		Optional<Usuarios> usu = usuariosRep.findByCorreo(authentication.getName());
		if (usu.isPresent()) {
			Usuarios us = usu.get();
			model.addAttribute("correo", us.getCorreo());
			model.addAttribute("idus", us.getId());
			model.addAttribute("foto", us.getFoto());
			model.addAttribute("nombre", us.getNombre());
			model.addAttribute("privilegio", us.getPrivilegio().getDescripcion());
		}
		model.addAttribute("alimentos", alimentosRep.findAll().size());
		model.addAttribute("animales", animalesRep.findByEstadoOrEstado(EstadoAnimal.VIVO, EstadoAnimal.ENFERMO).size());
		return "dashboard";
	}
}
