package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class TelefonoPersonaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TelefonoPersona.class);
        TelefonoPersona telefonoPersona1 = new TelefonoPersona();
        telefonoPersona1.setId(1L);
        TelefonoPersona telefonoPersona2 = new TelefonoPersona();
        telefonoPersona2.setId(telefonoPersona1.getId());
        assertThat(telefonoPersona1).isEqualTo(telefonoPersona2);
        telefonoPersona2.setId(2L);
        assertThat(telefonoPersona1).isNotEqualTo(telefonoPersona2);
        telefonoPersona1.setId(null);
        assertThat(telefonoPersona1).isNotEqualTo(telefonoPersona2);
    }
}
