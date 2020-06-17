package it.maggioli.repository;

import it.maggioli.domain.LineaOrdine;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LineaOrdine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LineaOrdineRepository extends JpaRepository<LineaOrdine, Long> {
}
