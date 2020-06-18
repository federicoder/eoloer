package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.RuoloOrganizzazioneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RuoloOrganizzazione} and its DTO {@link RuoloOrganizzazioneDTO}.
 */
@Mapper(componentModel = "spring", uses = {OrganizzazioneMapper.class, PersonaFisicaMapper.class})
public interface RuoloOrganizzazioneMapper extends EntityMapper<RuoloOrganizzazioneDTO, RuoloOrganizzazione> {

    @Mapping(source = "idOrganizzazione.id", target = "idOrganizzazioneId")
    @Mapping(source = "idPersonaFisica.id", target = "idPersonaFisicaId")
    RuoloOrganizzazioneDTO toDto(RuoloOrganizzazione ruoloOrganizzazione);

    @Mapping(source = "idOrganizzazioneId", target = "idOrganizzazione")
    @Mapping(source = "idPersonaFisicaId", target = "idPersonaFisica")
    RuoloOrganizzazione toEntity(RuoloOrganizzazioneDTO ruoloOrganizzazioneDTO);

    default RuoloOrganizzazione fromId(Long id) {
        if (id == null) {
            return null;
        }
        RuoloOrganizzazione ruoloOrganizzazione = new RuoloOrganizzazione();
        ruoloOrganizzazione.setId(id);
        return ruoloOrganizzazione;
    }
}
