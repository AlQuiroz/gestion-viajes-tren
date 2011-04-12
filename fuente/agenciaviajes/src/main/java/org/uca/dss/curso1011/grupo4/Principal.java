/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;
import org.joda.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

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
        CargaDatos c=new CargaDatos("/home/manuel/Escritorio/DSS/gestion-viajes-tren/fuente/agenciaviajes/src/main/java/org/uca/dss/curso1011/grupo4/interfaz/trayectos.csv", "/home/manuel/Escritorio/DSS/gestion-viajes-tren/fuente/agenciaviajes/src/main/java/org/uca/dss/curso1011/grupo4/interfaz/trenes.csv");
        
        LocalDate fecha=new LocalDate(2011, 10, 20);
        Ciudad origen=new Ciudad("cádiz", "España");
        Ciudad destino=new Ciudad("sevilla", "España");
        
        ListadoViajes l=new ListadoViajes(fecha, origen, destino, c);
       // System.out.println(l.GetViajes().get(1).GetTrayecto().GetHorarioElegido().GetHoraLlegada().toString());
        ArrayList<Viaje> prueba= new ArrayList<Viaje>();
        //Dejo al viaje sin asientos disponibles
        l.GetViajes().get(0).GetTrayecto().GetHorarioElegido().ActualizaAsientos(-10);
        //Listo los viajes por asiento disponible
        prueba=l.ListarViajesPorAsientoDisponible();
        
        int i=0;
        System.out.println(l.GetViajes().size());
        
        while(i<prueba.size()){
            System.out.println("\nViaje nº"+i+": ");
            System.out.println("Ciudad origen: "+prueba.get(i).GetTrayecto().GetOrigen().getNombre()+" Ciudad destino: "+prueba.get(i).GetTrayecto().GetDestino().getNombre()+" Hora salida: "+prueba.get(i).GetTrayecto().GetHorarioElegido().GetHoraSalida().toString()+"Hora llegada: "+prueba.get(i).GetTrayecto().GetHorarioElegido().GetHoraLlegada().toString()+"Precio: "+prueba.get(i).GetPrecio());
            System.out.println("Coste tren: "+prueba.get(i).GetTrayecto().GetHorarioElegido().GetTren().getCostetramo());
            System.out.println("Numero de tramos: "+prueba.get(i).GetTrayecto().GetTramos());
            System.out.println("Asientos disponibles: "+ prueba.get(i).GetTrayecto().GetHorarioElegido().GetAsientosDisponibles());
            i=i+1;
        }

        Map<Viaje, Float> lista=new HashMap<Viaje, Float>();
        lista=l.ListarViajesConPrecio();
        System.out.println("Fin de la aplicación");

        
        

    }
}
