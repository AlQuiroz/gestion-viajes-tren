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
import org.joda.time.LocalTime;
import org.joda.time.LocalDate;
import java.util.ArrayList;
import org.uca.dss.trenes.interfazExtendido.InterfazVehiculo;

/**
 * Representa el horario previsto para un viaje en el sistema.
 *
 * Describe tanto la hora de salida como la hora de llegada del mismo.
 *
 * @author Manuel Jesús de la Calle Brihuega
 */
public class Horario {

    /* Número de asientos disponibles para el horario.*/
    private int asientosDisponibles;
    /* Lista con los números de asientos reservados. */
    private ArrayList<Integer> asientosReservados;
    /* Hora de llegada del horario. */
    private String horaLlegada;
    /* Hora de salida del horario. */
    private String horaSalida;
    /* Vehículo que trabajará dicho horario. */
    private InterfazVehiculo vehiculo;
    /* Fecha para la que se pone en práctica el horario. */
    private LocalDate fecha;

    // Constructores
    /**
     * Constructor
     *
     * Construye un objeto de la clase Horario recibiendo como parámetros los atributos del objeto.
     *
     * @param salida hora de salida del viaje
     * @param llegada hora de llegada del viaje
     * @param asientos asientos disponibles para el viaje
     * @param vehiculo vehículo en el que se realizará el horario
     * @param fecha LocalDate con la fecha para la que se aplica el horario.
     */
    public Horario(LocalTime salida, LocalTime llegada, int asientos, InterfazVehiculo vehiculo, LocalDate fecha){
  
        this.setHoraSalida(salida);
        this.setHoraLlegada(llegada);
        this.setAsientosDisponibles(asientos);
        this.setVehiculo(vehiculo);
        this.setFecha(fecha);
        this.asientosReservados=new ArrayList<Integer>();
    }

    /**
     * Constructor de copia
     *
     * Construye un objeto de la clase Horario recibiendo como parámetro otro objeto Horario.
     * @param valor objeto del que se realizará una copia
     */
    public Horario(Horario valor){
        this.setHoraSalida(valor.getHoraSalida());
        this.setHoraLlegada(valor.getHoraLlegada());
        this.setAsientosDisponibles(valor.getAsientosDisponibles());
        this.setVehiculo(valor.getVehiculo());
        this.setFecha(valor.getFecha());
        this.asientosReservados=valor.asientosReservados;
    }
    
    // Métodos privados de asignación

    private void setHoraSalida(LocalTime valor){
        this.horaSalida=valor.toString();
    }


    private void setFecha(LocalDate valor){
        this.fecha=valor;
    }

    private void setHoraLlegada(LocalTime valor){
        this.horaLlegada=valor.toString();
    }

    private void setAsientosDisponibles(int valor){
        if(valor < 1)
        {
            this.asientosDisponibles=0;
        }
        else
        {
            this.asientosDisponibles=valor;
        }

    }
    
    private void setVehiculo(InterfazVehiculo valor){
        this.vehiculo=valor;
    }
     

    // Métodos 'Get'

    /**
     * Método consultor de disponibilidad.
     * Devuelve el número de asientos disponibles para el horario
     *
     * @return número de asientos disponibles
     */
    public int getAsientosDisponibles(){
        return this.asientosDisponibles;
    }

    /**
     * Método consultor de asientos reservados.
     *
     * Devuelve una lista de enteros, con los números de los asientos reservados hasta el momento.
     *
     * @return lista de enteros con los asientos
     */
    public ArrayList<Integer> getListaAsientosReservados(){
        return this.asientosReservados;
    }
    
    /**
     * Método modificador de asientos.
     *
     * Establece el asiento, cuyo número se recibe por parámetro, como reservado.
     *
     * @param numero entero con el número que representa al asiento.
     */
    public void setAsientoReservado(int numero){
        this.asientosReservados.add(numero);
    }

    /**
     * Método consultor de fecha.
     *
     * Devuelve la fecha en la cual se aplica el Horario.
     *
     * @return LocalDate con la fecha.
     */
    public LocalDate getFecha(){
        return this.fecha;
    }

    /**
     * Método consultor de vehículo.
     *
     * Devuelve el vehículo que realiza el trayecto para este horario.
     *
     * @return objeto de la clase que implemente a la interfaz Interfazvehículo que realiza el trayecto para el horario
     */
    public InterfazVehiculo getVehiculo(){
        return this.vehiculo;
    }

    /**
     * Método consultor de hora.
     * 
     * Devuelve la hora de llegada prevista para el tren en este horario.
     *
     * @return hora de llegada del tren
     */
    public LocalTime getHoraLlegada(){
        return new LocalTime(this.horaLlegada);
    }

    /**
     * Método consultor de hora.
     * 
     * Devuelve la hora de salida prevista para el tren en este horario.
     *
     * @return hora de salida del tren
     */
    public LocalTime getHoraSalida(){
        return new LocalTime(this.horaSalida);
    }

    //Otros métodos

    /**
     * Actualiza el número de asientos libres para el horario.
     *
     * Decrementa o incremedecrementa el atributo asientosDisponibles con el número de asientos reservados o cancelados(parámetro 'asientos_reservados').
     *
     * @param asientosReservados numero de asientos a decrementar (reservados).
     */
    public void actualizaAsientos(int asientosReservados){
        /* Obtenemos el número de asientos disponibles. */
        int disponibles=this.getAsientosDisponibles();
        /* Actualizamos los asientos disponibles con los que recibimos por parámetro. */
        disponibles=disponibles + asientosReservados;
        if(disponibles < 0){
            throw new RuntimeException("Error: No hay asientos disponibles");
        }
        else
        {
            /* Actualizamos el nuevo número de asientos. */
            this.setAsientosDisponibles(disponibles);
        }

    }

    /**
     * Comprueba la disponibilidad del viaje.
     *
     * Revisa si existen asientos disponibles para el horario. Devuelve un booleano indicando su resultado.
     *
     * @return booleano que verifica la disponibildad o no de asientos
     */
    public boolean comprobarDisponibilidad(){
        return this.getAsientosDisponibles() > 0;
    }
    
    /**
     * Método consultor de precio.
     * 
     * Devuelve el precio de un horario, que no es más que el precio por tramo del tren que cubre. No devuelve un precio final, solo el precio del tramo viajado.
     * 
     * @return precio del horario
     */
    public float getPrecioHorario(){
        return this.getVehiculo().getCosteTramo();
    }
    
}
