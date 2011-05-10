/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;
import org.uca.dss.curso1011.grupo4.interfaz.InterfazListados;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

/**
 *
 * @author manuel
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Pruebas de funcionamiento
        CargaDatos c=new CargaDatos("./src/main/java/org/uca/dss/curso1011/grupo4/interfaz/trayectos.csv", "./src/main/java/org/uca/dss/curso1011/grupo4/interfaz/trenes.csv");
        
        LocalDate fecha=new LocalDate(2011, 10, 20);
        Ciudad origen=new Ciudad("cádiz", "España");
        Ciudad destino=new Ciudad("sevilla", "España");
        
        ListadoViajes l=new ListadoViajes(fecha, origen, destino, c);
       // System.out.println(l.GetViajes().get(1).GetTrayecto().GetHorarioElegido().GetHoraLlegada().toString());
        ArrayList<Viaje> prueba= new ArrayList<Viaje>();
        //Dejo al viaje sin asientos disponibles
        l.getViajes().get(0).getTrayecto().getHorarioElegido().actualizaAsientos(-10);
        //Listo los viajes por asiento disponible
        prueba=l.listarViajesPorAsientoDisponible();
        
        int i=0;
        System.out.println(l.getViajes().size());
        
        while(i<prueba.size()){
            System.out.println("\nViaje nº"+i+": ");
            System.out.println("Ciudad origen: "+prueba.get(i).getTrayecto().getOrigen().getNombre()+" Ciudad destino: "+prueba.get(i).getTrayecto().getDestino().getNombre()+" Hora salida: "+prueba.get(i).getTrayecto().getHorarioElegido().getHoraSalida().toString()+"Hora llegada: "+prueba.get(i).getTrayecto().getHorarioElegido().getHoraLlegada().toString()+"Precio: "+prueba.get(i).getPrecio());
            System.out.println("Coste tren: "+prueba.get(i).getTrayecto().getHorarioElegido().getTren().getCosteTramo());
            System.out.println("Numero de tramos: "+prueba.get(i).getTrayecto().getTramos());
            System.out.println("Asientos disponibles: "+ prueba.get(i).getTrayecto().getHorarioElegido().getAsientosDisponibles());
            i=i+1;
        }

        Map<Viaje, Float> lista=new HashMap<Viaje, Float>();
        lista=l.listarViajesConPrecio();
        System.out.println("Fin de la aplicación");
        List<LocalTime> listilla= new LinkedList<LocalTime>();
        AdaptadorListado ll=new AdaptadorListado(c);

        listilla=ll.getHorarios("sevilla", "valencia", new LocalDate(2011, 8, 8));
        int o=0;
        while(o<listilla.size()){
            System.out.println("Hora salida del Viaje "+o+": "+listilla.get(o).toString());
            o=o+1;
        }
        

    }
}
