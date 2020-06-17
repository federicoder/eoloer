package it.maggioli.repository;

import it.maggioli.domain.UserPersona;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserPersona entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserPersonaRepository extends JpaRepository<UserPersona, Long> {
}
