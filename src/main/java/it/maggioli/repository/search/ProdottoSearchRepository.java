package it.maggioli.repository.search;

import it.maggioli.domain.Prodotto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Prodotto} entity.
 */
public interface ProdottoSearchRepository extends ElasticsearchRepository<Prodotto, Long> {
}
