package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.Pratica;
import it.maggioli.repository.PraticaRepository;
import it.maggioli.repository.search.PraticaSearchRepository;
import it.maggioli.service.PraticaService;
import it.maggioli.service.dto.PraticaDTO;
import it.maggioli.service.mapper.PraticaMapper;

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
 * Integration tests for the {@link PraticaResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class PraticaResourceIT {

    private static final Integer DEFAULT_ID_PRATICA = 8;
    private static final Integer UPDATED_ID_PRATICA = 7;

    private static final Integer DEFAULT_ID_STUDIO_PROFESSIONALE_REF = 8;
    private static final Integer UPDATED_ID_STUDIO_PROFESSIONALE_REF = 7;

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_APERTURA = "AAAAAAAAAA";
    private static final String UPDATED_DATA_APERTURA = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_CHIUSURA = "AAAAAAAAAA";
    private static final String UPDATED_DATA_CHIUSURA = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_SCADENZA = "AAAAAAAAAA";
    private static final String UPDATED_DATA_SCADENZA = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATO = 1;
    private static final Integer UPDATED_STATO = 2;

    private static final String DEFAULT_MOTIVO_CHIUSURA = "AAAAAAAAAA";
    private static final String UPDATED_MOTIVO_CHIUSURA = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_TITOLARE = 8;
    private static final Integer UPDATED_ID_TITOLARE = 7;

    private static final Integer DEFAULT_PRC_AVANZATO = 1;
    private static final Integer UPDATED_PRC_AVANZATO = 2;

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_VALUTA = "AAAAAAAAAA";
    private static final String UPDATED_VALUTA = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_TEMPLATE_PRATICA_REF = 1;
    private static final Integer UPDATED_ID_TEMPLATE_PRATICA_REF = 2;

    @Autowired
    private PraticaRepository praticaRepository;

    @Autowired
    private PraticaMapper praticaMapper;

    @Autowired
    private PraticaService praticaService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.PraticaSearchRepositoryMockConfiguration
     */
    @Autowired
    private PraticaSearchRepository mockPraticaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPraticaMockMvc;

    private Pratica pratica;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pratica createEntity(EntityManager em) {
        Pratica pratica = new Pratica()
            .idPratica(DEFAULT_ID_PRATICA)
            .idStudioProfessionaleRef(DEFAULT_ID_STUDIO_PROFESSIONALE_REF)
            .numero(DEFAULT_NUMERO)
            .nome(DEFAULT_NOME)
            .dataApertura(DEFAULT_DATA_APERTURA)
            .dataChiusura(DEFAULT_DATA_CHIUSURA)
            .dataScadenza(DEFAULT_DATA_SCADENZA)
            .stato(DEFAULT_STATO)
            .motivoChiusura(DEFAULT_MOTIVO_CHIUSURA)
            .idTitolare(DEFAULT_ID_TITOLARE)
            .prcAvanzato(DEFAULT_PRC_AVANZATO)
            .version(DEFAULT_VERSION)
            .valuta(DEFAULT_VALUTA)
            .idTemplatePraticaRef(DEFAULT_ID_TEMPLATE_PRATICA_REF);
        return pratica;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pratica createUpdatedEntity(EntityManager em) {
        Pratica pratica = new Pratica()
            .idPratica(UPDATED_ID_PRATICA)
            .idStudioProfessionaleRef(UPDATED_ID_STUDIO_PROFESSIONALE_REF)
            .numero(UPDATED_NUMERO)
            .nome(UPDATED_NOME)
            .dataApertura(UPDATED_DATA_APERTURA)
            .dataChiusura(UPDATED_DATA_CHIUSURA)
            .dataScadenza(UPDATED_DATA_SCADENZA)
            .stato(UPDATED_STATO)
            .motivoChiusura(UPDATED_MOTIVO_CHIUSURA)
            .idTitolare(UPDATED_ID_TITOLARE)
            .prcAvanzato(UPDATED_PRC_AVANZATO)
            .version(UPDATED_VERSION)
            .valuta(UPDATED_VALUTA)
            .idTemplatePraticaRef(UPDATED_ID_TEMPLATE_PRATICA_REF);
        return pratica;
    }

    @BeforeEach
    public void initTest() {
        pratica = createEntity(em);
    }

    @Test
    @Transactional
    public void createPratica() throws Exception {
        int databaseSizeBeforeCreate = praticaRepository.findAll().size();
        // Create the Pratica
        PraticaDTO praticaDTO = praticaMapper.toDto(pratica);
        restPraticaMockMvc.perform(post("/api/praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(praticaDTO)))
            .andExpect(status().isCreated());

        // Validate the Pratica in the database
        List<Pratica> praticaList = praticaRepository.findAll();
        assertThat(praticaList).hasSize(databaseSizeBeforeCreate + 1);
        Pratica testPratica = praticaList.get(praticaList.size() - 1);
        assertThat(testPratica.getIdPratica()).isEqualTo(DEFAULT_ID_PRATICA);
        assertThat(testPratica.getIdStudioProfessionaleRef()).isEqualTo(DEFAULT_ID_STUDIO_PROFESSIONALE_REF);
        assertThat(testPratica.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testPratica.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testPratica.getDataApertura()).isEqualTo(DEFAULT_DATA_APERTURA);
        assertThat(testPratica.getDataChiusura()).isEqualTo(DEFAULT_DATA_CHIUSURA);
        assertThat(testPratica.getDataScadenza()).isEqualTo(DEFAULT_DATA_SCADENZA);
        assertThat(testPratica.getStato()).isEqualTo(DEFAULT_STATO);
        assertThat(testPratica.getMotivoChiusura()).isEqualTo(DEFAULT_MOTIVO_CHIUSURA);
        assertThat(testPratica.getIdTitolare()).isEqualTo(DEFAULT_ID_TITOLARE);
        assertThat(testPratica.getPrcAvanzato()).isEqualTo(DEFAULT_PRC_AVANZATO);
        assertThat(testPratica.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testPratica.getValuta()).isEqualTo(DEFAULT_VALUTA);
        assertThat(testPratica.getIdTemplatePraticaRef()).isEqualTo(DEFAULT_ID_TEMPLATE_PRATICA_REF);

        // Validate the Pratica in Elasticsearch
        verify(mockPraticaSearchRepository, times(1)).save(testPratica);
    }

    @Test
    @Transactional
    public void createPraticaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = praticaRepository.findAll().size();

        // Create the Pratica with an existing ID
        pratica.setId(1L);
        PraticaDTO praticaDTO = praticaMapper.toDto(pratica);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPraticaMockMvc.perform(post("/api/praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(praticaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pratica in the database
        List<Pratica> praticaList = praticaRepository.findAll();
        assertThat(praticaList).hasSize(databaseSizeBeforeCreate);

        // Validate the Pratica in Elasticsearch
        verify(mockPraticaSearchRepository, times(0)).save(pratica);
    }


    @Test
    @Transactional
    public void getAllPraticas() throws Exception {
        // Initialize the database
        praticaRepository.saveAndFlush(pratica);

        // Get all the praticaList
        restPraticaMockMvc.perform(get("/api/praticas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pratica.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPratica").value(hasItem(DEFAULT_ID_PRATICA)))
            .andExpect(jsonPath("$.[*].idStudioProfessionaleRef").value(hasItem(DEFAULT_ID_STUDIO_PROFESSIONALE_REF)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].dataApertura").value(hasItem(DEFAULT_DATA_APERTURA)))
            .andExpect(jsonPath("$.[*].dataChiusura").value(hasItem(DEFAULT_DATA_CHIUSURA)))
            .andExpect(jsonPath("$.[*].dataScadenza").value(hasItem(DEFAULT_DATA_SCADENZA)))
            .andExpect(jsonPath("$.[*].stato").value(hasItem(DEFAULT_STATO)))
            .andExpect(jsonPath("$.[*].motivoChiusura").value(hasItem(DEFAULT_MOTIVO_CHIUSURA)))
            .andExpect(jsonPath("$.[*].idTitolare").value(hasItem(DEFAULT_ID_TITOLARE)))
            .andExpect(jsonPath("$.[*].prcAvanzato").value(hasItem(DEFAULT_PRC_AVANZATO)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].valuta").value(hasItem(DEFAULT_VALUTA)))
            .andExpect(jsonPath("$.[*].idTemplatePraticaRef").value(hasItem(DEFAULT_ID_TEMPLATE_PRATICA_REF)));
    }
    
    @Test
    @Transactional
    public void getPratica() throws Exception {
        // Initialize the database
        praticaRepository.saveAndFlush(pratica);

        // Get the pratica
        restPraticaMockMvc.perform(get("/api/praticas/{id}", pratica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pratica.getId().intValue()))
            .andExpect(jsonPath("$.idPratica").value(DEFAULT_ID_PRATICA))
            .andExpect(jsonPath("$.idStudioProfessionaleRef").value(DEFAULT_ID_STUDIO_PROFESSIONALE_REF))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.dataApertura").value(DEFAULT_DATA_APERTURA))
            .andExpect(jsonPath("$.dataChiusura").value(DEFAULT_DATA_CHIUSURA))
            .andExpect(jsonPath("$.dataScadenza").value(DEFAULT_DATA_SCADENZA))
            .andExpect(jsonPath("$.stato").value(DEFAULT_STATO))
            .andExpect(jsonPath("$.motivoChiusura").value(DEFAULT_MOTIVO_CHIUSURA))
            .andExpect(jsonPath("$.idTitolare").value(DEFAULT_ID_TITOLARE))
            .andExpect(jsonPath("$.prcAvanzato").value(DEFAULT_PRC_AVANZATO))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.valuta").value(DEFAULT_VALUTA))
            .andExpect(jsonPath("$.idTemplatePraticaRef").value(DEFAULT_ID_TEMPLATE_PRATICA_REF));
    }
    @Test
    @Transactional
    public void getNonExistingPratica() throws Exception {
        // Get the pratica
        restPraticaMockMvc.perform(get("/api/praticas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePratica() throws Exception {
        // Initialize the database
        praticaRepository.saveAndFlush(pratica);

        int databaseSizeBeforeUpdate = praticaRepository.findAll().size();

        // Update the pratica
        Pratica updatedPratica = praticaRepository.findById(pratica.getId()).get();
        // Disconnect from session so that the updates on updatedPratica are not directly saved in db
        em.detach(updatedPratica);
        updatedPratica
            .idPratica(UPDATED_ID_PRATICA)
            .idStudioProfessionaleRef(UPDATED_ID_STUDIO_PROFESSIONALE_REF)
            .numero(UPDATED_NUMERO)
            .nome(UPDATED_NOME)
            .dataApertura(UPDATED_DATA_APERTURA)
            .dataChiusura(UPDATED_DATA_CHIUSURA)
            .dataScadenza(UPDATED_DATA_SCADENZA)
            .stato(UPDATED_STATO)
            .motivoChiusura(UPDATED_MOTIVO_CHIUSURA)
            .idTitolare(UPDATED_ID_TITOLARE)
            .prcAvanzato(UPDATED_PRC_AVANZATO)
            .version(UPDATED_VERSION)
            .valuta(UPDATED_VALUTA)
            .idTemplatePraticaRef(UPDATED_ID_TEMPLATE_PRATICA_REF);
        PraticaDTO praticaDTO = praticaMapper.toDto(updatedPratica);

        restPraticaMockMvc.perform(put("/api/praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(praticaDTO)))
            .andExpect(status().isOk());

        // Validate the Pratica in the database
        List<Pratica> praticaList = praticaRepository.findAll();
        assertThat(praticaList).hasSize(databaseSizeBeforeUpdate);
        Pratica testPratica = praticaList.get(praticaList.size() - 1);
        assertThat(testPratica.getIdPratica()).isEqualTo(UPDATED_ID_PRATICA);
        assertThat(testPratica.getIdStudioProfessionaleRef()).isEqualTo(UPDATED_ID_STUDIO_PROFESSIONALE_REF);
        assertThat(testPratica.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testPratica.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testPratica.getDataApertura()).isEqualTo(UPDATED_DATA_APERTURA);
        assertThat(testPratica.getDataChiusura()).isEqualTo(UPDATED_DATA_CHIUSURA);
        assertThat(testPratica.getDataScadenza()).isEqualTo(UPDATED_DATA_SCADENZA);
        assertThat(testPratica.getStato()).isEqualTo(UPDATED_STATO);
        assertThat(testPratica.getMotivoChiusura()).isEqualTo(UPDATED_MOTIVO_CHIUSURA);
        assertThat(testPratica.getIdTitolare()).isEqualTo(UPDATED_ID_TITOLARE);
        assertThat(testPratica.getPrcAvanzato()).isEqualTo(UPDATED_PRC_AVANZATO);
        assertThat(testPratica.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testPratica.getValuta()).isEqualTo(UPDATED_VALUTA);
        assertThat(testPratica.getIdTemplatePraticaRef()).isEqualTo(UPDATED_ID_TEMPLATE_PRATICA_REF);

        // Validate the Pratica in Elasticsearch
        verify(mockPraticaSearchRepository, times(1)).save(testPratica);
    }

    @Test
    @Transactional
    public void updateNonExistingPratica() throws Exception {
        int databaseSizeBeforeUpdate = praticaRepository.findAll().size();

        // Create the Pratica
        PraticaDTO praticaDTO = praticaMapper.toDto(pratica);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPraticaMockMvc.perform(put("/api/praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(praticaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pratica in the database
        List<Pratica> praticaList = praticaRepository.findAll();
        assertThat(praticaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Pratica in Elasticsearch
        verify(mockPraticaSearchRepository, times(0)).save(pratica);
    }

    @Test
    @Transactional
    public void deletePratica() throws Exception {
        // Initialize the database
        praticaRepository.saveAndFlush(pratica);

        int databaseSizeBeforeDelete = praticaRepository.findAll().size();

        // Delete the pratica
        restPraticaMockMvc.perform(delete("/api/praticas/{id}", pratica.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Pratica> praticaList = praticaRepository.findAll();
        assertThat(praticaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Pratica in Elasticsearch
        verify(mockPraticaSearchRepository, times(1)).deleteById(pratica.getId());
    }

    @Test
    @Transactional
    public void searchPratica() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        praticaRepository.saveAndFlush(pratica);
        when(mockPraticaSearchRepository.search(queryStringQuery("id:" + pratica.getId())))
            .thenReturn(Collections.singletonList(pratica));

        // Search the pratica
        restPraticaMockMvc.perform(get("/api/_search/praticas?query=id:" + pratica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pratica.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPratica").value(hasItem(DEFAULT_ID_PRATICA)))
            .andExpect(jsonPath("$.[*].idStudioProfessionaleRef").value(hasItem(DEFAULT_ID_STUDIO_PROFESSIONALE_REF)))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].dataApertura").value(hasItem(DEFAULT_DATA_APERTURA)))
            .andExpect(jsonPath("$.[*].dataChiusura").value(hasItem(DEFAULT_DATA_CHIUSURA)))
            .andExpect(jsonPath("$.[*].dataScadenza").value(hasItem(DEFAULT_DATA_SCADENZA)))
            .andExpect(jsonPath("$.[*].stato").value(hasItem(DEFAULT_STATO)))
            .andExpect(jsonPath("$.[*].motivoChiusura").value(hasItem(DEFAULT_MOTIVO_CHIUSURA)))
            .andExpect(jsonPath("$.[*].idTitolare").value(hasItem(DEFAULT_ID_TITOLARE)))
            .andExpect(jsonPath("$.[*].prcAvanzato").value(hasItem(DEFAULT_PRC_AVANZATO)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].valuta").value(hasItem(DEFAULT_VALUTA)))
            .andExpect(jsonPath("$.[*].idTemplatePraticaRef").value(hasItem(DEFAULT_ID_TEMPLATE_PRATICA_REF)));
    }
}
