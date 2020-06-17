package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class NotaPraticaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotaPraticaDTO.class);
        NotaPraticaDTO notaPraticaDTO1 = new NotaPraticaDTO();
        notaPraticaDTO1.setId(1L);
        NotaPraticaDTO notaPraticaDTO2 = new NotaPraticaDTO();
        assertThat(notaPraticaDTO1).isNotEqualTo(notaPraticaDTO2);
        notaPraticaDTO2.setId(notaPraticaDTO1.getId());
        assertThat(notaPraticaDTO1).isEqualTo(notaPraticaDTO2);
        notaPraticaDTO2.setId(2L);
        assertThat(notaPraticaDTO1).isNotEqualTo(notaPraticaDTO2);
        notaPraticaDTO1.setId(null);
        assertThat(notaPraticaDTO1).isNotEqualTo(notaPraticaDTO2);
    }
}
