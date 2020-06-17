package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class InvitatoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Invitato.class);
        Invitato invitato1 = new Invitato();
        invitato1.setId(1L);
        Invitato invitato2 = new Invitato();
        invitato2.setId(invitato1.getId());
        assertThat(invitato1).isEqualTo(invitato2);
        invitato2.setId(2L);
        assertThat(invitato1).isNotEqualTo(invitato2);
        invitato1.setId(null);
        assertThat(invitato1).isNotEqualTo(invitato2);
    }
}
