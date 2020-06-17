package it.maggioli.repository;

import it.maggioli.domain.AllegatoTask;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AllegatoTask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AllegatoTaskRepository extends JpaRepository<AllegatoTask, Long> {
}
