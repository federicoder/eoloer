package it.maggioli.repository.search;

import it.maggioli.domain.RisorseDisponibili;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link RisorseDisponibili} entity.
 */
public interface RisorseDisponibiliSearchRepository extends ElasticsearchRepository<RisorseDisponibili, Long> {
}
