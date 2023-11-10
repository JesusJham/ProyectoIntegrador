document.addEventListener("DOMContentLoaded", function () {
    // Botón Cancelar
    const cancelButton = document.getElementById("cancelButton");
    if (cancelButton) {
        cancelButton.addEventListener("click", function (event) {
            window.location.href = "CuentasBancarias.jsp";
        });
    }

    // Botones Currency (Compra y Venta)
    const compraBtn = document.getElementById("compraBtn");
    const ventaBtn = document.getElementById("ventaBtn");
    const envioCurrency = document.getElementById("envioCurrency");
    const recibeCurrency = document.getElementById("recibeCurrency");
    const envioMoney = document.getElementById("envioMoney");
    const recibeMoney = document.getElementById("recibeMoney");
    const envioMoneyInput = document.getElementById("envioInput");
    const recibeMoneyInput = document.getElementById("recibeInput");
    const envioMoneda = document.getElementById("envioMoneda");
    const recibeMoneda = document.getElementById("recibeMoneda");
    const cambio = document.getElementById("cambio");

    // Función para actualizar la interfaz según el modo (Compra o Venta)
    function updateCurrencyAndMoney(isCompra) {
        envioCurrency.textContent = isCompra ? "Dólares" : "Soles";
        recibeCurrency.textContent = isCompra ? "Soles" : "Dólares";
        envioMoney.textContent = isCompra ? "$" : "S/";
        recibeMoney.textContent = isCompra ? "S/" : "$";
        envioMoneda.value = isCompra ? "USD" : "PEN";
        recibeMoneda.value = isCompra ? "PEN" : "USD";
    }

    // Función para manejar errores de la solicitud a la API
    function handleApiError(error) {
        console.error('Error en la solicitud API:', error);
    }

    // Función para actualizar el DOM con la tasa de cambio
    function updateExchangeRate(moneda_one, moneda_two, envioValue, recibeValue, updatingInput) {
        fetch(`https://api.exchangerate-api.com/v4/latest/${moneda_one}`)
                .then((response) => {
                    if (!response.ok) {
                        throw new Error('No se puede acceder a la API. Código de estado: ' + response.status);
                    }
                    return response.json();
                })
                .then((data) => {
                    const taza = data.rates[moneda_two];

                    if (updatingInput === 'envio') {
                        recibeMoneyInput.value = (envioValue * taza).toFixed(2);
                    } else if (updatingInput === 'recibe') {
                        envioMoneyInput.value = (recibeValue / taza).toFixed(2);
                    }

                    // Mostrar el valor de los cambios en la consola
                    console.log(`1 ${moneda_one} = ${taza} ${moneda_two}`);
                    console.log(data);
                })
                .catch(handleApiError);
    }
    
    function updateExchangeRate2(moneda_one, moneda_two, envioValue, recibeValue) {
        fetch(`https://api.exchangerate-api.com/v4/latest/${moneda_one}`)
                .then((response) => {
                    if (!response.ok) {
                        throw new Error('No se puede acceder a la API. Código de estado: ' + response.status);
                    }
                    return response.json();
                })
                .then((data) => {
                    const taza = data.rates[moneda_two];
                    recibeMoneyInput.value = (envioValue * taza).toFixed(2);

                    // Mostrar el valor de los cambios en la consola
                    console.log(`1 ${moneda_one} = ${taza} ${moneda_two}`);
                    console.log(data);
                })
                .catch(handleApiError);
    }

    /*******************************CALCULAR******************************************/
// Modifica la función calculate para manejar el cálculo de envioInput y recibeInput
    function calculate2(envioValue, recibeValue) {
        const moneda_one = envioMoneda.value;
        const moneda_two = recibeMoneda.value;
        updateExchangeRate2(moneda_one, moneda_two, envioValue, recibeValue);
    }
    

// Modifica la función calculate para manejar el cálculo de envioInput y recibeInput
    function calculate(envioValue, recibeValue, updatingInput) {
        const moneda_one = envioMoneda.value;
        const moneda_two = recibeMoneda.value;
        updateExchangeRate(moneda_one, moneda_two, envioValue, recibeValue, updatingInput);
    }

// Agrega event listeners a envioInput y recibeInput
    envioMoneyInput.addEventListener("input", function () {
        calculate(envioMoneyInput.value, recibeMoneyInput.value, 'envio');
    });

    recibeMoneyInput.addEventListener("input", function () {
        calculate(envioMoneyInput.value, recibeMoneyInput.value, 'recibe');
    });


    /***************************************************************************************/

    // Inicialmente, estamos en el modo de compra
    let esCompra = true;

    // Función para cambiar entre Compra y Venta
    function toggleCompraVenta() {
        esCompra = !esCompra; // Cambiar el valor de la variable entre true y false
        updateCurrencyAndMoney(esCompra); // Llamar a la función para actualizar la interfaz
        if (esCompra) {
            compraBtn.classList.add("selected");
            ventaBtn.classList.remove("selected");
        } else {
            ventaBtn.classList.add("selected");
            compraBtn.classList.remove("selected");
        }
        // Llamar a la función calculate con los valores actuales de envioMoneyInput y recibeMoneyInput
        calculate2(envioMoneyInput.value, recibeMoneyInput.value);
        cambio.textContent = esCompra ? "Cambiar a Venta" : "Cambiar a Compra";
    }

    cambio.addEventListener('click', toggleCompraVenta);

    compraBtn.addEventListener("click", () => {
        if (!esCompra) {
            toggleCompraVenta();
        }
        compraBtn.classList.add("selected");
        ventaBtn.classList.remove("selected");
        updateCurrencyAndMoney(true);
    });

    ventaBtn.addEventListener("click", () => {
        if (esCompra) {
            toggleCompraVenta();
        }
        ventaBtn.classList.add("selected");
        compraBtn.classList.remove("selected");
        updateCurrencyAndMoney(false);
    });

    // Formulario Operacion
    const datosAdicionalesForm = document.getElementById("datosAdicionalesForm");
    const operacionTipoInput = document.getElementById("operacionTipo");
    const montoEnviadoInput = document.getElementById("montoEnviado");
    const montoRecibidoInput = document.getElementById("montoRecibido");

    datosAdicionalesForm.addEventListener("submit", (event) => {
        event.preventDefault();

        operacionTipoInput.value = compraBtn.classList.contains("selected") ? "compra" : "venta";
        montoEnviadoInput.value = envioMoneyInput.value;
        montoRecibidoInput.value = recibeMoneyInput.value;

        datosAdicionalesForm.submit();
    });
});

document.addEventListener("DOMContentLoaded", function () {
    // Elementos relacionados con las tarjetas y cuentas
    const tarjetaSelect = document.getElementById('tarjeta');
    const cuentaSelect = document.getElementById('cuenta');
    const titularSpan = document.getElementById('titular');
    const bancoSpan = document.getElementById('banco');

    // Función para actualizar el nombre del titular o banco
    function actualizarNombreElemento(select, span) {
        if (select) {
            select.addEventListener('change', function () {
                const selectedOption = select.options[select.selectedIndex];
                const nombre = selectedOption.getAttribute('data-titular');
                span.textContent = nombre;
            });
        }
    }

    actualizarNombreElemento(tarjetaSelect, titularSpan);
    actualizarNombreElemento(cuentaSelect, bancoSpan);
});
