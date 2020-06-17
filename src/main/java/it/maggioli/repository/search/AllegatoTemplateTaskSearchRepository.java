package it.maggioli.repository.search;

import it.maggioli.domain.AllegatoTemplateTask;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link AllegatoTemplateTask} entity.
 */
public interface AllegatoTemplateTaskSearchRepository extends ElasticsearchRepository<AllegatoTemplateTask, Long> {
}
