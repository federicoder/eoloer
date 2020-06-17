package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.DatiContabili;
import it.maggioli.repository.DatiContabiliRepository;
import it.maggioli.repository.search.DatiContabiliSearchRepository;
import it.maggioli.service.DatiContabiliService;
import it.maggioli.service.dto.DatiContabiliDTO;
import it.maggioli.service.mapper.DatiContabiliMapper;

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
 * Integration tests for the {@link DatiContabiliResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class DatiContabiliResourceIT {

    private static final Integer DEFAULT_ID_DATI_CONTABILI = 1;
    private static final Integer UPDATED_ID_DATI_CONTABILI = 2;

    private static final Integer DEFAULT_ID_PERSONA_REF = 1;
    private static final Integer UPDATED_ID_PERSONA_REF = 2;

    @Autowired
    private DatiContabiliRepository datiContabiliRepository;

    @Autowired
    private DatiContabiliMapper datiContabiliMapper;

    @Autowired
    private DatiContabiliService datiContabiliService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.DatiContabiliSearchRepositoryMockConfiguration
     */
    @Autowired
    private DatiContabiliSearchRepository mockDatiContabiliSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDatiContabiliMockMvc;

    private DatiContabili datiContabili;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DatiContabili createEntity(EntityManager em) {
        DatiContabili datiContabili = new DatiContabili()
            .idDatiContabili(DEFAULT_ID_DATI_CONTABILI)
            .idPersonaRef(DEFAULT_ID_PERSONA_REF);
        return datiContabili;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DatiContabili createUpdatedEntity(EntityManager em) {
        DatiContabili datiContabili = new DatiContabili()
            .idDatiContabili(UPDATED_ID_DATI_CONTABILI)
            .idPersonaRef(UPDATED_ID_PERSONA_REF);
        return datiContabili;
    }

    @BeforeEach
    public void initTest() {
        datiContabili = createEntity(em);
    }

    @Test
    @Transactional
    public void createDatiContabili() throws Exception {
        int databaseSizeBeforeCreate = datiContabiliRepository.findAll().size();
        // Create the DatiContabili
        DatiContabiliDTO datiContabiliDTO = datiContabiliMapper.toDto(datiContabili);
        restDatiContabiliMockMvc.perform(post("/api/dati-contabilis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(datiContabiliDTO)))
            .andExpect(status().isCreated());

        // Validate the DatiContabili in the database
        List<DatiContabili> datiContabiliList = datiContabiliRepository.findAll();
        assertThat(datiContabiliList).hasSize(databaseSizeBeforeCreate + 1);
        DatiContabili testDatiContabili = datiContabiliList.get(datiContabiliList.size() - 1);
        assertThat(testDatiContabili.getIdDatiContabili()).isEqualTo(DEFAULT_ID_DATI_CONTABILI);
        assertThat(testDatiContabili.getIdPersonaRef()).isEqualTo(DEFAULT_ID_PERSONA_REF);

        // Validate the DatiContabili in Elasticsearch
        verify(mockDatiContabiliSearchRepository, times(1)).save(testDatiContabili);
    }

    @Test
    @Transactional
    public void createDatiContabiliWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = datiContabiliRepository.findAll().size();

        // Create the DatiContabili with an existing ID
        datiContabili.setId(1L);
        DatiContabiliDTO datiContabiliDTO = datiContabiliMapper.toDto(datiContabili);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDatiContabiliMockMvc.perform(post("/api/dati-contabilis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(datiContabiliDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DatiContabili in the database
        List<DatiContabili> datiContabiliList = datiContabiliRepository.findAll();
        assertThat(datiContabiliList).hasSize(databaseSizeBeforeCreate);

        // Validate the DatiContabili in Elasticsearch
        verify(mockDatiContabiliSearchRepository, times(0)).save(datiContabili);
    }


    @Test
    @Transactional
    public void checkIdDatiContabiliIsRequired() throws Exception {
        int databaseSizeBeforeTest = datiContabiliRepository.findAll().size();
        // set the field null
        datiContabili.setIdDatiContabili(null);

        // Create the DatiContabili, which fails.
        DatiContabiliDTO datiContabiliDTO = datiContabiliMapper.toDto(datiContabili);


        restDatiContabiliMockMvc.perform(post("/api/dati-contabilis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(datiContabiliDTO)))
            .andExpect(status().isBadRequest());

        List<DatiContabili> datiContabiliList = datiContabiliRepository.findAll();
        assertThat(datiContabiliList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdPersonaRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = datiContabiliRepository.findAll().size();
        // set the field null
        datiContabili.setIdPersonaRef(null);

        // Create the DatiContabili, which fails.
        DatiContabiliDTO datiContabiliDTO = datiContabiliMapper.toDto(datiContabili);


        restDatiContabiliMockMvc.perform(post("/api/dati-contabilis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(datiContabiliDTO)))
            .andExpect(status().isBadRequest());

        List<DatiContabili> datiContabiliList = datiContabiliRepository.findAll();
        assertThat(datiContabiliList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDatiContabilis() throws Exception {
        // Initialize the database
        datiContabiliRepository.saveAndFlush(datiContabili);

        // Get all the datiContabiliList
        restDatiContabiliMockMvc.perform(get("/api/dati-contabilis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(datiContabili.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDatiContabili").value(hasItem(DEFAULT_ID_DATI_CONTABILI)))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF)));
    }
    
    @Test
    @Transactional
    public void getDatiContabili() throws Exception {
        // Initialize the database
        datiContabiliRepository.saveAndFlush(datiContabili);

        // Get the datiContabili
        restDatiContabiliMockMvc.perform(get("/api/dati-contabilis/{id}", datiContabili.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(datiContabili.getId().intValue()))
            .andExpect(jsonPath("$.idDatiContabili").value(DEFAULT_ID_DATI_CONTABILI))
            .andExpect(jsonPath("$.idPersonaRef").value(DEFAULT_ID_PERSONA_REF));
    }
    @Test
    @Transactional
    public void getNonExistingDatiContabili() throws Exception {
        // Get the datiContabili
        restDatiContabiliMockMvc.perform(get("/api/dati-contabilis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDatiContabili() throws Exception {
        // Initialize the database
        datiContabiliRepository.saveAndFlush(datiContabili);

        int databaseSizeBeforeUpdate = datiContabiliRepository.findAll().size();

        // Update the datiContabili
        DatiContabili updatedDatiContabili = datiContabiliRepository.findById(datiContabili.getId()).get();
        // Disconnect from session so that the updates on updatedDatiContabili are not directly saved in db
        em.detach(updatedDatiContabili);
        updatedDatiContabili
            .idDatiContabili(UPDATED_ID_DATI_CONTABILI)
            .idPersonaRef(UPDATED_ID_PERSONA_REF);
        DatiContabiliDTO datiContabiliDTO = datiContabiliMapper.toDto(updatedDatiContabili);

        restDatiContabiliMockMvc.perform(put("/api/dati-contabilis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(datiContabiliDTO)))
            .andExpect(status().isOk());

        // Validate the DatiContabili in the database
        List<DatiContabili> datiContabiliList = datiContabiliRepository.findAll();
        assertThat(datiContabiliList).hasSize(databaseSizeBeforeUpdate);
        DatiContabili testDatiContabili = datiContabiliList.get(datiContabiliList.size() - 1);
        assertThat(testDatiContabili.getIdDatiContabili()).isEqualTo(UPDATED_ID_DATI_CONTABILI);
        assertThat(testDatiContabili.getIdPersonaRef()).isEqualTo(UPDATED_ID_PERSONA_REF);

        // Validate the DatiContabili in Elasticsearch
        verify(mockDatiContabiliSearchRepository, times(1)).save(testDatiContabili);
    }

    @Test
    @Transactional
    public void updateNonExistingDatiContabili() throws Exception {
        int databaseSizeBeforeUpdate = datiContabiliRepository.findAll().size();

        // Create the DatiContabili
        DatiContabiliDTO datiContabiliDTO = datiContabiliMapper.toDto(datiContabili);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDatiContabiliMockMvc.perform(put("/api/dati-contabilis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(datiContabiliDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DatiContabili in the database
        List<DatiContabili> datiContabiliList = datiContabiliRepository.findAll();
        assertThat(datiContabiliList).hasSize(databaseSizeBeforeUpdate);

        // Validate the DatiContabili in Elasticsearch
        verify(mockDatiContabiliSearchRepository, times(0)).save(datiContabili);
    }

    @Test
    @Transactional
    public void deleteDatiContabili() throws Exception {
        // Initialize the database
        datiContabiliRepository.saveAndFlush(datiContabili);

        int databaseSizeBeforeDelete = datiContabiliRepository.findAll().size();

        // Delete the datiContabili
        restDatiContabiliMockMvc.perform(delete("/api/dati-contabilis/{id}", datiContabili.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DatiContabili> datiContabiliList = datiContabiliRepository.findAll();
        assertThat(datiContabiliList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the DatiContabili in Elasticsearch
        verify(mockDatiContabiliSearchRepository, times(1)).deleteById(datiContabili.getId());
    }

    @Test
    @Transactional
    public void searchDatiContabili() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        datiContabiliRepository.saveAndFlush(datiContabili);
        when(mockDatiContabiliSearchRepository.search(queryStringQuery("id:" + datiContabili.getId())))
            .thenReturn(Collections.singletonList(datiContabili));

        // Search the datiContabili
        restDatiContabiliMockMvc.perform(get("/api/_search/dati-contabilis?query=id:" + datiContabili.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(datiContabili.getId().intValue())))
            .andExpect(jsonPath("$.[*].idDatiContabili").value(hasItem(DEFAULT_ID_DATI_CONTABILI)))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF)));
    }
}
