package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NotaPraticaMapperTest {

    private NotaPraticaMapper notaPraticaMapper;

    @BeforeEach
    public void setUp() {
        notaPraticaMapper = new NotaPraticaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(notaPraticaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(notaPraticaMapper.fromId(null)).isNull();
    }
}
