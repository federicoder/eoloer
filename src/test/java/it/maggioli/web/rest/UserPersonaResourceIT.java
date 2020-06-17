package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.UserPersona;
import it.maggioli.repository.UserPersonaRepository;
import it.maggioli.repository.search.UserPersonaSearchRepository;
import it.maggioli.service.UserPersonaService;
import it.maggioli.service.dto.UserPersonaDTO;
import it.maggioli.service.mapper.UserPersonaMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UserPersonaResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class UserPersonaResourceIT {

    private static final Long DEFAULT_ID_PERSONA_REF = 1L;
    private static final Long UPDATED_ID_PERSONA_REF = 2L;

    private static final Long DEFAULT_NOME_USER = 1L;
    private static final Long UPDATED_NOME_USER = 2L;

    @Autowired
    private UserPersonaRepository userPersonaRepository;

    @Autowired
    private UserPersonaMapper userPersonaMapper;

    @Autowired
    private UserPersonaService userPersonaService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.UserPersonaSearchRepositoryMockConfiguration
     */
    @Autowired
    private UserPersonaSearchRepository mockUserPersonaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserPersonaMockMvc;

    private UserPersona userPersona;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserPersona createEntity(EntityManager em) {
        UserPersona userPersona = new UserPersona()
            .idPersonaRef(DEFAULT_ID_PERSONA_REF)
            .nomeUser(DEFAULT_NOME_USER);
        return userPersona;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserPersona createUpdatedEntity(EntityManager em) {
        UserPersona userPersona = new UserPersona()
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .nomeUser(UPDATED_NOME_USER);
        return userPersona;
    }

    @BeforeEach
    public void initTest() {
        userPersona = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserPersona() throws Exception {
        int databaseSizeBeforeCreate = userPersonaRepository.findAll().size();
        // Create the UserPersona
        UserPersonaDTO userPersonaDTO = userPersonaMapper.toDto(userPersona);
        restUserPersonaMockMvc.perform(post("/api/user-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userPersonaDTO)))
            .andExpect(status().isCreated());

        // Validate the UserPersona in the database
        List<UserPersona> userPersonaList = userPersonaRepository.findAll();
        assertThat(userPersonaList).hasSize(databaseSizeBeforeCreate + 1);
        UserPersona testUserPersona = userPersonaList.get(userPersonaList.size() - 1);
        assertThat(testUserPersona.getIdPersonaRef()).isEqualTo(DEFAULT_ID_PERSONA_REF);
        assertThat(testUserPersona.getNomeUser()).isEqualTo(DEFAULT_NOME_USER);

        // Validate the UserPersona in Elasticsearch
        verify(mockUserPersonaSearchRepository, times(1)).save(testUserPersona);
    }

    @Test
    @Transactional
    public void createUserPersonaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userPersonaRepository.findAll().size();

        // Create the UserPersona with an existing ID
        userPersona.setId(1L);
        UserPersonaDTO userPersonaDTO = userPersonaMapper.toDto(userPersona);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserPersonaMockMvc.perform(post("/api/user-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userPersonaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserPersona in the database
        List<UserPersona> userPersonaList = userPersonaRepository.findAll();
        assertThat(userPersonaList).hasSize(databaseSizeBeforeCreate);

        // Validate the UserPersona in Elasticsearch
        verify(mockUserPersonaSearchRepository, times(0)).save(userPersona);
    }


    @Test
    @Transactional
    public void getAllUserPersonas() throws Exception {
        // Initialize the database
        userPersonaRepository.saveAndFlush(userPersona);

        // Get all the userPersonaList
        restUserPersonaMockMvc.perform(get("/api/user-personas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userPersona.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF.intValue())))
            .andExpect(jsonPath("$.[*].nomeUser").value(hasItem(DEFAULT_NOME_USER.intValue())));
    }
    
    @Test
    @Transactional
    public void getUserPersona() throws Exception {
        // Initialize the database
        userPersonaRepository.saveAndFlush(userPersona);

        // Get the userPersona
        restUserPersonaMockMvc.perform(get("/api/user-personas/{id}", userPersona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userPersona.getId().intValue()))
            .andExpect(jsonPath("$.idPersonaRef").value(DEFAULT_ID_PERSONA_REF.intValue()))
            .andExpect(jsonPath("$.nomeUser").value(DEFAULT_NOME_USER.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingUserPersona() throws Exception {
        // Get the userPersona
        restUserPersonaMockMvc.perform(get("/api/user-personas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserPersona() throws Exception {
        // Initialize the database
        userPersonaRepository.saveAndFlush(userPersona);

        int databaseSizeBeforeUpdate = userPersonaRepository.findAll().size();

        // Update the userPersona
        UserPersona updatedUserPersona = userPersonaRepository.findById(userPersona.getId()).get();
        // Disconnect from session so that the updates on updatedUserPersona are not directly saved in db
        em.detach(updatedUserPersona);
        updatedUserPersona
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .nomeUser(UPDATED_NOME_USER);
        UserPersonaDTO userPersonaDTO = userPersonaMapper.toDto(updatedUserPersona);

        restUserPersonaMockMvc.perform(put("/api/user-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userPersonaDTO)))
            .andExpect(status().isOk());

        // Validate the UserPersona in the database
        List<UserPersona> userPersonaList = userPersonaRepository.findAll();
        assertThat(userPersonaList).hasSize(databaseSizeBeforeUpdate);
        UserPersona testUserPersona = userPersonaList.get(userPersonaList.size() - 1);
        assertThat(testUserPersona.getIdPersonaRef()).isEqualTo(UPDATED_ID_PERSONA_REF);
        assertThat(testUserPersona.getNomeUser()).isEqualTo(UPDATED_NOME_USER);

        // Validate the UserPersona in Elasticsearch
        verify(mockUserPersonaSearchRepository, times(1)).save(testUserPersona);
    }

    @Test
    @Transactional
    public void updateNonExistingUserPersona() throws Exception {
        int databaseSizeBeforeUpdate = userPersonaRepository.findAll().size();

        // Create the UserPersona
        UserPersonaDTO userPersonaDTO = userPersonaMapper.toDto(userPersona);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserPersonaMockMvc.perform(put("/api/user-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userPersonaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserPersona in the database
        List<UserPersona> userPersonaList = userPersonaRepository.findAll();
        assertThat(userPersonaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the UserPersona in Elasticsearch
        verify(mockUserPersonaSearchRepository, times(0)).save(userPersona);
    }

    @Test
    @Transactional
    public void deleteUserPersona() throws Exception {
        // Initialize the database
        userPersonaRepository.saveAndFlush(userPersona);

        int databaseSizeBeforeDelete = userPersonaRepository.findAll().size();

        // Delete the userPersona
        restUserPersonaMockMvc.perform(delete("/api/user-personas/{id}", userPersona.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserPersona> userPersonaList = userPersonaRepository.findAll();
        assertThat(userPersonaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the UserPersona in Elasticsearch
        verify(mockUserPersonaSearchRepository, times(1)).deleteById(userPersona.getId());
    }

    @Test
    @Transactional
    public void searchUserPersona() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        userPersonaRepository.saveAndFlush(userPersona);
        when(mockUserPersonaSearchRepository.search(queryStringQuery("id:" + userPersona.getId())))
            .thenReturn(Collections.singletonList(userPersona));

        // Search the userPersona
        restUserPersonaMockMvc.perform(get("/api/_search/user-personas?query=id:" + userPersona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userPersona.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF.intValue())))
            .andExpect(jsonPath("$.[*].nomeUser").value(hasItem(DEFAULT_NOME_USER.intValue())));
    }
}
