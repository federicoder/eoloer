package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.TaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Task} and its DTO {@link TaskDTO}.
 */
@Mapper(componentModel = "spring", uses = {ConsuntivoTaskMapper.class, PrevisioneTaskMapper.class, PraticaMapper.class})
public interface TaskMapper extends EntityMapper<TaskDTO, Task> {

    @Mapping(source = "idTask.id", target = "idTaskId")
    @Mapping(source = "idTask.id", target = "idTaskId")
    @Mapping(source = "idPratica.id", target = "idPraticaId")
    TaskDTO toDto(Task task);

    @Mapping(source = "idTaskId", target = "idTask")
    @Mapping(source = "idTaskId", target = "idTask")
    @Mapping(source = "idPraticaId", target = "idPratica")
    Task toEntity(TaskDTO taskDTO);

    default Task fromId(Long id) {
        if (id == null) {
            return null;
        }
        Task task = new Task();
        task.setId(id);
        return task;
    }
}
