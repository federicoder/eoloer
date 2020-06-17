package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RuoloOrganizzazioneMapperTest {

    private RuoloOrganizzazioneMapper ruoloOrganizzazioneMapper;

    @BeforeEach
    public void setUp() {
        ruoloOrganizzazioneMapper = new RuoloOrganizzazioneMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ruoloOrganizzazioneMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ruoloOrganizzazioneMapper.fromId(null)).isNull();
    }
}
