package aninfo.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import aninfo.modelo.Proyecto;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProyectoNoEncontradoExcepcion extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProyectoNoEncontradoExcepcion(Object o) {
        super("No se ha encontrado el proyecto de código: " + ((Proyecto) o).getCodigo() + "!");
    }
}
