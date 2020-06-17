package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AssegnazioneTaskMapperTest {

    private AssegnazioneTaskMapper assegnazioneTaskMapper;

    @BeforeEach
    public void setUp() {
        assegnazioneTaskMapper = new AssegnazioneTaskMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(assegnazioneTaskMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(assegnazioneTaskMapper.fromId(null)).isNull();
    }
}
