package electronicapractica10.demo.service;

import java.util.List;

import electronicapractica10.demo.model.Rol;
import electronicapractica10.demo.repository.RolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * RolServices
 */
@Service
public class RolServices {

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
