package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.TelefonoPersona;
import it.maggioli.repository.TelefonoPersonaRepository;
import it.maggioli.repository.search.TelefonoPersonaSearchRepository;
import it.maggioli.service.TelefonoPersonaService;
import it.maggioli.service.dto.TelefonoPersonaDTO;
import it.maggioli.service.mapper.TelefonoPersonaMapper;

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
 * Integration tests for the {@link TelefonoPersonaResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class TelefonoPersonaResourceIT {

    private static final Long DEFAULT_ID_PERSONA_REF = 1L;
    private static final Long UPDATED_ID_PERSONA_REF = 2L;

    private static final Long DEFAULT_ETICHETTA = 1L;
    private static final Long UPDATED_ETICHETTA = 2L;

    private static final Long DEFAULT_VALORE = 1L;
    private static final Long UPDATED_VALORE = 2L;

    @Autowired
    private TelefonoPersonaRepository telefonoPersonaRepository;

    @Autowired
    private TelefonoPersonaMapper telefonoPersonaMapper;

    @Autowired
    private TelefonoPersonaService telefonoPersonaService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.TelefonoPersonaSearchRepositoryMockConfiguration
     */
    @Autowired
    private TelefonoPersonaSearchRepository mockTelefonoPersonaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTelefonoPersonaMockMvc;

    private TelefonoPersona telefonoPersona;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TelefonoPersona createEntity(EntityManager em) {
        TelefonoPersona telefonoPersona = new TelefonoPersona()
            .idPersonaRef(DEFAULT_ID_PERSONA_REF)
            .etichetta(DEFAULT_ETICHETTA)
            .valore(DEFAULT_VALORE);
        return telefonoPersona;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TelefonoPersona createUpdatedEntity(EntityManager em) {
        TelefonoPersona telefonoPersona = new TelefonoPersona()
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .etichetta(UPDATED_ETICHETTA)
            .valore(UPDATED_VALORE);
        return telefonoPersona;
    }

    @BeforeEach
    public void initTest() {
        telefonoPersona = createEntity(em);
    }

    @Test
    @Transactional
    public void createTelefonoPersona() throws Exception {
        int databaseSizeBeforeCreate = telefonoPersonaRepository.findAll().size();
        // Create the TelefonoPersona
        TelefonoPersonaDTO telefonoPersonaDTO = telefonoPersonaMapper.toDto(telefonoPersona);
        restTelefonoPersonaMockMvc.perform(post("/api/telefono-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(telefonoPersonaDTO)))
            .andExpect(status().isCreated());

        // Validate the TelefonoPersona in the database
        List<TelefonoPersona> telefonoPersonaList = telefonoPersonaRepository.findAll();
        assertThat(telefonoPersonaList).hasSize(databaseSizeBeforeCreate + 1);
        TelefonoPersona testTelefonoPersona = telefonoPersonaList.get(telefonoPersonaList.size() - 1);
        assertThat(testTelefonoPersona.getIdPersonaRef()).isEqualTo(DEFAULT_ID_PERSONA_REF);
        assertThat(testTelefonoPersona.getEtichetta()).isEqualTo(DEFAULT_ETICHETTA);
        assertThat(testTelefonoPersona.getValore()).isEqualTo(DEFAULT_VALORE);

        // Validate the TelefonoPersona in Elasticsearch
        verify(mockTelefonoPersonaSearchRepository, times(1)).save(testTelefonoPersona);
    }

    @Test
    @Transactional
    public void createTelefonoPersonaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = telefonoPersonaRepository.findAll().size();

        // Create the TelefonoPersona with an existing ID
        telefonoPersona.setId(1L);
        TelefonoPersonaDTO telefonoPersonaDTO = telefonoPersonaMapper.toDto(telefonoPersona);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTelefonoPersonaMockMvc.perform(post("/api/telefono-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(telefonoPersonaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TelefonoPersona in the database
        List<TelefonoPersona> telefonoPersonaList = telefonoPersonaRepository.findAll();
        assertThat(telefonoPersonaList).hasSize(databaseSizeBeforeCreate);

        // Validate the TelefonoPersona in Elasticsearch
        verify(mockTelefonoPersonaSearchRepository, times(0)).save(telefonoPersona);
    }


    @Test
    @Transactional
    public void checkIdPersonaRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = telefonoPersonaRepository.findAll().size();
        // set the field null
        telefonoPersona.setIdPersonaRef(null);

        // Create the TelefonoPersona, which fails.
        TelefonoPersonaDTO telefonoPersonaDTO = telefonoPersonaMapper.toDto(telefonoPersona);


        restTelefonoPersonaMockMvc.perform(post("/api/telefono-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(telefonoPersonaDTO)))
            .andExpect(status().isBadRequest());

        List<TelefonoPersona> telefonoPersonaList = telefonoPersonaRepository.findAll();
        assertThat(telefonoPersonaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTelefonoPersonas() throws Exception {
        // Initialize the database
        telefonoPersonaRepository.saveAndFlush(telefonoPersona);

        // Get all the telefonoPersonaList
        restTelefonoPersonaMockMvc.perform(get("/api/telefono-personas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(telefonoPersona.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF.intValue())))
            .andExpect(jsonPath("$.[*].etichetta").value(hasItem(DEFAULT_ETICHETTA.intValue())))
            .andExpect(jsonPath("$.[*].valore").value(hasItem(DEFAULT_VALORE.intValue())));
    }
    
    @Test
    @Transactional
    public void getTelefonoPersona() throws Exception {
        // Initialize the database
        telefonoPersonaRepository.saveAndFlush(telefonoPersona);

        // Get the telefonoPersona
        restTelefonoPersonaMockMvc.perform(get("/api/telefono-personas/{id}", telefonoPersona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(telefonoPersona.getId().intValue()))
            .andExpect(jsonPath("$.idPersonaRef").value(DEFAULT_ID_PERSONA_REF.intValue()))
            .andExpect(jsonPath("$.etichetta").value(DEFAULT_ETICHETTA.intValue()))
            .andExpect(jsonPath("$.valore").value(DEFAULT_VALORE.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTelefonoPersona() throws Exception {
        // Get the telefonoPersona
        restTelefonoPersonaMockMvc.perform(get("/api/telefono-personas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTelefonoPersona() throws Exception {
        // Initialize the database
        telefonoPersonaRepository.saveAndFlush(telefonoPersona);

        int databaseSizeBeforeUpdate = telefonoPersonaRepository.findAll().size();

        // Update the telefonoPersona
        TelefonoPersona updatedTelefonoPersona = telefonoPersonaRepository.findById(telefonoPersona.getId()).get();
        // Disconnect from session so that the updates on updatedTelefonoPersona are not directly saved in db
        em.detach(updatedTelefonoPersona);
        updatedTelefonoPersona
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .etichetta(UPDATED_ETICHETTA)
            .valore(UPDATED_VALORE);
        TelefonoPersonaDTO telefonoPersonaDTO = telefonoPersonaMapper.toDto(updatedTelefonoPersona);

        restTelefonoPersonaMockMvc.perform(put("/api/telefono-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(telefonoPersonaDTO)))
            .andExpect(status().isOk());

        // Validate the TelefonoPersona in the database
        List<TelefonoPersona> telefonoPersonaList = telefonoPersonaRepository.findAll();
        assertThat(telefonoPersonaList).hasSize(databaseSizeBeforeUpdate);
        TelefonoPersona testTelefonoPersona = telefonoPersonaList.get(telefonoPersonaList.size() - 1);
        assertThat(testTelefonoPersona.getIdPersonaRef()).isEqualTo(UPDATED_ID_PERSONA_REF);
        assertThat(testTelefonoPersona.getEtichetta()).isEqualTo(UPDATED_ETICHETTA);
        assertThat(testTelefonoPersona.getValore()).isEqualTo(UPDATED_VALORE);

        // Validate the TelefonoPersona in Elasticsearch
        verify(mockTelefonoPersonaSearchRepository, times(1)).save(testTelefonoPersona);
    }

    @Test
    @Transactional
    public void updateNonExistingTelefonoPersona() throws Exception {
        int databaseSizeBeforeUpdate = telefonoPersonaRepository.findAll().size();

        // Create the TelefonoPersona
        TelefonoPersonaDTO telefonoPersonaDTO = telefonoPersonaMapper.toDto(telefonoPersona);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTelefonoPersonaMockMvc.perform(put("/api/telefono-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(telefonoPersonaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TelefonoPersona in the database
        List<TelefonoPersona> telefonoPersonaList = telefonoPersonaRepository.findAll();
        assertThat(telefonoPersonaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TelefonoPersona in Elasticsearch
        verify(mockTelefonoPersonaSearchRepository, times(0)).save(telefonoPersona);
    }

    @Test
    @Transactional
    public void deleteTelefonoPersona() throws Exception {
        // Initialize the database
        telefonoPersonaRepository.saveAndFlush(telefonoPersona);

        int databaseSizeBeforeDelete = telefonoPersonaRepository.findAll().size();

        // Delete the telefonoPersona
        restTelefonoPersonaMockMvc.perform(delete("/api/telefono-personas/{id}", telefonoPersona.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TelefonoPersona> telefonoPersonaList = telefonoPersonaRepository.findAll();
        assertThat(telefonoPersonaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TelefonoPersona in Elasticsearch
        verify(mockTelefonoPersonaSearchRepository, times(1)).deleteById(telefonoPersona.getId());
    }

    @Test
    @Transactional
    public void searchTelefonoPersona() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        telefonoPersonaRepository.saveAndFlush(telefonoPersona);
        when(mockTelefonoPersonaSearchRepository.search(queryStringQuery("id:" + telefonoPersona.getId())))
            .thenReturn(Collections.singletonList(telefonoPersona));

        // Search the telefonoPersona
        restTelefonoPersonaMockMvc.perform(get("/api/_search/telefono-personas?query=id:" + telefonoPersona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(telefonoPersona.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF.intValue())))
            .andExpect(jsonPath("$.[*].etichetta").value(hasItem(DEFAULT_ETICHETTA.intValue())))
            .andExpect(jsonPath("$.[*].valore").value(hasItem(DEFAULT_VALORE.intValue())));
    }
}
