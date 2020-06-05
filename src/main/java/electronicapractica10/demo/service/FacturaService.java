package electronicapractica10.demo.service;


import electronicapractica10.demo.model.Factura;

import java.util.List;

public interface FacturaService {

    Factura crearFactura(Factura factura);
    void actualizarFactura(Factura factura);
    void borrarFacturaPorId(long id);
    void borrarTodasLasFacturas();
    List<Factura> buscarTodasFacturas();
    Factura buscarPorId(long id);
    boolean facturaExiste(Factura factura);

}
