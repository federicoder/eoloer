package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.RappresentanzaPratica;
import it.maggioli.repository.RappresentanzaPraticaRepository;
import it.maggioli.repository.search.RappresentanzaPraticaSearchRepository;
import it.maggioli.service.RappresentanzaPraticaService;
import it.maggioli.service.dto.RappresentanzaPraticaDTO;
import it.maggioli.service.mapper.RappresentanzaPraticaMapper;

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

import it.maggioli.domain.enumeration.Ruoli;
/**
 * Integration tests for the {@link RappresentanzaPraticaResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class RappresentanzaPraticaResourceIT {

    private static final Long DEFAULT_ID_RUOLO_PERSONA = 1L;
    private static final Long UPDATED_ID_RUOLO_PERSONA = 2L;

    private static final Long DEFAULT_ID_PERSONA_REF = 1L;
    private static final Long UPDATED_ID_PERSONA_REF = 2L;

    private static final Ruoli DEFAULT_RUOLI = Ruoli.TDP;
    private static final Ruoli UPDATED_RUOLI = Ruoli.COLLABORATOREINTERNO;

    @Autowired
    private RappresentanzaPraticaRepository rappresentanzaPraticaRepository;

    @Autowired
    private RappresentanzaPraticaMapper rappresentanzaPraticaMapper;

    @Autowired
    private RappresentanzaPraticaService rappresentanzaPraticaService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.RappresentanzaPraticaSearchRepositoryMockConfiguration
     */
    @Autowired
    private RappresentanzaPraticaSearchRepository mockRappresentanzaPraticaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRappresentanzaPraticaMockMvc;

    private RappresentanzaPratica rappresentanzaPratica;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RappresentanzaPratica createEntity(EntityManager em) {
        RappresentanzaPratica rappresentanzaPratica = new RappresentanzaPratica()
            .idRuoloPersona(DEFAULT_ID_RUOLO_PERSONA)
            .idPersonaRef(DEFAULT_ID_PERSONA_REF)
            .ruoli(DEFAULT_RUOLI);
        return rappresentanzaPratica;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RappresentanzaPratica createUpdatedEntity(EntityManager em) {
        RappresentanzaPratica rappresentanzaPratica = new RappresentanzaPratica()
            .idRuoloPersona(UPDATED_ID_RUOLO_PERSONA)
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .ruoli(UPDATED_RUOLI);
        return rappresentanzaPratica;
    }

    @BeforeEach
    public void initTest() {
        rappresentanzaPratica = createEntity(em);
    }

    @Test
    @Transactional
    public void createRappresentanzaPratica() throws Exception {
        int databaseSizeBeforeCreate = rappresentanzaPraticaRepository.findAll().size();
        // Create the RappresentanzaPratica
        RappresentanzaPraticaDTO rappresentanzaPraticaDTO = rappresentanzaPraticaMapper.toDto(rappresentanzaPratica);
        restRappresentanzaPraticaMockMvc.perform(post("/api/rappresentanza-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rappresentanzaPraticaDTO)))
            .andExpect(status().isCreated());

        // Validate the RappresentanzaPratica in the database
        List<RappresentanzaPratica> rappresentanzaPraticaList = rappresentanzaPraticaRepository.findAll();
        assertThat(rappresentanzaPraticaList).hasSize(databaseSizeBeforeCreate + 1);
        RappresentanzaPratica testRappresentanzaPratica = rappresentanzaPraticaList.get(rappresentanzaPraticaList.size() - 1);
        assertThat(testRappresentanzaPratica.getIdRuoloPersona()).isEqualTo(DEFAULT_ID_RUOLO_PERSONA);
        assertThat(testRappresentanzaPratica.getIdPersonaRef()).isEqualTo(DEFAULT_ID_PERSONA_REF);
        assertThat(testRappresentanzaPratica.getRuoli()).isEqualTo(DEFAULT_RUOLI);

        // Validate the RappresentanzaPratica in Elasticsearch
        verify(mockRappresentanzaPraticaSearchRepository, times(1)).save(testRappresentanzaPratica);
    }

    @Test
    @Transactional
    public void createRappresentanzaPraticaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rappresentanzaPraticaRepository.findAll().size();

        // Create the RappresentanzaPratica with an existing ID
        rappresentanzaPratica.setId(1L);
        RappresentanzaPraticaDTO rappresentanzaPraticaDTO = rappresentanzaPraticaMapper.toDto(rappresentanzaPratica);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRappresentanzaPraticaMockMvc.perform(post("/api/rappresentanza-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rappresentanzaPraticaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RappresentanzaPratica in the database
        List<RappresentanzaPratica> rappresentanzaPraticaList = rappresentanzaPraticaRepository.findAll();
        assertThat(rappresentanzaPraticaList).hasSize(databaseSizeBeforeCreate);

        // Validate the RappresentanzaPratica in Elasticsearch
        verify(mockRappresentanzaPraticaSearchRepository, times(0)).save(rappresentanzaPratica);
    }


    @Test
    @Transactional
    public void checkIdRuoloPersonaIsRequired() throws Exception {
        int databaseSizeBeforeTest = rappresentanzaPraticaRepository.findAll().size();
        // set the field null
        rappresentanzaPratica.setIdRuoloPersona(null);

        // Create the RappresentanzaPratica, which fails.
        RappresentanzaPraticaDTO rappresentanzaPraticaDTO = rappresentanzaPraticaMapper.toDto(rappresentanzaPratica);


        restRappresentanzaPraticaMockMvc.perform(post("/api/rappresentanza-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rappresentanzaPraticaDTO)))
            .andExpect(status().isBadRequest());

        List<RappresentanzaPratica> rappresentanzaPraticaList = rappresentanzaPraticaRepository.findAll();
        assertThat(rappresentanzaPraticaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdPersonaRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = rappresentanzaPraticaRepository.findAll().size();
        // set the field null
        rappresentanzaPratica.setIdPersonaRef(null);

        // Create the RappresentanzaPratica, which fails.
        RappresentanzaPraticaDTO rappresentanzaPraticaDTO = rappresentanzaPraticaMapper.toDto(rappresentanzaPratica);


        restRappresentanzaPraticaMockMvc.perform(post("/api/rappresentanza-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rappresentanzaPraticaDTO)))
            .andExpect(status().isBadRequest());

        List<RappresentanzaPratica> rappresentanzaPraticaList = rappresentanzaPraticaRepository.findAll();
        assertThat(rappresentanzaPraticaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRappresentanzaPraticas() throws Exception {
        // Initialize the database
        rappresentanzaPraticaRepository.saveAndFlush(rappresentanzaPratica);

        // Get all the rappresentanzaPraticaList
        restRappresentanzaPraticaMockMvc.perform(get("/api/rappresentanza-praticas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rappresentanzaPratica.getId().intValue())))
            .andExpect(jsonPath("$.[*].idRuoloPersona").value(hasItem(DEFAULT_ID_RUOLO_PERSONA.intValue())))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF.intValue())))
            .andExpect(jsonPath("$.[*].ruoli").value(hasItem(DEFAULT_RUOLI.toString())));
    }
    
    @Test
    @Transactional
    public void getRappresentanzaPratica() throws Exception {
        // Initialize the database
        rappresentanzaPraticaRepository.saveAndFlush(rappresentanzaPratica);

        // Get the rappresentanzaPratica
        restRappresentanzaPraticaMockMvc.perform(get("/api/rappresentanza-praticas/{id}", rappresentanzaPratica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rappresentanzaPratica.getId().intValue()))
            .andExpect(jsonPath("$.idRuoloPersona").value(DEFAULT_ID_RUOLO_PERSONA.intValue()))
            .andExpect(jsonPath("$.idPersonaRef").value(DEFAULT_ID_PERSONA_REF.intValue()))
            .andExpect(jsonPath("$.ruoli").value(DEFAULT_RUOLI.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingRappresentanzaPratica() throws Exception {
        // Get the rappresentanzaPratica
        restRappresentanzaPraticaMockMvc.perform(get("/api/rappresentanza-praticas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRappresentanzaPratica() throws Exception {
        // Initialize the database
        rappresentanzaPraticaRepository.saveAndFlush(rappresentanzaPratica);

        int databaseSizeBeforeUpdate = rappresentanzaPraticaRepository.findAll().size();

        // Update the rappresentanzaPratica
        RappresentanzaPratica updatedRappresentanzaPratica = rappresentanzaPraticaRepository.findById(rappresentanzaPratica.getId()).get();
        // Disconnect from session so that the updates on updatedRappresentanzaPratica are not directly saved in db
        em.detach(updatedRappresentanzaPratica);
        updatedRappresentanzaPratica
            .idRuoloPersona(UPDATED_ID_RUOLO_PERSONA)
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .ruoli(UPDATED_RUOLI);
        RappresentanzaPraticaDTO rappresentanzaPraticaDTO = rappresentanzaPraticaMapper.toDto(updatedRappresentanzaPratica);

        restRappresentanzaPraticaMockMvc.perform(put("/api/rappresentanza-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rappresentanzaPraticaDTO)))
            .andExpect(status().isOk());

        // Validate the RappresentanzaPratica in the database
        List<RappresentanzaPratica> rappresentanzaPraticaList = rappresentanzaPraticaRepository.findAll();
        assertThat(rappresentanzaPraticaList).hasSize(databaseSizeBeforeUpdate);
        RappresentanzaPratica testRappresentanzaPratica = rappresentanzaPraticaList.get(rappresentanzaPraticaList.size() - 1);
        assertThat(testRappresentanzaPratica.getIdRuoloPersona()).isEqualTo(UPDATED_ID_RUOLO_PERSONA);
        assertThat(testRappresentanzaPratica.getIdPersonaRef()).isEqualTo(UPDATED_ID_PERSONA_REF);
        assertThat(testRappresentanzaPratica.getRuoli()).isEqualTo(UPDATED_RUOLI);

        // Validate the RappresentanzaPratica in Elasticsearch
        verify(mockRappresentanzaPraticaSearchRepository, times(1)).save(testRappresentanzaPratica);
    }

    @Test
    @Transactional
    public void updateNonExistingRappresentanzaPratica() throws Exception {
        int databaseSizeBeforeUpdate = rappresentanzaPraticaRepository.findAll().size();

        // Create the RappresentanzaPratica
        RappresentanzaPraticaDTO rappresentanzaPraticaDTO = rappresentanzaPraticaMapper.toDto(rappresentanzaPratica);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRappresentanzaPraticaMockMvc.perform(put("/api/rappresentanza-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rappresentanzaPraticaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RappresentanzaPratica in the database
        List<RappresentanzaPratica> rappresentanzaPraticaList = rappresentanzaPraticaRepository.findAll();
        assertThat(rappresentanzaPraticaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the RappresentanzaPratica in Elasticsearch
        verify(mockRappresentanzaPraticaSearchRepository, times(0)).save(rappresentanzaPratica);
    }

    @Test
    @Transactional
    public void deleteRappresentanzaPratica() throws Exception {
        // Initialize the database
        rappresentanzaPraticaRepository.saveAndFlush(rappresentanzaPratica);

        int databaseSizeBeforeDelete = rappresentanzaPraticaRepository.findAll().size();

        // Delete the rappresentanzaPratica
        restRappresentanzaPraticaMockMvc.perform(delete("/api/rappresentanza-praticas/{id}", rappresentanzaPratica.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RappresentanzaPratica> rappresentanzaPraticaList = rappresentanzaPraticaRepository.findAll();
        assertThat(rappresentanzaPraticaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the RappresentanzaPratica in Elasticsearch
        verify(mockRappresentanzaPraticaSearchRepository, times(1)).deleteById(rappresentanzaPratica.getId());
    }

    @Test
    @Transactional
    public void searchRappresentanzaPratica() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        rappresentanzaPraticaRepository.saveAndFlush(rappresentanzaPratica);
        when(mockRappresentanzaPraticaSearchRepository.search(queryStringQuery("id:" + rappresentanzaPratica.getId())))
            .thenReturn(Collections.singletonList(rappresentanzaPratica));

        // Search the rappresentanzaPratica
        restRappresentanzaPraticaMockMvc.perform(get("/api/_search/rappresentanza-praticas?query=id:" + rappresentanzaPratica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rappresentanzaPratica.getId().intValue())))
            .andExpect(jsonPath("$.[*].idRuoloPersona").value(hasItem(DEFAULT_ID_RUOLO_PERSONA.intValue())))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF.intValue())))
            .andExpect(jsonPath("$.[*].ruoli").value(hasItem(DEFAULT_RUOLI.toString())));
    }
}
