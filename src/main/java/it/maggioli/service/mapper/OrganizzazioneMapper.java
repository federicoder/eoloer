package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.OrganizzazioneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Organizzazione} and its DTO {@link OrganizzazioneDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonaMapper.class})
public interface OrganizzazioneMapper extends EntityMapper<OrganizzazioneDTO, Organizzazione> {

    @Mapping(source = "idPersona.id", target = "idPersonaId")
    OrganizzazioneDTO toDto(Organizzazione organizzazione);

    @Mapping(source = "idPersonaId", target = "idPersona")
    @Mapping(target = "idOrganizzazione", ignore = true)
    Organizzazione toEntity(OrganizzazioneDTO organizzazioneDTO);

    default Organizzazione fromId(Long id) {
        if (id == null) {
            return null;
        }
        Organizzazione organizzazione = new Organizzazione();
        organizzazione.setId(id);
        return organizzazione;
    }
}
