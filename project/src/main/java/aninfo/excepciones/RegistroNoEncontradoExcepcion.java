package aninfo.excepciones;

import aninfo.modelo.RegistroCarga;

public class RegistroNoEncontradoExcepcion extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RegistroNoEncontradoExcepcion(Object o) {
        super("No se ha encontrado en la tarea el registro de id: " + ((RegistroCarga)o).getId() + "!");
    }
}
