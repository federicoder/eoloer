package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class LineaOrdineDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LineaOrdineDTO.class);
        LineaOrdineDTO lineaOrdineDTO1 = new LineaOrdineDTO();
        lineaOrdineDTO1.setId(1L);
        LineaOrdineDTO lineaOrdineDTO2 = new LineaOrdineDTO();
        assertThat(lineaOrdineDTO1).isNotEqualTo(lineaOrdineDTO2);
        lineaOrdineDTO2.setId(lineaOrdineDTO1.getId());
        assertThat(lineaOrdineDTO1).isEqualTo(lineaOrdineDTO2);
        lineaOrdineDTO2.setId(2L);
        assertThat(lineaOrdineDTO1).isNotEqualTo(lineaOrdineDTO2);
        lineaOrdineDTO1.setId(null);
        assertThat(lineaOrdineDTO1).isNotEqualTo(lineaOrdineDTO2);
    }
}
