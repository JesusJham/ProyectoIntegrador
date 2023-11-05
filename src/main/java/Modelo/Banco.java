/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;

public class Banco {
    
    private int idBanco;
    private String nombreBanco;
    private List<CuentaBancarias> cuentaBancaria;
    private List<Transferencias> transferencias;
    private List<CuentaEmpresa> cuentaEmpresa;

    public Banco() {
    }

    public Banco(int idBanco, String nombreBanco) {
        this.idBanco = idBanco;
        this.nombreBanco = nombreBanco;
    }
   
    public Banco(int idBanco){
        this.idBanco = idBanco;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    
}
