package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.IndirizzoPersona;
import it.maggioli.repository.IndirizzoPersonaRepository;
import it.maggioli.repository.search.IndirizzoPersonaSearchRepository;
import it.maggioli.service.IndirizzoPersonaService;
import it.maggioli.service.dto.IndirizzoPersonaDTO;
import it.maggioli.service.mapper.IndirizzoPersonaMapper;

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
 * Integration tests for the {@link IndirizzoPersonaResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class IndirizzoPersonaResourceIT {

    private static final Long DEFAULT_ID_PERSONA_REF = 1L;
    private static final Long UPDATED_ID_PERSONA_REF = 2L;

    private static final String DEFAULT_INDIRIZZO = "AAAAAAAAAA";
    private static final String UPDATED_INDIRIZZO = "BBBBBBBBBB";

    private static final String DEFAULT_COMUNE = "AAAAAAAAAA";
    private static final String UPDATED_COMUNE = "BBBBBBBBBB";

    private static final Long DEFAULT_CAP = 1L;
    private static final Long UPDATED_CAP = 2L;

    private static final String DEFAULT_PROVINCIA = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCIA = "BBBBBBBBBB";

    private static final String DEFAULT_REGIONE = "AAAAAAAAAA";
    private static final String UPDATED_REGIONE = "BBBBBBBBBB";

    private static final String DEFAULT_NAZIONE = "AAAAAAAAAA";
    private static final String UPDATED_NAZIONE = "BBBBBBBBBB";

    @Autowired
    private IndirizzoPersonaRepository indirizzoPersonaRepository;

    @Autowired
    private IndirizzoPersonaMapper indirizzoPersonaMapper;

    @Autowired
    private IndirizzoPersonaService indirizzoPersonaService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.IndirizzoPersonaSearchRepositoryMockConfiguration
     */
    @Autowired
    private IndirizzoPersonaSearchRepository mockIndirizzoPersonaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restIndirizzoPersonaMockMvc;

    private IndirizzoPersona indirizzoPersona;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IndirizzoPersona createEntity(EntityManager em) {
        IndirizzoPersona indirizzoPersona = new IndirizzoPersona()
            .idPersonaRef(DEFAULT_ID_PERSONA_REF)
            .indirizzo(DEFAULT_INDIRIZZO)
            .comune(DEFAULT_COMUNE)
            .cap(DEFAULT_CAP)
            .provincia(DEFAULT_PROVINCIA)
            .regione(DEFAULT_REGIONE)
            .nazione(DEFAULT_NAZIONE);
        return indirizzoPersona;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IndirizzoPersona createUpdatedEntity(EntityManager em) {
        IndirizzoPersona indirizzoPersona = new IndirizzoPersona()
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .indirizzo(UPDATED_INDIRIZZO)
            .comune(UPDATED_COMUNE)
            .cap(UPDATED_CAP)
            .provincia(UPDATED_PROVINCIA)
            .regione(UPDATED_REGIONE)
            .nazione(UPDATED_NAZIONE);
        return indirizzoPersona;
    }

    @BeforeEach
    public void initTest() {
        indirizzoPersona = createEntity(em);
    }

    @Test
    @Transactional
    public void createIndirizzoPersona() throws Exception {
        int databaseSizeBeforeCreate = indirizzoPersonaRepository.findAll().size();
        // Create the IndirizzoPersona
        IndirizzoPersonaDTO indirizzoPersonaDTO = indirizzoPersonaMapper.toDto(indirizzoPersona);
        restIndirizzoPersonaMockMvc.perform(post("/api/indirizzo-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(indirizzoPersonaDTO)))
            .andExpect(status().isCreated());

        // Validate the IndirizzoPersona in the database
        List<IndirizzoPersona> indirizzoPersonaList = indirizzoPersonaRepository.findAll();
        assertThat(indirizzoPersonaList).hasSize(databaseSizeBeforeCreate + 1);
        IndirizzoPersona testIndirizzoPersona = indirizzoPersonaList.get(indirizzoPersonaList.size() - 1);
        assertThat(testIndirizzoPersona.getIdPersonaRef()).isEqualTo(DEFAULT_ID_PERSONA_REF);
        assertThat(testIndirizzoPersona.getIndirizzo()).isEqualTo(DEFAULT_INDIRIZZO);
        assertThat(testIndirizzoPersona.getComune()).isEqualTo(DEFAULT_COMUNE);
        assertThat(testIndirizzoPersona.getCap()).isEqualTo(DEFAULT_CAP);
        assertThat(testIndirizzoPersona.getProvincia()).isEqualTo(DEFAULT_PROVINCIA);
        assertThat(testIndirizzoPersona.getRegione()).isEqualTo(DEFAULT_REGIONE);
        assertThat(testIndirizzoPersona.getNazione()).isEqualTo(DEFAULT_NAZIONE);

        // Validate the IndirizzoPersona in Elasticsearch
        verify(mockIndirizzoPersonaSearchRepository, times(1)).save(testIndirizzoPersona);
    }

    @Test
    @Transactional
    public void createIndirizzoPersonaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = indirizzoPersonaRepository.findAll().size();

        // Create the IndirizzoPersona with an existing ID
        indirizzoPersona.setId(1L);
        IndirizzoPersonaDTO indirizzoPersonaDTO = indirizzoPersonaMapper.toDto(indirizzoPersona);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIndirizzoPersonaMockMvc.perform(post("/api/indirizzo-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(indirizzoPersonaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IndirizzoPersona in the database
        List<IndirizzoPersona> indirizzoPersonaList = indirizzoPersonaRepository.findAll();
        assertThat(indirizzoPersonaList).hasSize(databaseSizeBeforeCreate);

        // Validate the IndirizzoPersona in Elasticsearch
        verify(mockIndirizzoPersonaSearchRepository, times(0)).save(indirizzoPersona);
    }


    @Test
    @Transactional
    public void checkIdPersonaRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = indirizzoPersonaRepository.findAll().size();
        // set the field null
        indirizzoPersona.setIdPersonaRef(null);

        // Create the IndirizzoPersona, which fails.
        IndirizzoPersonaDTO indirizzoPersonaDTO = indirizzoPersonaMapper.toDto(indirizzoPersona);


        restIndirizzoPersonaMockMvc.perform(post("/api/indirizzo-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(indirizzoPersonaDTO)))
            .andExpect(status().isBadRequest());

        List<IndirizzoPersona> indirizzoPersonaList = indirizzoPersonaRepository.findAll();
        assertThat(indirizzoPersonaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIndirizzoPersonas() throws Exception {
        // Initialize the database
        indirizzoPersonaRepository.saveAndFlush(indirizzoPersona);

        // Get all the indirizzoPersonaList
        restIndirizzoPersonaMockMvc.perform(get("/api/indirizzo-personas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(indirizzoPersona.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF.intValue())))
            .andExpect(jsonPath("$.[*].indirizzo").value(hasItem(DEFAULT_INDIRIZZO)))
            .andExpect(jsonPath("$.[*].comune").value(hasItem(DEFAULT_COMUNE)))
            .andExpect(jsonPath("$.[*].cap").value(hasItem(DEFAULT_CAP.intValue())))
            .andExpect(jsonPath("$.[*].provincia").value(hasItem(DEFAULT_PROVINCIA)))
            .andExpect(jsonPath("$.[*].regione").value(hasItem(DEFAULT_REGIONE)))
            .andExpect(jsonPath("$.[*].nazione").value(hasItem(DEFAULT_NAZIONE)));
    }
    
    @Test
    @Transactional
    public void getIndirizzoPersona() throws Exception {
        // Initialize the database
        indirizzoPersonaRepository.saveAndFlush(indirizzoPersona);

        // Get the indirizzoPersona
        restIndirizzoPersonaMockMvc.perform(get("/api/indirizzo-personas/{id}", indirizzoPersona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(indirizzoPersona.getId().intValue()))
            .andExpect(jsonPath("$.idPersonaRef").value(DEFAULT_ID_PERSONA_REF.intValue()))
            .andExpect(jsonPath("$.indirizzo").value(DEFAULT_INDIRIZZO))
            .andExpect(jsonPath("$.comune").value(DEFAULT_COMUNE))
            .andExpect(jsonPath("$.cap").value(DEFAULT_CAP.intValue()))
            .andExpect(jsonPath("$.provincia").value(DEFAULT_PROVINCIA))
            .andExpect(jsonPath("$.regione").value(DEFAULT_REGIONE))
            .andExpect(jsonPath("$.nazione").value(DEFAULT_NAZIONE));
    }
    @Test
    @Transactional
    public void getNonExistingIndirizzoPersona() throws Exception {
        // Get the indirizzoPersona
        restIndirizzoPersonaMockMvc.perform(get("/api/indirizzo-personas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIndirizzoPersona() throws Exception {
        // Initialize the database
        indirizzoPersonaRepository.saveAndFlush(indirizzoPersona);

        int databaseSizeBeforeUpdate = indirizzoPersonaRepository.findAll().size();

        // Update the indirizzoPersona
        IndirizzoPersona updatedIndirizzoPersona = indirizzoPersonaRepository.findById(indirizzoPersona.getId()).get();
        // Disconnect from session so that the updates on updatedIndirizzoPersona are not directly saved in db
        em.detach(updatedIndirizzoPersona);
        updatedIndirizzoPersona
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .indirizzo(UPDATED_INDIRIZZO)
            .comune(UPDATED_COMUNE)
            .cap(UPDATED_CAP)
            .provincia(UPDATED_PROVINCIA)
            .regione(UPDATED_REGIONE)
            .nazione(UPDATED_NAZIONE);
        IndirizzoPersonaDTO indirizzoPersonaDTO = indirizzoPersonaMapper.toDto(updatedIndirizzoPersona);

        restIndirizzoPersonaMockMvc.perform(put("/api/indirizzo-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(indirizzoPersonaDTO)))
            .andExpect(status().isOk());

        // Validate the IndirizzoPersona in the database
        List<IndirizzoPersona> indirizzoPersonaList = indirizzoPersonaRepository.findAll();
        assertThat(indirizzoPersonaList).hasSize(databaseSizeBeforeUpdate);
        IndirizzoPersona testIndirizzoPersona = indirizzoPersonaList.get(indirizzoPersonaList.size() - 1);
        assertThat(testIndirizzoPersona.getIdPersonaRef()).isEqualTo(UPDATED_ID_PERSONA_REF);
        assertThat(testIndirizzoPersona.getIndirizzo()).isEqualTo(UPDATED_INDIRIZZO);
        assertThat(testIndirizzoPersona.getComune()).isEqualTo(UPDATED_COMUNE);
        assertThat(testIndirizzoPersona.getCap()).isEqualTo(UPDATED_CAP);
        assertThat(testIndirizzoPersona.getProvincia()).isEqualTo(UPDATED_PROVINCIA);
        assertThat(testIndirizzoPersona.getRegione()).isEqualTo(UPDATED_REGIONE);
        assertThat(testIndirizzoPersona.getNazione()).isEqualTo(UPDATED_NAZIONE);

        // Validate the IndirizzoPersona in Elasticsearch
        verify(mockIndirizzoPersonaSearchRepository, times(1)).save(testIndirizzoPersona);
    }

    @Test
    @Transactional
    public void updateNonExistingIndirizzoPersona() throws Exception {
        int databaseSizeBeforeUpdate = indirizzoPersonaRepository.findAll().size();

        // Create the IndirizzoPersona
        IndirizzoPersonaDTO indirizzoPersonaDTO = indirizzoPersonaMapper.toDto(indirizzoPersona);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIndirizzoPersonaMockMvc.perform(put("/api/indirizzo-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(indirizzoPersonaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IndirizzoPersona in the database
        List<IndirizzoPersona> indirizzoPersonaList = indirizzoPersonaRepository.findAll();
        assertThat(indirizzoPersonaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the IndirizzoPersona in Elasticsearch
        verify(mockIndirizzoPersonaSearchRepository, times(0)).save(indirizzoPersona);
    }

    @Test
    @Transactional
    public void deleteIndirizzoPersona() throws Exception {
        // Initialize the database
        indirizzoPersonaRepository.saveAndFlush(indirizzoPersona);

        int databaseSizeBeforeDelete = indirizzoPersonaRepository.findAll().size();

        // Delete the indirizzoPersona
        restIndirizzoPersonaMockMvc.perform(delete("/api/indirizzo-personas/{id}", indirizzoPersona.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<IndirizzoPersona> indirizzoPersonaList = indirizzoPersonaRepository.findAll();
        assertThat(indirizzoPersonaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the IndirizzoPersona in Elasticsearch
        verify(mockIndirizzoPersonaSearchRepository, times(1)).deleteById(indirizzoPersona.getId());
    }

    @Test
    @Transactional
    public void searchIndirizzoPersona() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        indirizzoPersonaRepository.saveAndFlush(indirizzoPersona);
        when(mockIndirizzoPersonaSearchRepository.search(queryStringQuery("id:" + indirizzoPersona.getId())))
            .thenReturn(Collections.singletonList(indirizzoPersona));

        // Search the indirizzoPersona
        restIndirizzoPersonaMockMvc.perform(get("/api/_search/indirizzo-personas?query=id:" + indirizzoPersona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(indirizzoPersona.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF.intValue())))
            .andExpect(jsonPath("$.[*].indirizzo").value(hasItem(DEFAULT_INDIRIZZO)))
            .andExpect(jsonPath("$.[*].comune").value(hasItem(DEFAULT_COMUNE)))
            .andExpect(jsonPath("$.[*].cap").value(hasItem(DEFAULT_CAP.intValue())))
            .andExpect(jsonPath("$.[*].provincia").value(hasItem(DEFAULT_PROVINCIA)))
            .andExpect(jsonPath("$.[*].regione").value(hasItem(DEFAULT_REGIONE)))
            .andExpect(jsonPath("$.[*].nazione").value(hasItem(DEFAULT_NAZIONE)));
    }
}
