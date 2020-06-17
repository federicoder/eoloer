package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class UserPersonaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserPersona.class);
        UserPersona userPersona1 = new UserPersona();
        userPersona1.setId(1L);
        UserPersona userPersona2 = new UserPersona();
        userPersona2.setId(userPersona1.getId());
        assertThat(userPersona1).isEqualTo(userPersona2);
        userPersona2.setId(2L);
        assertThat(userPersona1).isNotEqualTo(userPersona2);
        userPersona1.setId(null);
        assertThat(userPersona1).isNotEqualTo(userPersona2);
    }
}
