package electronicapractica10.demo.service;


import electronicapractica10.demo.model.Rol;
import electronicapractica10.demo.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * RolServices
 */
@Service
public class ServiciosRol {

    @Autowired
    RolRepository rolRepository;

    @Transactional
    public void crearRol(Rol rol) {

        rolRepository.save(rol);
    }

    public List<Rol> listaRoles() {

        return rolRepository.findAll();
    }


}