package py.com.mabit.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import py.com.mabit.entidades.Privilegios;
import py.com.mabit.entidades.Usuarios;
import py.com.mabit.repositorios.UsuarioRepositorio;

@Controller
@RequestMapping("/login")
public class LoginControlador {
	@Autowired
	UsuarioRepositorio usuRepos;

	@GetMapping
	public String login(Model model, String error, String logout, String existe, String exito, String passnocoinc,
			String sessionExpired) {
		if (error != null)
			model.addAttribute("errorMsg", "Credenciales inválidas. Verifique y vuelva a intentar.");
		if (logout != null)
			model.addAttribute("logoutMsg", "Ha cerrado sesión exitosamente");
		if (existe != null)
			model.addAttribute("errorMsg", "Ya existe un usuario registrado con este correo electrónico");
		if (passnocoinc != null)
			model.addAttribute("errorMsg", "Las contraseñas ingresadas no coinciden");
		if (sessionExpired != null)
			model.addAttribute("errorMsg", "La sesión ha expirado");
		if (exito != null)
			model.addAttribute("logoutMsg", "Se ha registrado su cuenta con éxito");
		return "login";
	}

	@GetMapping("/resetear-password")
	public String resetearContrasenha(Model modelo, String existe, String noexist) {
		if (existe != null)
			modelo.addAttribute("exitoMsg", "Se ha enviado un código de recuperación a su correo. "
					+ "Verifíquelo y siga las instrucciones.");
		if (noexist != null)
			modelo.addAttribute("errorMsg", "No se ha encontrado un usuario registrado con este correo electrónico");
		return "resetear_password";
	}

	@PostMapping("/resetear-password")
	public String postMethodName(String email) {
		if (usuRepos.findByCorreo(email).isPresent()) {
			return "redirect:/login/resetear-password?existe=true";
		} else {
			return "redirect:/login/resetear-password?noexist=true";
		}
	}

	@PostMapping
	public String guardarUsuario(@ModelAttribute("objusu") Usuarios usu,
			@RequestParam("confirmarContrasenha") String confirmarContrasenha) {
		BCryptPasswordEncoder cifrado = new BCryptPasswordEncoder(12);

		if (usuRepos.findByCorreo(usu.getCorreo()).isPresent()) {
			return "redirect:/login?existe=true";
		} else if (cifrado.matches(cifrado.encode(confirmarContrasenha), usu.getContrasenha())) {
			return "redirect:/login?passnocoinc=true";
		} else {
			usu.setVerificado(true);
			usu.setBloqueado(false);
			usu.setFoto("");
			usu.setCodigoRecuperacion("");
			Privilegios p = new Privilegios();
			p.setId(1l);
			usu.setPrivilegio(p);
			usuRepos.save(usu);
			return "redirect:/login?exito=true";
		}
	}
}