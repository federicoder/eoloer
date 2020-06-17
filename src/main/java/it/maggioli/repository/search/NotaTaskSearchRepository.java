package it.maggioli.repository.search;

import it.maggioli.domain.NotaTask;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link NotaTask} entity.
 */
public interface NotaTaskSearchRepository extends ElasticsearchRepository<NotaTask, Long> {
}
