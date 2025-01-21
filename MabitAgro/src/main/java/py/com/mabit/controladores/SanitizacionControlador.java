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

import py.com.mabit.entidades.Insumos;
import py.com.mabit.entidades.Lotes;
import py.com.mabit.entidades.LotesOcupacion;
import py.com.mabit.entidades.Sanitizacion;
import py.com.mabit.entidades.StockInsumos;
import py.com.mabit.repositorios.LotesRepositorio;
import py.com.mabit.repositorios.SanitizacionRepositorio;
import py.com.mabit.repositorios.StockInsumosRepositorio;

@Controller
@RequestMapping("sanitizacion")
@SessionAttributes("listSanitizacion")
public class SanitizacionControlador {
	@Autowired
	LotesRepositorio repositorioLotes;
	@Autowired
	StockInsumosRepositorio repositorioInsumos;
	@Autowired
	SanitizacionRepositorio repositorio;

	@ModelAttribute("listSanitizacion")
	public ArrayList<Sanitizacion> inicializarLista() {
		return new ArrayList<Sanitizacion>();
	}

	@GetMapping
	public String alimentacion(Model html,
			@ModelAttribute("listSanitizacion") ArrayList<Sanitizacion> listSanitizacion,
			@RequestParam(defaultValue = "0") Long idLote, String insuExist, String stockInsuf, String guardar,
			String limpiar) {
		if (limpiar != null) {
			listSanitizacion.clear();
		}
		if (guardar != null) {
			List<Sanitizacion> listDefSanit = new ArrayList<>();
			Lotes lot = repositorioLotes.findById(idLote).get();
			for (LotesOcupacion animales : lot.getListAnimales()) {
				for (Sanitizacion sanit : listSanitizacion) {
					Sanitizacion sa = new Sanitizacion();
					sa.setInsumo(sanit.getInsumo());
					sa.setAnimal(animales.getAnimal());
					sa.setCantidad(sanit.getCantidad() / lot.getListAnimales().size());
					sa.setCosto(sanit.getCosto());
					sa.setFecha(LocalDate.now());
					listDefSanit.add(sa);
				}
			}
			repositorio.saveAll(listDefSanit);
			List<Insumos> listdescontarStockInsu = new ArrayList<>();
			for (Sanitizacion sanitizacion : listSanitizacion) {
				Insumos insu = sanitizacion.getInsumo();
//				insu.setStock(insu.getStock() - sanitizacion.getCantidad());
//				listdescontarStockInsu.add(ali);
			}
//			repositorioInsumos.saveAll(listdescontarStockInsu);
			html.addAttribute("msg", "Se ha registrado la alimentación de los animales");
			listSanitizacion.clear();
			idLote = 0l;
		}
		if (idLote > 0) {
			Lotes lote = repositorioLotes.findById(idLote).get();
			html.addAttribute("loteObj", lote);
			double gasTotal = 0d;
			for (Sanitizacion sanitizacion : listSanitizacion) {
				//gasTotal += sanitizacion.getCantidad() * sanitizacion.getInsumo().getPrecioUnitario().doubleValue();
			}
			html.addAttribute("gastoxanimal", gasTotal / lote.getListAnimales().size());
			html.addAttribute("gastoTotal", gasTotal);
		} else {
			html.addAttribute("loteObj", new Lotes());
		}
	//	html.addAttribute("totalPesoAlim", listSanitizacion.stream().mapToDouble(Insumos::get).sum());
		html.addAttribute("listInsumos", repositorioInsumos.findAll());
		html.addAttribute("listaLotes", repositorioLotes.findAll());
		if (stockInsuf != null) {
			html.addAttribute("error", "No existe el stock suficiente para asignar este alimento");
		}
		if (insuExist != null) {
			html.addAttribute("error",
					"Ya existe este alimento en la lista. Si desea modificar la cantidad, quite de la lista de abajo y vuelve a agregar.");
		}
		return "form_sanitizacion";
	}

	@PostMapping("/addInsu")
	public String agregarAlimento(@RequestParam("insumo") Long idInsumo, @RequestParam("cantidad") Double cantidad,
			@ModelAttribute("listSanitizacion") ArrayList<Sanitizacion> listSanitizacion) {
		if (listSanitizacion.stream().noneMatch(in -> in.getInsumo().getId() == idInsumo)) {
			Sanitizacion ali = new Sanitizacion();
			List<StockInsumos> stin = repositorioInsumos.findByInsumoId(idInsumo);
			if (stin.size()>0) {
				ali.setInsumo(stin.get(0).getInsumo());				
			}
			ali.setCantidad(cantidad);
		//	ali.setCosto(ali.getInsumo().getPrecioUnitario());
			ali.setFecha(LocalDate.now());
			if (1000 < ali.getCantidad()) {
				return "redirect:/sanitizacion?stockInsuf=true";
			} else {
				listSanitizacion.add(ali);
			}
		} else {
			return "redirect:/sanitizacion?insuExist=true";
		}
		return "redirect:/sanitizacion";
	}

	@GetMapping("/quitar/{id}")
	public String quitarAlimento(@PathVariable("id") Long idInsumo,
			@ModelAttribute("listSanitizacion") ArrayList<Sanitizacion> listSanitizacion) {
		listSanitizacion.removeIf(sani -> sani.getInsumo().getId() == idInsumo);
		return "redirect:/sanitizacion";
	}
}