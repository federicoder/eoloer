package it.maggioli.repository.search;

import it.maggioli.domain.PersonaFisica;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link PersonaFisica} entity.
 */
public interface PersonaFisicaSearchRepository extends ElasticsearchRepository<PersonaFisica, Long> {
}
