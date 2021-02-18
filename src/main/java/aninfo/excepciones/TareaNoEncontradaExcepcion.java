package aninfo.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import aninfo.modelo.Tarea;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TareaNoEncontradaExcepcion extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TareaNoEncontradaExcepcion(Object o) {
        super("No se ha encontrado la tarea de código: " + ((Tarea) o).getCodigo() + "!");
    }
}
