package it.maggioli.repository;

import it.maggioli.domain.IndirizzoPersona;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the IndirizzoPersona entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IndirizzoPersonaRepository extends JpaRepository<IndirizzoPersona, Long> {
}
