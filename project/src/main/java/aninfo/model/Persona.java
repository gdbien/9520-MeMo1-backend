package aninfo.model;

import java.util.Objects;

public class Persona {
    private String nombre;
    private String apellido;
    private String dni;

    public Persona(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (this == other)
            return true;
        if (getClass() != other.getClass())
            return false;
        return Objects.equals(dni, ((Persona) other).getDni());
    }

    public String getNombre() {
        return nombre;
    }
}
