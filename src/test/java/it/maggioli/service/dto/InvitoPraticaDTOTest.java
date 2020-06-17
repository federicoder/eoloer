package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class InvitoPraticaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvitoPraticaDTO.class);
        InvitoPraticaDTO invitoPraticaDTO1 = new InvitoPraticaDTO();
        invitoPraticaDTO1.setId(1L);
        InvitoPraticaDTO invitoPraticaDTO2 = new InvitoPraticaDTO();
        assertThat(invitoPraticaDTO1).isNotEqualTo(invitoPraticaDTO2);
        invitoPraticaDTO2.setId(invitoPraticaDTO1.getId());
        assertThat(invitoPraticaDTO1).isEqualTo(invitoPraticaDTO2);
        invitoPraticaDTO2.setId(2L);
        assertThat(invitoPraticaDTO1).isNotEqualTo(invitoPraticaDTO2);
        invitoPraticaDTO1.setId(null);
        assertThat(invitoPraticaDTO1).isNotEqualTo(invitoPraticaDTO2);
    }
}
