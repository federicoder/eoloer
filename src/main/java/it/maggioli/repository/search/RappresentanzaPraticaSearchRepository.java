package it.maggioli.repository.search;

import it.maggioli.domain.RappresentanzaPratica;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link RappresentanzaPratica} entity.
 */
public interface RappresentanzaPraticaSearchRepository extends ElasticsearchRepository<RappresentanzaPratica, Long> {
}
