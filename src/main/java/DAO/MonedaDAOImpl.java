package DAO;

import Controlador.Conexion;
import Interfaces.MonedaDAO;
import Modelo.Banco;
import Modelo.Moneda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonedaDAOImpl implements MonedaDAO {

    Conexion conexion;

    public MonedaDAOImpl() {
        conexion = new Conexion();
    }

    @Override
    public List<Moneda> obtenerMonedas() throws SQLException {
        try (Connection connection = conexion.getConexion()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Moneda");
            ResultSet resultSet = statement.executeQuery();

            List<Moneda> monedas = new ArrayList<>();
            while (resultSet.next()) {
                int idMoneda = resultSet.getInt("idMoneda");
                String tipoMoneda = resultSet.getString("tipoMoneda");
                Moneda moneda = new Moneda(idMoneda, tipoMoneda);
                monedas.add(moneda);
            }
            return monedas;
        }
    }
}
