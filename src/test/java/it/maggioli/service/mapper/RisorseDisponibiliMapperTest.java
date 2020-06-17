package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RisorseDisponibiliMapperTest {

    private RisorseDisponibiliMapper risorseDisponibiliMapper;

    @BeforeEach
    public void setUp() {
        risorseDisponibiliMapper = new RisorseDisponibiliMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(risorseDisponibiliMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(risorseDisponibiliMapper.fromId(null)).isNull();
    }
}
