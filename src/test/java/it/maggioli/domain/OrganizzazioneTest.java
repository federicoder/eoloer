package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class OrganizzazioneTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Organizzazione.class);
        Organizzazione organizzazione1 = new Organizzazione();
        organizzazione1.setId(1L);
        Organizzazione organizzazione2 = new Organizzazione();
        organizzazione2.setId(organizzazione1.getId());
        assertThat(organizzazione1).isEqualTo(organizzazione2);
        organizzazione2.setId(2L);
        assertThat(organizzazione1).isNotEqualTo(organizzazione2);
        organizzazione1.setId(null);
        assertThat(organizzazione1).isNotEqualTo(organizzazione2);
    }
}
