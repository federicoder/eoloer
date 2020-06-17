package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.InvitoAttivitaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvitoAttivita} and its DTO {@link InvitoAttivitaDTO}.
 */
@Mapper(componentModel = "spring", uses = {InvitoMapper.class})
public interface InvitoAttivitaMapper extends EntityMapper<InvitoAttivitaDTO, InvitoAttivita> {

    @Mapping(source = "idAttivita.id", target = "idAttivitaId")
    InvitoAttivitaDTO toDto(InvitoAttivita invitoAttivita);

    @Mapping(source = "idAttivitaId", target = "idAttivita")
    @Mapping(target = "idAttivita", ignore = true)
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
