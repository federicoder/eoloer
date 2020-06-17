package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class RisorseDisponibiliTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RisorseDisponibili.class);
        RisorseDisponibili risorseDisponibili1 = new RisorseDisponibili();
        risorseDisponibili1.setId(1L);
        RisorseDisponibili risorseDisponibili2 = new RisorseDisponibili();
        risorseDisponibili2.setId(risorseDisponibili1.getId());
        assertThat(risorseDisponibili1).isEqualTo(risorseDisponibili2);
        risorseDisponibili2.setId(2L);
        assertThat(risorseDisponibili1).isNotEqualTo(risorseDisponibili2);
        risorseDisponibili1.setId(null);
        assertThat(risorseDisponibili1).isNotEqualTo(risorseDisponibili2);
    }
}
