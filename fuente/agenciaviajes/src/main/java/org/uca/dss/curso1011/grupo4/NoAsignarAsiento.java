/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.uca.dss.trenes.interfazExtendido.IRepartoAsiento;
/**
 *
 * @author migue
 */
public class NoAsignarAsiento implements IRepartoAsiento{

    public int reparteAsiento(GestionReservas gr,String origen, String destino,LocalDate fecha, LocalTime hora){
        //datos.
        if (gr.asientosLibres(origen,destino,fecha,hora) < 0){
            throw new RuntimeException("No quedan asientos disponibles");
        }
        return -1;
    }



}
