package it.maggioli.repository.search;

import it.maggioli.domain.InvitoAttivita;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link InvitoAttivita} entity.
 */
public interface InvitoAttivitaSearchRepository extends ElasticsearchRepository<InvitoAttivita, Long> {
}
