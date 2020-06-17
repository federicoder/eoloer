package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class ConsuntivoTaskTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsuntivoTask.class);
        ConsuntivoTask consuntivoTask1 = new ConsuntivoTask();
        consuntivoTask1.setId(1L);
        ConsuntivoTask consuntivoTask2 = new ConsuntivoTask();
        consuntivoTask2.setId(consuntivoTask1.getId());
        assertThat(consuntivoTask1).isEqualTo(consuntivoTask2);
        consuntivoTask2.setId(2L);
        assertThat(consuntivoTask1).isNotEqualTo(consuntivoTask2);
        consuntivoTask1.setId(null);
        assertThat(consuntivoTask1).isNotEqualTo(consuntivoTask2);
    }
}
