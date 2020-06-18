package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.RappresentanzaPraticaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RappresentanzaPratica} and its DTO {@link RappresentanzaPraticaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonaMapper.class})
public interface RappresentanzaPraticaMapper extends EntityMapper<RappresentanzaPraticaDTO, RappresentanzaPratica> {

    @Mapping(source = "idPersona.id", target = "idPersonaId")
    RappresentanzaPraticaDTO toDto(RappresentanzaPratica rappresentanzaPratica);

    @Mapping(source = "idPersonaId", target = "idPersona")
    RappresentanzaPratica toEntity(RappresentanzaPraticaDTO rappresentanzaPraticaDTO);

    default RappresentanzaPratica fromId(Long id) {
        if (id == null) {
            return null;
        }
        RappresentanzaPratica rappresentanzaPratica = new RappresentanzaPratica();
        rappresentanzaPratica.setId(id);
        return rappresentanzaPratica;
    }
}
