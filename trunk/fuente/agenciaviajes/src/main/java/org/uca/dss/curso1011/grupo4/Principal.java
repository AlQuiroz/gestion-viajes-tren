/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;
import org.joda.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println("Hola");
        CargaDatos c=new CargaDatos("/home/manuel/Escritorio/DSS/gestion-viajes-tren/fuente/agenciaviajes/src/main/java/org/uca/dss/curso1011/grupo4/interfaz/trayectos.csv", "/home/manuel/Escritorio/DSS/gestion-viajes-tren/fuente/agenciaviajes/src/main/java/org/uca/dss/curso1011/grupo4/interfaz/trenes.csv");
        
        LocalDate fecha=new LocalDate(2011, 10, 20);
        Ciudad origen=new Ciudad("cádiz", "España");
        Ciudad destino=new Ciudad("sevilla", "España");
        
        ListadoViajes l=new ListadoViajes(fecha, origen, destino, c);
       // System.out.println(l.GetViajes().get(1).GetTrayecto().GetHorarioElegido().GetHoraLlegada().toString());
        ArrayList<Viaje> prueba= new ArrayList<Viaje>();
        prueba=l.GetViajes();
        
        int i=0;
        System.out.println(l.GetViajes().size());
        while(i<prueba.size()){
            System.out.println("Holaaaaaaa");
         //   System.out.println("Ciudad origen: "+prueba.get(i).GetTrayecto().GetOrigen().getNombre()+" Ciudad destino: "+prueba.get(i).GetTrayecto().GetDestino().getNombre()+" Hora salida: "+prueba.get(i).GetTrayecto().GetHorarioElegido().GetHoraSalida().toString());//+"Hora llegada: "+prueba.get(i).GetTrayecto().GetHorarioElegido().GetHoraLlegada().toString()+"Precio: "+prueba.get(i).GetPrecio());
            i=i+1;
        }
    }
}
