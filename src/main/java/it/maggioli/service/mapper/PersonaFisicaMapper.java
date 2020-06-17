package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.PersonaFisicaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PersonaFisica} and its DTO {@link PersonaFisicaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonaMapper.class})
public interface PersonaFisicaMapper extends EntityMapper<PersonaFisicaDTO, PersonaFisica> {

    @Mapping(source = "idPersonaRef.id", target = "idPersonaRefId")
    PersonaFisicaDTO toDto(PersonaFisica personaFisica);

    @Mapping(source = "idPersonaRefId", target = "idPersonaRef")
    @Mapping(target = "ids", ignore = true)
    @Mapping(target = "removeId", ignore = true)
    @Mapping(target = "idRuoloPersona", ignore = true)
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
