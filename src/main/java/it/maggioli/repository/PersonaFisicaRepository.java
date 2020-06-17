package it.maggioli.repository;

import it.maggioli.domain.PersonaFisica;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PersonaFisica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonaFisicaRepository extends JpaRepository<PersonaFisica, Long> {
}
