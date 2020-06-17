package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class NotePersonaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotePersonaDTO.class);
        NotePersonaDTO notePersonaDTO1 = new NotePersonaDTO();
        notePersonaDTO1.setId(1L);
        NotePersonaDTO notePersonaDTO2 = new NotePersonaDTO();
        assertThat(notePersonaDTO1).isNotEqualTo(notePersonaDTO2);
        notePersonaDTO2.setId(notePersonaDTO1.getId());
        assertThat(notePersonaDTO1).isEqualTo(notePersonaDTO2);
        notePersonaDTO2.setId(2L);
        assertThat(notePersonaDTO1).isNotEqualTo(notePersonaDTO2);
        notePersonaDTO1.setId(null);
        assertThat(notePersonaDTO1).isNotEqualTo(notePersonaDTO2);
    }
}
