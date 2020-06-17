package it.maggioli.repository;

import it.maggioli.domain.AssegnazioneTask;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AssegnazioneTask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssegnazioneTaskRepository extends JpaRepository<AssegnazioneTask, Long> {
}
