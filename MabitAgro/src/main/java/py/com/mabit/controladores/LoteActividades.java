package py.com.mabit.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import py.com.mabit.entidades.Alimentacion;
import py.com.mabit.entidades.Alimentos;
import py.com.mabit.entidades.Lotes;
import py.com.mabit.repositorios.AlimentosRepositorio;
import py.com.mabit.repositorios.LotesRepositorio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("lote-actividades")
@SessionAttributes("listaAlimentosAsignados")
public class LoteActividades {
	@ModelAttribute("listaAlimentosAsignados")
	public List<Alimentacion> getListaAlimentosAsignados() {
		return new ArrayList<>();
	}

	@Autowired
	LotesRepositorio repositorio;
	@Autowired
	AlimentosRepositorio repositorioAlimentos;

	@GetMapping({ "alimentacion", "/alimentacion/aggAlim/{id}" })
	public String alimentacion(Model html, @ModelAttribute("alimentac") Alimentacion alimenta, Long idLote,
			String acorLote, Long alim, Double cant,
			@ModelAttribute("listaAlimentosAsignados") List<Alimentacion> alimAsignado) {
		List<Lotes> listLotes = null;
		Lotes l = new Lotes();
		List<Alimentos> listAlimentos = repositorioAlimentos.findAll();

		if (idLote == null || idLote == 0) {
			listLotes = repositorio.findAll();
		} else {
			listLotes = new ArrayList<>();
			l = repositorio.findById(idLote).get();
			listLotes.add(l);
		}
		html.addAttribute("listAlimentos", listAlimentos);
		html.addAttribute("lts", l);
		html.addAttribute("listaLotes", listLotes);
		html.addAttribute("alimentaciones", alimAsignado);
		return "form_lote_actividades";
	}
}