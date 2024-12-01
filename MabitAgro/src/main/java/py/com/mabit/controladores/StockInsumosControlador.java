package py.com.mabit.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import py.com.mabit.entidades.StockInsumos;
import py.com.mabit.repositorios.StockInsumosRepositorio;
import py.com.mabit.repositorios.InsumosRepositorio;
import py.com.mabit.repositorios.ProveedorRepositorio;

@Controller
@RequestMapping("/stockInsumos")
public class StockInsumosControlador {

    @Autowired
    StockInsumosRepositorio repositorio;

    @Autowired
     InsumosRepositorio insumosRepositorio;

    @Autowired
     ProveedorRepositorio proveedorRepositorio;

    @GetMapping({ "", "/editar/{id}" })
    public String formulario(Model html, 
                             @PathVariable(required = false) Long id,
                             @RequestParam(defaultValue = "") String buscar, 
                             @ModelAttribute("stockInsumo") StockInsumos stockInsu) {
        html.addAttribute("entidad", "Stock de Insumos");
        if (id != null) {
            html.addAttribute("stockInsumo", repositorio.findById(id).get());
            html.addAttribute("formColapsado", true);
        } else {
            html.addAttribute("formColapsado", false);
        }

        if (buscar.isEmpty()) {
            html.addAttribute("lista", repositorio.findAll());
            html.addAttribute("insumo", insumosRepositorio.findAll());
            html.addAttribute("proveedores", proveedorRepositorio.findAll());
        } else {
            html.addAttribute("lista", repositorio.findByInsumo_DescripcionIgnoreCaseContaining(buscar));
            html.addAttribute("insumo", insumosRepositorio.findByDescripcionIgnoreCaseContaining(buscar));
            html.addAttribute("proveedores", proveedorRepositorio.findByNombreIgnoreCaseContaining(buscar));
        }

        return "form_stockInsumos";
    }

    @PostMapping
    public String guardar(@ModelAttribute("stockInsumo") StockInsumos stock) {
        repositorio.save(stock); // Guarda en la base de datos
        return "redirect:/stockInsumos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        repositorio.deleteById(id);
        return "redirect:/stockInsumos";
    }
}
