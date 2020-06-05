package electronicapractica10.demo.controller;


import java.util.List;
import electronicapractica10.demo.model.Rol;
import electronicapractica10.demo.service.RolServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * RolController
 */
@Controller
public class RolController {

    @Autowired
    RolServices rolServices;

    @RequestMapping(value = "/rol/crear", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<List<Rol>> crearRol(@RequestBody List<Rol> rolList) {

        for (Rol rol : rolList) {

            rolServices.crearRol(rol);
        }

        return new ResponseEntity<>(rolList, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/roles", produces = {"application/json"})
    public List<Rol> roles() {
        return rolServices.listaRoles();
    }
}