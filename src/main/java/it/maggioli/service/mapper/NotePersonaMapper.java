package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.NotePersonaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotePersona} and its DTO {@link NotePersonaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonaMapper.class})
public interface NotePersonaMapper extends EntityMapper<NotePersonaDTO, NotePersona> {

    @Mapping(source = "idPersonaRef.id", target = "idPersonaRefId")
    NotePersonaDTO toDto(NotePersona notePersona);

    @Mapping(source = "idPersonaRefId", target = "idPersonaRef")
    NotePersona toEntity(NotePersonaDTO notePersonaDTO);

    default NotePersona fromId(Long id) {
        if (id == null) {
            return null;
        }
        NotePersona notePersona = new NotePersona();
        notePersona.setId(id);
        return notePersona;
    }
}
