
package Modelo;

import java.util.Date;

public class HistorialEstadoTransferencia {
    
    private int idHistorial;
    private Transferencias transferencia;
    private String estadoTransferencia;
    private Date fechaHoraCambio;

    public HistorialEstadoTransferencia() {
    }

    public HistorialEstadoTransferencia(int idHistorial, Transferencias transferencia, String estadoTransferencia, Date fechaHoraCambio) {
        this.idHistorial = idHistorial;
        this.transferencia = transferencia;
        this.estadoTransferencia = estadoTransferencia;
        this.fechaHoraCambio = fechaHoraCambio;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Transferencias getTransferencia() {
        return transferencia;
    }

    public void setTransferencia(Transferencias transferencia) {
        this.transferencia = transferencia;
    }

    public String getEstadoTransferencia() {
        return estadoTransferencia;
    }

    public void setEstadoTransferencia(String estadoTransferencia) {
        this.estadoTransferencia = estadoTransferencia;
    }

    public Date getFechaHoraCambio() {
        return fechaHoraCambio;
    }

    public void setFechaHoraCambio(Date fechaHoraCambio) {
        this.fechaHoraCambio = fechaHoraCambio;
    }
    
    
    
}
