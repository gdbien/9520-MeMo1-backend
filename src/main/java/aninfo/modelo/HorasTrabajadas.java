package aninfo.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import aninfo.excepciones.RegistroConCantHorasInvalidasExcepcion;
import aninfo.excepciones.RegistroConFechaInvalidaExcepcion;

@Entity
public class HorasTrabajadas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private int idPersona;
    private int idProyecto;
    private int idTarea;
    private double cantHoras;
    private LocalDate fechaTrabajada;
    private LocalDate fechaDeCarga;
    
    public HorasTrabajadas() {

    }

    public HorasTrabajadas(int idPersona, int idProyecto, int idTarea, double cantHoras, String fechaTrabajada) {
        if (cantHoras <= 0 || cantHoras > 24)
            throw new RegistroConCantHorasInvalidasExcepcion();

        LocalDate fCarga = LocalDate.now();
        LocalDate fTrabajada = LocalDate.parse(fechaTrabajada);
        if (fTrabajada.isAfter(fCarga))
            throw new RegistroConFechaInvalidaExcepcion();

        this.idPersona = idPersona;
        this.idProyecto = idProyecto;
        this.idTarea = idTarea;
        this.cantHoras = cantHoras;
        this.fechaTrabajada = fTrabajada;
        this.fechaDeCarga = fCarga;
    }

    public int getId() {
        return id;
    }

    public double getCantHoras() {
        return cantHoras;
    }

    public void setCantHoras(double cantHoras) {
        if (cantHoras <= 0 || cantHoras > 24)
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

    public int getIdPersona() {
        return this.idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdProyecto() {
        return this.idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdTarea() {
        return this.idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public void setId(int id) {
        this.id = id;
    }
}
