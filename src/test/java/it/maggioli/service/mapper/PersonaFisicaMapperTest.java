package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonaFisicaMapperTest {

    private PersonaFisicaMapper personaFisicaMapper;

    @BeforeEach
    public void setUp() {
        personaFisicaMapper = new PersonaFisicaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(personaFisicaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(personaFisicaMapper.fromId(null)).isNull();
    }
}
