package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class PrevisioneEventoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrevisioneEventoDTO.class);
        PrevisioneEventoDTO previsioneEventoDTO1 = new PrevisioneEventoDTO();
        previsioneEventoDTO1.setId(1L);
        PrevisioneEventoDTO previsioneEventoDTO2 = new PrevisioneEventoDTO();
        assertThat(previsioneEventoDTO1).isNotEqualTo(previsioneEventoDTO2);
        previsioneEventoDTO2.setId(previsioneEventoDTO1.getId());
        assertThat(previsioneEventoDTO1).isEqualTo(previsioneEventoDTO2);
        previsioneEventoDTO2.setId(2L);
        assertThat(previsioneEventoDTO1).isNotEqualTo(previsioneEventoDTO2);
        previsioneEventoDTO1.setId(null);
        assertThat(previsioneEventoDTO1).isNotEqualTo(previsioneEventoDTO2);
    }
}
