package it.maggioli.repository;

import it.maggioli.domain.PrevisioneEvento;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PrevisioneEvento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrevisioneEventoRepository extends JpaRepository<PrevisioneEvento, Long> {
}
