package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class AssegnazioneTaskTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AssegnazioneTask.class);
        AssegnazioneTask assegnazioneTask1 = new AssegnazioneTask();
        assegnazioneTask1.setId(1L);
        AssegnazioneTask assegnazioneTask2 = new AssegnazioneTask();
        assegnazioneTask2.setId(assegnazioneTask1.getId());
        assertThat(assegnazioneTask1).isEqualTo(assegnazioneTask2);
        assegnazioneTask2.setId(2L);
        assertThat(assegnazioneTask1).isNotEqualTo(assegnazioneTask2);
        assegnazioneTask1.setId(null);
        assertThat(assegnazioneTask1).isNotEqualTo(assegnazioneTask2);
    }
}
