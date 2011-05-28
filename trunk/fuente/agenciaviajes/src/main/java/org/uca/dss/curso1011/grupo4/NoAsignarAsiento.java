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
public class NoAsignarAsiento implements IRepartoAsiento{

    public int reparteAsiento(GestionReservas gr){
        //datos.
        if (gr.asientosLibres(gr.getOrigen().getNombre(),gr.getDestino().getNombre(),gr.getFecha(),gr.getHora()) == 0){
            throw new RuntimeException("No quedan asientos disponibles");
        }
        return -1;
    }


    public IRepartoAsiento clone() throws CloneNotSupportedException {
        return (IRepartoAsiento) super.clone();
    }

}
