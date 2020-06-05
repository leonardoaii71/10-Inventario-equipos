package electronicapractica10.demo.service;



import electronicapractica10.demo.model.SubFamilia;

import java.util.List;

public interface SubFamiliaService {

    void crearSubFamilia(SubFamilia subFamilia);
    void actualizarSubFamilia(SubFamilia subFamilia);
    void borrarSubFamiliaPorId(SubFamilia subFamilia);
    void borrarTodasLasSubFamilias();
    List<SubFamilia> buscarTodasSubFamilias();
    SubFamilia buscarPorId(long id);
    SubFamilia findByNombreSubFamilia(String nombre);
    boolean subFamiliaExiste(SubFamilia subFamilia);
}
