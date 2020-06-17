package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.PrevisioneTaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PrevisioneTask} and its DTO {@link PrevisioneTaskDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PrevisioneTaskMapper extends EntityMapper<PrevisioneTaskDTO, PrevisioneTask> {

    @Mapping(source = "previsioneTask.id", target = "previsioneTaskId")
    PrevisioneTaskDTO toDto(PrevisioneTask previsioneTask);

    @Mapping(target = "idTaskRefs", ignore = true)
    @Mapping(target = "removeIdTaskRef", ignore = true)
    @Mapping(target = "idTaskRef", ignore = true)
    @Mapping(target = "idTaskRef", ignore = true)
    @Mapping(target = "idTaskRef", ignore = true)
    @Mapping(source = "previsioneTaskId", target = "previsioneTask")
    PrevisioneTask toEntity(PrevisioneTaskDTO previsioneTaskDTO);

    default PrevisioneTask fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrevisioneTask previsioneTask = new PrevisioneTask();
        previsioneTask.setId(id);
        return previsioneTask;
    }
}
