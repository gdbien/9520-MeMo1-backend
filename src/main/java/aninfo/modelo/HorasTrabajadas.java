package aninfo.modelo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDate;

import aninfo.excepciones.RegistroConCantHorasInvalidasExcepcion;

@Entity
public class HorasTrabajadas {

    //private static int nextId = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double cantHoras;
    private LocalDate fechaTrabajada;
    private LocalDate fechaDeCarga;
    private int idPersona;
    private int idProyecto;
    private int idTarea;

    //private Map<Integer, RegistroCarga> registros;

    /*public HorasTrabajadas(double cantHoras, String fechaTrabajada) {
        //this.id = HorasTrabajadas.nextId++;
        this.cantHoras = cantHoras;
        //Se puede lanzar error en el caso que fechaTrabajada sea mayor a fechaDeCarga.
        this.fechaTrabajada = LocalDate.parse(fechaTrabajada);
        this.fechaDeCarga = LocalDate.now();
    }*/
    public HorasTrabajadas(){

    }
    public HorasTrabajadas(double cantHoras, String fechaTrabajada, int idPersona, int idProyecto, int idTarea) {
        //this.id = HorasTrabajadas.nextId++;
        this.cantHoras = cantHoras;
        //Se puede lanzar error en el caso que fechaTrabajada sea mayor a fechaDeCarga.
        this.fechaTrabajada = LocalDate.parse(fechaTrabajada);
        this.fechaDeCarga = LocalDate.now();
        this.idPersona = idPersona;
        this.idProyecto = idProyecto;
        this.idTarea = idTarea;
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

    /*public RegistroCarga agregarHoras(double cantHoras, String fechaTrabajada) {
        cantHorasTot += cantHoras;
        RegistroCarga regCarga = new RegistroCarga(cantHoras, fechaTrabajada);
        registros.put(regCarga.getId(), regCarga);
        return regCarga;
    }*/

    /*public double getCantHorasTot() {
        return cantHorasTot;
    }*/

   /* public void eliminarHoras(double cantHoras, int idRegistro) {
        if (!registros.containsKey(idRegistro))
            throw new RegistroNoEncontradoExcepcion(idRegistro);

        RegistroCarga regCarga = registros.get(idRegistro);
        Double cantHorasActual = regCarga.getCantHoras();
        try {
            regCarga.eliminarHoras(cantHoras);
            cantHorasTot -= cantHoras;
        } catch (RegistroConCantHorasInvalidasExcepcion e) {
            registros.remove(idRegistro);
            cantHorasTot -= cantHorasActual;
        }
    }

	public void eliminar(int idRegistro) {
        if (!registros.containsKey(idRegistro))
            throw new RegistroNoEncontradoExcepcion(idRegistro);
        RegistroCarga regCarga = registros.get(idRegistro);
        cantHorasTot -= regCarga.getCantHoras();
        registros.remove(idRegistro);
	}

	public List<RegistroCarga> getRegistros() {
		return new ArrayList<RegistroCarga>(registros.values());
	}

	public RegistroCarga obtenerRegistro(int idRegistro) {
        if (!registros.containsKey(idRegistro))
            throw new RegistroNoEncontradoExcepcion(idRegistro);
        return registros.get(idRegistro);
	}*/
}
