package aninfo.model;

import java.time.LocalDateTime;

import aninfo.model.excepciones.RegistroConCantHorasInvalidasExcepcion;

public class RegistroCarga {
    private static int nextId = 1;

    private int id;
    private double cantHoras;
    private LocalDateTime fecha;

    public RegistroCarga(double cantHoras, LocalDateTime fecha) {
        this.id = RegistroCarga.nextId++;
        this.cantHoras = cantHoras;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public double getCantHoras() {
        return cantHoras;
    }

    public void eliminarHoras(double cantHoras) {
        if (this.cantHoras - cantHoras <= 0)
            throw new RegistroConCantHorasInvalidasExcepcion();
        this.cantHoras -= cantHoras;
    }

}
