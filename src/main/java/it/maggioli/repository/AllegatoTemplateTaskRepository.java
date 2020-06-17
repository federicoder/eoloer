package it.maggioli.repository;

import it.maggioli.domain.AllegatoTemplateTask;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AllegatoTemplateTask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AllegatoTemplateTaskRepository extends JpaRepository<AllegatoTemplateTask, Long> {
}
