package it.maggioli.repository;

import it.maggioli.domain.TagPersona;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TagPersona entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TagPersonaRepository extends JpaRepository<TagPersona, Long> {
}
