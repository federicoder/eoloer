package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AllegatoTemplateTaskMapperTest {

    private AllegatoTemplateTaskMapper allegatoTemplateTaskMapper;

    @BeforeEach
    public void setUp() {
        allegatoTemplateTaskMapper = new AllegatoTemplateTaskMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(allegatoTemplateTaskMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(allegatoTemplateTaskMapper.fromId(null)).isNull();
    }
}
