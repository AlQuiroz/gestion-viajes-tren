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
        return itinerario.size();
    }

    public boolean isEmpty() {
        return itinerario.isEmpty();
    }

    public boolean contains(Object o) {
        return itinerario.contains(o);
    }

    public Iterator<InformacionTrayecto> iterator() {
        return itinerario.iterator();
    }

    public Object[] toArray() {
        return itinerario.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return itinerario.toArray(a);
    }

    public boolean add(InformacionTrayecto e) {
        return itinerario.add(e);
    }

    public boolean remove(Object o) {
        return itinerario.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return itinerario.containsAll(c);
    }

    public boolean addAll(Collection<? extends InformacionTrayecto> c) {
        return itinerario.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends InformacionTrayecto> c) {
        return itinerario.addAll(index, c);
    }

    public boolean removeAll(Collection<?> c) {
        return itinerario.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return itinerario.retainAll(c);
    }

    public void clear() {
        itinerario.clear();
    }

    public InformacionTrayecto get(int index) {
        return itinerario.get(index);
    }

    public InformacionTrayecto set(int index, InformacionTrayecto element) {
        return itinerario.set(index, element);
    }

    public void add(int index, InformacionTrayecto element) {
        itinerario.add(index, element);
    }

    public InformacionTrayecto remove(int index) {
        return itinerario.remove(index);
    }

    public int indexOf(Object o) {
        return itinerario.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return itinerario.lastIndexOf(o);
    }

    public ListIterator<InformacionTrayecto> listIterator() {
        return itinerario.listIterator();
    }

    public ListIterator<InformacionTrayecto> listIterator(int index) {
        return itinerario.listIterator(index);
    }

    public List<InformacionTrayecto> subList(int fromIndex, int toIndex) {
        return itinerario.subList(fromIndex, toIndex);
    }

   }
