/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;

import java.util.ArrayList;
import org.uca.dss.trenes.interfazExtendido.Itinerario;
import org.uca.dss.trenes.interfazExtendido.InformacionTrayecto;
import java.util.LinkedList;
/**
 *
 * @author manuel
 */
public class ItinerarioViaje extends ArrayList<InformacionTrayecto> implements Itinerario {


    public double getPrecio(){
        double precio=0;

        for(int i=0; i<this.size(); i++){
            precio=precio + this.get(i).getPrecio();
        }
        return precio;
    }
}
