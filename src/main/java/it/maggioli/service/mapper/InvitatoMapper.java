package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.InvitatoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Invitato} and its DTO {@link InvitatoDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserPersonaMapper.class, InvitoMapper.class})
public interface InvitatoMapper extends EntityMapper<InvitatoDTO, Invitato> {

    @Mapping(source = "userPersona.id", target = "userPersonaId")
    @Mapping(source = "invito.id", target = "invitoId")
    InvitatoDTO toDto(Invitato invitato);

    @Mapping(source = "userPersonaId", target = "userPersona")
    @Mapping(source = "invitoId", target = "invito")
    Invitato toEntity(InvitatoDTO invitatoDTO);

    default Invitato fromId(Long id) {
        if (id == null) {
            return null;
        }
        Invitato invitato = new Invitato();
        invitato.setId(id);
        return invitato;
    }
}
