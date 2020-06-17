package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NotaTaskMapperTest {

    private NotaTaskMapper notaTaskMapper;

    @BeforeEach
    public void setUp() {
        notaTaskMapper = new NotaTaskMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(notaTaskMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(notaTaskMapper.fromId(null)).isNull();
    }
}
