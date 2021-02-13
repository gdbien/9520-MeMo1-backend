package aninfo.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import aninfo.excepciones.PersonaNoEncontradaExcepcion;
import aninfo.excepciones.ProyectoNoEncontradoExcepcion;
import aninfo.excepciones.TareaNoEncontradaExcepcion;
import aninfo.modelo.HorasTrabajadas;
import aninfo.modelo.Persona;
import aninfo.modelo.Proyecto;
import aninfo.modelo.RegistroCarga;
import aninfo.modelo.Tarea;

@Service
public class ServicioHoras {
	private Map<Persona, Map<Proyecto, Map<Tarea, HorasTrabajadas>>> personas = new HashMap<Persona, Map<Proyecto, Map<Tarea, HorasTrabajadas>>>();

	public RegistroCarga cargarHoras(Persona persona, Proyecto proyecto, Tarea tarea, double cantHoras, String fechaTrabajada) {
		if (!personas.containsKey(persona))
			personas.put(persona, new HashMap<Proyecto, Map<Tarea, HorasTrabajadas>>());

		Map<Proyecto, Map<Tarea, HorasTrabajadas>> proyectos = personas.get(persona);
		if (!proyectos.containsKey(proyecto))
			proyectos.put(proyecto, new HashMap<Tarea, HorasTrabajadas>());

		Map<Tarea, HorasTrabajadas> tareas = proyectos.get(proyecto);
		if (!tareas.containsKey(tarea))
			tareas.put(tarea, new HorasTrabajadas());

		return tareas.get(tarea).agregarHoras(cantHoras, fechaTrabajada);
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

		HorasTrabajadas horasTrabajadas = tareas.get(tarea);
		horasTrabajadas.eliminarHoras(cantHoras, idRegistro);
		if (horasTrabajadas.getCantHorasTot() == 0)
			tareas.remove(tarea);
		if (tareas.size() == 0)
			proyectos.remove(proyecto);
		if (proyectos.size() == 0)
			personas.remove(persona);
	}

	public void eliminarRegistro(Persona persona, Proyecto proyecto, Tarea tarea, int idRegistro) {
		if (!personas.containsKey(persona))
			throw new PersonaNoEncontradaExcepcion(persona);

		Map<Proyecto, Map<Tarea, HorasTrabajadas>> proyectos = personas.get(persona);
		if (!proyectos.containsKey(proyecto))
			throw new ProyectoNoEncontradoExcepcion(proyecto);

		Map<Tarea, HorasTrabajadas> tareas = proyectos.get(proyecto);
		if (!tareas.containsKey(tarea))
			throw new TareaNoEncontradaExcepcion(tarea);

		HorasTrabajadas horasTrabajadas = tareas.get(tarea);
		horasTrabajadas.eliminar(idRegistro);
		if (horasTrabajadas.getCantHorasTot() == 0)
			tareas.remove(tarea);
		if (tareas.size() == 0)
			proyectos.remove(proyecto);
		if (proyectos.size() == 0)
			personas.remove(persona);
	}

	public List<RegistroCarga> obtenerRegistros(Persona persona, Proyecto proyecto, Tarea tarea) {
		if (!personas.containsKey(persona))
			throw new PersonaNoEncontradaExcepcion(persona);

		Map<Proyecto, Map<Tarea, HorasTrabajadas>> proyectos = personas.get(persona);
		if (!proyectos.containsKey(proyecto))
			throw new ProyectoNoEncontradoExcepcion(proyecto);

		Map<Tarea, HorasTrabajadas> tareas = proyectos.get(proyecto);
		if (!tareas.containsKey(tarea))
			throw new TareaNoEncontradaExcepcion(tarea);

		return tareas.get(tarea).getRegistros();
	}

	public RegistroCarga obtenerRegistro(Persona persona, Proyecto proyecto, Tarea tarea, int idRegistro) {
		if (!personas.containsKey(persona))
			throw new PersonaNoEncontradaExcepcion(persona);

		Map<Proyecto, Map<Tarea, HorasTrabajadas>> proyectos = personas.get(persona);
		if (!proyectos.containsKey(proyecto))
			throw new ProyectoNoEncontradoExcepcion(proyecto);

		Map<Tarea, HorasTrabajadas> tareas = proyectos.get(proyecto);
		if (!tareas.containsKey(tarea))
			throw new TareaNoEncontradaExcepcion(tarea);

		HorasTrabajadas horasTrabajadas = tareas.get(tarea);
		return horasTrabajadas.obtenerRegistro(idRegistro);
	}

	public RegistroCarga actualizarRegistro(Persona persona, Proyecto proyecto, Tarea tarea, int idRegistro, int cantHoras) {
		RegistroCarga regCarga = obtenerRegistro(persona, proyecto, tarea, idRegistro);
		regCarga.setCantHoras(cantHoras);
		return regCarga;
	}



}
