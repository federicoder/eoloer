package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class AllegatoTemplateTaskTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AllegatoTemplateTask.class);
        AllegatoTemplateTask allegatoTemplateTask1 = new AllegatoTemplateTask();
        allegatoTemplateTask1.setId(1L);
        AllegatoTemplateTask allegatoTemplateTask2 = new AllegatoTemplateTask();
        allegatoTemplateTask2.setId(allegatoTemplateTask1.getId());
        assertThat(allegatoTemplateTask1).isEqualTo(allegatoTemplateTask2);
        allegatoTemplateTask2.setId(2L);
        assertThat(allegatoTemplateTask1).isNotEqualTo(allegatoTemplateTask2);
        allegatoTemplateTask1.setId(null);
        assertThat(allegatoTemplateTask1).isNotEqualTo(allegatoTemplateTask2);
    }
}
