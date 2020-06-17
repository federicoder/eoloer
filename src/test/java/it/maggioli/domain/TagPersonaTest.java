package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class TagPersonaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagPersona.class);
        TagPersona tagPersona1 = new TagPersona();
        tagPersona1.setId(1L);
        TagPersona tagPersona2 = new TagPersona();
        tagPersona2.setId(tagPersona1.getId());
        assertThat(tagPersona1).isEqualTo(tagPersona2);
        tagPersona2.setId(2L);
        assertThat(tagPersona1).isNotEqualTo(tagPersona2);
        tagPersona1.setId(null);
        assertThat(tagPersona1).isNotEqualTo(tagPersona2);
    }
}
