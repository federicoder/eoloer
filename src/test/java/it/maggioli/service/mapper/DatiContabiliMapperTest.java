package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DatiContabiliMapperTest {

    private DatiContabiliMapper datiContabiliMapper;

    @BeforeEach
    public void setUp() {
        datiContabiliMapper = new DatiContabiliMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(datiContabiliMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(datiContabiliMapper.fromId(null)).isNull();
    }
}
