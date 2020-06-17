package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LineaOrdineMapperTest {

    private LineaOrdineMapper lineaOrdineMapper;

    @BeforeEach
    public void setUp() {
        lineaOrdineMapper = new LineaOrdineMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(lineaOrdineMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(lineaOrdineMapper.fromId(null)).isNull();
    }
}
