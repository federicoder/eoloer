package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PrevisioneEventoMapperTest {

    private PrevisioneEventoMapper previsioneEventoMapper;

    @BeforeEach
    public void setUp() {
        previsioneEventoMapper = new PrevisioneEventoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(previsioneEventoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(previsioneEventoMapper.fromId(null)).isNull();
    }
}
