package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class CondivisionePraticaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CondivisionePratica.class);
        CondivisionePratica condivisionePratica1 = new CondivisionePratica();
        condivisionePratica1.setId(1L);
        CondivisionePratica condivisionePratica2 = new CondivisionePratica();
        condivisionePratica2.setId(condivisionePratica1.getId());
        assertThat(condivisionePratica1).isEqualTo(condivisionePratica2);
        condivisionePratica2.setId(2L);
        assertThat(condivisionePratica1).isNotEqualTo(condivisionePratica2);
        condivisionePratica1.setId(null);
        assertThat(condivisionePratica1).isNotEqualTo(condivisionePratica2);
    }
}
