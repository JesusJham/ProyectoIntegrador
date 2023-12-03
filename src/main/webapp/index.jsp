<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Nexus</title>
        <!--=================== Google Fonts ====================-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100&family=Space+Grotesk:wght@400;500;600;700&display=swap" rel="stylesheet">
        <!--=================== Remixicons ====================-->
        <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
        <!--=================== SwiperJS CSS ====================-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css" />
        <!--=================== Main CSS ====================-->
        <link rel="stylesheet" href="css/index.css">
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
                                <a href="#hero" class="nav__link">Inicio</a>
                            </li>
                            <li class="nav__item">
                                <a href="#nosotros" class="nav__link">Nosotros</a>
                            </li>
                            <li class="nav__item">
                                <a href="#qualities" class="nav__link">Cualidades</a>
                            </li>
                            <li class="nav__item">
                                <a href="#opinions" class="nav__link">Opiniones</a>
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
        <!--=================== main ====================-->
        <main class="main">
            <!--=================== Hero ====================-->
            <seccion class="hero" id="hero">
                <div class="container">
                    <div class="d-grid hero__wrapper">
                        <div class="hero__content">
                            <h1 class="hero__title">Nexus</h1>
                            <p class="hero__description">Una empresa confiable y segura. Además  de ofrecer el mejor tipo de cambio para cambiar dólares y soles online en Lima, Perú.
                            </p>
                        </div>
                        <div class="currency-container">
                            <div class="currency-exchange-container">
                                <button class="currency-btn">Compra</button>
                                <button class="currency-btn">Venta</button>
                            </div>
                            <div class="container-show-currency">
                                <div class="show-currency">
                                    <div class="show-currency-num">
                                        <span class="currency-text">¿Cuánto envías?</span>
                                        <input type="text" class="show-money" inputmode="numeric" value="S/ 100">
                                    </div>
                                    <div class="money">Soles</div>
                                </div>
                                <div class="show-currency">
                                    <div class="show-currency-num">
                                        <span class="currency-text">Entonces recibes</span>
                                        <input type="text" class="show-money" inputmode="numeric" value="$ 360">
                                    </div>
                                    <div class="money">Dólares</div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="section__header">
                        <h2 class="section__title">¿Como funciona?</h2>
                        <span class="section__subtitle">Proceso Sencillo</span>
                    </div>
                    <div class="d-grid process__wrapper">
                        <div class="process">
                            <img src="img/CREA_C.png" alt="icono1" class="process__img">
                            <h2 class="process__title">Crea tu cuenta</h2>
                            <p class="process__description">Crea tu cuenta personal o de
                                empresa, es completamente gratis.</p>
                        </div>
                        <div class="process">
                            <img src="img/cotiza.png" alt="icono2" class="process__img">
                            <h2 class="process__title">Cotiza tu operación</h2>
                            <p class="process__description">Elige el monto que deseas cambiar e inicia una operación.</p>
                        </div>
                        <div class="process">
                            <img src="img/transferencia.png" alt="icono3" class="process__img">
                            <h2 class="process__title">Transfiere</h2>
                            <p class="process__description">El monto desde tu banca y
                                envíanos una foto del comprobante.</p>
                        </div>
                        <div class="process">
                            <img src="img/recibe.png" alt="icono4" class="process__img">
                            <h2 class="process__title">Recibe</h2>
                            <p class="process__description">Recibe el dinero en una cuenta bancaria o tarjeta de crédito</p>
                        </div>
                    </div>
                </div>
            </seccion>

            <!--=================== Nosotros ====================-->
            <section class="section nosotros" id="nosotros">
                <div class="container">
                    <div class="section__header">
                        <h2 class="section__title">Nosotros</h2>
                        <span class="section__subtitle">Nuestra Historia</span>
                    </div>
                    <p class="section__description">Somos los mejores en nuestro campo, y todo gracias a las increíbles relaciones que hemos establecido con nuestros clientes. A diferencia de nuestros competidores, invertimos en desarrollar una conexión personal con todos y cada uno de nuestros clientes, brindándoles un servicio de calidad y estando disponibles las 24 horas del día, los 7 días de la semana</p>
                </div>
            </section>

            <!--=================== Qualities ====================-->
            <section class="section qualities" id="qualities">
                <div class="container">
                    <div class="section__header">
                        <h2 class="section__title">Cualidades</h2>
                        <span class="section__subtitle">Nuestros Atributos</span>
                    </div>
                    <div class="d-grid qualities__wrapper">
                        <div class="qualities__content">
                            <img src="img/calidad.png" alt="icono5" class="qualities__img">
                            <h3 class="qualities__title">Mejor Calidad</h3>
                            <p class="qualities__description">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Nihil quaerat,
                                quos porro libero sint eveniet incidunt dignissimos dicta. Cum impedit repellendus cupiditate soluta sunt
                                amet eaque minus id quo temporibus.</p>
                        </div>
                        <div class="qualities__content">
                            <img src="img/atencion.png" alt="icono6" class="qualities__img">
                            <h3 class="qualities__title">Soporte 24/7</h3>
                            <p class="qualities__description">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Nihil quaerat,
                                quos porro libero sint eveniet incidunt dignissimos dicta. Cum impedit repellendus cupiditate soluta
                                sunt amet eaque minus id quo temporibus.</p>
                        </div>
                        <div class="qualities__content">
                            <img src="img/precio.png" alt="icono6" class="qualities__img">
                            <h3 class="qualities__title">Precio competitivo</h3>
                            <p class="qualities__description">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Nihil quaerat,
                                quos porro libero sint eveniet incidunt dignissimos dicta. Cum impedit repellendus cupiditate soluta
                                sunt amet eaque minus id quo temporibus.</p>
                        </div>
                    </div>
                </div>
            </section>

            <!--=================== Opiniones ====================-->
            <section id="opinions" class="section opinions">
                <div class="container">
                    <div class="section__header">
                        <h2 class="section__title">Opiniones</h2>
                        <span class="section__subtitle">Lo Que Dicen Nuestros Clientes</span>
                    </div>
                    <div class="testimonial__wrapper swiper">
                        <div class="swiper-wrapper">
                            <!--========= testimonial 1 ============ -->
                            <div class="testimonial__card swiper-slide">
                                <img src="assets/img/testimonial1.jpg" alt="Natasha Smith" class="testimonial__img">
                                <div class="testimonial__content">
                                    <p class="testimonial__description">Utilicé esta página web para cambiar soles a dólares y quedé impresionado por lo rápido y fácil que fue. El proceso de conversión fue muy sencillo, y obtuve una tasa de cambio competitiva. Definitivamente la recomendaría</p>
                                    <h3 class="testimonial__name">Natasha Smith</h3>
                                </div>
                            </div>
                            <!--========= testimonial 2 ============ -->
                            <div class="testimonial__card swiper-slide">
                                <img src="assets/img/testimonial2.jpg" alt="John Clarke" class="testimonial__img">
                                <div class="testimonial__content">
                                    <p class="testimonial__description">Tenía mis dudas al principio, pero decidí probar esta página para cambiar soles a dólares. La experiencia fue muy positiva. La tasa de cambio era justa, y la transacción se completó sin problemas. Ahorré tiempo y dinero en comparación con otras opciones.</p>
                                    <h3 class="testimonial__name">John Clarke</h3>
                                </div>
                            </div>
                            <!--========= testimonial 3 ============ -->
                            <div class="testimonial__card swiper-slide">
                                <img src="assets/img/testimonial3.jpg" alt="Emily Walker" class="testimonial__img">
                                <div class="testimonial__content">
                                    <p class="testimonial__description">Me encontré con esta página web para cambiar soles a dólares y la usé en un apuro. Fue una sorpresa agradable. La conversión fue rápida, y recibí una cantidad justa de dólares en mi cuenta. Sin duda, volveré a utilizarla en el futuro.</p>
                                    <h3 class="testimonial__name">Emily Walker</h3>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-pagination"></div>
                    </div>
                </div>
            </section>
        </main>

        <!--=================== FOOTER ====================-->
        <footer id="footer" class="footer">
            <div class="container">
                <div class="d-grid footer__wrapper">
                    <div class="footer__content">
                        <h4 class="footer__title">Follow Me</h4>
                        <ul class="footer__social-list">
                            <li class="footer__social-item">
                                <a href="#" class="footer__social-link">
                                    <i class="ri-facebook-fill"></i>
                                </a>
                            </li>
                            <li class="footer__social-item">
                                <a href="#" class="footer__social-link">
                                    <i class="ri-instagram-fill"></i>
                                </a>
                            </li>
                            <li class="footer__social-item">
                                <a href="#" class="footer__social-link">
                                    <i class="ri-twitter-fill"></i>
                                </a>
                            </li>
                            <li class="footer__social-item">
                                <a href="#" class="footer__social-link">
                                    <i class="ri-github-fill"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="footer__content">
                        <h4 class="footer__title">Email Me</h4>
                        <a href="" class="footer__contact">nexuscompany@nexus.com</a>
                    </div>
                    <div class="footer__content">
                        <h4 class="footer__title">Call Me</h4>
                        <a href="" class="footer__contact">952120321</a>
                    </div>
                </div>
                <p class="footer__copyright">&copy; 2023 Nexus. All Rights Reserved</p>
            </div>
        </footer>

        <!--=================== SwiperJS  ====================-->
        <script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>

        <!--=================== ScrollReveal ==================-->
        <script src="https://unpkg.com/scrollreveal"></script>

        <!--=================== Main JS ====================-->
        <script src="assets/js/main.js"></script>

    </body>

</html>