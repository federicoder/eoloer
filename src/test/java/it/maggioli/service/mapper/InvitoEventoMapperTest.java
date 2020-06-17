package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InvitoEventoMapperTest {

    private InvitoEventoMapper invitoEventoMapper;

    @BeforeEach
    public void setUp() {
        invitoEventoMapper = new InvitoEventoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(invitoEventoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(invitoEventoMapper.fromId(null)).isNull();
    }
}
