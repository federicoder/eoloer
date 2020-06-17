package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OrdineMapperTest {

    private OrdineMapper ordineMapper;

    @BeforeEach
    public void setUp() {
        ordineMapper = new OrdineMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ordineMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ordineMapper.fromId(null)).isNull();
    }
}
