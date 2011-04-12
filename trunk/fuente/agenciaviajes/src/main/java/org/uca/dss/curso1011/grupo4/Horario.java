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
import org.joda.time.LocalTime;

/**
 * Representa el horario previsto para un viaje en el sistema.
 *
 * Describe tanto la hora de salida como la hora de llegada del mismo.
 *
 * @author Manuel Jesús de la Calle Brihuega
 */
public class Horario {

    //Atributos
    private int asientos_disponibles;
    private LocalTime hora_llegada;
    private LocalTime hora_salida;
    private Tren tren;

    // Constructores
    /**
     * Constructor
     *
     * Construye un objeto de la clase Horario recibiendo como parámetros los atributos del objeto.
     *
     * @param salida hora de salida del viaje
     * @param llegada hora de llegada del viaje
     * @param asientos asientos disponibles para el viaje
     */
    public Horario(LocalTime salida, LocalTime llegada, int asientos, Tren tren){
  
        this.SetHoraSalida(salida);
        this.SetHoraLlegada(llegada);
        this.SetAsientosDisponibles(asientos);
        this.SetTren(tren);
    }

    /**
     * Constructor de copia
     *
     * Construye un objeto de la clase Horario recibiendo como parámetro otro objeto Horario.
     * @param valor objeto del que se realizará una copia
     */
    public Horario(Horario valor){
        this.SetHoraSalida(valor.GetHoraSalida());
        this.SetHoraLlegada(valor.GetHoraLlegada());
        this.SetAsientosDisponibles(valor.GetAsientosDisponibles());
        this.SetTren(valor.GetTren());
    }
    //Métodos de asignación

    private void SetHoraSalida(LocalTime valor){
        this.hora_salida=new LocalTime(valor);
    }

    private void SetHoraLlegada(LocalTime valor){
        this.hora_llegada=new LocalTime(valor);
    }

    private void SetAsientosDisponibles(int valor){
        if(valor < 1)
        {
            this.asientos_disponibles=0;
        }
        else
        {
            this.asientos_disponibles=valor;
        }

    }
    
    private void SetTren(Tren valor){
        this.tren=new Tren(valor);
    }
     

    // Métodos 'Get'

    /**
     * Método consultor de disponibilidad.
     * Devuelve el número de asientos disponibles para el horario
     *
     * @return número de asientos disponibles
     */
    public int GetAsientosDisponibles(){
        return this.asientos_disponibles;
    }


    public Tren GetTren(){
        return this.tren;
    }

    /**
     * Método consultor que devuelve la hora de llegada prevista
     *
     * @return hora de llegada del tren
     */
    public LocalTime GetHoraLlegada(){
        return this.hora_llegada;
    }

    /**
     * Método consultor que devuelve la hora de salida prevista
     *
     * @return hora de salida del tren
     */
    public LocalTime GetHoraSalida(){
        return this.hora_salida;
    }

    //Otros métodos

    /**
     * Actualiza el número de asientos libres para el horario.
     *
     * Decrementa o incremedecrementa el atributo asientos_disponibles con el número de asientos reservados o cancelados(parámetro 'asientos_reservados').
     *
     * @param asientos_reservados numero de asientos a decrementar (reservados).
     */
    public void ActualizaAsientos(int asientos_reservados){
        int disponibles=this.GetAsientosDisponibles();
        disponibles=disponibles + asientos_reservados;
        if(disponibles < 0){
            // Aquí lanzaremos una excepción
        }
        else
        {
            //If supera numero de asientos del tren otra excepcion
            this.SetAsientosDisponibles(disponibles);
        }

    }

    /**
     * Comprueba la disponibilidad del viaje.
     *
     * Revisa si existen asientos disponibles para el horario. Devuelve un booleano indicando su resultado.
     *
     * @return booleano que verifica la disponibildad o no de asientos
     */
    public boolean ComprobarDisponibilidad(){
        if(this.GetAsientosDisponibles() > 0)
            return true;
        else
            return false;
    }
    
    public float ObtenerPrecioHorario(){
        return this.GetTren().getCostetramo();
    }
    
}
