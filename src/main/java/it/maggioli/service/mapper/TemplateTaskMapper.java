package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.TemplateTaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TemplateTask} and its DTO {@link TemplateTaskDTO}.
 */
@Mapper(componentModel = "spring", uses = {TemplatePraticaMapper.class})
public interface TemplateTaskMapper extends EntityMapper<TemplateTaskDTO, TemplateTask> {

    @Mapping(source = "templatePratica.id", target = "templatePraticaId")
    @Mapping(source = "templateTask.id", target = "templateTaskId")
    TemplateTaskDTO toDto(TemplateTask templateTask);

    @Mapping(target = "idTemplates", ignore = true)
    @Mapping(target = "removeIdTemplate", ignore = true)
    @Mapping(target = "idTemplateTasks", ignore = true)
    @Mapping(target = "removeIdTemplateTask", ignore = true)
    @Mapping(source = "templatePraticaId", target = "templatePratica")
    @Mapping(source = "templateTaskId", target = "templateTask")
    TemplateTask toEntity(TemplateTaskDTO templateTaskDTO);

    default TemplateTask fromId(Long id) {
        if (id == null) {
            return null;
        }
        TemplateTask templateTask = new TemplateTask();
        templateTask.setId(id);
        return templateTask;
    }
}
