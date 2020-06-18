package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.TagPersonaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TagPersona} and its DTO {@link TagPersonaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonaMapper.class})
public interface TagPersonaMapper extends EntityMapper<TagPersonaDTO, TagPersona> {

    @Mapping(source = "idPersona.id", target = "idPersonaId")
    TagPersonaDTO toDto(TagPersona tagPersona);

    @Mapping(source = "idPersonaId", target = "idPersona")
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
