package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.CondivisionePraticaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CondivisionePratica} and its DTO {@link CondivisionePraticaDTO}.
 */
@Mapper(componentModel = "spring", uses = {RappresentanzaPraticaMapper.class, PersonaMapper.class, PraticaMapper.class, UserPersonaMapper.class})
public interface CondivisionePraticaMapper extends EntityMapper<CondivisionePraticaDTO, CondivisionePratica> {

    @Mapping(source = "ruolo.id", target = "ruoloId")
    @Mapping(source = "idUserConcedente.id", target = "idUserConcedenteId")
    @Mapping(source = "pratica.id", target = "praticaId")
    @Mapping(source = "userPersona.id", target = "userPersonaId")
    CondivisionePraticaDTO toDto(CondivisionePratica condivisionePratica);

    @Mapping(source = "ruoloId", target = "ruolo")
    @Mapping(source = "idUserConcedenteId", target = "idUserConcedente")
    @Mapping(source = "praticaId", target = "pratica")
    @Mapping(source = "userPersonaId", target = "userPersona")
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
