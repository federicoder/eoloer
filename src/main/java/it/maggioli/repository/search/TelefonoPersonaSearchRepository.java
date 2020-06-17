package it.maggioli.repository.search;

import it.maggioli.domain.TelefonoPersona;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link TelefonoPersona} entity.
 */
public interface TelefonoPersonaSearchRepository extends ElasticsearchRepository<TelefonoPersona, Long> {
}
