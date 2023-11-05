/*========================FUNCTION BUTTON CANCELAR=======================*/
// Asegúrate de que el código se ejecute después de que el DOM esté cargado
document.addEventListener("DOMContentLoaded", function () {
    // Verificar si el elemento "cancelButton" existe en la página
    var cancelButton = document.getElementById("cancelButton");
    if (cancelButton) {
        // Asignar el evento click solo si el elemento existe
        cancelButton.addEventListener("click", function (event) {
            window.location.href = "CuentasBancarias.jsp"; // Redirigir a la página JSP deseada
        });
    }
});


/*======================BOTON CURRENCY==================================================*/
// Obtén los botones por su ID
document.addEventListener("DOMContentLoaded", function () {
    // Obtén los botones por su ID
    const compraBtn = document.getElementById("compraBtn");
    const ventaBtn = document.getElementById("ventaBtn");

    // Agrega un event listener para el botón de compra
    compraBtn.addEventListener("click", () => {
        // Quita la clase "selected" de todos los botones
        compraBtn.classList.add("selected");
        ventaBtn.classList.remove("selected");

    });

    // Agrega un event listener para el botón de venta
    ventaBtn.addEventListener("click", () => {
        // Quita la clase "selected" de todos los botones
        ventaBtn.classList.add("selected");
        compraBtn.classList.remove("selected");

    });
});

/*=========================CAMBIO DE POSICION SOLES Y DOLARES==================================*/
document.addEventListener("DOMContentLoaded", function () {
    const compraBtn = document.getElementById("compraBtn");
    const ventaBtn = document.getElementById("ventaBtn");
    const envioCurrency = document.getElementById("envioCurrency");
    const recibeCurrency = document.getElementById("recibeCurrency");
    const envioMoney = document.getElementById("envioMoney");
    const recibeMoney = document.getElementById("recibeMoney");
    const envioMoneyInput = document.getElementById("envioInput");
    const recibeMoneyInput = document.getElementById("recibeInput");

    compraBtn.addEventListener("click", () => {
        envioCurrency.textContent = "Dólares";
        recibeCurrency.textContent = "Soles";
        envioMoney.textContent = "$";
        recibeMoney.textContent = "S/";
    });

    ventaBtn.addEventListener("click", () => {
        envioCurrency.textContent = "Soles";
        recibeCurrency.textContent = "Dólares";
        envioMoney.textContent = "S/";
        recibeMoney.textContent = "$";
    });

    // Agrega un event listener para el botón de compra
    compraBtn.addEventListener("click", () => {
        // Asigna el valor inicial al input de envío (en dólares)
        const valorInicialDolares = parseFloat(envioMoneyInput.value);
        envioMoneyInput.value = valorInicialDolares.toFixed(2); // Redondea a 2 decimales

        // Calcula y muestra el resultado en el segundo input (en soles)
        recibeMoneyInput.value = (valorInicialDolares * 3.6).toFixed(2); // Redondea a 2 decimales
    });

    // Agrega un event listener para el botón de venta
    ventaBtn.addEventListener("click", () => {
        // Asigna el valor inicial al input de envío (en soles)
        const valorInicialSoles = parseFloat(envioMoneyInput.value);
        envioMoneyInput.value = valorInicialSoles.toFixed(2); // Redondea a 2 decimales

        // Calcula y muestra el resultado en el segundo input (en dólares)
        recibeMoneyInput.value = (valorInicialSoles / 3.6).toFixed(2); // Redondea a 2 decimales
    });
});


/**===================FORMULARIO OPERACION============================*/
document.addEventListener("DOMContentLoaded", function () {
    const datosAdicionalesForm = document.getElementById("datosAdicionalesForm");
    const operacionTipoInput = document.getElementById("operacionTipo");
    const montoEnviadoInput = document.getElementById("montoEnviado");
    const montoRecibidoInput = document.getElementById("montoRecibido");

    datosAdicionalesForm.addEventListener("submit", (event) => {
        // Evitar que el formulario se envíe automáticamente
        event.preventDefault();

        // Configurar los datos según lo seleccionado en "compra" o "venta"
        const compraBtn = document.getElementById("compraBtn");
        const ventaBtn = document.getElementById("ventaBtn");

        if (compraBtn.classList.contains("selected")) {
            operacionTipoInput.value = "compra";
            montoEnviadoInput.value = document.getElementById("envioInput").value;
            montoRecibidoInput.value = document.getElementById("recibeInput").value;
        } else if (ventaBtn.classList.contains("selected")) {
            operacionTipoInput.value = "venta";
            montoEnviadoInput.value = document.getElementById("envioInput").value;
            montoRecibidoInput.value = document.getElementById("recibeInput").value;
        }

        // Enviar el formulario de datos adicionales
        datosAdicionalesForm.submit();
    });
});

/*===============OBTENER EL NOMBRE DEL TITULAR y BANCO=============================================================*/

document.addEventListener("DOMContentLoaded", function () {
    const tarjetaSelect = document.getElementById('tarjeta');
    const cuentaSelect = document.getElementById('cuenta');
    const titularSpan = document.getElementById('titular');
    const bancoSpan = document.getElementById('banco');

    // Verifica si el elemento 'tarjetaSelect' se encuentra en la página
    if (tarjetaSelect) {
        tarjetaSelect.addEventListener('change', function () {
            // Obtén el nombre del titular desde el atributo data de la opción seleccionada
            const selectedOption = tarjetaSelect.options[tarjetaSelect.selectedIndex];
            const nombreTitular = selectedOption.getAttribute('data-titular');
            // Muestra el nombre del titular en el elemento HTML
            titularSpan.textContent = nombreTitular;
        });
    }
});

document.addEventListener("DOMContentLoaded", function () {
    const cuentaSelect = document.getElementById('cuenta');
    const bancoSpan = document.getElementById('banco');

    // Verifica si el elemento 'tarjetaSelect' se encuentra en la página
    if (cuentaSelect) {
        cuentaSelect.addEventListener('change', function () {
            // Obtén el nombre del titular desde el atributo data de la opción seleccionada
            const selectedOption = cuentaSelect.options[cuentaSelect.selectedIndex];
            const nombreBanco = selectedOption.getAttribute('data-titular');
            // Muestra el nombre del titular en el elemento HTML
            bancoSpan.textContent = nombreBanco;
        });
    }
});




