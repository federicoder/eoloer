package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.InvitoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Invito} and its DTO {@link InvitoDTO}.
 */
@Mapper(componentModel = "spring", uses = {StudioProfessionaleMapper.class, AssegnazioneTaskMapper.class})
public interface InvitoMapper extends EntityMapper<InvitoDTO, Invito> {

    @Mapping(source = "idStudioProfessionale.id", target = "idStudioProfessionaleId")
    @Mapping(source = "assegnazioneTask.id", target = "assegnazioneTaskId")
    InvitoDTO toDto(Invito invito);

    @Mapping(source = "idStudioProfessionaleId", target = "idStudioProfessionale")
    @Mapping(source = "assegnazioneTaskId", target = "assegnazioneTask")
    Invito toEntity(InvitoDTO invitoDTO);

    default Invito fromId(Long id) {
        if (id == null) {
            return null;
        }
        Invito invito = new Invito();
        invito.setId(id);
        return invito;
    }
}
