package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class ConsuntivoTaskDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsuntivoTaskDTO.class);
        ConsuntivoTaskDTO consuntivoTaskDTO1 = new ConsuntivoTaskDTO();
        consuntivoTaskDTO1.setId(1L);
        ConsuntivoTaskDTO consuntivoTaskDTO2 = new ConsuntivoTaskDTO();
        assertThat(consuntivoTaskDTO1).isNotEqualTo(consuntivoTaskDTO2);
        consuntivoTaskDTO2.setId(consuntivoTaskDTO1.getId());
        assertThat(consuntivoTaskDTO1).isEqualTo(consuntivoTaskDTO2);
        consuntivoTaskDTO2.setId(2L);
        assertThat(consuntivoTaskDTO1).isNotEqualTo(consuntivoTaskDTO2);
        consuntivoTaskDTO1.setId(null);
        assertThat(consuntivoTaskDTO1).isNotEqualTo(consuntivoTaskDTO2);
    }
}
