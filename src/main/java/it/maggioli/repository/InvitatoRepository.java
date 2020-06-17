package it.maggioli.repository;

import it.maggioli.domain.Invitato;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Invitato entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvitatoRepository extends JpaRepository<Invitato, Long> {
}
