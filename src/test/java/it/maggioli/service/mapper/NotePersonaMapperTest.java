package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NotePersonaMapperTest {

    private NotePersonaMapper notePersonaMapper;

    @BeforeEach
    public void setUp() {
        notePersonaMapper = new NotePersonaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(notePersonaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(notePersonaMapper.fromId(null)).isNull();
    }
}
