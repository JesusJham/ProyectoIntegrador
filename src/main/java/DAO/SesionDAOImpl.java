package DAO;

import Controlador.Conexion;
import Interfaces.SesionDAO;
import Interfaces.UsuarioDAO;
import Modelo.Sesion;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SesionDAOImpl implements SesionDAO {

    Conexion conexion;

    public SesionDAOImpl() {
        conexion = new Conexion();
    }

    @Override
    public boolean crearSesion(Sesion sesion) {
        try (Connection connection = conexion.getConexion()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Sesion (idUsuario, token, fechaExpiracion) VALUES (?, ?, ?)");
            statement.setInt(1, sesion.getUsuario().getIdUsuario());
            statement.setString(2, sesion.getToken());
            statement.setTimestamp(3, new java.sql.Timestamp(sesion.getFechaExpiracion().getTime()));

            int filasAfectadas = statement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Sesion obtenerSesionPorToken(String token) {
        try (Connection connection = conexion.getConexion()) {
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Sesion WHERE token = ?");
            statement.setString(1, token);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idSesion = resultSet.getInt("idSesion");
                int idUsuario = resultSet.getInt("idUsuario");
                Date fechaExpiracion = resultSet.getTimestamp("fechaExpiracion");

                UsuarioDAO usuarioDAO = new UsuarioDAOImpl(); // Reemplaza por tu implementación real
                Usuario usuario = usuarioDAO.obtenerUsuarioPorId(idUsuario);

                return new Sesion(idSesion, usuario, token, fechaExpiracion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean actualizarSesion(Sesion sesion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarSesion(int idSesion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
