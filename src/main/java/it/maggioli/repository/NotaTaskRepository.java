package it.maggioli.repository;

import it.maggioli.domain.NotaTask;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NotaTask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotaTaskRepository extends JpaRepository<NotaTask, Long> {
}
