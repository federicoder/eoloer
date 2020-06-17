package it.maggioli.repository;

import it.maggioli.domain.PrevisioneTask;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PrevisioneTask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrevisioneTaskRepository extends JpaRepository<PrevisioneTask, Long> {
}
