package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.TaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Task} and its DTO {@link TaskDTO}.
 */
@Mapper(componentModel = "spring", uses = {ConsuntivoTaskMapper.class, PrevisioneTaskMapper.class, AssegnazioneTaskMapper.class, InvitoAttivitaMapper.class, PraticaMapper.class})
public interface TaskMapper extends EntityMapper<TaskDTO, Task> {

    @Mapping(source = "id.id", target = "idId")
    @Mapping(source = "id.id", target = "idId")
    @Mapping(source = "id.id", target = "idId")
    @Mapping(source = "id.id", target = "idId")
    @Mapping(source = "pratica.id", target = "praticaId")
    TaskDTO toDto(Task task);

    @Mapping(source = "idId", target = "id")
    @Mapping(source = "idId", target = "id")
    @Mapping(source = "idId", target = "id")
    @Mapping(source = "idId", target = "id")
    @Mapping(target = "ids", ignore = true)
    @Mapping(target = "removeId", ignore = true)
    @Mapping(target = "ids", ignore = true)
    @Mapping(target = "removeId", ignore = true)
    @Mapping(source = "praticaId", target = "pratica")
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
