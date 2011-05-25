/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;

/**
 * Representa al tren que realiza el viaje.
 *
 * Clase encargada de representar al tren que realizará el viaje determinado en el sistema. En ella se describen aspectos fundamentales del mismo, como puede ser su nombre, que lo identificar�, su n�mero de asientos, el tipo o el coste por tramo viajado.
 *
 * @author migue
 */
public class Tren {
    /**
     * Constructor de asiento.
     *
     * Crea un tren a partir de asientos, coste del tramo y el nombre del tren)
     *
     * @param asientos
     * @param costetramo
     * @param nombre
     */
    public Tren(int asientos, float costetramo, String nombre){
        this.numAsientos = asientos;
        this.costeTramo = costetramo;
        this.nombre = nombre;
    }

    /**
     * Constructor de copia
     *
     * Construye un tren a partir de otro objeto Tren
     * @param tren a copiar
     */
    public Tren(Tren tren){
        this.numAsientos= tren.getNumAsientos();
        this.costeTramo= tren.getCosteTramo();
        this.nombre= tren.getNombre();
     }
     /**
     * Método consultor del número de asiento
     *
     * @return Duelve el valor del numasiento
     */
    public int getNumAsientos() {
        return numAsientos;
    }

    /**
     * Método consultor de coste del tramo.
     * Devuelve el coste del tramo que realiza el tren
     *
     * @return Devuelve el valor de costeramo
     */
    public float getCosteTramo() {
        return costeTramo;
    }


    /**
     * Método Consultor del nombre del tren.
     *
     * Devuelve el nombre que tiene el tren.
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    private int numAsientos;
    private float costeTramo;
    private String nombre;

}
