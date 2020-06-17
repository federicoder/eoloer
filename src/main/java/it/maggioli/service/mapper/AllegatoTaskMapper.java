package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.AllegatoTaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AllegatoTask} and its DTO {@link AllegatoTaskDTO}.
 */
@Mapper(componentModel = "spring", uses = {TipoAllegatoMapper.class, TaskMapper.class})
public interface AllegatoTaskMapper extends EntityMapper<AllegatoTaskDTO, AllegatoTask> {

    @Mapping(source = "tipoAllegato.id", target = "tipoAllegatoId")
    @Mapping(source = "allegatoTask.id", target = "allegatoTaskId")
    @Mapping(source = "task.id", target = "taskId")
    AllegatoTaskDTO toDto(AllegatoTask allegatoTask);

    @Mapping(target = "idAllegatoTasks", ignore = true)
    @Mapping(target = "removeIdAllegatoTask", ignore = true)
    @Mapping(source = "tipoAllegatoId", target = "tipoAllegato")
    @Mapping(source = "allegatoTaskId", target = "allegatoTask")
    @Mapping(source = "taskId", target = "task")
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
