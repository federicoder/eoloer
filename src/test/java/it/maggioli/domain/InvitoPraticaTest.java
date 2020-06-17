package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class InvitoPraticaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvitoPratica.class);
        InvitoPratica invitoPratica1 = new InvitoPratica();
        invitoPratica1.setId(1L);
        InvitoPratica invitoPratica2 = new InvitoPratica();
        invitoPratica2.setId(invitoPratica1.getId());
        assertThat(invitoPratica1).isEqualTo(invitoPratica2);
        invitoPratica2.setId(2L);
        assertThat(invitoPratica1).isNotEqualTo(invitoPratica2);
        invitoPratica1.setId(null);
        assertThat(invitoPratica1).isNotEqualTo(invitoPratica2);
    }
}
