package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.PrevisioneAttivita;
import it.maggioli.repository.PrevisioneAttivitaRepository;
import it.maggioli.repository.search.PrevisioneAttivitaSearchRepository;
import it.maggioli.service.PrevisioneAttivitaService;
import it.maggioli.service.dto.PrevisioneAttivitaDTO;
import it.maggioli.service.mapper.PrevisioneAttivitaMapper;

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
 * Integration tests for the {@link PrevisioneAttivitaResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class PrevisioneAttivitaResourceIT {

    private static final Integer DEFAULT_ID_TASK = 8;
    private static final Integer UPDATED_ID_TASK = 7;

    private static final String DEFAULT_DATA_PIANIFICATA = "AAAAAAAAAA";
    private static final String UPDATED_DATA_PIANIFICATA = "BBBBBBBBBB";

    private static final String DEFAULT_ORA_PIANIFICATA = "AAAAAAAAAA";
    private static final String UPDATED_ORA_PIANIFICATA = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_SCADENZA = "AAAAAAAAAA";
    private static final String UPDATED_DATA_SCADENZA = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    @Autowired
    private PrevisioneAttivitaRepository previsioneAttivitaRepository;

    @Autowired
    private PrevisioneAttivitaMapper previsioneAttivitaMapper;

    @Autowired
    private PrevisioneAttivitaService previsioneAttivitaService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.PrevisioneAttivitaSearchRepositoryMockConfiguration
     */
    @Autowired
    private PrevisioneAttivitaSearchRepository mockPrevisioneAttivitaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrevisioneAttivitaMockMvc;

    private PrevisioneAttivita previsioneAttivita;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrevisioneAttivita createEntity(EntityManager em) {
        PrevisioneAttivita previsioneAttivita = new PrevisioneAttivita()
            .idTask(DEFAULT_ID_TASK)
            .dataPianificata(DEFAULT_DATA_PIANIFICATA)
            .oraPianificata(DEFAULT_ORA_PIANIFICATA)
            .dataScadenza(DEFAULT_DATA_SCADENZA)
            .version(DEFAULT_VERSION);
        return previsioneAttivita;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrevisioneAttivita createUpdatedEntity(EntityManager em) {
        PrevisioneAttivita previsioneAttivita = new PrevisioneAttivita()
            .idTask(UPDATED_ID_TASK)
            .dataPianificata(UPDATED_DATA_PIANIFICATA)
            .oraPianificata(UPDATED_ORA_PIANIFICATA)
            .dataScadenza(UPDATED_DATA_SCADENZA)
            .version(UPDATED_VERSION);
        return previsioneAttivita;
    }

    @BeforeEach
    public void initTest() {
        previsioneAttivita = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrevisioneAttivita() throws Exception {
        int databaseSizeBeforeCreate = previsioneAttivitaRepository.findAll().size();
        // Create the PrevisioneAttivita
        PrevisioneAttivitaDTO previsioneAttivitaDTO = previsioneAttivitaMapper.toDto(previsioneAttivita);
        restPrevisioneAttivitaMockMvc.perform(post("/api/previsione-attivitas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsioneAttivitaDTO)))
            .andExpect(status().isCreated());

        // Validate the PrevisioneAttivita in the database
        List<PrevisioneAttivita> previsioneAttivitaList = previsioneAttivitaRepository.findAll();
        assertThat(previsioneAttivitaList).hasSize(databaseSizeBeforeCreate + 1);
        PrevisioneAttivita testPrevisioneAttivita = previsioneAttivitaList.get(previsioneAttivitaList.size() - 1);
        assertThat(testPrevisioneAttivita.getIdTask()).isEqualTo(DEFAULT_ID_TASK);
        assertThat(testPrevisioneAttivita.getDataPianificata()).isEqualTo(DEFAULT_DATA_PIANIFICATA);
        assertThat(testPrevisioneAttivita.getOraPianificata()).isEqualTo(DEFAULT_ORA_PIANIFICATA);
        assertThat(testPrevisioneAttivita.getDataScadenza()).isEqualTo(DEFAULT_DATA_SCADENZA);
        assertThat(testPrevisioneAttivita.getVersion()).isEqualTo(DEFAULT_VERSION);

        // Validate the PrevisioneAttivita in Elasticsearch
        verify(mockPrevisioneAttivitaSearchRepository, times(1)).save(testPrevisioneAttivita);
    }

    @Test
    @Transactional
    public void createPrevisioneAttivitaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = previsioneAttivitaRepository.findAll().size();

        // Create the PrevisioneAttivita with an existing ID
        previsioneAttivita.setId(1L);
        PrevisioneAttivitaDTO previsioneAttivitaDTO = previsioneAttivitaMapper.toDto(previsioneAttivita);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrevisioneAttivitaMockMvc.perform(post("/api/previsione-attivitas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsioneAttivitaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PrevisioneAttivita in the database
        List<PrevisioneAttivita> previsioneAttivitaList = previsioneAttivitaRepository.findAll();
        assertThat(previsioneAttivitaList).hasSize(databaseSizeBeforeCreate);

        // Validate the PrevisioneAttivita in Elasticsearch
        verify(mockPrevisioneAttivitaSearchRepository, times(0)).save(previsioneAttivita);
    }


    @Test
    @Transactional
    public void checkIdTaskIsRequired() throws Exception {
        int databaseSizeBeforeTest = previsioneAttivitaRepository.findAll().size();
        // set the field null
        previsioneAttivita.setIdTask(null);

        // Create the PrevisioneAttivita, which fails.
        PrevisioneAttivitaDTO previsioneAttivitaDTO = previsioneAttivitaMapper.toDto(previsioneAttivita);


        restPrevisioneAttivitaMockMvc.perform(post("/api/previsione-attivitas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsioneAttivitaDTO)))
            .andExpect(status().isBadRequest());

        List<PrevisioneAttivita> previsioneAttivitaList = previsioneAttivitaRepository.findAll();
        assertThat(previsioneAttivitaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPrevisioneAttivitas() throws Exception {
        // Initialize the database
        previsioneAttivitaRepository.saveAndFlush(previsioneAttivita);

        // Get all the previsioneAttivitaList
        restPrevisioneAttivitaMockMvc.perform(get("/api/previsione-attivitas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(previsioneAttivita.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTask").value(hasItem(DEFAULT_ID_TASK)))
            .andExpect(jsonPath("$.[*].dataPianificata").value(hasItem(DEFAULT_DATA_PIANIFICATA)))
            .andExpect(jsonPath("$.[*].oraPianificata").value(hasItem(DEFAULT_ORA_PIANIFICATA)))
            .andExpect(jsonPath("$.[*].dataScadenza").value(hasItem(DEFAULT_DATA_SCADENZA)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }
    
    @Test
    @Transactional
    public void getPrevisioneAttivita() throws Exception {
        // Initialize the database
        previsioneAttivitaRepository.saveAndFlush(previsioneAttivita);

        // Get the previsioneAttivita
        restPrevisioneAttivitaMockMvc.perform(get("/api/previsione-attivitas/{id}", previsioneAttivita.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(previsioneAttivita.getId().intValue()))
            .andExpect(jsonPath("$.idTask").value(DEFAULT_ID_TASK))
            .andExpect(jsonPath("$.dataPianificata").value(DEFAULT_DATA_PIANIFICATA))
            .andExpect(jsonPath("$.oraPianificata").value(DEFAULT_ORA_PIANIFICATA))
            .andExpect(jsonPath("$.dataScadenza").value(DEFAULT_DATA_SCADENZA))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION));
    }
    @Test
    @Transactional
    public void getNonExistingPrevisioneAttivita() throws Exception {
        // Get the previsioneAttivita
        restPrevisioneAttivitaMockMvc.perform(get("/api/previsione-attivitas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrevisioneAttivita() throws Exception {
        // Initialize the database
        previsioneAttivitaRepository.saveAndFlush(previsioneAttivita);

        int databaseSizeBeforeUpdate = previsioneAttivitaRepository.findAll().size();

        // Update the previsioneAttivita
        PrevisioneAttivita updatedPrevisioneAttivita = previsioneAttivitaRepository.findById(previsioneAttivita.getId()).get();
        // Disconnect from session so that the updates on updatedPrevisioneAttivita are not directly saved in db
        em.detach(updatedPrevisioneAttivita);
        updatedPrevisioneAttivita
            .idTask(UPDATED_ID_TASK)
            .dataPianificata(UPDATED_DATA_PIANIFICATA)
            .oraPianificata(UPDATED_ORA_PIANIFICATA)
            .dataScadenza(UPDATED_DATA_SCADENZA)
            .version(UPDATED_VERSION);
        PrevisioneAttivitaDTO previsioneAttivitaDTO = previsioneAttivitaMapper.toDto(updatedPrevisioneAttivita);

        restPrevisioneAttivitaMockMvc.perform(put("/api/previsione-attivitas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsioneAttivitaDTO)))
            .andExpect(status().isOk());

        // Validate the PrevisioneAttivita in the database
        List<PrevisioneAttivita> previsioneAttivitaList = previsioneAttivitaRepository.findAll();
        assertThat(previsioneAttivitaList).hasSize(databaseSizeBeforeUpdate);
        PrevisioneAttivita testPrevisioneAttivita = previsioneAttivitaList.get(previsioneAttivitaList.size() - 1);
        assertThat(testPrevisioneAttivita.getIdTask()).isEqualTo(UPDATED_ID_TASK);
        assertThat(testPrevisioneAttivita.getDataPianificata()).isEqualTo(UPDATED_DATA_PIANIFICATA);
        assertThat(testPrevisioneAttivita.getOraPianificata()).isEqualTo(UPDATED_ORA_PIANIFICATA);
        assertThat(testPrevisioneAttivita.getDataScadenza()).isEqualTo(UPDATED_DATA_SCADENZA);
        assertThat(testPrevisioneAttivita.getVersion()).isEqualTo(UPDATED_VERSION);

        // Validate the PrevisioneAttivita in Elasticsearch
        verify(mockPrevisioneAttivitaSearchRepository, times(1)).save(testPrevisioneAttivita);
    }

    @Test
    @Transactional
    public void updateNonExistingPrevisioneAttivita() throws Exception {
        int databaseSizeBeforeUpdate = previsioneAttivitaRepository.findAll().size();

        // Create the PrevisioneAttivita
        PrevisioneAttivitaDTO previsioneAttivitaDTO = previsioneAttivitaMapper.toDto(previsioneAttivita);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrevisioneAttivitaMockMvc.perform(put("/api/previsione-attivitas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsioneAttivitaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PrevisioneAttivita in the database
        List<PrevisioneAttivita> previsioneAttivitaList = previsioneAttivitaRepository.findAll();
        assertThat(previsioneAttivitaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PrevisioneAttivita in Elasticsearch
        verify(mockPrevisioneAttivitaSearchRepository, times(0)).save(previsioneAttivita);
    }

    @Test
    @Transactional
    public void deletePrevisioneAttivita() throws Exception {
        // Initialize the database
        previsioneAttivitaRepository.saveAndFlush(previsioneAttivita);

        int databaseSizeBeforeDelete = previsioneAttivitaRepository.findAll().size();

        // Delete the previsioneAttivita
        restPrevisioneAttivitaMockMvc.perform(delete("/api/previsione-attivitas/{id}", previsioneAttivita.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PrevisioneAttivita> previsioneAttivitaList = previsioneAttivitaRepository.findAll();
        assertThat(previsioneAttivitaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PrevisioneAttivita in Elasticsearch
        verify(mockPrevisioneAttivitaSearchRepository, times(1)).deleteById(previsioneAttivita.getId());
    }

    @Test
    @Transactional
    public void searchPrevisioneAttivita() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        previsioneAttivitaRepository.saveAndFlush(previsioneAttivita);
        when(mockPrevisioneAttivitaSearchRepository.search(queryStringQuery("id:" + previsioneAttivita.getId())))
            .thenReturn(Collections.singletonList(previsioneAttivita));

        // Search the previsioneAttivita
        restPrevisioneAttivitaMockMvc.perform(get("/api/_search/previsione-attivitas?query=id:" + previsioneAttivita.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(previsioneAttivita.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTask").value(hasItem(DEFAULT_ID_TASK)))
            .andExpect(jsonPath("$.[*].dataPianificata").value(hasItem(DEFAULT_DATA_PIANIFICATA)))
            .andExpect(jsonPath("$.[*].oraPianificata").value(hasItem(DEFAULT_ORA_PIANIFICATA)))
            .andExpect(jsonPath("$.[*].dataScadenza").value(hasItem(DEFAULT_DATA_SCADENZA)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }
}
