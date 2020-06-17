package it.maggioli.service.mapper;


import it.maggioli.domain.*;
import it.maggioli.service.dto.OrdineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ordine} and its DTO {@link OrdineDTO}.
 */
@Mapper(componentModel = "spring", uses = {StudioProfessionaleMapper.class})
public interface OrdineMapper extends EntityMapper<OrdineDTO, Ordine> {

    @Mapping(source = "studioProfessionale.id", target = "studioProfessionaleId")
    OrdineDTO toDto(Ordine ordine);

    @Mapping(target = "ids", ignore = true)
    @Mapping(target = "removeId", ignore = true)
    @Mapping(source = "studioProfessionaleId", target = "studioProfessionale")
    Ordine toEntity(OrdineDTO ordineDTO);

    default Ordine fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ordine ordine = new Ordine();
        ordine.setId(id);
        return ordine;
    }
}
