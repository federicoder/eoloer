package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TelefonoPersonaMapperTest {

    private TelefonoPersonaMapper telefonoPersonaMapper;

    @BeforeEach
    public void setUp() {
        telefonoPersonaMapper = new TelefonoPersonaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(telefonoPersonaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(telefonoPersonaMapper.fromId(null)).isNull();
    }
}
