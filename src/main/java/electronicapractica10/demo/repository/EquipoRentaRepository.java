package electronicapractica10.demo.repository;

import electronicapractica10.demo.model.Equipo;
import electronicapractica10.demo.model.EquipoRenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRentaRepository extends JpaRepository<EquipoRenta, Long> {

    EquipoRenta findById(long id);
    EquipoRenta findByEquipo(Equipo equipo);

}
