package py.com.mabit.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import py.com.mabit.entidades.Mortandad;


@Controller
@RequestMapping("/mortandad")
public class MortandadControlador {

	@GetMapping("/individual")
	public String getMethodName(Model html,@ModelAttribute("mortandad") Mortandad mortandad) {
		return "form_mortandad_individual";
	}
	
	
}
