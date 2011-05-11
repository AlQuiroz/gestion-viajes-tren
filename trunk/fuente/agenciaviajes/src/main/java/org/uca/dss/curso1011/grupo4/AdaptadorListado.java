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

    //Constructor
    /**
     * Construye un objeto de la clase AdaptadorListado
     *
     * Recibe de parámetro de entrada un objeto de la clase CargaDatos con los trayectos y trenes precargados.
     * @param datos objeto de la clase CargaDatos
     */
    public AdaptadorListado(CargaDatos datos){
        this.setDatos(datos);
    }
    
    private void setDatos(CargaDatos valor){
        this.datos=new CargaDatos(valor);
    }

    //Métodos Get

    public List<LocalTime> getHorarios(String origen, String destino, LocalDate fecha){
        this.listado=new ListadoViajes(fecha, new Ciudad(origen, "España"), new Ciudad(destino, "España"), this.getDatos());
        ArrayList<Viaje> listaViajes=new ArrayList<Viaje>();
        listaViajes=this.listado.listarViajesPorAsientoDisponible();
        List<LocalTime> listadoSalidas=new LinkedList<LocalTime>();
        int i=0;
        while(i<listaViajes.size()){
            Trayecto auxTrayecto=new Trayecto(listaViajes.get(i).getTrayecto());
            if(!listadoSalidas.add(auxTrayecto.getHorarioElegido().getHoraSalida())){
                throw new RuntimeException("Error al crear el listado");
            }
            i=i+1;
        }
        return listadoSalidas;
    }

    /**
     * Método consultor de datos.
     *
     * Devuelve el objeto de la clase CargaDatos que contiene los trenes y trayectos precargados.
     * @return objeto de CargaDatos con los trenes y trayectos existentes.
     */
    public CargaDatos getDatos(){
        return this.datos;
    }

}
