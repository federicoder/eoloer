package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StudioProfessionaleMapperTest {

    private StudioProfessionaleMapper studioProfessionaleMapper;

    @BeforeEach
    public void setUp() {
        studioProfessionaleMapper = new StudioProfessionaleMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(studioProfessionaleMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(studioProfessionaleMapper.fromId(null)).isNull();
    }
}
