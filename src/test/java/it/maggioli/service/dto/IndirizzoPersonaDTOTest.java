package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class IndirizzoPersonaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IndirizzoPersonaDTO.class);
        IndirizzoPersonaDTO indirizzoPersonaDTO1 = new IndirizzoPersonaDTO();
        indirizzoPersonaDTO1.setId(1L);
        IndirizzoPersonaDTO indirizzoPersonaDTO2 = new IndirizzoPersonaDTO();
        assertThat(indirizzoPersonaDTO1).isNotEqualTo(indirizzoPersonaDTO2);
        indirizzoPersonaDTO2.setId(indirizzoPersonaDTO1.getId());
        assertThat(indirizzoPersonaDTO1).isEqualTo(indirizzoPersonaDTO2);
        indirizzoPersonaDTO2.setId(2L);
        assertThat(indirizzoPersonaDTO1).isNotEqualTo(indirizzoPersonaDTO2);
        indirizzoPersonaDTO1.setId(null);
        assertThat(indirizzoPersonaDTO1).isNotEqualTo(indirizzoPersonaDTO2);
    }
}
