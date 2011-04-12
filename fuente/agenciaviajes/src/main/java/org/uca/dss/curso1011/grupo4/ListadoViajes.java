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
 *
 * @author Manuel Jesús de la Calle Brihuega
 */
public class ListadoViajes {
    private LocalDate fecha;
    private Ciudad origen;
    private Ciudad destino;
    private ArrayList<Viaje> Viajes;

    // Constructores

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
                    trayecto_.SeleccionarHorario(trayectos.get(i).ListarHorarios().get(j));
                    Viaje viaje_=new Viaje(fecha, trayecto_);
                    Viajes.add(viaje_);
                    System.out.println("Construyendo horarios"+trayectos.get(i).GetHorarioElegido().GetHoraLlegada().toString());
                    j=j+1;
                }
            }
            i=i+1;
        }
   }

   public ArrayList<Viaje> GetViajes(){
       return this.Viajes;
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

   public Map<Viaje, Float> ListarViajesConPrecio(){
       Map<Viaje, Float> listado=new HashMap<Viaje, Float>();
       int i=0;
       while(i<this.GetViajes().size()){
            listado.put(this.GetViajes().get(i), this.GetViajes().get(i).GetPrecio());
            i=i+1;
       }
       return listado;
   }

   public ArrayList<Viaje> ListarViajesPorAsientoDisponible(){
       ArrayList<Viaje> listado= new ArrayList<Viaje>();
       int i=0;
       while(i<this.GetViajes().size()){
           if(this.GetViajes().get(i).GetTrayecto().GetHorarioElegido().ComprobarDisponibilidad()){
               listado.add(this.GetViajes().get(i));
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
