package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.CondivisionePraticaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CondivisionePratica} and its DTO {@link CondivisionePraticaDTO}.
 */
@Mapper(componentModel = "spring", uses = {RappresentanzaPraticaMapper.class, PersonaMapper.class, UserPersonaMapper.class, PraticaMapper.class})
public interface CondivisionePraticaMapper extends EntityMapper<CondivisionePraticaDTO, CondivisionePratica> {

    @Mapping(source = "idRuoloPersona.id", target = "idRuoloPersonaId")
    @Mapping(source = "idPersona.id", target = "idPersonaId")
    @Mapping(source = "idUserPersona.id", target = "idUserPersonaId")
    @Mapping(source = "idPratica.id", target = "idPraticaId")
    CondivisionePraticaDTO toDto(CondivisionePratica condivisionePratica);

    @Mapping(source = "idRuoloPersonaId", target = "idRuoloPersona")
    @Mapping(source = "idPersonaId", target = "idPersona")
    @Mapping(source = "idUserPersonaId", target = "idUserPersona")
    @Mapping(source = "idPraticaId", target = "idPratica")
    CondivisionePratica toEntity(CondivisionePraticaDTO condivisionePraticaDTO);

    default CondivisionePratica fromId(Long id) {
        if (id == null) {
            return null;
        }
        CondivisionePratica condivisionePratica = new CondivisionePratica();
        condivisionePratica.setId(id);
        return condivisionePratica;
    }
}
