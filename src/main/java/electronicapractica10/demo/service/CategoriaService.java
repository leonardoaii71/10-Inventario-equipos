package electronicapractica10.demo.service;



import electronicapractica10.demo.model.Categoria;

import java.util.List;

public interface CategoriaService {

    void crearCategoria(Categoria categoria);
    void actualizarCategoria(Categoria categoria);
    void borrarCategoriaPorId(Categoria categoria);
    void borrarTodasLasCategorias();
    List<Categoria> buscarTodasCategorias();
    Categoria buscarPorId(long id);
    Categoria findByNombreCategoria(String nombre);
    boolean categoriaExiste(Categoria categoria);

}
