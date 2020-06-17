package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class PersonaFisicaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonaFisicaDTO.class);
        PersonaFisicaDTO personaFisicaDTO1 = new PersonaFisicaDTO();
        personaFisicaDTO1.setId(1L);
        PersonaFisicaDTO personaFisicaDTO2 = new PersonaFisicaDTO();
        assertThat(personaFisicaDTO1).isNotEqualTo(personaFisicaDTO2);
        personaFisicaDTO2.setId(personaFisicaDTO1.getId());
        assertThat(personaFisicaDTO1).isEqualTo(personaFisicaDTO2);
        personaFisicaDTO2.setId(2L);
        assertThat(personaFisicaDTO1).isNotEqualTo(personaFisicaDTO2);
        personaFisicaDTO1.setId(null);
        assertThat(personaFisicaDTO1).isNotEqualTo(personaFisicaDTO2);
    }
}
