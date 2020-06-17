package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.Organizzazione;
import it.maggioli.repository.OrganizzazioneRepository;
import it.maggioli.repository.search.OrganizzazioneSearchRepository;
import it.maggioli.service.OrganizzazioneService;
import it.maggioli.service.dto.OrganizzazioneDTO;
import it.maggioli.service.mapper.OrganizzazioneMapper;

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
 * Integration tests for the {@link OrganizzazioneResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class OrganizzazioneResourceIT {

    private static final Long DEFAULT_ID_PERSONA_REF = 1L;
    private static final Long UPDATED_ID_PERSONA_REF = 2L;

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    @Autowired
    private OrganizzazioneRepository organizzazioneRepository;

    @Autowired
    private OrganizzazioneMapper organizzazioneMapper;

    @Autowired
    private OrganizzazioneService organizzazioneService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.OrganizzazioneSearchRepositoryMockConfiguration
     */
    @Autowired
    private OrganizzazioneSearchRepository mockOrganizzazioneSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrganizzazioneMockMvc;

    private Organizzazione organizzazione;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Organizzazione createEntity(EntityManager em) {
        Organizzazione organizzazione = new Organizzazione()
            .idPersonaRef(DEFAULT_ID_PERSONA_REF)
            .nome(DEFAULT_NOME)
            .tipo(DEFAULT_TIPO);
        return organizzazione;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Organizzazione createUpdatedEntity(EntityManager em) {
        Organizzazione organizzazione = new Organizzazione()
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .nome(UPDATED_NOME)
            .tipo(UPDATED_TIPO);
        return organizzazione;
    }

    @BeforeEach
    public void initTest() {
        organizzazione = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrganizzazione() throws Exception {
        int databaseSizeBeforeCreate = organizzazioneRepository.findAll().size();
        // Create the Organizzazione
        OrganizzazioneDTO organizzazioneDTO = organizzazioneMapper.toDto(organizzazione);
        restOrganizzazioneMockMvc.perform(post("/api/organizzaziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizzazioneDTO)))
            .andExpect(status().isCreated());

        // Validate the Organizzazione in the database
        List<Organizzazione> organizzazioneList = organizzazioneRepository.findAll();
        assertThat(organizzazioneList).hasSize(databaseSizeBeforeCreate + 1);
        Organizzazione testOrganizzazione = organizzazioneList.get(organizzazioneList.size() - 1);
        assertThat(testOrganizzazione.getIdPersonaRef()).isEqualTo(DEFAULT_ID_PERSONA_REF);
        assertThat(testOrganizzazione.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testOrganizzazione.getTipo()).isEqualTo(DEFAULT_TIPO);

        // Validate the Organizzazione in Elasticsearch
        verify(mockOrganizzazioneSearchRepository, times(1)).save(testOrganizzazione);
    }

    @Test
    @Transactional
    public void createOrganizzazioneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = organizzazioneRepository.findAll().size();

        // Create the Organizzazione with an existing ID
        organizzazione.setId(1L);
        OrganizzazioneDTO organizzazioneDTO = organizzazioneMapper.toDto(organizzazione);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrganizzazioneMockMvc.perform(post("/api/organizzaziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizzazioneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Organizzazione in the database
        List<Organizzazione> organizzazioneList = organizzazioneRepository.findAll();
        assertThat(organizzazioneList).hasSize(databaseSizeBeforeCreate);

        // Validate the Organizzazione in Elasticsearch
        verify(mockOrganizzazioneSearchRepository, times(0)).save(organizzazione);
    }


    @Test
    @Transactional
    public void checkIdPersonaRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = organizzazioneRepository.findAll().size();
        // set the field null
        organizzazione.setIdPersonaRef(null);

        // Create the Organizzazione, which fails.
        OrganizzazioneDTO organizzazioneDTO = organizzazioneMapper.toDto(organizzazione);


        restOrganizzazioneMockMvc.perform(post("/api/organizzaziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizzazioneDTO)))
            .andExpect(status().isBadRequest());

        List<Organizzazione> organizzazioneList = organizzazioneRepository.findAll();
        assertThat(organizzazioneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrganizzaziones() throws Exception {
        // Initialize the database
        organizzazioneRepository.saveAndFlush(organizzazione);

        // Get all the organizzazioneList
        restOrganizzazioneMockMvc.perform(get("/api/organizzaziones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(organizzazione.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF.intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO)));
    }
    
    @Test
    @Transactional
    public void getOrganizzazione() throws Exception {
        // Initialize the database
        organizzazioneRepository.saveAndFlush(organizzazione);

        // Get the organizzazione
        restOrganizzazioneMockMvc.perform(get("/api/organizzaziones/{id}", organizzazione.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(organizzazione.getId().intValue()))
            .andExpect(jsonPath("$.idPersonaRef").value(DEFAULT_ID_PERSONA_REF.intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO));
    }
    @Test
    @Transactional
    public void getNonExistingOrganizzazione() throws Exception {
        // Get the organizzazione
        restOrganizzazioneMockMvc.perform(get("/api/organizzaziones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrganizzazione() throws Exception {
        // Initialize the database
        organizzazioneRepository.saveAndFlush(organizzazione);

        int databaseSizeBeforeUpdate = organizzazioneRepository.findAll().size();

        // Update the organizzazione
        Organizzazione updatedOrganizzazione = organizzazioneRepository.findById(organizzazione.getId()).get();
        // Disconnect from session so that the updates on updatedOrganizzazione are not directly saved in db
        em.detach(updatedOrganizzazione);
        updatedOrganizzazione
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .nome(UPDATED_NOME)
            .tipo(UPDATED_TIPO);
        OrganizzazioneDTO organizzazioneDTO = organizzazioneMapper.toDto(updatedOrganizzazione);

        restOrganizzazioneMockMvc.perform(put("/api/organizzaziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizzazioneDTO)))
            .andExpect(status().isOk());

        // Validate the Organizzazione in the database
        List<Organizzazione> organizzazioneList = organizzazioneRepository.findAll();
        assertThat(organizzazioneList).hasSize(databaseSizeBeforeUpdate);
        Organizzazione testOrganizzazione = organizzazioneList.get(organizzazioneList.size() - 1);
        assertThat(testOrganizzazione.getIdPersonaRef()).isEqualTo(UPDATED_ID_PERSONA_REF);
        assertThat(testOrganizzazione.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testOrganizzazione.getTipo()).isEqualTo(UPDATED_TIPO);

        // Validate the Organizzazione in Elasticsearch
        verify(mockOrganizzazioneSearchRepository, times(1)).save(testOrganizzazione);
    }

    @Test
    @Transactional
    public void updateNonExistingOrganizzazione() throws Exception {
        int databaseSizeBeforeUpdate = organizzazioneRepository.findAll().size();

        // Create the Organizzazione
        OrganizzazioneDTO organizzazioneDTO = organizzazioneMapper.toDto(organizzazione);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrganizzazioneMockMvc.perform(put("/api/organizzaziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(organizzazioneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Organizzazione in the database
        List<Organizzazione> organizzazioneList = organizzazioneRepository.findAll();
        assertThat(organizzazioneList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Organizzazione in Elasticsearch
        verify(mockOrganizzazioneSearchRepository, times(0)).save(organizzazione);
    }

    @Test
    @Transactional
    public void deleteOrganizzazione() throws Exception {
        // Initialize the database
        organizzazioneRepository.saveAndFlush(organizzazione);

        int databaseSizeBeforeDelete = organizzazioneRepository.findAll().size();

        // Delete the organizzazione
        restOrganizzazioneMockMvc.perform(delete("/api/organizzaziones/{id}", organizzazione.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Organizzazione> organizzazioneList = organizzazioneRepository.findAll();
        assertThat(organizzazioneList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Organizzazione in Elasticsearch
        verify(mockOrganizzazioneSearchRepository, times(1)).deleteById(organizzazione.getId());
    }

    @Test
    @Transactional
    public void searchOrganizzazione() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        organizzazioneRepository.saveAndFlush(organizzazione);
        when(mockOrganizzazioneSearchRepository.search(queryStringQuery("id:" + organizzazione.getId())))
            .thenReturn(Collections.singletonList(organizzazione));

        // Search the organizzazione
        restOrganizzazioneMockMvc.perform(get("/api/_search/organizzaziones?query=id:" + organizzazione.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(organizzazione.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF.intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO)));
    }
}
