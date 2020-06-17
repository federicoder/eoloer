package it.maggioli.repository.search;

import it.maggioli.domain.TemplateTask;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link TemplateTask} entity.
 */
public interface TemplateTaskSearchRepository extends ElasticsearchRepository<TemplateTask, Long> {
}
