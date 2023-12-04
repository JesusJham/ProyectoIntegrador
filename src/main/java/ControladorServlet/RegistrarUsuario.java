package ControladorServlet;

import DAO.UsuarioDAOImpl;
import Modelo.Usuario;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RegistrarUsuario", urlPatterns = {"/RegistrarUsuario"})
public class RegistrarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AddressException, MessagingException {
        HttpSession session = request.getSession();
        String subject = "Confirmación de registro";
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        String celular = request.getParameter("celular");
        String toEmail = request.getParameter("email");
        String password = request.getParameter("password");
        String nuevoToken = generarNuevoToken();
        session.setAttribute("token", nuevoToken); // Almacena el nuevo token en la sesión

        // Parsea la fecha de nacimiento
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNacimiento = null;
        try {
            fechaNacimiento = sdf.parse(fechaNacimientoStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Crea una instancia de Usuario
        Usuario usuario = new Usuario(0, nombre, apellido, fechaNacimiento, new Date(), celular, toEmail, password);

        // Crea una instancia de UsuarioDAO y registra al usuario
        UsuarioDAOImpl usuarioDAOImpl = new UsuarioDAOImpl();

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Credenciales de la cuenta de Gmail
        String username = "jhidalgo0202@gmail.com"; // Reemplaza con tu dirección de correo de Gmail
        String pass = "juyg gyxh teyh cxhn"; // Reemplaza con tu contraseña de Gmail

        // Autenticación
        Session sesion = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, pass);
            }
        });

        if (usuarioDAOImpl.insertarUsuario(usuario)) {
            // Crear el mensaje
            Message message = new MimeMessage(sesion);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);

            // Generar token único
            String urlBase = "http://localhost:8080/ProyectoIntegrador/ConfirmacionUsuarioRegistro?token=" + nuevoToken;

            String urlConfirmacion = urlBase + "&accion=confirmar";

            String emailContent = "<html>"
                    + "<head>"
                    + "<style>"
                    + "body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }"
                    + "h2 { color: #2ecc71; text-align: center; }"
                    + "p { color: #333; text-align: center; }"
                    + "form { margin-top: 20px; text-align: center; }"
                    + "input[type=submit] { background-color: #4CAF50; color: white; padding: 20px 30px; border: none; border-radius: 8px; cursor: pointer; font-size: 18px; transition: background-color 0.3s; }"
                    + "input[type=submit]:hover { background-color: #45a049; }"
                    + "input[type=submit]:active { background-color: #3e8e41; }"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div style='background-color: #ffffff; max-width: 600px; margin: 20px auto; padding: 20px;'>"
                    + "<h2>Bienvenido a Cambio de Moneda</h2>"
                    + "<p>¡Gracias por registrarte en nuestro servicio de cambio de moneda! Estamos emocionados de tenerte.</p>"
                    + "<p>Para confirmar tu registro, haz clic en el siguiente botón:</p>"
                    + "<form action='" + urlConfirmacion + "' method='post'>"
                    + "<input type='submit' value='Confirmar'>"
                    + "</form>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            message.setContent(emailContent, "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("Correo enviado exitosamente.");

            // Redirigir a la página de éxito
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } else {
            // Error en la inserción, redirige a una página de error
            response.sendRedirect("error.jsp");
        }

    }

    private String generarNuevoToken() {
        return UUID.randomUUID().toString();
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
        } catch (MessagingException ex) {
            Logger.getLogger(RegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (MessagingException ex) {
            Logger.getLogger(RegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
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

}
