package aninfo.excepciones;

import aninfo.modelo.Proyecto;

public class ProyectoNoEncontradoExcepcion extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ProyectoNoEncontradoExcepcion(Object o) {
        super("No se ha encontrado el proyecto de codigo: " + ((Proyecto) o).getCodigo() + "!");
    }
}
