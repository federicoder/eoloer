package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.PrevisioneEvento;
import it.maggioli.repository.PrevisioneEventoRepository;
import it.maggioli.repository.search.PrevisioneEventoSearchRepository;
import it.maggioli.service.PrevisioneEventoService;
import it.maggioli.service.dto.PrevisioneEventoDTO;
import it.maggioli.service.mapper.PrevisioneEventoMapper;

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
 * Integration tests for the {@link PrevisioneEventoResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class PrevisioneEventoResourceIT {

    private static final Integer DEFAULT_ID_TASK = 8;
    private static final Integer UPDATED_ID_TASK = 7;

    private static final String DEFAULT_DATA_INIZIO = "AAAAAAAAAA";
    private static final String UPDATED_DATA_INIZIO = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_FINE = "AAAAAAAAAA";
    private static final String UPDATED_DATA_FINE = "BBBBBBBBBB";

    private static final String DEFAULT_LUOGO = "AAAAAAAAAA";
    private static final String UPDATED_LUOGO = "BBBBBBBBBB";

    private static final String DEFAULT_INDICAZIONI_LUOGO = "AAAAAAAAAA";
    private static final String UPDATED_INDICAZIONI_LUOGO = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    @Autowired
    private PrevisioneEventoRepository previsioneEventoRepository;

    @Autowired
    private PrevisioneEventoMapper previsioneEventoMapper;

    @Autowired
    private PrevisioneEventoService previsioneEventoService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.PrevisioneEventoSearchRepositoryMockConfiguration
     */
    @Autowired
    private PrevisioneEventoSearchRepository mockPrevisioneEventoSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrevisioneEventoMockMvc;

    private PrevisioneEvento previsioneEvento;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrevisioneEvento createEntity(EntityManager em) {
        PrevisioneEvento previsioneEvento = new PrevisioneEvento()
            .idTask(DEFAULT_ID_TASK)
            .dataInizio(DEFAULT_DATA_INIZIO)
            .dataFine(DEFAULT_DATA_FINE)
            .luogo(DEFAULT_LUOGO)
            .indicazioniLuogo(DEFAULT_INDICAZIONI_LUOGO)
            .version(DEFAULT_VERSION);
        return previsioneEvento;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrevisioneEvento createUpdatedEntity(EntityManager em) {
        PrevisioneEvento previsioneEvento = new PrevisioneEvento()
            .idTask(UPDATED_ID_TASK)
            .dataInizio(UPDATED_DATA_INIZIO)
            .dataFine(UPDATED_DATA_FINE)
            .luogo(UPDATED_LUOGO)
            .indicazioniLuogo(UPDATED_INDICAZIONI_LUOGO)
            .version(UPDATED_VERSION);
        return previsioneEvento;
    }

    @BeforeEach
    public void initTest() {
        previsioneEvento = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrevisioneEvento() throws Exception {
        int databaseSizeBeforeCreate = previsioneEventoRepository.findAll().size();
        // Create the PrevisioneEvento
        PrevisioneEventoDTO previsioneEventoDTO = previsioneEventoMapper.toDto(previsioneEvento);
        restPrevisioneEventoMockMvc.perform(post("/api/previsione-eventos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsioneEventoDTO)))
            .andExpect(status().isCreated());

        // Validate the PrevisioneEvento in the database
        List<PrevisioneEvento> previsioneEventoList = previsioneEventoRepository.findAll();
        assertThat(previsioneEventoList).hasSize(databaseSizeBeforeCreate + 1);
        PrevisioneEvento testPrevisioneEvento = previsioneEventoList.get(previsioneEventoList.size() - 1);
        assertThat(testPrevisioneEvento.getIdTask()).isEqualTo(DEFAULT_ID_TASK);
        assertThat(testPrevisioneEvento.getDataInizio()).isEqualTo(DEFAULT_DATA_INIZIO);
        assertThat(testPrevisioneEvento.getDataFine()).isEqualTo(DEFAULT_DATA_FINE);
        assertThat(testPrevisioneEvento.getLuogo()).isEqualTo(DEFAULT_LUOGO);
        assertThat(testPrevisioneEvento.getIndicazioniLuogo()).isEqualTo(DEFAULT_INDICAZIONI_LUOGO);
        assertThat(testPrevisioneEvento.getVersion()).isEqualTo(DEFAULT_VERSION);

        // Validate the PrevisioneEvento in Elasticsearch
        verify(mockPrevisioneEventoSearchRepository, times(1)).save(testPrevisioneEvento);
    }

    @Test
    @Transactional
    public void createPrevisioneEventoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = previsioneEventoRepository.findAll().size();

        // Create the PrevisioneEvento with an existing ID
        previsioneEvento.setId(1L);
        PrevisioneEventoDTO previsioneEventoDTO = previsioneEventoMapper.toDto(previsioneEvento);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrevisioneEventoMockMvc.perform(post("/api/previsione-eventos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsioneEventoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PrevisioneEvento in the database
        List<PrevisioneEvento> previsioneEventoList = previsioneEventoRepository.findAll();
        assertThat(previsioneEventoList).hasSize(databaseSizeBeforeCreate);

        // Validate the PrevisioneEvento in Elasticsearch
        verify(mockPrevisioneEventoSearchRepository, times(0)).save(previsioneEvento);
    }


    @Test
    @Transactional
    public void checkIdTaskIsRequired() throws Exception {
        int databaseSizeBeforeTest = previsioneEventoRepository.findAll().size();
        // set the field null
        previsioneEvento.setIdTask(null);

        // Create the PrevisioneEvento, which fails.
        PrevisioneEventoDTO previsioneEventoDTO = previsioneEventoMapper.toDto(previsioneEvento);


        restPrevisioneEventoMockMvc.perform(post("/api/previsione-eventos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsioneEventoDTO)))
            .andExpect(status().isBadRequest());

        List<PrevisioneEvento> previsioneEventoList = previsioneEventoRepository.findAll();
        assertThat(previsioneEventoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPrevisioneEventos() throws Exception {
        // Initialize the database
        previsioneEventoRepository.saveAndFlush(previsioneEvento);

        // Get all the previsioneEventoList
        restPrevisioneEventoMockMvc.perform(get("/api/previsione-eventos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(previsioneEvento.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTask").value(hasItem(DEFAULT_ID_TASK)))
            .andExpect(jsonPath("$.[*].dataInizio").value(hasItem(DEFAULT_DATA_INIZIO)))
            .andExpect(jsonPath("$.[*].dataFine").value(hasItem(DEFAULT_DATA_FINE)))
            .andExpect(jsonPath("$.[*].luogo").value(hasItem(DEFAULT_LUOGO)))
            .andExpect(jsonPath("$.[*].indicazioniLuogo").value(hasItem(DEFAULT_INDICAZIONI_LUOGO)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }
    
    @Test
    @Transactional
    public void getPrevisioneEvento() throws Exception {
        // Initialize the database
        previsioneEventoRepository.saveAndFlush(previsioneEvento);

        // Get the previsioneEvento
        restPrevisioneEventoMockMvc.perform(get("/api/previsione-eventos/{id}", previsioneEvento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(previsioneEvento.getId().intValue()))
            .andExpect(jsonPath("$.idTask").value(DEFAULT_ID_TASK))
            .andExpect(jsonPath("$.dataInizio").value(DEFAULT_DATA_INIZIO))
            .andExpect(jsonPath("$.dataFine").value(DEFAULT_DATA_FINE))
            .andExpect(jsonPath("$.luogo").value(DEFAULT_LUOGO))
            .andExpect(jsonPath("$.indicazioniLuogo").value(DEFAULT_INDICAZIONI_LUOGO))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION));
    }
    @Test
    @Transactional
    public void getNonExistingPrevisioneEvento() throws Exception {
        // Get the previsioneEvento
        restPrevisioneEventoMockMvc.perform(get("/api/previsione-eventos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrevisioneEvento() throws Exception {
        // Initialize the database
        previsioneEventoRepository.saveAndFlush(previsioneEvento);

        int databaseSizeBeforeUpdate = previsioneEventoRepository.findAll().size();

        // Update the previsioneEvento
        PrevisioneEvento updatedPrevisioneEvento = previsioneEventoRepository.findById(previsioneEvento.getId()).get();
        // Disconnect from session so that the updates on updatedPrevisioneEvento are not directly saved in db
        em.detach(updatedPrevisioneEvento);
        updatedPrevisioneEvento
            .idTask(UPDATED_ID_TASK)
            .dataInizio(UPDATED_DATA_INIZIO)
            .dataFine(UPDATED_DATA_FINE)
            .luogo(UPDATED_LUOGO)
            .indicazioniLuogo(UPDATED_INDICAZIONI_LUOGO)
            .version(UPDATED_VERSION);
        PrevisioneEventoDTO previsioneEventoDTO = previsioneEventoMapper.toDto(updatedPrevisioneEvento);

        restPrevisioneEventoMockMvc.perform(put("/api/previsione-eventos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsioneEventoDTO)))
            .andExpect(status().isOk());

        // Validate the PrevisioneEvento in the database
        List<PrevisioneEvento> previsioneEventoList = previsioneEventoRepository.findAll();
        assertThat(previsioneEventoList).hasSize(databaseSizeBeforeUpdate);
        PrevisioneEvento testPrevisioneEvento = previsioneEventoList.get(previsioneEventoList.size() - 1);
        assertThat(testPrevisioneEvento.getIdTask()).isEqualTo(UPDATED_ID_TASK);
        assertThat(testPrevisioneEvento.getDataInizio()).isEqualTo(UPDATED_DATA_INIZIO);
        assertThat(testPrevisioneEvento.getDataFine()).isEqualTo(UPDATED_DATA_FINE);
        assertThat(testPrevisioneEvento.getLuogo()).isEqualTo(UPDATED_LUOGO);
        assertThat(testPrevisioneEvento.getIndicazioniLuogo()).isEqualTo(UPDATED_INDICAZIONI_LUOGO);
        assertThat(testPrevisioneEvento.getVersion()).isEqualTo(UPDATED_VERSION);

        // Validate the PrevisioneEvento in Elasticsearch
        verify(mockPrevisioneEventoSearchRepository, times(1)).save(testPrevisioneEvento);
    }

    @Test
    @Transactional
    public void updateNonExistingPrevisioneEvento() throws Exception {
        int databaseSizeBeforeUpdate = previsioneEventoRepository.findAll().size();

        // Create the PrevisioneEvento
        PrevisioneEventoDTO previsioneEventoDTO = previsioneEventoMapper.toDto(previsioneEvento);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrevisioneEventoMockMvc.perform(put("/api/previsione-eventos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsioneEventoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PrevisioneEvento in the database
        List<PrevisioneEvento> previsioneEventoList = previsioneEventoRepository.findAll();
        assertThat(previsioneEventoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PrevisioneEvento in Elasticsearch
        verify(mockPrevisioneEventoSearchRepository, times(0)).save(previsioneEvento);
    }

    @Test
    @Transactional
    public void deletePrevisioneEvento() throws Exception {
        // Initialize the database
        previsioneEventoRepository.saveAndFlush(previsioneEvento);

        int databaseSizeBeforeDelete = previsioneEventoRepository.findAll().size();

        // Delete the previsioneEvento
        restPrevisioneEventoMockMvc.perform(delete("/api/previsione-eventos/{id}", previsioneEvento.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PrevisioneEvento> previsioneEventoList = previsioneEventoRepository.findAll();
        assertThat(previsioneEventoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PrevisioneEvento in Elasticsearch
        verify(mockPrevisioneEventoSearchRepository, times(1)).deleteById(previsioneEvento.getId());
    }

    @Test
    @Transactional
    public void searchPrevisioneEvento() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        previsioneEventoRepository.saveAndFlush(previsioneEvento);
        when(mockPrevisioneEventoSearchRepository.search(queryStringQuery("id:" + previsioneEvento.getId())))
            .thenReturn(Collections.singletonList(previsioneEvento));

        // Search the previsioneEvento
        restPrevisioneEventoMockMvc.perform(get("/api/_search/previsione-eventos?query=id:" + previsioneEvento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(previsioneEvento.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTask").value(hasItem(DEFAULT_ID_TASK)))
            .andExpect(jsonPath("$.[*].dataInizio").value(hasItem(DEFAULT_DATA_INIZIO)))
            .andExpect(jsonPath("$.[*].dataFine").value(hasItem(DEFAULT_DATA_FINE)))
            .andExpect(jsonPath("$.[*].luogo").value(hasItem(DEFAULT_LUOGO)))
            .andExpect(jsonPath("$.[*].indicazioniLuogo").value(hasItem(DEFAULT_INDICAZIONI_LUOGO)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }
}
