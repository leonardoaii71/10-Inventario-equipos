package electronicapractica10.demo.repository;

import electronicapractica10.demo.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolRepository extends JpaRepository<Rol, Long> {

    Rol findByNombreRol(String rol);

}
