package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TemplateTaskMapperTest {

    private TemplateTaskMapper templateTaskMapper;

    @BeforeEach
    public void setUp() {
        templateTaskMapper = new TemplateTaskMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(templateTaskMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(templateTaskMapper.fromId(null)).isNull();
    }
}
