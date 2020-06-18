package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.ProdottoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Prodotto} and its DTO {@link ProdottoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProdottoMapper extends EntityMapper<ProdottoDTO, Prodotto> {



    default Prodotto fromId(Long id) {
        if (id == null) {
            return null;
        }
        Prodotto prodotto = new Prodotto();
        prodotto.setId(id);
        return prodotto;
    }
}
