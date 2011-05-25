/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;

import org.joda.time.LocalDate;

/**
 * Clase que representa la reserva.
 *
 * Esta clase creara la reserva para un viaje concreto.
 *
 * @author migue
 */
public class Reserva {
    /**
     * Constructor de Reserva
     *
     * Clase encargada de representar una reserva de un número de asientos determinado para un viaje concreto. La reserva se identificar� por un c�digo 'ID_reserva', y tambi�n se almacenar� la fecha en la que se realice la misma.
     *
     * @param asientos int con los asientos que se desean reservar
     * @param viaje Objeto Viaje con el viaje a reservar
     * @param id String con el identificador de la reserva
     * @param horarioReservado Horario para el cual se realiza la reserva
     *
     */
    public Reserva(int asientos, Viaje viaje, String id, Horario horarioReservado){
      this.numAsientos = asientos;
      this.fechaReserva = new LocalDate();
      this.viaje = viaje;
      this.idReserva = id;
      this.horario=horarioReservado;
  }

    /**
     * Constructor.
     *
     * Construye una reserva que, por defecto, es para un asiento.
     *
     * @param viaje Viaje que queremos reservar.
     * @param id String con el identificador de la reserva.
     * @param horarioReservado Horario para el cual se realizará la reserva.
     */
    public Reserva(Viaje viaje,String id, Horario horarioReservado){
      this.numAsientos = 1;
      this.fechaReserva = new LocalDate();
      this.viaje = viaje;
      this.idReserva = id;
      this.horario=horarioReservado;
   }
     /**
     * Método consultor del número de asientos reservados
     *
     * Devuelve el numero de asientos reservados
      *
     * @return número de asientos reservados
     */
    public int getNumAsientos() {
        return numAsientos;
    }


    /**
     * Método consultor de Horario.
     *
     * Consulta y devuelve el objeto Horario para el cual se ha realizado la reserva.
     *
     * @return Horario para el que se a realizado la reserva.
     */
    public Horario getHorario(){
        return this.horario;
    }
    /**
     * Método consultor de la fecha de la reserva
     *
     * Devuelve la fecha de la reserva
     *
     * @return fecha de la reserva
     */
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }


    /**
     * Método consultor del Id_reserva
     *
     * Devuelve el id de la reserva
     *
     * @return El identificador de la reserva
     */
    public String getIdReserva() {
        return idReserva;
    }



    /**
     * Método consulto del viaje
     *
     * Devuelve le viaje que se ha reservado
     *
     * @return Viaje de la reserva
     */
    public Viaje getViaje() {
        return viaje;
    };

    private int numAsientos;
    private LocalDate fechaReserva;
    private String idReserva;
    private Viaje viaje;
    private Horario horario;
}
