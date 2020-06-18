package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.ConsuntivoTaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ConsuntivoTask} and its DTO {@link ConsuntivoTaskDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConsuntivoTaskMapper extends EntityMapper<ConsuntivoTaskDTO, ConsuntivoTask> {



    default ConsuntivoTask fromId(Long id) {
        if (id == null) {
            return null;
        }
        ConsuntivoTask consuntivoTask = new ConsuntivoTask();
        consuntivoTask.setId(id);
        return consuntivoTask;
    }
}
