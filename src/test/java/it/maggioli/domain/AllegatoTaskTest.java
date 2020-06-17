package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class AllegatoTaskTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AllegatoTask.class);
        AllegatoTask allegatoTask1 = new AllegatoTask();
        allegatoTask1.setId(1L);
        AllegatoTask allegatoTask2 = new AllegatoTask();
        allegatoTask2.setId(allegatoTask1.getId());
        assertThat(allegatoTask1).isEqualTo(allegatoTask2);
        allegatoTask2.setId(2L);
        assertThat(allegatoTask1).isNotEqualTo(allegatoTask2);
        allegatoTask1.setId(null);
        assertThat(allegatoTask1).isNotEqualTo(allegatoTask2);
    }
}
