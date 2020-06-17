package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.RuoloOrganizzazioneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RuoloOrganizzazione} and its DTO {@link RuoloOrganizzazioneDTO}.
 */
@Mapper(componentModel = "spring", uses = {OrganizzazioneMapper.class, PersonaFisicaMapper.class})
public interface RuoloOrganizzazioneMapper extends EntityMapper<RuoloOrganizzazioneDTO, RuoloOrganizzazione> {

    @Mapping(source = "id.id", target = "idId")
    @Mapping(source = "id.id", target = "idId")
    RuoloOrganizzazioneDTO toDto(RuoloOrganizzazione ruoloOrganizzazione);

    @Mapping(source = "idId", target = "id")
    @Mapping(source = "idId", target = "id")
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
