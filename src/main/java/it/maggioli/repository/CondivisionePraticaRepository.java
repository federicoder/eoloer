package it.maggioli.repository;

import it.maggioli.domain.CondivisionePratica;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CondivisionePratica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CondivisionePraticaRepository extends JpaRepository<CondivisionePratica, Long> {
}
