package it.maggioli.repository.search;

import it.maggioli.domain.PrevisioneAttivita;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link PrevisioneAttivita} entity.
 */
public interface PrevisioneAttivitaSearchRepository extends ElasticsearchRepository<PrevisioneAttivita, Long> {
}
