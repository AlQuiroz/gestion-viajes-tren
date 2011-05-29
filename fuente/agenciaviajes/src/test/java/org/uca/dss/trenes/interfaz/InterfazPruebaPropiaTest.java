/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.trenes.interfaz;

import org.joda.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import org.joda.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import org.uca.dss.curso1011.grupo4.NoAsignarAsiento;
import org.uca.dss.trenes.interfazExtendido.Itinerario;
import org.uca.dss.trenes.interfazExtendido.ReservaTrayecto;
import org.uca.dss.curso1011.grupo4.IncrementalAsignarAsiento;
import org.uca.dss.curso1011.grupo4.AleatorioAsignarAsiento;
/**
 *
 * @author manuel
 */
public class InterfazPruebaPropiaTest extends InterfazTest {
     /**
     * Comprueba que los posibles itinerarios para el viaje Cádiz - Barcelona son 6, y los muestra por pantalla
     */
    @Test
    public void testListadoItinerariosSinRangoHorario() {
        List<Itinerario> itinerarios = listado.getItinerarios(origen, "barcelona", hoy);
        for(int i=0;i<itinerarios.size(); i++){
            System.out.println("==================================");
            String salida0="Itinerario "+i+": Origen: "+origen+ " Destino: "+"barcelona";
            System.out.println(salida0);
            for(int j=0; j<itinerarios.get(i).size(); j++){
                String salida="Origen: "+itinerarios.get(i).get(j).getOrigen()+" Destino: "+ itinerarios.get(i).get(j).getDestino();
                String salida2= "Hora de salida: "+itinerarios.get(i).get(j).getHoraSalida();
                String salida3= "Hora de llegada: "+itinerarios.get(i).get(j).getHoraLlegada();
                System.out.println(salida);
                System.out.println(salida2);
                System.out.println(salida3);
            }
        }
        //Comprobamos que son 6 los itinerarios posibles
        assertEquals(itinerarios.size(),5);

    }


    @Test
    public void testListadoItinerariosConRangoHorario() {
        List<Itinerario> itinerarios = listado.getItinerariosEntre(origen, "barcelona", hoy, new LocalTime("9:00"), new LocalTime("18:00"));
        for(int i=0;i<itinerarios.size(); i++){
            System.out.println("==================================");
            String salida0="Itinerario "+i+": Origen: "+origen+ " Destino: "+"barcelona";
            System.out.println(salida0);
            for(int j=0; j<itinerarios.get(i).size(); j++){
                String salida="Origen: "+itinerarios.get(i).get(j).getOrigen()+" Destino: "+ itinerarios.get(i).get(j).getDestino();
                String salida2= "Hora de salida: "+itinerarios.get(i).get(j).getHoraSalida();
                String salida3= "Hora de llegada: "+itinerarios.get(i).get(j).getHoraLlegada();
                System.out.println(salida);
                System.out.println(salida2);
                System.out.println(salida3);
            }
        }
        //Comprobamos que son 2 los itinerarios posibles dentro del rango horario.
        assertEquals(itinerarios.size(),2);

    }


    @Test
    public void testNumeroAsientoTrayecto(){
        List<Itinerario> itinerarios = listado.getItinerariosEntre(origen, "huelva", hoy, new LocalTime("9:00"), new LocalTime("18:00"));
        int asiento = compras.asientosLibres(hoy,itinerarios.get(0));
        assertEquals(asiento, 10);
    }
    @Test
    public void testReservaTrayectoSinAsignacion() throws CloneNotSupportedException{
        compras.setRepartoAsientoStrategy(new NoAsignarAsiento());
        List<Itinerario> itinerarios = listado.getItinerariosEntre(origen, "huelva", hoy, new LocalTime("9:00"), new LocalTime("18:00"));
        int reservas = 0;
        while(compras.asientosLibres(hoy,itinerarios.get(0))>0){
            List<ReservaTrayecto> rt = compras.reservaAsiento(itinerarios.get(0), hoy);
            assertEquals(rt.get(0).getNumeroAsiento(),-1);
            ++reservas;
        }
        assertEquals(reservas, 10);
    }
    @Test
    public void testReservaTrayectoAsignacionIncremental() throws CloneNotSupportedException{
        compras.setRepartoAsientoStrategy(new IncrementalAsignarAsiento());
        List<Itinerario> itinerarios = listado.getItinerariosEntre(origen, "huelva", hoy, new LocalTime("9:00"), new LocalTime("18:00"));
        int reservas = 0;
        while(compras.asientosLibres(hoy,itinerarios.get(0))>0){
            List<ReservaTrayecto> rt = compras.reservaAsiento(itinerarios.get(0), hoy);
            assertEquals(rt.get(0).getNumeroAsiento(),reservas+1);
            ++reservas;
        }
        assertEquals(reservas, 10);
    }

    @Test
    public void testReservaTrayectoAsignacionAleatoria() throws CloneNotSupportedException{
        compras.setRepartoAsientoStrategy(new AleatorioAsignarAsiento());
        List<Itinerario> itinerarios = listado.getItinerariosEntre(origen, "huelva", hoy, new LocalTime("9:00"), new LocalTime("18:00"));
        int reservas = 0;
        while(compras.asientosLibres(hoy,itinerarios.get(0))>0){
            List<ReservaTrayecto> rt = compras.reservaAsiento(itinerarios.get(0), hoy);
            System.out.println(rt.get(0).getNumeroAsiento());
            //assertEquals(rt.get(0).getNumeroAsiento(),reservas+1);
            ++reservas;
        }
        assertEquals(reservas, 10);
    }
}