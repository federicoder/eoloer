package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PrevisioneTaskMapperTest {

    private PrevisioneTaskMapper previsioneTaskMapper;

    @BeforeEach
    public void setUp() {
        previsioneTaskMapper = new PrevisioneTaskMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(previsioneTaskMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(previsioneTaskMapper.fromId(null)).isNull();
    }
}
