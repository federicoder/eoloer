package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class InvitoEventoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvitoEvento.class);
        InvitoEvento invitoEvento1 = new InvitoEvento();
        invitoEvento1.setId(1L);
        InvitoEvento invitoEvento2 = new InvitoEvento();
        invitoEvento2.setId(invitoEvento1.getId());
        assertThat(invitoEvento1).isEqualTo(invitoEvento2);
        invitoEvento2.setId(2L);
        assertThat(invitoEvento1).isNotEqualTo(invitoEvento2);
        invitoEvento1.setId(null);
        assertThat(invitoEvento1).isNotEqualTo(invitoEvento2);
    }
}
