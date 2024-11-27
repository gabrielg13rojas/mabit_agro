package py.com.mabit.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import py.com.mabit.entidades.Insumos;
import py.com.mabit.repositorios.InsumosRepositorio;
import py.com.mabit.repositorios.UnidadMedidaRepositorio;
@Controller
@RequestMapping("/insumos")
public class InsumosControlador {

    @Autowired
InsumosRepositorio repositorio;
    @Autowired
	UnidadMedidaRepositorio unidadMedidaRepositorio;

    @GetMapping({ "", "/editar/{id}" })
    public String formulario(Model html,
    		@PathVariable(required = false) Long id,
            @RequestParam(defaultValue = "") String buscar, 
            @ModelAttribute("insumo") Insumos insu) {
        html.addAttribute("entidad", "Insumos");
        if (id != null) {
        	html.addAttribute("insumo", repositorio.findById(id).get());
            html.addAttribute("formColapsado", true);
        } else {
            html.addAttribute("formColapsado", false);
        }

        if (buscar.isEmpty()) {
            html.addAttribute("lista", repositorio.findAll());
            html.addAttribute("unidadesMedida", unidadMedidaRepositorio.findAll());
        } else {
            html.addAttribute("lista", repositorio.findByDescripcionIgnoreCaseContaining(buscar));
            html.addAttribute("unidadesMedida", unidadMedidaRepositorio.findByDescripcionIgnoreCaseContaining(buscar)); 
        }

        return "form_insumos";
    }

    @PostMapping
   
    public String guardar(@ModelAttribute("insumo") Insumos i) {
        repositorio.save(i); // Guarda en la base de datos
        return "redirect:/insumos";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        repositorio.deleteById(id);
        return "redirect:/insumos";
    }
}
