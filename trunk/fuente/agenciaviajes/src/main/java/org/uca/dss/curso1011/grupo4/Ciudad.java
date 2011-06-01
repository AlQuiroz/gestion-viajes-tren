/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;

/**
 * Representa la ciudad origen o destino del viaje.
 *
 * Clase encargada de representar una ciudad en el sistema de viajes de tren, identificándola en el mismo mediante su nombre. También guarda el país al que pertenece.
 *
 * @author migue
 */
public class Ciudad {
    //Constructor
    /**
     * Constructor
     *
     * Construye un objeto de la clase Ciudad
     * @param nombre nombre de la ciudad
     * @param pais nombre del pa�s en el que esta la ciudad
     */
    public Ciudad(String nombre, String pais){
        this.nombre = nombre;
        this.pais = pais;
    }
    /**
     * Conversor de String a Ciudad
     *
     * Construye un objeto de la clase Ciudad a partir de un string
     * @param nombre nombre de la ciudad
     */
    public Ciudad(String nombre){
        this.nombre = nombre;
        this.pais = "España";
    }
    /**
     * Constructor copia
     *
     * Construye un objeto de la clase Ciudad a partir de
     * @param valor
     * @param pais nombre del país en el que esta la ciudad
     */
    public Ciudad(Ciudad valor){
        this.nombre=valor.getNombre();
        this.pais=valor.getPais();
    }
/**
 * Método observador
 *
 * Devuelve el nombre de la ciudad.
 *
 * @return nombre de la ciudad
 */

    public String getNombre() {
        return nombre;
    }

/**
 * Método observador
 *
 * Devuelve el país de la ciudad
 * @return País de la ciudad
 */
    public String getPais() {
        return pais;
    }

    private
    String nombre;
    String pais;
}
