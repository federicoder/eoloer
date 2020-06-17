package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.LineaOrdineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LineaOrdine} and its DTO {@link LineaOrdineDTO}.
 */
@Mapper(componentModel = "spring", uses = {OrdineMapper.class})
public interface LineaOrdineMapper extends EntityMapper<LineaOrdineDTO, LineaOrdine> {

    @Mapping(source = "ordine.id", target = "ordineId")
    LineaOrdineDTO toDto(LineaOrdine lineaOrdine);

    @Mapping(target = "idProdottoRef", ignore = true)
    @Mapping(source = "ordineId", target = "ordine")
    LineaOrdine toEntity(LineaOrdineDTO lineaOrdineDTO);

    default LineaOrdine fromId(Long id) {
        if (id == null) {
            return null;
        }
        LineaOrdine lineaOrdine = new LineaOrdine();
        lineaOrdine.setId(id);
        return lineaOrdine;
    }
}
