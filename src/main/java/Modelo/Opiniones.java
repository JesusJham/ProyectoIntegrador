/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;


public class Opiniones {
    
    private int IdOpinion;
    private Usuario usuario;
    private int puntuacion;
    private String comentario;
    private Date fechaPublicacion;

    public Opiniones() {
    }

    public Opiniones(int IdOpinion, Usuario usuario, int puntuacion, String comentario, Date fechaPublicacion) {
        this.IdOpinion = IdOpinion;
        this.usuario = usuario;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getIdOpinion() {
        return IdOpinion;
    }

    public void setIdOpinion(int IdOpinion) {
        this.IdOpinion = IdOpinion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    
    
    
    
}
