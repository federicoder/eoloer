package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.RappresentanzaPraticaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RappresentanzaPratica} and its DTO {@link RappresentanzaPraticaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonaMapper.class})
public interface RappresentanzaPraticaMapper extends EntityMapper<RappresentanzaPraticaDTO, RappresentanzaPratica> {

    @Mapping(source = "idPersonaRef.id", target = "idPersonaRefId")
    RappresentanzaPraticaDTO toDto(RappresentanzaPratica rappresentanzaPratica);

    @Mapping(source = "idPersonaRefId", target = "idPersonaRef")
    @Mapping(target = "idRuoloPersona", ignore = true)
    @Mapping(target = "idRuoloPersona", ignore = true)
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
