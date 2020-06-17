package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class StudioProfessionaleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudioProfessionale.class);
        StudioProfessionale studioProfessionale1 = new StudioProfessionale();
        studioProfessionale1.setId(1L);
        StudioProfessionale studioProfessionale2 = new StudioProfessionale();
        studioProfessionale2.setId(studioProfessionale1.getId());
        assertThat(studioProfessionale1).isEqualTo(studioProfessionale2);
        studioProfessionale2.setId(2L);
        assertThat(studioProfessionale1).isNotEqualTo(studioProfessionale2);
        studioProfessionale1.setId(null);
        assertThat(studioProfessionale1).isNotEqualTo(studioProfessionale2);
    }
}
