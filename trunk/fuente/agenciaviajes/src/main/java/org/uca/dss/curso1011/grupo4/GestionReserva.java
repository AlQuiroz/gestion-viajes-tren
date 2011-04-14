/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;
import java.util.ArrayList;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.uca.dss.curso1011.grupo4.interfaz.InterfazCompras;



/**
 * Genera y gestiona una Reserva.
 *
 * Clase encargada de generar y gestionar una reserva determinada, para un viaje de nuestro sistema. Ser� la encargada de interaccionar con el usuario a la hora de realizar la reserva.
 *
 * @author migue
 */
public class GestionReserva implements InterfazCompras{
    /**
     * Constructor de la clase GestionReserva
     *
     * Recibe la fecha del viaje, la ciudad origen, la ciudad destino, la hora del viaje, el tren y el n�mero de asiento a reservar
     *
     * @param cFecha
     * @param cOrigen
     * @param cDestino
     * @param cHora
     * @param cTren
     * @param numasientos
     */
    public GestionReserva(LocalDate cFecha, Ciudad cOrigen, Ciudad cDestino, LocalTime cHora, Tren cTren, int numasientos ){
        this.fecha = cFecha;
        this.origen = cOrigen;
        this.destino = cDestino;
        this.hora = cHora;
        this.tren = cTren;
        this.numAsientos = numasientos;
    };

    /**
     * Metodo Consultor de la fecha de reserva
     *
     * Devuelve la fecha en la que se viajar�
     *
     * @return La fecha del viaje de la reserva
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * M�todo Consultor del origen del viaje
     *
     * Devuelve la ciudad origen del viaje
     *
     * @return Ciudad origen del viaje de la reserva
     */
    public Ciudad getOrigen() {
        return origen;
    }

    /**
     * M�todo Consultor del destino del viaje
     *
     * Devuelve la ciudad destino del viaje
     *
     * @return Ciudad destino del viaje de la resreva
     */
    public Ciudad getDestino() {
        return destino;
    }

    /**
     * M�todo consultor de la hora del viaje
     *
     * Devuelve la hora del viaje
     *
     * @return Hora del viaje reservado
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * M�todo consultor del tren del viaje
     *
     * Duelve el tren del viaje reservador
     *
     * @return Tren del viaje reservado
     */
    public Tren getTren() {
        return tren;
    }

    /**
     * M�todo consultor del n�mero de asientos reservado.
     *
     * Devuelve el n�mero de asientos que se han reservado.
     * @return the num_asientos
     */
    public int getNumAsientos() {
        return numAsientos;
    }

    /**
     * M�todo consultor del precio.
     *
     * Devuelve el precio de la reserva.
     *
     * @param origen
     * @param destino
     * @param fecha
     * @param hora
     * @return Precio de la reserva
     */
    public double getPrecio(String origen, String destino, LocalDate fecha, LocalTime hora) {
        double precioViajes;
        CargaDatos c=new CargaDatos("./src/main/java/org/uca/dss/curso1011/grupo4/interfaz/trayectos.csv", "./src/main/java/org/uca/dss/curso1011/grupo4/interfaz/trenes.csv");
        ListadoViajes l = new ListadoViajes(fecha, new Ciudad(origen), new Ciudad(destino), c);
        ArrayList<Viaje> viajes = new ArrayList<Viaje>();
        viajes  = l.GetViajes();
        int i=0;
        precioViajes = 0;
        while(i <viajes.size()){
            if(viajes.get(i).GetTrayecto().GetOrigen().getNombre().equals(origen) && viajes.get(i).GetTrayecto().GetDestino().getNombre().equals(destino))
                precioViajes = viajes.get(i).GetTrayecto().GetPrecio();
            else
            ++i;
        }
        return precioViajes;
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Realiza la reserva del viaje.
     *
     * Reserva un asiento para el viaje dandole ciudad orige, ciudad destino, fecha y hora
     * 
     * @param origen
     * @param destino
     * @param fecha
     * @param hora
     * @return
     */
    public String reservaAsiento(String origen, String destino, LocalDate fecha, LocalTime hora) {
        CargaDatos c=new CargaDatos("./src/main/java/org/uca/dss/curso1011/grupo4/interfaz/trayectos.csv", "./src/main/java/org/uca/dss/curso1011/grupo4/interfaz/trenes.csv");
        ListadoViajes l = new ListadoViajes(fecha, new Ciudad(origen), new Ciudad(destino), c);
        ArrayList<Viaje> viajes = new ArrayList<Viaje>();
        Viaje viaje = null;
        viajes  = l.GetViajes();
        int i=0;
        while(i <viajes.size()){
            if(viajes.get(i).GetTrayecto().GetOrigen().getNombre().equals(origen) && viajes.get(i).GetTrayecto().GetDestino().getNombre().equals(destino))
                viaje = new Viaje(viajes.get(i));
            else
            ++i;
        }
        if (viaje == null) throw new IllegalArgumentException();
        // una guia de dB4o: http://www.programacion.com/articulo/persistencia_de_objetos_java_utilizando_db4o_308#4_ejemplo
        //Para grabar datos parece que hay que usar lo siguiente
        //ObjectContainer db = Db4o.openFile("./src/main/resources/reservas.yap");
        Reserva reserva = new Reserva(1,viaje);
        return reserva.getIdReserva();
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Cancela una reserva.
     *
     * Dado un c�digo de reserva, cancela la reserva si existe liberando los asientes del tren de dicho viaje.
     *
     * @param codigoReserva de la reserva a cancelar
     */
    public void cancelaReserva(String codigoReserva) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    private LocalDate fecha;
    private Ciudad origen;
    private Ciudad destino;
    private LocalTime hora;
    private Tren tren;
    private int numAsientos;
    
}
