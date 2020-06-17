package it.maggioli.repository.search;

import it.maggioli.domain.StudioProfessionale;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link StudioProfessionale} entity.
 */
public interface StudioProfessionaleSearchRepository extends ElasticsearchRepository<StudioProfessionale, Long> {
}
