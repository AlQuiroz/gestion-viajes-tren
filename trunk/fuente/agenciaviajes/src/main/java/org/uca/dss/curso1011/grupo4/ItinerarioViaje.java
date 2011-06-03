/*
 *  Copyright (C) 2011 Manuel Jesús de la Calle Brihuega
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.uca.dss.curso1011.grupo4;

import java.util.ArrayList;
import org.uca.dss.trenes.interfazExtendido.Itinerario;
import org.uca.dss.trenes.interfazExtendido.InformacionTrayecto;
/**
 * Clase encargada de implementar a la interfaz 'Itinerario'.
 *
 * Se encarga de implementar el único método de la interfaz. Además hereda de un ArrayList<InformacionTrayecto>.
 *
 * @author Manuel Jesús de la Calle Brihuega
 */
public class ItinerarioViaje extends ArrayList<InformacionTrayecto> implements Itinerario {

    /**
     * Método consultor de precio.
     *
     * Se encarga de calcular el precio y lo devuelve por parámetro.
     *
     * @return double con el precio
     */
    public double getPrecio(){
        /* Establecemos el precio a 0.*/
        double precio=0;
        /* Recorremos el ArrayList y vamos sumando precios.*/
        for(int i=0; i<this.size(); i++){
            precio=precio + this.get(i).getPrecio();
        }
        /* Devolvemos el precio. */
        return precio;
    }
}
