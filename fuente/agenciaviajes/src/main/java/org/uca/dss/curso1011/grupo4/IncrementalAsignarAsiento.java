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
public class IncrementalAsignarAsiento implements IRepartoAsiento{

    public int reparteAsiento(GestionReservas gr){
        int asientosTren = gr.getTren().getNumAsientos();
        int asientosLibres =gr.asientosLibres(gr.getOrigen().getNombre(),gr.getDestino().getNombre(),gr.getFecha(),gr.getHora());
        if (asientosLibres == 0){
            throw new RuntimeException("No quedan asientos disponibles");
        }
        return asientosTren-asientosLibres +1;
    }


}
