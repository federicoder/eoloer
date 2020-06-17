package it.maggioli.repository.search;

import it.maggioli.domain.ConsuntivoTask;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link ConsuntivoTask} entity.
 */
public interface ConsuntivoTaskSearchRepository extends ElasticsearchRepository<ConsuntivoTask, Long> {
}
