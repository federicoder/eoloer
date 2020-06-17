package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.NotaPratica;
import it.maggioli.repository.NotaPraticaRepository;
import it.maggioli.repository.search.NotaPraticaSearchRepository;
import it.maggioli.service.NotaPraticaService;
import it.maggioli.service.dto.NotaPraticaDTO;
import it.maggioli.service.mapper.NotaPraticaMapper;

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
 * Integration tests for the {@link NotaPraticaResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class NotaPraticaResourceIT {

    private static final Integer DEFAULT_ID_NOTA_PRATICA = 8;
    private static final Integer UPDATED_ID_NOTA_PRATICA = 7;

    private static final Integer DEFAULT_ID_PRATICA_REF = 8;
    private static final Integer UPDATED_ID_PRATICA_REF = 7;

    private static final String DEFAULT_DATA = "AAAAAAAAAA";
    private static final String UPDATED_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_NOTA = "AAAAAAAAAA";
    private static final String UPDATED_NOTA = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    @Autowired
    private NotaPraticaRepository notaPraticaRepository;

    @Autowired
    private NotaPraticaMapper notaPraticaMapper;

    @Autowired
    private NotaPraticaService notaPraticaService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.NotaPraticaSearchRepositoryMockConfiguration
     */
    @Autowired
    private NotaPraticaSearchRepository mockNotaPraticaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNotaPraticaMockMvc;

    private NotaPratica notaPratica;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotaPratica createEntity(EntityManager em) {
        NotaPratica notaPratica = new NotaPratica()
            .idNotaPratica(DEFAULT_ID_NOTA_PRATICA)
            .idPraticaRef(DEFAULT_ID_PRATICA_REF)
            .data(DEFAULT_DATA)
            .nota(DEFAULT_NOTA)
            .version(DEFAULT_VERSION);
        return notaPratica;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotaPratica createUpdatedEntity(EntityManager em) {
        NotaPratica notaPratica = new NotaPratica()
            .idNotaPratica(UPDATED_ID_NOTA_PRATICA)
            .idPraticaRef(UPDATED_ID_PRATICA_REF)
            .data(UPDATED_DATA)
            .nota(UPDATED_NOTA)
            .version(UPDATED_VERSION);
        return notaPratica;
    }

    @BeforeEach
    public void initTest() {
        notaPratica = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotaPratica() throws Exception {
        int databaseSizeBeforeCreate = notaPraticaRepository.findAll().size();
        // Create the NotaPratica
        NotaPraticaDTO notaPraticaDTO = notaPraticaMapper.toDto(notaPratica);
        restNotaPraticaMockMvc.perform(post("/api/nota-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notaPraticaDTO)))
            .andExpect(status().isCreated());

        // Validate the NotaPratica in the database
        List<NotaPratica> notaPraticaList = notaPraticaRepository.findAll();
        assertThat(notaPraticaList).hasSize(databaseSizeBeforeCreate + 1);
        NotaPratica testNotaPratica = notaPraticaList.get(notaPraticaList.size() - 1);
        assertThat(testNotaPratica.getIdNotaPratica()).isEqualTo(DEFAULT_ID_NOTA_PRATICA);
        assertThat(testNotaPratica.getIdPraticaRef()).isEqualTo(DEFAULT_ID_PRATICA_REF);
        assertThat(testNotaPratica.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testNotaPratica.getNota()).isEqualTo(DEFAULT_NOTA);
        assertThat(testNotaPratica.getVersion()).isEqualTo(DEFAULT_VERSION);

        // Validate the NotaPratica in Elasticsearch
        verify(mockNotaPraticaSearchRepository, times(1)).save(testNotaPratica);
    }

    @Test
    @Transactional
    public void createNotaPraticaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notaPraticaRepository.findAll().size();

        // Create the NotaPratica with an existing ID
        notaPratica.setId(1L);
        NotaPraticaDTO notaPraticaDTO = notaPraticaMapper.toDto(notaPratica);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotaPraticaMockMvc.perform(post("/api/nota-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notaPraticaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotaPratica in the database
        List<NotaPratica> notaPraticaList = notaPraticaRepository.findAll();
        assertThat(notaPraticaList).hasSize(databaseSizeBeforeCreate);

        // Validate the NotaPratica in Elasticsearch
        verify(mockNotaPraticaSearchRepository, times(0)).save(notaPratica);
    }


    @Test
    @Transactional
    public void checkIdNotaPraticaIsRequired() throws Exception {
        int databaseSizeBeforeTest = notaPraticaRepository.findAll().size();
        // set the field null
        notaPratica.setIdNotaPratica(null);

        // Create the NotaPratica, which fails.
        NotaPraticaDTO notaPraticaDTO = notaPraticaMapper.toDto(notaPratica);


        restNotaPraticaMockMvc.perform(post("/api/nota-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notaPraticaDTO)))
            .andExpect(status().isBadRequest());

        List<NotaPratica> notaPraticaList = notaPraticaRepository.findAll();
        assertThat(notaPraticaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNotaPraticas() throws Exception {
        // Initialize the database
        notaPraticaRepository.saveAndFlush(notaPratica);

        // Get all the notaPraticaList
        restNotaPraticaMockMvc.perform(get("/api/nota-praticas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notaPratica.getId().intValue())))
            .andExpect(jsonPath("$.[*].idNotaPratica").value(hasItem(DEFAULT_ID_NOTA_PRATICA)))
            .andExpect(jsonPath("$.[*].idPraticaRef").value(hasItem(DEFAULT_ID_PRATICA_REF)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA)))
            .andExpect(jsonPath("$.[*].nota").value(hasItem(DEFAULT_NOTA)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }
    
    @Test
    @Transactional
    public void getNotaPratica() throws Exception {
        // Initialize the database
        notaPraticaRepository.saveAndFlush(notaPratica);

        // Get the notaPratica
        restNotaPraticaMockMvc.perform(get("/api/nota-praticas/{id}", notaPratica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notaPratica.getId().intValue()))
            .andExpect(jsonPath("$.idNotaPratica").value(DEFAULT_ID_NOTA_PRATICA))
            .andExpect(jsonPath("$.idPraticaRef").value(DEFAULT_ID_PRATICA_REF))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA))
            .andExpect(jsonPath("$.nota").value(DEFAULT_NOTA))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION));
    }
    @Test
    @Transactional
    public void getNonExistingNotaPratica() throws Exception {
        // Get the notaPratica
        restNotaPraticaMockMvc.perform(get("/api/nota-praticas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotaPratica() throws Exception {
        // Initialize the database
        notaPraticaRepository.saveAndFlush(notaPratica);

        int databaseSizeBeforeUpdate = notaPraticaRepository.findAll().size();

        // Update the notaPratica
        NotaPratica updatedNotaPratica = notaPraticaRepository.findById(notaPratica.getId()).get();
        // Disconnect from session so that the updates on updatedNotaPratica are not directly saved in db
        em.detach(updatedNotaPratica);
        updatedNotaPratica
            .idNotaPratica(UPDATED_ID_NOTA_PRATICA)
            .idPraticaRef(UPDATED_ID_PRATICA_REF)
            .data(UPDATED_DATA)
            .nota(UPDATED_NOTA)
            .version(UPDATED_VERSION);
        NotaPraticaDTO notaPraticaDTO = notaPraticaMapper.toDto(updatedNotaPratica);

        restNotaPraticaMockMvc.perform(put("/api/nota-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notaPraticaDTO)))
            .andExpect(status().isOk());

        // Validate the NotaPratica in the database
        List<NotaPratica> notaPraticaList = notaPraticaRepository.findAll();
        assertThat(notaPraticaList).hasSize(databaseSizeBeforeUpdate);
        NotaPratica testNotaPratica = notaPraticaList.get(notaPraticaList.size() - 1);
        assertThat(testNotaPratica.getIdNotaPratica()).isEqualTo(UPDATED_ID_NOTA_PRATICA);
        assertThat(testNotaPratica.getIdPraticaRef()).isEqualTo(UPDATED_ID_PRATICA_REF);
        assertThat(testNotaPratica.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testNotaPratica.getNota()).isEqualTo(UPDATED_NOTA);
        assertThat(testNotaPratica.getVersion()).isEqualTo(UPDATED_VERSION);

        // Validate the NotaPratica in Elasticsearch
        verify(mockNotaPraticaSearchRepository, times(1)).save(testNotaPratica);
    }

    @Test
    @Transactional
    public void updateNonExistingNotaPratica() throws Exception {
        int databaseSizeBeforeUpdate = notaPraticaRepository.findAll().size();

        // Create the NotaPratica
        NotaPraticaDTO notaPraticaDTO = notaPraticaMapper.toDto(notaPratica);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotaPraticaMockMvc.perform(put("/api/nota-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notaPraticaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotaPratica in the database
        List<NotaPratica> notaPraticaList = notaPraticaRepository.findAll();
        assertThat(notaPraticaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the NotaPratica in Elasticsearch
        verify(mockNotaPraticaSearchRepository, times(0)).save(notaPratica);
    }

    @Test
    @Transactional
    public void deleteNotaPratica() throws Exception {
        // Initialize the database
        notaPraticaRepository.saveAndFlush(notaPratica);

        int databaseSizeBeforeDelete = notaPraticaRepository.findAll().size();

        // Delete the notaPratica
        restNotaPraticaMockMvc.perform(delete("/api/nota-praticas/{id}", notaPratica.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NotaPratica> notaPraticaList = notaPraticaRepository.findAll();
        assertThat(notaPraticaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the NotaPratica in Elasticsearch
        verify(mockNotaPraticaSearchRepository, times(1)).deleteById(notaPratica.getId());
    }

    @Test
    @Transactional
    public void searchNotaPratica() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        notaPraticaRepository.saveAndFlush(notaPratica);
        when(mockNotaPraticaSearchRepository.search(queryStringQuery("id:" + notaPratica.getId())))
            .thenReturn(Collections.singletonList(notaPratica));

        // Search the notaPratica
        restNotaPraticaMockMvc.perform(get("/api/_search/nota-praticas?query=id:" + notaPratica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notaPratica.getId().intValue())))
            .andExpect(jsonPath("$.[*].idNotaPratica").value(hasItem(DEFAULT_ID_NOTA_PRATICA)))
            .andExpect(jsonPath("$.[*].idPraticaRef").value(hasItem(DEFAULT_ID_PRATICA_REF)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA)))
            .andExpect(jsonPath("$.[*].nota").value(hasItem(DEFAULT_NOTA)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }
}
