
package org.uca.dss.curso1011.grupo4;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.uca.dss.trenes.interfazExtendido.InterfazCompras;
import org.uca.dss.trenes.basededatos.DBUtils;
import org.uca.dss.trenes.interfazExtendido.InterfazRepartoAsiento;
import org.uca.dss.trenes.interfazExtendido.InformacionTrayecto;
import org.uca.dss.trenes.interfazExtendido.Itinerario;
import org.uca.dss.trenes.interfazExtendido.ReservaTrayecto;
import org.uca.dss.trenes.interfazExtendido.InterfazVehiculo;
import java.util.Map;
import java.util.HashMap;

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
     * @param cVehiculo
     * @param numasientos int con el número de asientos que queremos reservar.
     * @param adaptador Objeto Adaptador que contiene los datos del sistema.
     */
    public GestionReservas(LocalDate cFecha, Ciudad cOrigen, Ciudad cDestino, LocalTime cHora, InterfazVehiculo cVehiculo, int numasientos, Adaptador adaptador ){
        this.fecha = cFecha;
        this.origen = cOrigen;
        this.destino = cDestino;
        this.hora = cHora;
        this.vehiculo = cVehiculo;
        this.numAsientos = numasientos;
        this.datos=adaptador;
        this.reservasRealizadas=new HashMap<String, Horario>();
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
        this.reservasRealizadas=new HashMap<String, Horario>();
    };

    
    public int asientosLibres(LocalDate fecha, Itinerario itinerario){
        /* Establecemos una variable con un valor muy alto. 10000 por ejemplo.*/
        int minNumAsiento=10000;
        /* Recorremos el itinerario.*/
        for (int i=0;i<itinerario.size();++i)
        {
            int numAsiento;
            InformacionTrayecto rt = itinerario.get(i);
            numAsiento=this.asientosLibres(rt.getOrigen(), rt.getDestino(), fecha, rt.getHoraSalida());
            /* Nos quedamos con el mínimo número de asientos libres de entre todos los trayectos del itinerario.*/
            if(numAsiento<minNumAsiento){minNumAsiento=numAsiento;}
        }
        return minNumAsiento;
    }

