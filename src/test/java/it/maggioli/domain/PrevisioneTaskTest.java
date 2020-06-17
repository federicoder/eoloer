package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class PrevisioneTaskTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrevisioneTask.class);
        PrevisioneTask previsioneTask1 = new PrevisioneTask();
        previsioneTask1.setId(1L);
        PrevisioneTask previsioneTask2 = new PrevisioneTask();
        previsioneTask2.setId(previsioneTask1.getId());
        assertThat(previsioneTask1).isEqualTo(previsioneTask2);
        previsioneTask2.setId(2L);
        assertThat(previsioneTask1).isNotEqualTo(previsioneTask2);
        previsioneTask1.setId(null);
        assertThat(previsioneTask1).isNotEqualTo(previsioneTask2);
    }
}
