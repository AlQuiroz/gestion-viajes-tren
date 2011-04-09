/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;

/**
 * Clase que representa las Ciudades del sistema.
 *
 * Son las ciudades a las cuales un tren tiene estación y para en ella.
 *
 * @author migue
 */
public class Ciudad {
    
    public Ciudad(String nombre, String provincia, String pais){
        this._nombre = nombre;
        this._provincia = provincia;
        this._pais = pais;
    };


    public String getNombre() {
        return _nombre;
    }

    public String getProvincia() {
        return _provincia;
    }

    public String getPais() {
        return _pais;
    }

    private
    String _nombre;
    String _provincia;
    String _pais;
}
