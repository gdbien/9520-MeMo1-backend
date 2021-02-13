package aninfo.modelo;

import java.time.LocalDate;

import aninfo.excepciones.RegistroConCantHorasInvalidasExcepcion;

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

    public void eliminarHoras(double cantHoras) {
        if (this.cantHoras - cantHoras <= 0)
            throw new RegistroConCantHorasInvalidasExcepcion();
        this.cantHoras -= cantHoras;
    }

    public int getId() {
        return id;
    }

    public double getCantHoras() {
        return cantHoras;
    }

    public void setCantHoras(double cantHoras) {
        if (cantHoras <= 0)
            throw new RegistroConCantHorasInvalidasExcepcion();     
        this.cantHoras = cantHoras;
    }

    public LocalDate getFechaTrabajada() {
        return fechaTrabajada;
    }

    public void setFechaTrabajada(LocalDate fechaTrabajada) {
        this.fechaTrabajada = fechaTrabajada;
    }

    public LocalDate getFechaDeCarga() {
        return fechaDeCarga;
    }

    public void setFechaDeCarga(LocalDate fechaDeCarga) {
        this.fechaDeCarga = fechaDeCarga;
    }

}
