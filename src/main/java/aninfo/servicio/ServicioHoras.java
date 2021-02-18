package aninfo.servicio;

import java.util.ArrayList;
import java.util.Collection;
//import org.graalvm.compiler.hotspot.replacements.HotSpotArraySubstitutions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aninfo.excepciones.PersonaNoEncontradaExcepcion;
import aninfo.excepciones.ProyectoNoEncontradoExcepcion;
import aninfo.excepciones.RegistroNoEncontradoExcepcion;
import aninfo.excepciones.TareaNoEncontradaExcepcion;
import aninfo.modelo.HorasTrabajadas;
import aninfo.modelo.Persona;
import aninfo.modelo.Proyecto;
import aninfo.modelo.RegistroCarga;
import aninfo.modelo.Tarea;
import aninfo.repositorio.RepositorioHorasTrabajadas;

@Service
public class ServicioHoras {
    @Autowired
	private RepositorioHorasTrabajadas repositorioHorasTrabajadas;
	
	public RegistroCarga cargarHoras(Persona persona, Proyecto proyecto, Tarea tarea, double cantHoras, String fechaTrabajada) {
		HorasTrabajadas horas = new HorasTrabajadas(persona.getNumLegajo(), proyecto.getCodigo(), tarea.getCodigo(), cantHoras, fechaTrabajada);
		repositorioHorasTrabajadas.save(horas);
		return new RegistroCarga(horas.getId(), horas.getCantHoras(), horas.getFechaTrabajada(), horas.getFechaDeCarga());
	}

	public void eliminarRegistro(Persona persona, Proyecto proyecto, Tarea tarea, int idRegistro) {
		validarPersonaProyectoTareaYRegistro(persona, proyecto, tarea, idRegistro);
		repositorioHorasTrabajadas.deleteById(idRegistro);
	}

	public Collection<RegistroCarga> obtenerRegistros(Persona persona, Proyecto proyecto, Tarea tarea) {
		validarPersonaProyectoYTarea(persona, proyecto, tarea);
		Collection<HorasTrabajadas> horas = new ArrayList<>();
        repositorioHorasTrabajadas.findAllHorasTrabajadasByIdPersonaAndIdProyectoAndIdTarea(
			persona.getNumLegajo(), proyecto.getCodigo(), tarea.getCodigo()).forEach(horas::add);
		Collection<RegistroCarga> regCargas = new ArrayList<>();
		for (HorasTrabajadas hora: horas) {
			regCargas.add(new RegistroCarga(hora.getId(), hora.getCantHoras(), hora.getFechaTrabajada(), hora.getFechaDeCarga()));
		}	
		return regCargas;
	}

	/* 
		Se fija en el repositorio si existe algun registro que tenga el idPersona, idProyecto 
		e idTarea correspondiente. Si en alguno de los pasos no encuentra un registro que contenga 
		alguno de esos ids entonces lanza una excepción.
	*/
	private void validarPersonaProyectoYTarea(Persona persona, Proyecto proyecto, Tarea tarea) {
		if (!repositorioHorasTrabajadas.existsByIdPersona(persona.getNumLegajo()))
			throw new PersonaNoEncontradaExcepcion(persona.getNumLegajo());
		if (!repositorioHorasTrabajadas.existsByIdPersonaAndIdProyecto(persona.getNumLegajo(), 
																	   proyecto.getCodigo()))
			throw new ProyectoNoEncontradoExcepcion(proyecto);
		if (!repositorioHorasTrabajadas.existsByIdPersonaAndIdProyectoAndIdTarea(persona.getNumLegajo(),
																				 proyecto.getCodigo(),
																				 tarea.getCodigo()))
			throw new TareaNoEncontradaExcepcion(tarea);
	}

	private void validarPersonaProyectoTareaYRegistro(Persona persona, Proyecto proyecto, Tarea tarea, int idRegistro) {
		validarPersonaProyectoYTarea(persona, proyecto, tarea);
		if (!repositorioHorasTrabajadas.existsByIdPersonaAndIdProyectoAndIdTareaAndId(persona.getNumLegajo(),
																					 proyecto.getCodigo(),  
																					 tarea.getCodigo(),  
																					 idRegistro))
			throw new RegistroNoEncontradoExcepcion(idRegistro);
	}

	public RegistroCarga obtenerRegistro(Persona persona, Proyecto proyecto, Tarea tarea, int idRegistro) {	
		validarPersonaProyectoTareaYRegistro(persona, proyecto, tarea, idRegistro);
		HorasTrabajadas horas = repositorioHorasTrabajadas.findHorasTrabajadasById(idRegistro);
		return new RegistroCarga(horas.getId(), horas.getCantHoras(), horas.getFechaTrabajada(), horas.getFechaDeCarga()); 
	}

	public RegistroCarga actualizarRegistro(Persona persona, Proyecto proyecto, Tarea tarea, int idRegistro, double cantHoras) {
		validarPersonaProyectoTareaYRegistro(persona, proyecto, tarea, idRegistro);
       	HorasTrabajadas horas = repositorioHorasTrabajadas.findHorasTrabajadasById(idRegistro);
        horas.setCantHoras(cantHoras);
		repositorioHorasTrabajadas.save(horas);
		return new RegistroCarga(horas.getId(), horas.getCantHoras(), horas.getFechaTrabajada(), horas.getFechaDeCarga());
	}

	//Exclusivo BDD
	public int obtenerCantTareas(Persona persona) {
		if (!repositorioHorasTrabajadas.existsByIdPersona(persona.getNumLegajo()))
			throw new PersonaNoEncontradaExcepcion(persona.getNumLegajo());
		return repositorioHorasTrabajadas.contarTareasTrabajadasDePersona(persona.getNumLegajo());
	}

	//Exclusivo BDD
	public void limpiar() {
		repositorioHorasTrabajadas.deleteAll();
	}

	/*	
		Exclusivo BDD
		Devuelve la cantidad total de horas que una persona trabajó en una tarea específica.
	*/
	public double consultarHoras(Persona persona, Proyecto proyecto, Tarea tarea) {
		validarPersonaProyectoYTarea(persona, proyecto, tarea);
		double totalHoras = 0;
		Collection<HorasTrabajadas> horas = new ArrayList<HorasTrabajadas>();
        repositorioHorasTrabajadas.findAllHorasTrabajadasByIdPersonaAndIdProyectoAndIdTarea(
			persona.getNumLegajo(), proyecto.getCodigo(), tarea.getCodigo()).forEach(horas::add);
		for (HorasTrabajadas horaTrabajada: horas) {
			totalHoras += horaTrabajada.getCantHoras();
		}
		return totalHoras;
	}
}
