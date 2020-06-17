package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RappresentanzaPraticaMapperTest {

    private RappresentanzaPraticaMapper rappresentanzaPraticaMapper;

    @BeforeEach
    public void setUp() {
        rappresentanzaPraticaMapper = new RappresentanzaPraticaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(rappresentanzaPraticaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(rappresentanzaPraticaMapper.fromId(null)).isNull();
    }
}
