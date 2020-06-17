package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class NotaTaskTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotaTask.class);
        NotaTask notaTask1 = new NotaTask();
        notaTask1.setId(1L);
        NotaTask notaTask2 = new NotaTask();
        notaTask2.setId(notaTask1.getId());
        assertThat(notaTask1).isEqualTo(notaTask2);
        notaTask2.setId(2L);
        assertThat(notaTask1).isNotEqualTo(notaTask2);
        notaTask1.setId(null);
        assertThat(notaTask1).isNotEqualTo(notaTask2);
    }
}
