package DAO;

import Controlador.Conexion;
import Interfaces.HistorialEstadoTransferenciaDAO;
import Interfaces.TransferenciasDAO;
import Modelo.HistorialEstadoTransferencia;
import Modelo.Transferencias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;

public class TransferenciasDAOImpl implements TransferenciasDAO {

    Conexion conexion;
    HistorialEstadoTransferenciaDAO historialEstadoTransferenciaDAO;

    public TransferenciasDAOImpl() {
        conexion = new Conexion();
        historialEstadoTransferenciaDAO = new HistorialEstadoTransferenciaDAOImpl(); // Instancia del DAO para el historial

    }

    @Override
    public boolean registrarTransferencia(Transferencias transferencias, HttpSession session) {
        int idUsuario = (int) session.getAttribute("idUsuario");
        try (Connection connection = conexion.getConexion()) {
            String sql = "INSERT INTO Transferencia (idUsuario, idTarjeta, idCuentaB, codigoOper, tipoOperacion, montoEnviado, montoRecibido, tipoCambio, estado, fechaHora) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, idUsuario);
            statement.setInt(2, transferencias.getTarjeta().getIdTarjeta());
            statement.setInt(3, transferencias.getCuentaBancarias().getIdCuentaB());
            statement.setInt(4, transferencias.getCodigoOper());
            statement.setString(5, transferencias.getTipoOperacion());
            statement.setFloat(6, transferencias.getMontoEnviado());
            statement.setFloat(7, transferencias.getMontoRecibido());
            statement.setFloat(8, transferencias.getTipoCambio());
            statement.setString(9, transferencias.getEstado());
            statement.setTimestamp(10, new java.sql.Timestamp(transferencias.getFechaHora().getTime()));

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idTransferenciaGenerado = generatedKeys.getInt(1);
                        HistorialEstadoTransferencia historial = new HistorialEstadoTransferencia(0, transferencias, transferencias.getEstado(), transferencias.getFechaHora());
                        historial.getTransferencia().setIdTransferencia(idTransferenciaGenerado); // Asignar el ID generado a la transferencia en el historial

                        // Acceder al DAO correspondiente y registrar el historial de la transferencia
                        HistorialEstadoTransferenciaDAO historialDAO = new HistorialEstadoTransferenciaDAOImpl();
                        historialDAO.registrarHistorialTransferencia(historial);
                    }
                }
            }
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Transferencias> obtenerTransferencia(int idUsuario) {
        List<Transferencias> transferencias = new ArrayList<>();

        try (Connection connection = conexion.getConexion()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Transferencia WHERE idUsuario = ?");
            statement.setInt(1, idUsuario);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("idUsuario");
                int codigoOper = resultSet.getInt("codigoOper");
                float montoEnviado = resultSet.getFloat("montoEnviado");
                float montoRecibido = resultSet.getFloat("montoRecibido");
                float tipoCambio = resultSet.getFloat("tipoCambio");
                String tipoOperacion = resultSet.getString("tipoOperacion");
                Date fechaHora = resultSet.getDate("fechaHora");
                String estado = resultSet.getString("estado");

                Transferencias transferencia = new Transferencias();
                transferencia.setIdTransferencia(id);
                transferencia.setCodigoOper(codigoOper);
                transferencia.setMontoEnviado(montoEnviado);
                transferencia.setMontoRecibido(montoRecibido);
                transferencia.setTipoCambio(tipoCambio);
                transferencia.setTipoOperacion(tipoOperacion);
                transferencia.setFechaHora(fechaHora);
                transferencia.setEstado(estado);

                transferencias.add(transferencia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transferencias;
    }

    @Override
    public boolean actualizarEstadoPorUsuario(int idTransferencia, String nuevoEstado, Date nuevaFecha) {
        try (Connection connection = conexion.getConexion()) {
            String sql = "UPDATE Transferencia SET estado = ?, fechaHora = ? WHERE idTransferencia = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nuevoEstado);
            statement.setTimestamp(2, new java.sql.Timestamp(nuevaFecha.getTime()));
            statement.setInt(3, idTransferencia);

            int filasAfectadas = statement.executeUpdate();
           
            if (filasAfectadas > 0) {
                Transferencias transferencia = new Transferencias();
                transferencia.setIdTransferencia(idTransferencia);
                // Aquí se registra en el historial
                HistorialEstadoTransferencia historialEstado = new HistorialEstadoTransferencia(0, transferencia, nuevoEstado, nuevaFecha);
                boolean historialRegistrado = historialEstadoTransferenciaDAO.registrarHistorialTransferencia(historialEstado);

                return historialRegistrado;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
