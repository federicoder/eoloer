package it.maggioli.repository.search;

import it.maggioli.domain.InvitoPratica;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link InvitoPratica} entity.
 */
public interface InvitoPraticaSearchRepository extends ElasticsearchRepository<InvitoPratica, Long> {
}
