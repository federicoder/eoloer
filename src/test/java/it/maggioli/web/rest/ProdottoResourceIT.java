package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.Prodotto;
import it.maggioli.repository.ProdottoRepository;
import it.maggioli.repository.search.ProdottoSearchRepository;
import it.maggioli.service.ProdottoService;
import it.maggioli.service.dto.ProdottoDTO;
import it.maggioli.service.mapper.ProdottoMapper;

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
 * Integration tests for the {@link ProdottoResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProdottoResourceIT {

    private static final Integer DEFAULT_ID_PRODOTTO = 1;
    private static final Integer UPDATED_ID_PRODOTTO = 2;

    private static final Integer DEFAULT_NUOVA_LICENZA = 1;
    private static final Integer UPDATED_NUOVA_LICENZA = 2;

    private static final Integer DEFAULT_RINNOVO_LICENZA = 1;
    private static final Integer UPDATED_RINNOVO_LICENZA = 2;

    private static final Integer DEFAULT_STORAGE = 1;
    private static final Integer UPDATED_STORAGE = 2;

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private ProdottoMapper prodottoMapper;

    @Autowired
    private ProdottoService prodottoService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.ProdottoSearchRepositoryMockConfiguration
     */
    @Autowired
    private ProdottoSearchRepository mockProdottoSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProdottoMockMvc;

    private Prodotto prodotto;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prodotto createEntity(EntityManager em) {
        Prodotto prodotto = new Prodotto()
            .idProdotto(DEFAULT_ID_PRODOTTO)
            .nuovaLicenza(DEFAULT_NUOVA_LICENZA)
            .rinnovoLicenza(DEFAULT_RINNOVO_LICENZA)
            .storage(DEFAULT_STORAGE);
        return prodotto;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prodotto createUpdatedEntity(EntityManager em) {
        Prodotto prodotto = new Prodotto()
            .idProdotto(UPDATED_ID_PRODOTTO)
            .nuovaLicenza(UPDATED_NUOVA_LICENZA)
            .rinnovoLicenza(UPDATED_RINNOVO_LICENZA)
            .storage(UPDATED_STORAGE);
        return prodotto;
    }

    @BeforeEach
    public void initTest() {
        prodotto = createEntity(em);
    }

    @Test
    @Transactional
    public void createProdotto() throws Exception {
        int databaseSizeBeforeCreate = prodottoRepository.findAll().size();
        // Create the Prodotto
        ProdottoDTO prodottoDTO = prodottoMapper.toDto(prodotto);
        restProdottoMockMvc.perform(post("/api/prodottos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prodottoDTO)))
            .andExpect(status().isCreated());

        // Validate the Prodotto in the database
        List<Prodotto> prodottoList = prodottoRepository.findAll();
        assertThat(prodottoList).hasSize(databaseSizeBeforeCreate + 1);
        Prodotto testProdotto = prodottoList.get(prodottoList.size() - 1);
        assertThat(testProdotto.getIdProdotto()).isEqualTo(DEFAULT_ID_PRODOTTO);
        assertThat(testProdotto.getNuovaLicenza()).isEqualTo(DEFAULT_NUOVA_LICENZA);
        assertThat(testProdotto.getRinnovoLicenza()).isEqualTo(DEFAULT_RINNOVO_LICENZA);
        assertThat(testProdotto.getStorage()).isEqualTo(DEFAULT_STORAGE);

        // Validate the Prodotto in Elasticsearch
        verify(mockProdottoSearchRepository, times(1)).save(testProdotto);
    }

    @Test
    @Transactional
    public void createProdottoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = prodottoRepository.findAll().size();

        // Create the Prodotto with an existing ID
        prodotto.setId(1L);
        ProdottoDTO prodottoDTO = prodottoMapper.toDto(prodotto);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProdottoMockMvc.perform(post("/api/prodottos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prodottoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prodotto in the database
        List<Prodotto> prodottoList = prodottoRepository.findAll();
        assertThat(prodottoList).hasSize(databaseSizeBeforeCreate);

        // Validate the Prodotto in Elasticsearch
        verify(mockProdottoSearchRepository, times(0)).save(prodotto);
    }


    @Test
    @Transactional
    public void checkIdProdottoIsRequired() throws Exception {
        int databaseSizeBeforeTest = prodottoRepository.findAll().size();
        // set the field null
        prodotto.setIdProdotto(null);

        // Create the Prodotto, which fails.
        ProdottoDTO prodottoDTO = prodottoMapper.toDto(prodotto);


        restProdottoMockMvc.perform(post("/api/prodottos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prodottoDTO)))
            .andExpect(status().isBadRequest());

        List<Prodotto> prodottoList = prodottoRepository.findAll();
        assertThat(prodottoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProdottos() throws Exception {
        // Initialize the database
        prodottoRepository.saveAndFlush(prodotto);

        // Get all the prodottoList
        restProdottoMockMvc.perform(get("/api/prodottos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prodotto.getId().intValue())))
            .andExpect(jsonPath("$.[*].idProdotto").value(hasItem(DEFAULT_ID_PRODOTTO)))
            .andExpect(jsonPath("$.[*].nuovaLicenza").value(hasItem(DEFAULT_NUOVA_LICENZA)))
            .andExpect(jsonPath("$.[*].rinnovoLicenza").value(hasItem(DEFAULT_RINNOVO_LICENZA)))
            .andExpect(jsonPath("$.[*].storage").value(hasItem(DEFAULT_STORAGE)));
    }
    
    @Test
    @Transactional
    public void getProdotto() throws Exception {
        // Initialize the database
        prodottoRepository.saveAndFlush(prodotto);

        // Get the prodotto
        restProdottoMockMvc.perform(get("/api/prodottos/{id}", prodotto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(prodotto.getId().intValue()))
            .andExpect(jsonPath("$.idProdotto").value(DEFAULT_ID_PRODOTTO))
            .andExpect(jsonPath("$.nuovaLicenza").value(DEFAULT_NUOVA_LICENZA))
            .andExpect(jsonPath("$.rinnovoLicenza").value(DEFAULT_RINNOVO_LICENZA))
            .andExpect(jsonPath("$.storage").value(DEFAULT_STORAGE));
    }
    @Test
    @Transactional
    public void getNonExistingProdotto() throws Exception {
        // Get the prodotto
        restProdottoMockMvc.perform(get("/api/prodottos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProdotto() throws Exception {
        // Initialize the database
        prodottoRepository.saveAndFlush(prodotto);

        int databaseSizeBeforeUpdate = prodottoRepository.findAll().size();

        // Update the prodotto
        Prodotto updatedProdotto = prodottoRepository.findById(prodotto.getId()).get();
        // Disconnect from session so that the updates on updatedProdotto are not directly saved in db
        em.detach(updatedProdotto);
        updatedProdotto
            .idProdotto(UPDATED_ID_PRODOTTO)
            .nuovaLicenza(UPDATED_NUOVA_LICENZA)
            .rinnovoLicenza(UPDATED_RINNOVO_LICENZA)
            .storage(UPDATED_STORAGE);
        ProdottoDTO prodottoDTO = prodottoMapper.toDto(updatedProdotto);

        restProdottoMockMvc.perform(put("/api/prodottos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prodottoDTO)))
            .andExpect(status().isOk());

        // Validate the Prodotto in the database
        List<Prodotto> prodottoList = prodottoRepository.findAll();
        assertThat(prodottoList).hasSize(databaseSizeBeforeUpdate);
        Prodotto testProdotto = prodottoList.get(prodottoList.size() - 1);
        assertThat(testProdotto.getIdProdotto()).isEqualTo(UPDATED_ID_PRODOTTO);
        assertThat(testProdotto.getNuovaLicenza()).isEqualTo(UPDATED_NUOVA_LICENZA);
        assertThat(testProdotto.getRinnovoLicenza()).isEqualTo(UPDATED_RINNOVO_LICENZA);
        assertThat(testProdotto.getStorage()).isEqualTo(UPDATED_STORAGE);

        // Validate the Prodotto in Elasticsearch
        verify(mockProdottoSearchRepository, times(1)).save(testProdotto);
    }

    @Test
    @Transactional
    public void updateNonExistingProdotto() throws Exception {
        int databaseSizeBeforeUpdate = prodottoRepository.findAll().size();

        // Create the Prodotto
        ProdottoDTO prodottoDTO = prodottoMapper.toDto(prodotto);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProdottoMockMvc.perform(put("/api/prodottos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(prodottoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prodotto in the database
        List<Prodotto> prodottoList = prodottoRepository.findAll();
        assertThat(prodottoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Prodotto in Elasticsearch
        verify(mockProdottoSearchRepository, times(0)).save(prodotto);
    }

    @Test
    @Transactional
    public void deleteProdotto() throws Exception {
        // Initialize the database
        prodottoRepository.saveAndFlush(prodotto);

        int databaseSizeBeforeDelete = prodottoRepository.findAll().size();

        // Delete the prodotto
        restProdottoMockMvc.perform(delete("/api/prodottos/{id}", prodotto.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Prodotto> prodottoList = prodottoRepository.findAll();
        assertThat(prodottoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Prodotto in Elasticsearch
        verify(mockProdottoSearchRepository, times(1)).deleteById(prodotto.getId());
    }

    @Test
    @Transactional
    public void searchProdotto() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        prodottoRepository.saveAndFlush(prodotto);
        when(mockProdottoSearchRepository.search(queryStringQuery("id:" + prodotto.getId())))
            .thenReturn(Collections.singletonList(prodotto));

        // Search the prodotto
        restProdottoMockMvc.perform(get("/api/_search/prodottos?query=id:" + prodotto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prodotto.getId().intValue())))
            .andExpect(jsonPath("$.[*].idProdotto").value(hasItem(DEFAULT_ID_PRODOTTO)))
            .andExpect(jsonPath("$.[*].nuovaLicenza").value(hasItem(DEFAULT_NUOVA_LICENZA)))
            .andExpect(jsonPath("$.[*].rinnovoLicenza").value(hasItem(DEFAULT_RINNOVO_LICENZA)))
            .andExpect(jsonPath("$.[*].storage").value(hasItem(DEFAULT_STORAGE)));
    }
}
