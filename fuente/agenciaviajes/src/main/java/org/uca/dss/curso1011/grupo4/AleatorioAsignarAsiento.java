/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.uca.dss.trenes.interfazExtendido.InterfazRepartoAsiento;
import java.util.ArrayList;

/**
 *
 * @author migue
 */
public class AleatorioAsignarAsiento implements InterfazRepartoAsiento{
    //TODO hay q mirar cuales han sido ya repartidos

    public int reparteAsiento(GestionReservas gr,String origen, String destino,LocalDate fecha, LocalTime hora){
        int asientosLibres =gr.asientosLibres(origen,destino,fecha,hora);
        int numeroAleatorio=0;
        boolean existeViaje=false;
        CargaDatos datos=gr.getDatos().getDatosDia(fecha);
        for(int i=0; i<datos.getTrayectosCargados().size(); i++){
            Trayecto trayecto=datos.getTrayectosCargados().get(i);
            if(trayecto.getOrigen().getNombre().equals(origen) && trayecto.getDestino().getNombre().equals(destino)){
                for(int j=0; j<trayecto.listarHorarios().size(); j++){
                    Horario horario=trayecto.listarHorarios().get(j);
                    if(horario.getHoraSalida().equals(hora)){
                        existeViaje=true;
                        numeroAleatorio=(int) (Math.random()*gr.getVehiculo().getNumAsientos()+1);
                        horario.setAsientoReservado(numeroAleatorio);
                    }
                }
            }
        }
        if(asientosLibres < 0){
            throw new RuntimeException("No quedan asientos disponibles");
        }
        if(!existeViaje){
            throw new RuntimeException("No existe el viaje indicado");
        }
        return numeroAleatorio;
    }

}
