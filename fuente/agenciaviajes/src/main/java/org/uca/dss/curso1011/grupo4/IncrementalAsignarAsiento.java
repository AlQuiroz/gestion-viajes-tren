/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.uca.dss.trenes.interfazExtendido.InterfazRepartoAsiento;

/**
 * Clase encargada de implementar a la interfaz 'InterfazRepartoAsiento'.
 *
 * Se encarga de repartir los asientos de manera incremental, implementando el único método de la interfaz.
 *
 * @author migue
 */
public class IncrementalAsignarAsiento implements InterfazRepartoAsiento{

    public int reparteAsiento(GestionReservas info,String origen, String destino,LocalDate fecha, LocalTime hora){
        /*Obtenemos el número de asientos del vehículo.*/
        int asientos = info.getVehiculo().getNumAsientos();
        /*Obtenemos los asientos libres.*/
        int asientosLibres =info.asientosLibres(origen,destino,fecha,hora);
        if (asientosLibres < 0){
            throw new RuntimeException("No quedan asientos disponibles");
        }
        /*Devolvemos la diferencia entre asientos del vehículo y asientos libres.*/
        return asientos-asientosLibres;
    }
}
