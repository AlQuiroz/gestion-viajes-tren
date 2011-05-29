/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;

import org.uca.dss.trenes.interfazExtendido.IRepartoAsiento;

/**
 *
 * @author migue
 */
public class AleatorioAsignarAsiento implements IRepartoAsiento{
    //TODO hay q mirar cuales han sido ya repartidos
    public int reparteAsiento(GestionReservas gr){
        int asientosLibres =gr.asientosLibres(gr.getOrigen().getNombre(),gr.getDestino().getNombre(),gr.getFecha(),gr.getHora());
        if (asientosLibres == 0){
            throw new RuntimeException("No quedan asientos disponibles");
        }
        return (int) (Math.random()*30+1);
    }


}
