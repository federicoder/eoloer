package it.maggioli.repository.search;

import it.maggioli.domain.NotePersona;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link NotePersona} entity.
 */
public interface NotePersonaSearchRepository extends ElasticsearchRepository<NotePersona, Long> {
}
