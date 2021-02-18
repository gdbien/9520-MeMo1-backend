package aninfo.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RegistroConCantHorasInvalidasExcepcion extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RegistroConCantHorasInvalidasExcepcion() {
        super("La cantidad de horas debe ser mayor a 0!");
    }
}
