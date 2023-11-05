package Modelo;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

public class Sesion {

    private int idSesion;
    private Usuario usuario;
    private String token;
    private Date fechaExpiracion;

    public Sesion() {
    }

    public Sesion(int idSesion, Usuario usuario, String token, Date fechaExpiracion) {
        this.idSesion = idSesion;
        this.usuario = usuario;
        this.token = token;
        this.fechaExpiracion = fechaExpiracion;
    }

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }


}
