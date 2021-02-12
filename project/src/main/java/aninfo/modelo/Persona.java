package aninfo.modelo;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Persona {
    @JsonAlias("Nombre")
    private String nombre;

    @JsonAlias("Apellido")
    private String apellido;

    @JsonAlias("legajo")
    private int numLegajo;

    public Persona() {
    }

    public Persona(int numLegajo, String nombre, String apellido) {
        this.numLegajo = numLegajo;
        this.nombre = nombre;
        this.apellido = apellido;
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
        return Objects.equals(numLegajo, ((Persona) other).getNumLegajo());
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNumLegajo() {
        return numLegajo;
    }

    public void setNumLegajo(int numLegajo) {
        this.numLegajo = numLegajo;
    }

}
