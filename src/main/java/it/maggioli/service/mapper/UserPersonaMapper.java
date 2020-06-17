package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.UserPersonaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserPersona} and its DTO {@link UserPersonaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonaFisicaMapper.class})
public interface UserPersonaMapper extends EntityMapper<UserPersonaDTO, UserPersona> {

    @Mapping(source = "personaFisica.id", target = "personaFisicaId")
    UserPersonaDTO toDto(UserPersona userPersona);

    @Mapping(target = "ids", ignore = true)
    @Mapping(target = "removeId", ignore = true)
    @Mapping(target = "ids", ignore = true)
    @Mapping(target = "removeId", ignore = true)
    @Mapping(target = "ids", ignore = true)
    @Mapping(target = "removeId", ignore = true)
    @Mapping(source = "personaFisicaId", target = "personaFisica")
    UserPersona toEntity(UserPersonaDTO userPersonaDTO);

    default UserPersona fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserPersona userPersona = new UserPersona();
        userPersona.setId(id);
        return userPersona;
    }
}
