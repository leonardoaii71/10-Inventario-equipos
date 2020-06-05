package electronicapractica10.demo.service;

import electronicapractica10.demo.model.Factura;
import electronicapractica10.demo.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FacturaServices {

    @Autowired
    FacturaRepository facturaRepository;

    @Transactional
    public void crearFactura(Factura factura){

        facturaRepository.save(factura);
    }

    public List<Factura> listaFacturas(){

        return facturaRepository.findAll();
    }

}
