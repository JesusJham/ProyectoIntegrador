/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Transferencias;
import java.util.List;
import javax.servlet.http.HttpSession;

public interface TransferenciasDAO {
    
    boolean registrarTransferencia(Transferencias transferencias, HttpSession session);
    List<Transferencias> obtenerTransferencia(int idUsuario);
}
