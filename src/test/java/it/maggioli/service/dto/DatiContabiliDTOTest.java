package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class DatiContabiliDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DatiContabiliDTO.class);
        DatiContabiliDTO datiContabiliDTO1 = new DatiContabiliDTO();
        datiContabiliDTO1.setId(1L);
        DatiContabiliDTO datiContabiliDTO2 = new DatiContabiliDTO();
        assertThat(datiContabiliDTO1).isNotEqualTo(datiContabiliDTO2);
        datiContabiliDTO2.setId(datiContabiliDTO1.getId());
        assertThat(datiContabiliDTO1).isEqualTo(datiContabiliDTO2);
        datiContabiliDTO2.setId(2L);
        assertThat(datiContabiliDTO1).isNotEqualTo(datiContabiliDTO2);
        datiContabiliDTO1.setId(null);
        assertThat(datiContabiliDTO1).isNotEqualTo(datiContabiliDTO2);
    }
}
