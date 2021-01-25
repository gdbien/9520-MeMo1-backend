package aninfo.model;

import java.util.Objects;

public class Persona {
    private String nombre;
    private String apellido;
    private int numLegajo;

    public Persona(String nombre, String apellido, int numLegajo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numLegajo = numLegajo;
    }

    public int getnumLegajo() {
        return numLegajo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numLegajo);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (this == other)
            return true;
        if (getClass() != other.getClass())
            return false;
        return Objects.equals(numLegajo, ((Persona) other).getnumLegajo());
    }

    public String getNombre() {
        return nombre;
    }
}
