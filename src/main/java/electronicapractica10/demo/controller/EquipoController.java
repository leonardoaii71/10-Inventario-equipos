package electronicapractica10.demo.controller;

import electronicapractica10.demo.model.Categoria;
import electronicapractica10.demo.model.Equipo;
import electronicapractica10.demo.model.SubFamilia;
import electronicapractica10.demo.service.CategoriaServiceImpl;
import electronicapractica10.demo.service.ClienteEquipoServiceImpl;
import electronicapractica10.demo.service.EquipoServiceImpl;
import electronicapractica10.demo.service.SubFamiliaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/equipos")
public class EquipoController {

    private static String UPLOADED_FOLDER = "/img/";


    @Autowired
    private EquipoServiceImpl equipoService;
    @Autowired
    private CategoriaServiceImpl categoriaService;
    @Autowired
    private SubFamiliaServiceImpl subFamiliaService;
    @Autowired
    private ClienteEquipoServiceImpl clienteEquipoService;

    @GetMapping("/")
    public String equipos(Model model)
    {
        List<Equipo> equipos = new ArrayList<>();
        equipos = equipoService.buscarTodosEquipos();
        List<Categoria> categories = categoriaService.buscarTodasCategorias();
        List<SubFamilia> subFamilias = subFamiliaService.buscarTodasSubFamilias();
        model.addAttribute("subfamilias", subFamilias);
        model.addAttribute("categorias", categories);
        model.addAttribute("equipos", equipos);
        return "equiposview";
    }

    @PostMapping("/crear/")
    public String crearEquipo(@RequestParam("foto") MultipartFile foto,
                              @RequestParam("nombre") String nombre,
                              @RequestParam("precio") String precio,
                              @RequestParam("existencia") String existencia,
                              @RequestParam("familia") String familia,
                              @RequestParam("subfamilia") String subfamilia,
                              RedirectAttributes redirectAttributes) {



        Equipo equipo = new Equipo();

        try {

            // Get the file and save it somewhere
            byte[] bytes = foto.getBytes();
            equipo.setImagen(bytes);
            Path path = Paths.get(UPLOADED_FOLDER + foto.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + foto.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        equipo.setNombreEquipo(nombre);
        equipo.setPrecio(Float.parseFloat(precio));
        equipo.setExistencia(Integer.parseInt(existencia));
        System.out.println(familia);
        Categoria categoria1 = categoriaService.findByNombreCategoria(familia);
        equipo.setCategoria(categoria1);
        equipo.setSubFamilia(subFamiliaService.findByNombreSubFamilia(subfamilia));
        equipoService.crearEquipo(equipo);
        return "redirect:/equipos/";
    }

    @RequestMapping(value = "/ver/{id}", method = RequestMethod.GET)
    public String ver(Model model, @PathVariable String id)
    {
        Equipo equipo = equipoService.buscarPorId(Long.parseLong(id));

        model.addAttribute("equipo", equipo);
        return "verequipo";
    }

    @GetMapping("/{id}/")
    @ResponseBody
    public Equipo postResponseController(@PathVariable Long id ) {
        Equipo equipo;
        try {
            equipo = equipoService.buscarPorId(id);
        }
        catch (Exception e){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }

        return equipo;
    }

    @PostMapping("/modificar/")
    public String modificarEquipo(@RequestParam("nombre2") String nombre,
                                  @RequestParam("id2") String id,
                                  @RequestParam("precio2") String precio,
                                  @RequestParam("existencia2") String existencia,
                                  @RequestParam("categoria2") String categoria,
                                  @RequestParam("foto2") MultipartFile foto,
                                  RedirectAttributes redirectAttributes){

        Equipo equipo = equipoService.buscarPorId(Long.parseLong(id));
        equipo.setNombreEquipo(nombre);
        equipo.setPrecio(Float.parseFloat(precio));
        equipo.setExistencia(Integer.parseInt(existencia));
        equipo.setCategoria(categoriaService.findByNombreCategoria(categoria));
        try {

            // Get the file and save it somewhere
            byte[] bytes = foto.getBytes();
            equipo.setImagen(bytes);
            Path path = Paths.get(UPLOADED_FOLDER + foto.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + foto.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
        equipoService.actualizarEquipo(equipo);
        return "redirect:/equipos/";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String borrarEquipo(@PathVariable String id) {
        Equipo equipo = equipoService.buscarPorId(Long.parseLong(id));
        equipoService.borrarEquipoPorId(equipo);
        return "redirect:/equipos/";
    }
}
