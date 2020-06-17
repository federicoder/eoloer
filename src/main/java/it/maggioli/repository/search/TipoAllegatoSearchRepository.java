package it.maggioli.repository.search;

import it.maggioli.domain.TipoAllegato;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link TipoAllegato} entity.
 */
public interface TipoAllegatoSearchRepository extends ElasticsearchRepository<TipoAllegato, Long> {
}