public int asientosLibres(String origen, String destino, LocalDate fecha, LocalTime hora){
       /* Obtenemos los datos para la fecha indicada.*/
       CargaDatos datosDia=this.datos.getDatosDia(fecha);
       int asientos=0;
       /* Creamos dos booleanos para controlar la existencia de las ciudades */
       boolean existeOrigen=false;
       boolean existeDestino=false;
       ArrayList<Trayecto> lista=datosDia.getTrayectosCargados();
       /* Buscamos el trayecto, y el horario dentro del trayecto, que corresponda.*/
       for(int i=0; i<lista.size(); i++){
           String origenAux=lista.get(i).getOrigen().getNombre();
           String destinoAux=lista.get(i).getDestino().getNombre();
           if(origenAux.equals(origen)){
               existeOrigen=true;
               if(origenAux.equals(origen) && destinoAux.equals(destino)){
                   existeDestino=true;
                   for(int j=0; j<lista.get(i).listarHorarios().size(); j++){
                        if(lista.get(i).listarHorarios().get(j).getHoraSalida().equals(hora)){
                            if(lista.get(i).listarHorarios().get(j).comprobarDisponibilidad()){
                                /* Obtenemos el número de asientos libres */
                                asientos=lista.get(i).listarHorarios().get(j).getAsientosDisponibles();
                                /* Establecemos el vehículo que se utiliza en el viaje, obtenido del horario.*/
                                this.vehiculo=lista.get(i).listarHorarios().get(j).getVehiculo();
                            }
                        }
                   }
                }
          }
       }
       /* Controlamos mediante excepciones la existencia del origen y el destino.*/
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
    public InterfazVehiculo getVehiculo() {
        return this.vehiculo;
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
        /* Creamos un listado de viajes.*/
        ListadoViajes l = new ListadoViajes(fecha, new Ciudad(origen), new Ciudad(destino), this.datos);
        ArrayList<Viaje> viajes = new ArrayList<Viaje>();
        /* Obtenemos los viajes del listado creado anteriormente.*/
        viajes  = l.getViajes();
        int i=0;
        precioViajes = 0;
        /* Recorremos la lista de viajes.*/
        while(i <viajes.size()){
            Trayecto auxTrayecto=new Trayecto(viajes.get(i).getTrayecto());
            Ciudad auxOrigen=new Ciudad(auxTrayecto.getOrigen());
            Ciudad auxDestino=new Ciudad(auxTrayecto.getDestino());
            if(auxOrigen.getNombre().equals(origen) && auxDestino.getNombre().equals(destino)){
                /* Obtenemos el precio.*/
                precioViajes = viajes.get(i).getTrayecto().getPrecio();}
            else{
                ++i;}
        }
        /*Devolvemos el precio.*/
        return precioViajes;
    
    }
    
    public List<ReservaTrayecto> reservaAsiento(Itinerario itinerario, LocalDate fecha){
        /* Si no hay reparto de asientos establecido, se lanza una excepción. */
        if (repartoAsiento == null)
            throw new RuntimeException("No hay estrategia de reparto de asientos seleccionada");
        List<ReservaTrayecto> listRT = new ArrayList<ReservaTrayecto> ();
        /*Establecemos el número de asiento a -2, que es un valor no válido.*/
        int numeroAsientoAnterior=-2;
        /* Recorremos el itinerario.*/
        for (int i=0;i<itinerario.size();++i)
        {
            InformacionTrayecto rt = itinerario.get(i);
	    /* Obtenemos un código de reserva para cada trayecto a reservar del itinerario.*/
            String codReserva =this.reservaAsiento(rt.getOrigen(),rt.getDestino(),fecha,rt.getHoraSalida());
            /* En la primera iteración, es decir, en el primer trayecto del itinerario, establecemos un número de asiento.*/
            if(numeroAsientoAnterior!=-2){
             if(!this.comprobarDisponibilidadAsiento(rt, fecha, numeroAsientoAnterior)){
                 while(!this.comprobarDisponibilidadAsiento(rt, fecha, numeroAsientoAnterior)){
                    numeroAsientoAnterior=repartoAsiento.reparteAsiento(this,rt.getOrigen(),rt.getDestino(),fecha,rt.getHoraSalida());
                 }
             }
            }else{
            /*En el resto de iteraciones, es decir, en el resto de trayectos del itinerario, intentaremos que se reserve el mismo número de asiento, si está disponible.*/
            numeroAsientoAnterior=repartoAsiento.reparteAsiento(this,rt.getOrigen(),rt.getDestino(),fecha,rt.getHoraSalida());
            while(!this.comprobarDisponibilidadAsiento(rt, fecha, numeroAsientoAnterior)){
                    numeroAsientoAnterior=repartoAsiento.reparteAsiento(this,rt.getOrigen(),rt.getDestino(),fecha,rt.getHoraSalida());
            }
            }
	    /* Creamos la reserva y la añadimos a nuestra lista de reservas.*/
            ReservaTrayecto reserva= new ReservaTrayecto(rt,fecha, numeroAsientoAnterior,codReserva);
            listRT.add(reserva);
            /* Almacenamos la reserva en la BD.*/
	    ObjectContainer db = DBUtils.getDb();
            db.store(reserva);
        }
        return listRT;
    }

    /**
     * Método para comprobar disponibilidad.
     *
     * Se encarga de comprobar la disponibilidad de un asiento con un número determinado, que se pasa por parámetro.
     * 
     * @param info información del trayecto para el que se quiere comprobar la disponibilidad del asiento.
     * @param fecha fecha para la que queremos comprobar la disponibilidad
     * @param numAsiento número del asiento que queremos comprobar.
     * @return booleano con true o false indicando si está disponible el asiento o no.
     */
    public boolean comprobarDisponibilidadAsiento(InformacionTrayecto info, LocalDate fecha, int numAsiento){
        /*Comenzamos pensando que el asiento está disponible.*/
        boolean disponible=true;
        /* Creamos una reserva con los datos dados, que nos servirá para realizar una consulta en la BD.*/
        ReservaTrayecto reserva = new ReservaTrayecto(info, fecha, numAsiento, null);
        ObjectContainer db = DBUtils.getDb();
        /* Realizamos la consulta en la BD.*/
        ObjectSet result=db.queryByExample(reserva);
        /* Si el resultado de la consulta no es vacío, el asiento estará ocupado, por tanto no disponible.*/
        if (!result.isEmpty()){
            disponible=false;
        }
        /* En el caso de asientos sin asignación, su número será -1, por tanto estará disponible.*/
        if(numAsiento==-1){
            disponible=true;
        }
        return disponible;
    }

    public String reservaAsiento(String origen, String destino, LocalDate fecha, LocalTime hora) {
        /*Primero obtenemos los datos para el día indicado.*/
        CargaDatos datosDia=this.datos.getDatosDia(fecha);
        ArrayList<Trayecto> lista=datosDia.getTrayectosCargados();
        for(int i=0; i<lista.size(); i++){
            String origenAux=lista.get(i).getOrigen().getNombre();
            String destinoAux=datosDia.getTrayectosCargados().get(i).getDestino().getNombre();
            if(origenAux.equals(origen) && destinoAux.equals(destino)){
                ArrayList<Horario> horarios=datosDia.getTrayectosCargados().get(i).listarHorarios();
                for(int j=0; j<horarios.size(); j++){
                    if(horarios.get(j).getHoraSalida().equals(hora)){
                        /* Una vez encontrado el trayecto y el horario, comprobamos su disponibilidad.*/
                        if(horarios.get(j).comprobarDisponibilidad()){
                            /*Decrementamos los asientos en 1.*/
                            horarios.get(j).actualizaAsientos(-1);
                            this.vehiculo=horarios.get(j).getVehiculo();
                        }else{
                            throw new RuntimeException("No quedan asientos disponibles para este viaje");
                        }
                    }
                }
            }
        }
        /*Creamos un listado de viajes, con los datos dados.*/
        ListadoViajes l=new ListadoViajes(fecha, new Ciudad(origen), new Ciudad(destino), this.datos);
        ArrayList<Viaje> viajes = new ArrayList<Viaje>();
        ArrayList<Viaje> posiblesviajes = new ArrayList<Viaje>();
        /*Obtenemos los viajes de dicho listado.*/
        viajes  = l.getViajes();
        int i=0;
        /*Recorremos todos los viajes, y los que coincidan con nuestros parámetros, los almacenamos como viajes posibles.*/
        while(i <viajes.size()){
            String origenViaje=viajes.get(i).getTrayecto().getOrigen().getNombre();
            String destinoViaje=viajes.get(i).getTrayecto().getDestino().getNombre();
            if(origenViaje.equals(origen) && destinoViaje.equals(destino))
            {
                posiblesviajes.add(viajes.get(i));
                ++i;
            }
            else{
                ++i;
            }
        }
        /* Si no encontramos viajes, lanzamos una excepción.*/
        if (posiblesviajes.isEmpty()) {
            throw new IllegalArgumentException();
        }
        int viajeSeleccionado=0;
        boolean encontrado=false;
        Horario elegido;
        int indiceHorarioElegido=0;
        /* Buscamos el viaje de entre los posibles viajes.*/
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
            ++viajeSeleccionado;
        }
        }
        /*Creamos una reserva vacía, para que a la hora de consultar la BD, nos devuelva todas las reservas existentes.*/
        ReservaTrayecto reservaVacia=new ReservaTrayecto(null, null, 0, null);
        ObjectContainer db = DBUtils.getDb();
        /*Consultamos la BD.*/
        ObjectSet result=db.queryByExample(reservaVacia);//devuelve todas las reservas
        elegido=posiblesviajes.get(viajeSeleccionado).getTrayecto().listarHorarios().get(indiceHorarioElegido);
        /*Si el resultado de la consulta es vacío, no existe ninguna reserva en la BD, introducimos '0' como código para la primera reserva.*/
        if(result.size()==0)
        {
            if(this.repartoAsiento == null){
                ReservaTrayecto reserva=new ReservaTrayecto(posiblesviajes.get(viajeSeleccionado).getInformacionTrayecto(), fecha, -1, "0");
                db.store(reserva);
    	    }

            this.reservasRealizadas.put("0", elegido);
            return "0";
        }
        else
        {
            String codigo = ""+ result.size();
            if(this.repartoAsiento==null){
                ReservaTrayecto reserva=new ReservaTrayecto(posiblesviajes.get(viajeSeleccionado).getInformacionTrayecto(), fecha, -1, codigo);
                db.store(reserva);
            }
            this.reservasRealizadas.put(codigo, elegido);
            return codigo;
        }
    }

    public void cancelaReserva(String codigoReserva) {
        /*Creamos una reserva, con dicho código, que nos servirá para buscar en la BD, la reserva.*/
        ReservaTrayecto reserva = new ReservaTrayecto(null, null, 0, codigoReserva);
        ObjectContainer db = DBUtils.getDb();
        /*Realizamos la consulta.*/
        ObjectSet result=db.queryByExample(reserva);
        /*Si no obtenemos nada, entonces no existe ninguna reserva en la BD con dicho código.*/
        if (result.isEmpty()){
            throw new RuntimeException("El código de reserva no existe");}
        else{
            /*Si obtenemos resultado, iremos mirando una por una las reservas obtenidas, hasta dar con la nuestra.*/
            while (result.hasNext()){
                ReservaTrayecto reservaCancelada =  (ReservaTrayecto)result.next();
                if(reservaCancelada.getCodigoReserva().equals(codigoReserva))
                {
                    /*Una vez encontrada la reserva, liberamos el asiento.*/
                    this.reservasRealizadas.get(codigoReserva).actualizaAsientos(1);
                }
                /*Eliminamos la reserva de la BD.*/
                db.delete(reservaCancelada);
            }
            }
    }
    /**
     * Cancela una reserva, dejando el asiento indicado libre
     *
     * @param reserva a cancelar
     */
    public void cancelaReserva(ReservaTrayecto reserva){
        this.cancelaReserva(reserva.getCodigoReserva());
    }
    
    public void cancelaReserva(List<ReservaTrayecto> reservas){
        /*Recorremos la lista de reservas y vamos cancelándolas una a una.*/
        for (int i=0;i<reservas.size();++i)
        {
            this.cancelaReserva(reservas.get(i).getCodigoReserva());
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
    
    public void setEstrategiaRepartoAsientos(InterfazRepartoAsiento reparto) throws CloneNotSupportedException {
        this.repartoAsiento = (InterfazRepartoAsiento) reparto;
    }

    /* Fecha de la reserva a gestionar.*/
    private LocalDate fecha;
    /* Ciudad origen del trayecto perteneciente a la reserva a gestionar.*/
    private Ciudad origen;
    /* Ciudad destino del trayecto perteneciente a la reserva a gestionar.*/
    private Ciudad destino;
    /* Hora de la reserva a gestionar.*/
    private LocalTime hora;
    /* Vehículo que recorrerá el trayecto perteneciente a la reserva a gestionar.*/
    private InterfazVehiculo vehiculo;
    /* Número de asientos reservados.*/
    private int numAsientos;
    /* Objeto adaptador de la clase al sistema, y que contiene todos los datos necesarios.*/
    private Adaptador datos;
    /* Contiene la forma de repartir los asientos para la reserva a gestionar.*/
    private InterfazRepartoAsiento repartoAsiento;
    /*Map con los horarios reservados y sus códigos de reserva.*/
    private Map<String, Horario> reservasRealizadas;
}
