/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;

import org.joda.time.LocalDate;

/**
 * Clasa que representa las reserva.
 *
 * Esta clase creara la reserva para un viaje concreto.
 *
 * @author migue
 */
public class Reserva {
    /*
     * Constructor de Reserva
     *
     * Clase encargada de representar una reserva de un n�mero de asientos determinado para un viaje concreto. La reserva se identificar� por un c�digo 'ID_reserva', y tambi�n se almacenar� la fecha en la que se realice la misma.
     *
     * @param asientos que se desean reservar
     * @param viaje a reservar
     */
    public Reserva(int asientos, Viaje viaje){
      //Primero hay que combrar que se pueda hacer la reserva si hay asientos
      this.numAsientos = asientos;
      this.fechaReserva = new LocalDate();
      this.viaje = viaje;
      //el _idReserva tendra que hacerse de forma automática incrementando el anterior
    }

    /**
     * Constructor de reserva de un viaje que por defecto es para un asiento
     *
     * Crea un objeto de la clase Reserva a partir de un viaje
     *
     * @param viaje
     */
    public Reserva(Viaje viaje){
       //Primero hay que combrar que se pueda hacer la reserva si hay asientos
      this.numAsientos = 1;
      this.fechaReserva = new LocalDate();
      this.viaje = viaje;
      //el _id_reserva tendra que hacerse de forma autom�tica incrementando el anterior
    }
     /**
     * M�todo consultor del n�mero de asientos reservados
     *
     * Devuelve el numero de asientos reservados
      *
     * @return n�mero de asientos reservados
     */
    public int getNumAsientos() {
        return numAsientos;
    }


    /**
     * M�todo consultor de la fecha de la reserva
     *
     * Devuelve la fecha de la reserva
     *
     * @return fecha de la reserva
     */
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }


    /**
     * M�todo consultor del Id_reserva
     *
     * Devuelve el id de la reserva
     *
     * @return El identificador de la reserva
     */
    public String getIdReserva() {
        return idReserva;
    }



    /**
     * M�todo consulto del viaje
     *
     * Devuelve le viaje que se ha reservado
     *
     * @return Viaje de la reserva
     */
    public Viaje getViaje() {
        return viaje;
    }

;
    private int numAsientos;
    private LocalDate fechaReserva;
    private String idReserva;
    private Viaje viaje;
}
