package it.maggioli.repository;

import it.maggioli.domain.StudioProfessionale;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the StudioProfessionale entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudioProfessionaleRepository extends JpaRepository<StudioProfessionale, Long> {
}
