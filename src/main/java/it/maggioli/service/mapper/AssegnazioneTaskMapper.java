package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.AssegnazioneTaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AssegnazioneTask} and its DTO {@link AssegnazioneTaskDTO}.
 */
@Mapper(componentModel = "spring", uses = {TaskMapper.class, RappresentanzaPraticaMapper.class, UserPersonaMapper.class})
public interface AssegnazioneTaskMapper extends EntityMapper<AssegnazioneTaskDTO, AssegnazioneTask> {

    @Mapping(source = "idTask.id", target = "idTaskId")
    @Mapping(source = "idRuoloPersona.id", target = "idRuoloPersonaId")
    @Mapping(source = "idPersona.id", target = "idPersonaId")
    AssegnazioneTaskDTO toDto(AssegnazioneTask assegnazioneTask);

    @Mapping(source = "idTaskId", target = "idTask")
    @Mapping(source = "idRuoloPersonaId", target = "idRuoloPersona")
    @Mapping(target = "idInvitos", ignore = true)
    @Mapping(target = "removeIdInvito", ignore = true)
    @Mapping(source = "idPersonaId", target = "idPersona")
    AssegnazioneTask toEntity(AssegnazioneTaskDTO assegnazioneTaskDTO);

    default AssegnazioneTask fromId(Long id) {
        if (id == null) {
            return null;
        }
        AssegnazioneTask assegnazioneTask = new AssegnazioneTask();
        assegnazioneTask.setId(id);
        return assegnazioneTask;
    }
}
