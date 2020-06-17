package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.PrevisioneEventoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PrevisioneEvento} and its DTO {@link PrevisioneEventoDTO}.
 */
@Mapper(componentModel = "spring", uses = {PrevisioneTaskMapper.class})
public interface PrevisioneEventoMapper extends EntityMapper<PrevisioneEventoDTO, PrevisioneEvento> {

    @Mapping(source = "idTask.id", target = "idTaskId")
    PrevisioneEventoDTO toDto(PrevisioneEvento previsioneEvento);

    @Mapping(source = "idTaskId", target = "idTask")
    @Mapping(target = "idTasks", ignore = true)
    @Mapping(target = "removeIdTask", ignore = true)
    PrevisioneEvento toEntity(PrevisioneEventoDTO previsioneEventoDTO);

    default PrevisioneEvento fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrevisioneEvento previsioneEvento = new PrevisioneEvento();
        previsioneEvento.setId(id);
        return previsioneEvento;
    }
}
