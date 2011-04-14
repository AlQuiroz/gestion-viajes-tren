/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;

/**
 * Clase que representa los trenes del sistema.
 *
 * Son los trenes que realizaran los viajes de nuestra aplicaci�n.
 *
 * @author migue
 */
public class Tren {
    public Tren(int asientos, float costetramo, String nombre){
        this.numAsientos = asientos;
        this.costeTramo = costetramo;
        this.nombre = nombre;
    }

    public Tren(Tren valor){
        this.numAsientos= valor.getNumAsientos();
        this.costeTramo= valor.getCosteTramo();
        this.nombre= valor.getNombre();
    }
        /**
     * @return the _numasiento
     */
    public int getNumAsientos() {
        return numAsientos;
    }

    /**
     * @return the _costetramo
     */
    public float getCosteTramo() {
        return costeTramo;
    }


    /**
     * @return the _nombre
     */
    public String getNombre() {
        return nombre;
    }

    private int numAsientos;
    private float costeTramo;
    private String nombre;

}
