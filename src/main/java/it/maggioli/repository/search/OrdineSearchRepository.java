package it.maggioli.repository.search;

import it.maggioli.domain.Ordine;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Ordine} entity.
 */
public interface OrdineSearchRepository extends ElasticsearchRepository<Ordine, Long> {
}
