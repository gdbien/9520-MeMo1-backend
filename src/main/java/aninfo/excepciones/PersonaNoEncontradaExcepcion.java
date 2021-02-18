package aninfo.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PersonaNoEncontradaExcepcion extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PersonaNoEncontradaExcepcion(int numLegajo) {
        super("No se ha encontrado la persona de legajo: " + numLegajo + "!");
    }
}
