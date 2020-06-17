package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class IndirizzoPersonaMapperTest {

    private IndirizzoPersonaMapper indirizzoPersonaMapper;

    @BeforeEach
    public void setUp() {
        indirizzoPersonaMapper = new IndirizzoPersonaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(indirizzoPersonaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(indirizzoPersonaMapper.fromId(null)).isNull();
    }
}
