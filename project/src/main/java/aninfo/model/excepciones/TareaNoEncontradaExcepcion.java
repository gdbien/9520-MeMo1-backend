package aninfo.model.excepciones;

import aninfo.model.Tarea;

public class TareaNoEncontradaExcepcion extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TareaNoEncontradaExcepcion(Object o) {
        super("No se ha encontrado la tarea de titulo: " + ((Tarea) o).getTitulo() + "!");
    }
}
