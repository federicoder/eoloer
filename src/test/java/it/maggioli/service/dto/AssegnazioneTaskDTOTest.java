package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class AssegnazioneTaskDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AssegnazioneTaskDTO.class);
        AssegnazioneTaskDTO assegnazioneTaskDTO1 = new AssegnazioneTaskDTO();
        assegnazioneTaskDTO1.setId(1L);
        AssegnazioneTaskDTO assegnazioneTaskDTO2 = new AssegnazioneTaskDTO();
        assertThat(assegnazioneTaskDTO1).isNotEqualTo(assegnazioneTaskDTO2);
        assegnazioneTaskDTO2.setId(assegnazioneTaskDTO1.getId());
        assertThat(assegnazioneTaskDTO1).isEqualTo(assegnazioneTaskDTO2);
        assegnazioneTaskDTO2.setId(2L);
        assertThat(assegnazioneTaskDTO1).isNotEqualTo(assegnazioneTaskDTO2);
        assegnazioneTaskDTO1.setId(null);
        assertThat(assegnazioneTaskDTO1).isNotEqualTo(assegnazioneTaskDTO2);
    }
}
