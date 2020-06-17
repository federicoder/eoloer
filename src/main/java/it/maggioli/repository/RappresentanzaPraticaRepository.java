package it.maggioli.repository;

import it.maggioli.domain.RappresentanzaPratica;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RappresentanzaPratica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RappresentanzaPraticaRepository extends JpaRepository<RappresentanzaPratica, Long> {
}
