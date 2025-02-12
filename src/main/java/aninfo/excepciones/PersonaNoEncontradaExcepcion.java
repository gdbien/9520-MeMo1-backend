package aninfo.excepciones;

import aninfo.modelo.Persona;

public class PersonaNoEncontradaExcepcion extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PersonaNoEncontradaExcepcion(Object o) {
        super("No se ha encontrado la persona de nombre: " + ((Persona) o).getNombre() + "!");
    }
}
