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
import org.uca.dss.curso1011.grupo4.interfazExtendido.Itinerario;

/**
 * Clase encargada de representar un viaje en el sistema.
 *
 * En ella se describe el trayecto a realizar por un determinado tren, en una fecha y hora concretas, además de su precio.
 *
 * @author Manuel Jesús de la Calle Brihuega
 */
public class Viaje {
    private double precioFinal;
    private LocalDate fecha;
    private Trayecto trayecto;
    private Itinerario itinerario;
    private boolean compuesto;
    //Constructor

    /**
     * Constructor
     *
     * Construye un objeto de la clase Viaje.
     *
     * @param fecha fecha prevista para el viaje
     * @param trayecto trayecto que compone el viaje
     */
    public Viaje(LocalDate fecha, Trayecto trayecto){
        
        this.setFecha(fecha);

        this.setTrayecto(trayecto);

        this.calcularPrecioViaje();
        this.compuesto=false;
    }

    public Viaje(LocalDate fecha, Itinerario itinerario){
        this.compuesto=true;
        this.setFecha(fecha);
        this.itinerario=itinerario;
    }
    /**
     * Constructor de copia
     *
     * Construye un objeto de la clase Viaje a partir de otro.
     *
     * @param viaje objeto a partir del cual se crea el nuevo
     */
    public Viaje(Viaje viaje){
        if(viaje.compuesto){
            this.compuesto=viaje.compuesto;
            this.setFecha(viaje.getFecha());
            this.precioFinal= viaje.getPrecio();
            this.itinerario=viaje.getItinerario();
            
        }else{
            this.setFecha(viaje.getFecha());
            this.setTrayecto(viaje.getTrayecto());
            this.precioFinal = viaje.getPrecio();
            this.compuesto=viaje.compuesto;
        }
    }



    //Métodos de asignación


    private void setFecha(LocalDate valor){
        this.fecha=new LocalDate(valor);
    }

    private void setTrayecto(Trayecto valor){
        if(!this.compuesto){
            this.trayecto=new Trayecto(valor);
        }
    }
    

    private void setPrecio(double valor){
        this.precioFinal=valor;
    }

    // Métodos consultores

    /**
     * Método consultor de la fecha
     *
     * Devuelve la fecha en la que se realizará el viaje.
     *
     * @return fecha de comienzo del viaje
     */
    public LocalDate getFecha(){
        return this.fecha;
    }

    public Itinerario getItinerario(){
        if(this.compuesto){
            return this.itinerario;
        }else{
            return null;
        }
    }
    /**
     * Método consultor del trayecto
     *
     * Devuelve el trayecto que compone el viaje.
     *
     * @return trayecto que compone el viaje
     */
    public Trayecto getTrayecto(){
        if(!this.compuesto)
            return this.trayecto;
        else
            return null;
    }
    

    /**
     * Método consultor del precio
     *
     * Devuelve el precio del viaje.
     *
     * @return precio del viaje
     */
    public double getPrecio(){
        return this.precioFinal;
    }

    //Otros métodos

    /**
     * Calcula el precio final del viaje.
     *
     * Lo calcula en función del precio del trayecto, y el número de trayectos que componen el viaje. Establece dicho precio en el atributo 'precio_final'.
     */
    private void calcularPrecioViaje(){
        if(this.compuesto){
            this.setPrecio(this.getItinerario().getPrecio());

        }else{
            this.setPrecio(this.getTrayecto().getPrecio());
        }
    }

    /**
     * Método consultor de horarios
     *
     * Devuelve una lista con todos los horarios del viaje.
     *
     * @return lista de horarios
     */
    public ArrayList<Horario> obtenerHorarios(){
        if(!this.compuesto)
            return this.getTrayecto().listarHorarios();
        else
            return null;
    }

    /**
     * Método consultor de horarios y precios.
     *
     * Devuelve un conjunto de horarios para el viaje con sus precios asociados.
     *
     * @return map de horarios y precios asociados
     */
    public Map<Horario, Float> obtenerHorariosConPrecios(){
        if(!this.compuesto)
            return this.getTrayecto().listarHorariosConPrecios();
        else
            return null;
    }
    
}
