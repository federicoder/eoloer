package it.maggioli.repository;

import it.maggioli.domain.ConsuntivoTask;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ConsuntivoTask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsuntivoTaskRepository extends JpaRepository<ConsuntivoTask, Long> {
}
