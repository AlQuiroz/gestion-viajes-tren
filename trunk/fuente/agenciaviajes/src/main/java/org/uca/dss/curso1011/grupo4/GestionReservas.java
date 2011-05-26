/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.ArrayList;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.uca.dss.trenes.interfazExtendido.InterfazCompras;
import org.uca.dss.trenes.basededatos.DBUtils;


/**
 * Genera y gestiona una Reserva.
 *
 * Clase encargada de generar y gestionar una reserva determinada, para un viaje de nuestro sistema. Será la encargada de interaccionar con el usuario a la hora de realizar la reserva.
 *
 * @author migue
 */

public class GestionReservas implements InterfazCompras{
    /**
     * Constructor de la clase GestionReserva
     *
     * Recibe la fecha del viaje, la ciudad origen, la ciudad destino, la hora del viaje, el tren, el número de asiento a reservar y el objeto adaptador que contiene los datos del sistema.
     *
     * @param cFecha LocalDate con la fecha para la cual queremos gestionar la reserva.
     * @param cOrigen Ciudad de origen del viaje a reservar.
     * @param cDestino Ciudad de destino del viaje a reservar.
     * @param cHora LocalTime con la hora de salida para la que queremos reservar el viaje.
     * @param cTren Tren con el tren para el que queremos reservar el viaje.
     * @param numasientos int con el número de asientos que queremos reservar.
     * @param adaptador Objeto Adaptador que contiene los datos del sistema.
     */
    public GestionReservas(LocalDate cFecha, Ciudad cOrigen, Ciudad cDestino, LocalTime cHora, Tren cTren, int numasientos, Adaptador adaptador ){
        this.fecha = cFecha;
        this.origen = cOrigen;
        this.destino = cDestino;
        this.hora = cHora;
        this.tren = cTren;
        this.numAsientos = numasientos;
        this.datos=adaptador;
 };

 /**
  * Constructor de la clase GestionReserva
  *
  * Recibe el objeto adaptador a partir del cual se construye el sistema, y crea un objeto GestionReserva.
  *
  * @param adaptador Objeto Adaptador que contiene los datos del sistema.
  */
 public GestionReservas(Adaptador adaptador){
        this.datos=adaptador;
    };

public int asientosLibres(String origen, String destino, LocalDate fecha, LocalTime hora){
       CargaDatos datosDia=this.datos.getDatosDia(fecha);
       int asientos=0;
       boolean existeOrigen=false;
       boolean existeDestino=false;
       for(int i=0; i<datosDia.getTrayectosCargados().size(); i++){
           if(datosDia.getTrayectosCargados().get(i).getOrigen().getNombre().equals(origen)){
               existeOrigen=true;
               if(datosDia.getTrayectosCargados().get(i).getOrigen().getNombre().equals(origen) && datosDia.getTrayectosCargados().get(i).getDestino().getNombre().equals(destino)){
                   existeDestino=true;
                   for(int j=0; j<datosDia.getTrayectosCargados().get(i).listarHorarios().size(); j++){
                        if(datosDia.getTrayectosCargados().get(i).listarHorarios().get(j).getHoraSalida().equals(hora)){
                            if(datosDia.getTrayectosCargados().get(i).listarHorarios().get(j).comprobarDisponibilidad()){
                                asientos=datosDia.getTrayectosCargados().get(i).listarHorarios().get(j).getAsientosDisponibles();
                            }
                        }
                   }
                }
          }
       }

       if(!existeOrigen){
            throw new IllegalArgumentException("Error: La ciudad origen no existe");

       }
       if(!existeDestino){
                throw new IllegalArgumentException("Error: La ciudad destino no existe");

       }    
       
       return asientos;
     };
    /**
     * Metodo Consultor de la fecha de reserva
     *
     * Devuelve la fecha en la que se viajará
     *
     * @return La fecha del viaje de la reserva
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Método Consultor del origen del viaje
     *
     * Devuelve la ciudad origen del viaje
     *
     * @return Ciudad origen del viaje de la reserva
     */
    public Ciudad getOrigen() {
        return origen;
    }

    /**
     * Método Consultor del destino del viaje
     *
     * Devuelve la ciudad destino del viaje
     *
     * @return Ciudad destino del viaje de la resreva
     */
    public Ciudad getDestino() {
        return destino;
    }

    /**
     * Método consultor de la hora del viaje
     *
     * Devuelve la hora del viaje
     *
     * @return Hora del viaje reservado
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * Método consultor del tren del viaje
     *
     * Duelve el tren del viaje reservador
     *
     * @return Tren del viaje reservado
     */
    public Tren getTren() {
        return tren;
    }

    /**
     * Método consultor del número de asientos reservado.
     *
     * Devuelve el número de asientos que se han reservado.
     * @return int Número de asientos que queremos gestionar para la reserva.
     */
    public int getNumAsientos() {
        return numAsientos;
    }

    
    public double getPrecio(String origen, String destino, LocalDate fecha, LocalTime hora) {
        double precioViajes;
        ListadoViajes l = new ListadoViajes(fecha, new Ciudad(origen), new Ciudad(destino), this.datos);
        ArrayList<Viaje> viajes = new ArrayList<Viaje>();
        viajes  = l.getViajes();
        int i=0;
        precioViajes = 0;
        while(i <viajes.size()){
            Trayecto auxTrayecto=new Trayecto(viajes.get(i).getTrayecto());
            Ciudad auxOrigen=new Ciudad(auxTrayecto.getOrigen());
            Ciudad auxDestino=new Ciudad(auxTrayecto.getDestino());
            if(auxOrigen.getNombre().equals(origen) && auxDestino.getNombre().equals(destino)){
                precioViajes = viajes.get(i).getTrayecto().getPrecio();}
            else{
                ++i;}
        }
        return precioViajes;
    
    }
    
