package electronicapractica10.demo.service;

import electronicapractica10.demo.model.ClienteEquipo;


import java.util.List;

public interface ClienteEquipoService {

    ClienteEquipo crearClienteEquipo(ClienteEquipo clienteEquipo);
    void actualizarClienteEquipo(ClienteEquipo clienteEquipo);
    void borrarClienteEquipoPorId(ClienteEquipo clienteEquipo);
    void borrarTodosLosClientesEquipos();
    List<ClienteEquipo> buscarTodosClientesEquipos();
    ClienteEquipo buscarPorId(long id);
    boolean clienteEquipoExiste(ClienteEquipo clienteEquipo);
    List<Object[]> equiposAlquiladosNoDevueltos();
    List<ClienteEquipo> historialCliente(long id);
    List<Object[]> promedioAlquilerPorFamilia(long id);


}
