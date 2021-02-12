package aninfo;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@PostMapping("/cargas/{idLegajo}/{idProyecto}/{idTarea}")
	@ResponseStatus(HttpStatus.CREATED)
	public RegistroCarga cargarHoras(@RequestParam int idLegajo,
									 @RequestParam int idProyecto,
									 @RequestParam int idTarea,
									 @RequestParam double cantHoras,
									 @RequestParam String fechaTrabajada) {
		//Llamada a API para obtener Persona, lo voy a simular
		Persona persona = new Persona(idLegajo, "Franco", "Schischlo");
		//Llamada a API para obtener Proyecto, lo voy a simular
		Proyecto proyecto = new Proyecto(idProyecto, "Techly");
		//Llamada a API para obtener Tarea, lo voy a simular
		Tarea tarea = new Tarea(idTarea, "Arreglar bug lista enlazada");						
		
		return servicioHoras.cargarHoras(persona, proyecto, tarea, cantHoras, fechaTrabajada);
	}

	@DeleteMapping("/cargas/{idLegajo}/{idProyecto}/{idTarea}/{idRegistro}")
	public void eliminarRegistro(@PathVariable int idLegajo,
								 @PathVariable int idProyecto,
								 @PathVariable int idTarea,
								 @PathVariable int idRegistro) {
		//Llamada a API para obtener Persona, lo voy a simular
		Persona persona = new Persona(idLegajo, "Franco", "Schischlo");
		//Llamada a API para obtener Proyecto, lo voy a simular
		Proyecto proyecto = new Proyecto(idProyecto, "Techly");
		//Llamada a API para obtener Tarea, lo voy a simular
		Tarea tarea = new Tarea(idTarea, "Arreglar bug lista enlazada");

		servicioHoras.eliminarRegistro(persona, proyecto, tarea, idRegistro);
	}

	@GetMapping("/cargas/{idLegajo}/{idProyecto}/{idTarea}")
	public Collection<RegistroCarga> obtenerRegistros(
									@PathVariable int idLegajo,
									@PathVariable int idProyecto,
									@PathVariable int idTarea) {
		//Llamada a API para obtener Persona, lo voy a simular
		Persona persona = new Persona(idLegajo, "Franco", "Schischlo");
		//Llamada a API para obtener Proyecto, lo voy a simular
		Proyecto proyecto = new Proyecto(idProyecto, "Techly");
		//Llamada a API para obtener Tarea, lo voy a simular
		Tarea tarea = new Tarea(idTarea, "Arreglar bug lista enlazada");

		return servicioHoras.obtenerRegistros(persona, proyecto, tarea);
	}

	@GetMapping("/cargas/{idLegajo}/{idProyecto}/{idTarea}/{idRegistro}")
	public RegistroCarga obtenerRegistro(@PathVariable int idLegajo,
										 @PathVariable int idProyecto,
										 @PathVariable int idTarea,
										 @PathVariable int idRegistro) {
		//Llamada a API para obtener Persona, lo voy a simular
		Persona persona = new Persona(idLegajo, "Franco", "Schischlo");
		//Llamada a API para obtener Proyecto, lo voy a simular
		Proyecto proyecto = new Proyecto(idProyecto, "Techly");
		//Llamada a API para obtener Tarea, lo voy a simular
		Tarea tarea = new Tarea(idTarea, "Arreglar bug lista enlazada");

		return servicioHoras.obtenerRegistro(persona, proyecto, tarea, idRegistro);
	}

	@PutMapping("/cargas/{idLegajo}/{idProyecto}/{idTarea}/{idRegistro}")
	public RegistroCarga actualizarRegistro(@PathVariable int idLegajo,
										    @PathVariable int idProyecto,
										 	@PathVariable int idTarea,
											@PathVariable int idRegistro,
											@RequestParam int cantHoras) {
		//Llamada a API para obtener Persona, lo voy a simular
		Persona persona = new Persona(idLegajo, "Franco", "Schischlo");
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

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build();
	}
}
