package Interfaces;

import Modelo.Banco;
import java.sql.SQLException;
import java.util.List;

public interface BancoDAO {

    List<Banco> obtenerBancos() throws SQLException;
    
}
