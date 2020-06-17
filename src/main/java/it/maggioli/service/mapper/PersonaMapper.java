package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.PersonaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Persona} and its DTO {@link PersonaDTO}.
 */
@Mapper(componentModel = "spring", uses = {IndirizzoPersonaMapper.class})
public interface PersonaMapper extends EntityMapper<PersonaDTO, Persona> {

    @Mapping(source = "idPersona.id", target = "idPersonaId")
    PersonaDTO toDto(Persona persona);

    @Mapping(source = "idPersonaId", target = "idPersona")
    @Mapping(target = "idPersonas", ignore = true)
    @Mapping(target = "removeIdPersona", ignore = true)
    @Mapping(target = "idPersonas", ignore = true)
    @Mapping(target = "removeIdPersona", ignore = true)
    @Mapping(target = "idPersonas", ignore = true)
    @Mapping(target = "removeIdPersona", ignore = true)
    @Mapping(target = "idPersonas", ignore = true)
    @Mapping(target = "removeIdPersona", ignore = true)
    @Mapping(target = "idPersonas", ignore = true)
    @Mapping(target = "removeIdPersona", ignore = true)
    @Mapping(target = "idPersonas", ignore = true)
    @Mapping(target = "removeIdPersona", ignore = true)
    @Mapping(target = "idPersonas", ignore = true)
    @Mapping(target = "removeIdPersona", ignore = true)
    @Mapping(target = "idPersona", ignore = true)
    @Mapping(target = "idPersona", ignore = true)
    @Mapping(target = "idPersona", ignore = true)
    Persona toEntity(PersonaDTO personaDTO);

    default Persona fromId(Long id) {
        if (id == null) {
            return null;
        }
        Persona persona = new Persona();
        persona.setId(id);
        return persona;
    }
}
