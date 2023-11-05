
package ControladorServlet;

import DAO.UsuarioDAOImpl;
import Modelo.Usuario;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegistrarUsuario", urlPatterns = {"/RegistrarUsuario"})
public class RegistrarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Parsea la fecha de nacimiento
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNacimiento = null;
        try {
            fechaNacimiento = sdf.parse(fechaNacimientoStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Crea una instancia de Usuario
        Usuario usuario = new Usuario(0, nombre, apellido, fechaNacimiento, new Date(), celular, email, password);

        // Crea una instancia de UsuarioDAO y registra al usuario
        UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();
        if (usuarioDAOImpl.insertarUsuario(usuario)) {
            // Inserción exitosa, redirige a una página de éxito
            response.sendRedirect("index.jsp");
        } else {
            // Error en la inserción, redirige a una página de error
            response.sendRedirect("error.jsp");
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
