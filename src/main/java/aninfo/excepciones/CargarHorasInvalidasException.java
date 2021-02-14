package aninfo.excepciones;

public class CargarHorasInvalidasException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CargarHorasInvalidasException() {
        super("La cantidad de horas a cargar debe ser mayor que 0");
    }
}

