package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AllegatoTaskMapperTest {

    private AllegatoTaskMapper allegatoTaskMapper;

    @BeforeEach
    public void setUp() {
        allegatoTaskMapper = new AllegatoTaskMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(allegatoTaskMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(allegatoTaskMapper.fromId(null)).isNull();
    }
}
