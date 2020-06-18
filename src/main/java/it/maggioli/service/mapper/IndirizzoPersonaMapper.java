package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.IndirizzoPersonaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link IndirizzoPersona} and its DTO {@link IndirizzoPersonaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonaMapper.class})
public interface IndirizzoPersonaMapper extends EntityMapper<IndirizzoPersonaDTO, IndirizzoPersona> {

    @Mapping(source = "idPersona.id", target = "idPersonaId")
    IndirizzoPersonaDTO toDto(IndirizzoPersona indirizzoPersona);

    @Mapping(source = "idPersonaId", target = "idPersona")
    IndirizzoPersona toEntity(IndirizzoPersonaDTO indirizzoPersonaDTO);

    default IndirizzoPersona fromId(Long id) {
        if (id == null) {
            return null;
        }
        IndirizzoPersona indirizzoPersona = new IndirizzoPersona();
        indirizzoPersona.setId(id);
        return indirizzoPersona;
    }
}
