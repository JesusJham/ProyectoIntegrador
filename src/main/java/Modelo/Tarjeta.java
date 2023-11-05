
package Modelo;

public class Tarjeta {
    
    private int idTarjeta;
    private Usuario usuario;
    private Banco banco;
    private Moneda moneda;
    private String numeroTarjeta;
    private String cvv;
    private String fechaVencimiento;
    private String nombreTitular;

  
    public Tarjeta() {
    }

    public Tarjeta(int idTarjeta, Usuario usuario, Banco banco, Moneda moneda, String numeroTarjeta, String cvv, String fechaVencimiento, String nombreTitular) {
        this.idTarjeta = idTarjeta;
        this.usuario = usuario;
        this.banco = banco;
        this.moneda = moneda;
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
        this.fechaVencimiento = fechaVencimiento;
        this.nombreTitular = nombreTitular;
    }

    public Tarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    
    
}
