
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
        <%@include file="HeaderVertical.jsp" %>
        <!-- Contenido principal de la página -->
        <main class="main-content">
            <div class="content-container">
                <h2 class="main__title">Agregar una nueva cuenta </h2>
                <form action="AgregarCuenta" method="post" id="cuentaForm">
                    <div class="form-container">
                        <div class="form-row">
                            <div class="form-col">
                                <label for="banco" class="label-container">Banco:</label>
                                <select id="banco" name="banco" class="select-container">
                                    <option disabled selected class="options-container">Selecciona un banco</option>
                                    <%BancoDAOImpl bancoDAOImpl = new BancoDAOImpl();
                                        List<Banco> listaBancos = null;
                                        try {
                                            listaBancos = bancoDAOImpl.obtenerBancos();
                                        } catch (SQLException e) {
                                            out.println(e.toString());
                                        }
                                    %>
                                    <% for (Banco banco : listaBancos) {%>
                                    <option class="options-container" value="<%= banco.getIdBanco()%>"><%= banco.getNombreBanco()%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div class="form-col">
                                <label for="moneda" class="label-container">Tipo de Moneda:</label>
                                <select id="moneda" name="moneda"  class="select-container">
                                    <option disabled selected class="options-container">Selecciona una moneda</option>
                                    <%
                                        MonedaDAOImpl monedaDAOImpl = new MonedaDAOImpl();
                                        List<Moneda> listaMonedas = null;
                                        try {
                                            listaMonedas = monedaDAOImpl.obtenerMonedas();
                                        } catch (SQLException e) {
                                            out.println(e.toString());
                                        }
                                    %>
                                    <% for (Moneda moneda : listaMonedas) {%>
                                    <option class="options-container" value="<%= moneda.getIdMoneda()%>"><%= moneda.getTipoMoneda()%></option>
                                    <%}%>
                                </select>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-col">
                                <label for="numeroCuenta" class="label-container">Número de Cuenta:</label>
                                <input type="text" id="numeroCuenta" name="numeroCuenta" class="input-container" required placeholder="Ingresa tu número de cuenta aquí.">
                            </div>
                            <div class="form-col">
                                <label for="aliasBanco" class="label-container">Ponle nombre a tu cuenta:</label>
                                <input type="text" id="aliasBanco" name="aliasBanco"  class="input-container" placeholder="¿Cuál será el nombre de tu cuenta?" required >
                            </div>
                        </div>
                    </div>
                    <div class="form-buttons">
                        <button type="submit" class="btn btn-primary">Agregar Cuenta</button>
                        <button type="button" class="btn btn-secondary" id="cancelButton">Cancelar</button>
                    </div>
                </form>
            </div>
        </main>
        <script src="js/script.js"></script>
    </body>
</html>
