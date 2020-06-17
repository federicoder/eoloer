package it.maggioli.repository.search;

import it.maggioli.domain.DatiContabili;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link DatiContabili} entity.
 */
public interface DatiContabiliSearchRepository extends ElasticsearchRepository<DatiContabili, Long> {
}
