package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.PersonaFisicaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PersonaFisica} and its DTO {@link PersonaFisicaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonaMapper.class})
public interface PersonaFisicaMapper extends EntityMapper<PersonaFisicaDTO, PersonaFisica> {

    @Mapping(source = "idPersona.id", target = "idPersonaId")
    PersonaFisicaDTO toDto(PersonaFisica personaFisica);

    @Mapping(source = "idPersonaId", target = "idPersona")
    PersonaFisica toEntity(PersonaFisicaDTO personaFisicaDTO);

    default PersonaFisica fromId(Long id) {
        if (id == null) {
            return null;
        }
        PersonaFisica personaFisica = new PersonaFisica();
        personaFisica.setId(id);
        return personaFisica;
    }
}
