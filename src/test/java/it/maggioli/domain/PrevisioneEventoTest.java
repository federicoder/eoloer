package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class PrevisioneEventoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrevisioneEvento.class);
        PrevisioneEvento previsioneEvento1 = new PrevisioneEvento();
        previsioneEvento1.setId(1L);
        PrevisioneEvento previsioneEvento2 = new PrevisioneEvento();
        previsioneEvento2.setId(previsioneEvento1.getId());
        assertThat(previsioneEvento1).isEqualTo(previsioneEvento2);
        previsioneEvento2.setId(2L);
        assertThat(previsioneEvento1).isNotEqualTo(previsioneEvento2);
        previsioneEvento1.setId(null);
        assertThat(previsioneEvento1).isNotEqualTo(previsioneEvento2);
    }
}
