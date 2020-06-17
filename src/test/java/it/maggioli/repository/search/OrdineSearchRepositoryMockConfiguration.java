package it.maggioli.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link OrdineSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class OrdineSearchRepositoryMockConfiguration {

    @MockBean
    private OrdineSearchRepository mockOrdineSearchRepository;

}
