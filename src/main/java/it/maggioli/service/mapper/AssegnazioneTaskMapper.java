package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.AssegnazioneTaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AssegnazioneTask} and its DTO {@link AssegnazioneTaskDTO}.
 */
@Mapper(componentModel = "spring", uses = {RappresentanzaPraticaMapper.class, UserPersonaMapper.class})
public interface AssegnazioneTaskMapper extends EntityMapper<AssegnazioneTaskDTO, AssegnazioneTask> {

    @Mapping(source = "ruolo.id", target = "ruoloId")
    @Mapping(source = "idUserAmmesso.id", target = "idUserAmmessoId")
    AssegnazioneTaskDTO toDto(AssegnazioneTask assegnazioneTask);

    @Mapping(source = "ruoloId", target = "ruolo")
    @Mapping(target = "idUserConcedentes", ignore = true)
    @Mapping(target = "removeIdUserConcedente", ignore = true)
    @Mapping(source = "idUserAmmessoId", target = "idUserAmmesso")
    @Mapping(target = "idTaskRef", ignore = true)
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
