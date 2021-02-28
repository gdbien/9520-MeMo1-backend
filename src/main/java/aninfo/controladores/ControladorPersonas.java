package aninfo.controladores;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import aninfo.modelo.Persona;
import aninfo.servicio.ServicioPersonas;

@RestController
@CrossOrigin(origins = "*")
public class ControladorPersonas {
    @Autowired
    private ServicioPersonas servicioPersonas;
    
    @GetMapping("/personas")
	public Collection<Persona> obtenerPersonas() {
		return servicioPersonas.obtenerPersonas();
	}

	@GetMapping("/personas/{idPersona}")
	public Persona obtenerPersona(@PathVariable int idPersona) {
		return servicioPersonas.obtenerPersona(idPersona);
	}
}
