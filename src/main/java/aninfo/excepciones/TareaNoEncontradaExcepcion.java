package aninfo.excepciones;

import aninfo.modelo.Tarea;

public class TareaNoEncontradaExcepcion extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TareaNoEncontradaExcepcion(Object o) {
        super("No se ha encontrado la tarea de código: " + ((Tarea) o).getCodigo() + "!");
    }
}
