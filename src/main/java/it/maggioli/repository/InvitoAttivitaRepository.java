package it.maggioli.repository;

import it.maggioli.domain.InvitoAttivita;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InvitoAttivita entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvitoAttivitaRepository extends JpaRepository<InvitoAttivita, Long> {
}
