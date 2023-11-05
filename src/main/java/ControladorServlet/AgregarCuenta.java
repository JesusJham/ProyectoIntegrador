/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControladorServlet;

import DAO.CuentaBancariaDAOImpl;
import Interfaces.CuentaBancariaDAO;
import Modelo.Banco;
import Modelo.CuentaBancarias;
import Modelo.Moneda;
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
@WebServlet(name = "AgregarCuenta", urlPatterns = {"/AgregarCuenta"})
public class AgregarCuenta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            HttpSession session = request.getSession();
            int idUsuario = (int) session.getAttribute("idUsuario");

            int idBanco = Integer.parseInt(request.getParameter("banco"));
            int idMoneda = Integer.parseInt(request.getParameter("moneda"));
            String aliasBanco = request.getParameter("aliasBanco");
            String numeroCuenta = request.getParameter("numeroCuenta");

            Usuario usuario = new Usuario(idUsuario);
            Banco banco = new Banco(idBanco);
            Moneda moneda = new Moneda(idMoneda);

            CuentaBancarias cuentaBancarias = new CuentaBancarias(0, usuario, banco, moneda, aliasBanco, numeroCuenta);

            CuentaBancariaDAOImpl cuentaDAO = new CuentaBancariaDAOImpl();

            boolean exito = cuentaDAO.agregarCuenta(cuentaBancarias, session);

            if (exito) {
                // La cuenta se agregó correctamente, redirecciona a una página de éxito
                response.sendRedirect("CuentasBancarias.jsp");
            } else {
                // Hubo un error al agregar la cuenta, redirecciona a una página de error
                response.sendRedirect("error.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de excepciones aquí, puedes redirigir a una página de error personalizada si es necesario
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
