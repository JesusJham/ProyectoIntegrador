package DAO;

import Controlador.Conexion;
import Interfaces.UsuarioDAO;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UsuarioDAOImpl implements UsuarioDAO {

    Conexion conexion;

    public UsuarioDAOImpl() {
        conexion = new Conexion();
    }

    public boolean insertarUsuario(Usuario usuario) {
        try (Connection connection = conexion.getConexion()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Usuario (nombre, apellido, fechaNacimiento, fechaRegistro, celular, email, pass) VALUES (?,?,?,?,?,?,?)");
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setDate(3, new java.sql.Date(usuario.getFechaNacimiento().getTime()));
            statement.setDate(4, new java.sql.Date(usuario.getFechaRegistro().getTime()));
            statement.setString(5, usuario.getCelular());
            statement.setString(6, usuario.getEmail());
            statement.setString(7, usuario.getPassword());

            int filasAfectadas = statement.executeUpdate();

            // Si se insertaron filas (filasAfectadas > 0), se considera una inserción exitosa.
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Error en la inserción
        }
    }

    @Override
    public Usuario obtenerUsuarioPorId(int idUsuario) {
        try (Connection connection = conexion.getConexion()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Usuario WHERE idUsuario = ?");
            statement.setInt(1, idUsuario);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("idUsuario");
                return new Usuario(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public Usuario obtenerUsuarioPorEmail(String email) {
        int id = 0;
        try (Connection connection = conexion.getConexion()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Usuario WHERE email = ?");
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idUsuario = resultSet.getInt("idUsuario");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                Date fechaNacimiento = resultSet.getDate("fechaNacimiento");
                Date fechaRegistro = resultSet.getDate("fechaRegistro");
                String celular = resultSet.getString("celular");
                String emailUsuario = resultSet.getString("email");
                String password = resultSet.getString("pass");

                return new Usuario(idUsuario, nombre, apellido, fechaNacimiento, fechaRegistro, celular, emailUsuario, password);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean autenticarUsuario(String email, String password) {
        try (Connection connection = conexion.getConexion()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Usuario WHERE email = ? AND pass = ?");
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next(); // Si hay resultados, las credenciales son válidas
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error durante la autenticación
        }
    }

}
