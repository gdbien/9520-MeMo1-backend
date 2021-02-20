package aninfo.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TareaNoEncontradaExcepcion extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TareaNoEncontradaExcepcion(int idTarea) {
        super("No se ha encontrado la tarea de código: " + idTarea + "!");
    }

    public TareaNoEncontradaExcepcion(int idProyecto, int idTarea) {
        super("No se ha encontrado dentro del proyecto de código: " + idProyecto + " la tarea de código: " + idTarea + "!");
    }
}
