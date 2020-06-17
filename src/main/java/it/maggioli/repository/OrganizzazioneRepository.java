package it.maggioli.repository;

import it.maggioli.domain.Organizzazione;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Organizzazione entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganizzazioneRepository extends JpaRepository<Organizzazione, Long> {
}
