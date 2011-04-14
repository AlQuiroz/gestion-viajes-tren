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

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import org.joda.time.LocalTime;
/**
 * Clase encargada de cargar datos.
 *
 * Realiza una carga de los trenes y trayectos que obtiene de los ficheros csv.
 * @author Manuel Jesús de la Calle Brihuega
 */
public class CargaDatos {

    private ArrayList<Tren> TrenesCargados;
    private ArrayList<Trayecto> TrayectosCargados;

    /**
     * Constructor de la clase.
     *
     * Recibe como parámetros dos strings con las rutas de los ficheros trenes.csv y trayectos.csv de los cuales se obtienen los trenes y trayectos.
     *
     * @param ArchivoTrayectos ruta del fichero trayectos.csv
     * @param ArchivoTrenes ruta del fichero trenes.csv
     */
    public CargaDatos(String ArchivoTrayectos, String ArchivoTrenes){
        try{
            CSVReader readerTrenes=new CSVReader(new FileReader(ArchivoTrenes));
            String [] nextLine;
            this.TrenesCargados= new ArrayList<Tren>();
            while ((nextLine = readerTrenes.readNext ()) != null ) {
                //Aqui vamos cargando los trenes
                String nombreTren= nextLine[0];
                String asientos= nextLine[1];
                String precio= nextLine[2];
                float precioConvertido=Float.valueOf(precio.trim()).floatValue();
                int asientosConvertido=Integer.parseInt(asientos.trim());
                Tren tren= new Tren(asientosConvertido, precioConvertido, nombreTren.trim());
                if(!this.TrenesCargados.add(tren)){
                    System.out.println("Error al introducir tren" + nombreTren);
                }
            }

        }catch(Exception e){

                System.out.println("Se ha producido un error de lectura en el fichero de trenes: " + e.getMessage());
       }

        try{
            CSVReader readerTrayectos=new CSVReader(new FileReader(ArchivoTrayectos));
            String [] nextLineTrayectos;
            this.TrayectosCargados=new ArrayList<Trayecto>();
            while ((nextLineTrayectos = readerTrayectos.readNext ()) != null ) {
                //Aqui vamos cargando los trayectos
                String nombre=nextLineTrayectos[0];
                String ciudadOrigen= nextLineTrayectos[1];
                String ciudadDestino=nextLineTrayectos[2];
                String tramos=nextLineTrayectos[3];
                Tren tren=new Tren(this.GetTren(nombre.trim()));
                Ciudad origen= new Ciudad(ciudadOrigen.trim(), "España");
                Ciudad destino= new Ciudad(ciudadDestino.trim(), "España");
                int numtramos=Integer.parseInt(tramos.trim());
                int i=4;
                ArrayList<Horario> horariosTrayecto=new ArrayList<Horario>();
                
                while(i<nextLineTrayectos.length){
                    
                    String horaSalida=nextLineTrayectos[i];
                    LocalTime salida=this.StringToLocaltime(horaSalida);
                    i=i+1;
                    String horaLlegada=nextLineTrayectos[i];
                    LocalTime llegada=this.StringToLocaltime(horaLlegada);
                    i=i+1;
                    Horario h=new Horario(salida, llegada, tren.getNumAsientos(), tren);
                    
                    if(!horariosTrayecto.add(h)){
                        System.out.println("Error al introducir horario en trayecto");
                    }
                    
                }
                
                Trayecto trayecto_= new Trayecto(numtramos, origen, destino, horariosTrayecto);
                
                if(!this.TrayectosCargados.add(trayecto_)){
                    System.out.println("Error al introducir trayecto");
                }
            }

        }catch(Exception e){
                System.out.println("Se ha producido un error de lectura en el fichero de trayectos: " + e.getMessage());
       }
    }

    /**
     * Constructor de copia.
     *
     * Construye un objeto CargaDatos que será una copia del que recibe por parámetro.
     *
     * @param valor objeto de CargaDatos del cual se realizará la copia.
     */
    public CargaDatos(CargaDatos valor){
        this.TrenesCargados= new ArrayList<Tren>();
        this.TrayectosCargados=new ArrayList<Trayecto>();
        this.TrayectosCargados=valor.GetTrayectosCargados();
        this.TrenesCargados=valor.GetTrenesCargados();
    }
    /**
     * Método convertidor.
     *
     * Método que convierte un string que contiene una hora determinada, en un objeto LocalTime con dicha hora.
     *
     * @param valor string con la hora que queremos convertir
     * @return hora contenida en el string de entrada en formato LocalTime
     */
    public LocalTime StringToLocaltime(String valor){

        String [] vector= valor.split(":");
        
        int horasInt=Integer.parseInt(vector[0].trim());
        int minutosInt=Integer.parseInt(vector[1].trim());
        return new LocalTime(horasInt,minutosInt);
    }

    /**
     * Método consultor de tren.
     *
     * Devuelve el tren de entre los trenes cargados cuyo nombre coincide con el string que recibe por parámetro.
     *
     * @param nombre string con el nombre del tren que queremos obtener.
     * @return objeto tren cuyo nombre coincide con el parámetro de entrada.
     */
    public Tren GetTren(String nombre){
        int i=0, j=0;
        Tren t;
        while(i<this.TrenesCargados.size()){
            if(this.TrenesCargados.get(i).getNombre().equalsIgnoreCase(nombre)){
                j= i;
            }
            i=i+1;
        }
        t=new Tren(this.TrenesCargados.get(j));
        return t;
    }
    /**
     * Método consultor de trenes.
     *
     * Devuelve el conjunto de trenes que han sido cargados del fichero trenes.csv
     * @return conjunto de trenes existentes
     */
    public ArrayList<Tren> GetTrenesCargados(){
        return this.TrenesCargados;
    }
    
    /**
     * Método consultor de trayectos.
     *
     * Devuelve el conjunto de trayectos que han sido cargados del fichero trayectos.csv
     *
     * @return conjunto de trayectos existentes
     */
    public ArrayList<Trayecto> GetTrayectosCargados(){
        return this.TrayectosCargados;
    }

}
