package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class TagPersonaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TagPersonaDTO.class);
        TagPersonaDTO tagPersonaDTO1 = new TagPersonaDTO();
        tagPersonaDTO1.setId(1L);
        TagPersonaDTO tagPersonaDTO2 = new TagPersonaDTO();
        assertThat(tagPersonaDTO1).isNotEqualTo(tagPersonaDTO2);
        tagPersonaDTO2.setId(tagPersonaDTO1.getId());
        assertThat(tagPersonaDTO1).isEqualTo(tagPersonaDTO2);
        tagPersonaDTO2.setId(2L);
        assertThat(tagPersonaDTO1).isNotEqualTo(tagPersonaDTO2);
        tagPersonaDTO1.setId(null);
        assertThat(tagPersonaDTO1).isNotEqualTo(tagPersonaDTO2);
    }
}
