package aninfo.servicio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import org.graalvm.compiler.hotspot.replacements.HotSpotArraySubstitutions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aninfo.excepciones.CargarHorasInvalidasException;
import aninfo.excepciones.PersonaNoEncontradaExcepcion;
import aninfo.excepciones.ProyectoNoEncontradoExcepcion;
import aninfo.excepciones.TareaNoEncontradaExcepcion;
import aninfo.modelo.HorasTrabajadas;
import aninfo.modelo.Persona;
import aninfo.modelo.Proyecto;
import aninfo.modelo.RegistroCarga;
import aninfo.modelo.Tarea;
import aninfo.repositorio.RepositorioHorasTrabajadas;

@Service
public class ServicioHoras {
	private Map<Persona, Map<Proyecto, Map<Tarea, HorasTrabajadas>>> personas = new HashMap<Persona, Map<Proyecto, Map<Tarea, HorasTrabajadas>>>();
	
    @Autowired
	private RepositorioHorasTrabajadas repositorioHorasTrabajadas;
	
	public HorasTrabajadas cargarHoras(Persona persona, Proyecto proyecto, Tarea tarea, double cantHoras, String fechaTrabajada) {
		if (cantHoras <= 0){
			throw new CargarHorasInvalidasException();
		}
		HorasTrabajadas horas = new HorasTrabajadas(cantHoras, fechaTrabajada, persona.getNumLegajo(), proyecto.getCodigo(), tarea.getCodigo());
		return repositorioHorasTrabajadas.save(horas);
	}

	//¿Como obtenemos las horas totales?
	public double consultarHoras(Persona persona, Proyecto proyecto, Tarea tarea) {
		double totalHoras = 0;
		Collection<HorasTrabajadas> horas = new ArrayList<>();
        repositorioHorasTrabajadas.findAllHorasTrabajadasByPersonaAndProyectoAndTarea(
			persona.getNumLegajo(), proyecto.getCodigo(), tarea.getCodigo()).forEach(horas::add);
		for (Iterator<HorasTrabajadas> iterator = horas.iterator(); iterator.hasNext();){
			totalHoras += ((HorasTrabajadas) iterator).getCantHoras();
		}
		return totalHoras;
	}

	//Esto solo se usa en el BDD
	public int obtenerCantTareas(Persona persona) {
		int totalTareas = 0;
		if (!personas.containsKey(persona))
			throw new PersonaNoEncontradaExcepcion(persona);

		Map<Proyecto, Map<Tarea, HorasTrabajadas>> proyectos = personas.get(persona);

		for (Map<Tarea, HorasTrabajadas> tareas : proyectos.values()) {
			totalTareas += tareas.size();
		}
		return totalTareas;
	}

	//ya no se usa mas
	/*
	public void eliminarHoras(Persona persona, Proyecto proyecto, Tarea tarea, double cantHoras, int idRegistro) {
		if (!personas.containsKey(persona))
			throw new PersonaNoEncontradaExcepcion(persona);

		Map<Proyecto, Map<Tarea, HorasTrabajadas>> proyectos = personas.get(persona);
		if (!proyectos.containsKey(proyecto))
			throw new ProyectoNoEncontradoExcepcion(proyecto);

		Map<Tarea, HorasTrabajadas> tareas = proyectos.get(proyecto);
		if (!tareas.containsKey(tarea))
			throw new TareaNoEncontradaExcepcion(tarea);

		HorasTrabajadas horasTrabajadas = tareas.get(tarea);
		horasTrabajadas.eliminarHoras(cantHoras, idRegistro);
		if (horasTrabajadas.getCantHorasTot() == 0)
			tareas.remove(tarea);
		if (tareas.size() == 0)
			proyectos.remove(proyecto);
		if (proyectos.size() == 0)
			personas.remove(persona);
	}*/

	public void eliminarRegistro(int idRegistro) {
		repositorioHorasTrabajadas.deleteById(idRegistro);
	}

	public Collection<HorasTrabajadas> obtenerRegistros(Persona persona, Proyecto proyecto, Tarea tarea) {
		Collection<HorasTrabajadas> horas = new ArrayList<>();
        repositorioHorasTrabajadas.findAllHorasTrabajadasByPersonaAndProyectoAndTarea(
			persona.getNumLegajo(), proyecto.getCodigo(), tarea.getCodigo()).forEach(horas::add);
        return horas;
	}

	public HorasTrabajadas obtenerRegistro(Persona persona, Proyecto proyecto, Tarea tarea, int idRegistro) {
		return repositorioHorasTrabajadas.findHorasTrabajadasById(idRegistro);
	}

	public HorasTrabajadas actualizarRegistro(Persona persona, Proyecto proyecto, Tarea tarea, int idRegistro, int cantHoras) {
		if (cantHoras <= 0) {
            throw new CargarHorasInvalidasException();
        }

       	HorasTrabajadas horas = repositorioHorasTrabajadas.findHorasTrabajadasById(idRegistro);
        horas.setCantHoras(cantHoras);
		repositorioHorasTrabajadas.save(horas);
		return horas;
	}
}
