package DAO;

import Controlador.Conexion;
import Interfaces.TransferenciasDAO;
import Modelo.Transferencias;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

public class TransferenciasDAOImpl implements TransferenciasDAO {

    Conexion conexion;

    public TransferenciasDAOImpl() {
        conexion = new Conexion();
    }

    @Override
    public boolean registrarTransferencia(Transferencias transferencias, HttpSession session) {
        int idUsuario = (int) session.getAttribute("idUsuario");
        float tipoCambio = 3.6f;
        String estado = "Atendido";
        try (Connection connection = conexion.getConexion()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Transferencia (idUsuario, idTarjeta, idCuentaB, codigoOper, tipoOperacion, montoEnviado, montoRecibido, tipoCambio, estado, fechaHora) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, idUsuario); // Establecer el parámetro
            statement.setInt(2, transferencias.getTarjeta().getIdTarjeta());
            statement.setInt(3, transferencias.getCuentaBancarias().getIdCuentaB());
            statement.setInt(4, transferencias.getCodigoOper());
            statement.setString(5, transferencias.getTipoOperacion());
            statement.setFloat(6, transferencias.getMontoEnviado());
            statement.setFloat(7, transferencias.getMontoRecibido());
            statement.setFloat(8, tipoCambio);
            statement.setString(9, estado);
            statement.setTimestamp(10, new java.sql.Timestamp(transferencias.getFechaHora().getTime()));

            int filasAfectadas = statement.executeUpdate();
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
            
            while(resultSet.next()){
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
            
        }catch (SQLException e){
            e.printStackTrace();
        }
        return transferencias;
    }
}
