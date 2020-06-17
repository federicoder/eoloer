package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class IndirizzoPersonaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IndirizzoPersona.class);
        IndirizzoPersona indirizzoPersona1 = new IndirizzoPersona();
        indirizzoPersona1.setId(1L);
        IndirizzoPersona indirizzoPersona2 = new IndirizzoPersona();
        indirizzoPersona2.setId(indirizzoPersona1.getId());
        assertThat(indirizzoPersona1).isEqualTo(indirizzoPersona2);
        indirizzoPersona2.setId(2L);
        assertThat(indirizzoPersona1).isNotEqualTo(indirizzoPersona2);
        indirizzoPersona1.setId(null);
        assertThat(indirizzoPersona1).isNotEqualTo(indirizzoPersona2);
    }
}
