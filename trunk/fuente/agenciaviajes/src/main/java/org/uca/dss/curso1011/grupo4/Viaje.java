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
    private Float precio_final;
    private LocalDate fecha;
    //private ArrayList<Reserva> Reservas;
    private Trayecto trayecto;

    //Constructor

    /**
     * Constructor
     *
     * Construye un objeto de la clase Viaje.
     *
     * @param fecha fecha prevista para el viaje
     * @param trayecto trayecto que compone el viaje
     */
    public Viaje(LocalDate fecha, Trayecto trayecto/*, ArrayList<Reserva> Reservas*/){
        this.fecha=new LocalDate();
        this.SetFecha(fecha);

        this.SetTrayecto(trayecto);

        //this.SetReservas(Reservas);
        this.CalcularPrecioViaje();
    }

    //Métodos de asignación

    private void SetFecha(LocalDate valor){
        this.fecha=valor;
    }

    private void SetTrayecto(Trayecto valor){
        this.trayecto=new Trayecto(valor);
    }
    /*
    private void SetReservas(ArrayList<Reserva> valor){
        this.Reservas=valor;
    }*/

    private void SetPrecio(Float valor){
        this.precio_final=valor;
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
    /*
    public ArrayList<Reserva> GetReservas(){
        return this.Reservas;
    }*/

    /**
     * Método consultor del precio
     *
     * Devuelve el precio del viaje.
     *
     * @return precio del viaje
     */
    public Float GetPrecio(){
        return this.precio_final;
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
