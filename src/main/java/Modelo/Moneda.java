
package Modelo;

public class Moneda {
    
    private int idMoneda;
    private String tipoMoneda;

    public Moneda() {
    }
    
    public Moneda(int idMoneda){
        this.idMoneda = idMoneda;
    }

    public Moneda(int idMoneda, String tipoMoneda) {
        this.idMoneda = idMoneda;
        this.tipoMoneda = tipoMoneda;
    }

    public int getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(int idMoneda) {
        this.idMoneda = idMoneda;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }
    
    
    
}
