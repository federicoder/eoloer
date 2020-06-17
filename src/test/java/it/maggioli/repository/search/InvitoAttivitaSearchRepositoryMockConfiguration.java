package it.maggioli.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link InvitoAttivitaSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class InvitoAttivitaSearchRepositoryMockConfiguration {

    @MockBean
    private InvitoAttivitaSearchRepository mockInvitoAttivitaSearchRepository;

}
