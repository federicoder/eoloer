package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.InvitoPraticaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvitoPratica} and its DTO {@link InvitoPraticaDTO}.
 */
@Mapper(componentModel = "spring", uses = {InvitoMapper.class, PraticaMapper.class})
public interface InvitoPraticaMapper extends EntityMapper<InvitoPraticaDTO, InvitoPratica> {

    @Mapping(source = "idPratica.id", target = "idPraticaId")
    @Mapping(source = "idPratica.id", target = "idPraticaId")
    InvitoPraticaDTO toDto(InvitoPratica invitoPratica);

    @Mapping(source = "idPraticaId", target = "idPratica")
    @Mapping(source = "idPraticaId", target = "idPratica")
    InvitoPratica toEntity(InvitoPraticaDTO invitoPraticaDTO);

    default InvitoPratica fromId(Long id) {
        if (id == null) {
            return null;
        }
        InvitoPratica invitoPratica = new InvitoPratica();
        invitoPratica.setId(id);
        return invitoPratica;
    }
}
