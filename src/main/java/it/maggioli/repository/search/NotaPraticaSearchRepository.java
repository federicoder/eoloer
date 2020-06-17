package it.maggioli.repository.search;

import it.maggioli.domain.NotaPratica;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link NotaPratica} entity.
 */
public interface NotaPraticaSearchRepository extends ElasticsearchRepository<NotaPratica, Long> {
}
