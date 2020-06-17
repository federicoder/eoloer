package it.maggioli.repository;

import it.maggioli.domain.DatiContabili;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DatiContabili entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DatiContabiliRepository extends JpaRepository<DatiContabili, Long> {
}
