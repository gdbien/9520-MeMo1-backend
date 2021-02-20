package aninfo.modelo;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Proyecto {
    @JsonAlias("codeId")
    private int codigo;

    @JsonAlias("name")
    private String nombre;

    public Proyecto(int codigo, String nombre) {
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
        return Objects.equals(codigo, ((Proyecto) other).getCodigo());
    }

    public Proyecto() {
    }

}
