package electronicapractica10.demo.service;

import electronicapractica10.demo.model.Categoria;
import electronicapractica10.demo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service("categoriaService")
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public void crearCategoria(Categoria categoria){
        categoriaRepository.save(categoria);
    }
    public void actualizarCategoria(Categoria categoria){
        crearCategoria(categoria);
    }
    public void borrarCategoriaPorId(Categoria categoria){
        categoria.setDeleted(true);
        actualizarCategoria(categoria);    }
    public void borrarTodasLasCategorias(){
        categoriaRepository.deleteAll();
    }

    public List<Categoria> buscarTodasCategorias(){
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria buscarPorId(long id){
        return categoriaRepository.findById(id).get();
    }
    public Categoria findByNombreCategoria(String nombre){
        return categoriaRepository.findByNombreCategoria(nombre);
    }
    public boolean categoriaExiste(Categoria categoria){
        return findByNombreCategoria(categoria.getNombreCategoria()) != null;
    }
}
