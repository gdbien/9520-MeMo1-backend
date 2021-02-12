package aninfo.modelo;

import java.util.Objects;

public class Tarea {
    private int codigo;
    private String nombre;

    public Tarea(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (this == other)
            return true;
        if (getClass() != other.getClass())
            return false;
        return Objects.equals(codigo, ((Tarea) other).getCodigo());
    }
}
