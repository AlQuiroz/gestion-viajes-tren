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
      this._num_asientos = asientos;
      this._fecha_reserva = new LocalDate();
      this._viaje = viaje;
      //el _id_reserva tendra que hacerse de forma automática incrementando el anterior
    }

    /**
     * @return the _num_asientos
     */
    public int getNum_asientos() {
        return _num_asientos;
    }

    /**
     * @param num_asientos the _num_asientos to set
     */
    public void setNum_asientos(int num_asientos) {
        this._num_asientos = num_asientos;
    }

    /**
     * @return the _fecha_reserva
     */
    public LocalDate getFecha_reserva() {
        return _fecha_reserva;
    }

    /**
     * @param fecha_reserva the _fecha_reserva to set
     */
    public void setFecha_reserva(LocalDate fecha_reserva) {
        this._fecha_reserva = fecha_reserva;
    }

    /**
     * @return the _id_reserva
     */
    public int getId_reserva() {
        return _id_reserva;
    }

    /**
     * @param id_reserva the _id_reserva to set
     */
    public void setId_reserva(int id_reserva) {
        this._id_reserva = id_reserva;
    }

    /**
     * @return the _viaje
     */
    public Viaje getViaje() {
        return _viaje;
    }

    /**
     * @param viaje the _viaje to set
     */
    public void setViaje(Viaje viaje) {
        this._viaje = viaje;
    }
;
    private
    int _num_asientos;
    private LocalDate _fecha_reserva;
    private int _id_reserva;
    private Viaje _viaje;
}
