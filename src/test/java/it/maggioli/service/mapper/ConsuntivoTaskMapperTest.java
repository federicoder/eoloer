package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ConsuntivoTaskMapperTest {

    private ConsuntivoTaskMapper consuntivoTaskMapper;

    @BeforeEach
    public void setUp() {
        consuntivoTaskMapper = new ConsuntivoTaskMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(consuntivoTaskMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(consuntivoTaskMapper.fromId(null)).isNull();
    }
}
