package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProdottoMapperTest {

    private ProdottoMapper prodottoMapper;

    @BeforeEach
    public void setUp() {
        prodottoMapper = new ProdottoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(prodottoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(prodottoMapper.fromId(null)).isNull();
    }
}
