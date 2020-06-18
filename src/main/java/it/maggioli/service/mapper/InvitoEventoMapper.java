package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.InvitoEventoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvitoEvento} and its DTO {@link InvitoEventoDTO}.
 */
@Mapper(componentModel = "spring", uses = {InvitoMapper.class, PrevisioneEventoMapper.class})
public interface InvitoEventoMapper extends EntityMapper<InvitoEventoDTO, InvitoEvento> {

    @Mapping(source = "idTask.id", target = "idTaskId")
    @Mapping(source = "previsioneEvento.id", target = "previsioneEventoId")
    InvitoEventoDTO toDto(InvitoEvento invitoEvento);

    @Mapping(source = "idTaskId", target = "idTask")
    @Mapping(source = "previsioneEventoId", target = "previsioneEvento")
    InvitoEvento toEntity(InvitoEventoDTO invitoEventoDTO);

    default InvitoEvento fromId(Long id) {
        if (id == null) {
            return null;
        }
        InvitoEvento invitoEvento = new InvitoEvento();
        invitoEvento.setId(id);
        return invitoEvento;
    }
}
