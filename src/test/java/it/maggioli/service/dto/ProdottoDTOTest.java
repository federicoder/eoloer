package it.maggioli.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class ProdottoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProdottoDTO.class);
        ProdottoDTO prodottoDTO1 = new ProdottoDTO();
        prodottoDTO1.setId(1L);
        ProdottoDTO prodottoDTO2 = new ProdottoDTO();
        assertThat(prodottoDTO1).isNotEqualTo(prodottoDTO2);
        prodottoDTO2.setId(prodottoDTO1.getId());
        assertThat(prodottoDTO1).isEqualTo(prodottoDTO2);
        prodottoDTO2.setId(2L);
        assertThat(prodottoDTO1).isNotEqualTo(prodottoDTO2);
        prodottoDTO1.setId(null);
        assertThat(prodottoDTO1).isNotEqualTo(prodottoDTO2);
    }
}
