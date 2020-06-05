package electronicapractica10.demo.service;

import electronicapractica10.demo.model.Renta;
import electronicapractica10.demo.model.Cliente;
import electronicapractica10.demo.repository.RentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RentaServices {

    @Autowired
    RentaRepository rentaRepository;

    @Transactional
    public void crearAlquiler(Renta renta){

        rentaRepository.save(renta);
    }

    public List<Renta> listaAlquileres(){

        return rentaRepository.findAllByPendienteOrderByFechaEntregaDesc(true);
    }

    public List<Renta> alquilerCliente(Cliente cliente){
        return rentaRepository.findAllByCliente(cliente);
    }

    public Renta buscarAlquiler(long id){

        return rentaRepository.findById(id);
    }

    public List<Map<Long, Long>> reporteAlquiler(){
        return rentaRepository.reporteCliente();
    }

    public  List<Map<String, Map.Entry<String, Boolean>>> reporteEquipos(){
        return rentaRepository.reporteEquipo();
    }


}
