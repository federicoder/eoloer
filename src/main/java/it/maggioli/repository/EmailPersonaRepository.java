package it.maggioli.repository;

import it.maggioli.domain.EmailPersona;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EmailPersona entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmailPersonaRepository extends JpaRepository<EmailPersona, Long> {
}
