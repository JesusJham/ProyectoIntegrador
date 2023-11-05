/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Tarjeta;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;

public interface TarjetaDAO {

    boolean agregarTarjeta(Tarjeta tarjeta, HttpSession session);
    List<Tarjeta> obtenerTarjetas(int idUsuario) throws SQLException;

}
