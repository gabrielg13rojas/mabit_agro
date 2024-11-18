package py.com.mabit.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import py.com.mabit.entidades.Empresas;
import py.com.mabit.repositorios.EmpresasRepositorio;
@Controller
@RequestMapping("/empresas")
public class EmpresaControlador {

    @Autowired
    EmpresasRepositorio repositorio;

    @GetMapping({ "", "/editar/{id}" })
    public String formulario(Model html, @PathVariable(required = false) Long id,
            @RequestParam(defaultValue = "") String buscar, @ModelAttribute("empresa") Empresas empre) {
        html.addAttribute("entidad", "Empresas");
        if (id != null) {
        	html.addAttribute("empresa", repositorio.findById(id).get());
            html.addAttribute("formColapsado", true);
        } else {
            html.addAttribute("formColapsado", false);
        }

        if (buscar.isEmpty()) {
            html.addAttribute("lista", repositorio.findAll());
        } else {
            html.addAttribute("lista", repositorio.findByNombreIgnoreCaseContaining(buscar));
        }

        return "form_empresas";
    }

    @PostMapping
   
    public String guardar(@ModelAttribute("empresa") Empresas e) {
        repositorio.save(e); // Guarda en la base de datos
        return "redirect:/empresas";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        repositorio.deleteById(id);
        return "redirect:/empresas";
    }
}
