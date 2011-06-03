/*
 *  Copyright (C) 2011 Manuel Jesús de la Calle Brihuega
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.uca.dss.curso1011.grupo4;

import org.uca.dss.trenes.interfazExtendido.InterfazListados;
import org.uca.dss.trenes.interfazExtendido.Itinerario;
import org.uca.dss.trenes.interfazExtendido.InformacionTrayecto;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalTime;
import org.joda.time.LocalDate;
import java.util.LinkedList;

/**
 * Clase adaptadora de la interfaz.
 *
 * Se encarga de implementar la funcionalidad de la interfaz 'InterfazListados', en otras palabras, adapta la interfaz a la clase de gestión de listados. Además es la clase central del sistema, de la que parten todos los datos a utilizar por el mismo, ya que guarda internamente una lista de objetos CargaDatos, con los datos cargados para cada día.
 *
 * @author Manuel Jesús de la Calle Brihuega
 */

public class Adaptador implements InterfazListados {

    /* string con la ruta del fichero donde se encuentran los vehículos. */
    private String rutaVehiculos;
    /* string con la ruta del fichero donde se encuentran los trayectos. */
    private String rutaTrayectos;
    /* Lista de objetos CargaDatos. Cada objeto contiene los datos necesarios para una fecha determinada */
    private List<CargaDatos> listaDatos;

    /* Constructor */

    /**
     * Construye un objeto de la clase Adaptador
     *
     * Recibe de parámetros de entrada las rutas de los archivos CSV con los trayectos y trenes que utilizará el sistema e inicializa internamente la lista de CargaDatos.
     *
     * @param vehiculos ruta del fichero csv que contiene los vehículos
     * @param trayectos ruta del fichero csv que contiene los trayectos
     */
    public Adaptador(String vehiculos, String trayectos){
        this.rutaTrayectos=trayectos;
        this.rutaVehiculos=vehiculos;
        this.listaDatos=new LinkedList();
    }

    /**
     * Método modificador
     *
     * Genera los datos (el objeto CargaDatos) correspondientes al día introducido por parámetro. Si ya existen cargados, no realiza ninguna acción.
     *
     * @param dia LocalDate con la fecha para la cual queremos cargar los datos.
     */
    public void generarDatosDia(LocalDate dia){
        /* controlamos mediante un booleano, si existen datos cargados para el día dado. */
        boolean existenDatos=false;
        /* buscamos por la lista, si existen datos cargados para el día dado. */
        for(int i=0; i<this.listaDatos.size(); i++){
            if(this.listaDatos.get(i).getFecha().equals(dia)){
                existenDatos=true;
            }
        }
        /* En el caso de que no existan, generamos los datos para dicho día. */
        if(!existenDatos){
            CargaDatos datos=new CargaDatos(this.rutaTrayectos, this.rutaVehiculos, dia);
            this.listaDatos.add(datos);
        }
    }


    //Métodos Get

    /**
     * Método consultor
     *
     * Devuelve los datos cargados para la fecha que recibe por parámetro. Internamente, si descubre que para esa fecha no existen datos cargados, los carga.
     *
     * @param dia LocalDate con la fecha para la cual queremos obtener los datos cargados.
     *
     * @return Un objeto CargaDatos con los datos cargados para el día indicado por parámetro.
     */
    public CargaDatos getDatosDia(LocalDate dia){
        CargaDatos datos;
        int indice=0;
        /* llamamos a la función de generación de datos, que los generará para tal día si no existen ya.*/
        this.generarDatosDia(dia);
        /* buscamos en la lista, hasta que coincida la fecha, y los devolvemos.*/
        for(int j=0; j<this.listaDatos.size(); j++){
            if(this.listaDatos.get(j).getFecha().equals(dia)){
                indice=j;
            }
        }
        return this.listaDatos.get(indice);
 }


