package DAO;

import Controlador.Conexion;
import Interfaces.CuentaBancariaDAO;
import Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

public class CuentaBancariaDAOImpl implements CuentaBancariaDAO {

    Conexion conexion;

    public CuentaBancariaDAOImpl() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregarCuenta(CuentaBancarias cuentaBancarias, HttpSession session) {
        int idUsuario = (int) session.getAttribute("idUsuario");

        try (Connection connection = conexion.getConexion()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CuentaBancarias (idUsuario, idBanco, idMoneda, aliasBanco, numeroCuenta) VALUES (?,?,?,?,?)");

            // Asigna los valores a los parámetros del PreparedStatement
            statement.setInt(1, idUsuario);
            statement.setInt(2, cuentaBancarias.getBanco().getIdBanco());
            statement.setInt(3, cuentaBancarias.getMoneda().getIdMoneda());
            statement.setString(4, cuentaBancarias.getAliasBanco());
            statement.setString(5, cuentaBancarias.getNumeroCuenta());

            int filasAfectadas = statement.executeUpdate();

            // Si se insertaron filas (filasAfectadas > 0), se considera una inserción exitosa.
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Error en la inserción
        }
    }

    @Override
    public List<CuentaBancarias> obtenerCuentaBancarias(int idUsuario) throws SQLException {

        List<CuentaBancarias> cuentaBancarias = new ArrayList<>();

        try (Connection connection = conexion.getConexion()) {
            PreparedStatement statement = connection.prepareStatement("SELECT c.idCuentaB, c.aliasBanco, c.numeroCuenta, b.nombreBanco, m.tipoMoneda, "
                    + "u.nombre, u.apellido "
                    + "FROM CuentaBancarias c "
                    + "INNER JOIN Usuario u ON c.idUsuario = u.idUsuario "
                    + "INNER JOIN Banco b ON c.idBanco = b.idBanco "    
                    + "INNER JOIN Moneda m ON c.idMoneda = m.idMoneda "
                    + "WHERE c.idUsuario = ?");
            statement.setInt(1, idUsuario); 
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int idCuentaB = resultSet.getInt("idCuentaB");
                String aliasBanco = resultSet.getString("aliasBanco");
                String numeroCuenta = resultSet.getString("numeroCuenta");
                String nombreBanco = resultSet.getString("nombreBanco");
                String tipoMoneda = resultSet.getString("tipoMoneda");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                
                CuentaBancarias cuenta = new CuentaBancarias();
                cuenta.setIdCuentaB(idCuentaB);
                cuenta.setAliasBanco(aliasBanco);
                cuenta.setNumeroCuenta(numeroCuenta);

                Usuario usuario = new Usuario();
                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                cuenta.setUsuario(usuario);

                Banco banco = new Banco();
                banco.setNombreBanco(nombreBanco);
                cuenta.setBanco(banco);

                Moneda moneda = new Moneda();
                moneda.setTipoMoneda(tipoMoneda);
                cuenta.setMoneda(moneda);

                cuentaBancarias.add(cuenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return cuentaBancarias;

    }
}
