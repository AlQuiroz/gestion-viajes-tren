/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;

/**
 * Clase que representa los trenes del sistema.
 *
 * Son los trenes que realizaran los viajes de nuestra aplicación.
 *
 * @author migue
 */
public class Tren {
    public Tren(int asientos, float costetramo, String tipo, String nombre){
        this._numasiento = asientos;
        this._costetramo = costetramo;
        this._tipo = tipo;
        this._nombre = nombre;
    };
        /**
     * @return the _numasiento
     */
    public int getNumasiento() {
        return _numasiento;
    }

    /**
     * @return the _costetramo
     */
    public float getCostetramo() {
        return _costetramo;
    }

    /**
     * @return the _tipo
     */
    public String getTipo() {
        return _tipo;
    }

    /**
     * @return the _nombre
     */
    public String getNombre() {
        return _nombre;
    }

    private

    int _numasiento;
    private float _costetramo;
    private String _tipo;
    private String _nombre;

}