    public List<LocalTime> getHorarios(String origen, String destino, LocalDate fecha){
        int indice=0;
        /* buscamos primero los datos para la fecha indicada, en nuestro listado de datos.*/
        CargaDatos datos=this.getDatosDia(fecha);
        List<LocalTime> listadoSalidas=new LinkedList<LocalTime>();
        /* Obtenemos todos los trayectos. */
        ArrayList<Trayecto> listaTrayectos=datos.getTrayectosCargados();
        /* Buscamos todos los trayectos que coincidan con nuestro origen y detsino y guardamos las horas de salida de los mismos. */
        for(int i=0; i<listaTrayectos.size();i++){
            if(listaTrayectos.get(i).getOrigen().getNombre().equals(origen) && listaTrayectos.get(i).getDestino().getNombre().equals(destino)){
                    for(int j=0; j<listaTrayectos.get(i).listarHorarios().size(); j++){
                        if(listaTrayectos.get(i).listarHorarios().get(j).comprobarDisponibilidad()){
                            if(!listadoSalidas.add(listaTrayectos.get(i).listarHorarios().get(j).getHoraSalida())){
                                throw new RuntimeException("Error al crear el listado");
                            }
                        }
                    }
                }
            }
        /* devolvemos la lista con las horas de salida.*/
        return listadoSalidas;
    }

    public List<Itinerario> getItinerarios(String origen, String destino, LocalDate fechaSalida){
        /* Esta lista contendrá la información de todos los trayectos cuyo origen coincide con el solicitado.*/
        List<InformacionTrayecto> trayectosOrigen=new LinkedList<InformacionTrayecto>();
        /* Esta lista contendrá la información de todos los trayectos cuyo destino coincide con el solicitado.*/
        List<InformacionTrayecto> trayectosDestino=new LinkedList<InformacionTrayecto>();
        /* Esta lista contendrá los itinerarios solicitados, los cuales devolveremos.*/
        List<Itinerario> listaItinerarios=new LinkedList<Itinerario>();
        /* Listas auxiliares que nos ayudarán a acortar rutas de búsqueda.*/
        ArrayList<Trayecto> listaTrayectos;
        ArrayList<Horario> listaHorariosOrigen;
        ArrayList<Horario> listaHorariosDestino;

        /* buscamos primero los datos para la fecha indicada, en nuestro listado de datos.*/
        CargaDatos datosNecesarios=this.getDatosDia(fechaSalida);
            if(datosNecesarios.getFecha().equals(fechaSalida)){
                listaTrayectos=datosNecesarios.getTrayectosCargados();
                /* Buscamos todos los trayectos cuyo origen coincida con nuestro origen. */
                for(int j=0; j<listaTrayectos.size(); j++){
                    if(listaTrayectos.get(j).getOrigen().getNombre().equals(origen)){
                        listaHorariosOrigen=listaTrayectos.get(j).listarHorarios();
                        for(int k=0; k<listaTrayectos.get(j).listarHorarios().size(); k++){
                            if(listaHorariosOrigen.get(k).comprobarDisponibilidad()){
                                /* Una vez encontrado el trayecto, creamos su objeto de información y lo almacenamos en una lista. */
                                InformacionTrayecto info=new InformacionTrayecto(listaTrayectos.get(j).getOrigen().getNombre(), listaTrayectos.get(j).getDestino().getNombre(), listaHorariosOrigen.get(k).getHoraSalida(), listaHorariosOrigen.get(k).getHoraLlegada(), listaHorariosOrigen.get(k).getPrecioHorario());
                                if(!trayectosOrigen.add(info)){
                                    throw new RuntimeException("Error al buscar trayectos");
                                }

                            }
                        }
                    }
                }
            }
        
            if(datosNecesarios.getFecha().equals(fechaSalida)){
                listaTrayectos=datosNecesarios.getTrayectosCargados();
                /* Buscamos todos los trayectos cuyo destino coincida con nuestro destino. */
                for(int m=0; m< listaTrayectos.size(); m++){
                    if(listaTrayectos.get(m).getDestino().getNombre().equals(destino)){
                        listaHorariosDestino=listaTrayectos.get(m).listarHorarios();
                        for(int n=0; n<listaHorariosDestino.size(); n++){
                            if(listaHorariosDestino.get(n).comprobarDisponibilidad()){
                                /* Una vez encontrado el trayecto, creamos su objeto de información y lo almacenamos en una lista. */
                                InformacionTrayecto info=new InformacionTrayecto(listaTrayectos.get(m).getOrigen().getNombre(), listaTrayectos.get(m).getDestino().getNombre(), listaHorariosDestino.get(n).getHoraSalida(), listaHorariosDestino.get(n).getHoraLlegada(), listaHorariosDestino.get(n).getPrecioHorario());
                                if(!trayectosDestino.add(info)){
                                    throw new RuntimeException("Error al buscar trayectos");
                                }

                            }
                        }
                    }
                }
            }

        /* buscamos en la lista de trayectos de origen, los que su destino coincida con el origen de alguno de los trayectos de destino.*/
        for(int p=0; p< trayectosOrigen.size(); p++){
            for(int q=0; q<trayectosDestino.size(); q++){
                if(trayectosOrigen.get(p).getDestino().equals(trayectosDestino.get(q).getOrigen())){
                    /* una vez encontrado los trayectos coincidentes, comprobamos que la hora de salida del segundo sea igual o posterior a la hora de llegada del primero más 10 minutos.*/
                    LocalTime hora=trayectosOrigen.get(p).getHoraLlegada().plusMinutes(10);
                    if(trayectosDestino.get(q).getHoraSalida().isAfter(hora) || trayectosDestino.get(q).getHoraSalida().equals(hora)){
                        Itinerario itinerario = new ItinerarioViaje();
                        itinerario.add(trayectosOrigen.get(p));
                        itinerario.add(trayectosDestino.get(q));
                        listaItinerarios.add(itinerario);
                    }
                }
            }
        }
        /* buscamos en la lista de trayectos de origen, si existe algun trayecto directo que no requiera de trasbordo.*/
        for(int u=0; u<trayectosOrigen.size(); u++){
            if(trayectosOrigen.get(u).getDestino().equals(destino)){
                    Itinerario itinerario=new ItinerarioViaje();
                    itinerario.add(trayectosOrigen.get(u));
                    listaItinerarios.add(itinerario);
            }

        }
        /*Devolvemos la lista con los itinerarios recopilados.*/
        return listaItinerarios;

    }

