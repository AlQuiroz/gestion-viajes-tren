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
    public Reserva(int asientos, Viaje viaje){
      //Primero hay que combrar que se pueda hacer la reserva si hay asientos
      this.numAsientos = asientos;
      this.fechaReserva = new LocalDate();
      this.viaje = viaje;
      //el _idReserva tendra que hacerse de forma automática incrementando el anterior
    }

    /**
     * @return the _numAsientos
     */
    public int getNumAsientos() {
        return numAsientos;
    }


    /**
     * @return the _fechaReserva
     */
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }


    /**
     * @return the _idReserva
     */
    public String getIdReserva() {
        return idReserva;
    }



    /**
     * @return the _viaje
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
