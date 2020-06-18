package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.PrevisioneAttivitaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PrevisioneAttivita} and its DTO {@link PrevisioneAttivitaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PrevisioneTaskMapper.class})
public interface PrevisioneAttivitaMapper extends EntityMapper<PrevisioneAttivitaDTO, PrevisioneAttivita> {

    @Mapping(source = "idPrevisioneAttivita.id", target = "idPrevisioneAttivitaId")
    PrevisioneAttivitaDTO toDto(PrevisioneAttivita previsioneAttivita);

    @Mapping(source = "idPrevisioneAttivitaId", target = "idPrevisioneAttivita")
    PrevisioneAttivita toEntity(PrevisioneAttivitaDTO previsioneAttivitaDTO);

    default PrevisioneAttivita fromId(Long id) {
        if (id == null) {
            return null;
        }
        PrevisioneAttivita previsioneAttivita = new PrevisioneAttivita();
        previsioneAttivita.setId(id);
        return previsioneAttivita;
    }
}
