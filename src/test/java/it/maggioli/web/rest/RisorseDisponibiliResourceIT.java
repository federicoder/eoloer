package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.RisorseDisponibili;
import it.maggioli.repository.RisorseDisponibiliRepository;
import it.maggioli.repository.search.RisorseDisponibiliSearchRepository;
import it.maggioli.service.RisorseDisponibiliService;
import it.maggioli.service.dto.RisorseDisponibiliDTO;
import it.maggioli.service.mapper.RisorseDisponibiliMapper;

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
 * Integration tests for the {@link RisorseDisponibiliResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class RisorseDisponibiliResourceIT {

    private static final Integer DEFAULT_ID_STUDIO_PROFESSIONALE_REF = 1;
    private static final Integer UPDATED_ID_STUDIO_PROFESSIONALE_REF = 2;

    private static final String DEFAULT_DATA_ATTIVAZIONE_LICENZA = "AAAAAAAAAA";
    private static final String UPDATED_DATA_ATTIVAZIONE_LICENZA = "BBBBBBBBBB";

    private static final Integer DEFAULT_NR_LICENZA = 1;
    private static final Integer UPDATED_NR_LICENZA = 2;

    private static final Integer DEFAULT_STORAGE_TOTALE = 1;
    private static final Integer UPDATED_STORAGE_TOTALE = 2;

    @Autowired
    private RisorseDisponibiliRepository risorseDisponibiliRepository;

    @Autowired
    private RisorseDisponibiliMapper risorseDisponibiliMapper;

    @Autowired
    private RisorseDisponibiliService risorseDisponibiliService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.RisorseDisponibiliSearchRepositoryMockConfiguration
     */
    @Autowired
    private RisorseDisponibiliSearchRepository mockRisorseDisponibiliSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRisorseDisponibiliMockMvc;

    private RisorseDisponibili risorseDisponibili;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RisorseDisponibili createEntity(EntityManager em) {
        RisorseDisponibili risorseDisponibili = new RisorseDisponibili()
            .idStudioProfessionaleRef(DEFAULT_ID_STUDIO_PROFESSIONALE_REF)
            .dataAttivazioneLicenza(DEFAULT_DATA_ATTIVAZIONE_LICENZA)
            .nrLicenza(DEFAULT_NR_LICENZA)
            .storageTotale(DEFAULT_STORAGE_TOTALE);
        return risorseDisponibili;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RisorseDisponibili createUpdatedEntity(EntityManager em) {
        RisorseDisponibili risorseDisponibili = new RisorseDisponibili()
            .idStudioProfessionaleRef(UPDATED_ID_STUDIO_PROFESSIONALE_REF)
            .dataAttivazioneLicenza(UPDATED_DATA_ATTIVAZIONE_LICENZA)
            .nrLicenza(UPDATED_NR_LICENZA)
            .storageTotale(UPDATED_STORAGE_TOTALE);
        return risorseDisponibili;
    }

    @BeforeEach
    public void initTest() {
        risorseDisponibili = createEntity(em);
    }

    @Test
    @Transactional
    public void createRisorseDisponibili() throws Exception {
        int databaseSizeBeforeCreate = risorseDisponibiliRepository.findAll().size();
        // Create the RisorseDisponibili
        RisorseDisponibiliDTO risorseDisponibiliDTO = risorseDisponibiliMapper.toDto(risorseDisponibili);
        restRisorseDisponibiliMockMvc.perform(post("/api/risorse-disponibilis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(risorseDisponibiliDTO)))
            .andExpect(status().isCreated());

        // Validate the RisorseDisponibili in the database
        List<RisorseDisponibili> risorseDisponibiliList = risorseDisponibiliRepository.findAll();
        assertThat(risorseDisponibiliList).hasSize(databaseSizeBeforeCreate + 1);
        RisorseDisponibili testRisorseDisponibili = risorseDisponibiliList.get(risorseDisponibiliList.size() - 1);
        assertThat(testRisorseDisponibili.getIdStudioProfessionaleRef()).isEqualTo(DEFAULT_ID_STUDIO_PROFESSIONALE_REF);
        assertThat(testRisorseDisponibili.getDataAttivazioneLicenza()).isEqualTo(DEFAULT_DATA_ATTIVAZIONE_LICENZA);
        assertThat(testRisorseDisponibili.getNrLicenza()).isEqualTo(DEFAULT_NR_LICENZA);
        assertThat(testRisorseDisponibili.getStorageTotale()).isEqualTo(DEFAULT_STORAGE_TOTALE);

        // Validate the RisorseDisponibili in Elasticsearch
        verify(mockRisorseDisponibiliSearchRepository, times(1)).save(testRisorseDisponibili);
    }

    @Test
    @Transactional
    public void createRisorseDisponibiliWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = risorseDisponibiliRepository.findAll().size();

        // Create the RisorseDisponibili with an existing ID
        risorseDisponibili.setId(1L);
        RisorseDisponibiliDTO risorseDisponibiliDTO = risorseDisponibiliMapper.toDto(risorseDisponibili);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRisorseDisponibiliMockMvc.perform(post("/api/risorse-disponibilis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(risorseDisponibiliDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RisorseDisponibili in the database
        List<RisorseDisponibili> risorseDisponibiliList = risorseDisponibiliRepository.findAll();
        assertThat(risorseDisponibiliList).hasSize(databaseSizeBeforeCreate);

        // Validate the RisorseDisponibili in Elasticsearch
        verify(mockRisorseDisponibiliSearchRepository, times(0)).save(risorseDisponibili);
    }


    @Test
    @Transactional
    public void checkIdStudioProfessionaleRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = risorseDisponibiliRepository.findAll().size();
        // set the field null
        risorseDisponibili.setIdStudioProfessionaleRef(null);

        // Create the RisorseDisponibili, which fails.
        RisorseDisponibiliDTO risorseDisponibiliDTO = risorseDisponibiliMapper.toDto(risorseDisponibili);


        restRisorseDisponibiliMockMvc.perform(post("/api/risorse-disponibilis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(risorseDisponibiliDTO)))
            .andExpect(status().isBadRequest());

        List<RisorseDisponibili> risorseDisponibiliList = risorseDisponibiliRepository.findAll();
        assertThat(risorseDisponibiliList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRisorseDisponibilis() throws Exception {
        // Initialize the database
        risorseDisponibiliRepository.saveAndFlush(risorseDisponibili);

        // Get all the risorseDisponibiliList
        restRisorseDisponibiliMockMvc.perform(get("/api/risorse-disponibilis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(risorseDisponibili.getId().intValue())))
            .andExpect(jsonPath("$.[*].idStudioProfessionaleRef").value(hasItem(DEFAULT_ID_STUDIO_PROFESSIONALE_REF)))
            .andExpect(jsonPath("$.[*].dataAttivazioneLicenza").value(hasItem(DEFAULT_DATA_ATTIVAZIONE_LICENZA)))
            .andExpect(jsonPath("$.[*].nrLicenza").value(hasItem(DEFAULT_NR_LICENZA)))
            .andExpect(jsonPath("$.[*].storageTotale").value(hasItem(DEFAULT_STORAGE_TOTALE)));
    }
    
    @Test
    @Transactional
    public void getRisorseDisponibili() throws Exception {
        // Initialize the database
        risorseDisponibiliRepository.saveAndFlush(risorseDisponibili);

        // Get the risorseDisponibili
        restRisorseDisponibiliMockMvc.perform(get("/api/risorse-disponibilis/{id}", risorseDisponibili.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(risorseDisponibili.getId().intValue()))
            .andExpect(jsonPath("$.idStudioProfessionaleRef").value(DEFAULT_ID_STUDIO_PROFESSIONALE_REF))
            .andExpect(jsonPath("$.dataAttivazioneLicenza").value(DEFAULT_DATA_ATTIVAZIONE_LICENZA))
            .andExpect(jsonPath("$.nrLicenza").value(DEFAULT_NR_LICENZA))
            .andExpect(jsonPath("$.storageTotale").value(DEFAULT_STORAGE_TOTALE));
    }
    @Test
    @Transactional
    public void getNonExistingRisorseDisponibili() throws Exception {
        // Get the risorseDisponibili
        restRisorseDisponibiliMockMvc.perform(get("/api/risorse-disponibilis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRisorseDisponibili() throws Exception {
        // Initialize the database
        risorseDisponibiliRepository.saveAndFlush(risorseDisponibili);

        int databaseSizeBeforeUpdate = risorseDisponibiliRepository.findAll().size();

        // Update the risorseDisponibili
        RisorseDisponibili updatedRisorseDisponibili = risorseDisponibiliRepository.findById(risorseDisponibili.getId()).get();
        // Disconnect from session so that the updates on updatedRisorseDisponibili are not directly saved in db
        em.detach(updatedRisorseDisponibili);
        updatedRisorseDisponibili
            .idStudioProfessionaleRef(UPDATED_ID_STUDIO_PROFESSIONALE_REF)
            .dataAttivazioneLicenza(UPDATED_DATA_ATTIVAZIONE_LICENZA)
            .nrLicenza(UPDATED_NR_LICENZA)
            .storageTotale(UPDATED_STORAGE_TOTALE);
        RisorseDisponibiliDTO risorseDisponibiliDTO = risorseDisponibiliMapper.toDto(updatedRisorseDisponibili);

        restRisorseDisponibiliMockMvc.perform(put("/api/risorse-disponibilis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(risorseDisponibiliDTO)))
            .andExpect(status().isOk());

        // Validate the RisorseDisponibili in the database
        List<RisorseDisponibili> risorseDisponibiliList = risorseDisponibiliRepository.findAll();
        assertThat(risorseDisponibiliList).hasSize(databaseSizeBeforeUpdate);
        RisorseDisponibili testRisorseDisponibili = risorseDisponibiliList.get(risorseDisponibiliList.size() - 1);
        assertThat(testRisorseDisponibili.getIdStudioProfessionaleRef()).isEqualTo(UPDATED_ID_STUDIO_PROFESSIONALE_REF);
        assertThat(testRisorseDisponibili.getDataAttivazioneLicenza()).isEqualTo(UPDATED_DATA_ATTIVAZIONE_LICENZA);
        assertThat(testRisorseDisponibili.getNrLicenza()).isEqualTo(UPDATED_NR_LICENZA);
        assertThat(testRisorseDisponibili.getStorageTotale()).isEqualTo(UPDATED_STORAGE_TOTALE);

        // Validate the RisorseDisponibili in Elasticsearch
        verify(mockRisorseDisponibiliSearchRepository, times(1)).save(testRisorseDisponibili);
    }

    @Test
    @Transactional
    public void updateNonExistingRisorseDisponibili() throws Exception {
        int databaseSizeBeforeUpdate = risorseDisponibiliRepository.findAll().size();

        // Create the RisorseDisponibili
        RisorseDisponibiliDTO risorseDisponibiliDTO = risorseDisponibiliMapper.toDto(risorseDisponibili);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRisorseDisponibiliMockMvc.perform(put("/api/risorse-disponibilis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(risorseDisponibiliDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RisorseDisponibili in the database
        List<RisorseDisponibili> risorseDisponibiliList = risorseDisponibiliRepository.findAll();
        assertThat(risorseDisponibiliList).hasSize(databaseSizeBeforeUpdate);

        // Validate the RisorseDisponibili in Elasticsearch
        verify(mockRisorseDisponibiliSearchRepository, times(0)).save(risorseDisponibili);
    }

    @Test
    @Transactional
    public void deleteRisorseDisponibili() throws Exception {
        // Initialize the database
        risorseDisponibiliRepository.saveAndFlush(risorseDisponibili);

        int databaseSizeBeforeDelete = risorseDisponibiliRepository.findAll().size();

        // Delete the risorseDisponibili
        restRisorseDisponibiliMockMvc.perform(delete("/api/risorse-disponibilis/{id}", risorseDisponibili.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RisorseDisponibili> risorseDisponibiliList = risorseDisponibiliRepository.findAll();
        assertThat(risorseDisponibiliList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the RisorseDisponibili in Elasticsearch
        verify(mockRisorseDisponibiliSearchRepository, times(1)).deleteById(risorseDisponibili.getId());
    }

    @Test
    @Transactional
    public void searchRisorseDisponibili() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        risorseDisponibiliRepository.saveAndFlush(risorseDisponibili);
        when(mockRisorseDisponibiliSearchRepository.search(queryStringQuery("id:" + risorseDisponibili.getId())))
            .thenReturn(Collections.singletonList(risorseDisponibili));

        // Search the risorseDisponibili
        restRisorseDisponibiliMockMvc.perform(get("/api/_search/risorse-disponibilis?query=id:" + risorseDisponibili.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(risorseDisponibili.getId().intValue())))
            .andExpect(jsonPath("$.[*].idStudioProfessionaleRef").value(hasItem(DEFAULT_ID_STUDIO_PROFESSIONALE_REF)))
            .andExpect(jsonPath("$.[*].dataAttivazioneLicenza").value(hasItem(DEFAULT_DATA_ATTIVAZIONE_LICENZA)))
            .andExpect(jsonPath("$.[*].nrLicenza").value(hasItem(DEFAULT_NR_LICENZA)))
            .andExpect(jsonPath("$.[*].storageTotale").value(hasItem(DEFAULT_STORAGE_TOTALE)));
    }
}
