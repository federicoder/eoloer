package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TagPersonaMapperTest {

    private TagPersonaMapper tagPersonaMapper;

    @BeforeEach
    public void setUp() {
        tagPersonaMapper = new TagPersonaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tagPersonaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tagPersonaMapper.fromId(null)).isNull();
    }
}
