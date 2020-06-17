package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class PrevisioneAttivitaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrevisioneAttivitaDTO.class);
        PrevisioneAttivitaDTO previsioneAttivitaDTO1 = new PrevisioneAttivitaDTO();
        previsioneAttivitaDTO1.setId(1L);
        PrevisioneAttivitaDTO previsioneAttivitaDTO2 = new PrevisioneAttivitaDTO();
        assertThat(previsioneAttivitaDTO1).isNotEqualTo(previsioneAttivitaDTO2);
        previsioneAttivitaDTO2.setId(previsioneAttivitaDTO1.getId());
        assertThat(previsioneAttivitaDTO1).isEqualTo(previsioneAttivitaDTO2);
        previsioneAttivitaDTO2.setId(2L);
        assertThat(previsioneAttivitaDTO1).isNotEqualTo(previsioneAttivitaDTO2);
        previsioneAttivitaDTO1.setId(null);
        assertThat(previsioneAttivitaDTO1).isNotEqualTo(previsioneAttivitaDTO2);
    }
}
