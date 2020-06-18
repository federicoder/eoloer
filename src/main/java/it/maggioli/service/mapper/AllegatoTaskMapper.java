package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.AllegatoTaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AllegatoTask} and its DTO {@link AllegatoTaskDTO}.
 */
@Mapper(componentModel = "spring", uses = {TipoAllegatoMapper.class, TaskMapper.class})
public interface AllegatoTaskMapper extends EntityMapper<AllegatoTaskDTO, AllegatoTask> {

    @Mapping(source = "idTipoAllegato.id", target = "idTipoAllegatoId")
    @Mapping(source = "idTask.id", target = "idTaskId")
    @Mapping(source = "allegatoTask.id", target = "allegatoTaskId")
    AllegatoTaskDTO toDto(AllegatoTask allegatoTask);

    @Mapping(target = "idAllegatoTasks", ignore = true)
    @Mapping(target = "removeIdAllegatoTask", ignore = true)
    @Mapping(source = "idTipoAllegatoId", target = "idTipoAllegato")
    @Mapping(source = "idTaskId", target = "idTask")
    @Mapping(source = "allegatoTaskId", target = "allegatoTask")
    AllegatoTask toEntity(AllegatoTaskDTO allegatoTaskDTO);

    default AllegatoTask fromId(Long id) {
        if (id == null) {
            return null;
        }
        AllegatoTask allegatoTask = new AllegatoTask();
        allegatoTask.setId(id);
        return allegatoTask;
    }
}
