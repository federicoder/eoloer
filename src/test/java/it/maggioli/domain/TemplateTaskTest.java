package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class TemplateTaskTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TemplateTask.class);
        TemplateTask templateTask1 = new TemplateTask();
        templateTask1.setId(1L);
        TemplateTask templateTask2 = new TemplateTask();
        templateTask2.setId(templateTask1.getId());
        assertThat(templateTask1).isEqualTo(templateTask2);
        templateTask2.setId(2L);
        assertThat(templateTask1).isNotEqualTo(templateTask2);
        templateTask1.setId(null);
        assertThat(templateTask1).isNotEqualTo(templateTask2);
    }
}
