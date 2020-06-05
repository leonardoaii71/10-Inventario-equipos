package electronicapractica10.demo.repository;

import electronicapractica10.demo.model.SubFamilia;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubFamiliaRepository extends JpaRepository<SubFamilia, Long> {

    SubFamilia findByNombreSubFamilia(String nombre);

}
