package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.CondivisionePraticaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CondivisionePratica} and its DTO {@link CondivisionePraticaDTO}.
 */
@Mapper(componentModel = "spring", uses = {RappresentanzaPraticaMapper.class, PersonaMapper.class, UserPersonaMapper.class, PraticaMapper.class})
public interface CondivisionePraticaMapper extends EntityMapper<CondivisionePraticaDTO, CondivisionePratica> {

    @Mapping(source = "ruolo.id", target = "ruoloId")
    @Mapping(source = "idUserConcedente.id", target = "idUserConcedenteId")
    @Mapping(source = "idUserAmmesso.id", target = "idUserAmmessoId")
    @Mapping(source = "idPraticaRef.id", target = "idPraticaRefId")
    CondivisionePraticaDTO toDto(CondivisionePratica condivisionePratica);

    @Mapping(source = "ruoloId", target = "ruolo")
    @Mapping(source = "idUserConcedenteId", target = "idUserConcedente")
    @Mapping(source = "idUserAmmessoId", target = "idUserAmmesso")
    @Mapping(source = "idPraticaRefId", target = "idPraticaRef")
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
