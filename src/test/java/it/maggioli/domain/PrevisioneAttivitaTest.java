package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class PrevisioneAttivitaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrevisioneAttivita.class);
        PrevisioneAttivita previsioneAttivita1 = new PrevisioneAttivita();
        previsioneAttivita1.setId(1L);
        PrevisioneAttivita previsioneAttivita2 = new PrevisioneAttivita();
        previsioneAttivita2.setId(previsioneAttivita1.getId());
        assertThat(previsioneAttivita1).isEqualTo(previsioneAttivita2);
        previsioneAttivita2.setId(2L);
        assertThat(previsioneAttivita1).isNotEqualTo(previsioneAttivita2);
        previsioneAttivita1.setId(null);
        assertThat(previsioneAttivita1).isNotEqualTo(previsioneAttivita2);
    }
}
