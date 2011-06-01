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

import org.joda.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.uca.dss.trenes.interfazExtendido.InformacionTrayecto;

/**
 * Clase encargada de generar un listado de viajes.
 *
 * Genera y representa un listado de los viajes disponibles para una fecha determinada entre una ciudad origen y una ciudad destino.
 *
 * @author Manuel Jesús de la Calle Brihuega
 */
public class ListadoViajes {
    private LocalDate fecha;
    private Ciudad origen;
    private Ciudad destino;
    private ArrayList<Viaje> viajes;
    private Adaptador datos;

    // Constructores

    /**
     * Constructor.
     *
     * Construye un objeto de ListadoViajes, recibiendo por parámetros los datos necesarios para crear el listado.
     *
     * @param fecha fecha para los viajes que queremos listar
     * @param origen ciudad origen de los viajes que queremos listar
     * @param destino ciudad destino de los viajes que queremos listar
     * @param adaptador referencia del objeto Adaptador que contiene los datos del sistema.
     */
    public ListadoViajes(LocalDate fecha, Ciudad origen, Ciudad destino, Adaptador adaptador){

        this.datos=adaptador;
        this.setFecha(fecha);
        CargaDatos datos=this.datos.getDatosDia(fecha);
        this.setOrigen(origen);
        this.setDestino(destino);
        ArrayList<Trayecto> trayectos=new ArrayList<Trayecto>();
        trayectos=datos.getTrayectosCargados();
        int i=0;
        this.viajes=new ArrayList<Viaje>();
        while(i<trayectos.size()){
            Ciudad auxOrigen=new Ciudad(trayectos.get(i).getOrigen());
            Ciudad auxDestino=new Ciudad(trayectos.get(i).getDestino());
            if(auxOrigen.getNombre().equalsIgnoreCase(origen.getNombre()) && auxDestino.getNombre().equalsIgnoreCase(destino.getNombre())){
                int j=0;
                while(j<trayectos.get(i).listarHorarios().size()){
                    Trayecto trayecto_=new Trayecto(trayectos.get(i));
                    trayecto_.setHorarioElegido(trayecto_.listarHorarios().get(j));
                    InformacionTrayecto info=new InformacionTrayecto(origen.getNombre(), destino.getNombre(), trayecto_.listarHorarios().get(j).getHoraSalida(), trayecto_.listarHorarios().get(j).getHoraLlegada(), trayecto_.listarHorarios().get(j).getPrecioHorario());
                    Viaje viaje_=new Viaje(fecha, trayecto_, info);
                    if(!viajes.add(viaje_)){
                        throw new RuntimeException("Error al cargar el viaje");
                    }
                    
                    j=j+1;
                }
            }
            i=i+1;
        }
   }

    /**
     * Constructor de copia
     *
     * Construye un objeto de la clase ListadoViajes que será una copia del que recibe por parámetro.
     *
     * @param valor objeto del que queremos realizar la copia
     */
    public ListadoViajes(ListadoViajes valor){
       this.datos=valor.datos;
       this.fecha=new LocalDate(valor.getFecha());
       this.origen=new Ciudad(valor.getOrigen());
       this.destino=new Ciudad(valor.getDestino());
       this.viajes=new ArrayList<Viaje>();
       this.viajes=valor.getViajes();
   }

    /**
     * Método consultor de ciudad origen.
     *
     * Devuelve la ciudad origen de los viajes para los cuales se ha generado el listado.
     *
     * @return ciudad origen de los viajes
     */
    public Ciudad getOrigen(){
       return this.origen;
   }


   /**
    * Método consultor de ciudad destino.
    *
    * Devuelve la ciudad destino de los viajes para los cuales se ha generado el listado.
    *
    * @return ciudad destino de los viajes
    */
   public Ciudad getDestino(){
       return this.destino;
   }

   /**
    * Método consultor de viajes
    *
    * Genera y devuelve un conjunto de objetos Viaje, que serán los viajes que forman parte del listado.
    *
    * @return listado de viajes
    */
   public ArrayList<Viaje> getViajes(){
       return this.viajes;
   }

   /**
    * Método consultor de fecha.
    *
    * Devuelve la fecha para la cual se ha pedido el listado de viajes.
    *
    * @return fecha de viaje
    */
   public LocalDate getFecha(){
       return this.fecha;
   }

   private void setFecha(LocalDate valor){
       this.fecha=valor;
   }

   private void setOrigen(Ciudad valor){
       this.origen=new Ciudad(valor);
   }

   private void setDestino(Ciudad valor){
       this.destino=new Ciudad(valor);
   }

   /**
    * Método consultor de listado.
    *
    * Genera y devuelve un listado de los viajes con sus correspondientes precios.
    *
    * @return conjunto de pares de viaje-precio
    */
   public Map<Viaje, Float> listarViajesConPrecio(){
       Map<Viaje, Float> listado=new HashMap<Viaje, Float>();
       int i=0;
       while(i<this.getViajes().size()){
            listado.put(this.getViajes().get(i), this.getViajes().get(i).getPrecio());
            i=i+1;
       }
       return listado;
   }

   /**
    * Método consultor de listado.
    *
    * Genera y devuelve un listado de los viajes que disponen de asientos libres.
    *
    * @return conjunto de viajes que disponen de asientos libres
    */
   public ArrayList<Viaje> listarViajesPorAsientoDisponible(){
       ArrayList<Viaje> listado= new ArrayList<Viaje>();
       int i=0;
       while(i<this.getViajes().size()){
           Trayecto auxiliar=new Trayecto(this.getViajes().get(i).getTrayecto());
           Horario hAuxiliar= new Horario(auxiliar.getHorarioElegido());
           if(hAuxiliar.comprobarDisponibilidad()){
               if(!listado.add(this.getViajes().get(i))){
                   throw new RuntimeException("Error al crear el listado");
               }
           }
           i=i+1;
       }
       return listado;
   }

}
