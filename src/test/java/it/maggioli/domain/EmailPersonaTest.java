package it.maggioli.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.maggioli.web.rest.TestUtil;

public class EmailPersonaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmailPersona.class);
        EmailPersona emailPersona1 = new EmailPersona();
        emailPersona1.setId(1L);
        EmailPersona emailPersona2 = new EmailPersona();
        emailPersona2.setId(emailPersona1.getId());
        assertThat(emailPersona1).isEqualTo(emailPersona2);
        emailPersona2.setId(2L);
        assertThat(emailPersona1).isNotEqualTo(emailPersona2);
        emailPersona1.setId(null);
        assertThat(emailPersona1).isNotEqualTo(emailPersona2);
    }
}
