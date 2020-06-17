package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.OrganizzazioneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Organizzazione} and its DTO {@link OrganizzazioneDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonaMapper.class})
public interface OrganizzazioneMapper extends EntityMapper<OrganizzazioneDTO, Organizzazione> {

    @Mapping(source = "idPersonaRef.id", target = "idPersonaRefId")
    OrganizzazioneDTO toDto(Organizzazione organizzazione);

    @Mapping(source = "idPersonaRefId", target = "idPersonaRef")
    @Mapping(target = "id", ignore = true)
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
