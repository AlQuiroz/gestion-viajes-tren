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
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.uca.dss.trenes.interfazExtendido.InterfazVehiculo;
/**
 * Clase encargada de representar un trayecto.
 *
 * Se identifica con la ciudad origen y destino del mismo, y forma parte de un viaje. Su distancia se mide mediante tramos.
 *
 * @author manuel
 */
public class Trayecto {
    private int tramos;
    private Ciudad origen;
    private Ciudad destino;
    private ArrayList<Horario> horarios;
    private Horario horarioElegido;
    private float precio;
    private boolean horarioSeleccionado;

    //Constructores

    /**
     * Constructor
     *
     * Construye un objeto de la clase Trayecto, recibiendo por parámetros el origen, destino, horarios y número de tramos del trayecto a construir.
     * 
     * @param tramos número de tramos que componen el trayecto
     * @param Origen ciudad origen del trayecto
     * @param Horarios conjunto de horarios en los que se realiza el trayecto
     * @param Destino ciudad destino del trayecto
     */

    public Trayecto(int tramos, Ciudad Origen, Ciudad Destino, ArrayList<Horario> Horarios){
        
        this.setTramos(tramos);
        this.setOrigen(Origen);
        this.setDestino(Destino);
        this.setHorarios(Horarios);
        this.setPrecio(0);
        this.horarioSeleccionado=false;
    }

    // Constructor de copia

    /**
     * Constructor de copia.
     *
     * Construye un objeto de la clase Trayecto, copia del trayecto que recibe por parámetro.
     *
     * @param valor objeto Trayecto del que queremos realizar una copia
     */
    public Trayecto(Trayecto valor){

        this.setTramos(valor.getTramos());
        this.setOrigen(valor.getOrigen());
        this.setDestino(valor.getDestino());
        this.setHorarios(valor.listarHorarios());
        this.setPrecio(valor.getPrecio());
        if(valor.horarioSeleccionado){
            this.setHorarioElegido(valor.getHorarioElegido());
        }
    }

    //Métodos de asignación

    private void setTramos(int valor){
        this.tramos=valor;
    }
    
    private void setOrigen(Ciudad valor){
        this.origen=new Ciudad(valor);

    }

    private void setDestino(Ciudad valor){
        this.destino=new Ciudad(valor);
    }

    private void setHorarios(ArrayList<Horario> valor){
        this.horarios= new ArrayList<Horario>();
        this.horarios=valor;
    }

    /**
     * Método modificador.
     *
     * Selecciona el horario que el usuario ha elegido de entre todos, para realizar el trayecto.
     * 
     * @param valor horario que queremos establecer como horario elegido para el trayecto
     */
    public void setHorarioElegido(Horario valor){
        this.horarioSeleccionado=true;
        this.horarioElegido=new Horario(valor);
        this.setPrecio(this.calcularPrecioTrayecto(valor));
        
    }

    private void setPrecio(float valor){
        this.precio=valor;
    }

    //Métodos consultores

    /**
     * Método consultor de tramos
     *
     * Devuelve el número de tramos que componen el trayecto.
     *
     * @return número de tramos del trayecto
     */

    public int getTramos(){
        return this.tramos;
    }

    
    /**
     * Método consultor de ciudad origen.
     *
     * Devuelve la ciudad origen del trayecto.
     *
     * @return ciudad origen del trayecto
     */
    public Ciudad getOrigen(){
       return this.origen;
    }

    /**
     * Método consultor de ciudad destino.
     *
     * Devuelve la ciudad destino del trayecto.
     *
     * @return ciudad destino del trayecto
     */
    public Ciudad getDestino(){
       return this.destino;
    }

    /**
     * Método consultor de horarios.
     * 
     * Devuelve un conjunto de horarios, que son los previstos para el trayecto.
     * 
     * @return lista de horarios disponibles para el trayecto.
     */
    public ArrayList<Horario> listarHorarios(){
        return this.horarios;
    }

    /**
     * Método consultor del horario elegido.
     *
     * Devuelve el horario elegido por el usuario para realizar el trayecto.
     *
     * @return horario elegido para el trayecto
     */
    public Horario getHorarioElegido(){
        
        if(this.horarioSeleccionado){
            return this.horarioElegido;
        }else{
            try{
                return this.horarioElegido;
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
    public Float getPrecio(){
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
    public Float calcularPrecioTrayecto(Horario HorarioElegido){
        InterfazVehiculo auxiliar=horarioElegido.getVehiculo();
        float costePorTramo=auxiliar.getCosteTramo();
        int numTramos=this.getTramos();
        return costePorTramo*numTramos;
    }

    

    /**
     * Método consultor de horarios con precios asociados
     *
     * Lista y devuelve un conjunto de pares horario-precio, es decir, la lista de horarios disponibles para el trayecto y su precio asociado.
     *
     * @return conjunto de pares horario-precio
     */
    public Map<Horario, Float> listarHorariosConPrecios(){
        Map<Horario, Float> mapa= new HashMap<Horario, Float>();
        for(int i=0; i<this.listarHorarios().size(); i++){
            Horario h=new Horario(this.listarHorarios().get(i));
            Float PrecioParcial=this.calcularPrecioTrayecto(h);
            mapa.put(h, PrecioParcial);
        }
        return mapa;

    }


}
