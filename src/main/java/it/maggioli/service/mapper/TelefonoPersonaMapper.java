package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.TelefonoPersonaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TelefonoPersona} and its DTO {@link TelefonoPersonaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonaMapper.class})
public interface TelefonoPersonaMapper extends EntityMapper<TelefonoPersonaDTO, TelefonoPersona> {

    @Mapping(source = "persona.id", target = "personaId")
    TelefonoPersonaDTO toDto(TelefonoPersona telefonoPersona);

    @Mapping(source = "personaId", target = "persona")
    TelefonoPersona toEntity(TelefonoPersonaDTO telefonoPersonaDTO);

    default TelefonoPersona fromId(Long id) {
        if (id == null) {
            return null;
        }
        TelefonoPersona telefonoPersona = new TelefonoPersona();
        telefonoPersona.setId(id);
        return telefonoPersona;
    }
}
