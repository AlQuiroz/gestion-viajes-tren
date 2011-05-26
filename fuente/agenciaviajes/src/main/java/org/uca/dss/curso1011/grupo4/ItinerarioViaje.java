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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.uca.dss.curso1011.grupo4.interfazExtendido.InformacionTrayecto;
import org.uca.dss.curso1011.grupo4.interfazExtendido.Itinerario;

/**
 *
 * @author manuel
 */

public class ItinerarioViaje implements Itinerario{

    private Itinerario itinerario;
    

    //Constructor
    public ItinerarioViaje(Itinerario i){
        this.itinerario=i;
    }

    public double getPrecio(){
        double precio=0;
        for(int i=0; i<this.itinerario.size(); i++){
            precio= precio + this.itinerario.get(i).getPrecio();
        }
        return precio;
    }

    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Iterator<InformacionTrayecto> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean add(InformacionTrayecto e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean addAll(Collection<? extends InformacionTrayecto> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean addAll(int index, Collection<? extends InformacionTrayecto> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public InformacionTrayecto get(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public InformacionTrayecto set(int index, InformacionTrayecto element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void add(int index, InformacionTrayecto element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public InformacionTrayecto remove(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ListIterator<InformacionTrayecto> listIterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ListIterator<InformacionTrayecto> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<InformacionTrayecto> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   }
