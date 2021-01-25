package aninfo.model;

import java.time.LocalDate;

import aninfo.model.excepciones.RegistroConCantHorasInvalidasExcepcion;

public class RegistroCarga {
    private static int nextId = 1;

    private int id;
    private double cantHoras;
    private LocalDate fechaTrabajada;
    private LocalDate fechaDeCarga;

    public RegistroCarga(double cantHoras, String fechaTrabajada) {
        this.id = RegistroCarga.nextId++;
        this.cantHoras = cantHoras;
        //Se puede lanzar error en el caso que fechaTrabajada sea mayor a fechaDeCarga.
        this.fechaTrabajada = LocalDate.parse(fechaTrabajada);
        this.fechaDeCarga = LocalDate.now();
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
