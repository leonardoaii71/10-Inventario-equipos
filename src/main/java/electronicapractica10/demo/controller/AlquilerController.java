package electronicapractica10.demo.controller;

import electronicapractica10.demo.model.*;
import electronicapractica10.demo.service.ClienteEquipoServiceImpl;
import electronicapractica10.demo.service.ClienteServiceImpl;
import electronicapractica10.demo.service.EquipoServiceImpl;
import electronicapractica10.demo.service.FacturaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/alquileres")
public class AlquilerController {

    @Autowired
    private ClienteEquipoServiceImpl clienteEquipoService;
    @Autowired
    private ClienteServiceImpl clienteService;
    @Autowired
    private EquipoServiceImpl equipoService;
    @Autowired
    private FacturaServiceImpl facturaService;

    @GetMapping(value = "/")
    public String alquileres(Model model)
    {
        List<Equipo> equipos = new ArrayList<>();
        List<ClienteEquipo> alquileres = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        equipos = equipoService.buscarTodosEquipos();
        alquileres = clienteEquipoService.buscarTodosClientesEquipos();
        clientes = clienteService.buscarTodosClientes();
        model.addAttribute("clientes", clientes);
        model.addAttribute("equipos", equipos);
        model.addAttribute("alquileres", alquileres);
        return "alquileresview";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/despachar/", produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String despacho(@RequestBody List<ClienteEquipo> alquileres){

        float precioTotal = 0;
        Factura factura = new Factura();
        factura.setFechaFacturacion(LocalDate.now());
        Cliente cl = new Cliente();
        Equipo eq;

       for(ClienteEquipo alquiler : alquileres) {
           alquiler.setFechaInicioAlquiler(LocalDate.now());
           long numdays = ChronoUnit.DAYS.between(alquiler.getFechaInicioAlquiler(), alquiler.getFechaFinAlquiler());
           alquiler.setCosto(alquiler.getCantidad() * alquiler.getEquipo().getPrecio() * numdays);
           equipoService.actualizarEquipo(alquiler.getEquipo());
           cl = clienteService.buscarPorId(alquiler.getCliente().getId());
           eq = equipoService.buscarPorId(alquiler.getEquipo().getId());
           alquiler.setEquipo(eq);
           alquiler.setCliente(cl);
           ClienteEquipo createdAlquiler = clienteEquipoService.crearClienteEquipo(alquiler);
           factura.getAlquileres().add(createdAlquiler);
           precioTotal += alquiler.getCosto();
        }
        factura.setCliente(cl);
        factura.setMontoTotal(precioTotal);
        Factura nuevafactura = facturaService.crearFactura(factura);

        return "/alquileres/factura/"+nuevafactura.getId();
    }

    @GetMapping(value = "/entrega/{id}")
    public String entrega(@PathVariable("id") String id){
        ClienteEquipo a = clienteEquipoService.buscarPorId(Long.parseLong(id));
        a.setEstado("Entregado");
        a.getEquipo().setExistencia(a.getCantidad());
        clienteEquipoService.actualizarClienteEquipo(a);
        return "redirect:/alquileres/";
    }

    @GetMapping(value = "/factura/{id}")
    public String entregado(Model model, @PathVariable("id") String id){
        Factura factura = facturaService.buscarPorId(Long.parseLong(id));
        model.addAttribute("factura", factura);
        return "invoice";
    }

    @RequestMapping(value = "/nodevueltos/", method = RequestMethod.GET)
    public String nodevueltosList(Model model) {
        List<Object[]> nodevueltos = clienteEquipoService.equiposAlquiladosNoDevueltos();
        model.addAttribute("alquileres", nodevueltos);
        return "retornosView";
    }

   @GetMapping(value = "/famaverage/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
   @ResponseBody
   public  List<Object[]> familiaAverage(@PathVariable("id") Long id){
        List<Object[]> avg = clienteEquipoService.promedioAlquilerPorFamilia(id);
        return avg;
    }
}
