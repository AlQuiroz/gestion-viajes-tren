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
 * Clase encargada de implementar la interfaz 'InterfazRepartoAsiento'
 *
 * Se encarga de repartir los asientos de manera aleatoria, implementando el único método de la interfaz.
 *
 * @author migue
 */
public class AleatorioAsignarAsiento implements InterfazRepartoAsiento{

    public int reparteAsiento(GestionReservas gr,String origen, String destino,LocalDate fecha, LocalTime hora){
        /*Almacenamos el número de asientos libres.*/
        int asientosLibres =gr.asientosLibres(origen,destino,fecha,hora);
        /*Inicializamos a 0 la variable del número aleatorio.*/
        int numeroAleatorio=0;
        /*Creamos un booleano que nos servirá para avisarnos de la existencia o no del viaje.*/
        boolean existeViaje=false;
        /*Obtenemos los datos correspondientes al dia.*/
        CargaDatos datos=gr.getDatos().getDatosDia(fecha);
        /*creamos una lista auxiliar de trayectos.*/
        ArrayList<Trayecto> listaTrayectos=datos.getTrayectosCargados();
        /* buscamos el trayecto cuyos origen y destino coincidan con los dados.*/
        for(int i=0; i<listaTrayectos.size(); i++){
            Trayecto trayecto=listaTrayectos.get(i);
            if(trayecto.getOrigen().getNombre().equals(origen) && trayecto.getDestino().getNombre().equals(destino)){
                /* buscamos el horario dentro del trayecto encontrado.*/
                for(int j=0; j<trayecto.listarHorarios().size(); j++){
                    Horario horario=trayecto.listarHorarios().get(j);
                    if(horario.getHoraSalida().equals(hora)){
                        /*Una vez encontrado, indicamos la existencia del viaje-*/
                        existeViaje=true;
                        /*Generamos el número aleatorio y lo establecemos como asiento reservado.*/
                        numeroAleatorio=(int) (Math.random()*gr.getVehiculo().getNumAsientos()+1);
                        horario.setAsientoReservado(numeroAleatorio);
                    }
                }
            }
        }
        /* Lanzamos las excepciones oportunas, si así se requiere.*/
        if(asientosLibres < 0){
            throw new RuntimeException("No quedan asientos disponibles");
        }
        if(!existeViaje){
            throw new RuntimeException("No existe el viaje indicado");
        }
        /* Devolvemos el número generado anteriormente.*/
        return numeroAleatorio;
    }

}
