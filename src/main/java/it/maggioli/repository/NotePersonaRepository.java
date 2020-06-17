package it.maggioli.repository;

import it.maggioli.domain.NotePersona;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NotePersona entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotePersonaRepository extends JpaRepository<NotePersona, Long> {
}
