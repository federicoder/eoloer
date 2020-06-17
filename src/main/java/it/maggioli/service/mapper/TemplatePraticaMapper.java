package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.TemplatePraticaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TemplatePratica} and its DTO {@link TemplatePraticaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TemplatePraticaMapper extends EntityMapper<TemplatePraticaDTO, TemplatePratica> {


    @Mapping(target = "ids", ignore = true)
    @Mapping(target = "removeId", ignore = true)
    TemplatePratica toEntity(TemplatePraticaDTO templatePraticaDTO);

    default TemplatePratica fromId(Long id) {
        if (id == null) {
            return null;
        }
        TemplatePratica templatePratica = new TemplatePratica();
        templatePratica.setId(id);
        return templatePratica;
    }
}
