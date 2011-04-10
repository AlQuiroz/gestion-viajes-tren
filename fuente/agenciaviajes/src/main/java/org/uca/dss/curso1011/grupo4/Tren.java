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
    public Tren(int asientos, float costetramo, String tipo, String nombre){
        this.numasiento = asientos;
        this.costetramo = costetramo;
        this.tipo = tipo;
        this.nombre = nombre;
    };
        /**
     * @return the _numasiento
     */
    public int getNumasiento() {
        return numasiento;
    }

    /**
     * @return the _costetramo
     */
    public float getCostetramo() {
        return costetramo;
    }

    /**
     * @return the _tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @return the _nombre
     */
    public String getNombre() {
        return nombre;
    }

    private

    int numasiento;
    private float costetramo;
    private String tipo;
    private String nombre;

}
