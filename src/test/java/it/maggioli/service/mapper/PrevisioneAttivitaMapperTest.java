package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PrevisioneAttivitaMapperTest {

    private PrevisioneAttivitaMapper previsioneAttivitaMapper;

    @BeforeEach
    public void setUp() {
        previsioneAttivitaMapper = new PrevisioneAttivitaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(previsioneAttivitaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(previsioneAttivitaMapper.fromId(null)).isNull();
    }
}
