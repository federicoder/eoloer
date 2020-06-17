package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class InvitoAttivitaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvitoAttivitaDTO.class);
        InvitoAttivitaDTO invitoAttivitaDTO1 = new InvitoAttivitaDTO();
        invitoAttivitaDTO1.setId(1L);
        InvitoAttivitaDTO invitoAttivitaDTO2 = new InvitoAttivitaDTO();
        assertThat(invitoAttivitaDTO1).isNotEqualTo(invitoAttivitaDTO2);
        invitoAttivitaDTO2.setId(invitoAttivitaDTO1.getId());
        assertThat(invitoAttivitaDTO1).isEqualTo(invitoAttivitaDTO2);
        invitoAttivitaDTO2.setId(2L);
        assertThat(invitoAttivitaDTO1).isNotEqualTo(invitoAttivitaDTO2);
        invitoAttivitaDTO1.setId(null);
        assertThat(invitoAttivitaDTO1).isNotEqualTo(invitoAttivitaDTO2);
    }
}
