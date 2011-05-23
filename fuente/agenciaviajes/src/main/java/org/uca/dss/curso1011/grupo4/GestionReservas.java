/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.curso1011.grupo4;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.uca.dss.curso1011.grupo4.interfaz.InterfazCompras;
import org.uca.dss.trenes.basededatos.DBUtils;


/**
 * Genera y gestiona una Reserva.
 *
 * Clase encargada de generar y gestionar una reserva determinada, para un viaje de nuestro sistema. Ser� la encargada de interaccionar con el usuario a la hora de realizar la reserva.
 *
 * @author migue
 */
public class GestionReservas implements InterfazCompras{
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
    public GestionReservas(LocalDate cFecha, Ciudad cOrigen, Ciudad cDestino, LocalTime cHora, Tren cTren, int numasientos, String trenes, String trayectos ){
        this.fecha = cFecha;
        this.origen = cOrigen;
        this.destino = cDestino;
        this.hora = cHora;
        this.tren = cTren;
        this.numAsientos = numasientos;
        DBUtils.initDataBase("./src/main/resources/reservas.yap");
        CargaDatos datosprevios= new CargaDatos(trayectos, trenes);
        this.datos= new AdaptadorListado(datosprevios);

        // Trayecto trayecto=new Trayecto();
        //Viaje viaje=new Viaje(this.fecha, trayecto);
        //this.reserva= new Reserva(numasientos, viaje);
    };

    public GestionReservas(String trenes, String trayectos){
        CargaDatos datosprevios= new CargaDatos(trayectos, trenes);
        this.datos= new AdaptadorListado(datosprevios);
        //DBUtils.initDataBase("./src/main/resources/reservas.yap");
    };

   public int asientosLibres(String origen, String destino, LocalDate fecha, LocalTime hora){
       Trayecto t;
       int resultado=-1;
       int[] numTrayecto=new int[5];
       int indiceTrayecto=0;
       boolean or=false;
       boolean des=false;
       ArrayList<Trayecto> trayectos = this.datos.getDatos().getTrayectosCargados();
       for(int i=0; i<trayectos.size(); i++){
           Ciudad orig=new Ciudad(this.datos.getDatos().getTrayectosCargados().get(i).getOrigen());
           //System.out.println(this.datos.getDatos().getTrayectosCargados().get(i).getOrigen());
           if(orig.getNombre().equals(origen)){
               or=true;
           }
           Ciudad dest=new Ciudad(this.datos.getDatos().getTrayectosCargados().get(i).getDestino());
           if(dest.getNombre().equals(destino)){
               des=true;
           }
           if(orig.getNombre().equals(origen) && dest.getNombre().equals(destino)){
               //Horario salida = new Horario(trayectos.get(i).getHorarioElegido());
               //if (salida.getHoraSalida().equals(hora))
                    numTrayecto[indiceTrayecto]=i;
                    ++indiceTrayecto;
           }
       }
       
       if(!or){
           throw new IllegalArgumentException("Error: La ciudad origen no existe");
       }else{
           if(!des){
               throw new IllegalArgumentException("Error: La ciudad destino no existe");
           }else{
               for(int i=0;i<indiceTrayecto;++i)
               {
                t=new Trayecto(trayectos.get(numTrayecto[i]));

                for(int j=0; j< t.listarHorarios().size(); j++){
                   Horario h=new Horario(t.listarHorarios().get(j));
                   if(h.getHoraSalida().equals(hora)){
                       
                       resultado= h.getAsientosDisponibles();
                   }
                }
               }
           }
       }
       
       return resultado;
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
     * @return the numAsientos
     */
    public int getNumAsientos() {
        return numAsientos;
    }

    /**
     * Método consultor del precio.
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
        viajes  = l.getViajes();
        int i=0;
        precioViajes = 0;
        while(i <viajes.size()){
            Trayecto auxTrayecto=new Trayecto(viajes.get(i).getTrayecto());
            Ciudad auxOrigen=new Ciudad(auxTrayecto.getOrigen());
            Ciudad auxDestino=new Ciudad(auxTrayecto.getDestino());
            if(auxOrigen.getNombre().equals(origen) && auxDestino.getNombre().equals(destino))
                precioViajes = viajes.get(i).getTrayecto().getPrecio();
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
        ListadoViajes l= new ListadoViajes(this.datos.getListado(origen, destino, fecha));
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
            else
                ++i;
        }
        if (posiblesviajes.isEmpty()) throw new IllegalArgumentException();
        // una guia de dB4o: http://www.programacion.com/articulo/persistencia_de_objetos_java_utilizando_db4o_308#4_ejemplo
        //Para grabar datos parece que hay que usar lo siguiente
        //ObjectContainer db = Db4o.openFile("./src/main/resources/reservas.yap");
        int viajeSeleccionado=0;
        boolean encontrado=false;
        while(viajeSeleccionado<posiblesviajes.size() && !encontrado)
        {
        Trayecto t= posiblesviajes.get(viajeSeleccionado).getTrayecto();
               for(int j=0; j< t.listarHorarios().size(); j++){
                   Horario h=t.listarHorarios().get(j);
                   if(h.getHoraSalida().equals(hora)){
                       encontrado=true;
                       if(h.comprobarDisponibilidad())
                           h.actualizaAsientos(-1);
                       else
                           throw new RuntimeException("No quedan asientos disponibles");
                   }
               }
               ++viajeSeleccionado;
        }
        Reserva reservaVacia = new Reserva(0,null,null);
        ObjectContainer db = DBUtils.getDb();
        ObjectSet result=db.queryByExample(reservaVacia);//devuelve todas las reservas
        if(result.size()==0)
        {
            Reserva reserva = new Reserva(1,posiblesviajes.get(viajeSeleccionado),"0");
            db.store(reserva);
            return reserva.getIdReserva();
        }
        else
        {
            String codigo = ""+ result.size();
            Reserva reserva = new Reserva(1,posiblesviajes.get(viajeSeleccionado),codigo);
            db.store(reserva);
            return reserva.getIdReserva();
        }
        //disminuir las plazas del tren
    }
    /**
     * Cancela una reserva.
     *
     * Dado un código de reserva, cancela la reserva si existe liberando los asientes del tren de dicho viaje.
     *
     * @param codigoReserva de la reserva a cancelar
     */
    public void cancelaReserva(String codigoReserva) {
        Reserva reserva = new Reserva(0,null,codigoReserva);
        ObjectContainer db = DBUtils.getDb();
        ObjectSet result=db.queryByExample(reserva);//devuelve todas las reservas
        if (result.isEmpty()) throw new RuntimeException("El código de reserva no existe");
        else{
            Reserva reservaCancelada =  (Reserva)result.next();
            reservaCancelada.getViaje().getTrayecto().getHorarioElegido().actualizaAsientos(reservaCancelada.getNumAsientos());//aumento asiento
            db.delete(reservaCancelada);
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

    public AdaptadorListado getDatos(){
        return this.datos;
    }

    private LocalDate fecha;
    private Ciudad origen;
    private Ciudad destino;
    private LocalTime hora;
    private Tren tren;
    private int numAsientos;
    private AdaptadorListado datos;
    //private ListadoViajes listado;
    //private DBUtils db = new DBUtils();
   // private Reserva reserva;
    
}