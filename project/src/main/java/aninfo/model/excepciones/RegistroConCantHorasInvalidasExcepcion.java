package aninfo.model.excepciones;

public class RegistroConCantHorasInvalidasExcepcion extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RegistroConCantHorasInvalidasExcepcion() {
        super("El registro debe eliminarse porque no tiene mas horas asociadas!");
    }

}
