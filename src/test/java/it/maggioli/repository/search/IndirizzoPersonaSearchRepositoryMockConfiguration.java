package it.maggioli.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link IndirizzoPersonaSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class IndirizzoPersonaSearchRepositoryMockConfiguration {

    @MockBean
    private IndirizzoPersonaSearchRepository mockIndirizzoPersonaSearchRepository;

}
