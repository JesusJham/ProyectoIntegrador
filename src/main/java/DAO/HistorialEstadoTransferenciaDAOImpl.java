package DAO;

import Controlador.Conexion;
import Interfaces.HistorialEstadoTransferenciaDAO;
import Modelo.HistorialEstadoTransferencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HistorialEstadoTransferenciaDAOImpl implements HistorialEstadoTransferenciaDAO {

    Conexion conexion;

    public HistorialEstadoTransferenciaDAOImpl() {
        conexion = new Conexion();
    }

    @Override
    public boolean registrarHistorialTransferencia(HistorialEstadoTransferencia historialEstadoTransferencia) {
        try (Connection connection = conexion.getConexion()) {
            String sql = "INSERT INTO HistorialEstadoTransferencia (idTransferencia, estadoTransferencia, fechaHoraCambio) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, historialEstadoTransferencia.getTransferencia().getIdTransferencia());
            statement.setString(2, historialEstadoTransferencia.getEstadoTransferencia());
            statement.setTimestamp(3, new java.sql.Timestamp(historialEstadoTransferencia.getFechaHoraCambio().getTime()));

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
