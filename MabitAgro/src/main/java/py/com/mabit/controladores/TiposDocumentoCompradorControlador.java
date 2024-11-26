package py.com.mabit.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import py.com.mabit.entidades.TiposDocumentoComprador;
import py.com.mabit.repositorios.TiposDocumentoCompradorRepositorio;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/tipo-doc-comprador")
public class TiposDocumentoCompradorControlador {
	@Autowired
	TiposDocumentoCompradorRepositorio repositorio;

	@GetMapping({ "", "/editar/{id}" })
	public String formulario(Model html, @PathVariable(required = false) Long id,
			@RequestParam(defaultValue = "") String buscar,
			@ModelAttribute("TiposDocumentoComprador") TiposDocumentoComprador docu) {
		html.addAttribute("entidad", "Tipos de documentos (compradores)");
		if (id != null) {
			html.addAttribute("TiposDocumentoComprador", repositorio.findById(id).get());
			html.addAttribute("formColapsado", true);
		} else {
			html.addAttribute("formColapsado", false);
		}
		if (buscar.isEmpty()) {
			html.addAttribute("lista", repositorio.findAll());

		} else {
			html.addAttribute("lista", repositorio.findByDescripcionIgnoreCaseContaining(buscar));

		}
		return "form_tipo_doc_comprador";
	}

	@PostMapping
	public String guardar(@ModelAttribute("TiposDocumentoComprador") TiposDocumentoComprador docu) {
		repositorio.save(docu);

		return "redirect:/tipo-doc-comprador";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		TiposDocumentoComprador docu = new TiposDocumentoComprador();
		docu.setId(id);
		repositorio.delete(docu);
		return "redirect:/tipo-doc-comprador";
	}
}