package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class RisorseDisponibiliDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RisorseDisponibiliDTO.class);
        RisorseDisponibiliDTO risorseDisponibiliDTO1 = new RisorseDisponibiliDTO();
        risorseDisponibiliDTO1.setId(1L);
        RisorseDisponibiliDTO risorseDisponibiliDTO2 = new RisorseDisponibiliDTO();
        assertThat(risorseDisponibiliDTO1).isNotEqualTo(risorseDisponibiliDTO2);
        risorseDisponibiliDTO2.setId(risorseDisponibiliDTO1.getId());
        assertThat(risorseDisponibiliDTO1).isEqualTo(risorseDisponibiliDTO2);
        risorseDisponibiliDTO2.setId(2L);
        assertThat(risorseDisponibiliDTO1).isNotEqualTo(risorseDisponibiliDTO2);
        risorseDisponibiliDTO1.setId(null);
        assertThat(risorseDisponibiliDTO1).isNotEqualTo(risorseDisponibiliDTO2);
    }
}
