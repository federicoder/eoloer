package it.maggioli.repository;

import it.maggioli.domain.TemplateTask;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TemplateTask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TemplateTaskRepository extends JpaRepository<TemplateTask, Long> {
}
