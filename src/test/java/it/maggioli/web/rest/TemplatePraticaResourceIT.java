package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.TemplatePratica;
import it.maggioli.repository.TemplatePraticaRepository;
import it.maggioli.repository.search.TemplatePraticaSearchRepository;
import it.maggioli.service.TemplatePraticaService;
import it.maggioli.service.dto.TemplatePraticaDTO;
import it.maggioli.service.mapper.TemplatePraticaMapper;

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
 * Integration tests for the {@link TemplatePraticaResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class TemplatePraticaResourceIT {

    private static final Integer DEFAULT_ID_TEMPLATE_PRATICA = 8;
    private static final Integer UPDATED_ID_TEMPLATE_PRATICA = 7;

    private static final Integer DEFAULT_NOME_TEMPLATE = 1;
    private static final Integer UPDATED_NOME_TEMPLATE = 2;

    private static final Integer DEFAULT_ELENCO_TAG_AMBITO = 1;
    private static final Integer UPDATED_ELENCO_TAG_AMBITO = 2;

    @Autowired
    private TemplatePraticaRepository templatePraticaRepository;

    @Autowired
    private TemplatePraticaMapper templatePraticaMapper;

    @Autowired
    private TemplatePraticaService templatePraticaService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.TemplatePraticaSearchRepositoryMockConfiguration
     */
    @Autowired
    private TemplatePraticaSearchRepository mockTemplatePraticaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTemplatePraticaMockMvc;

    private TemplatePratica templatePratica;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TemplatePratica createEntity(EntityManager em) {
        TemplatePratica templatePratica = new TemplatePratica()
            .idTemplatePratica(DEFAULT_ID_TEMPLATE_PRATICA)
            .nomeTemplate(DEFAULT_NOME_TEMPLATE)
            .elencoTagAmbito(DEFAULT_ELENCO_TAG_AMBITO);
        return templatePratica;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TemplatePratica createUpdatedEntity(EntityManager em) {
        TemplatePratica templatePratica = new TemplatePratica()
            .idTemplatePratica(UPDATED_ID_TEMPLATE_PRATICA)
            .nomeTemplate(UPDATED_NOME_TEMPLATE)
            .elencoTagAmbito(UPDATED_ELENCO_TAG_AMBITO);
        return templatePratica;
    }

    @BeforeEach
    public void initTest() {
        templatePratica = createEntity(em);
    }

    @Test
    @Transactional
    public void createTemplatePratica() throws Exception {
        int databaseSizeBeforeCreate = templatePraticaRepository.findAll().size();
        // Create the TemplatePratica
        TemplatePraticaDTO templatePraticaDTO = templatePraticaMapper.toDto(templatePratica);
        restTemplatePraticaMockMvc.perform(post("/api/template-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(templatePraticaDTO)))
            .andExpect(status().isCreated());

        // Validate the TemplatePratica in the database
        List<TemplatePratica> templatePraticaList = templatePraticaRepository.findAll();
        assertThat(templatePraticaList).hasSize(databaseSizeBeforeCreate + 1);
        TemplatePratica testTemplatePratica = templatePraticaList.get(templatePraticaList.size() - 1);
        assertThat(testTemplatePratica.getIdTemplatePratica()).isEqualTo(DEFAULT_ID_TEMPLATE_PRATICA);
        assertThat(testTemplatePratica.getNomeTemplate()).isEqualTo(DEFAULT_NOME_TEMPLATE);
        assertThat(testTemplatePratica.getElencoTagAmbito()).isEqualTo(DEFAULT_ELENCO_TAG_AMBITO);

        // Validate the TemplatePratica in Elasticsearch
        verify(mockTemplatePraticaSearchRepository, times(1)).save(testTemplatePratica);
    }

    @Test
    @Transactional
    public void createTemplatePraticaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = templatePraticaRepository.findAll().size();

        // Create the TemplatePratica with an existing ID
        templatePratica.setId(1L);
        TemplatePraticaDTO templatePraticaDTO = templatePraticaMapper.toDto(templatePratica);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTemplatePraticaMockMvc.perform(post("/api/template-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(templatePraticaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TemplatePratica in the database
        List<TemplatePratica> templatePraticaList = templatePraticaRepository.findAll();
        assertThat(templatePraticaList).hasSize(databaseSizeBeforeCreate);

        // Validate the TemplatePratica in Elasticsearch
        verify(mockTemplatePraticaSearchRepository, times(0)).save(templatePratica);
    }


    @Test
    @Transactional
    public void checkIdTemplatePraticaIsRequired() throws Exception {
        int databaseSizeBeforeTest = templatePraticaRepository.findAll().size();
        // set the field null
        templatePratica.setIdTemplatePratica(null);

        // Create the TemplatePratica, which fails.
        TemplatePraticaDTO templatePraticaDTO = templatePraticaMapper.toDto(templatePratica);


        restTemplatePraticaMockMvc.perform(post("/api/template-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(templatePraticaDTO)))
            .andExpect(status().isBadRequest());

        List<TemplatePratica> templatePraticaList = templatePraticaRepository.findAll();
        assertThat(templatePraticaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTemplatePraticas() throws Exception {
        // Initialize the database
        templatePraticaRepository.saveAndFlush(templatePratica);

        // Get all the templatePraticaList
        restTemplatePraticaMockMvc.perform(get("/api/template-praticas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(templatePratica.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTemplatePratica").value(hasItem(DEFAULT_ID_TEMPLATE_PRATICA)))
            .andExpect(jsonPath("$.[*].nomeTemplate").value(hasItem(DEFAULT_NOME_TEMPLATE)))
            .andExpect(jsonPath("$.[*].elencoTagAmbito").value(hasItem(DEFAULT_ELENCO_TAG_AMBITO)));
    }
    
    @Test
    @Transactional
    public void getTemplatePratica() throws Exception {
        // Initialize the database
        templatePraticaRepository.saveAndFlush(templatePratica);

        // Get the templatePratica
        restTemplatePraticaMockMvc.perform(get("/api/template-praticas/{id}", templatePratica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(templatePratica.getId().intValue()))
            .andExpect(jsonPath("$.idTemplatePratica").value(DEFAULT_ID_TEMPLATE_PRATICA))
            .andExpect(jsonPath("$.nomeTemplate").value(DEFAULT_NOME_TEMPLATE))
            .andExpect(jsonPath("$.elencoTagAmbito").value(DEFAULT_ELENCO_TAG_AMBITO));
    }
    @Test
    @Transactional
    public void getNonExistingTemplatePratica() throws Exception {
        // Get the templatePratica
        restTemplatePraticaMockMvc.perform(get("/api/template-praticas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTemplatePratica() throws Exception {
        // Initialize the database
        templatePraticaRepository.saveAndFlush(templatePratica);

        int databaseSizeBeforeUpdate = templatePraticaRepository.findAll().size();

        // Update the templatePratica
        TemplatePratica updatedTemplatePratica = templatePraticaRepository.findById(templatePratica.getId()).get();
        // Disconnect from session so that the updates on updatedTemplatePratica are not directly saved in db
        em.detach(updatedTemplatePratica);
        updatedTemplatePratica
            .idTemplatePratica(UPDATED_ID_TEMPLATE_PRATICA)
            .nomeTemplate(UPDATED_NOME_TEMPLATE)
            .elencoTagAmbito(UPDATED_ELENCO_TAG_AMBITO);
        TemplatePraticaDTO templatePraticaDTO = templatePraticaMapper.toDto(updatedTemplatePratica);

        restTemplatePraticaMockMvc.perform(put("/api/template-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(templatePraticaDTO)))
            .andExpect(status().isOk());

        // Validate the TemplatePratica in the database
        List<TemplatePratica> templatePraticaList = templatePraticaRepository.findAll();
        assertThat(templatePraticaList).hasSize(databaseSizeBeforeUpdate);
        TemplatePratica testTemplatePratica = templatePraticaList.get(templatePraticaList.size() - 1);
        assertThat(testTemplatePratica.getIdTemplatePratica()).isEqualTo(UPDATED_ID_TEMPLATE_PRATICA);
        assertThat(testTemplatePratica.getNomeTemplate()).isEqualTo(UPDATED_NOME_TEMPLATE);
        assertThat(testTemplatePratica.getElencoTagAmbito()).isEqualTo(UPDATED_ELENCO_TAG_AMBITO);

        // Validate the TemplatePratica in Elasticsearch
        verify(mockTemplatePraticaSearchRepository, times(1)).save(testTemplatePratica);
    }

    @Test
    @Transactional
    public void updateNonExistingTemplatePratica() throws Exception {
        int databaseSizeBeforeUpdate = templatePraticaRepository.findAll().size();

        // Create the TemplatePratica
        TemplatePraticaDTO templatePraticaDTO = templatePraticaMapper.toDto(templatePratica);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTemplatePraticaMockMvc.perform(put("/api/template-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(templatePraticaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TemplatePratica in the database
        List<TemplatePratica> templatePraticaList = templatePraticaRepository.findAll();
        assertThat(templatePraticaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TemplatePratica in Elasticsearch
        verify(mockTemplatePraticaSearchRepository, times(0)).save(templatePratica);
    }

    @Test
    @Transactional
    public void deleteTemplatePratica() throws Exception {
        // Initialize the database
        templatePraticaRepository.saveAndFlush(templatePratica);

        int databaseSizeBeforeDelete = templatePraticaRepository.findAll().size();

        // Delete the templatePratica
        restTemplatePraticaMockMvc.perform(delete("/api/template-praticas/{id}", templatePratica.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TemplatePratica> templatePraticaList = templatePraticaRepository.findAll();
        assertThat(templatePraticaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TemplatePratica in Elasticsearch
        verify(mockTemplatePraticaSearchRepository, times(1)).deleteById(templatePratica.getId());
    }

    @Test
    @Transactional
    public void searchTemplatePratica() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        templatePraticaRepository.saveAndFlush(templatePratica);
        when(mockTemplatePraticaSearchRepository.search(queryStringQuery("id:" + templatePratica.getId())))
            .thenReturn(Collections.singletonList(templatePratica));

        // Search the templatePratica
        restTemplatePraticaMockMvc.perform(get("/api/_search/template-praticas?query=id:" + templatePratica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(templatePratica.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTemplatePratica").value(hasItem(DEFAULT_ID_TEMPLATE_PRATICA)))
            .andExpect(jsonPath("$.[*].nomeTemplate").value(hasItem(DEFAULT_NOME_TEMPLATE)))
            .andExpect(jsonPath("$.[*].elencoTagAmbito").value(hasItem(DEFAULT_ELENCO_TAG_AMBITO)));
    }
}
