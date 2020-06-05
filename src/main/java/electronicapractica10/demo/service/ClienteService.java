package electronicapractica10.demo.service;

import electronicapractica10.demo.model.Cliente;

import java.util.List;

public interface ClienteService {

     void crearCliente(Cliente cliente);
     void actualizarCliente(Cliente cliente);
     void borrarClientePorId(Cliente cliente);
     void borrarTodosLosClientes();
     List<Cliente> buscarTodosClientes();
     Cliente buscarPorId(long id);
     Cliente findByNombre(String nombre);
     boolean clienteExiste(Cliente cliente);

}
