/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.uca.dss.trenes.interfazExtendido.InterfazRepartoAsiento;
/**
 * Clase encargada de implementar la interfaz 'InterfazRepartoAsiento'.
 *
 * Se encarga de repartir los asientos sin ninguna asignación, implementando el único método de la interfaz.
 *
 * @author migue
 */
public class NoAsignarAsiento implements InterfazRepartoAsiento{

    public int reparteAsiento(GestionReservas info,String origen, String destino,LocalDate fecha, LocalTime hora){
        /*Lanzamos una excepción si no existen asientos libres.*/
        if (info.asientosLibres(origen,destino,fecha,hora) < 0){
            throw new RuntimeException("No quedan asientos disponibles");
        }
        /* Devolvemos -1, que será el número de los asientos a los que no se asigna un tipo de reparto.*/
        return -1;
    }
}
