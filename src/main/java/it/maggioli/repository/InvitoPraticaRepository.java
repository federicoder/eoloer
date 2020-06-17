package it.maggioli.repository;

import it.maggioli.domain.InvitoPratica;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InvitoPratica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvitoPraticaRepository extends JpaRepository<InvitoPratica, Long> {
}
