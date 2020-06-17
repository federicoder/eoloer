package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EmailPersonaMapperTest {

    private EmailPersonaMapper emailPersonaMapper;

    @BeforeEach
    public void setUp() {
        emailPersonaMapper = new EmailPersonaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(emailPersonaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(emailPersonaMapper.fromId(null)).isNull();
    }
}
