package aninfo.model;

import java.util.Objects;

public class Tarea {
    private String titulo;

    public Tarea(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titulo);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (this == other)
            return true;
        if (getClass() != other.getClass())
            return false;
        return Objects.equals(titulo, ((Tarea) other).getTitulo());
    }

}
