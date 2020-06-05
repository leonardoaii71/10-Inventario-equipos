package electronicapractica10.demo.service;

import electronicapractica10.demo.model.FacturaEquipo;

import java.util.List;

public interface FacturaEquipoService {

    void crearFacturaEquipo(FacturaEquipo facturaEquipo);
    void actualizarFacturaEquipo(FacturaEquipo facturaEquipo);
    void borrarFacturaEquipoPorId(long id);
    void borrarTodasLasFacturaEquipos();
    List<FacturaEquipo> buscarTodasFacturaEquipos();
    FacturaEquipo buscarPorId(long id);
    boolean facturaEquipoExiste(FacturaEquipo facturaEquipo);
}
