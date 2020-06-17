package it.maggioli.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserPersonaMapperTest {

    private UserPersonaMapper userPersonaMapper;

    @BeforeEach
    public void setUp() {
        userPersonaMapper = new UserPersonaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(userPersonaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(userPersonaMapper.fromId(null)).isNull();
    }
}
