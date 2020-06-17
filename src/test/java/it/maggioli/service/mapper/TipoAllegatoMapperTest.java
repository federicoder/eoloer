package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TipoAllegatoMapperTest {

    private TipoAllegatoMapper tipoAllegatoMapper;

    @BeforeEach
    public void setUp() {
        tipoAllegatoMapper = new TipoAllegatoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tipoAllegatoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tipoAllegatoMapper.fromId(null)).isNull();
    }
}
