/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.CuentaBancarias;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;

public interface CuentaBancariaDAO {
    
    boolean agregarCuenta(CuentaBancarias cuentaBancarias, HttpSession session);
    List<CuentaBancarias> obtenerCuentaBancarias(int idUsuario) throws SQLException;

}
