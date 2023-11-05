
package Interfaces;

import Modelo.Moneda;
import java.sql.SQLException;
import java.util.List;

public interface MonedaDAO {
        List<Moneda> obtenerMonedas() throws SQLException;

}
