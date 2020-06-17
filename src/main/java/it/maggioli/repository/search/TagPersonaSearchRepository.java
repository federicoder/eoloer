package it.maggioli.repository.search;

import it.maggioli.domain.TagPersona;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link TagPersona} entity.
 */
public interface TagPersonaSearchRepository extends ElasticsearchRepository<TagPersona, Long> {
}
