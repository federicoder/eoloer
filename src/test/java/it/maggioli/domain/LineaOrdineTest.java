package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class LineaOrdineTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LineaOrdine.class);
        LineaOrdine lineaOrdine1 = new LineaOrdine();
        lineaOrdine1.setId(1L);
        LineaOrdine lineaOrdine2 = new LineaOrdine();
        lineaOrdine2.setId(lineaOrdine1.getId());
        assertThat(lineaOrdine1).isEqualTo(lineaOrdine2);
        lineaOrdine2.setId(2L);
        assertThat(lineaOrdine1).isNotEqualTo(lineaOrdine2);
        lineaOrdine1.setId(null);
        assertThat(lineaOrdine1).isNotEqualTo(lineaOrdine2);
    }
}
