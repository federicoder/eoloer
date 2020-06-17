package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.NotaPraticaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NotaPratica} and its DTO {@link NotaPraticaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PraticaMapper.class})
public interface NotaPraticaMapper extends EntityMapper<NotaPraticaDTO, NotaPratica> {

    @Mapping(source = "idPraticaRef.id", target = "idPraticaRefId")
    NotaPraticaDTO toDto(NotaPratica notaPratica);

    @Mapping(source = "idPraticaRefId", target = "idPraticaRef")
    NotaPratica toEntity(NotaPraticaDTO notaPraticaDTO);

    default NotaPratica fromId(Long id) {
        if (id == null) {
            return null;
        }
        NotaPratica notaPratica = new NotaPratica();
        notaPratica.setId(id);
        return notaPratica;
    }
}
