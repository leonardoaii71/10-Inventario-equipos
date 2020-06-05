package electronicapractica10.demo.repository;

import electronicapractica10.demo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria findByNombreCategoria(String nombre);

}
