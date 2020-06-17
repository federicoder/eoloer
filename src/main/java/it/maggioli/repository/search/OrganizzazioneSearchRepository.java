package it.maggioli.repository.search;

import it.maggioli.domain.Organizzazione;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Organizzazione} entity.
 */
public interface OrganizzazioneSearchRepository extends ElasticsearchRepository<Organizzazione, Long> {
}
