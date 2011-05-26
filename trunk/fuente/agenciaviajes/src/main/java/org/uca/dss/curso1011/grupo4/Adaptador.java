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

import org.uca.dss.curso1011.grupo4.interfazExtendido.InterfazListados;
import org.uca.dss.curso1011.grupo4.interfazExtendido.Itinerario;
import org.uca.dss.curso1011.grupo4.interfazExtendido.InformacionTrayecto;
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

    private String rutaTrenes;
    private String rutaTrayectos;
    private List<CargaDatos> listaDatos;

    //Constructor
    /**
     * Construye un objeto de la clase Adaptador
     *
     * Recibe de parámetros de entrada las rutas de los archivos CSV con los trayectos y trenes que utilizará el sistema e inicializa internamente la lista de CargaDatos.
     *
     * @param Trenes String con la ruta del archivo CSV que contiene los trenes a cargar.
     * @param Trayectos String con la ruta del archivo CSV que contiene los trayectos a cargar.
     */
    public Adaptador(String Trenes, String Trayectos){
        this.rutaTrayectos=Trayectos;
        this.rutaTrenes=Trenes;
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
        boolean existenDatos=false;
        for(int i=0; i<this.listaDatos.size(); i++){
            if(this.listaDatos.get(i).getFecha().equals(dia)){
                existenDatos=true;
            }
        }
        if(!existenDatos){
            CargaDatos datos=new CargaDatos(this.rutaTrayectos, this.rutaTrenes, dia);
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
        boolean existe=false;
        int indice=0;
        for(int i=0; i<this.listaDatos.size(); i++){
            if(this.listaDatos.get(i).getFecha().equals(dia)){
                existe=true;
                indice=i;
            }
        }
        if(!existe){
            this.generarDatosDia(dia);
            for(int j=0; j<this.listaDatos.size(); j++){
                if(this.listaDatos.get(j).getFecha().equals(dia)){
                    existe=true;
                    indice=j;
                }
            }
        }
        datos=this.listaDatos.get(indice);
        return datos;
    }

    
    public List<LocalTime> getHorarios(String origen, String destino, LocalDate fecha){
        int indice=0;
        boolean existe=false;
        for(int c=0; c<this.listaDatos.size(); c++){
            if(this.listaDatos.get(c).getFecha().equals(fecha)){
                existe=true;
                indice=c;
            }
        }
        List<LocalTime> listadoSalidas=new LinkedList<LocalTime>();
        if(!existe){
            this.generarDatosDia(fecha);
        }

            ArrayList<Trayecto> listaTrayectos=this.listaDatos.get(indice).getTrayectosCargados();
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
        return listadoSalidas;
    }

    public List<Itinerario> getItinerarios(String origen, String destino, LocalDate fechaSalida){
        List<InformacionTrayecto> trayectosOrigen=new LinkedList<InformacionTrayecto>();
        List<InformacionTrayecto> trayectosDestino=new LinkedList<InformacionTrayecto>();
        List<Itinerario> listaItinerarios=new LinkedList<Itinerario>();
        for(int i=0; i<this.listaDatos.size(); i++){
            if(this.listaDatos.get(i).getFecha().equals(fechaSalida)){
                for(int j=0; j<this.listaDatos.get(i).getTrayectosCargados().size(); j++){
                    if(this.listaDatos.get(i).getTrayectosCargados().get(j).getOrigen().getNombre().equals(origen)){
                        for(int k=0; k<this.listaDatos.get(i).getTrayectosCargados().get(j).listarHorarios().size(); k++){
                            if(this.listaDatos.get(i).getTrayectosCargados().get(j).listarHorarios().get(k).comprobarDisponibilidad()){
                                InformacionTrayecto info=new InformacionTrayecto(this.listaDatos.get(i).getTrayectosCargados().get(j).getOrigen().getNombre(), this.listaDatos.get(i).getTrayectosCargados().get(j).getDestino().getNombre(), this.listaDatos.get(i).getTrayectosCargados().get(j).listarHorarios().get(k).getHoraSalida(), this.listaDatos.get(i).getTrayectosCargados().get(j).listarHorarios().get(k).getHoraLlegada(), this.listaDatos.get(i).getTrayectosCargados().get(j).listarHorarios().get(k).getPrecioHorario());
                                if(!trayectosOrigen.add(info)){
                                    throw new RuntimeException("Error al buscar trayectos");
                                }

                            }
                        }
                    }
                }
            }
        }
        for(int l=0; l<this.listaDatos.size(); l++){
            if(this.listaDatos.get(l).getFecha().equals(fechaSalida)){
                for(int m=0; m<this.listaDatos.get(l).getTrayectosCargados().size(); m++){
                    if(this.listaDatos.get(l).getTrayectosCargados().get(m).getDestino().getNombre().equals(destino)){
                        for(int n=0; n<this.listaDatos.get(l).getTrayectosCargados().get(m).listarHorarios().size(); n++){
                            if(this.listaDatos.get(l).getTrayectosCargados().get(m).listarHorarios().get(n).comprobarDisponibilidad()){
                                InformacionTrayecto info=new InformacionTrayecto(this.listaDatos.get(l).getTrayectosCargados().get(m).getOrigen().getNombre(), this.listaDatos.get(l).getTrayectosCargados().get(m).getDestino().getNombre(), this.listaDatos.get(l).getTrayectosCargados().get(m).listarHorarios().get(n).getHoraSalida(), this.listaDatos.get(l).getTrayectosCargados().get(m).listarHorarios().get(n).getHoraLlegada(), this.listaDatos.get(l).getTrayectosCargados().get(m).listarHorarios().get(n).getPrecioHorario());
                                if(!trayectosDestino.add(info)){
                                    throw new RuntimeException("Error al buscar trayectos");
                                }

                            }
                        }
                    }
                }
            }
        }
        //Ahora tenemos trayectosOrigen y trayectosDestino
        for(int p=0; p< trayectosOrigen.size(); p++){
            for(int q=0; q<trayectosDestino.size(); q++){
                if(trayectosOrigen.get(p).getDestino().equals(trayectosDestino.get(q).getOrigen())){
                        Itinerario itinerario = null;
                        itinerario.add(trayectosOrigen.get(p));
                        itinerario.add(trayectosDestino.get(q));
                        listaItinerarios.add(itinerario);
                }
            }
        }

        for(int u=0; u<trayectosOrigen.size(); u++){
            if(trayectosOrigen.get(u).getDestino().equals(destino)){
                    Itinerario itinerario=null;
                    itinerario.add(trayectosOrigen.get(u));
                    listaItinerarios.add(itinerario);
            }

        }
        return listaItinerarios;

    }

    public List<Itinerario> getItinerariosEntre(String origen, String destino, LocalDate fechaSalida, LocalTime horaSalida, LocalTime horaLlegada){
        List<InformacionTrayecto> trayectosOrigen=new LinkedList<InformacionTrayecto>();
        List<InformacionTrayecto> trayectosDestino=new LinkedList<InformacionTrayecto>();
        List<Itinerario> listaItinerarios=new LinkedList<Itinerario>();
        for(int i=0; i<this.listaDatos.size(); i++){
            if(this.listaDatos.get(i).getFecha().equals(fechaSalida)){
                for(int j=0; j<this.listaDatos.get(i).getTrayectosCargados().size(); j++){
                    if(this.listaDatos.get(i).getTrayectosCargados().get(j).getOrigen().getNombre().equals(origen)){
                        for(int k=0; k<this.listaDatos.get(i).getTrayectosCargados().get(j).listarHorarios().size(); k++){
                            if(this.listaDatos.get(i).getTrayectosCargados().get(j).listarHorarios().get(k).comprobarDisponibilidad()){

                                if(this.listaDatos.get(i).getTrayectosCargados().get(j).listarHorarios().get(k).getHoraSalida().equals(horaSalida) || (this.listaDatos.get(i).getTrayectosCargados().get(j).listarHorarios().get(k).getHoraSalida().isAfter(horaSalida) && (this.listaDatos.get(i).getTrayectosCargados().get(j).listarHorarios().get(k).getHoraLlegada().isBefore(horaLlegada) || this.listaDatos.get(i).getTrayectosCargados().get(j).listarHorarios().get(k).getHoraLlegada().equals(horaLlegada)))){
                                    InformacionTrayecto info=new InformacionTrayecto(this.listaDatos.get(i).getTrayectosCargados().get(j).getOrigen().getNombre(), this.listaDatos.get(i).getTrayectosCargados().get(j).getDestino().getNombre(), this.listaDatos.get(i).getTrayectosCargados().get(j).listarHorarios().get(k).getHoraSalida(), this.listaDatos.get(i).getTrayectosCargados().get(j).listarHorarios().get(k).getHoraLlegada(), this.listaDatos.get(i).getTrayectosCargados().get(j).listarHorarios().get(k).getPrecioHorario());
                                    if(!trayectosOrigen.add(info)){
                                        throw new RuntimeException("Error al buscar trayectos");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for(int l=0; l<this.listaDatos.size(); l++){
            if(this.listaDatos.get(l).getFecha().equals(fechaSalida)){
                for(int m=0; m<this.listaDatos.get(l).getTrayectosCargados().size(); m++){
                    if(this.listaDatos.get(l).getTrayectosCargados().get(m).getDestino().getNombre().equals(destino)){
                        for(int n=0; n<this.listaDatos.get(l).getTrayectosCargados().get(m).listarHorarios().size(); n++){
                            if(this.listaDatos.get(l).getTrayectosCargados().get(m).listarHorarios().get(n).comprobarDisponibilidad()){
                                 if(this.listaDatos.get(l).getTrayectosCargados().get(m).listarHorarios().get(n).getHoraSalida().equals(horaSalida) || (this.listaDatos.get(l).getTrayectosCargados().get(m).listarHorarios().get(n).getHoraSalida().isAfter(horaSalida) && (this.listaDatos.get(l).getTrayectosCargados().get(m).listarHorarios().get(n).getHoraLlegada().isBefore(horaLlegada) || this.listaDatos.get(l).getTrayectosCargados().get(m).listarHorarios().get(n).getHoraLlegada().equals(horaLlegada)))){

                                    InformacionTrayecto info=new InformacionTrayecto(this.listaDatos.get(l).getTrayectosCargados().get(m).getOrigen().getNombre(), this.listaDatos.get(l).getTrayectosCargados().get(m).getDestino().getNombre(), this.listaDatos.get(l).getTrayectosCargados().get(m).listarHorarios().get(n).getHoraSalida(), this.listaDatos.get(l).getTrayectosCargados().get(m).listarHorarios().get(n).getHoraLlegada(), this.listaDatos.get(l).getTrayectosCargados().get(m).listarHorarios().get(n).getPrecioHorario());
                                    if(!trayectosDestino.add(info)){
                                        throw new RuntimeException("Error al buscar trayectos");
                                    }
                                 }
                            }
                        }
                    }
                }
            }
        }
        //Ahora tenemos trayectosOrigen y trayectosDestino
        for(int p=0; p< trayectosOrigen.size(); p++){
            for(int q=0; q<trayectosDestino.size(); q++){
                if(trayectosOrigen.get(p).getDestino().equals(trayectosDestino.get(q).getOrigen())){
                    LocalTime hora=trayectosOrigen.get(p).getHoraLlegada().plusMinutes(10);
                    if(trayectosDestino.get(q).getHoraSalida().isAfter(hora) || trayectosDestino.get(q).getHoraSalida().equals(hora)){
                        Itinerario itinerario = null;
                        itinerario.add(trayectosOrigen.get(p));
                        itinerario.add(trayectosDestino.get(q));
                        listaItinerarios.add(itinerario);
                    }
                }
            }
        }

        for(int u=0; u<trayectosOrigen.size(); u++){
            if(trayectosOrigen.get(u).getDestino().equals(destino)){
                if(trayectosOrigen.get(u).getHoraLlegada().isBefore(horaLlegada) || trayectosOrigen.get(u).getHoraLlegada().equals(horaLlegada)){
                    Itinerario itinerario=null;
                    itinerario.add(trayectosOrigen.get(u));
                    listaItinerarios.add(itinerario);
                }

            }

        }

        return listaItinerarios;
    }
}
