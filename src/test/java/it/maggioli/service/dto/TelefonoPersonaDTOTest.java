package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class TelefonoPersonaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TelefonoPersonaDTO.class);
        TelefonoPersonaDTO telefonoPersonaDTO1 = new TelefonoPersonaDTO();
        telefonoPersonaDTO1.setId(1L);
        TelefonoPersonaDTO telefonoPersonaDTO2 = new TelefonoPersonaDTO();
        assertThat(telefonoPersonaDTO1).isNotEqualTo(telefonoPersonaDTO2);
        telefonoPersonaDTO2.setId(telefonoPersonaDTO1.getId());
        assertThat(telefonoPersonaDTO1).isEqualTo(telefonoPersonaDTO2);
        telefonoPersonaDTO2.setId(2L);
        assertThat(telefonoPersonaDTO1).isNotEqualTo(telefonoPersonaDTO2);
        telefonoPersonaDTO1.setId(null);
        assertThat(telefonoPersonaDTO1).isNotEqualTo(telefonoPersonaDTO2);
    }
}
