package it.maggioli.repository.search;

import it.maggioli.domain.AllegatoTask;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link AllegatoTask} entity.
 */
public interface AllegatoTaskSearchRepository extends ElasticsearchRepository<AllegatoTask, Long> {
}
