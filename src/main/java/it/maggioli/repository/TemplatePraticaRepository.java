package it.maggioli.repository;

import it.maggioli.domain.TemplatePratica;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TemplatePratica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TemplatePraticaRepository extends JpaRepository<TemplatePratica, Long> {
}
