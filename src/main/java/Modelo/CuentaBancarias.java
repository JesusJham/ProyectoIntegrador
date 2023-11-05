package Modelo;

public class CuentaBancarias {

    private int idCuentaB;
    private Usuario usuario;
    private Banco banco;
    private Moneda moneda;
    private String aliasBanco;
    private String numeroCuenta;

    public CuentaBancarias() {
    }

    public CuentaBancarias(int idCuentaB, Usuario usuario, Banco banco, Moneda moneda, String aliasBanco, String numeroCuenta) {
        this.idCuentaB = idCuentaB;
        this.usuario = usuario;
        this.banco = banco;
        this.moneda = moneda;
        this.aliasBanco = aliasBanco;
        this.numeroCuenta = numeroCuenta;
    }

    public CuentaBancarias(int idCuentaB) {
        this.idCuentaB = idCuentaB;
    }

    public int getIdCuentaB() {
        return idCuentaB;
    }

    public void setIdCuentaB(int idCuentaB) {
        this.idCuentaB = idCuentaB;
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

    public String getAliasBanco() {
        return aliasBanco;
    }

    public void setAliasBanco(String aliasBanco) {
        this.aliasBanco = aliasBanco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    
    
    
}
