package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.TipoAllegatoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoAllegato} and its DTO {@link TipoAllegatoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoAllegatoMapper extends EntityMapper<TipoAllegatoDTO, TipoAllegato> {


    @Mapping(target = "ids", ignore = true)
    @Mapping(target = "removeId", ignore = true)
    @Mapping(target = "ids", ignore = true)
    @Mapping(target = "removeId", ignore = true)
    TipoAllegato toEntity(TipoAllegatoDTO tipoAllegatoDTO);

    default TipoAllegato fromId(Long id) {
        if (id == null) {
            return null;
        }
        TipoAllegato tipoAllegato = new TipoAllegato();
        tipoAllegato.setId(id);
        return tipoAllegato;
    }
}
