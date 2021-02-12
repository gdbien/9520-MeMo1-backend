package aninfo.modelo;

import java.util.HashMap;
import java.util.Map;

import aninfo.excepciones.PersonaNoEncontradaExcepcion;
import aninfo.excepciones.ProyectoNoEncontradoExcepcion;
import aninfo.excepciones.TareaNoEncontradaExcepcion;

public class SistemaCargador {
	private Map<Persona, Map<Proyecto, Map<Tarea, HorasTrabajadas>>> personas = new HashMap<Persona, Map<Proyecto, Map<Tarea, HorasTrabajadas>>>();

	public void cargarHoras(Persona persona, Proyecto proyecto, Tarea tarea, double cantHoras, String fechaTrabajada) {
		if (!personas.containsKey(persona))
			personas.put(persona, new HashMap<Proyecto, Map<Tarea, HorasTrabajadas>>());

		Map<Proyecto, Map<Tarea, HorasTrabajadas>> proyectos = personas.get(persona);
		if (!proyectos.containsKey(proyecto))
			proyectos.put(proyecto, new HashMap<Tarea, HorasTrabajadas>());

		Map<Tarea, HorasTrabajadas> tareas = proyectos.get(proyecto);
		if (!tareas.containsKey(tarea))
			tareas.put(tarea, new HorasTrabajadas());

		tareas.get(tarea).agregarHoras(cantHoras, fechaTrabajada);
	}

	public double consultarHoras(Persona persona, Proyecto proyecto, Tarea tarea) {
		if (!personas.containsKey(persona))
			throw new PersonaNoEncontradaExcepcion(persona);

		Map<Proyecto, Map<Tarea, HorasTrabajadas>> proyectos = personas.get(persona);
		if (!proyectos.containsKey(proyecto))
			throw new ProyectoNoEncontradoExcepcion(proyecto);

		Map<Tarea, HorasTrabajadas> tareas = proyectos.get(proyecto);
		if (!tareas.containsKey(tarea))
			throw new TareaNoEncontradaExcepcion(tarea);

		return tareas.get(tarea).getCantHorasTot();
	}

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

	public void eliminarHoras(Persona persona, Proyecto proyecto, Tarea tarea, double cantHoras, int idRegistro) {
		if (!personas.containsKey(persona))
			throw new PersonaNoEncontradaExcepcion(persona);

		Map<Proyecto, Map<Tarea, HorasTrabajadas>> proyectos = personas.get(persona);
		if (!proyectos.containsKey(proyecto))
			throw new ProyectoNoEncontradoExcepcion(proyecto);

		Map<Tarea, HorasTrabajadas> tareas = proyectos.get(proyecto);
		if (!tareas.containsKey(tarea))
			throw new TareaNoEncontradaExcepcion(tarea);

		HorasTrabajadas hs_trabajadas = tareas.get(tarea);
		hs_trabajadas.eliminarHoras(cantHoras, idRegistro);
		if (hs_trabajadas.getCantHorasTot() == 0)
			tareas.remove(tarea);
		if (tareas.size() == 0)
			proyectos.remove(proyecto);
		if (proyectos.size() == 0)
			personas.remove(persona);
	}

}
