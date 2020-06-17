package it.maggioli.repository.search;

import it.maggioli.domain.PrevisioneEvento;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link PrevisioneEvento} entity.
 */
public interface PrevisioneEventoSearchRepository extends ElasticsearchRepository<PrevisioneEvento, Long> {
}
