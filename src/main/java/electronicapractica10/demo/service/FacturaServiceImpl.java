package electronicapractica10.demo.service;

import electronicapractica10.demo.model.Factura;
import electronicapractica10.demo.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service("facturaService")
@Transactional

public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public Factura crearFactura(Factura factura){
        return facturaRepository.save(factura);
    }
    public void actualizarFactura(Factura factura){
        crearFactura(factura);
    }
    public void borrarFacturaPorId(long id){
        facturaRepository.deleteById(id);
    }
    public void borrarTodasLasFacturas(){
        facturaRepository.deleteAll();
    }
    public List<Factura> buscarTodasFacturas(){
        return facturaRepository.findAll();
    }
    @Override
    public Factura buscarPorId(long id){
        return facturaRepository.findById(id).get();
    }
    public boolean facturaExiste(Factura factura){
        return buscarPorId(factura.getId()) != null;
    }
}
