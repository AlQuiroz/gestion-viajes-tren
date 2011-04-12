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
    
    public Ciudad(String nombre, String pais){
        this.nombre = nombre;
        this.pais = pais;
    }
    public Ciudad(String nombre){
        this.nombre = nombre;
        this.pais = "España";
    }

    public Ciudad(Ciudad valor){
        this.nombre=valor.getNombre();
        this.pais=valor.getPais();
    }
/**
 * M�todo observador
 *
 * Devuelve el nombre de la ciudad.
 * @return nombre de la ciudad
 */

    public String getNombre() {
        return nombre;
    }

/**
 * M�todo observador
 *
 * Devuelve el pa�s de la ciudad
 * @return Pa�s de la ciudad
 */
    public String getPais() {
        return pais;
    }

    private
    String nombre;
    String pais;
}
