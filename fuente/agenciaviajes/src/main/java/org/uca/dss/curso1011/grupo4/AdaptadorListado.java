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

    private String rutaTrenes;
    private String rutaTrayectos;
    //private CargaDatos datos;
    //private ListadoViajes listado;
    private List<CargaDatos> listaDatos;
    //Constructor
    /**
     * Construye un objeto de la clase AdaptadorListado
     *
     * Recibe de parámetro de entrada un objeto de la clase CargaDatos con los trayectos y trenes precargados.
     * @param datos objeto de la clase CargaDatos
     */
    public AdaptadorListado(String Trenes, String Trayectos, String origen, String destino, LocalDate fecha){
        this.rutaTrayectos=Trayectos;
        this.rutaTrenes=Trenes;
      //  this.listado=new ListadoViajes(fecha, new Ciudad(origen, "España"), new Ciudad(destino, "España"), this.rutaTrenes, this.rutaTrayectos);
        this.listaDatos=new LinkedList();

    }

    public void generarDatosDia(LocalDate dia){
        CargaDatos datos=new CargaDatos(this.rutaTrayectos, this.rutaTrenes, dia);
        this.listaDatos.add(datos);
    }

    public CargaDatos getDatosDia(LocalDate dia){
        CargaDatos datos;
        boolean existe=false;
        int indice=0;
        for(int i=0; i<this.listaDatos.size(); i++){
            if(this.listaDatos.get(i).getFecha().equals(dia)){
                existe=true;
                indice=i;
            }
        }
        if(!existe){
            this.generarDatosDia(dia);
            for(int j=0; j<this.listaDatos.size(); j++){
                if(this.listaDatos.get(j).getFecha().equals(dia)){
                    existe=true;
                    indice=j;
                }
            }
        }
        datos=this.listaDatos.get(indice);
        return datos;
    }

    
    //Métodos Get
    //public ListadoViajes getListado(){
      //  return this.listado;
   // }
    public List<LocalTime> getHorarios(String origen, String destino, LocalDate fecha){
        int indice=0;
        boolean existe=false;
        for(int c=0; c<this.listaDatos.size(); c++){
            if(this.listaDatos.get(c).getFecha().equals(fecha)){
                existe=true;
                indice=c;
            }
        }
        List<LocalTime> listadoSalidas=new LinkedList<LocalTime>();
        if(!existe){
            this.generarDatosDia(fecha);
        }

            ArrayList<Trayecto> listaTrayectos=this.listaDatos.get(indice).getTrayectosCargados();
            for(int i=0; i<listaTrayectos.size();i++){
                if(listaTrayectos.get(i).getOrigen().getNombre().equals(origen) && listaTrayectos.get(i).getDestino().getNombre().equals(destino)){
                    for(int j=0; j<listaTrayectos.get(i).listarHorarios().size(); j++){
                        if(listaTrayectos.get(i).listarHorarios().get(j).comprobarDisponibilidad()){
                            if(!listadoSalidas.add(listaTrayectos.get(i).listarHorarios().get(j).getHoraSalida())){
                                throw new RuntimeException("Error al crear el listado");
                            }
                        }
                    }
                }
            }
        return listadoSalidas;
    }

    /**
     * Método consultor de datos.
     *
     * Devuelve el objeto de la clase CargaDatos que contiene los trenes y trayectos precargados.
     * @return objeto de CargaDatos con los trenes y trayectos existentes.
     */
    
     
}
