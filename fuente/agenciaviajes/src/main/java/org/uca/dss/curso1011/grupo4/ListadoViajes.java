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
import java.lang.Float;
import java.util.Map;
import java.util.HashMap;

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
    private ArrayList<Viaje> Viajes;

    // Constructores

    /**
     * Constructor.
     *
     * Construye un objeto de ListadoViajes, recibiendo por parámetros los datos necesarios para crear el listado.
     *
     * @param fecha fecha para los viajes que queremos listar
     * @param origen ciudad origen de los viajes que queremos listar
     * @param destino ciudad destino de los viajes que queremos listar
     * @param datos objeto de la clase CargaDatos que contiene los datos de trenes y trayectos precargados.
     */
    public ListadoViajes(LocalDate fecha, Ciudad origen, Ciudad destino, CargaDatos datos){

        this.SetFecha(fecha);
        if(!this.ComprobarValidezFecha()){
            System.out.println("La fecha indicada es anterior a la actual");
        }
        this.SetOrigen(origen);
        this.SetDestino(destino);
        //Ahora creamos los viajes
        ArrayList<Trayecto> trayectos=new ArrayList<Trayecto>();
        trayectos=datos.GetTrayectosCargados();
        int i=0;
        this.Viajes=new ArrayList<Viaje>();
        while(i<trayectos.size()){
            if(trayectos.get(i).GetOrigen().getNombre().equalsIgnoreCase(origen.getNombre()) && trayectos.get(i).GetDestino().getNombre().equalsIgnoreCase(destino.getNombre())){
                int j=0;
                while(j<trayectos.get(i).ListarHorarios().size()){
                    Trayecto trayecto_=new Trayecto(trayectos.get(i));
                    trayecto_.SetHorarioElegido(trayecto_.ListarHorarios().get(j));
                    Viaje viaje_=new Viaje(fecha, trayecto_);
                    if(!Viajes.add(viaje_)){
                        System.out.println("Error al cargar el viaje");
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
       this.fecha=new LocalDate(valor.GetFecha());
       this.origen=new Ciudad(valor.GetOrigen());
       this.destino=new Ciudad(valor.GetDestino());
       this.Viajes=new ArrayList<Viaje>();
       this.Viajes=valor.GetViajes();
   }

    /**
     * Método consultor de ciudad origen.
     *
     * Devuelve la ciudad origen de los viajes para los cuales se ha generado el listado.
     *
     * @return ciudad origen de los viajes
     */
    public Ciudad GetOrigen(){
       return this.origen;
   }

   /**
    * Método consultor de ciudad destino.
    *
    * Devuelve la ciudad destino de los viajes para los cuales se ha generado el listado.
    *
    * @return ciudad destino de los viajes
    */
   public Ciudad GetDestino(){
       return this.destino;
   }

   /**
    * Método consultor de viajes
    *
    * Genera y devuelve un conjunto de objetos Viaje, que serán los viajes que forman parte del listado.
    *
    * @return listado de viajes
    */
   public ArrayList<Viaje> GetViajes(){
       return this.Viajes;
   }

   /**
    * Método consultor de fecha.
    *
    * Devuelve la fecha para la cual se ha pedido el listado de viajes.
    *
    * @return fecha de viaje
    */
   public LocalDate GetFecha(){
       return this.fecha;
   }
   private void SetFecha(LocalDate valor){
       this.fecha=valor;
   }

   private void SetOrigen(Ciudad valor){
       this.origen=new Ciudad(valor);
   }

   private void SetDestino(Ciudad valor){
       this.destino=new Ciudad(valor);
   }

   /**
    * Método consultor de listado.
    *
    * Genera y devuelve un listado de los viajes con sus correspondientes precios.
    *
    * @return conjunto de pares de viaje-precio
    */
   public Map<Viaje, Float> ListarViajesConPrecio(){
       Map<Viaje, Float> listado=new HashMap<Viaje, Float>();
       int i=0;
       while(i<this.GetViajes().size()){
            listado.put(this.GetViajes().get(i), this.GetViajes().get(i).GetPrecio());
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
   public ArrayList<Viaje> ListarViajesPorAsientoDisponible(){
       ArrayList<Viaje> listado= new ArrayList<Viaje>();
       int i=0;
       while(i<this.GetViajes().size()){
           if(this.GetViajes().get(i).GetTrayecto().GetHorarioElegido().ComprobarDisponibilidad()){
               if(!listado.add(this.GetViajes().get(i))){
                   System.out.println("Error al crear el listado");
               }
           }
           i=i+1;
       }
       return listado;
   }

   private boolean ComprobarValidezFecha(){
       if(this.fecha.isBefore(new LocalDate())){
           return false;
       }else{
           return true;
       }
   }
}
