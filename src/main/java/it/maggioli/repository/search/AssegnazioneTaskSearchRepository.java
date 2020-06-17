package it.maggioli.repository.search;

import it.maggioli.domain.AssegnazioneTask;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link AssegnazioneTask} entity.
 */
public interface AssegnazioneTaskSearchRepository extends ElasticsearchRepository<AssegnazioneTask, Long> {
}
