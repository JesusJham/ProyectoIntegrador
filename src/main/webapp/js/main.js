document.addEventListener("DOMContentLoaded", function () {

        updateExchangeRateCommon(0, 0, (taza, envioValue, recibeValue) => {}); // Llamada a la función para mostrar las tasas de cambio

    const cancelButton = document.getElementById("cancelButton");
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
    let esCompra = true; // Declaración global, no la repitas

    function updateCurrencyAndMoney(isCompra) {
        envioCurrency.textContent = isCompra ? "Dólares" : "Soles";
        recibeCurrency.textContent = isCompra ? "Soles" : "Dólares";
        envioMoney.textContent = isCompra ? "$" : "S/";
        recibeMoney.textContent = isCompra ? "S/" : "$";
        envioMoneda.value = isCompra ? "USD" : "PEN";
        recibeMoneda.value = isCompra ? "PEN" : "USD";
    }

    function handleApiError(error) {
        console.error('Error en la solicitud API:', error);
    }

    function updateExchangeRateCommon(envioValue, recibeValue, callback) {
        fetch('https://api.exchangerate-api.com/v4/latest/USD')
                .then(response => {
                    if (!response.ok)
                        throw new Error('No se puede acceder a la API. Código de estado: ' + response.status);
                    return response.json();
                })
                .then(data => {
                    let tazaCompra = data.rates.PEN; // Obtenemos el tipo de cambio de USD a PEN para compra
                    let tazaVenta = data.rates.PEN; // Obtenemos el tipo de cambio de USD a PEN para venta
                    let uptadeTazaVenta = data.rates.PEN;
                    let tazaCompraGlobal = data.rates.PEN;
                    let tazaVentaGlobal = data.rates.PEN;
                        
                        tazaVenta = 1 / (tazaVenta * 1.003); // Si es 'venta', invertimos la taza
                        uptadeTazaVenta = 1 / tazaVenta;
                        
                        tazaCompra = tazaCompra - (tazaCompra * 0.003);

                    if (!esCompra) {
                        console.log(uptadeTazaVenta);
                        document.getElementById('taza').value = uptadeTazaVenta.toFixed(3); // Asignamos el valor de tazaPEN para venta
                    } else {
                        document.getElementById('taza').value = tazaCompra.toFixed(3); // Asignamos el valor de tazaPEN para compra
                    }
                    callback(esCompra ? tazaCompra.toFixed(3) : tazaVenta.toFixed(3), envioValue, recibeValue);
                    document.getElementById('compraBtn').innerText = `Compra (Tasa: ${tazaCompra.toFixed(3)})`;
                    document.getElementById('ventaBtn').innerText = `Venta (Tasa: ${uptadeTazaVenta.toFixed(3)})`;
                    console.log(`1 USD = ${esCompra ? tazaCompra : tazaVenta} PEN`);
                })
                .catch(handleApiError);
    }


    function updateExchangeRate(moneda_one, moneda_two, envioValue, recibeValue, updatingInput) {
        updateExchangeRateCommon(envioValue, recibeValue, (taza, envioValue, recibeValue) => {
            if (updatingInput === 'envio') {
                recibeMoneyInput.value = (envioValue * taza).toFixed(2);
            } else if (updatingInput === 'recibe') {
                envioMoneyInput.value = (recibeValue / taza).toFixed(2);
            }
        });
    }

    function updateExchangeRate2(moneda_one, moneda_two, envioValue, recibeValue) {
        updateExchangeRateCommon(envioValue, recibeValue, (taza, envioValue) => {
            recibeMoneyInput.value = (envioValue * taza).toFixed(2);
        });
    }

    function calculate(envioValue, recibeValue, updatingInput) {
        updateExchangeRate(envioMoneda.value, recibeMoneda.value, envioValue, recibeValue, updatingInput);
    }

    function calculate2(envioValue, recibeValue) {
        updateExchangeRate2(envioMoneda.value, recibeMoneda.value, envioValue, recibeValue);
    }

    envioMoneyInput.addEventListener("input", () => calculate(envioMoneyInput.value, recibeMoneyInput.value, 'envio'));
    recibeMoneyInput.addEventListener("input", () => calculate(envioMoneyInput.value, recibeMoneyInput.value, 'recibe'));


    function toggleCompraVenta() {
        esCompra = !esCompra;
        updateCurrencyAndMoney(esCompra);
        compraBtn.classList.toggle("selected", esCompra);
        ventaBtn.classList.toggle("selected", !esCompra);
        calculate2(envioMoneyInput.value, recibeMoneyInput.value);
        cambio.textContent = esCompra ? "Cambiar a Venta" : "Cambiar a Compra";
    }

    cambio.addEventListener('click', toggleCompraVenta);

    compraBtn.addEventListener("click", () => {
        if (!esCompra)
            toggleCompraVenta();
        compraBtn.classList.add("selected");
        ventaBtn.classList.remove("selected");
        updateCurrencyAndMoney(true);
    });

    ventaBtn.addEventListener("click", () => {
        if (esCompra)
            toggleCompraVenta();
        ventaBtn.classList.add("selected");
        compraBtn.classList.remove("selected");
        updateCurrencyAndMoney(false);
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