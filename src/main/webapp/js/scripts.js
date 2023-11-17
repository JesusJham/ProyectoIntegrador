// scripts.js
$(document).ready(function () {
    $("#OperacionForm").submit(function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: $(this).attr("action"),
            data: $(this).serialize(),
            success: function (response) {
                console.log(response);
                // Mostrar notificación con SweetAlert
                Swal.fire({
                    icon: 'success',
                    title: '¡Éxito!',
                    text: 'Se ha enviado una confirmación a tu correo electrónico. Por favor, verifica tu bandeja de entrada.'
                }).then((result) => {
                    // Redirigir a espera.jsp después de mostrar la notificación
                    window.location.href = "Inicio.jsp";
                });
            },
            error: function (error) {
                console.error(error);
            }
        });
    });
});
