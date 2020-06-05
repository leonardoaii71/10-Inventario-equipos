package electronicapractica10.demo.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import electronicapractica10.demo.model.Cliente;
import electronicapractica10.demo.model.ClienteEquipo;
import electronicapractica10.demo.service.ClienteEquipoServiceImpl;
import electronicapractica10.demo.service.ClienteServiceImpl;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private static String UPLOADED_FOLDER = "/img/";

    @Autowired
    private ClienteServiceImpl clienteService;
    @Autowired
    private ClienteEquipoServiceImpl clienteEquipoService;

    @RequestMapping(value = "/")
    public String clientes(Model model) {
        List<Cliente> clientes = new ArrayList<>();
        clientes = clienteService.buscarTodosClientes();
        model.addAttribute("clientes", clientes);
        return "clientesview";
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public String historial(Model model, @PathVariable String id) {
        Cliente cliente = clienteService.buscarPorId(Long.parseLong(id));
        List<ClienteEquipo> historial = clienteEquipoService.historialCliente(Long.parseLong(id));

        model.addAttribute("cliente", cliente);
        model.addAttribute("historial", historial);
        return "userprofile";
    }

    @GetMapping("/{id}/")
    @ResponseBody
    public Cliente postResponseController(@PathVariable Long id ) {
        Cliente cliente;
        try {
            cliente = clienteService.buscarPorId(id);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }

        return cliente;
    }

    @PostMapping(value = "/add/")
    public String crearCliente(@RequestParam("foto") MultipartFile foto,
                               @RequestParam("nombre") String nombre,
                               @RequestParam("apellido") String apellido,
                               @RequestParam("cedula") String cedula,
                               @RequestParam("fechaNacimiento") String fechaNacimiento,
                               RedirectAttributes redirectAttributes) {

        Cliente cliente = new Cliente();

        try {

            // Get the file and save it somewhere
            byte[] bytes = foto.getBytes();
            cliente.setImagen(bytes);
            Path path = Paths.get(UPLOADED_FOLDER + foto.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + foto.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format = new SimpleDateFormat("yy-mm-dd");
        LocalDate date = LocalDate.parse(fechaNacimiento);
        System.out.println(nombre + " " + cedula + " " + date);

        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setCedula(cedula);
        cliente.setFechaNacimiento(date);
        clienteService.crearCliente(cliente);
        return "redirect:/clientes/";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String borrarCliente(@PathVariable String id) {
        Cliente cliente = clienteService.buscarPorId(Long.parseLong(id));
        clienteService.borrarClientePorId(cliente);
        return "redirect:/clientes/";

    }

    @PostMapping("/modificar/")
    public String modificarCliente(@RequestParam("nombre") String nombre,
                                   @RequestParam("id") String id,
                                   @RequestParam("apellido") String apellido,
                                   @RequestParam("cedula") String cedula,
                                   @RequestParam("fechaNacimiento") String fechaNacimiento,
                                   @RequestParam("foto") MultipartFile foto,
                                   RedirectAttributes redirectAttributes) {

        Cliente cliente = clienteService.buscarPorId(Long.parseLong(id));
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        SimpleDateFormat format = new SimpleDateFormat("yy-mm-dd");
        LocalDate date = LocalDate.parse(fechaNacimiento);
        cliente.setFechaNacimiento(date);
        try {

            // Get the file and save it somewhere
            byte[] bytes = foto.getBytes();
            cliente.setImagen(bytes);
            Path path = Paths.get(UPLOADED_FOLDER + foto.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + foto.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
        clienteService.actualizarCliente(cliente);
        return "redirect:/clientes/";
    }
    
}
