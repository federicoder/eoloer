package it.maggioli.repository;

import it.maggioli.domain.RisorseDisponibili;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RisorseDisponibili entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RisorseDisponibiliRepository extends JpaRepository<RisorseDisponibili, Long> {
}
