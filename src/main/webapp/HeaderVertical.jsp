<%@page import="Modelo.*"%>
<%@page import="DAO.*"%>
<%@page import="java.sql.SQLException"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/inicio.css">
        <!--======================= Icons ================================== -->
        <script src="https://kit.fontawesome.com/cb34aa4764.js" crossorigin="anonymous"></script>
        <!--=================== SwiperJS CSS ====================-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css" />
        <!--===================== JavaScript main.js=========================-->
        <script src="js/main.js"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

        <title>Tu Página JSP</title>
    </head>
    <body>
        <%
            // Verificar si el usuario ha iniciado sesión
            //   HttpSession userSession = request.getSession(false);
            // if (session == null || userSession.getAttribute("nombreUsuario") == null) {
            // El usuario no ha iniciado sesión, redirigir a la página de inicio de sesión
            //   response.sendRedirect(request.getContextPath() + "/IniciarSesion.jsp");
            //}
%> 
        <header class="header" id="header">
            <div class="container-nav">
                <nav class="nav-header">
                    <a href="Inicio.jsp" class="nav__logo">
                        <img src="img/logo.png" alt="Crypto Application " class="logo__img">
                        <div class="logo__text">NEXUS</div>
                    </a>
                    <div class="nav__menu" id="nav__menu">
                        <div class="nav__login">
                            <a href="IniciarSesion.jsp" class="nav__brand">
                                <i class="fa-solid fa-user" style="color: #ffffff; padding: 10px"></i><%= session.getAttribute("nombreUsuario")%>
                            </a>
                        </div>
                        <div class="nav__toggle">
                            <i id="nav-toggle" class="ri-menu-3-line"></i>
                        </div>
                    </div>
                </nav>
            </div>
        </header>
        <div class="container-nav">
            <nav class="vertical-nav">
                <ul class="nav-ul">
                    <li class="nav-li"><a href="Inicio.jsp" class="nav__item">Inicio de transferencia</a></li>
                    <li class="nav-li"><a href="HistorialOperaciones.jsp" class="nav__item">Historial de Operaciones</a></li>
                    <li class="nav-li"><a href="Tarjetas.jsp" class="nav__item">Tarjetas</a></li>
                    <li class="nav-li"><a href="CuentasBancarias.jsp" class="nav__item">Cuentas Bancarias</a></li>
                </ul>
            </nav>
        </div>
    </body>
</html>
