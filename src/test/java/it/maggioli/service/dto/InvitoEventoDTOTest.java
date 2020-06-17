package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class InvitoEventoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvitoEventoDTO.class);
        InvitoEventoDTO invitoEventoDTO1 = new InvitoEventoDTO();
        invitoEventoDTO1.setId(1L);
        InvitoEventoDTO invitoEventoDTO2 = new InvitoEventoDTO();
        assertThat(invitoEventoDTO1).isNotEqualTo(invitoEventoDTO2);
        invitoEventoDTO2.setId(invitoEventoDTO1.getId());
        assertThat(invitoEventoDTO1).isEqualTo(invitoEventoDTO2);
        invitoEventoDTO2.setId(2L);
        assertThat(invitoEventoDTO1).isNotEqualTo(invitoEventoDTO2);
        invitoEventoDTO1.setId(null);
        assertThat(invitoEventoDTO1).isNotEqualTo(invitoEventoDTO2);
    }
}
