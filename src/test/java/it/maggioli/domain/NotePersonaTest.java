package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class NotePersonaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotePersona.class);
        NotePersona notePersona1 = new NotePersona();
        notePersona1.setId(1L);
        NotePersona notePersona2 = new NotePersona();
        notePersona2.setId(notePersona1.getId());
        assertThat(notePersona1).isEqualTo(notePersona2);
        notePersona2.setId(2L);
        assertThat(notePersona1).isNotEqualTo(notePersona2);
        notePersona1.setId(null);
        assertThat(notePersona1).isNotEqualTo(notePersona2);
    }
}
