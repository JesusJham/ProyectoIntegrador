/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControladorServlet;

import DAO.TransferenciasDAOImpl;
import Modelo.CuentaBancarias;
import Modelo.Tarjeta;
import Modelo.Transferencias;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "EnviarCorreo", urlPatterns = {"/EnviarCorreo"})
public class EnviarCorreo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idUsuario = (int) session.getAttribute("idUsuario");
        String toEmail = (String) session.getAttribute("email");

        String token = (String) session.getAttribute("token");

        String subject = "Confirmación para la transacción";
        String montoEnviadoStr = request.getParameter("montoEnviado");
        String montoRecibidoStr = request.getParameter("montoRecibido");
        int idTarjeta = Integer.parseInt(request.getParameter("tarjeta"));
        int idCuentaB = Integer.parseInt(request.getParameter("cuenta"));
        String tipoOperacion = request.getParameter("operacionTipo");
        float tipoCambio = Float.parseFloat(request.getParameter("taza"));
        String estado = "En Proceso";
        int codigo = generarCodigoUnico();
        Date fechaHora = fechaHora();
        float montoEnviado = Float.parseFloat(montoEnviadoStr); // Establece un valor predeterminado o directo
        float montoRecibido = Float.parseFloat(montoRecibidoStr);

        // Verificar el tipo de operación y establecer los símbolos de moneda correspondientes
        String simboloMontoEnviado = "";
        String simboloMontoRecibido = "";

        //Informacion adicional para el envio del correo
        String nombreTitular = request.getParameter("nombreTitular");
        String numeroTarjeta = request.getParameter("numeroTarjeta");
        String nombreBancoTarjeta = request.getParameter("nombreBancoTarjeta");
        String nombreBancoCuenta = request.getParameter("nombreBancoCuenta");
        String numeroCuenta = request.getParameter("numeroCuenta");

        Usuario usuario = new Usuario(idUsuario);
        Tarjeta tarjeta = new Tarjeta(idTarjeta);
        CuentaBancarias cuentaBancarias = new CuentaBancarias(idCuentaB);

        Transferencias transferencias = new Transferencias(0, usuario, tarjeta, cuentaBancarias, codigo, tipoOperacion, montoEnviado, montoRecibido, tipoCambio, estado, fechaHora);
        TransferenciasDAOImpl transferenciasDAOImpl = new TransferenciasDAOImpl();

        boolean exito = transferenciasDAOImpl.registrarTransferencia(transferencias, session);

        // Configuración de la sesión de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Credenciales de la cuenta de Gmail
        String username = "jhidalgo0202@gmail.com"; // Reemplaza con tu dirección de correo de Gmail
        String password = "juyg gyxh teyh cxhn"; // Reemplaza con tu contraseña de Gmail

        // Autenticación
        Session sesion = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            if (exito) {

                int idTransferencia = transferencias.getIdTransferencia();

                // Crear el mensaje
                Message message = new MimeMessage(sesion);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
                message.setSubject(subject);

                // Generar token único
                String urlBase = "http://localhost:8080/ProyectoIntegrador/RegistrarOperacion?token=" + token;

                String urlConfirmacion = urlBase + "&accion=confirmar";
                String urlCancelar = urlBase + "&accion=cancelar";

                // Incluir el mensaje con el formulario y el botón de confirmación
                String emailContent = "Tipo de operación: " + tipoOperacion + "\n\nTaza:\n" + tipoCambio
                        + "\n\nDetalles del Envío:\n" + "\n\nMonto Enviado:\n" + montoEnviadoStr + simboloMontoEnviado
                        + "\n\nNombre del titular de la Tarjeta:\n" + nombreTitular
                        + "\n\nNombre del banco de la Tarjeta:\n" + nombreBancoTarjeta
                        + "\n\nNúmero de la tarjeta:\n" + numeroTarjeta
                        + "\n\nDetalles de Recepción:\n" + "\n\nMonto a Recibir:\n" + montoRecibidoStr + simboloMontoRecibido
                        + "\n\nNombre del Banco de la Cuenta Bancaria:\n" + nombreBancoCuenta
                        + "\n\nNúmero de Cuenta:\n" + numeroCuenta
                        + "<form action=\"" + urlConfirmacion + "\" method=\"post\">"
                        + "<input type=\"hidden\" name=\"idUsuario\" value=\"" + idUsuario + "\">"
                        + "<input type=\"hidden\" name=\"idTransferencia\" value=\"" + idTransferencia + "\">"
                        + "<input type=\"submit\" name=\"accion\" value=\"Confirmar\">"
                        + "</form>"
                        + "<form action=\"" + urlCancelar + "\" method=\"post\">"
                        + "<input type=\"hidden\" name=\"idUsuario\" value=\"" + idUsuario + "\">"
                        + "<input type=\"hidden\" name=\"idTransferencia\" value=\"" + idTransferencia + "\">"
                        + "<input type=\"submit\" name=\"accion\" value=\"Cancelar\">"
                        + "</form>";

                message.setContent(emailContent, "text/html; charset=utf-8");

                // Almacenar el token en algún lugar para su verificación posterior
                // Enviar el mensaje
                Transport.send(message);

                System.out.println("Correo enviado exitosamente.");

                // Redirigir a la página de éxito
                RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio.jsp");
                dispatcher.forward(request, response);

            } else {
                response.sendRedirect("error.jsp");
            }

        } catch (MessagingException e) {
            e.printStackTrace(); // Agrega un registro para entender cualquier problema

            // Manejar errores
            throw new RuntimeException(e);
        }
    }

    private Date fechaHora() {
        // Obtener la hora actual
        Calendar calendar = Calendar.getInstance();
        // Obtener la fecha y hora actual
        calendar.add(Calendar.MINUTE, -300);
        Date fechaHoraActual = calendar.getTime();
        return fechaHoraActual;
    }

    private static int generarCodigoUnico() {
        Random rand = new Random();
        int min = 10000000; // El número más pequeño de 8 dígitos
        int max = 99999999; // El número más grande de 8 dígitos
        int codigoAleatorio = rand.nextInt((max - min) + 1) + min;
        return codigoAleatorio;
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
