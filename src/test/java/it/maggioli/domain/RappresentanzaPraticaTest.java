package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class RappresentanzaPraticaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RappresentanzaPratica.class);
        RappresentanzaPratica rappresentanzaPratica1 = new RappresentanzaPratica();
        rappresentanzaPratica1.setId(1L);
        RappresentanzaPratica rappresentanzaPratica2 = new RappresentanzaPratica();
        rappresentanzaPratica2.setId(rappresentanzaPratica1.getId());
        assertThat(rappresentanzaPratica1).isEqualTo(rappresentanzaPratica2);
        rappresentanzaPratica2.setId(2L);
        assertThat(rappresentanzaPratica1).isNotEqualTo(rappresentanzaPratica2);
        rappresentanzaPratica1.setId(null);
        assertThat(rappresentanzaPratica1).isNotEqualTo(rappresentanzaPratica2);
    }
}
