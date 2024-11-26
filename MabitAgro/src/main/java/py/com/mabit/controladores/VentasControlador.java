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

import py.com.mabit.entidades.Alimentos;
import py.com.mabit.repositorios.CompradoresRepositorio;
import py.com.mabit.repositorios.TrabajadoresRepositorio;
import py.com.mabit.repositorios.VentasDetalleRepositorio;
import py.com.mabit.repositorios.VentasRepositorio;

@Controller
@RequestMapping("/ventas")
public class VentasControlador {
	@Autowired
	VentasRepositorio repositorioCabecera;
	@Autowired
	VentasDetalleRepositorio repositorioDetalle;
	@Autowired
	CompradoresRepositorio repositorioComprador;
	@Autowired
	TrabajadoresRepositorio repositorioTrabajador;

	@GetMapping
	public String formulario(Model html, @PathVariable(required = false) Long id,
			@RequestParam(defaultValue = "") String buscar, @ModelAttribute("detalle") VentasDetalleRepositorio ven) {

		return "form_ventas";
	}

	@PostMapping
	public String guardar(@ModelAttribute("alimento") Alimentos ali) {

		return "redirect:/alimentos";
	}
}