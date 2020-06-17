package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class TipoAllegatoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoAllegatoDTO.class);
        TipoAllegatoDTO tipoAllegatoDTO1 = new TipoAllegatoDTO();
        tipoAllegatoDTO1.setId(1L);
        TipoAllegatoDTO tipoAllegatoDTO2 = new TipoAllegatoDTO();
        assertThat(tipoAllegatoDTO1).isNotEqualTo(tipoAllegatoDTO2);
        tipoAllegatoDTO2.setId(tipoAllegatoDTO1.getId());
        assertThat(tipoAllegatoDTO1).isEqualTo(tipoAllegatoDTO2);
        tipoAllegatoDTO2.setId(2L);
        assertThat(tipoAllegatoDTO1).isNotEqualTo(tipoAllegatoDTO2);
        tipoAllegatoDTO1.setId(null);
        assertThat(tipoAllegatoDTO1).isNotEqualTo(tipoAllegatoDTO2);
    }
}
