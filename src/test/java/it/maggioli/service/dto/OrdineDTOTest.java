package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class OrdineDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrdineDTO.class);
        OrdineDTO ordineDTO1 = new OrdineDTO();
        ordineDTO1.setId(1L);
        OrdineDTO ordineDTO2 = new OrdineDTO();
        assertThat(ordineDTO1).isNotEqualTo(ordineDTO2);
        ordineDTO2.setId(ordineDTO1.getId());
        assertThat(ordineDTO1).isEqualTo(ordineDTO2);
        ordineDTO2.setId(2L);
        assertThat(ordineDTO1).isNotEqualTo(ordineDTO2);
        ordineDTO1.setId(null);
        assertThat(ordineDTO1).isNotEqualTo(ordineDTO2);
    }
}
