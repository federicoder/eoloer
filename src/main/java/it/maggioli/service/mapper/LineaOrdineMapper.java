package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.LineaOrdineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LineaOrdine} and its DTO {@link LineaOrdineDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProdottoMapper.class, OrdineMapper.class})
public interface LineaOrdineMapper extends EntityMapper<LineaOrdineDTO, LineaOrdine> {

    @Mapping(source = "idProdotto.id", target = "idProdottoId")
    @Mapping(source = "idOrdine.id", target = "idOrdineId")
    LineaOrdineDTO toDto(LineaOrdine lineaOrdine);

    @Mapping(source = "idProdottoId", target = "idProdotto")
    @Mapping(source = "idOrdineId", target = "idOrdine")
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
