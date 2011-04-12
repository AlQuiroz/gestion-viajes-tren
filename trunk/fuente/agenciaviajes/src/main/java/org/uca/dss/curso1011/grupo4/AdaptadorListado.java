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

import org.uca.dss.curso1011.grupo4.interfaz.InterfazListados;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalTime;
import org.joda.time.LocalDate;
import java.util.LinkedList;

/**
 * Clase adaptadora de la interfaz.
 *
 * Se encarga de implementar la funcionalidad de la interfaz, en otras palabras, adaptala interfaz a la clase de gestión de listados
 *
 * @author Manuel Jesús de la Calle Brihuega
 */
public class AdaptadorListado implements InterfazListados {
    private CargaDatos datos;
    private ListadoViajes listado;
    public AdaptadorListado(CargaDatos datos){
        this.SetDatos(datos);
    }
    
    private void SetDatos(CargaDatos valor){
        this.datos=new CargaDatos(valor);
    }
    public List<LocalTime> getHorarios(String origen, String destino, LocalDate fecha){
        this.listado=new ListadoViajes(fecha, new Ciudad(origen, "España"), new Ciudad(destino, "España"), this.GetDatos());
        ArrayList<Viaje> listaViajes=new ArrayList<Viaje>();
        listaViajes=this.listado.ListarViajesPorAsientoDisponible();
        List<LocalTime> listadoSalidas=new LinkedList<LocalTime>();
        int i=0;
        while(i<listaViajes.size()){
            if(!listadoSalidas.add(listaViajes.get(i).GetTrayecto().GetHorarioElegido().GetHoraSalida())){
                System.out.println("Error al crear el listado");
            }
            i=i+1;
        }
        return listadoSalidas;
    }

    public CargaDatos GetDatos(){
        return this.datos;
    }

}
