package electronicapractica10.demo.service;

public interface SecurityService {

    String usuarioLogueado();

    void autoLogin(String username, String password);
}
