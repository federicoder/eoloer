package it.maggioli.repository;

import it.maggioli.domain.InvitoEvento;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InvitoEvento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvitoEventoRepository extends JpaRepository<InvitoEvento, Long> {
}
