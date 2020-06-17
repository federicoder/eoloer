package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CondivisionePraticaMapperTest {

    private CondivisionePraticaMapper condivisionePraticaMapper;

    @BeforeEach
    public void setUp() {
        condivisionePraticaMapper = new CondivisionePraticaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(condivisionePraticaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(condivisionePraticaMapper.fromId(null)).isNull();
    }
}
