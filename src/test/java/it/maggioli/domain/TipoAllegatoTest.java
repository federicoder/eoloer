package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class TipoAllegatoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoAllegato.class);
        TipoAllegato tipoAllegato1 = new TipoAllegato();
        tipoAllegato1.setId(1L);
        TipoAllegato tipoAllegato2 = new TipoAllegato();
        tipoAllegato2.setId(tipoAllegato1.getId());
        assertThat(tipoAllegato1).isEqualTo(tipoAllegato2);
        tipoAllegato2.setId(2L);
        assertThat(tipoAllegato1).isNotEqualTo(tipoAllegato2);
        tipoAllegato1.setId(null);
        assertThat(tipoAllegato1).isNotEqualTo(tipoAllegato2);
    }
}
