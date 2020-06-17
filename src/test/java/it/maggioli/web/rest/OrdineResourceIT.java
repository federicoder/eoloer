package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.Ordine;
import it.maggioli.repository.OrdineRepository;
import it.maggioli.repository.search.OrdineSearchRepository;
import it.maggioli.service.OrdineService;
import it.maggioli.service.dto.OrdineDTO;
import it.maggioli.service.mapper.OrdineMapper;

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
 * Integration tests for the {@link OrdineResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class OrdineResourceIT {

    private static final Integer DEFAULT_ID_ORDINE = 1;
    private static final Integer UPDATED_ID_ORDINE = 2;

    private static final Integer DEFAULT_ID_STUDIO_PROFESSIONALE_REF = 1;
    private static final Integer UPDATED_ID_STUDIO_PROFESSIONALE_REF = 2;

    private static final Integer DEFAULT_STATO_ORDINE = 1;
    private static final Integer UPDATED_STATO_ORDINE = 2;

    private static final Integer DEFAULT_TOT_IMPONIBILE = 1;
    private static final Integer UPDATED_TOT_IMPONIBILE = 2;

    private static final Integer DEFAULT_TOT_IVA = 1;
    private static final Integer UPDATED_TOT_IVA = 2;

    private static final Integer DEFAULT_TOT_ORDINE = 1;
    private static final Integer UPDATED_TOT_ORDINE = 2;

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private OrdineMapper ordineMapper;

    @Autowired
    private OrdineService ordineService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.OrdineSearchRepositoryMockConfiguration
     */
    @Autowired
    private OrdineSearchRepository mockOrdineSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrdineMockMvc;

    private Ordine ordine;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ordine createEntity(EntityManager em) {
        Ordine ordine = new Ordine()
            .idOrdine(DEFAULT_ID_ORDINE)
            .idStudioProfessionaleRef(DEFAULT_ID_STUDIO_PROFESSIONALE_REF)
            .statoOrdine(DEFAULT_STATO_ORDINE)
            .totImponibile(DEFAULT_TOT_IMPONIBILE)
            .totIva(DEFAULT_TOT_IVA)
            .totOrdine(DEFAULT_TOT_ORDINE);
        return ordine;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ordine createUpdatedEntity(EntityManager em) {
        Ordine ordine = new Ordine()
            .idOrdine(UPDATED_ID_ORDINE)
            .idStudioProfessionaleRef(UPDATED_ID_STUDIO_PROFESSIONALE_REF)
            .statoOrdine(UPDATED_STATO_ORDINE)
            .totImponibile(UPDATED_TOT_IMPONIBILE)
            .totIva(UPDATED_TOT_IVA)
            .totOrdine(UPDATED_TOT_ORDINE);
        return ordine;
    }

    @BeforeEach
    public void initTest() {
        ordine = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrdine() throws Exception {
        int databaseSizeBeforeCreate = ordineRepository.findAll().size();
        // Create the Ordine
        OrdineDTO ordineDTO = ordineMapper.toDto(ordine);
        restOrdineMockMvc.perform(post("/api/ordines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordineDTO)))
            .andExpect(status().isCreated());

        // Validate the Ordine in the database
        List<Ordine> ordineList = ordineRepository.findAll();
        assertThat(ordineList).hasSize(databaseSizeBeforeCreate + 1);
        Ordine testOrdine = ordineList.get(ordineList.size() - 1);
        assertThat(testOrdine.getIdOrdine()).isEqualTo(DEFAULT_ID_ORDINE);
        assertThat(testOrdine.getIdStudioProfessionaleRef()).isEqualTo(DEFAULT_ID_STUDIO_PROFESSIONALE_REF);
        assertThat(testOrdine.getStatoOrdine()).isEqualTo(DEFAULT_STATO_ORDINE);
        assertThat(testOrdine.getTotImponibile()).isEqualTo(DEFAULT_TOT_IMPONIBILE);
        assertThat(testOrdine.getTotIva()).isEqualTo(DEFAULT_TOT_IVA);
        assertThat(testOrdine.getTotOrdine()).isEqualTo(DEFAULT_TOT_ORDINE);

        // Validate the Ordine in Elasticsearch
        verify(mockOrdineSearchRepository, times(1)).save(testOrdine);
    }

    @Test
    @Transactional
    public void createOrdineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ordineRepository.findAll().size();

        // Create the Ordine with an existing ID
        ordine.setId(1L);
        OrdineDTO ordineDTO = ordineMapper.toDto(ordine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrdineMockMvc.perform(post("/api/ordines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ordine in the database
        List<Ordine> ordineList = ordineRepository.findAll();
        assertThat(ordineList).hasSize(databaseSizeBeforeCreate);

        // Validate the Ordine in Elasticsearch
        verify(mockOrdineSearchRepository, times(0)).save(ordine);
    }


    @Test
    @Transactional
    public void checkIdOrdineIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordineRepository.findAll().size();
        // set the field null
        ordine.setIdOrdine(null);

        // Create the Ordine, which fails.
        OrdineDTO ordineDTO = ordineMapper.toDto(ordine);


        restOrdineMockMvc.perform(post("/api/ordines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordineDTO)))
            .andExpect(status().isBadRequest());

        List<Ordine> ordineList = ordineRepository.findAll();
        assertThat(ordineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdStudioProfessionaleRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = ordineRepository.findAll().size();
        // set the field null
        ordine.setIdStudioProfessionaleRef(null);

        // Create the Ordine, which fails.
        OrdineDTO ordineDTO = ordineMapper.toDto(ordine);


        restOrdineMockMvc.perform(post("/api/ordines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordineDTO)))
            .andExpect(status().isBadRequest());

        List<Ordine> ordineList = ordineRepository.findAll();
        assertThat(ordineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOrdines() throws Exception {
        // Initialize the database
        ordineRepository.saveAndFlush(ordine);

        // Get all the ordineList
        restOrdineMockMvc.perform(get("/api/ordines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ordine.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOrdine").value(hasItem(DEFAULT_ID_ORDINE)))
            .andExpect(jsonPath("$.[*].idStudioProfessionaleRef").value(hasItem(DEFAULT_ID_STUDIO_PROFESSIONALE_REF)))
            .andExpect(jsonPath("$.[*].statoOrdine").value(hasItem(DEFAULT_STATO_ORDINE)))
            .andExpect(jsonPath("$.[*].totImponibile").value(hasItem(DEFAULT_TOT_IMPONIBILE)))
            .andExpect(jsonPath("$.[*].totIva").value(hasItem(DEFAULT_TOT_IVA)))
            .andExpect(jsonPath("$.[*].totOrdine").value(hasItem(DEFAULT_TOT_ORDINE)));
    }
    
    @Test
    @Transactional
    public void getOrdine() throws Exception {
        // Initialize the database
        ordineRepository.saveAndFlush(ordine);

        // Get the ordine
        restOrdineMockMvc.perform(get("/api/ordines/{id}", ordine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ordine.getId().intValue()))
            .andExpect(jsonPath("$.idOrdine").value(DEFAULT_ID_ORDINE))
            .andExpect(jsonPath("$.idStudioProfessionaleRef").value(DEFAULT_ID_STUDIO_PROFESSIONALE_REF))
            .andExpect(jsonPath("$.statoOrdine").value(DEFAULT_STATO_ORDINE))
            .andExpect(jsonPath("$.totImponibile").value(DEFAULT_TOT_IMPONIBILE))
            .andExpect(jsonPath("$.totIva").value(DEFAULT_TOT_IVA))
            .andExpect(jsonPath("$.totOrdine").value(DEFAULT_TOT_ORDINE));
    }
    @Test
    @Transactional
    public void getNonExistingOrdine() throws Exception {
        // Get the ordine
        restOrdineMockMvc.perform(get("/api/ordines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrdine() throws Exception {
        // Initialize the database
        ordineRepository.saveAndFlush(ordine);

        int databaseSizeBeforeUpdate = ordineRepository.findAll().size();

        // Update the ordine
        Ordine updatedOrdine = ordineRepository.findById(ordine.getId()).get();
        // Disconnect from session so that the updates on updatedOrdine are not directly saved in db
        em.detach(updatedOrdine);
        updatedOrdine
            .idOrdine(UPDATED_ID_ORDINE)
            .idStudioProfessionaleRef(UPDATED_ID_STUDIO_PROFESSIONALE_REF)
            .statoOrdine(UPDATED_STATO_ORDINE)
            .totImponibile(UPDATED_TOT_IMPONIBILE)
            .totIva(UPDATED_TOT_IVA)
            .totOrdine(UPDATED_TOT_ORDINE);
        OrdineDTO ordineDTO = ordineMapper.toDto(updatedOrdine);

        restOrdineMockMvc.perform(put("/api/ordines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordineDTO)))
            .andExpect(status().isOk());

        // Validate the Ordine in the database
        List<Ordine> ordineList = ordineRepository.findAll();
        assertThat(ordineList).hasSize(databaseSizeBeforeUpdate);
        Ordine testOrdine = ordineList.get(ordineList.size() - 1);
        assertThat(testOrdine.getIdOrdine()).isEqualTo(UPDATED_ID_ORDINE);
        assertThat(testOrdine.getIdStudioProfessionaleRef()).isEqualTo(UPDATED_ID_STUDIO_PROFESSIONALE_REF);
        assertThat(testOrdine.getStatoOrdine()).isEqualTo(UPDATED_STATO_ORDINE);
        assertThat(testOrdine.getTotImponibile()).isEqualTo(UPDATED_TOT_IMPONIBILE);
        assertThat(testOrdine.getTotIva()).isEqualTo(UPDATED_TOT_IVA);
        assertThat(testOrdine.getTotOrdine()).isEqualTo(UPDATED_TOT_ORDINE);

        // Validate the Ordine in Elasticsearch
        verify(mockOrdineSearchRepository, times(1)).save(testOrdine);
    }

    @Test
    @Transactional
    public void updateNonExistingOrdine() throws Exception {
        int databaseSizeBeforeUpdate = ordineRepository.findAll().size();

        // Create the Ordine
        OrdineDTO ordineDTO = ordineMapper.toDto(ordine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrdineMockMvc.perform(put("/api/ordines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ordine in the database
        List<Ordine> ordineList = ordineRepository.findAll();
        assertThat(ordineList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Ordine in Elasticsearch
        verify(mockOrdineSearchRepository, times(0)).save(ordine);
    }

    @Test
    @Transactional
    public void deleteOrdine() throws Exception {
        // Initialize the database
        ordineRepository.saveAndFlush(ordine);

        int databaseSizeBeforeDelete = ordineRepository.findAll().size();

        // Delete the ordine
        restOrdineMockMvc.perform(delete("/api/ordines/{id}", ordine.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ordine> ordineList = ordineRepository.findAll();
        assertThat(ordineList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Ordine in Elasticsearch
        verify(mockOrdineSearchRepository, times(1)).deleteById(ordine.getId());
    }

    @Test
    @Transactional
    public void searchOrdine() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        ordineRepository.saveAndFlush(ordine);
        when(mockOrdineSearchRepository.search(queryStringQuery("id:" + ordine.getId())))
            .thenReturn(Collections.singletonList(ordine));

        // Search the ordine
        restOrdineMockMvc.perform(get("/api/_search/ordines?query=id:" + ordine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ordine.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOrdine").value(hasItem(DEFAULT_ID_ORDINE)))
            .andExpect(jsonPath("$.[*].idStudioProfessionaleRef").value(hasItem(DEFAULT_ID_STUDIO_PROFESSIONALE_REF)))
            .andExpect(jsonPath("$.[*].statoOrdine").value(hasItem(DEFAULT_STATO_ORDINE)))
            .andExpect(jsonPath("$.[*].totImponibile").value(hasItem(DEFAULT_TOT_IMPONIBILE)))
            .andExpect(jsonPath("$.[*].totIva").value(hasItem(DEFAULT_TOT_IVA)))
            .andExpect(jsonPath("$.[*].totOrdine").value(hasItem(DEFAULT_TOT_ORDINE)));
    }
}
