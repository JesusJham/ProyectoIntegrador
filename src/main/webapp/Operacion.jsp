<%@page import="DAO.TarjetaDAOImpl"%>
<%@page import="Modelo.Tarjeta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%> <!-- Habilita el acceso a la sesión -->

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cuentas Bancarias</title>
        <link rel="stylesheet" href="css/inicio.css">
        <script src="js/scripts.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    </head>
    <body>
        <%@include file="HeaderVertical.jsp" %>
        <%            // Obtén los valores de "montoEnviado2" y "montoRecibido2" del primer formulario
            String montoEnviado = request.getParameter("montoEnviado");
            String montoRecibido = request.getParameter("montoRecibido");
            if (montoEnviado == null || montoRecibido == null) {
                // Manejo de error o redirección a una página de error
                response.sendRedirect("error.jsp");
                return; // Termina la ejecución del código para evitar NullPointerException
            }
            HttpSession sesion = request.getSession();
            sesion.setAttribute("montoEnviado", montoEnviado);
            sesion.setAttribute("montoRecibido", montoRecibido);
        %>
        <main class="main-content">
            <div class="currency-container">
                <h2 class="main__title">Registro de operación</h2>
                <div class="description__cuentas">
                    <p>Selecciona la tarjeta en la que nos transferirás y agrega 
                        la cuenta bancaria donde recibirás el dinero:</p>
                </div>
                <%
                    String idUsuarioStr = request.getParameter("idUsuario");
                    int idUsuario = Integer.parseInt(idUsuarioStr);
                    String operacionTipo = request.getParameter("operacionTipo");
                    String taza = request.getParameter("taza");
                %>

                <form action="EnviarCorreo" method="post" id="OperacionForm">
                    <div class="form-container">
                        <div class="form-col">
                            <% if ("compra".equals(request.getParameter("operacionTipo"))) {%>
                            <label for="enviarMoney" class="label-container">Enviarás en Dólares:</label>
                            <p class="enviarMoney">USD $ <%= montoEnviado%></p>
                            <input type="hidden" name="montoEnviado" value="<%= montoEnviado%>">
                            <input type="hidden" name="operacionTipo" value="<%= operacionTipo%>">
                            <%} else if ("venta".equals(request.getParameter("operacionTipo"))) {%>
                            <label for="enviarMoney" class="label-container">Enviarás en Soles:</label>
                            <p class="enviarMoney">PEN S/ <%= montoEnviado%></p>
                            <input type="hidden" name="montoEnviado" value="<%= montoEnviado%>">
                            <input type="hidden" name="operacionTipo" value="<%= operacionTipo%>">
                            <%}%>
                            <label for="tarjeta" class="label-container">Tu tarjeta:</label>
                            <select id="tarjeta" name="tarjeta"  class="select-container">
                                <option disabled selected class="options-container">¿Desde qué tarjeta enviarás tu dinero?</option>
                                <% TarjetaDAOImpl tarjetaDAO = new TarjetaDAOImpl();
                                    List<Tarjeta> listaTarjetas = null;

                                    try {
                                        // Utiliza el idUsuario obtenido de la sesión para obtener las cuentas bancarias
                                        listaTarjetas = tarjetaDAO.obtenerTarjetas(idUsuario);
                                    } catch (SQLException e) {
                                        out.println(e.toString());
                                    }
                                %>
                                <%for (Tarjeta tarjeta : listaTarjetas) {%>
                                <option class="options-container" value="<%= tarjeta.getIdTarjeta()%>" 
                                        data-titular="<%= tarjeta.getNombreTitular()%>" 
                                        data-numeroTarjeta="<%= tarjeta.getNumeroTarjeta()%>"
                                        data-nombreBanco="<%= tarjeta.getBanco().getNombreBanco()%>">
                                    <%= tarjeta.getNumeroTarjeta()%></option>
                                    <%}%>
                            </select>
                            <input type="hidden" name="nombreTitular" id="nombreTitular" value="">
                            <input type="hidden" name="numeroTarjeta" id="numeroTarjeta" value="">
                            <input type="hidden" name="nombreBancoTarjeta" id="nombreBancoTarjeta" value="">
                            <label for="titular" class="label-container">Titular de la tarjeta:</label>
                            <span id="titular" class="titular-container"></span>
                        </div>
                        <div class="form-col">
                            <% if ("compra".equals(request.getParameter("operacionTipo"))) {%>
                            <label for="enviarMoney" class="label-container">Recibirás en Soles:</label>
                            <p class="enviarMoney" >PEN S/ <%= montoRecibido%></p>
                            <input type="hidden" name="montoRecibido" value="<%= montoRecibido%>">
                            <%} else if ("venta".equals(request.getParameter("operacionTipo"))) {%>
                            <label for="enviarMoney" class="label-container">Recibirás en Dólares</label>
                            <p class="enviarMoney">USD $ <%= montoRecibido%></p>
                            <input type="hidden" name="montoRecibido" value="<%= montoRecibido%>">
                            <%}%>
                            <label for="cuenta" class="label-container">Tu Cuenta Bancaria:</label>
                            <select id="cuenta" name="cuenta"  class="select-container">
                                <option disabled selected class="options-container">Elige a dónde te transferimos el dinero:</option>
                                <% CuentaBancariaDAOImpl cuentaDAO = new CuentaBancariaDAOImpl();
                                    List<CuentaBancarias> listaCuentas = null;

                                    try {
                                        // Utiliza el idUsuario obtenido de la sesión para obtener las cuentas bancarias
                                        listaCuentas = cuentaDAO.obtenerCuentaBancarias(idUsuario);
                                    } catch (SQLException e) {
                                        out.println(e.toString());
                                    }
                                %>
                                <%for (CuentaBancarias cuenta : listaCuentas) {%>
                                <option class="options-container" value="<%= cuenta.getIdCuentaB()%>" 
                                        data-titular="<%= cuenta.getBanco().getNombreBanco()%>"
                                        data-numeroCuenta="<%= cuenta.getNumeroCuenta()%>">
                                    <%= cuenta.getNumeroCuenta()%></option>
                                    <%}%>
                            </select>
                            <input type="hidden" name="nombreBancoCuenta" id="nombreBancoCuenta" value="">
                            <input type="hidden" name="numeroCuenta" id="numeroCuenta" value="">
                            <label for="banco" class="label-container">Nombre del Banco:</label>
                            <span id="banco" class="titular-container"></span>
                            <input type="hidden" name="taza" value="<%= taza%>">
                        </div>
                    </div>
                    <div class="form-buttons">
                        <button type="submit" class="btn btn-primary">Finalizar Operación</button>
                        <button type="button" class="btn btn-secondary" id="cancelButton">Cancelar</button>
                    </div>
                </form>
            </div>
        </main>
    </body>
    <script>
        document.getElementById("tarjeta").addEventListener("change", function () {
            var selectedTarjeta = this.value;
            console.log("ID de la tarjeta seleccionada: " + selectedTarjeta);
        });

        document.getElementById('tarjeta').addEventListener('change', function () {
            var selectedOption = this.options[this.selectedIndex];
            var nombreTitular = selectedOption.getAttribute('data-titular');
            var numeroTarjeta = selectedOption.getAttribute('data-numeroTarjeta');
            var nombreBancoTarjeta = selectedOption.getAttribute('data-nombreBanco');

            document.getElementById('nombreTitular').value = nombreTitular;
            document.getElementById('numeroTarjeta').value = numeroTarjeta;
            document.getElementById('nombreBancoTarjeta').value = nombreBancoTarjeta;
        });

        document.getElementById('cuenta').addEventListener('change', function () {
            var selectedOption = this.options[this.selectedIndex];
            var nombreBancoCuenta = selectedOption.getAttribute('data-titular');
            var numeroCuenta = selectedOption.getAttribute('data-numeroCuenta');

            document.getElementById('nombreBancoCuenta').value = nombreBancoCuenta;
            document.getElementById('numeroCuenta').value = numeroCuenta;
        });
    </script>

</html>
