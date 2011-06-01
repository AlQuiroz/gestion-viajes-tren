/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.uca.dss.trenes.interfazExtendido.InterfazRepartoAsiento;
/**
 *
 * @author migue
 */
public class NoAsignarAsiento implements InterfazRepartoAsiento{

    public int reparteAsiento(GestionReservas info,String origen, String destino,LocalDate fecha, LocalTime hora){
        if (info.asientosLibres(origen,destino,fecha,hora) < 0){
            throw new RuntimeException("No quedan asientos disponibles");
        }
        return -1;
    }



}
