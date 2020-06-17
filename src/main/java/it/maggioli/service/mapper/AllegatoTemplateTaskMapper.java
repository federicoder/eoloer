package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.AllegatoTemplateTaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AllegatoTemplateTask} and its DTO {@link AllegatoTemplateTaskDTO}.
 */
@Mapper(componentModel = "spring", uses = {TemplateTaskMapper.class, TipoAllegatoMapper.class})
public interface AllegatoTemplateTaskMapper extends EntityMapper<AllegatoTemplateTaskDTO, AllegatoTemplateTask> {

    @Mapping(source = "templateTask.id", target = "templateTaskId")
    @Mapping(source = "tipoAllegato.id", target = "tipoAllegatoId")
    AllegatoTemplateTaskDTO toDto(AllegatoTemplateTask allegatoTemplateTask);

    @Mapping(source = "templateTaskId", target = "templateTask")
    @Mapping(source = "tipoAllegatoId", target = "tipoAllegato")
    AllegatoTemplateTask toEntity(AllegatoTemplateTaskDTO allegatoTemplateTaskDTO);

    default AllegatoTemplateTask fromId(Long id) {
        if (id == null) {
            return null;
        }
        AllegatoTemplateTask allegatoTemplateTask = new AllegatoTemplateTask();
        allegatoTemplateTask.setId(id);
        return allegatoTemplateTask;
    }
}
