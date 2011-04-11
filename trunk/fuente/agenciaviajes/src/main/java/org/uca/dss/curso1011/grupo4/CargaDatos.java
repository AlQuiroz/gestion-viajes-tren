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
 *
 * @author manuel
 */
public class CargaDatos {

    private ArrayList<Tren> TrenesCargados;
    private ArrayList<Trayecto> TrayectosCargados;

    public CargaDatos(String ArchivoTrayectos, String ArchivoTrenes){
        try{
            CSVReader readerTrenes=new CSVReader(new FileReader(ArchivoTrenes));
            String [] nextLine;
            while ((nextLine = readerTrenes.readNext ()) != null ) {
                //Aqui vamos cargando los trenes
                String nombreTren= nextLine[0];
                String asientos= nextLine[1];
                String precio= nextLine[2];
                float precioConvertido=Float.valueOf(precio).floatValue();
                int asientosConvertido=Integer.parseInt(asientos.trim());
                this.TrenesCargados.add(new Tren(asientosConvertido, precioConvertido, nombreTren));
            }

        }catch(Exception e){
                System.out.println("Se ha producido un error de lectura en el fichero de trenes");
       }

        try{
            CSVReader readerTrayectos=new CSVReader(new FileReader(ArchivoTrayectos));
            String [] nextLineTrayectos;
            while ((nextLineTrayectos = readerTrayectos.readNext ()) != null ) {
                //Aqui vamos cargando los trayectos
                String nombre=nextLineTrayectos[0];
                String ciudadOrigen= nextLineTrayectos[1];
                String ciudadDestino=nextLineTrayectos[2];
                String tramos=nextLineTrayectos[3];
                Tren tren=new Tren(this.GetTren(nombre));
                Ciudad origen= new Ciudad(ciudadOrigen, "España");
                Ciudad destino= new Ciudad(ciudadDestino, "España");
                int numtramos=Integer.parseInt(tramos.trim());
                int i=4;
                ArrayList<Horario> horariosTrayecto=new ArrayList<Horario>();
                while(!nextLineTrayectos[i].isEmpty()){

                    String horaSalida=nextLineTrayectos[i];
                    LocalTime salida=this.StringToLocaltime(horaSalida);
                    i=i+1;
                    String horaLlegada=nextLineTrayectos[i];
                    LocalTime llegada=this.StringToLocaltime(horaLlegada);
                    i=i+1;
                    Horario h=new Horario(salida, llegada, tren.getNumasiento(), tren);
                    horariosTrayecto.add(h);
                }
                this.TrayectosCargados.add(new Trayecto(numtramos, origen, destino, horariosTrayecto));
            }

        }catch(Exception e){
                System.out.println("Se ha producido un error de lectura en el fichero de trayectos");
       }
    }

    private LocalTime StringToLocaltime(String valor){
        int i=0;
        String hora=new String();
        String minutos=new String();
        char hora_[]= new char[2];
        char minutos_[]= new char[2];
        while(valor.charAt(i)!=':'){
            hora_[i]=valor.charAt(i);
            i=i+1;
        }
        hora.valueOf(hora_);
        i=i+1;
        minutos_[0]=valor.charAt(i);
        i=i+1;
        minutos_[1]=valor.charAt(i);
        minutos.valueOf(minutos_);
        int horasInt=Integer.parseInt(hora.trim());
        int minutosInt=Integer.parseInt(minutos.trim());
        return new LocalTime(horasInt,minutosInt);
    }

    public Tren GetTren(String nombre){
        int i=0, j=0;
        Tren t;
        while(!this.TrenesCargados.isEmpty()){
            if(this.TrenesCargados.get(i).getNombre()==nombre){
                j= i;
            }
            i=i+1;
        }
        t=new Tren(this.TrenesCargados.get(j));
        return t;
    }
}
