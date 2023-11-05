package Interfaces;

import Modelo.Usuario;

public interface UsuarioDAO {

    boolean insertarUsuario(Usuario usuario);
    boolean autenticarUsuario(String email, String password);
    Usuario obtenerUsuarioPorId(int idUsuario);
    Usuario obtenerUsuarioPorEmail(String email);

}
