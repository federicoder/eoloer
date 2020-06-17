package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.StudioProfessionaleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link StudioProfessionale} and its DTO {@link StudioProfessionaleDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonaMapper.class})
public interface StudioProfessionaleMapper extends EntityMapper<StudioProfessionaleDTO, StudioProfessionale> {

    @Mapping(source = "persona.id", target = "personaId")
    StudioProfessionaleDTO toDto(StudioProfessionale studioProfessionale);

    @Mapping(target = "ids", ignore = true)
    @Mapping(target = "removeId", ignore = true)
    @Mapping(target = "ids", ignore = true)
    @Mapping(target = "removeId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "personaId", target = "persona")
    StudioProfessionale toEntity(StudioProfessionaleDTO studioProfessionaleDTO);

    default StudioProfessionale fromId(Long id) {
        if (id == null) {
            return null;
        }
        StudioProfessionale studioProfessionale = new StudioProfessionale();
        studioProfessionale.setId(id);
        return studioProfessionale;
    }
}
