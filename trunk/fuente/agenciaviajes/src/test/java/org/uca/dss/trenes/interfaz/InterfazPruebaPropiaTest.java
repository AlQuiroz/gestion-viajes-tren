/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.trenes.interfaz;

import org.joda.time.LocalTime;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
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

     /**
     * Comprueba que los posibles itinerarios para el viaje Cádiz - Barcelona son 6, y muestra por pantalla los que correspondan según el rango horario
     */

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

   /**
     * Comprueba el correcto funcionamiento de la función asientosLibres
     */

    @Test
    public void testNumerosAsientosTrayecto(){
        List<Itinerario> itinerarios = listado.getItinerariosEntre(origen, "huelva", hoy, new LocalTime("9:00"), new LocalTime("18:00"));
        int asiento = compras.asientosLibres(hoy,itinerarios.get(0));
        assertEquals(asiento, 10);
    }

    /**
     * Reserva los asientos (sin asignación) correspondientes a los trayectos que componen el itinerario "cádiz" - "huelva"
     */
    @Test
    public void testReservaTrayectoSinAsignacion() throws CloneNotSupportedException{
        compras.setEstrategiaRepartoAsientos(new NoAsignarAsiento());
        List<Itinerario> itinerarios = listado.getItinerariosEntre(origen, "huelva", hoy, new LocalTime("9:00"), new LocalTime("18:00"));
        int reservas = 0;
        while(compras.asientosLibres(hoy,itinerarios.get(0))>0){
            List<ReservaTrayecto> rt = compras.reservaAsiento(itinerarios.get(0), hoy);
            assertEquals(rt.get(0).getNumeroAsiento(),-1);
            ++reservas;
        }
        assertEquals(reservas, 10);
    }
    /**
     * Reserva los asientos (con asignación incremental) correspondientes a los trayectos que componen el itinerario "cádiz" - "huelva"
     */
    @Test
    public void testReservaTrayectoAsignacionIncremental() throws CloneNotSupportedException{
        compras.setEstrategiaRepartoAsientos(new IncrementalAsignarAsiento());
        List<Itinerario> itinerarios = listado.getItinerariosEntre(origen, "huelva", hoy, new LocalTime("9:00"), new LocalTime("18:00"));
        int reservas = 0;
        while(compras.asientosLibres(hoy,itinerarios.get(0))>0){
            List<ReservaTrayecto> rt = compras.reservaAsiento(itinerarios.get(0), hoy);
            assertEquals(rt.get(0).getNumeroAsiento(),reservas+1);
            ++reservas;
        }
        assertEquals(reservas, 10);
    }

    /**
     * Reserva los asientos (con asignación aleatoria) correspondientes a los trayectos que componen el itinerario "cádiz" - "huelva"
     */
    @Test
    public void testReservaTrayectoAsignacionAleatoria() throws CloneNotSupportedException{
        compras.setEstrategiaRepartoAsientos(new AleatorioAsignarAsiento());
        List<Itinerario> itinerarios = listado.getItinerariosEntre(origen, "huelva", hoy, new LocalTime("9:00"), new LocalTime("18:00"));
        int reservas = 0;
        ArrayList<Integer> listaNumeros=new ArrayList<Integer>();
        boolean existe=false;
        while(compras.asientosLibres(hoy,itinerarios.get(0))>0){
            List<ReservaTrayecto> rt = compras.reservaAsiento(itinerarios.get(0), hoy);
            listaNumeros.add(rt.get(0).getNumeroAsiento());
            ++reservas;
        }
        //Comprobamos que no se repite el número de asiento
        for(int i=0; i<listaNumeros.size(); i++){
            existe=false;
            for(int j=0; j<listaNumeros.size(); j++){
                if(listaNumeros.get(i).equals(listaNumeros.get(j)) && i!=j){
                    existe=true;
                }
            }
            if(existe){
                throw new RuntimeException("No pasa el test");
            }
        }
        // Comprobamos que se han reservado 10 asientos.
        assertEquals(listaNumeros.size(), 10);
        assertEquals(reservas, 10);
    }

    /**
     * En el siguiente test comprobamos que si están disponibles, para varios trayectos del mismo itinerario se reservan los mismos asientos (con asignación incremental). 
     */
    @Test
    public void testReservaItinerarioMismoAsientoIncremental() throws CloneNotSupportedException{
        compras.setEstrategiaRepartoAsientos(new IncrementalAsignarAsiento());
        List<Itinerario> itinerarios = listado.getItinerariosEntre(origen, "huelva", hoy, new LocalTime("9:00"), new LocalTime("18:00"));
        int reservas = 0;
        List<ReservaTrayecto> listaReservas=compras.reservaAsiento(itinerarios.get(0), hoy);
        for(int i=0; i<listaReservas.size(); i++){
            if((i+1)<listaReservas.size()){
            assertSame(listaReservas.get(i).getNumeroAsiento(), listaReservas.get(i+1).getNumeroAsiento());
            }
        }

        }

    /**
     * En el siguiente test comprobamos que si están disponibles, para varios trayectos del mismo itinerario se reservan los mismos asientos (con asignación aleatoria).
     */

    @Test
    public void testReservaItinerarioMismoAsientoAleatorio() throws CloneNotSupportedException{
        compras.setEstrategiaRepartoAsientos(new AleatorioAsignarAsiento());
        List<Itinerario> itinerarios = listado.getItinerariosEntre(origen, "huelva", hoy, new LocalTime("9:00"), new LocalTime("18:00"));
        int reservas = 0;
        List<ReservaTrayecto> listaReservas=compras.reservaAsiento(itinerarios.get(0), hoy);
        for(int i=0; i<listaReservas.size(); i++){
            if((i+1)<listaReservas.size()){
            assertSame(listaReservas.get(i).getNumeroAsiento(), listaReservas.get(i+1).getNumeroAsiento());
            }
        }

        }


    /**
     * En el siguiente test comprobamos que si no está disponible el mismo asiento que el primero para el segundo trayecto del itinerario, se reserva un asiento distinto.
     */
    @Test
    public void testReservaItinerarioDistintoAsiento() throws CloneNotSupportedException{
        compras.setEstrategiaRepartoAsientos(new IncrementalAsignarAsiento());
        List<Itinerario> itinerarios = listado.getItinerariosEntre(origen, "huelva", hoy, new LocalTime("9:00"), new LocalTime("18:00"));
        int reservas = 0;
        // Reservamos los asientos numeros 1.
        List<ReservaTrayecto> lista1=compras.reservaAsiento(itinerarios.get(0), hoy);

        lista1.remove(1);
        // Cancelamos solo el primer trayecto del itinerario
        compras.cancelaReserva(lista1);
        List<ReservaTrayecto> lista2=compras.reservaAsiento(itinerarios.get(0), hoy);
        // Para el primer trayecto podremos reservar ahora el asiento 1
        // Para el segundo trayecto no podremos reservarlo, y se reservará el 2
        for(int y=0; y<lista2.size(); y++){
            if(y==0){
                assertSame(lista2.get(y).getNumeroAsiento(), 1);
            }else{
                assertSame(lista2.get(y).getNumeroAsiento(), 2);
            }
        }

        }

    /**
     * En el siguiente test realizamos varias reservas y cancelaciones, para comprobar el correcto funcionamiento de las funciones que implementan dicha funcionalidad.
     */
    @Test
    public void testReservaCancelayReserva() throws CloneNotSupportedException{
        compras.setEstrategiaRepartoAsientos(new IncrementalAsignarAsiento());
        List<Itinerario> itinerarios = listado.getItinerariosEntre(origen, "huelva", hoy, new LocalTime("9:00"), new LocalTime("18:00"));
        int reservas = 0;
        // Reservamos los asientos numeros 1.
        List<ReservaTrayecto> lista1=compras.reservaAsiento(itinerarios.get(0), hoy);
        
        for(int y=0; y<lista1.size(); y++){
            assertSame(lista1.get(y).getNumeroAsiento(), 1);
        }
        // Cancelamos la reserva
        compras.cancelaReserva(lista1);
        // Volvemos a reservar, por lo que los asientos tendrán que ser de nuevo los numeros 1
        List<ReservaTrayecto> lista2=compras.reservaAsiento(itinerarios.get(0), hoy);

        for(int i=0; i<lista2.size(); i++){
            assertSame(lista2.get(i).getNumeroAsiento(), 1);
        }

        // Volvemos a reservar, por lo que los asientos serán los número 2.
        List<ReservaTrayecto> lista3= compras.reservaAsiento(itinerarios.get(0), hoy);

        for(int j=0; j<lista3.size(); j++){
            assertSame(lista3.get(j).getNumeroAsiento(), 2);
        }
        // Cancelamos todo
        compras.cancelaReserva(lista3);
        compras.cancelaReserva(lista2);
        // Reservamos 10 viajes
        int viajes=0;
        while(viajes<10){
            List<ReservaTrayecto> lista=compras.reservaAsiento(itinerarios.get(0), hoy);
            viajes++;
            // Comprobamos que la asignación es incremental
            assertSame(lista.get(0).getNumeroAsiento(), viajes);
            // Cancelamos el viaje cuyo asiento es el numero 8 y lo volvemos a reservar
            if(lista.get(0).getNumeroAsiento()==8){
                compras.cancelaReserva(lista);
                List<ReservaTrayecto> lista4=compras.reservaAsiento(itinerarios.get(0), hoy);
                assertSame(lista4.get(0).getNumeroAsiento(),8);
            }
        }
        

        }

    /**
     * Hacemos lo mismo que en el test anterior pero para una asignación de asientos aleatorios.
     */
    @Test
    public void testReservaCancelayReservaAsientosAleatorios() throws CloneNotSupportedException{
        compras.setEstrategiaRepartoAsientos(new AleatorioAsignarAsiento());
        List<Itinerario> itinerarios = listado.getItinerariosEntre(origen, "huelva", hoy, new LocalTime("9:00"), new LocalTime("18:00"));
        int reservas = 0;
        // Reservamos asientos.
        List<ReservaTrayecto> lista1=compras.reservaAsiento(itinerarios.get(0), hoy);
        int asientoDisponibledeNuevo=lista1.get(0).getNumeroAsiento();
        // Cancelamos la reserva
        compras.cancelaReserva(lista1);
        // Volvemos a reservar, por lo que los números utilizados anteriormente podrán volver a usarse
        List<ReservaTrayecto> lista2=compras.reservaAsiento(itinerarios.get(0), hoy);
        int asientoDisponibledeNuevo2=lista2.get(0).getNumeroAsiento();
        // Volvemos a cancelar
        compras.cancelaReserva(lista2);
        // Reservamos las 10 plazas disponibles, por lo que los asientos liberados anteriormente tendrán que ser usados.
        int viajes=0;
        boolean exito1=false;
        boolean exito2=false;
        while(viajes<10){
            List<ReservaTrayecto> lista=compras.reservaAsiento(itinerarios.get(0), hoy);
            viajes++;
            if(lista.get(0).getNumeroAsiento()==asientoDisponibledeNuevo){
                exito1=true;
            }
            if(lista.get(0).getNumeroAsiento()==asientoDisponibledeNuevo2){
                exito2=true;
            }

        }
       if(!exito1 || !exito2){
                throw new RuntimeException("No utiliza los asientos liberados por la cancelación");
            }

        }

}