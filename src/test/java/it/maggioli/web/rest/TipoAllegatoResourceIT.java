package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.TipoAllegato;
import it.maggioli.repository.TipoAllegatoRepository;
import it.maggioli.repository.search.TipoAllegatoSearchRepository;
import it.maggioli.service.TipoAllegatoService;
import it.maggioli.service.dto.TipoAllegatoDTO;
import it.maggioli.service.mapper.TipoAllegatoMapper;

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
 * Integration tests for the {@link TipoAllegatoResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class TipoAllegatoResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_FORMATI_AMMESSI = "AAAAAAAAAA";
    private static final String UPDATED_FORMATI_AMMESSI = "BBBBBBBBBB";

    private static final String DEFAULT_MAX_DIMENSIONE_AMMESSA = "AAAAAAAAAA";
    private static final String UPDATED_MAX_DIMENSIONE_AMMESSA = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    @Autowired
    private TipoAllegatoRepository tipoAllegatoRepository;

    @Autowired
    private TipoAllegatoMapper tipoAllegatoMapper;

    @Autowired
    private TipoAllegatoService tipoAllegatoService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.TipoAllegatoSearchRepositoryMockConfiguration
     */
    @Autowired
    private TipoAllegatoSearchRepository mockTipoAllegatoSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTipoAllegatoMockMvc;

    private TipoAllegato tipoAllegato;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoAllegato createEntity(EntityManager em) {
        TipoAllegato tipoAllegato = new TipoAllegato()
            .nome(DEFAULT_NOME)
            .formatiAmmessi(DEFAULT_FORMATI_AMMESSI)
            .maxDimensioneAmmessa(DEFAULT_MAX_DIMENSIONE_AMMESSA)
            .version(DEFAULT_VERSION);
        return tipoAllegato;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoAllegato createUpdatedEntity(EntityManager em) {
        TipoAllegato tipoAllegato = new TipoAllegato()
            .nome(UPDATED_NOME)
            .formatiAmmessi(UPDATED_FORMATI_AMMESSI)
            .maxDimensioneAmmessa(UPDATED_MAX_DIMENSIONE_AMMESSA)
            .version(UPDATED_VERSION);
        return tipoAllegato;
    }

    @BeforeEach
    public void initTest() {
        tipoAllegato = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoAllegato() throws Exception {
        int databaseSizeBeforeCreate = tipoAllegatoRepository.findAll().size();
        // Create the TipoAllegato
        TipoAllegatoDTO tipoAllegatoDTO = tipoAllegatoMapper.toDto(tipoAllegato);
        restTipoAllegatoMockMvc.perform(post("/api/tipo-allegatoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoAllegatoDTO)))
            .andExpect(status().isCreated());

        // Validate the TipoAllegato in the database
        List<TipoAllegato> tipoAllegatoList = tipoAllegatoRepository.findAll();
        assertThat(tipoAllegatoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoAllegato testTipoAllegato = tipoAllegatoList.get(tipoAllegatoList.size() - 1);
        assertThat(testTipoAllegato.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTipoAllegato.getFormatiAmmessi()).isEqualTo(DEFAULT_FORMATI_AMMESSI);
        assertThat(testTipoAllegato.getMaxDimensioneAmmessa()).isEqualTo(DEFAULT_MAX_DIMENSIONE_AMMESSA);
        assertThat(testTipoAllegato.getVersion()).isEqualTo(DEFAULT_VERSION);

        // Validate the TipoAllegato in Elasticsearch
        verify(mockTipoAllegatoSearchRepository, times(1)).save(testTipoAllegato);
    }

    @Test
    @Transactional
    public void createTipoAllegatoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoAllegatoRepository.findAll().size();

        // Create the TipoAllegato with an existing ID
        tipoAllegato.setId(1L);
        TipoAllegatoDTO tipoAllegatoDTO = tipoAllegatoMapper.toDto(tipoAllegato);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoAllegatoMockMvc.perform(post("/api/tipo-allegatoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoAllegatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoAllegato in the database
        List<TipoAllegato> tipoAllegatoList = tipoAllegatoRepository.findAll();
        assertThat(tipoAllegatoList).hasSize(databaseSizeBeforeCreate);

        // Validate the TipoAllegato in Elasticsearch
        verify(mockTipoAllegatoSearchRepository, times(0)).save(tipoAllegato);
    }


    @Test
    @Transactional
    public void getAllTipoAllegatoes() throws Exception {
        // Initialize the database
        tipoAllegatoRepository.saveAndFlush(tipoAllegato);

        // Get all the tipoAllegatoList
        restTipoAllegatoMockMvc.perform(get("/api/tipo-allegatoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoAllegato.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].formatiAmmessi").value(hasItem(DEFAULT_FORMATI_AMMESSI)))
            .andExpect(jsonPath("$.[*].maxDimensioneAmmessa").value(hasItem(DEFAULT_MAX_DIMENSIONE_AMMESSA)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }
    
    @Test
    @Transactional
    public void getTipoAllegato() throws Exception {
        // Initialize the database
        tipoAllegatoRepository.saveAndFlush(tipoAllegato);

        // Get the tipoAllegato
        restTipoAllegatoMockMvc.perform(get("/api/tipo-allegatoes/{id}", tipoAllegato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tipoAllegato.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.formatiAmmessi").value(DEFAULT_FORMATI_AMMESSI))
            .andExpect(jsonPath("$.maxDimensioneAmmessa").value(DEFAULT_MAX_DIMENSIONE_AMMESSA))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION));
    }
    @Test
    @Transactional
    public void getNonExistingTipoAllegato() throws Exception {
        // Get the tipoAllegato
        restTipoAllegatoMockMvc.perform(get("/api/tipo-allegatoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoAllegato() throws Exception {
        // Initialize the database
        tipoAllegatoRepository.saveAndFlush(tipoAllegato);

        int databaseSizeBeforeUpdate = tipoAllegatoRepository.findAll().size();

        // Update the tipoAllegato
        TipoAllegato updatedTipoAllegato = tipoAllegatoRepository.findById(tipoAllegato.getId()).get();
        // Disconnect from session so that the updates on updatedTipoAllegato are not directly saved in db
        em.detach(updatedTipoAllegato);
        updatedTipoAllegato
            .nome(UPDATED_NOME)
            .formatiAmmessi(UPDATED_FORMATI_AMMESSI)
            .maxDimensioneAmmessa(UPDATED_MAX_DIMENSIONE_AMMESSA)
            .version(UPDATED_VERSION);
        TipoAllegatoDTO tipoAllegatoDTO = tipoAllegatoMapper.toDto(updatedTipoAllegato);

        restTipoAllegatoMockMvc.perform(put("/api/tipo-allegatoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoAllegatoDTO)))
            .andExpect(status().isOk());

        // Validate the TipoAllegato in the database
        List<TipoAllegato> tipoAllegatoList = tipoAllegatoRepository.findAll();
        assertThat(tipoAllegatoList).hasSize(databaseSizeBeforeUpdate);
        TipoAllegato testTipoAllegato = tipoAllegatoList.get(tipoAllegatoList.size() - 1);
        assertThat(testTipoAllegato.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTipoAllegato.getFormatiAmmessi()).isEqualTo(UPDATED_FORMATI_AMMESSI);
        assertThat(testTipoAllegato.getMaxDimensioneAmmessa()).isEqualTo(UPDATED_MAX_DIMENSIONE_AMMESSA);
        assertThat(testTipoAllegato.getVersion()).isEqualTo(UPDATED_VERSION);

        // Validate the TipoAllegato in Elasticsearch
        verify(mockTipoAllegatoSearchRepository, times(1)).save(testTipoAllegato);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoAllegato() throws Exception {
        int databaseSizeBeforeUpdate = tipoAllegatoRepository.findAll().size();

        // Create the TipoAllegato
        TipoAllegatoDTO tipoAllegatoDTO = tipoAllegatoMapper.toDto(tipoAllegato);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoAllegatoMockMvc.perform(put("/api/tipo-allegatoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoAllegatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoAllegato in the database
        List<TipoAllegato> tipoAllegatoList = tipoAllegatoRepository.findAll();
        assertThat(tipoAllegatoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TipoAllegato in Elasticsearch
        verify(mockTipoAllegatoSearchRepository, times(0)).save(tipoAllegato);
    }

    @Test
    @Transactional
    public void deleteTipoAllegato() throws Exception {
        // Initialize the database
        tipoAllegatoRepository.saveAndFlush(tipoAllegato);

        int databaseSizeBeforeDelete = tipoAllegatoRepository.findAll().size();

        // Delete the tipoAllegato
        restTipoAllegatoMockMvc.perform(delete("/api/tipo-allegatoes/{id}", tipoAllegato.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipoAllegato> tipoAllegatoList = tipoAllegatoRepository.findAll();
        assertThat(tipoAllegatoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TipoAllegato in Elasticsearch
        verify(mockTipoAllegatoSearchRepository, times(1)).deleteById(tipoAllegato.getId());
    }

    @Test
    @Transactional
    public void searchTipoAllegato() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        tipoAllegatoRepository.saveAndFlush(tipoAllegato);
        when(mockTipoAllegatoSearchRepository.search(queryStringQuery("id:" + tipoAllegato.getId())))
            .thenReturn(Collections.singletonList(tipoAllegato));

        // Search the tipoAllegato
        restTipoAllegatoMockMvc.perform(get("/api/_search/tipo-allegatoes?query=id:" + tipoAllegato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoAllegato.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].formatiAmmessi").value(hasItem(DEFAULT_FORMATI_AMMESSI)))
            .andExpect(jsonPath("$.[*].maxDimensioneAmmessa").value(hasItem(DEFAULT_MAX_DIMENSIONE_AMMESSA)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }
}
