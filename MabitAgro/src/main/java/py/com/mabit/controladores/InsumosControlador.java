package py.com.mabit.controladores;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import py.com.mabit.entidades.Insumos;
import py.com.mabit.entidades.Proveedores;
import py.com.mabit.entidades.StockInsumos;
import py.com.mabit.repositorios.InsumosRepositorio;
import py.com.mabit.repositorios.ProveedoresRepositorio;
import py.com.mabit.repositorios.StockInsumosRepositorio;
import py.com.mabit.repositorios.UnidadMedidaRepositorio;

@Controller
@RequestMapping("/insumos")
public class InsumosControlador {

	@Autowired
	InsumosRepositorio repositorio;
	@Autowired
	StockInsumosRepositorio stockRepositorio;
	@Autowired
	UnidadMedidaRepositorio unidadMedidaRepositorio;
	@Autowired
	ProveedoresRepositorio proveedoresRepositorio;

	@GetMapping({ "", "/editar/{id}" })
	public String formulario(Model html, @PathVariable(required = false) Long id,
			@RequestParam(defaultValue = "") String buscar, @ModelAttribute("insumo") Insumos insu) {
		html.addAttribute("entidad", "Insumos");
		if (id != null) {
			html.addAttribute("insumo", repositorio.findById(id).get());
			html.addAttribute("historialStock", stockRepositorio.findByInsumoId(id));
			html.addAttribute("formColapsado", true);
		} else {
			html.addAttribute("formColapsado", false);
		}

		if (buscar.isEmpty()) {
			html.addAttribute("lista", repositorio.findAll());
		} else {
			html.addAttribute("lista", repositorio.findByDescripcionIgnoreCaseContaining(buscar));
		}
		html.addAttribute("unidadesMedida", unidadMedidaRepositorio.findAll());
		html.addAttribute("proveedores", proveedoresRepositorio.findAll());

		return "form_insumos";
	}

	@PostMapping
	public String guardar(@ModelAttribute("insumo") Insumos i, String lote, LocalDate vencimiento, BigDecimal stock,
			Long proveedor) {
		if (i != null) {
			repositorio.save(i);
		}
		System.out.println("Stock: " + stock);
		System.out.println("Lote: " + lote);
		System.out.println("Venc: " + vencimiento);
		System.out.println("Proveedor: " + proveedor);
		if (lote != null && vencimiento != null && stock != null && proveedor != null) {
			StockInsumos st = new StockInsumos();
			st.setInsumo(i);
			st.setLote(lote);
			st.setStock(stock);
			st.setVencimiento(vencimiento);
			Proveedores prov = new Proveedores();
			prov.setId(proveedor);
			st.setProveedor(prov);
			stockRepositorio.save(st);
		}
		return "redirect:/insumos";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		repositorio.deleteById(id);
		return "redirect:/insumos";
	}
}
