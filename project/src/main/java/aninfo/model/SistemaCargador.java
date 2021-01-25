package aninfo.model;

import java.util.HashMap;
import java.util.Map;

import aninfo.model.excepciones.PersonaNoEncontradaExcepcion;
import aninfo.model.excepciones.TareaNoEncontradaExcepcion;

public class SistemaCargador {
	private Map<Persona, Map<Tarea, HorasTrabajadas>> personas = new HashMap<Persona, Map<Tarea, HorasTrabajadas>>();

	public void cargarHoras(Persona persona, Tarea tarea, double cantHoras, String fechaTrabajada) {
		if (!personas.containsKey(persona))
			personas.put(persona, new HashMap<Tarea, HorasTrabajadas>());

		Map<Tarea, HorasTrabajadas> tareas = personas.get(persona);
		if (!tareas.containsKey(tarea))
			tareas.put(tarea, new HorasTrabajadas());

		tareas.get(tarea).agregarHoras(cantHoras, fechaTrabajada);
	}

	public double consultarHoras(Persona persona, Tarea tarea) {
		if (!personas.containsKey(persona))
			throw new PersonaNoEncontradaExcepcion(persona);

		Map<Tarea, HorasTrabajadas> tareas = personas.get(persona);
		if (!tareas.containsKey(tarea))
			throw new TareaNoEncontradaExcepcion(tarea);

		return tareas.get(tarea).getCantHorasTot();
	}

	public int obtenerCantTareas(Persona persona) {
		if (!personas.containsKey(persona))
			throw new PersonaNoEncontradaExcepcion(persona);
		return personas.get(persona).size();
	}

	public void eliminarHoras(Persona persona, Tarea tarea, double cantHoras, int idRegistro) {
		if (!personas.containsKey(persona))
			throw new PersonaNoEncontradaExcepcion(persona);

		Map<Tarea, HorasTrabajadas> tareas = personas.get(persona);
		if (!tareas.containsKey(tarea))
			throw new TareaNoEncontradaExcepcion(tarea);

		HorasTrabajadas hs_trabajadas = tareas.get(tarea);
		hs_trabajadas.eliminarHoras(cantHoras, idRegistro);
		if (hs_trabajadas.getCantHorasTot() == 0)
			tareas.remove(tarea);
		if (tareas.size() == 0)
			personas.remove(persona);
	}

}
