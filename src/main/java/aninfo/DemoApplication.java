package aninfo;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import aninfo.modelo.Persona;
import aninfo.modelo.Proyecto;
import aninfo.modelo.RegistroCarga;
import aninfo.modelo.Tarea;
import aninfo.servicio.ServicioHoras;
import aninfo.servicio.ServicioPersonas;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

	@Autowired
	private ServicioHoras servicioHoras;

	@Autowired
	private ServicioPersonas servicioPersonas;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostMapping("/cargas/personas/{idPersona}/proyectos/{idProyecto}/tareas/{idTarea}")
	@ResponseStatus(HttpStatus.CREATED)
	public RegistroCarga cargarHoras(@PathVariable int idPersona,
									   @PathVariable int idProyecto,
									   @PathVariable int idTarea,
									   @RequestBody CargaHora carga) {
		
		Persona persona = servicioPersonas.obtenerPersona(idPersona);
		//Llamada a API para obtener Proyecto, lo voy a simular
		Proyecto proyecto = new Proyecto(idProyecto, "Techly");
		//Llamada a API para obtener Tarea, lo voy a simular
		Tarea tarea = new Tarea(idTarea, "Arreglar bug lista enlazada");

		return servicioHoras.cargarHoras(persona, proyecto, tarea, carga.getCantidadHoras(), carga.getFechaTrabajada());
	}

	@DeleteMapping("/cargas/personas/{idPersona}/proyectos/{idProyecto}/tareas/{idTarea}/registros/{idRegistro}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarRegistro(@PathVariable int idPersona,
								 @PathVariable int idProyecto,
								 @PathVariable int idTarea,
								 @PathVariable int idRegistro) {
		
		Persona persona = servicioPersonas.obtenerPersona(idPersona);
		//Llamada a API para obtener Proyecto, lo voy a simular
		Proyecto proyecto = new Proyecto(idProyecto, "Techly");
		//Llamada a API para obtener Tarea, lo voy a simular
		Tarea tarea = new Tarea(idTarea, "Arreglar bug lista enlazada");

		servicioHoras.eliminarRegistro(persona, proyecto, tarea, idRegistro);
	}

	@GetMapping("/cargas/personas/{idPersona}/proyectos/{idProyecto}/tareas/{idTarea}/registros")
	public Collection<RegistroCarga> obtenerRegistros(@PathVariable int idPersona,
														@PathVariable int idProyecto,
														@PathVariable int idTarea) {
		Persona persona = servicioPersonas.obtenerPersona(idPersona);
		//Llamada a API para obtener Proyecto, lo voy a simular
		Proyecto proyecto = new Proyecto(idProyecto, "Techly");
		//Llamada a API para obtener Tarea, lo voy a simular
		Tarea tarea = new Tarea(idTarea, "Arreglar bug lista enlazada");

		return servicioHoras.obtenerRegistros(persona, proyecto, tarea);
	}

	@GetMapping("/cargas/personas/{idPersona}/proyectos/{idProyecto}/tareas/{idTarea}/registros/{idRegistro}")
	public RegistroCarga obtenerRegistro(@PathVariable int idPersona,
										   @PathVariable int idProyecto,
										   @PathVariable int idTarea,
										   @PathVariable int idRegistro) {
		
		Persona persona = servicioPersonas.obtenerPersona(idPersona);
		//Llamada a API para obtener Proyecto, lo voy a simular
		Proyecto proyecto = new Proyecto(idProyecto, "Techly");
		//Llamada a API para obtener Tarea, lo voy a simular
		Tarea tarea = new Tarea(idTarea, "Arreglar bug lista enlazada");

		return servicioHoras.obtenerRegistro(persona, proyecto, tarea, idRegistro);
	}

	@PatchMapping("/cargas/personas/{idPersona}/proyectos/{idProyecto}/tareas/{idTarea}/registros/{idRegistro}")
	public RegistroCarga actualizarRegistro(@PathVariable int idPersona,
										      @PathVariable int idProyecto,
										 	  @PathVariable int idTarea,
											  @PathVariable int idRegistro,
											  @RequestBody int cantHoras) {
		
		Persona persona = servicioPersonas.obtenerPersona(idPersona);
		//Llamada a API para obtener Proyecto, lo voy a simular
		Proyecto proyecto = new Proyecto(idProyecto, "Techly");
		//Llamada a API para obtener Tarea, lo voy a simular
		Tarea tarea = new Tarea(idTarea, "Arreglar bug lista enlazada");

		return servicioHoras.actualizarRegistro(persona, proyecto, tarea, idRegistro, cantHoras);
	}
	
	@GetMapping("/personas")
	public Collection<Persona> obtenerPersonas() {
		return servicioPersonas.obtenerPersonas();
	}

	@GetMapping("/personas/{idPersona}")
	public Persona obtenerPersona(@PathVariable int idPersona) {
		return servicioPersonas.obtenerPersona(idPersona);
	}

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build();
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
