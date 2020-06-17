package it.maggioli.repository;

import it.maggioli.domain.RuoloOrganizzazione;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RuoloOrganizzazione entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RuoloOrganizzazioneRepository extends JpaRepository<RuoloOrganizzazione, Long> {
}
