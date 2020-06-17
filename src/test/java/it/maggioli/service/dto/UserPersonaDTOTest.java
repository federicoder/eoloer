package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class UserPersonaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserPersonaDTO.class);
        UserPersonaDTO userPersonaDTO1 = new UserPersonaDTO();
        userPersonaDTO1.setId(1L);
        UserPersonaDTO userPersonaDTO2 = new UserPersonaDTO();
        assertThat(userPersonaDTO1).isNotEqualTo(userPersonaDTO2);
        userPersonaDTO2.setId(userPersonaDTO1.getId());
        assertThat(userPersonaDTO1).isEqualTo(userPersonaDTO2);
        userPersonaDTO2.setId(2L);
        assertThat(userPersonaDTO1).isNotEqualTo(userPersonaDTO2);
        userPersonaDTO1.setId(null);
        assertThat(userPersonaDTO1).isNotEqualTo(userPersonaDTO2);
    }
}
