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
 * Clase que gestiona el caso de uso de las reservas
 *
 * @author migue
 */
public class GestionReserva implements InterfazCompras{
    public GestionReserva(LocalDate cFecha, Ciudad cOrigen, Ciudad cDestino, LocalTime cHora, Tren cTren ){
        this.fecha = cFecha;
        this.origen = cOrigen;
        this.destino = cDestino;
        this.hora = cHora;
        this.tren = cTren;
    };

    /**
     * @return the fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * @return the origen
     */
    public Ciudad getOrigen() {
        return origen;
    }

    /**
     * @return the destino
     */
    public Ciudad getDestino() {
        return destino;
    }

    /**
     * @return the hora
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * @return the tren
     */
    public Tren getTren() {
        return tren;
    }

    /**
     * @return the num_asientos
     */
    public int getNum_asientos() {
        return num_asientos;
    }


    public double getPrecio(String origen, String destino, LocalDate fecha, LocalTime hora) {
        double precioViajes;
        CargaDatos c=new CargaDatos("./src/main/java/org/uca/dss/curso1011/grupo4/interfaz/trayectos.csv", "./src/main/java/org/uca/dss/curso1011/grupo4/interfaz/trenes.csv");
        ListadoViajes l = new ListadoViajes(fecha, new Ciudad(origen), new Ciudad(destino), c);
        ArrayList<Viaje> viajes = new ArrayList<Viaje>();
        viajes  = l.GetViajes();
        int i=0;
        precioViajes = 0;
        while(i <viajes.size()){
            if(viajes.get(i).GetTrayecto().GetOrigen().getNombre().equals(origen) || viajes.get(i).GetTrayecto().GetDestino().getNombre().equals(destino))
                precioViajes = viajes.get(i).GetTrayecto().GetPrecio();
            else
            ++i;
        }
        return precioViajes;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public String reservaAsiento(String origen, String destino, LocalDate fecha, LocalTime hora) {
        return "hola";
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void cancelaReserva(String codigoReserva) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    private
    LocalDate fecha;
    private Ciudad origen;
    private Ciudad destino;
    private LocalTime hora;
    private Tren tren;
    private int num_asientos;   
    
}
