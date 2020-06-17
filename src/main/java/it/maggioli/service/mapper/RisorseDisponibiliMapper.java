package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.RisorseDisponibiliDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RisorseDisponibili} and its DTO {@link RisorseDisponibiliDTO}.
 */
@Mapper(componentModel = "spring", uses = {StudioProfessionaleMapper.class})
public interface RisorseDisponibiliMapper extends EntityMapper<RisorseDisponibiliDTO, RisorseDisponibili> {

    @Mapping(source = "studioProfessionale.id", target = "studioProfessionaleId")
    RisorseDisponibiliDTO toDto(RisorseDisponibili risorseDisponibili);

    @Mapping(source = "studioProfessionaleId", target = "studioProfessionale")
    RisorseDisponibili toEntity(RisorseDisponibiliDTO risorseDisponibiliDTO);

    default RisorseDisponibili fromId(Long id) {
        if (id == null) {
            return null;
        }
        RisorseDisponibili risorseDisponibili = new RisorseDisponibili();
        risorseDisponibili.setId(id);
        return risorseDisponibili;
    }
}
