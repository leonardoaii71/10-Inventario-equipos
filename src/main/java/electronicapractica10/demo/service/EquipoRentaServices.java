package electronicapractica10.demo.service;

import electronicapractica10.demo.model.Equipo;
import electronicapractica10.demo.model.EquipoRenta;
import electronicapractica10.demo.repository.EquipoRentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EquipoRentaServices {

    @Autowired
    EquipoRentaRepository equipoRentaRepository;

    @Transactional
    public void crearEquipoAlquiler(EquipoRenta equipoRenta) {

        equipoRentaRepository.save(equipoRenta);

    }

    public List<Equipo> obtenerEquiposAlquiler(Set<EquipoRenta> equipoRentaSet) {

        List<Equipo> equipoList = new ArrayList<>();

        for (EquipoRenta equipoRenta : equipoRentaSet) {

            equipoList.add(equipoRenta.getEquipo());

        }

        return equipoList;
    }

    public EquipoRenta buscarEquipoAlquilerById(long id){

        return equipoRentaRepository.findById(id);
    }


    public void devolverEquipo(long id){

        EquipoRenta equipoRenta = equipoRentaRepository.findById(id);
        equipoRenta.setDevuelto(!equipoRenta.getDevuelto());
        equipoRentaRepository.save(equipoRenta);

    }
}
