package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class OrganizzazioneDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrganizzazioneDTO.class);
        OrganizzazioneDTO organizzazioneDTO1 = new OrganizzazioneDTO();
        organizzazioneDTO1.setId(1L);
        OrganizzazioneDTO organizzazioneDTO2 = new OrganizzazioneDTO();
        assertThat(organizzazioneDTO1).isNotEqualTo(organizzazioneDTO2);
        organizzazioneDTO2.setId(organizzazioneDTO1.getId());
        assertThat(organizzazioneDTO1).isEqualTo(organizzazioneDTO2);
        organizzazioneDTO2.setId(2L);
        assertThat(organizzazioneDTO1).isNotEqualTo(organizzazioneDTO2);
        organizzazioneDTO1.setId(null);
        assertThat(organizzazioneDTO1).isNotEqualTo(organizzazioneDTO2);
    }
}
