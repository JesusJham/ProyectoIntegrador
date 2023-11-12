
package Interfaces;

import Modelo.HistorialEstadoTransferencia;
import javax.servlet.http.HttpSession;

public interface HistorialEstadoTransferenciaDAO {
    
        boolean registrarHistorialTransferencia(HistorialEstadoTransferencia historialEstadoTransferencia);

    
}
