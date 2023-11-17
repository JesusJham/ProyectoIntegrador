/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControladorServlet;

import DAO.SesionDAOImpl;
import DAO.UsuarioDAOImpl;
import Interfaces.SesionDAO;
import Interfaces.UsuarioDAO;
import Modelo.Sesion;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "IniciarSesion", urlPatterns = {"/IniciarSesion"})
public class IniciarSesion extends HttpServlet {

    private UsuarioDAO usuarioDAO;
    private SesionDAO sesionDAO;

    @Override
    public void init() throws ServletException {
        // Inicializar los DAO aquí
        usuarioDAO = new UsuarioDAOImpl();
        sesionDAO = new SesionDAOImpl();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Realiza la autenticación del usuario
        if (autenticarUsuario(email, password)) {
            // Si las credenciales son válidas, crea una sesión
            // Obtener el usuario a partir del correo electrónico
            Usuario usuario = usuarioDAO.obtenerUsuarioPorEmail(email);
            Usuario idUsuario = new Usuario(usuario.getIdUsuario());

            // Generar un token único
            String token = generarTokenUnico();
            //Generar fechaExpiracion
            Date fechaExpiracion = fechaExpiracion();

            // Crear una nueva instancia de Sesion
            Sesion sesion = new Sesion(0, idUsuario, token, fechaExpiracion);  // Esta es la línea donde se crea la instancia de Sesion

            // Luego, guardar la sesión en la base de datos
            boolean sesionCreada = sesionDAO.crearSesion(sesion);

            if (sesionCreada) {
                // Sesión creada exitosamente
                // Crear o recuperar una sesión de servlet

                HttpSession session = request.getSession();
                session.setAttribute("nombreUsuario", usuario.getNombre());
                session.setAttribute("idUsuario", usuario.getIdUsuario());
                session.setAttribute("email", usuario.getEmail());
                // Guardar el ID de la sesión en la sesión de servlet
                session.setAttribute("sesionId", sesion.getIdSesion());
                session.setAttribute("token", sesion.getToken());

                // Redirige al usuario a una página de inicio de sesión exitosa
                response.sendRedirect("Inicio.jsp");
            } else {
                // Error al crear la sesión, redirige al usuario a una página de error
                response.sendRedirect("inicioError1.jsp");
            }
        } else {
            // Si las credenciales son incorrectas, redirige al usuario a una página de error
            response.sendRedirect("inicioError2.jsp");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private boolean autenticarUsuario(String email, String password) throws SQLException {
        // Implementa la lógica de autenticación aquí
        return usuarioDAO.autenticarUsuario(email, password);
    }

    private String generarTokenUnico() {
        SecureRandom random = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        random.nextBytes(tokenBytes);
        return Base64.getEncoder().encodeToString(tokenBytes);
    }

    private Date fechaExpiracion() {
        // Obtener la hora actual
        Calendar calendar = Calendar.getInstance();
        // Añadir 30 minutos
        calendar.add(Calendar.MINUTE, -270);
        // Obtener la fecha de expiración
        Date fechaExpiracion = calendar.getTime();
        return fechaExpiracion;
    }

}
