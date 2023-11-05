/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

class CuentaEmpresa {
    
    private int idCuentaE;
    private Banco banco;
    private Moneda moneda;
    private String titular;
    private String RUC;

    public CuentaEmpresa() {
    }

    public CuentaEmpresa(int idCuentaE, Banco banco, Moneda moneda, String titular, String RUC) {
        this.idCuentaE = idCuentaE;
        this.banco = banco;
        this.moneda = moneda;
        this.titular = titular;
        this.RUC = RUC;
    }

    public int getIdCuentaE() {
        return idCuentaE;
    }

    public void setIdCuentaE(int idCuentaE) {
        this.idCuentaE = idCuentaE;
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

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }
    
    
}
