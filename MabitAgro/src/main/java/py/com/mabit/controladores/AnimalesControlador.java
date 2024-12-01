package py.com.mabit.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

import py.com.mabit.entidades.Animales;
import py.com.mabit.entidades.LotesOcupacion;
import py.com.mabit.entidades.MovimientosAnimales;
import py.com.mabit.entidades.Nacimientos;
import py.com.mabit.enums.EstadoAnimal;
import py.com.mabit.enums.EstadoLote;
import py.com.mabit.enums.SexoAnimal;
import py.com.mabit.enums.TipoMovimientoAnimal;
import py.com.mabit.repositorios.AnimalesRepositorio;
import py.com.mabit.repositorios.LoteOcupacionRepositorio;
import py.com.mabit.repositorios.LotesRepositorio;
import py.com.mabit.repositorios.MovimientoAnimalesRepositorio;
import py.com.mabit.repositorios.NacimientosRepositorio;
import py.com.mabit.repositorios.RazasRepositorio;

@Controller
@RequestMapping("/animales")
public class AnimalesControlador {
	@Autowired
	AnimalesRepositorio repositorio;
	@Autowired
	NacimientosRepositorio repositorioNacimiento;
	@Autowired
	RazasRepositorio repositorioRaza;
	@Autowired
	LotesRepositorio repositorioLote;
	@Autowired
	LoteOcupacionRepositorio repositorioOcupacionLote;
	@Autowired 
	MovimientoAnimalesRepositorio repositorioMovimientos;

	@GetMapping({ "", "/editar/{id}" })
	public String formulario(Model html, @PathVariable(required = false) Long id,
			@RequestParam(defaultValue = "") String buscar, @ModelAttribute("animal") Animales animal) {
		html.addAttribute("entidad", "animales");
		html.addAttribute("estados", EstadoAnimal.values());
		html.addAttribute("razas", repositorioRaza.findAll());
		html.addAttribute("sexos", SexoAnimal.values());
		List<EstadoAnimal> estadosFiltro = new ArrayList<>();
		estadosFiltro.add(EstadoAnimal.VIVO);
		estadosFiltro.add(EstadoAnimal.ENFERMO);
		html.addAttribute("padres", repositorio.findBySexoYEstado(SexoAnimal.MACHO, estadosFiltro));
		html.addAttribute("madres", repositorio.findBySexoYEstado(SexoAnimal.HEMBRA, estadosFiltro));
		html.addAttribute("lotes", repositorioLote.findByEstado(EstadoLote.DISPONIBLE));
		if (id != null) {
			html.addAttribute("animal", repositorio.findById(id).get());
			html.addAttribute("formColapsado", true);

			Optional<Nacimientos> nacimiento = repositorioNacimiento.findByCriaId(id);
			if (nacimiento.isPresent()) {
				html.addAttribute("padre", nacimiento.get().getPadre().getId());
				html.addAttribute("madre", nacimiento.get().getMadre().getId());
				html.addAttribute("observaciones", nacimiento.get().getObservaciones());
			}
		} else {
			html.addAttribute("formColapsado", false);
		}

		if (buscar.isEmpty()) {
			html.addAttribute("lista", repositorio.findAll());
		} else {
			html.addAttribute("lista",
					repositorio.findByIdentificadorIgnoreCaseContainingOrAliasIgnoreCaseContaining(buscar, buscar));
		}
		return "form_animales";
	}

	@PostMapping
	public String guardar(@ModelAttribute("animal") Animales ani, @RequestParam(defaultValue = "0") Long padre,
			@RequestParam(defaultValue = "0") Long madre, @RequestParam(defaultValue = "") String observaciones,
			@RequestParam(defaultValue = "") String observacionesLote, @RequestParam(defaultValue = "0") Long lote) {
		Animales animalGuardado = repositorio.save(ani);
		if (lote > 0) {
			LotesOcupacion ocupacion = new LotesOcupacion();
			ocupacion.setAnimal(animalGuardado);
			ocupacion.getLote().setId(lote);
			repositorioOcupacionLote.save(ocupacion);
			MovimientosAnimales mov = new MovimientosAnimales();
			mov.setAnimal(animalGuardado);
			mov.setFecha(LocalDate.now());
			mov.getLote().setId(lote);
			mov.setObservaciones(observacionesLote);
			mov.setPeso(animalGuardado.getPeso());
			mov.setTipo(TipoMovimientoAnimal.ENTRADA);
			repositorioMovimientos.save(mov);
		}
		if (madre > 0 && padre > 0) {
			Optional<Nacimientos> nacimiento = repositorioNacimiento.findByCriaId(animalGuardado.getId());
			Nacimientos nac = nacimiento.isPresent() ? nacimiento.get() : new Nacimientos();
			nac.setCria(animalGuardado);
			nac.setMadre(repositorio.findById(madre).get());
			nac.setPadre(repositorio.findById(padre).get());
			nac.setObservaciones(observaciones);
			repositorioNacimiento.save(nac);
		}
		return "redirect:/animales";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		Animales ani = new Animales();
		ani.setId(id);
		repositorio.delete(ani);
		return "redirect:/animales";
	}

}
