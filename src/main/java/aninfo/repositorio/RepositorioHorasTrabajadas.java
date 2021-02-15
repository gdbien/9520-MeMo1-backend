package aninfo.repositorio;

import aninfo.modelo.HorasTrabajadas;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepositorioHorasTrabajadas extends CrudRepository<HorasTrabajadas, Integer> {

    HorasTrabajadas findHorasTrabajadasById(int id);

    HorasTrabajadas findHorasTrabajadasByIdPersonaAndIdProyectoAndIdTareaAndId(int idPersona, int idProyecto, int idTarea, int id);
    
    Collection<HorasTrabajadas> findAllHorasTrabajadasByIdPersonaAndIdProyectoAndIdTarea(int idPersona, int idProyecto, int idTarea);

    boolean existsByIdPersona(int idPersona);

	boolean existsByIdProyecto(int idProyecto);

	boolean existsByIdProyectoAndIdTarea(int idProyecto , int idTarea);

	boolean existsByIdPersonaAndIdProyecto(int numLegajo, int idProyecto);

	boolean existsByIdPersonaAndIdProyectoAndIdTarea(int numLegajo, int idProyecto, int idTarea);

	boolean existsByIdPersonaAndIdProyectoAndIdTareaAndId(int numLegajo, int idProyecto, int idTarea, int idRegistro); 
}