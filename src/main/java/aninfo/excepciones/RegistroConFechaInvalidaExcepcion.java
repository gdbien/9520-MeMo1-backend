package aninfo.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RegistroConFechaInvalidaExcepcion extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RegistroConFechaInvalidaExcepcion() {
        super("La fecha trabajada no puede ser posterior a la fecha de carga!");
    }
}
