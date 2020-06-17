package it.maggioli.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link TemplatePraticaSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class TemplatePraticaSearchRepositoryMockConfiguration {

    @MockBean
    private TemplatePraticaSearchRepository mockTemplatePraticaSearchRepository;

}
