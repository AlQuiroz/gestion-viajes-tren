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
import org.uca.dss.trenes.interfazExtendido.Itinerario;

/**
 *
 * @author manuel
 */
public class InterfazPruebaPropiaTest extends InterfazTest {
 /**
     * Comprueba que para fechas iguales (sin reservas) los horarios coinciden
     */
    @Test
    public void testListadoItinerariosSinRangoHorario() {
        System.out.println("Comienza la prueba");
        int days = 10;
        List<Itinerario> itinerarios = listado.getItinerarios(origen, "barcelona", hoy);
        System.out.println("Sigue la prueba");
        for(int i=0;i<itinerarios.size(); i++){
            String salida0="Itinerario "+i+": Origen: "+origen+ " Destino: "+destino;
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

    }


}