    public String reservaAsiento(String origen, String destino, LocalDate fecha, LocalTime hora) {
        CargaDatos datosDia=this.datos.getDatosDia(fecha);
        //int asientosDisponibles=0;
        for(int i=0; i<datosDia.getTrayectosCargados().size(); i++){
            if(datosDia.getTrayectosCargados().get(i).getOrigen().getNombre().equals(origen) && datosDia.getTrayectosCargados().get(i).getDestino().getNombre().equals(destino)){
                for(int j=0; j<datosDia.getTrayectosCargados().get(i).listarHorarios().size(); j++){
                    if(datosDia.getTrayectosCargados().get(i).listarHorarios().get(j).getHoraSalida().equals(hora)){
                        //asientosDisponibles=datosDia.getTrayectosCargados().get(i).listarHorarios().get(j).getAsientosDisponibles();
                        if(datosDia.getTrayectosCargados().get(i).listarHorarios().get(j).comprobarDisponibilidad()){
                            datosDia.getTrayectosCargados().get(i).listarHorarios().get(j).actualizaAsientos(-1);
                        }else{
                            throw new RuntimeException("No quedan asientos disponibles para este viaje");
                        }
                    }
                }
            }
        }
        ListadoViajes l=new ListadoViajes(fecha, new Ciudad(origen), new Ciudad(destino), this.datos);
                //ListadoViajes l = new ListadoViajes(fecha, new Ciudad(origen), new Ciudad(destino), this.datos.getDatos());
        ArrayList<Viaje> viajes = new ArrayList<Viaje>();
        ArrayList<Viaje> posiblesviajes = new ArrayList<Viaje>();
        viajes  = l.getViajes();
        int i=0;
        while(i <viajes.size()){
            if(viajes.get(i).getTrayecto().getOrigen().getNombre().equals(origen) && viajes.get(i).getTrayecto().getDestino().getNombre().equals(destino))
            {
                posiblesviajes.add(viajes.get(i));
                ++i;
            }
            else{
                ++i;}
        }
        if (posiblesviajes.isEmpty()) {throw new IllegalArgumentException();}
        int viajeSeleccionado=0;
        boolean encontrado=false;
        Horario elegido;
        int indiceHorarioElegido=0;
        while(viajeSeleccionado<posiblesviajes.size() && !encontrado)
        {
        Trayecto t= posiblesviajes.get(viajeSeleccionado).getTrayecto();

                for(int j=0; j< t.listarHorarios().size(); j++){
                   Horario h=t.listarHorarios().get(j);
                   if(h.getHoraSalida().equals(hora) && h.getFecha().equals(fecha)){
                       encontrado=true;
                       indiceHorarioElegido=j;
                   }
               }
               if (!encontrado) {
                   ++viajeSeleccionado;}
        }
        Reserva reservaVacia = new Reserva(0,null,null, null);
        ObjectContainer db = DBUtils.getDb();
        ObjectSet result=db.queryByExample(reservaVacia);//devuelve todas las reservas
        elegido=posiblesviajes.get(viajeSeleccionado).getTrayecto().listarHorarios().get(indiceHorarioElegido);
        if(result.size()==0)
        {
            Reserva reserva = new Reserva(1,posiblesviajes.get(viajeSeleccionado),"0", elegido);
            db.store(reserva);
            return reserva.getIdReserva();
        }
        else
        {
            String codigo = ""+ result.size();
            Reserva reserva = new Reserva(1,posiblesviajes.get(viajeSeleccionado),codigo, elegido);
            db.store(reserva);
            return reserva.getIdReserva();
        }
    }

    public void cancelaReserva(String codigoReserva) {
        Reserva reserva = new Reserva(0,null,codigoReserva, null);
        ObjectContainer db = DBUtils.getDb();
        ObjectSet result=db.queryByExample(reserva);//devuelve todas las reservas
        if (result.isEmpty()){
            throw new RuntimeException("El código de reserva no existe");}
        else{
            while (result.hasNext()){
                Reserva reservaCancelada =  (Reserva)result.next();
                if(reservaCancelada.getIdReserva().equals(codigoReserva))
                {
                      reservaCancelada.getHorario().actualizaAsientos(reservaCancelada.getNumAsientos());
                      
                }
                    //aumento asiento
                    db.delete(reservaCancelada);
            }
            }
        
    }

    /**
     * Limpia la base de datos
     *
     * Sirve para no dejar datos inconsistentes en la base de datos
     */
    public void clear(){
        DBUtils.clear();
    }

    /**
     * Método consultor de datos.
     *
     * Consulta y devuelve una referencia del objeto Adaptador en el cual se basa el sistema, y el cual contiene los datos del mismo.
     *
     * @return objeto Adaptador con los datos del sistema.
     */
    public Adaptador getDatos(){
        return this.datos;
    }

    private LocalDate fecha;
    private Ciudad origen;
    private Ciudad destino;
    private LocalTime hora;
    private Tren tren;
    private int numAsientos;
    private Adaptador datos;
}
