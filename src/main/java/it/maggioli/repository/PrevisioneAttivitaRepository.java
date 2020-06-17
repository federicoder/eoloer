package it.maggioli.repository;

import it.maggioli.domain.PrevisioneAttivita;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PrevisioneAttivita entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrevisioneAttivitaRepository extends JpaRepository<PrevisioneAttivita, Long> {
}
