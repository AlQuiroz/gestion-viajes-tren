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
public class AleatorioAsignarAsiento implements IRepartoAsiento{
    //TODO hay q mirar cuales han sido ya repartidos
    public int reparteAsiento(GestionReservas gr,String origen, String destino,LocalDate fecha, LocalTime hora){
        int asientosLibres =gr.asientosLibres(origen,destino,fecha,hora);
        if (asientosLibres < 0){
            throw new RuntimeException("No quedan asientos disponibles");
        }
        return (int) (Math.random()*30+1);
    }


}
