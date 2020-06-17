package it.maggioli.repository.search;

import it.maggioli.domain.UserPersona;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link UserPersona} entity.
 */
public interface UserPersonaSearchRepository extends ElasticsearchRepository<UserPersona, Long> {
}
