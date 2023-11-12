
package Interfaces;

import Modelo.Transferencias;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;

public interface TransferenciasDAO {
    
    boolean registrarTransferencia(Transferencias transferencias, HttpSession session);
    List<Transferencias> obtenerTransferencia(int idUsuario);
    boolean actualizarEstadoPorUsuario(int idTransferencia, String nuevoEstado, Date nuevaFecha);
}
