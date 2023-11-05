
package Interfaces;

import Modelo.Sesion;

public interface SesionDAO {
    
    boolean crearSesion (Sesion sesion);
    Sesion obtenerSesionPorToken(String token);
    boolean actualizarSesion(Sesion sesion);
    boolean eliminarSesion(int idSesion);
    
}
