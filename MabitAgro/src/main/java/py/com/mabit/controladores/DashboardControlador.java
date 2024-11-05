package py.com.mabit.controladores;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/dashboard","/"})
public class DashboardControlador {
	@GetMapping
	public String dashboard(Authentication authentication, Model model) {
		// Obtener usuario actual
		String correo = authentication.getName();
		model.addAttribute("correo", correo);
		return "dashboard";
	}
}
