package py.com.mabit.controladores;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
	public String sanitizacion(Model html, @ModelAttribute("listSanitizacion") ArrayList<Sanitizacion> listSanitizacion,
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
					sa.setLoteInsumo(sanit.getLoteInsumo());
					sa.setAnimal(animales.getAnimal());
					sa.setCantidad(sanit.getCantidad().divide(new BigDecimal(lot.getListAnimales().size()), 2,
							RoundingMode.HALF_UP));
					sa.setCosto(sanit.getCosto().multiply(sa.getCantidad()));
					sa.setFecha(LocalDate.now());
					listDefSanit.add(sa);
				}
			}
			repositorio.saveAll(listDefSanit);
			List<StockInsumos> listdescontarStockInsu = new ArrayList<>();
			List<StockInsumos> listaInsumos = repositorioInsumos.findAll();
			for (Sanitizacion sanitizacion : listSanitizacion) {
				for (StockInsumos stockInsumos : listaInsumos) {
					if (stockInsumos.getInsumo().getId() == sanitizacion.getInsumo().getId()
							&& stockInsumos.getLote().equals(sanitizacion.getLoteInsumo())) {
						StockInsumos insu = stockInsumos;
						insu.setStock(insu.getStock().subtract(sanitizacion.getCantidad()));
						listdescontarStockInsu.add(insu);
					}
				}
			}
			repositorioInsumos.saveAll(listdescontarStockInsu);
			html.addAttribute("msg", "Se ha registrado la sanitización de los animales");
			listSanitizacion.clear();
			idLote = 0l;
		}
		if (idLote > 0) {
			Lotes lote = repositorioLotes.findById(idLote).get();
			html.addAttribute("loteObj", lote);
			BigDecimal gasTotal = new BigDecimal(0d);
			for (Sanitizacion sanitizacion : listSanitizacion) {
				gasTotal.add(sanitizacion.getCantidad().multiply(sanitizacion.getInsumo().getPrecioUnitario()));
			}
			html.addAttribute("gastoxanimal",
					gasTotal.divide(new BigDecimal(lote.getListAnimales().size()), 2, RoundingMode.HALF_UP));
			html.addAttribute("gastoTotal", gasTotal);
		} else {
			html.addAttribute("loteObj", new Lotes());
		}
		html.addAttribute("totalPesoAlim",
				listSanitizacion.stream().map(Sanitizacion::getCantidad).reduce(BigDecimal.ZERO, BigDecimal::add));
		html.addAttribute("listInsumos", repositorioInsumos.findAll());
		html.addAttribute("listaLotes", repositorioLotes.findAll());
		if (stockInsuf != null) {
			html.addAttribute("error", "No existe el stock suficiente para asignar este insumo");
		}
		if (insuExist != null) {
			html.addAttribute("error",
					"Ya existe este insumo en la lista. Si desea modificar la cantidad, quite de la lista de abajo y vuelve a agregar.");
		}
		return "form_sanitizacion";
	}

	@PostMapping("/addInsu")
	public String agregarInsumo(@RequestParam("insumo") Long idInsumo, @RequestParam("cantidad") BigDecimal cantidad,
			@ModelAttribute("listSanitizacion") ArrayList<Sanitizacion> listSanitizacion) {
		if (listSanitizacion.stream().noneMatch(in -> in.getId() == idInsumo)) {
			Sanitizacion sani = new Sanitizacion();
			StockInsumos stin = repositorioInsumos.findById(idInsumo).get();
			sani.setInsumo(stin.getInsumo());
			sani.setCosto(sani.getInsumo().getPrecioUnitario());
			sani.setLoteInsumo(stin.getLote());
			sani.setCantidad(cantidad);
			sani.setFecha(LocalDate.now());
			BigDecimal sumaCant = listSanitizacion.stream()
					.filter(sanit -> sanit.getInsumo().getId() == stin.getInsumo().getId()
							&& sanit.getLoteInsumo().equals(stin.getLote()))
					.map(Sanitizacion::getCantidad).reduce(BigDecimal.ZERO, BigDecimal::add);
			if (stin.getStock().compareTo(sumaCant.add(cantidad)) < 0) {
				return "redirect:/sanitizacion?stockInsuf=true";
			} else {
				listSanitizacion.add(sani);
			}
		} else {
			return "redirect:/sanitizacion?insuExist=true";
		}
		return "redirect:/sanitizacion";
	}

	@GetMapping("/quitar/{id}")
	public String quitarInsumo(@PathVariable("id") Long idInsumo,
			@RequestParam(value = "loteInsumo", defaultValue = "") String loteInsumo,
			@ModelAttribute("listSanitizacion") ArrayList<Sanitizacion> listSanitizacion) {
		listSanitizacion
				.removeIf(sani -> sani.getInsumo().getId() == idInsumo && sani.getLoteInsumo().equals(loteInsumo));
		return "redirect:/sanitizacion";
	}
}