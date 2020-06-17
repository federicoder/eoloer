package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class NotaTaskDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotaTaskDTO.class);
        NotaTaskDTO notaTaskDTO1 = new NotaTaskDTO();
        notaTaskDTO1.setId(1L);
        NotaTaskDTO notaTaskDTO2 = new NotaTaskDTO();
        assertThat(notaTaskDTO1).isNotEqualTo(notaTaskDTO2);
        notaTaskDTO2.setId(notaTaskDTO1.getId());
        assertThat(notaTaskDTO1).isEqualTo(notaTaskDTO2);
        notaTaskDTO2.setId(2L);
        assertThat(notaTaskDTO1).isNotEqualTo(notaTaskDTO2);
        notaTaskDTO1.setId(null);
        assertThat(notaTaskDTO1).isNotEqualTo(notaTaskDTO2);
    }
}
