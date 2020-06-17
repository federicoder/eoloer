package it.maggioli.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link NotaTaskSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class NotaTaskSearchRepositoryMockConfiguration {

    @MockBean
    private NotaTaskSearchRepository mockNotaTaskSearchRepository;

}
