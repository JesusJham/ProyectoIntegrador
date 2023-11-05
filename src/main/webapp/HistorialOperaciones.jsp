<%@page import="java.util.Date"%>
<%@page import="DAO.TransferenciasDAOImpl"%>
<%@page import="Modelo.Transferencias"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> <!-- Habilita el acceso a la sesión -->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Historial</title>
        <link rel="stylesheet" href="css/inicio.css">

    </head>
    <body>
        <%@include file="HeaderVertical.jsp" %>
        <main class="main-content" style="overflow: auto">
            <div class="content-container">
                <h2 class="main__title">Historial de operaciones</h2>

                <%    int idUsuario = -1;

                    if (session.getAttribute("idUsuario") != null) {
                        idUsuario = (Integer) session.getAttribute("idUsuario");
                    } else {
                        response.sendRedirect("IniciarSesion.jsp");
                    }
                %>
                <% if (idUsuario != -1) {%>
                <input type="hidden" name="idUsuario" value="<%= idUsuario%>">
                <%
                    TransferenciasDAOImpl transferenciasDAO = new TransferenciasDAOImpl();
                    List<Transferencias> listaTransferencias = transferenciasDAO.obtenerTransferencia(idUsuario);
                %>




                <%-- Mostrar la información de cada cuenta bancaria --%>
                <table class="container__table">
                    <tr class="container__row">
                        <th class="container__header">N° de Operación</th>
                        <th class="container__header">Envíado</th>
                        <th class="container__header">Recibe</th>
                        <th class="container__header">Tipo de Operación</th>
                        <th class="container__header">Tipo de cambio</th>
                        <th class="container__header">Fecha</th>
                        <th class="container__header">Estado</th>
                        <th class="container__header">Acciones</th>
                    </tr>

                    <%
                        for (Transferencias transferencia : listaTransferencias) {
                            // Escapamos los valores para evitar problemas de seguridad
                            int codigoOper = transferencia.getCodigoOper();
                            float montoEnviado = transferencia.getMontoEnviado();
                            float montoRecibido = transferencia.getMontoRecibido();
                            String tipoOperacion = transferencia.getTipoOperacion();
                            float tipoCambio = transferencia.getTipoCambio();
                            Date fechaHora = transferencia.getFechaHora();
                            String estado = transferencia.getEstado();

                    %>
                    <tr class="container__row">
                        <td class="container__cell"><%= codigoOper%></td>
                        <td class="container__cell"><%= montoEnviado%></td>
                        <td class="container__cell"><%= montoRecibido%></td>
                        <td class="container__cell"><%= tipoOperacion%></td>
                        <td class="container__cell"><%= tipoCambio%></td>
                        <td class="container__cell"><%= fechaHora%></td>
                        <td class="container__cell"><%= estado%></td>
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
