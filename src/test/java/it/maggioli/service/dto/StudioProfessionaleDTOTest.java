package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class StudioProfessionaleDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudioProfessionaleDTO.class);
        StudioProfessionaleDTO studioProfessionaleDTO1 = new StudioProfessionaleDTO();
        studioProfessionaleDTO1.setId(1L);
        StudioProfessionaleDTO studioProfessionaleDTO2 = new StudioProfessionaleDTO();
        assertThat(studioProfessionaleDTO1).isNotEqualTo(studioProfessionaleDTO2);
        studioProfessionaleDTO2.setId(studioProfessionaleDTO1.getId());
        assertThat(studioProfessionaleDTO1).isEqualTo(studioProfessionaleDTO2);
        studioProfessionaleDTO2.setId(2L);
        assertThat(studioProfessionaleDTO1).isNotEqualTo(studioProfessionaleDTO2);
        studioProfessionaleDTO1.setId(null);
        assertThat(studioProfessionaleDTO1).isNotEqualTo(studioProfessionaleDTO2);
    }
}
