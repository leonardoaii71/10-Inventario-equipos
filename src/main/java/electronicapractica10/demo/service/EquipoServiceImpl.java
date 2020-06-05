package electronicapractica10.demo.service;


import electronicapractica10.demo.model.Equipo;
import electronicapractica10.demo.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service("equipoService")
@Transactional
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public void crearEquipo(Equipo equipo){
        equipoRepository.save(equipo);
    }
    public void actualizarEquipo(Equipo equipo){
        crearEquipo(equipo);
    }
    public void borrarEquipoPorId(Equipo equipo){
        equipo.setDeleted(true);
        actualizarEquipo(equipo);
    }
    public void borrarTodosLosEquipos(){
        equipoRepository.deleteAll();
    }
    public List<Equipo> buscarTodosEquipos(){
        return equipoRepository.findAll();
    }
    @Override
    public Equipo buscarPorId(long id){
        return equipoRepository.findById(id).get();
    }
    public Equipo findByNombreEquipo(String nombre){
        return equipoRepository.findByNombreEquipo(nombre);
    }
    public boolean equipoExiste(Equipo equipo){
        return findByNombreEquipo(equipo.getNombreEquipo()) != null;
    }
}
