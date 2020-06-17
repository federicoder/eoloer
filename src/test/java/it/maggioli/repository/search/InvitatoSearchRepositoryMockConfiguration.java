package it.maggioli.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link InvitatoSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class InvitatoSearchRepositoryMockConfiguration {

    @MockBean
    private InvitatoSearchRepository mockInvitatoSearchRepository;

}
