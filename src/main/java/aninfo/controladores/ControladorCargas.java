package aninfo.controladores;

import java.util.Collection;

import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import aninfo.excepciones.ProyectoNoEncontradoExcepcion;
import aninfo.excepciones.TareaNoEncontradaExcepcion;
import aninfo.modelo.Persona;
import aninfo.modelo.Proyecto;
import aninfo.modelo.RegistroCarga;
import aninfo.modelo.Tarea;
import aninfo.servicio.ServicioCargas;
import aninfo.servicio.ServicioPersonas;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ControladorCargas {
    @Autowired
    private ServicioCargas servicioHoras;
    
    @Autowired
    private ServicioPersonas servicioPersonas;

	@PostMapping("/cargas/personas/{idPersona}/proyectos/{idProyecto}/tareas/{idTarea}")
	@ResponseStatus(HttpStatus.CREATED)
	public RegistroCarga cargarHoras(@PathVariable int idPersona,
									 @PathVariable int idProyecto,
									 @PathVariable int idTarea,
									 @RequestBody CargaHora carga) {	
		Persona persona = servicioPersonas.obtenerPersona(idPersona);
		Proyecto proyecto = buscarProyecto(idProyecto);
		Tarea tarea = buscarTarea(idProyecto, idTarea);
		return servicioHoras.cargarHoras(persona, proyecto, tarea, carga.getCantidadHoras(), carga.getFechaTrabajada());
	}

	@DeleteMapping("/cargas/personas/{idPersona}/proyectos/{idProyecto}/tareas/{idTarea}/registros/{idRegistro}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarRegistro(@PathVariable int idPersona,
								 @PathVariable int idProyecto,
								 @PathVariable int idTarea,
								 @PathVariable int idRegistro) {	
		Persona persona = servicioPersonas.obtenerPersona(idPersona);
		Proyecto proyecto = buscarProyecto(idProyecto);
		Tarea tarea = buscarTarea(idProyecto, idTarea);
		servicioHoras.eliminarRegistro(persona, proyecto, tarea, idRegistro);
	}

	@GetMapping("/cargas/personas/{idPersona}/proyectos/{idProyecto}/tareas/{idTarea}/registros")
	public Collection<RegistroCarga> obtenerRegistros(@PathVariable int idPersona,
													  @PathVariable int idProyecto,
													  @PathVariable int idTarea) {
		Persona persona = servicioPersonas.obtenerPersona(idPersona);
		Proyecto proyecto = buscarProyecto(idProyecto);
		Tarea tarea = buscarTarea(idProyecto, idTarea);
		return servicioHoras.obtenerRegistros(persona, proyecto, tarea);
	}

	@GetMapping("/cargas/personas/{idPersona}/proyectos/{idProyecto}/tareas/{idTarea}/registros/{idRegistro}")
	public RegistroCarga obtenerRegistro(@PathVariable int idPersona,
										 @PathVariable int idProyecto,
										 @PathVariable int idTarea,
										 @PathVariable int idRegistro) {
		Persona persona = servicioPersonas.obtenerPersona(idPersona);
		Proyecto proyecto = buscarProyecto(idProyecto);
		Tarea tarea = buscarTarea(idProyecto, idTarea);
		return servicioHoras.obtenerRegistro(persona, proyecto, tarea, idRegistro);
	}

	@PatchMapping("/cargas/personas/{idPersona}/proyectos/{idProyecto}/tareas/{idTarea}/registros/{idRegistro}")
	public RegistroCarga actualizarRegistro(@PathVariable int idPersona,
										    @PathVariable int idProyecto,
										 	@PathVariable int idTarea,
											@PathVariable int idRegistro,
											@RequestBody double cantHoras) {	
		Persona persona = servicioPersonas.obtenerPersona(idPersona);
		Proyecto proyecto = buscarProyecto(idProyecto);
		Tarea tarea = buscarTarea(idProyecto, idTarea);
		return servicioHoras.actualizarRegistro(persona, proyecto, tarea, idRegistro, cantHoras);
    }
    
	private Proyecto buscarProyecto(int idProyecto) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Proyecto> respProyecto;

		try {
			respProyecto = restTemplate.getForEntity(
                					"https://psa-projects.herokuapp.com/projects/project?id={idProyecto}",
									Proyecto.class, idProyecto);							
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND)
				throw new ProyectoNoEncontradoExcepcion(idProyecto);
			throw e;
		}
		
		return respProyecto.getBody();
	}

	private Tarea buscarTarea(int idProyecto, int idTarea) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ObjectNode> respTarea;

		try {
			respTarea = restTemplate.getForEntity(
			"https://psa-projects.herokuapp.com/tasks/task?id={idTarea}",
				ObjectNode.class, idTarea);	
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND)
				throw new TareaNoEncontradaExcepcion(idTarea);
			throw e;
		} 

		ObjectNode nodeTarea = respTarea.getBody();
		if (nodeTarea.get("projectId").asInt() != idProyecto)
			throw new TareaNoEncontradaExcepcion(idProyecto, idTarea);	

		return new Tarea(nodeTarea.get("taskId").asInt(), nodeTarea.get("name").asText());
	}

	//Wrapper para POST cargarHoras
	private static class CargaHora {
		private double cantidadHoras;
		private String fechaTrabajada;
		
		public CargaHora() {
		}

		public double getCantidadHoras() {
			return cantidadHoras;
		}

		public String getFechaTrabajada() {
			return fechaTrabajada;
		}
	}
}
