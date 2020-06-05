package electronicapractica10.demo.service;


import electronicapractica10.demo.model.Equipo;

import java.util.List;

public interface EquipoService {

    void crearEquipo(Equipo equipo);

    void actualizarEquipo(Equipo equipo);

    void borrarEquipoPorId(Equipo equipo);

    void borrarTodosLosEquipos();

    List<Equipo> buscarTodosEquipos();

    Equipo buscarPorId(long id);

    Equipo findByNombreEquipo(String nombre);

    boolean equipoExiste(Equipo equipo);
}
