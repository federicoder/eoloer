package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class InvitatoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvitatoDTO.class);
        InvitatoDTO invitatoDTO1 = new InvitatoDTO();
        invitatoDTO1.setId(1L);
        InvitatoDTO invitatoDTO2 = new InvitatoDTO();
        assertThat(invitatoDTO1).isNotEqualTo(invitatoDTO2);
        invitatoDTO2.setId(invitatoDTO1.getId());
        assertThat(invitatoDTO1).isEqualTo(invitatoDTO2);
        invitatoDTO2.setId(2L);
        assertThat(invitatoDTO1).isNotEqualTo(invitatoDTO2);
        invitatoDTO1.setId(null);
        assertThat(invitatoDTO1).isNotEqualTo(invitatoDTO2);
    }
}
