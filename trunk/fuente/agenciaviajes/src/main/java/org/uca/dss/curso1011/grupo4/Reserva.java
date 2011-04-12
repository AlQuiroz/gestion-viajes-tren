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
      this.num_asientos = asientos;
      this.fecha_reserva = new LocalDate();
      this.viaje = viaje;
      //el _id_reserva tendra que hacerse de forma autom�tica incrementando el anterior
    }

    /**
     * @return the _num_asientos
     */
    public int getNum_asientos() {
        return num_asientos;
    }


    /**
     * @return the _fecha_reserva
     */
    public LocalDate getFecha_reserva() {
        return fecha_reserva;
    }


    /**
     * @return the _id_reserva
     */
    public int getId_reserva() {
        return id_reserva;
    }



    /**
     * @return the _viaje
     */
    public Viaje getViaje() {
        return viaje;
    }

;
    private
    int num_asientos;
    private LocalDate fecha_reserva;
    private int id_reserva;
    private Viaje viaje;
}
