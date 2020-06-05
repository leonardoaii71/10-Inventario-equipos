package electronicapractica10.demo.repository;

import electronicapractica10.demo.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    Equipo findByNombreEquipo(String nombre);

}
