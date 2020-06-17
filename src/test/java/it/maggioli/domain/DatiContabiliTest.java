package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class DatiContabiliTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DatiContabili.class);
        DatiContabili datiContabili1 = new DatiContabili();
        datiContabili1.setId(1L);
        DatiContabili datiContabili2 = new DatiContabili();
        datiContabili2.setId(datiContabili1.getId());
        assertThat(datiContabili1).isEqualTo(datiContabili2);
        datiContabili2.setId(2L);
        assertThat(datiContabili1).isNotEqualTo(datiContabili2);
        datiContabili1.setId(null);
        assertThat(datiContabili1).isNotEqualTo(datiContabili2);
    }
}
