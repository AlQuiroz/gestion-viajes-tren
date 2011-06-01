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


package org.uca.dss.trenes.interfazExtendido;

/**
 * Interfaz encargada de representar a un vehículo en el sistema.
 *
 * Se encarga de representar un vehículo en general en el sistema de viajes, independientemente de si es un tren, coche, moto, avión, etc. Sus métodos permiten obtener su número de asientos, coste por tramo, y nombre.
 *
 * @author manuel
 */
public interface InterfazVehiculo {
    /**
     * Método consultor de coste del tramo.
     * Devuelve el coste del tramo que realiza el vehículo
     *
     * @return Devuelve un float con el coste del tramo recorrido para dicho vehículo.
     */
     public float getCosteTramo();

     /**
     * Método consultor del número de asientos.
     *
     * @return Duelve un int con el número de asientos del vehículo.
     */
     public int getNumAsientos();

     /**
     * Método Consultor del nombre del vehículo.
     *
     * Devuelve un string con el nombre que tiene el vehículo.
     *
     * @return nombre
     */
     public String getNombre();
}
