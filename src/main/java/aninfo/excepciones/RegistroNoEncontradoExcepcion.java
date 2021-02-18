package aninfo.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RegistroNoEncontradoExcepcion extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RegistroNoEncontradoExcepcion(int idRegistro) {
        super("No se ha encontrado en la tarea el registro de id: " + idRegistro + "!");
    }
}
