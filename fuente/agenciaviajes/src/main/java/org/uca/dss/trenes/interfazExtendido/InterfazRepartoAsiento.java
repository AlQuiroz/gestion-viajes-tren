/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.trenes.interfazExtendido;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.uca.dss.curso1011.grupo4.GestionReservas;

/**
 * Interfaz encargada de repartir asientos.
 *
 * Esta interfaz permite abstraer el método de reparto de asientos
 *
 * @author migue
 */
public interface InterfazRepartoAsiento  {
    /**
     * Da el asiento que le corresponde al cliente
     *
     * Establece internamente el número de asiento correspondiente al tipo de implementación de la interfaz.
     *
     * @param info Informacion del trayecto al cual debe de dar el asiento
     * @param origen nombre de la ciudad origen del trayecto para el cual debe dar el asiento
     * @param fecha fecha para el viaje del cual queremos dar el asiento.
     * @param destino nombre de la ciudad destino del trayecto para el cual debe dar el asiento
     * @param hora hora del viaje para el cual queremos dar el asiento
     * @return el número del asiento
     */
    public int reparteAsiento(GestionReservas info, String origen, String destino, LocalDate fecha, LocalTime hora);


}
