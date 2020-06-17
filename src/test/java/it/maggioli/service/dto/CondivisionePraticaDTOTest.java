package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class CondivisionePraticaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CondivisionePraticaDTO.class);
        CondivisionePraticaDTO condivisionePraticaDTO1 = new CondivisionePraticaDTO();
        condivisionePraticaDTO1.setId(1L);
        CondivisionePraticaDTO condivisionePraticaDTO2 = new CondivisionePraticaDTO();
        assertThat(condivisionePraticaDTO1).isNotEqualTo(condivisionePraticaDTO2);
        condivisionePraticaDTO2.setId(condivisionePraticaDTO1.getId());
        assertThat(condivisionePraticaDTO1).isEqualTo(condivisionePraticaDTO2);
        condivisionePraticaDTO2.setId(2L);
        assertThat(condivisionePraticaDTO1).isNotEqualTo(condivisionePraticaDTO2);
        condivisionePraticaDTO1.setId(null);
        assertThat(condivisionePraticaDTO1).isNotEqualTo(condivisionePraticaDTO2);
    }
}
