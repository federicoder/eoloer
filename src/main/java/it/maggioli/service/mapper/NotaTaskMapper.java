package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.NotaTaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotaTask} and its DTO {@link NotaTaskDTO}.
 */
@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface NotaTaskMapper extends EntityMapper<NotaTaskDTO, NotaTask> {

    @Mapping(source = "task.id", target = "taskId")
    NotaTaskDTO toDto(NotaTask notaTask);

    @Mapping(source = "taskId", target = "task")
    NotaTask toEntity(NotaTaskDTO notaTaskDTO);

    default NotaTask fromId(Long id) {
        if (id == null) {
            return null;
        }
        NotaTask notaTask = new NotaTask();
        notaTask.setId(id);
        return notaTask;
    }
}
