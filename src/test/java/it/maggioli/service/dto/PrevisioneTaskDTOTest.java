package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class PrevisioneTaskDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrevisioneTaskDTO.class);
        PrevisioneTaskDTO previsioneTaskDTO1 = new PrevisioneTaskDTO();
        previsioneTaskDTO1.setId(1L);
        PrevisioneTaskDTO previsioneTaskDTO2 = new PrevisioneTaskDTO();
        assertThat(previsioneTaskDTO1).isNotEqualTo(previsioneTaskDTO2);
        previsioneTaskDTO2.setId(previsioneTaskDTO1.getId());
        assertThat(previsioneTaskDTO1).isEqualTo(previsioneTaskDTO2);
        previsioneTaskDTO2.setId(2L);
        assertThat(previsioneTaskDTO1).isNotEqualTo(previsioneTaskDTO2);
        previsioneTaskDTO1.setId(null);
        assertThat(previsioneTaskDTO1).isNotEqualTo(previsioneTaskDTO2);
    }
}
