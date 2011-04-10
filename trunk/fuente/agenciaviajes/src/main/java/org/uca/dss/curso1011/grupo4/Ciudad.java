/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;

/**
 * Clase que representa las Ciudades del sistema.
 *
 * Son las ciudades a las cuales un tren tiene estaci�n y para en ella.
 *
 * @author migue
 */
public class Ciudad {
    
    public Ciudad(String nombre, String provincia, String pais){
        this._nombre = nombre;
        this._provincia = provincia;
        this._pais = pais;
    };
/**
 * M�todo observador
 *
 * Devuelve el nombre de la ciudad.
 * @return nombre de la ciudad
 */

    public String getNombre() {
        return _nombre;
    }
/**
 * M�todo observador
 *
 * Devuelve la provincia de la ciudad
 * @return provincia
 */
    public String getProvincia() {
        return _provincia;
    }
/**
 * M�todo observador
 *
 * Devuelve el pa�s de la ciudad
 * @return Pa�s de la ciudad
 */
    public String getPais() {
        return _pais;
    }

    private
    String _nombre;
    String _provincia;
    String _pais;
}
