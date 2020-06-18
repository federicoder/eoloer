package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.InvitoAttivitaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvitoAttivita} and its DTO {@link InvitoAttivitaDTO}.
 */
@Mapper(componentModel = "spring", uses = {TaskMapper.class, InvitoMapper.class})
public interface InvitoAttivitaMapper extends EntityMapper<InvitoAttivitaDTO, InvitoAttivita> {

    @Mapping(source = "idTask.id", target = "idTaskId")
    @Mapping(source = "idTask.id", target = "idTaskId")
    InvitoAttivitaDTO toDto(InvitoAttivita invitoAttivita);

    @Mapping(source = "idTaskId", target = "idTask")
    @Mapping(source = "idTaskId", target = "idTask")
    InvitoAttivita toEntity(InvitoAttivitaDTO invitoAttivitaDTO);

    default InvitoAttivita fromId(Long id) {
        if (id == null) {
            return null;
        }
        InvitoAttivita invitoAttivita = new InvitoAttivita();
        invitoAttivita.setId(id);
        return invitoAttivita;
    }
}
