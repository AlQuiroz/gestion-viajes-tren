/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.uca.dss.trenes.interfaz;
import org.uca.dss.curso1011.grupo4.interfaz.InterfazListados;
import org.uca.dss.curso1011.grupo4.interfaz.InterfazCompras;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.After;
import org.junit.Before;
import org.uca.dss.curso1011.grupo4.AdaptadorListado;
import org.uca.dss.trenes.basededatos.DBUtils;
import org.uca.dss.curso1011.grupo4.GestionReservas;
import org.uca.dss.curso1011.grupo4.CargaDatos;

/**
 *
 * @author dmolina
 */
public abstract class InterfazTest {
    /** Referencia a la clase Listados */
    protected InterfazListados listado;
    /** Referencia a la clase compras */
    protected InterfazCompras compras;
    protected LocalDate hoy;
    protected String origen;
    protected String destino;

    public InterfazTest() {
        origen = "cádiz";
        destino = "sevilla";
        hoy = new LocalDate();
    }

    public List<LocalTime> getHorasPosibles() {
        List<LocalTime> horasComprobar = new LinkedList<LocalTime>();
        horasComprobar.add(new LocalTime("17:00"));
        horasComprobar.add(new LocalTime("19:00"));
        horasComprobar.add(new LocalTime("9:00"));
        horasComprobar.add(new LocalTime("13:00"));
        horasComprobar.add(new LocalTime("9:15"));

        return horasComprobar;
    }

    public LocalTime getHoraAleatoria(List<LocalTime> horas) {
        int pos = new Random().nextInt(horas.size());
        return horas.get(pos);
    }

    protected GestionReservas gestion;

    @Before
    public void setUp() {
        DBUtils.initDataBase("reservastests.dat");
        gestion = new GestionReservas("trenest1.csv", "trayectost1.csv", hoy);
        listado = gestion.getDatos();
        compras = gestion;
    }

    @After
    public void tearDown() {
        DBUtils.clear();
        gestion = null;
    }

}
