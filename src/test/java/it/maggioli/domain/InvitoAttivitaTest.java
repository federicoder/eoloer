package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class InvitoAttivitaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvitoAttivita.class);
        InvitoAttivita invitoAttivita1 = new InvitoAttivita();
        invitoAttivita1.setId(1L);
        InvitoAttivita invitoAttivita2 = new InvitoAttivita();
        invitoAttivita2.setId(invitoAttivita1.getId());
        assertThat(invitoAttivita1).isEqualTo(invitoAttivita2);
        invitoAttivita2.setId(2L);
        assertThat(invitoAttivita1).isNotEqualTo(invitoAttivita2);
        invitoAttivita1.setId(null);
        assertThat(invitoAttivita1).isNotEqualTo(invitoAttivita2);
    }
}
