package it.maggioli.repository;

import it.maggioli.domain.NotaPratica;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NotaPratica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotaPraticaRepository extends JpaRepository<NotaPratica, Long> {
}
