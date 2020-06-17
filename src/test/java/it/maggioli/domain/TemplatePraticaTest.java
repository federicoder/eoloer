package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class TemplatePraticaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TemplatePratica.class);
        TemplatePratica templatePratica1 = new TemplatePratica();
        templatePratica1.setId(1L);
        TemplatePratica templatePratica2 = new TemplatePratica();
        templatePratica2.setId(templatePratica1.getId());
        assertThat(templatePratica1).isEqualTo(templatePratica2);
        templatePratica2.setId(2L);
        assertThat(templatePratica1).isNotEqualTo(templatePratica2);
        templatePratica1.setId(null);
        assertThat(templatePratica1).isNotEqualTo(templatePratica2);
    }
}
