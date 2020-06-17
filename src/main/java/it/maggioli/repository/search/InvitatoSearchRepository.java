package it.maggioli.repository.search;

import it.maggioli.domain.Invitato;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Invitato} entity.
 */
public interface InvitatoSearchRepository extends ElasticsearchRepository<Invitato, Long> {
}
