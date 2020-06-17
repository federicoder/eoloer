package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class AllegatoTaskDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AllegatoTaskDTO.class);
        AllegatoTaskDTO allegatoTaskDTO1 = new AllegatoTaskDTO();
        allegatoTaskDTO1.setId(1L);
        AllegatoTaskDTO allegatoTaskDTO2 = new AllegatoTaskDTO();
        assertThat(allegatoTaskDTO1).isNotEqualTo(allegatoTaskDTO2);
        allegatoTaskDTO2.setId(allegatoTaskDTO1.getId());
        assertThat(allegatoTaskDTO1).isEqualTo(allegatoTaskDTO2);
        allegatoTaskDTO2.setId(2L);
        assertThat(allegatoTaskDTO1).isNotEqualTo(allegatoTaskDTO2);
        allegatoTaskDTO1.setId(null);
        assertThat(allegatoTaskDTO1).isNotEqualTo(allegatoTaskDTO2);
    }
}
