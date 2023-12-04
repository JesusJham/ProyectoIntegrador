
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
        <!--=================== Google Fonts ====================-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Space+Grotesk:wght@400;500;600;700&display=swap"
              rel="stylesheet">
        <!--=================== Form CSS ====================-->
        <link rel="stylesheet" href="css/form.css">
        <!--=================== Main CSS ====================-->
        <link rel="stylesheet" href="css/index.css">
        <!--=================== Remixicons ====================-->
        <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
         <script src="js/scripts.js"></script>
    </head>
    <body>
        <!--=================== Header ====================-->
        <header class="header" id="header">
            <div class="container">
                <nav class="nav">
                    <a href="index.jsp" class="nav__brand">
                        <img src="img/logo.png" alt="Crypto Application " class="logo__img">
                        <div class="logo__text">NEXUS</div>
                    </a>
                    <div class="nav__menu" id="nav__menu">
                        <ul class="nav__list">
                            <li class="nav__item">
                                <a href="index.jsp" class="nav__link">Inicio</a>
                            </li>
                            <li class="nav__item">
                                <a href="index.jsp" class="nav__link">Nosotros</a>
                            </li>
                            <li class="nav__item">
                                <a href="index.jsp" class="nav__link">Cualidades</a>
                            </li>
                            <li class="nav__item">
                                <a href="index.jsp" class="nav__link">Opiniones</a>
                            </li>
                        </ul>
                        <div class="nav__login">
                            <a href="IniciarSesion.jsp" class="nav__brand">
                                <i class="ri-account-circle-fill"></i>Iniciar Sesión
                            </a>
                            <a href="Registrar.jsp" class="nav__brand">Registrarse</a>
                        </div>
                    </div>
                    <div class="nav__toggle">
                        <i id="nav-toggle" class="ri-menu-3-line"></i>
                    </div>
                </nav>
            </div>
        </header>

        <div class="form-wrapper">
            <div class="form-container register">
                <h1 class="form-title">Registro de Usuario</h1>
                <form action="RegistrarUsuario" method="post" id="RegistroForm">
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" name="nombre" required class="form-input">

                    <label for="apellido" class="form-label">Apellido:</label>
                    <input type="text" name="apellido" required class="form-input">

                    <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento:</label>
                    <input type="date" name="fechaNacimiento" required class="form-input">

                    <label for="celular" class="form-label">Celular:</label>
                    <input type="text" name="celular" required class="form-input">

                    <label for="email" class="form-label">Email:</label>
                    <input type="email" name="email" required class="form-input">

                    <label for="password" class="form-label">Contraseña:</label>
                    <input type="password" name="password" required class="form-input">

                    <input type="submit" value="Registrarse" class="form-submit">
                </form>
            </div>
        </div>
    </body>
</html>
