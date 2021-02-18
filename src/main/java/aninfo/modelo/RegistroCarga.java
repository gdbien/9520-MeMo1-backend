package aninfo.modelo;

import java.time.LocalDate;

public class RegistroCarga {
    private int id;
    private double cantHoras;
    private LocalDate fechaTrabajada;
    private LocalDate fechaDeCarga;

    public RegistroCarga(int id, double cantHoras, LocalDate fechaTrabajada, LocalDate fechaDeCarga) {
        this.id = id;
        this.cantHoras = cantHoras;
        this.fechaTrabajada = fechaTrabajada;
        this.fechaDeCarga = fechaDeCarga;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getCantHoras() {
        return cantHoras;
    }

    public void setCantHoras(double cantHoras) {     
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
