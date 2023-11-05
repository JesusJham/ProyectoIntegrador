
package Modelo;

import java.util.Date;
import java.util.List;

public class Usuario extends Persona {

    private int IdUsuario;
    private Date fechaRegistro;
    private String celular;
    private String email;
    private String password;
    private List<Sesion> sesiones;
    private List<CuentaBancarias> cuentaBancarias;
    private List<Transferencias> transferencias;
    private List<Opiniones> opiniones;

    public Usuario() {
        super();
    }

    public Usuario(int IdUsuario, String nombre, String apellido, Date fechaNacimiento,  Date fechaRegistro, String celular, String email, String password) {
        super(nombre, apellido, fechaNacimiento);
        this.IdUsuario = IdUsuario;
        this.fechaRegistro = fechaRegistro;
        this.celular = celular;
        this.email = email;
        this.password = password;
    }

    public Usuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }



    

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Sesion> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<Sesion> sesiones) {
        this.sesiones = sesiones;
    }

    public List<CuentaBancarias> getCuentaBancarias() {
        return cuentaBancarias;
    }

    public void setCuentaBancarias(List<CuentaBancarias> cuentaBancarias) {
        this.cuentaBancarias = cuentaBancarias;
    }

    public List<Transferencias> getTransferencias() {
        return transferencias;
    }

    public void setTransferencias(List<Transferencias> transferencias) {
        this.transferencias = transferencias;
    }

    public List<Opiniones> getOpiniones() {
        return opiniones;
    }

    public void setOpiniones(List<Opiniones> opiniones) {
        this.opiniones = opiniones;
    }
    
    

}
