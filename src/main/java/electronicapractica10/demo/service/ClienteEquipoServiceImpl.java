package electronicapractica10.demo.service;

import electronicapractica10.demo.model.ClienteEquipo;
import electronicapractica10.demo.repository.ClienteEquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service("clienteEquipoService")
@Transactional
public class ClienteEquipoServiceImpl implements ClienteEquipoService {

    @Autowired
    private ClienteEquipoRepository clienteEquipoRepository;

    public ClienteEquipo crearClienteEquipo(ClienteEquipo clienteEquipo){
        return clienteEquipoRepository.save(clienteEquipo);
    }
    public void actualizarClienteEquipo(ClienteEquipo clienteEquipo){
        crearClienteEquipo(clienteEquipo);
    }
    public void borrarClienteEquipoPorId(ClienteEquipo clienteEquipo){
        clienteEquipo.setDeleted(true);
        actualizarClienteEquipo(clienteEquipo);
    }
    public void borrarTodosLosClientesEquipos(){
        clienteEquipoRepository.deleteAll();
    }
    public List<ClienteEquipo> buscarTodosClientesEquipos(){
        return clienteEquipoRepository.findAll();
    }
    @Override
    public ClienteEquipo buscarPorId(long id){
        return clienteEquipoRepository.findById(id).get();
    }

    public boolean clienteEquipoExiste(ClienteEquipo clienteEquipo){
        return buscarPorId(clienteEquipo.getId()) != null;
    }

    public List<Object[]> equiposAlquiladosNoDevueltos(){
        return clienteEquipoRepository.equiposAlquiladosNoDevueltos();
    }

    public List<ClienteEquipo> historialCliente(long id){
        return clienteEquipoRepository.historialCliente(id);
    }
    public List<Object[]> promedioAlquilerPorFamilia(long id){
        return clienteEquipoRepository.promediosAlquiler(id);
    }


}