package it.maggioli.repository.search;

import it.maggioli.domain.PrevisioneTask;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link PrevisioneTask} entity.
 */
public interface PrevisioneTaskSearchRepository extends ElasticsearchRepository<PrevisioneTask, Long> {
}
