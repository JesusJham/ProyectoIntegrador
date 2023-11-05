/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControladorServlet;

import DAO.TransferenciasDAOImpl;
import Modelo.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RegistrarOperacion", urlPatterns = {"/RegistrarOperacion"})
public class RegistrarOperacion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            int idUsuario = (int) session.getAttribute("idUsuario");
            String montoEnviadoStr = request.getParameter("montoEnviado");
            String montoRecibidoStr = request.getParameter("montoRecibido");
            int idTarjeta = Integer.parseInt(request.getParameter("tarjeta"));
            int idCuentaB = Integer.parseInt(request.getParameter("cuenta"));
            String tipoOperacion = request.getParameter("operacionTipo");
            float tipoCambio = 3.6f;
            String estado = "Atendida";
            int codigo = generarCodigoUnico();
            Date fechaHora = fechaHora();
            float montoEnviado = Float.parseFloat(montoEnviadoStr); // Establece un valor predeterminado o directo
            float montoRecibido = Float.parseFloat(montoRecibidoStr);
            
            Usuario usuario = new Usuario(idUsuario);
            Tarjeta tarjeta = new Tarjeta(idTarjeta);
            CuentaBancarias cuentaBancarias = new CuentaBancarias(idCuentaB);

            Transferencias transferencias = new Transferencias(0, usuario, tarjeta, cuentaBancarias, codigo, tipoOperacion, montoEnviado, montoRecibido, tipoCambio, estado, fechaHora);
            TransferenciasDAOImpl transferenciasDAOImpl = new TransferenciasDAOImpl();

            boolean exito = transferenciasDAOImpl.registrarTransferencia(transferencias, session);

            if (exito) {
                response.sendRedirect("HistorialOperaciones.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static int generarCodigoUnico() {
        Random rand = new Random();
        int min = 10000000; // El número más pequeño de 8 dígitos
        int max = 99999999; // El número más grande de 8 dígitos
        int codigoAleatorio = rand.nextInt((max - min) + 1) + min;
        return codigoAleatorio;
    }

    private Date fechaHora() {
        // Obtener la hora actual
        Calendar calendar = Calendar.getInstance();
        // Obtener la fecha y hora actual
        calendar.add(Calendar.MINUTE, -300);
        Date fechaHoraActual = calendar.getTime();
        return fechaHoraActual;
    }

}
