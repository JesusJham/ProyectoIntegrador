/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControladorServlet;

import DAO.TarjetaDAOImpl;
import Modelo.Banco;
import Modelo.Moneda;
import Modelo.Tarjeta;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "AgregarTarjeta", urlPatterns = {"/AgregarTarjeta"})
public class AgregarTarjeta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            int idUsuario = (int) session.getAttribute("idUsuario");
            String numeroTarjeta = request.getParameter("numeroTarjeta");
            String cvv = request.getParameter("cvv");
            String fechaVencimiento = request.getParameter("fechaVencimiento");
            String nombreTitular = request.getParameter("nombreTitular");
            int idBanco = Integer.parseInt(request.getParameter("banco"));
            int idMoneda = Integer.parseInt(request.getParameter("moneda"));

            Usuario usuario = new Usuario(idUsuario);
            Banco banco = new Banco(idBanco);
            Moneda moneda = new Moneda(idMoneda);

            Tarjeta tarjeta = new Tarjeta(0, usuario, banco, moneda, numeroTarjeta, cvv, fechaVencimiento, nombreTitular);
            TarjetaDAOImpl tarjetaDAO = new TarjetaDAOImpl();

            boolean exito = tarjetaDAO.agregarTarjeta(tarjeta, session);

            if (exito) {
                response.sendRedirect("Tarjetas.jsp");
            } else {
                // Hubo un error al agregar la cuenta, redirecciona a una página de error
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

}
