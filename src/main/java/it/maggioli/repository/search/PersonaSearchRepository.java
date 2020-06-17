package it.maggioli.repository.search;

import it.maggioli.domain.Persona;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Persona} entity.
 */
public interface PersonaSearchRepository extends ElasticsearchRepository<Persona, Long> {
}
