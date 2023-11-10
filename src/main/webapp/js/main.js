document.addEventListener("DOMContentLoaded", function () {
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

    function updateExchangeRateCommon(moneda_one, moneda_two, envioValue, recibeValue, callback) {
        fetch(`https://api.exchangerate-api.com/v4/latest/${moneda_one}`)
                .then(response => {
                    if (!response.ok)
                        throw new Error('No se puede acceder a la API. Código de estado: ' + response.status);
                    return response.json();
                })
                .then(data => {
                    const taza = data.rates[moneda_two];
                    callback(taza, envioValue, recibeValue);
                    console.log(`1 ${moneda_one} = ${taza} ${moneda_two}`);
                    console.log(data);
                })
                .catch(handleApiError);
    }

    function updateExchangeRate(moneda_one, moneda_two, envioValue, recibeValue, updatingInput) {
        updateExchangeRateCommon(moneda_one, moneda_two, envioValue, recibeValue, (taza, envioValue, recibeValue) => {
            if (updatingInput === 'envio') {
                recibeMoneyInput.value = (envioValue * taza).toFixed(2);
            } else if (updatingInput === 'recibe') {
                envioMoneyInput.value = (recibeValue / taza).toFixed(2);
            }
        });
    }

    function updateExchangeRate2(moneda_one, moneda_two, envioValue, recibeValue) {
        updateExchangeRateCommon(moneda_one, moneda_two, envioValue, recibeValue, (taza, envioValue) => {
            recibeMoneyInput.value = (envioValue * taza).toFixed(2);
        });
    }

    function calculate(envioValue, recibeValue, updatingInput) {
        const moneda_one = envioMoneda.value;
        const moneda_two = recibeMoneda.value;
        updateExchangeRate(moneda_one, moneda_two, envioValue, recibeValue, updatingInput);
    }

    function calculate2(envioValue, recibeValue) {
        const moneda_one = envioMoneda.value;
        const moneda_two = recibeMoneda.value;
        updateExchangeRate2(moneda_one, moneda_two, envioValue, recibeValue);
    }

    envioMoneyInput.addEventListener("input", () => calculate(envioMoneyInput.value, recibeMoneyInput.value, 'envio'));
    recibeMoneyInput.addEventListener("input", () => calculate(envioMoneyInput.value, recibeMoneyInput.value, 'recibe'));

    let esCompra = true;

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