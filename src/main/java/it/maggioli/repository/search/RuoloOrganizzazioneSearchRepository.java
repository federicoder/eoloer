package it.maggioli.repository.search;

import it.maggioli.domain.RuoloOrganizzazione;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link RuoloOrganizzazione} entity.
 */
public interface RuoloOrganizzazioneSearchRepository extends ElasticsearchRepository<RuoloOrganizzazione, Long> {
}
