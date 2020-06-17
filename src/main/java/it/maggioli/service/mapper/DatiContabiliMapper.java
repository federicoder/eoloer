package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.DatiContabiliDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DatiContabili} and its DTO {@link DatiContabiliDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonaMapper.class})
public interface DatiContabiliMapper extends EntityMapper<DatiContabiliDTO, DatiContabili> {

    @Mapping(source = "persona.id", target = "personaId")
    DatiContabiliDTO toDto(DatiContabili datiContabili);

    @Mapping(source = "personaId", target = "persona")
    DatiContabili toEntity(DatiContabiliDTO datiContabiliDTO);

    default DatiContabili fromId(Long id) {
        if (id == null) {
            return null;
        }
        DatiContabili datiContabili = new DatiContabili();
        datiContabili.setId(id);
        return datiContabili;
    }
}
