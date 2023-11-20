<%@page import="DAO.CuentaBancariaDAOImpl"%>
<%@page import="Modelo.CuentaBancarias"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> <!-- Habilita el acceso a la sesión -->
<%@page import="javax.servlet.http.HttpSession"%>
<%
    HttpSession sesion = request.getSession();
    Integer idUsuario = (Integer) sesion.getAttribute("idUsuario");
    if (idUsuario == null) {
        // El usuario no ha iniciado sesión, redirigirlo a la página de inicio de sesión
        response.sendRedirect("IniciarSesion.jsp");
    }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio Operación</title>
        <link rel="stylesheet" href="css/inicio.css">
        <script src="js/main.js"></script>
        <script src="js/scripts.js"></script>

    </head>
    <body>
        <%@include file="HeaderVertical.jsp" %>
        <main class="main-content">
            <div class="currency-container">
                <h2 class="main__title">Cotiza tu operación</h2>
                <p><i class="fa-regular fa-clock" style="color: var(--color-green);"></i> Tiempo promedio de atención:<span class="span__p"> 15 min</span></p>
                <div class="currency-exchange-container">
                    <button class="currency-btn" id="compraBtn">Compra</button>
                    <button class="currency-btn" id="ventaBtn">Venta</button>
                </div>
                <div class="container-show-currency">
                    <div class="show-currency">
                        <div class="show-currency-num">
                            <span class="currency-text">¿Cuánto envías?</span>
                            <div class="container_numeric">
                                <span id="envioMoney">$</span>
                                <input type="text" class="show-money" name="montoEnviado" inputmode="numeric" value="100" id="envioInput">
                                <input type="hidden" id="envioMoneda" value="USD">
                            </div>
                        </div>
                        <div class="money" id="envioCurrency">Dólares</div>
                    </div>
                    <div class="show-currency">
                        <div class="show-currency-num">
                            <span class="currency-text">Entonces recibes</span>
                            <div class="container_numeric">
                                <span id="recibeMoney">S/</span>
                                <input type="text" class="show-money" name="montoRecibido" inputmode="numeric" value="360" id="recibeInput">
                                <input type="hidden" id="recibeMoneda" value="PEN">
                            </div>
                        </div>
                        <div class="money" id="recibeCurrency">Soles</div>
                    </div>
                </div>
                <div class="taza-cambio-container">
                    <button class="btn" id="cambio">cambio</button>
                </div>
                <form id="datosAdicionalesForm" method="POST" action="Operacion.jsp" class="datosAdicionalesForm">
                    <input type="hidden" id="operacionTipo" name="operacionTipo" value="">
                    <input type="hidden" id="montoEnviado" name="montoEnviado" value="">
                    <input type="hidden" id="montoRecibido" name="montoRecibido" value="">
                    <input type="hidden" id="taza" name="taza" value="">
                    <input type="hidden" id="idUsuario" name="idUsuario" value="<%= idUsuario%>">
                    <button type="submit" class="btn btn-primary" onclick="setMontos()">Inicio de operación</button>
                </form>
            </div>
        </main>
    </body>
    <script>
        function setMontos() {
            var montoEnviado = document.getElementById("envioInput").value;
            var montoRecibido = document.getElementById("recibeInput").value;

            document.getElementById("montoEnviado").value = montoEnviado;
            document.getElementById("montoRecibido").value = montoRecibido;
        }
    </script>

</html>

