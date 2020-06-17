package it.maggioli.repository.search;

import it.maggioli.domain.IndirizzoPersona;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link IndirizzoPersona} entity.
 */
public interface IndirizzoPersonaSearchRepository extends ElasticsearchRepository<IndirizzoPersona, Long> {
}
