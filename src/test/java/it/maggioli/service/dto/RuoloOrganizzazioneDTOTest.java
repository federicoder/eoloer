package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class RuoloOrganizzazioneDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RuoloOrganizzazioneDTO.class);
        RuoloOrganizzazioneDTO ruoloOrganizzazioneDTO1 = new RuoloOrganizzazioneDTO();
        ruoloOrganizzazioneDTO1.setId(1L);
        RuoloOrganizzazioneDTO ruoloOrganizzazioneDTO2 = new RuoloOrganizzazioneDTO();
        assertThat(ruoloOrganizzazioneDTO1).isNotEqualTo(ruoloOrganizzazioneDTO2);
        ruoloOrganizzazioneDTO2.setId(ruoloOrganizzazioneDTO1.getId());
        assertThat(ruoloOrganizzazioneDTO1).isEqualTo(ruoloOrganizzazioneDTO2);
        ruoloOrganizzazioneDTO2.setId(2L);
        assertThat(ruoloOrganizzazioneDTO1).isNotEqualTo(ruoloOrganizzazioneDTO2);
        ruoloOrganizzazioneDTO1.setId(null);
        assertThat(ruoloOrganizzazioneDTO1).isNotEqualTo(ruoloOrganizzazioneDTO2);
    }
}
