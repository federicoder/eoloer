package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class TemplatePraticaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TemplatePraticaDTO.class);
        TemplatePraticaDTO templatePraticaDTO1 = new TemplatePraticaDTO();
        templatePraticaDTO1.setId(1L);
        TemplatePraticaDTO templatePraticaDTO2 = new TemplatePraticaDTO();
        assertThat(templatePraticaDTO1).isNotEqualTo(templatePraticaDTO2);
        templatePraticaDTO2.setId(templatePraticaDTO1.getId());
        assertThat(templatePraticaDTO1).isEqualTo(templatePraticaDTO2);
        templatePraticaDTO2.setId(2L);
        assertThat(templatePraticaDTO1).isNotEqualTo(templatePraticaDTO2);
        templatePraticaDTO1.setId(null);
        assertThat(templatePraticaDTO1).isNotEqualTo(templatePraticaDTO2);
    }
}
