package it.maggioli.repository.search;

import it.maggioli.domain.LineaOrdine;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link LineaOrdine} entity.
 */
public interface LineaOrdineSearchRepository extends ElasticsearchRepository<LineaOrdine, Long> {
}
