package py.com.mabit.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import py.com.mabit.entidades.Alimentacion;
import py.com.mabit.entidades.Alimentos;
import py.com.mabit.entidades.Lotes;
import py.com.mabit.entidades.LotesOcupacion;
import py.com.mabit.repositorios.AlimentacionRepositorio;
import py.com.mabit.repositorios.AlimentosRepositorio;
import py.com.mabit.repositorios.LotesRepositorio;

@Controller
@RequestMapping("alimentacion")
@SessionAttributes("listAlimentaciones")
public class AlimentacionControlador {
	@Autowired
	LotesRepositorio repositorioLotes;
	@Autowired
	AlimentosRepositorio repositorioAlimentos;
	@Autowired
	AlimentacionRepositorio repositorio;

	@ModelAttribute("listAlimentaciones")
	public ArrayList<Alimentacion> inicializarLista() {
		return new ArrayList<>();
	}

	@GetMapping
	public String alimentacion(Model html,
			@ModelAttribute("listAlimentaciones") ArrayList<Alimentacion> listAlimentaciones,
			@RequestParam(defaultValue = "0") Long idLote, String alimExist, String stockInsuf, String guardar,
			String limpiar) {
		if (limpiar != null) {
			listAlimentaciones.clear();
		}
		if (guardar != null) {
			List<Alimentacion> listDefAlim = new ArrayList<>();
			Lotes lot = repositorioLotes.findById(idLote).get();
			for (LotesOcupacion animales : lot.getListAnimales()) {
				for (Alimentacion alimentacion : listAlimentaciones) {
					Alimentacion al = new Alimentacion();
					al.setAlimento(alimentacion.getAlimento());
					al.setAnimal(animales.getAnimal());
					al.setCantidad(alimentacion.getCantidad() / lot.getListAnimales().size());
					al.setCosto(alimentacion.getCosto());
					al.setFecha(LocalDate.now());
					listDefAlim.add(al);
				}
			}
			repositorio.saveAll(listDefAlim);
			List<Alimentos> listdescontarStockAlim = new ArrayList<>();
			for (Alimentacion alimentacion : listAlimentaciones) {
				Alimentos ali = alimentacion.getAlimento();
				ali.setStock(ali.getStock() - alimentacion.getCantidad());
				listdescontarStockAlim.add(ali);
			}
			repositorioAlimentos.saveAll(listdescontarStockAlim);
			html.addAttribute("msg", "Se ha registrado la alimentación de los animales");
			listAlimentaciones.clear();
			idLote = 0l;
		}
		if (idLote > 0) {
			Lotes lote = repositorioLotes.findById(idLote).get();
			html.addAttribute("loteObj", lote);
			double gasTotal = 0d;
			for (Alimentacion alimentacion : listAlimentaciones) {
				gasTotal += alimentacion.getCantidad() * alimentacion.getAlimento().getPrecioUnitario().doubleValue();
			}
			html.addAttribute("gastoxanimal", gasTotal / lote.getListAnimales().size());
			html.addAttribute("gastoTotal", gasTotal);
		} else {
			html.addAttribute("loteObj", new Lotes());
		}
		html.addAttribute("totalPesoAlim", listAlimentaciones.stream().mapToDouble(Alimentacion::getCantidad).sum());
		html.addAttribute("listAlimentos", repositorioAlimentos.findAll());
		html.addAttribute("listaLotes", repositorioLotes.findAll());
		if (stockInsuf != null) {
			html.addAttribute("error", "No existe el stock suficiente para asignar este alimento");
		}
		if (alimExist != null) {
			html.addAttribute("error",
					"Ya existe este alimento en la lista. Si desea modificar la cantidad, quite de la lista de abajo y vuelve a agregar.");
		}
		return "form_alimentacion";
	}

	@PostMapping("/addAlim")
	public String agregarAlimento(@RequestParam("alimento") Long idAlimento, @RequestParam("cantidad") Double cantidad,
			@ModelAttribute("listAlimentaciones") ArrayList<Alimentacion> listAlimentaciones) {
		if (listAlimentaciones.stream().noneMatch(a -> a.getAlimento().getId() == idAlimento)) {
			Alimentacion ali = new Alimentacion();
			ali.setAlimento(repositorioAlimentos.findById(idAlimento).get());
			ali.setCantidad(cantidad);
			ali.setCosto(ali.getAlimento().getPrecioUnitario());
			ali.setFecha(LocalDate.now());
			if (ali.getAlimento().getStock() < ali.getCantidad()) {
				return "redirect:/alimentacion?stockInsuf=true";
			} else {
				listAlimentaciones.add(ali);
			}
		} else {
			return "redirect:/alimentacion?alimExist=true";
		}
		return "redirect:/alimentacion";
	}

	@GetMapping("/quitar/{id}")
	public String quitarAlimento(@PathVariable("id") Long idAlimento,
			@ModelAttribute("listAlimentaciones") ArrayList<Alimentacion> listAlimentaciones) {
		listAlimentaciones.removeIf(a -> a.getAlimento().getId() == idAlimento);
		return "redirect:/alimentacion";
	}
}