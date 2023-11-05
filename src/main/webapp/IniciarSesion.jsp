
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
        <!-- ------             Iniciar Sesión ------------------>
        <div class="form-wrapper">
            <div class="form-container login">
                <h1 class="form-title">Iniciar Sesión</h1>
                <form action="IniciarSesion" method="post">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" name="email" required class="form-input">
                    <label for="password" class="form-label">Contraseña:</label>
                    <div class="password-input-container">
                        <input type="password" name="password" required class="password-input">
                        <i class="fas fa-eye" id="togglePassword"></i>
                        <!-- Ícono de ojo para mostrar/ocultar la contraseña -->
                    </div>
                    <input type="submit" value="Iniciar Sesión" class="form-submit">
                </form>
            </div>
        </div>
    </body>
</html>
