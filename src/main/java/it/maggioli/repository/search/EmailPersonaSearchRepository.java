package it.maggioli.repository.search;

import it.maggioli.domain.EmailPersona;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link EmailPersona} entity.
 */
public interface EmailPersonaSearchRepository extends ElasticsearchRepository<EmailPersona, Long> {
}
