package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.PraticaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Pratica} and its DTO {@link PraticaDTO}.
 */
@Mapper(componentModel = "spring", uses = {TemplatePraticaMapper.class})
public interface PraticaMapper extends EntityMapper<PraticaDTO, Pratica> {

    @Mapping(source = "idTemplatePratica.id", target = "idTemplatePraticaId")
    PraticaDTO toDto(Pratica pratica);

    @Mapping(source = "idTemplatePraticaId", target = "idTemplatePratica")
    Pratica toEntity(PraticaDTO praticaDTO);

    default Pratica fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pratica pratica = new Pratica();
        pratica.setId(id);
        return pratica;
    }
}
