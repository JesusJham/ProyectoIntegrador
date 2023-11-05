package DAO;

import Controlador.Conexion;
import Interfaces.BancoDAO;
import Modelo.Banco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BancoDAOImpl implements BancoDAO {

    Conexion conexion;

    public BancoDAOImpl() {
        conexion = new Conexion();
    }

    @Override
    public List<Banco> obtenerBancos() throws SQLException {
        try (Connection connection = conexion.getConexion()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Banco");
            ResultSet resultSet = statement.executeQuery();
            
            List<Banco> bancos = new ArrayList<>();
            while (resultSet.next()) {
                int idBanco = resultSet.getInt("idBanco");
                String nombreBanco = resultSet.getString("nombreBanco");
                Banco banco = new Banco(idBanco,nombreBanco);
                bancos.add(banco);
            }
            return bancos;
        }

    }
}
