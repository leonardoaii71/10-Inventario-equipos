package electronicapractica10.demo.service;

import electronicapractica10.demo.model.Usuario;


import java.util.List;

public interface UsuarioService {

    void crearUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    void borrarUsuarioPorId(long id);
    void borrarTodosLosUsuarios();
    List<Usuario> buscarTodosUsuarios();
    Usuario buscarPorId(long id);
    Usuario findByUsername(String username);
    boolean usuarioExiste(Usuario usuario);

}
