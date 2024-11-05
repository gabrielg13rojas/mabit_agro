package py.com.mabit.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioControlador {
	@GetMapping
	public String verRegistroUsuarios(Model model) {
		return "form_usuarios";
	}

}
