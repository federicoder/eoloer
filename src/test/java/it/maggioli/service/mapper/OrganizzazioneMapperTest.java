package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OrganizzazioneMapperTest {

    private OrganizzazioneMapper organizzazioneMapper;

    @BeforeEach
    public void setUp() {
        organizzazioneMapper = new OrganizzazioneMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(organizzazioneMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(organizzazioneMapper.fromId(null)).isNull();
    }
}
