package it.maggioli.repository.search;

import it.maggioli.domain.TemplatePratica;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link TemplatePratica} entity.
 */
public interface TemplatePraticaSearchRepository extends ElasticsearchRepository<TemplatePratica, Long> {
}
