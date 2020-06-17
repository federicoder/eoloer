package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InvitatoMapperTest {

    private InvitatoMapper invitatoMapper;

    @BeforeEach
    public void setUp() {
        invitatoMapper = new InvitatoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(invitatoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(invitatoMapper.fromId(null)).isNull();
    }
}
