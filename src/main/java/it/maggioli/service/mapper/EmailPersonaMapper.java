package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.EmailPersonaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EmailPersona} and its DTO {@link EmailPersonaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonaMapper.class})
public interface EmailPersonaMapper extends EntityMapper<EmailPersonaDTO, EmailPersona> {

    @Mapping(source = "idPersonaRef.id", target = "idPersonaRefId")
    EmailPersonaDTO toDto(EmailPersona emailPersona);

    @Mapping(source = "idPersonaRefId", target = "idPersonaRef")
    EmailPersona toEntity(EmailPersonaDTO emailPersonaDTO);

    default EmailPersona fromId(Long id) {
        if (id == null) {
            return null;
        }
        EmailPersona emailPersona = new EmailPersona();
        emailPersona.setId(id);
        return emailPersona;
    }
}
