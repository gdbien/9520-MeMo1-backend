package aninfo.excepciones;

public class RegistroNoEncontradoExcepcion extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RegistroNoEncontradoExcepcion(int idRegistro) {
        super("No se ha encontrado en la tarea el registro de id: " + idRegistro + "!");
    }
}
