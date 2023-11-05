<%@page import="DAO.CuentaBancariaDAOImpl"%>
<%@page import="Modelo.CuentaBancarias"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> <!-- Habilita el acceso a la sesión -->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tarjetas</title>
        <link rel="stylesheet" href="css/inicio.css">

    </head>
    <body>
        <%@include file="HeaderVertical.jsp" %>
        <main class="main-content">
            <div class="content-container">
                <h2 class="main__title">Tarjetas</h2>
                <div class="description__cuentas">
                    <p>Puedes elegir una tarjeta favorita
                        la cual será de fácil acceso durante tu operación.</p>
                    <a href="agregarTarjeta.jsp" class="btn btn-primary">
                        <i class="fa-solid fa-circle-plus" style="color: var(--color-green);"></i> 
                        Tarjeta
                    </a>
                </div>
                <% // Verifica si el usuario ha iniciado sesión comprobando si "idUsuario" está en la sesión
                    int idUsuario = -1; // Valor por defecto si no se encuentra el idUsuario en la sesión

                    if (session.getAttribute("idUsuario") != null) {
                        idUsuario = (Integer) session.getAttribute("idUsuario");
                    } else {
                        // El usuario no ha iniciado sesión, puedes redirigirlo a una página de inicio de sesión o mostrar un mensaje de error
                        response.sendRedirect("IniciarSesion.jsp");
                    }
                %>
                <% if (idUsuario != -1) { // Verifica si se encontró un idUsuario válido en la sesión %>
                <input type="hidden" name="idUsuario" value="<%= idUsuario%>">
                <% TarjetaDAOImpl tarjetaDAO = new TarjetaDAOImpl();
                    List<Tarjeta> listaTarjetas = null;

                    try {
                        // Utiliza el idUsuario obtenido de la sesión para obtener las cuentas bancarias
                        listaTarjetas = tarjetaDAO.obtenerTarjetas(idUsuario);
                    } catch (SQLException e) {
                        out.println(e.toString());
                    }
                %>

                <%-- Mostrar la información de cada cuenta bancaria --%>
                <table class="container__table">
                    <tr class="container__row">
                        <th class="container__header">Nombre del Titular</th>
                        <th class="container__header">Número de Tarjeta</th>
                        <th class="container__header">Fecha de Vencimiento</th>
                        <th class="container__header">Tipo de Moneda</th>
                        <th class="container__header">Acciones</th>
                    </tr>

                    <%
                        for (Tarjeta tarjeta : listaTarjetas) {
                            // Escapamos los valores para evitar problemas de seguridad
                            String nombreTitular = tarjeta.getNombreTitular();
                            String nombreBanco = tarjeta.getBanco().getNombreBanco();
                            String numeroTarjeta = tarjeta.getNumeroTarjeta();
                            String fechaVencimiento = tarjeta.getFechaVencimiento();
                            String tipoMoneda = tarjeta.getMoneda().getTipoMoneda();
                    %>
                    <tr class="container__row">
                        <td class="container__cell">
                            <p><%= nombreTitular%></p>
                        </td>
                        <td class="container__cell">
                            <p class="cell__first"><%= nombreBanco%></p>
                            <p><%= numeroTarjeta%></p>
                        </td>
                        <td class="container__cell"><%= fechaVencimiento%></td>
                        <td class="container__cell"><%= tipoMoneda%></td>
                        <td class="container__cell"></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <% } else { %>
                <p>Debes iniciar sesión para ver tus cuentas bancarias.</p>
                <% }%>
            </div>
        </main>
    </body>
</html>
