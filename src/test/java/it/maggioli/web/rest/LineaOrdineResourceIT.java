package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.LineaOrdine;
import it.maggioli.repository.LineaOrdineRepository;
import it.maggioli.repository.search.LineaOrdineSearchRepository;
import it.maggioli.service.LineaOrdineService;
import it.maggioli.service.dto.LineaOrdineDTO;
import it.maggioli.service.mapper.LineaOrdineMapper;

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
 * Integration tests for the {@link LineaOrdineResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class LineaOrdineResourceIT {

    private static final Integer DEFAULT_ID_ORDINE_REF = 1;
    private static final Integer UPDATED_ID_ORDINE_REF = 2;

    private static final Integer DEFAULT_ID_PRODOTTO_REF = 1;
    private static final Integer UPDATED_ID_PRODOTTO_REF = 2;

    private static final Integer DEFAULT_QUANTITA = 1;
    private static final Integer UPDATED_QUANTITA = 2;

    private static final Integer DEFAULT_IMPORTO = 1;
    private static final Integer UPDATED_IMPORTO = 2;

    private static final String DEFAULT_COD_IVA = "AAAAAAAAAA";
    private static final String UPDATED_COD_IVA = "BBBBBBBBBB";

    @Autowired
    private LineaOrdineRepository lineaOrdineRepository;

    @Autowired
    private LineaOrdineMapper lineaOrdineMapper;

    @Autowired
    private LineaOrdineService lineaOrdineService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.LineaOrdineSearchRepositoryMockConfiguration
     */
    @Autowired
    private LineaOrdineSearchRepository mockLineaOrdineSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLineaOrdineMockMvc;

    private LineaOrdine lineaOrdine;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LineaOrdine createEntity(EntityManager em) {
        LineaOrdine lineaOrdine = new LineaOrdine()
            .idOrdineRef(DEFAULT_ID_ORDINE_REF)
            .idProdottoRef(DEFAULT_ID_PRODOTTO_REF)
            .quantita(DEFAULT_QUANTITA)
            .importo(DEFAULT_IMPORTO)
            .codIva(DEFAULT_COD_IVA);
        return lineaOrdine;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LineaOrdine createUpdatedEntity(EntityManager em) {
        LineaOrdine lineaOrdine = new LineaOrdine()
            .idOrdineRef(UPDATED_ID_ORDINE_REF)
            .idProdottoRef(UPDATED_ID_PRODOTTO_REF)
            .quantita(UPDATED_QUANTITA)
            .importo(UPDATED_IMPORTO)
            .codIva(UPDATED_COD_IVA);
        return lineaOrdine;
    }

    @BeforeEach
    public void initTest() {
        lineaOrdine = createEntity(em);
    }

    @Test
    @Transactional
    public void createLineaOrdine() throws Exception {
        int databaseSizeBeforeCreate = lineaOrdineRepository.findAll().size();
        // Create the LineaOrdine
        LineaOrdineDTO lineaOrdineDTO = lineaOrdineMapper.toDto(lineaOrdine);
        restLineaOrdineMockMvc.perform(post("/api/linea-ordines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lineaOrdineDTO)))
            .andExpect(status().isCreated());

        // Validate the LineaOrdine in the database
        List<LineaOrdine> lineaOrdineList = lineaOrdineRepository.findAll();
        assertThat(lineaOrdineList).hasSize(databaseSizeBeforeCreate + 1);
        LineaOrdine testLineaOrdine = lineaOrdineList.get(lineaOrdineList.size() - 1);
        assertThat(testLineaOrdine.getIdOrdineRef()).isEqualTo(DEFAULT_ID_ORDINE_REF);
        assertThat(testLineaOrdine.getIdProdottoRef()).isEqualTo(DEFAULT_ID_PRODOTTO_REF);
        assertThat(testLineaOrdine.getQuantita()).isEqualTo(DEFAULT_QUANTITA);
        assertThat(testLineaOrdine.getImporto()).isEqualTo(DEFAULT_IMPORTO);
        assertThat(testLineaOrdine.getCodIva()).isEqualTo(DEFAULT_COD_IVA);

        // Validate the LineaOrdine in Elasticsearch
        verify(mockLineaOrdineSearchRepository, times(1)).save(testLineaOrdine);
    }

    @Test
    @Transactional
    public void createLineaOrdineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lineaOrdineRepository.findAll().size();

        // Create the LineaOrdine with an existing ID
        lineaOrdine.setId(1L);
        LineaOrdineDTO lineaOrdineDTO = lineaOrdineMapper.toDto(lineaOrdine);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLineaOrdineMockMvc.perform(post("/api/linea-ordines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lineaOrdineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LineaOrdine in the database
        List<LineaOrdine> lineaOrdineList = lineaOrdineRepository.findAll();
        assertThat(lineaOrdineList).hasSize(databaseSizeBeforeCreate);

        // Validate the LineaOrdine in Elasticsearch
        verify(mockLineaOrdineSearchRepository, times(0)).save(lineaOrdine);
    }


    @Test
    @Transactional
    public void checkIdOrdineRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = lineaOrdineRepository.findAll().size();
        // set the field null
        lineaOrdine.setIdOrdineRef(null);

        // Create the LineaOrdine, which fails.
        LineaOrdineDTO lineaOrdineDTO = lineaOrdineMapper.toDto(lineaOrdine);


        restLineaOrdineMockMvc.perform(post("/api/linea-ordines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lineaOrdineDTO)))
            .andExpect(status().isBadRequest());

        List<LineaOrdine> lineaOrdineList = lineaOrdineRepository.findAll();
        assertThat(lineaOrdineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdProdottoRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = lineaOrdineRepository.findAll().size();
        // set the field null
        lineaOrdine.setIdProdottoRef(null);

        // Create the LineaOrdine, which fails.
        LineaOrdineDTO lineaOrdineDTO = lineaOrdineMapper.toDto(lineaOrdine);


        restLineaOrdineMockMvc.perform(post("/api/linea-ordines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lineaOrdineDTO)))
            .andExpect(status().isBadRequest());

        List<LineaOrdine> lineaOrdineList = lineaOrdineRepository.findAll();
        assertThat(lineaOrdineList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLineaOrdines() throws Exception {
        // Initialize the database
        lineaOrdineRepository.saveAndFlush(lineaOrdine);

        // Get all the lineaOrdineList
        restLineaOrdineMockMvc.perform(get("/api/linea-ordines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lineaOrdine.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOrdineRef").value(hasItem(DEFAULT_ID_ORDINE_REF)))
            .andExpect(jsonPath("$.[*].idProdottoRef").value(hasItem(DEFAULT_ID_PRODOTTO_REF)))
            .andExpect(jsonPath("$.[*].quantita").value(hasItem(DEFAULT_QUANTITA)))
            .andExpect(jsonPath("$.[*].importo").value(hasItem(DEFAULT_IMPORTO)))
            .andExpect(jsonPath("$.[*].codIva").value(hasItem(DEFAULT_COD_IVA)));
    }
    
    @Test
    @Transactional
    public void getLineaOrdine() throws Exception {
        // Initialize the database
        lineaOrdineRepository.saveAndFlush(lineaOrdine);

        // Get the lineaOrdine
        restLineaOrdineMockMvc.perform(get("/api/linea-ordines/{id}", lineaOrdine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lineaOrdine.getId().intValue()))
            .andExpect(jsonPath("$.idOrdineRef").value(DEFAULT_ID_ORDINE_REF))
            .andExpect(jsonPath("$.idProdottoRef").value(DEFAULT_ID_PRODOTTO_REF))
            .andExpect(jsonPath("$.quantita").value(DEFAULT_QUANTITA))
            .andExpect(jsonPath("$.importo").value(DEFAULT_IMPORTO))
            .andExpect(jsonPath("$.codIva").value(DEFAULT_COD_IVA));
    }
    @Test
    @Transactional
    public void getNonExistingLineaOrdine() throws Exception {
        // Get the lineaOrdine
        restLineaOrdineMockMvc.perform(get("/api/linea-ordines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLineaOrdine() throws Exception {
        // Initialize the database
        lineaOrdineRepository.saveAndFlush(lineaOrdine);

        int databaseSizeBeforeUpdate = lineaOrdineRepository.findAll().size();

        // Update the lineaOrdine
        LineaOrdine updatedLineaOrdine = lineaOrdineRepository.findById(lineaOrdine.getId()).get();
        // Disconnect from session so that the updates on updatedLineaOrdine are not directly saved in db
        em.detach(updatedLineaOrdine);
        updatedLineaOrdine
            .idOrdineRef(UPDATED_ID_ORDINE_REF)
            .idProdottoRef(UPDATED_ID_PRODOTTO_REF)
            .quantita(UPDATED_QUANTITA)
            .importo(UPDATED_IMPORTO)
            .codIva(UPDATED_COD_IVA);
        LineaOrdineDTO lineaOrdineDTO = lineaOrdineMapper.toDto(updatedLineaOrdine);

        restLineaOrdineMockMvc.perform(put("/api/linea-ordines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lineaOrdineDTO)))
            .andExpect(status().isOk());

        // Validate the LineaOrdine in the database
        List<LineaOrdine> lineaOrdineList = lineaOrdineRepository.findAll();
        assertThat(lineaOrdineList).hasSize(databaseSizeBeforeUpdate);
        LineaOrdine testLineaOrdine = lineaOrdineList.get(lineaOrdineList.size() - 1);
        assertThat(testLineaOrdine.getIdOrdineRef()).isEqualTo(UPDATED_ID_ORDINE_REF);
        assertThat(testLineaOrdine.getIdProdottoRef()).isEqualTo(UPDATED_ID_PRODOTTO_REF);
        assertThat(testLineaOrdine.getQuantita()).isEqualTo(UPDATED_QUANTITA);
        assertThat(testLineaOrdine.getImporto()).isEqualTo(UPDATED_IMPORTO);
        assertThat(testLineaOrdine.getCodIva()).isEqualTo(UPDATED_COD_IVA);

        // Validate the LineaOrdine in Elasticsearch
        verify(mockLineaOrdineSearchRepository, times(1)).save(testLineaOrdine);
    }

    @Test
    @Transactional
    public void updateNonExistingLineaOrdine() throws Exception {
        int databaseSizeBeforeUpdate = lineaOrdineRepository.findAll().size();

        // Create the LineaOrdine
        LineaOrdineDTO lineaOrdineDTO = lineaOrdineMapper.toDto(lineaOrdine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLineaOrdineMockMvc.perform(put("/api/linea-ordines")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lineaOrdineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LineaOrdine in the database
        List<LineaOrdine> lineaOrdineList = lineaOrdineRepository.findAll();
        assertThat(lineaOrdineList).hasSize(databaseSizeBeforeUpdate);

        // Validate the LineaOrdine in Elasticsearch
        verify(mockLineaOrdineSearchRepository, times(0)).save(lineaOrdine);
    }

    @Test
    @Transactional
    public void deleteLineaOrdine() throws Exception {
        // Initialize the database
        lineaOrdineRepository.saveAndFlush(lineaOrdine);

        int databaseSizeBeforeDelete = lineaOrdineRepository.findAll().size();

        // Delete the lineaOrdine
        restLineaOrdineMockMvc.perform(delete("/api/linea-ordines/{id}", lineaOrdine.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LineaOrdine> lineaOrdineList = lineaOrdineRepository.findAll();
        assertThat(lineaOrdineList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the LineaOrdine in Elasticsearch
        verify(mockLineaOrdineSearchRepository, times(1)).deleteById(lineaOrdine.getId());
    }

    @Test
    @Transactional
    public void searchLineaOrdine() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        lineaOrdineRepository.saveAndFlush(lineaOrdine);
        when(mockLineaOrdineSearchRepository.search(queryStringQuery("id:" + lineaOrdine.getId())))
            .thenReturn(Collections.singletonList(lineaOrdine));

        // Search the lineaOrdine
        restLineaOrdineMockMvc.perform(get("/api/_search/linea-ordines?query=id:" + lineaOrdine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lineaOrdine.getId().intValue())))
            .andExpect(jsonPath("$.[*].idOrdineRef").value(hasItem(DEFAULT_ID_ORDINE_REF)))
            .andExpect(jsonPath("$.[*].idProdottoRef").value(hasItem(DEFAULT_ID_PRODOTTO_REF)))
            .andExpect(jsonPath("$.[*].quantita").value(hasItem(DEFAULT_QUANTITA)))
            .andExpect(jsonPath("$.[*].importo").value(hasItem(DEFAULT_IMPORTO)))
            .andExpect(jsonPath("$.[*].codIva").value(hasItem(DEFAULT_COD_IVA)));
    }
}
