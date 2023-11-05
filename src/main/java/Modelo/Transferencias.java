
package Modelo;

import java.util.Date;

public class Transferencias {
    
    private int idTransferencia;
    private Usuario usuario;
    private Tarjeta tarjeta;
    private CuentaBancarias cuentaBancarias;
    private int codigoOper;
    private String tipoOperacion;
    private float montoEnviado;
    private float montoRecibido;
    private float tipoCambio;
    private String estado;
    private Date fechaHora;

    public Transferencias() {
    }

    public Transferencias(int idTransferencia, Usuario usuario, Tarjeta tarjeta, CuentaBancarias cuentaBancarias, int codigoOper, String tipoOperacion, float montoEnviado, float montoRecibido, float tipoCambio, String estado, Date fechaHora) {
        this.idTransferencia = idTransferencia;
        this.usuario = usuario;
        this.tarjeta = tarjeta;
        this.cuentaBancarias = cuentaBancarias;
        this.codigoOper = codigoOper;
        this.tipoOperacion = tipoOperacion;
        this.montoEnviado = montoEnviado;
        this.montoRecibido = montoRecibido;
        this.tipoCambio = tipoCambio;
        this.estado = estado;
        this.fechaHora = fechaHora;
    }

    public int getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public CuentaBancarias getCuentaBancarias() {
        return cuentaBancarias;
    }

    public void setCuentaBancarias(CuentaBancarias cuentaBancarias) {
        this.cuentaBancarias = cuentaBancarias;
    }

    public int getCodigoOper() {
        return codigoOper;
    }

    public void setCodigoOper(int codigoOper) {
        this.codigoOper = codigoOper;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public float getMontoEnviado() {
        return montoEnviado;
    }

    public void setMontoEnviado(float montoEnviado) {
        this.montoEnviado = montoEnviado;
    }

    public float getMontoRecibido() {
        return montoRecibido;
    }

    public void setMontoRecibido(float montoRecibido) {
        this.montoRecibido = montoRecibido;
    }

    public float getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(float tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    

    
    


}
