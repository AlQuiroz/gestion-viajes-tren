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
import java.lang.Float;
import org.joda.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

/**
 * Clase encargada de representar un viaje en el sistema.
 *
 * En ella se describe el trayecto a realizar por un determinado tren, en una fecha y hora concretas, además de su precio.
 *
 * @author manuel
 */
public class Viaje {
    private Float precioFinal;
    private LocalDate fecha;
    private ArrayList<Reserva> Reservas;
    private Trayecto trayecto;
    private boolean realizadaReserva;

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
        
        this.SetFecha(fecha);

        this.SetTrayecto(trayecto);

        this.CalcularPrecioViaje();
        this.realizadaReserva=false;
    }
    /**
     * Constructor de copia
     *
     * Construye un objeto de la clase Viaje a partir de otro.
     *
     * @param viaje objeto a partir del cual se crea el nuevo
     */
    public Viaje(Viaje v){
        this.SetFecha(v.GetFecha());
        this.SetTrayecto(v.GetTrayecto());
        this.precioFinal = v.GetPrecio();
        this.realizadaReserva=v.realizadaReserva;
        if(v.realizadaReserva){
            this.SetReservas(v.GetReservas());
        }
    }

    //Métodos de asignación

    private void SetFecha(LocalDate valor){
        this.fecha=new LocalDate(valor);
    }

    private void SetTrayecto(Trayecto valor){
        this.trayecto=new Trayecto(valor);
    }
    
    /**
     * Método modificador.
     *
     * Establece la reserva, o conjunto de reservas realizadas previamente para el viaje.
     *
     * @param valor conjunto de reservas para el viaje
     */
    public void SetReservas(ArrayList<Reserva> valor){
        this.Reservas=new ArrayList<Reserva>();
        this.Reservas=valor;
        this.realizadaReserva=true;
    }

    private void SetPrecio(Float valor){
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
    public LocalDate GetFecha(){
        return this.fecha;
    }

    /**
     * Método consultor del trayecto
     *
     * Devuelve el trayecto que compone el viaje.
     *
     * @return trayecto que compone el viaje
     */
    public Trayecto GetTrayecto(){
        return this.trayecto;
    }
    
    /**
     * Método consultor.
     *
     * Devuelve el conjunto de reservas realizadas para el viaje.
     *
     * @return conjunto de reservas del viaje
     */
    public ArrayList<Reserva> GetReservas(){
        return this.Reservas;
    }

    /**
     * Método consultor del precio
     *
     * Devuelve el precio del viaje.
     *
     * @return precio del viaje
     */
    public Float GetPrecio(){
        return this.precioFinal;
    }

    //Otros métodos

    /**
     * Calcula el precio final del viaje.
     *
     * Lo calcula en función del precio del trayecto, y el número de trayectos que componen el viaje. Establece dicho precio en el atributo 'precio_final'.
     */
    public void CalcularPrecioViaje(){
        this.SetPrecio(this.GetTrayecto().GetPrecio());
    }

    /**
     * Método consultor de horarios
     *
     * Devuelve una lista con todos los horarios del viaje.
     *
     * @return lista de horarios
     */
    public ArrayList<Horario> ObtenerHorarios(){
        return this.GetTrayecto().ListarHorarios();
    }

    /**
     * Método consultor de horarios y precios.
     *
     * Devuelve un conjunto de horarios para el viaje con sus precios asociados.
     *
     * @return map de horarios y precios asociados
     */
    public Map<Horario, Float> ObtenerHorariosConPrecios(){
        return this.GetTrayecto().ListarHorariosConPrecios();
    }
    
}
