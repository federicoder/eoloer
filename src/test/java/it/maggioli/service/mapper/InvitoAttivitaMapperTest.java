package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InvitoAttivitaMapperTest {

    private InvitoAttivitaMapper invitoAttivitaMapper;

    @BeforeEach
    public void setUp() {
        invitoAttivitaMapper = new InvitoAttivitaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(invitoAttivitaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(invitoAttivitaMapper.fromId(null)).isNull();
    }
}
