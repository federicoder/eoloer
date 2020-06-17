package it.maggioli.repository;

import it.maggioli.domain.TipoAllegato;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TipoAllegato entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoAllegatoRepository extends JpaRepository<TipoAllegato, Long> {
}
