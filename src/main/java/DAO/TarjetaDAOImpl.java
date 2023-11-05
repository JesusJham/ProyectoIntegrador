package DAO;

import Controlador.Conexion;
import Interfaces.TarjetaDAO;
import Modelo.Banco;
import Modelo.Moneda;
import Modelo.Tarjeta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

public class TarjetaDAOImpl implements TarjetaDAO {

    Conexion conexion;

    public TarjetaDAOImpl() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregarTarjeta(Tarjeta tarjeta, HttpSession session) {
        int idUsuario = (int) session.getAttribute("idUsuario");
        try (Connection connection = conexion.getConexion()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO TARJETA (idUsuario, idBanco, idMoneda, numeroTarjeta, cvv, fechaVencimiento, nombreTitular) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, idUsuario);
            statement.setInt(2, tarjeta.getBanco().getIdBanco());
            statement.setInt(3, tarjeta.getMoneda().getIdMoneda());
            statement.setString(4, tarjeta.getNumeroTarjeta());
            statement.setString(5, tarjeta.getCvv());
            statement.setString(6, tarjeta.getFechaVencimiento());
            statement.setString(7, tarjeta.getNombreTitular());

            int filasAfectadas = statement.executeUpdate();

            // Si se insertaron filas (filasAfectadas > 0), se considera una inserción exitosa.
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Error en la inserción
        }
    }

    @Override
    public List<Tarjeta> obtenerTarjetas(int idUsuario) throws SQLException {

        List<Tarjeta> tarjetas = new ArrayList<>();

        try (Connection connection = conexion.getConexion()) {
            PreparedStatement statement = connection.prepareStatement("SELECT t.idTarjeta, t.numeroTarjeta, t.fechaVencimiento, t.nombreTitular, m.tipoMoneda, b.nombreBanco "
                    + "FROM TARJETA t "
                    + "INNER JOIN Moneda m ON t.idMoneda = m.idMoneda "
                    + "INNER JOIN Banco b ON t.idBanco = b.idBanco "
                    + "WHERE t.idUsuario = ?");

            statement.setInt(1, idUsuario); // Establecer el parámetro

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idTarjeta = resultSet.getInt("idTarjeta");
                String numeroTarjeta = resultSet.getString("numeroTarjeta");
                String ultimos4Digitos = numeroTarjeta.substring(numeroTarjeta.length() - 4); // Obtener los últimos 4 dígitos
                String enmascarado = "************" + ultimos4Digitos; // Enmascarar los dígitos anteriores
                String fechaVencimiento = resultSet.getString("fechaVencimiento");
                String nombreTitular = resultSet.getString("nombreTitular");
                String tipoMoneda = resultSet.getString("tipoMoneda");
                String nombreBanco = resultSet.getString("nombreBanco");

                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setIdTarjeta(idTarjeta);
                tarjeta.setNumeroTarjeta(enmascarado);
                tarjeta.setFechaVencimiento(fechaVencimiento);
                tarjeta.setNombreTitular(nombreTitular);

                Moneda moneda = new Moneda();
                moneda.setTipoMoneda(tipoMoneda);
                tarjeta.setMoneda(moneda);

                Banco banco = new Banco();
                banco.setNombreBanco(nombreBanco);
                tarjeta.setBanco(banco);

                tarjetas.add(tarjeta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return tarjetas;

    }

}
