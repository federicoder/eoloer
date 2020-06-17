package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class RuoloOrganizzazioneTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RuoloOrganizzazione.class);
        RuoloOrganizzazione ruoloOrganizzazione1 = new RuoloOrganizzazione();
        ruoloOrganizzazione1.setId(1L);
        RuoloOrganizzazione ruoloOrganizzazione2 = new RuoloOrganizzazione();
        ruoloOrganizzazione2.setId(ruoloOrganizzazione1.getId());
        assertThat(ruoloOrganizzazione1).isEqualTo(ruoloOrganizzazione2);
        ruoloOrganizzazione2.setId(2L);
        assertThat(ruoloOrganizzazione1).isNotEqualTo(ruoloOrganizzazione2);
        ruoloOrganizzazione1.setId(null);
        assertThat(ruoloOrganizzazione1).isNotEqualTo(ruoloOrganizzazione2);
    }
}
