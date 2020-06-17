package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TemplatePraticaMapperTest {

    private TemplatePraticaMapper templatePraticaMapper;

    @BeforeEach
    public void setUp() {
        templatePraticaMapper = new TemplatePraticaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(templatePraticaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(templatePraticaMapper.fromId(null)).isNull();
    }
}
