package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class NotaPraticaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotaPratica.class);
        NotaPratica notaPratica1 = new NotaPratica();
        notaPratica1.setId(1L);
        NotaPratica notaPratica2 = new NotaPratica();
        notaPratica2.setId(notaPratica1.getId());
        assertThat(notaPratica1).isEqualTo(notaPratica2);
        notaPratica2.setId(2L);
        assertThat(notaPratica1).isNotEqualTo(notaPratica2);
        notaPratica1.setId(null);
        assertThat(notaPratica1).isNotEqualTo(notaPratica2);
    }
}
