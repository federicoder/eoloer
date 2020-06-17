package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.TagPersonaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TagPersona} and its DTO {@link TagPersonaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonaMapper.class})
public interface TagPersonaMapper extends EntityMapper<TagPersonaDTO, TagPersona> {

    @Mapping(source = "idPersonaRef.id", target = "idPersonaRefId")
    TagPersonaDTO toDto(TagPersona tagPersona);

    @Mapping(source = "idPersonaRefId", target = "idPersonaRef")
    TagPersona toEntity(TagPersonaDTO tagPersonaDTO);

    default TagPersona fromId(Long id) {
        if (id == null) {
            return null;
        }
        TagPersona tagPersona = new TagPersona();
        tagPersona.setId(id);
        return tagPersona;
    }
}
