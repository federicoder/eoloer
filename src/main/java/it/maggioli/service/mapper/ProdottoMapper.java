package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.ProdottoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Prodotto} and its DTO {@link ProdottoDTO}.
 */
@Mapper(componentModel = "spring", uses = {LineaOrdineMapper.class})
public interface ProdottoMapper extends EntityMapper<ProdottoDTO, Prodotto> {

    @Mapping(source = "id.id", target = "idId")
    ProdottoDTO toDto(Prodotto prodotto);

    @Mapping(source = "idId", target = "id")
    Prodotto toEntity(ProdottoDTO prodottoDTO);

    default Prodotto fromId(Long id) {
        if (id == null) {
            return null;
        }
        Prodotto prodotto = new Prodotto();
        prodotto.setId(id);
        return prodotto;
    }
}
