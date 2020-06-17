package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class RappresentanzaPraticaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RappresentanzaPraticaDTO.class);
        RappresentanzaPraticaDTO rappresentanzaPraticaDTO1 = new RappresentanzaPraticaDTO();
        rappresentanzaPraticaDTO1.setId(1L);
        RappresentanzaPraticaDTO rappresentanzaPraticaDTO2 = new RappresentanzaPraticaDTO();
        assertThat(rappresentanzaPraticaDTO1).isNotEqualTo(rappresentanzaPraticaDTO2);
        rappresentanzaPraticaDTO2.setId(rappresentanzaPraticaDTO1.getId());
        assertThat(rappresentanzaPraticaDTO1).isEqualTo(rappresentanzaPraticaDTO2);
        rappresentanzaPraticaDTO2.setId(2L);
        assertThat(rappresentanzaPraticaDTO1).isNotEqualTo(rappresentanzaPraticaDTO2);
        rappresentanzaPraticaDTO1.setId(null);
        assertThat(rappresentanzaPraticaDTO1).isNotEqualTo(rappresentanzaPraticaDTO2);
    }
}
