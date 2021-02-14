package aninfo.repositorio;

import aninfo.modelo.HorasTrabajadas;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositorioHorasTrabajadas extends CrudRepository<HorasTrabajadas, Integer> {

    HorasTrabajadas findHorasTrabajadasById(int id);
    HorasTrabajadas findHorasTrabajadasByPersonaAndProyectoAndTareaAndId(int idPersona, int idProyecto, int idTarea, int id);
    Collection<HorasTrabajadas> findAllHorasTrabajadasByPersonaAndProyectoAndTarea(int idPersona, int idProyecto, int idTarea);

}