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
import org.uca.dss.curso1011.grupo4.Horario;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.lang.Float;
/**
 * Clase encargada de representar un trayecto.
 *
 * Se identifica con la ciudad origen y destino del mismo, y forma parte de un viaje. Su distancia se mide mediante tramos.
 *
 * @author manuel
 */
public class Trayecto {
    private int tramos;
    private Ciudad Origen;
    private Ciudad Destino;
    private ArrayList<Horario> Horarios;
    private Horario HorarioElegido;
    private float precio;
    private boolean HorarioSeleccionado;

    //Constructores

    /**
     *
     * @param tramos
     * @param Horarios
     */
    public Trayecto(int tramos, Ciudad Origen, Ciudad Destino, ArrayList<Horario> Horarios){
        
        this.SetTramos(tramos);
        this.SetOrigen(Origen);
        this.SetDestino(Destino);
        
        this.SetHorarios(Horarios);
        this.SetPrecio(0);
        this.HorarioSeleccionado=false;
    }

    // Constructor de copia

    public Trayecto(Trayecto valor){

        this.SetTramos(valor.GetTramos());
        this.SetOrigen(valor.GetOrigen());
        this.SetDestino(valor.GetDestino());

        this.SetHorarios(valor.ListarHorarios());
        this.SetPrecio(valor.GetPrecio());
        if(valor.HorarioSeleccionado){
            this.SetHorarioElegido(valor.GetHorarioElegido());
        }
    }

    //Métodos de asignación

    private void SetTramos(int valor){
        this.tramos=valor;
    }
    
    private void SetOrigen(Ciudad valor){
        this.Origen=new Ciudad(valor);

    }

    private void SetDestino(Ciudad valor){
        this.Destino=new Ciudad(valor);
    }

    private void SetHorarios(ArrayList<Horario> valor){
        this.Horarios= new ArrayList<Horario>();
        this.Horarios=valor;
    }

    /**
     * Selecciona el horario que el usuario ha elegido de entre todos, para realizar el trayecto.
     * @param Horario horario elegido por el usuario
     */
    public void SetHorarioElegido(Horario valor){
        this.HorarioSeleccionado=true;
        this.HorarioElegido=new Horario(valor);
        this.SetPrecio(this.CalcularPrecioTrayecto(valor));
        
    }

    private void SetPrecio(float valor){
        this.precio=valor;
    }

    //Métodos consultores

    /**
     * Método consultor de tramos
     *
     * Devuelve el número de tramos que forman el trayecto.
     *
     * @return número de tramos del trayecto
     */
    public int GetTramos(){
        return this.tramos;
    }

    
    public Ciudad GetOrigen(){
       return this.Origen;
    }

    public Ciudad GetDestino(){
       return this.Destino;
    }

    /**
     * Método consultor de horarios.
     * 
     * Devuelve un conjunto de horarios, que son los previstos para el trayecto.
     * 
     * @return lista de horarios disponibles para el trayecto.
     */
    public ArrayList<Horario> ListarHorarios(){
        return this.Horarios;
    }

    /**
     * Método consultor del horario elegido.
     *
     * Devuelve el horario elegido por el usuario para realizar el trayecto.
     *
     * @return horario elegido para el trayecto
     */
    public Horario GetHorarioElegido(){
        
        if(this.HorarioSeleccionado){
            return this.HorarioElegido;
        }else{
            try{
                return this.HorarioElegido;
            }catch(NullPointerException e){
                System.out.println("No se ha seleccionado aún ningun horario");
            }catch(Exception e){
                System.out.println("Se ha producido un error");
            }
            return null;
        }
    }

    /**
     * Método consultor del precio
     *
     * Devuelve el precio que tiene el trayecto.
     *
     * @return precio del trayecto
     */
    public Float GetPrecio(){
        return this.precio;
    }

    //Otros métodos
    /**
     * Método que calcula el precio para un horario dado
     *
     * Calcula y devuelve dicho precio, en función del coste por tramo del tren asociado al horario que recibe por parámetro.
     *
     * @param HorarioElegido horario del que queremos calcular su precio
     * @return precio para el horario introducido por parámetro
     */
    public Float CalcularPrecioTrayecto(Horario HorarioElegido){
        float CostePorTramo=HorarioElegido.GetTren().getCostetramo();
        int NumTramos=this.GetTramos();
        return CostePorTramo*NumTramos;
    }

    

    /**
     * Método consultor de horarios con precios asociados
     *
     * Lista y devuelve un conjunto de pares horario-precio, es decir, la lista de horarios disponibles para el trayecto y su precio asociado.
     *
     * @return conjunto de pares horario-precio
     */
    public Map<Horario, Float> ListarHorariosConPrecios(){
        Map<Horario, Float> mapa= new HashMap<Horario, Float>();
        for(int i=0; i<this.ListarHorarios().size(); i++){
            Horario h=new Horario(this.ListarHorarios().get(i));
            Float PrecioParcial=this.CalcularPrecioTrayecto(h);
            mapa.put(h, PrecioParcial);
        }
        return mapa;

    }


}
