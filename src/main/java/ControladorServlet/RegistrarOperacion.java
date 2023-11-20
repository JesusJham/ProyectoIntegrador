/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControladorServlet;

import DAO.TransferenciasDAOImpl;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
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
            String token = request.getParameter("token");
            String accion = request.getParameter("accion");
            String idTransferenciaParam = request.getParameter("idTransferencia");
            int idTransferencia = 0; // Valor predeterminado en caso de que el parámetro sea nulo
            Date nuevaFecha = fechaHora(); // Obtener la fecha actual

            if (idTransferenciaParam != null && !idTransferenciaParam.isEmpty()) {
                // Si el parámetro no es nulo ni está vacío, intenta convertirlo a un entero
                idTransferencia = Integer.parseInt(idTransferenciaParam);
            } else {
                // Manejar el caso en el que el parámetro es nulo o está vacío
                // Puedes imprimir un mensaje de error o tomar alguna otra acción apropiada.
                System.err.println("El parámetro 'idTransferencia' es nulo o está vacío.");
            }

            if ("confirmar".equals(accion)) {
                // Se ha realizado la transferencia, ahora programamos la actualización del estado
                String nuevoEstado = "Atendido"; // El nuevo estado

                // Llamar al método para actualizar el estado
                TransferenciasDAOImpl transferenciasDAO = new TransferenciasDAOImpl();
                boolean estadoActualizado = transferenciasDAO.actualizarEstadoPorUsuario(idTransferencia, nuevoEstado, nuevaFecha);
                if (estadoActualizado) {
                    System.out.println("Estado actualizado de inmediato Confirmado");
                } else {
                    System.out.println("Error al actualizar el estado");
                }
                response.sendRedirect("HistorialOperaciones.jsp");
            } else if ("cancelar".equals(accion)) {
                String nuevoEstado = "Cancelado"; // El nuevo estado

                // Llamar al método para actualizar el estado
                TransferenciasDAOImpl transferenciasDAO = new TransferenciasDAOImpl();
                boolean estadoActualizado = transferenciasDAO.actualizarEstadoPorUsuario(idTransferencia, nuevoEstado, nuevaFecha);
                if (estadoActualizado) {
                    System.out.println("Estado actualizado de inmediato");
                } else {
                    System.out.println("Error al actualizar el estado Cancelado");
                }
                response.sendRedirect("HistorialOperaciones.jsp");
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

    private Date fechaHora() {
        // Obtener la hora actual
        Calendar calendar = Calendar.getInstance();
        // Obtener la fecha y hora actual
        calendar.add(Calendar.MINUTE, -300);
        Date fechaHoraActual = calendar.getTime();
        return fechaHoraActual;
    }

}
