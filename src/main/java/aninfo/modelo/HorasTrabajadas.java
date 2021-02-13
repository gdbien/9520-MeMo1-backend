package aninfo.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aninfo.excepciones.RegistroConCantHorasInvalidasExcepcion;
import aninfo.excepciones.RegistroNoEncontradoExcepcion;

public class HorasTrabajadas {
    private double cantHorasTot;
    private Map<Integer, RegistroCarga> registros;

    public HorasTrabajadas() {
        cantHorasTot = 0;
        registros = new HashMap<Integer, RegistroCarga>();
    }

    public RegistroCarga agregarHoras(double cantHoras, String fechaTrabajada) {
        cantHorasTot += cantHoras;
        RegistroCarga regCarga = new RegistroCarga(cantHoras, fechaTrabajada);
        registros.put(regCarga.getId(), regCarga);
        return regCarga;
    }

    public double getCantHorasTot() {
        return cantHorasTot;
    }

    public void eliminarHoras(double cantHoras, int idRegistro) {
        if (!registros.containsKey(idRegistro))
            throw new RegistroNoEncontradoExcepcion(idRegistro);

        RegistroCarga regCarga = registros.get(idRegistro);
        Double cantHorasActual = regCarga.getCantHoras();
        try {
            regCarga.eliminarHoras(cantHoras);
            cantHorasTot -= cantHoras;
        } catch (RegistroConCantHorasInvalidasExcepcion e) {
            registros.remove(idRegistro);
            cantHorasTot -= cantHorasActual;
        }
    }

	public void eliminar(int idRegistro) {
        if (!registros.containsKey(idRegistro))
            throw new RegistroNoEncontradoExcepcion(idRegistro);
        RegistroCarga regCarga = registros.get(idRegistro);
        cantHorasTot -= regCarga.getCantHoras();
        registros.remove(idRegistro);
	}

	public List<RegistroCarga> getRegistros() {
		return new ArrayList<RegistroCarga>(registros.values());
	}

	public RegistroCarga obtenerRegistro(int idRegistro) {
        if (!registros.containsKey(idRegistro))
            throw new RegistroNoEncontradoExcepcion(idRegistro);
        return registros.get(idRegistro);
	}
}
