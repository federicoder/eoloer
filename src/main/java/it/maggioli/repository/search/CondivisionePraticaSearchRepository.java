package it.maggioli.repository.search;

import it.maggioli.domain.CondivisionePratica;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link CondivisionePratica} entity.
 */
public interface CondivisionePraticaSearchRepository extends ElasticsearchRepository<CondivisionePratica, Long> {
}
