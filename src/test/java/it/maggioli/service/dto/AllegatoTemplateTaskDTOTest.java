package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class AllegatoTemplateTaskDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AllegatoTemplateTaskDTO.class);
        AllegatoTemplateTaskDTO allegatoTemplateTaskDTO1 = new AllegatoTemplateTaskDTO();
        allegatoTemplateTaskDTO1.setId(1L);
        AllegatoTemplateTaskDTO allegatoTemplateTaskDTO2 = new AllegatoTemplateTaskDTO();
        assertThat(allegatoTemplateTaskDTO1).isNotEqualTo(allegatoTemplateTaskDTO2);
        allegatoTemplateTaskDTO2.setId(allegatoTemplateTaskDTO1.getId());
        assertThat(allegatoTemplateTaskDTO1).isEqualTo(allegatoTemplateTaskDTO2);
        allegatoTemplateTaskDTO2.setId(2L);
        assertThat(allegatoTemplateTaskDTO1).isNotEqualTo(allegatoTemplateTaskDTO2);
        allegatoTemplateTaskDTO1.setId(null);
        assertThat(allegatoTemplateTaskDTO1).isNotEqualTo(allegatoTemplateTaskDTO2);
    }
}
