package aninfo.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProyectoNoEncontradoExcepcion extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProyectoNoEncontradoExcepcion(int idProyecto) {
        super("No se ha encontrado el proyecto de código: " + idProyecto + "!");
    }
}
