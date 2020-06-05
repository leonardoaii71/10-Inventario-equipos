package electronicapractica10.demo.service;

import electronicapractica10.demo.model.FacturaEquipo;
import electronicapractica10.demo.repository.FacturaEquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service("facturaEquipoService")
@Transactional
public class FacturaEquipoServiceImpl implements FacturaEquipoService {

    @Autowired
    private FacturaEquipoRepository facturaEquipoRepository;

    public void crearFacturaEquipo(FacturaEquipo facturaEquipo){
        facturaEquipoRepository.save(facturaEquipo);
    }
    public void actualizarFacturaEquipo(FacturaEquipo facturaEquipo){
        crearFacturaEquipo(facturaEquipo);
    }
    public void borrarFacturaEquipoPorId(long id){
        facturaEquipoRepository.deleteById(id);
    }
    public void borrarTodasLasFacturaEquipos(){
        facturaEquipoRepository.deleteAll();
    }
    public List<FacturaEquipo> buscarTodasFacturaEquipos(){
        return facturaEquipoRepository.findAll();
    }
    @Override
    public FacturaEquipo buscarPorId(long id){
        return facturaEquipoRepository.findById(id).get();
    }

    public boolean facturaEquipoExiste(FacturaEquipo facturaEquipo){
        return buscarPorId(facturaEquipo.getId()) != null;
    }
}
