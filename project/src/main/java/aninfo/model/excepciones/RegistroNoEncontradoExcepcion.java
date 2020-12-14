package aninfo.model.excepciones;

public class RegistroNoEncontradoExcepcion extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RegistroNoEncontradoExcepcion(Object o) {
        super("No se ha encontrado en la tarea el registro: " + o + "!");
    }
}
