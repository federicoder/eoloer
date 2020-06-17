package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InvitoPraticaMapperTest {

    private InvitoPraticaMapper invitoPraticaMapper;

    @BeforeEach
    public void setUp() {
        invitoPraticaMapper = new InvitoPraticaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(invitoPraticaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(invitoPraticaMapper.fromId(null)).isNull();
    }
}