    public List<Itinerario> getItinerariosEntre(String origen, String destino, LocalDate fechaSalida, LocalTime horaSalida, LocalTime horaLlegada){
        /* Esta lista contendrá la información de todos los trayectos cuyo origen coincide con el solicitado.*/
        List<InformacionTrayecto> trayectosOrigen=new LinkedList<InformacionTrayecto>();
        /* Esta lista contendrá la información de todos los trayectos cuyo destino coincide con el solicitado.*/
        List<InformacionTrayecto> trayectosDestino=new LinkedList<InformacionTrayecto>();
        /* Esta lista contendrá los itinerarios solicitados, los cuales devolveremos.*/
        List<Itinerario> listaItinerarios=new LinkedList<Itinerario>();
        /* Listas auxiliares que nos ayudarán a acortar rutas de búsqueda.*/
        ArrayList<Trayecto> listaTrayectos;
        ArrayList<Horario> listaHorariosOrigen;
        ArrayList<Horario> listaHorariosDestino;

        /* buscamos primero los datos para la fecha indicada, en nuestro listado de datos.*/
        CargaDatos datosNecesarios=this.getDatosDia(fechaSalida);
        if(datosNecesarios.getFecha().equals(fechaSalida)){
            listaTrayectos=datosNecesarios.getTrayectosCargados();
            /* Buscamos todos los trayectos cuyo origen coincida con nuestro origen. */
            for(int j=0; j<listaTrayectos.size(); j++){
                    if(listaTrayectos.get(j).getOrigen().getNombre().equals(origen)){
                        listaHorariosOrigen=listaTrayectos.get(j).listarHorarios();
                        for(int k=0; k<listaHorariosOrigen.size(); k++){
                            /*Comprobamos si el horario está disponible. */
                            if(listaHorariosOrigen.get(k).comprobarDisponibilidad()){
                                /* Antes de elegir el trayecto, debemos de validar sus requisitos horarios.*/
                                if(listaHorariosOrigen.get(k).getHoraSalida().equals(horaSalida) || (listaHorariosOrigen.get(k).getHoraSalida().isAfter(horaSalida) && (listaHorariosOrigen.get(k).getHoraLlegada().isBefore(horaLlegada) || listaHorariosOrigen.get(k).getHoraLlegada().equals(horaLlegada)))){
                                    /* Una vez encontrado el trayecto, creamos su objeto de información y lo almacenamos en una lista. */
                                    InformacionTrayecto info=new InformacionTrayecto(listaTrayectos.get(j).getOrigen().getNombre(), listaTrayectos.get(j).getDestino().getNombre(), listaHorariosOrigen.get(k).getHoraSalida(), listaHorariosOrigen.get(k).getHoraLlegada(), listaHorariosOrigen.get(k).getPrecioHorario());
                                    if(!trayectosOrigen.add(info)){
                                        throw new RuntimeException("Error al buscar trayectos");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        

            if(datosNecesarios.getFecha().equals(fechaSalida)){
                listaTrayectos=datosNecesarios.getTrayectosCargados();
                /* Buscamos todos los trayectos cuyo destino coincida con nuestro destino. */
                for(int m=0; m<listaTrayectos.size(); m++){
                    if(listaTrayectos.get(m).getDestino().getNombre().equals(destino)){
                        listaHorariosDestino=listaTrayectos.get(m).listarHorarios();
                        for(int n=0; n<listaHorariosDestino.size(); n++){
                            /*Comprobamos si el horario está disponible. */
                            if(listaHorariosDestino.get(n).comprobarDisponibilidad()){
                                /* Antes de elegir el trayecto, debemos de validar sus requisitos horarios.*/
                                LocalTime horaSalida2=listaHorariosDestino.get(n).getHoraSalida();
                                LocalTime horaLlegada2=listaHorariosDestino.get(n).getHoraLlegada();
                                if(horaSalida2.equals(horaSalida) || (horaSalida2.isAfter(horaSalida) && (horaLlegada2.isBefore(horaLlegada) || horaLlegada2.equals(horaLlegada)))){
                                    /* Una vez encontrado el trayecto, creamos su objeto de información y lo almacenamos en una lista. */
                                    InformacionTrayecto info=new InformacionTrayecto(listaTrayectos.get(m).getOrigen().getNombre(), listaTrayectos.get(m).getDestino().getNombre(), horaSalida2, horaLlegada2, listaHorariosDestino.get(n).getPrecioHorario());
                                    if(!trayectosDestino.add(info)){
                                        throw new RuntimeException("Error al buscar trayectos");
                                    }
                                 }
                            }
                        }
                    }
                }
            }

        /* buscamos en la lista de trayectos de origen, los que su destino coincida con el origen de alguno de los trayectos de destino.*/
        for(int p=0; p< trayectosOrigen.size(); p++){
            for(int q=0; q<trayectosDestino.size(); q++){
                if(trayectosOrigen.get(p).getDestino().equals(trayectosDestino.get(q).getOrigen())){
                    LocalTime hora=trayectosOrigen.get(p).getHoraLlegada().plusMinutes(10);
                    /* una vez encontrado los trayectos coincidentes, comprobamos que la hora de salida del segundo sea igual o posterior a la hora de llegada del primero más 10 minutos.*/
                    if(trayectosDestino.get(q).getHoraSalida().isAfter(hora) || trayectosDestino.get(q).getHoraSalida().equals(hora)){
                        Itinerario itinerario = new ItinerarioViaje();
                        itinerario.add(trayectosOrigen.get(p));
                        itinerario.add(trayectosDestino.get(q));
                        listaItinerarios.add(itinerario);
                    }
                }
            }
        }
        /* buscamos en la lista de trayectos de origen, si existe algun trayecto directo que no requiera de trasbordo.*/
        for(int u=0; u<trayectosOrigen.size(); u++){
            if(trayectosOrigen.get(u).getDestino().equals(destino)){
                if(trayectosOrigen.get(u).getHoraLlegada().isBefore(horaLlegada) || trayectosOrigen.get(u).getHoraLlegada().equals(horaLlegada)){
                    Itinerario itinerario=new ItinerarioViaje();
                    itinerario.add(trayectosOrigen.get(u));
                    listaItinerarios.add(itinerario);
                }

            }

        }
        /* Devolvemos los itinerarios.*/
        return listaItinerarios;
    }
}