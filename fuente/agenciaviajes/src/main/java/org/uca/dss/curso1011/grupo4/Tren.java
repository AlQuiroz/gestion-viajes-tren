/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;

import org.uca.dss.trenes.interfazExtendido.InterfazVehiculo;
/**
 * Representa al tren que realiza el viaje.
 *
 * Clase encargada de representar al tren que realizará el viaje determinado en el sistema. En ella se describen aspectos fundamentales del mismo, como puede ser su nombre, que lo identificará, su número de asientos, el tipo o el coste por tramo viajado. Implementa a la interfaz 'InterfazVehiculo'
 *
 * @author migue
 */
public class Tren implements InterfazVehiculo{
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
     
    public int getNumAsientos() {
        return numAsientos;
    }

    
    public float getCosteTramo() {
        return costeTramo;
    }


    
    public String getNombre() {
        return nombre;
    }

    private int numAsientos;
    private float costeTramo;
    private String nombre;

}
