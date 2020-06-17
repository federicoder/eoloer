package it.maggioli.repository.search;

import it.maggioli.domain.InvitoEvento;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link InvitoEvento} entity.
 */
public interface InvitoEventoSearchRepository extends ElasticsearchRepository<InvitoEvento, Long> {
}
