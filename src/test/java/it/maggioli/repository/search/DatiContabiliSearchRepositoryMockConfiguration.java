package it.maggioli.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link DatiContabiliSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class DatiContabiliSearchRepositoryMockConfiguration {

    @MockBean
    private DatiContabiliSearchRepository mockDatiContabiliSearchRepository;

}
