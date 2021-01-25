package aninfo.model;

import java.util.HashMap;
import java.util.Map;

import aninfo.model.excepciones.RegistroConCantHorasInvalidasExcepcion;
import aninfo.model.excepciones.RegistroNoEncontradoExcepcion;

public class HorasTrabajadas {
    private double cantHorasTot;
    private Map<Integer, RegistroCarga> registros;

    public HorasTrabajadas() {
        cantHorasTot = 0;
        registros = new HashMap<Integer, RegistroCarga>();
    }

    public void agregarHoras(double cantHoras, String fechaTrabajada) {
        cantHorasTot += cantHoras;
        RegistroCarga reg_carga = new RegistroCarga(cantHoras, fechaTrabajada);
        registros.put(reg_carga.getId(), reg_carga);
    }

    public double getCantHorasTot() {
        return cantHorasTot;
    }

    public void eliminarHoras(double cantHoras, int idRegistro) {
        if (!registros.containsKey(idRegistro))
            throw new RegistroNoEncontradoExcepcion(idRegistro);

        RegistroCarga reg_carga = registros.get(idRegistro);
        Double cantHorasActual = reg_carga.getCantHoras();
        try {
            reg_carga.eliminarHoras(cantHoras);
            cantHorasTot -= cantHoras;
        } catch (RegistroConCantHorasInvalidasExcepcion e) {
            registros.remove(idRegistro);
            cantHorasTot -= cantHorasActual;
        }

    }

}
