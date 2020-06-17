package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class EmailPersonaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmailPersonaDTO.class);
        EmailPersonaDTO emailPersonaDTO1 = new EmailPersonaDTO();
        emailPersonaDTO1.setId(1L);
        EmailPersonaDTO emailPersonaDTO2 = new EmailPersonaDTO();
        assertThat(emailPersonaDTO1).isNotEqualTo(emailPersonaDTO2);
        emailPersonaDTO2.setId(emailPersonaDTO1.getId());
        assertThat(emailPersonaDTO1).isEqualTo(emailPersonaDTO2);
        emailPersonaDTO2.setId(2L);
        assertThat(emailPersonaDTO1).isNotEqualTo(emailPersonaDTO2);
        emailPersonaDTO1.setId(null);
        assertThat(emailPersonaDTO1).isNotEqualTo(emailPersonaDTO2);
    }
}
