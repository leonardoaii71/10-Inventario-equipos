package electronicapractica10.demo.controller;

import electronicapractica10.demo.model.Categoria;
import electronicapractica10.demo.service.CategoriaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private CategoriaServiceImpl categoriasService;

    @GetMapping(value="/")
    public String categorias(Model model)
    {
        List<Categoria> categorias = new ArrayList<>();
        categorias = categoriasService.buscarTodasCategorias();
        model.addAttribute("categorias", categorias);

        return "categoriasview";
    }

    @PostMapping("/crear/")
    public String crearCategoria(@RequestParam("nombre") String categoria){
    Categoria f = new Categoria();
    f.setNombreCategoria(categoria);
    categoriasService.crearCategoria(f);

        return "redirect:/categorias/";
    }

    @GetMapping(value = "/eliminar/{id}")
    public String borrarCategoria(@PathVariable Long id) {
        Categoria categoria = categoriasService.buscarPorId(id);
       categoriasService.borrarCategoriaPorId(categoria);

       return "redirect:/categorias/";
    }



}
