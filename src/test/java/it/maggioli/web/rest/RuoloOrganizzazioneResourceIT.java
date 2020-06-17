package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.RuoloOrganizzazione;
import it.maggioli.repository.RuoloOrganizzazioneRepository;
import it.maggioli.repository.search.RuoloOrganizzazioneSearchRepository;
import it.maggioli.service.RuoloOrganizzazioneService;
import it.maggioli.service.dto.RuoloOrganizzazioneDTO;
import it.maggioli.service.mapper.RuoloOrganizzazioneMapper;

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
 * Integration tests for the {@link RuoloOrganizzazioneResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class RuoloOrganizzazioneResourceIT {

    private static final Long DEFAULT_RUOLO_IN_ORG = 1L;
    private static final Long UPDATED_RUOLO_IN_ORG = 2L;

    @Autowired
    private RuoloOrganizzazioneRepository ruoloOrganizzazioneRepository;

    @Autowired
    private RuoloOrganizzazioneMapper ruoloOrganizzazioneMapper;

    @Autowired
    private RuoloOrganizzazioneService ruoloOrganizzazioneService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.RuoloOrganizzazioneSearchRepositoryMockConfiguration
     */
    @Autowired
    private RuoloOrganizzazioneSearchRepository mockRuoloOrganizzazioneSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRuoloOrganizzazioneMockMvc;

    private RuoloOrganizzazione ruoloOrganizzazione;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RuoloOrganizzazione createEntity(EntityManager em) {
        RuoloOrganizzazione ruoloOrganizzazione = new RuoloOrganizzazione()
            .ruoloInOrg(DEFAULT_RUOLO_IN_ORG);
        return ruoloOrganizzazione;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RuoloOrganizzazione createUpdatedEntity(EntityManager em) {
        RuoloOrganizzazione ruoloOrganizzazione = new RuoloOrganizzazione()
            .ruoloInOrg(UPDATED_RUOLO_IN_ORG);
        return ruoloOrganizzazione;
    }

    @BeforeEach
    public void initTest() {
        ruoloOrganizzazione = createEntity(em);
    }

    @Test
    @Transactional
    public void createRuoloOrganizzazione() throws Exception {
        int databaseSizeBeforeCreate = ruoloOrganizzazioneRepository.findAll().size();
        // Create the RuoloOrganizzazione
        RuoloOrganizzazioneDTO ruoloOrganizzazioneDTO = ruoloOrganizzazioneMapper.toDto(ruoloOrganizzazione);
        restRuoloOrganizzazioneMockMvc.perform(post("/api/ruolo-organizzaziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ruoloOrganizzazioneDTO)))
            .andExpect(status().isCreated());

        // Validate the RuoloOrganizzazione in the database
        List<RuoloOrganizzazione> ruoloOrganizzazioneList = ruoloOrganizzazioneRepository.findAll();
        assertThat(ruoloOrganizzazioneList).hasSize(databaseSizeBeforeCreate + 1);
        RuoloOrganizzazione testRuoloOrganizzazione = ruoloOrganizzazioneList.get(ruoloOrganizzazioneList.size() - 1);
        assertThat(testRuoloOrganizzazione.getRuoloInOrg()).isEqualTo(DEFAULT_RUOLO_IN_ORG);

        // Validate the RuoloOrganizzazione in Elasticsearch
        verify(mockRuoloOrganizzazioneSearchRepository, times(1)).save(testRuoloOrganizzazione);
    }

    @Test
    @Transactional
    public void createRuoloOrganizzazioneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ruoloOrganizzazioneRepository.findAll().size();

        // Create the RuoloOrganizzazione with an existing ID
        ruoloOrganizzazione.setId(1L);
        RuoloOrganizzazioneDTO ruoloOrganizzazioneDTO = ruoloOrganizzazioneMapper.toDto(ruoloOrganizzazione);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRuoloOrganizzazioneMockMvc.perform(post("/api/ruolo-organizzaziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ruoloOrganizzazioneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RuoloOrganizzazione in the database
        List<RuoloOrganizzazione> ruoloOrganizzazioneList = ruoloOrganizzazioneRepository.findAll();
        assertThat(ruoloOrganizzazioneList).hasSize(databaseSizeBeforeCreate);

        // Validate the RuoloOrganizzazione in Elasticsearch
        verify(mockRuoloOrganizzazioneSearchRepository, times(0)).save(ruoloOrganizzazione);
    }


    @Test
    @Transactional
    public void getAllRuoloOrganizzaziones() throws Exception {
        // Initialize the database
        ruoloOrganizzazioneRepository.saveAndFlush(ruoloOrganizzazione);

        // Get all the ruoloOrganizzazioneList
        restRuoloOrganizzazioneMockMvc.perform(get("/api/ruolo-organizzaziones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ruoloOrganizzazione.getId().intValue())))
            .andExpect(jsonPath("$.[*].ruoloInOrg").value(hasItem(DEFAULT_RUOLO_IN_ORG.intValue())));
    }
    
    @Test
    @Transactional
    public void getRuoloOrganizzazione() throws Exception {
        // Initialize the database
        ruoloOrganizzazioneRepository.saveAndFlush(ruoloOrganizzazione);

        // Get the ruoloOrganizzazione
        restRuoloOrganizzazioneMockMvc.perform(get("/api/ruolo-organizzaziones/{id}", ruoloOrganizzazione.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ruoloOrganizzazione.getId().intValue()))
            .andExpect(jsonPath("$.ruoloInOrg").value(DEFAULT_RUOLO_IN_ORG.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingRuoloOrganizzazione() throws Exception {
        // Get the ruoloOrganizzazione
        restRuoloOrganizzazioneMockMvc.perform(get("/api/ruolo-organizzaziones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRuoloOrganizzazione() throws Exception {
        // Initialize the database
        ruoloOrganizzazioneRepository.saveAndFlush(ruoloOrganizzazione);

        int databaseSizeBeforeUpdate = ruoloOrganizzazioneRepository.findAll().size();

        // Update the ruoloOrganizzazione
        RuoloOrganizzazione updatedRuoloOrganizzazione = ruoloOrganizzazioneRepository.findById(ruoloOrganizzazione.getId()).get();
        // Disconnect from session so that the updates on updatedRuoloOrganizzazione are not directly saved in db
        em.detach(updatedRuoloOrganizzazione);
        updatedRuoloOrganizzazione
            .ruoloInOrg(UPDATED_RUOLO_IN_ORG);
        RuoloOrganizzazioneDTO ruoloOrganizzazioneDTO = ruoloOrganizzazioneMapper.toDto(updatedRuoloOrganizzazione);

        restRuoloOrganizzazioneMockMvc.perform(put("/api/ruolo-organizzaziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ruoloOrganizzazioneDTO)))
            .andExpect(status().isOk());

        // Validate the RuoloOrganizzazione in the database
        List<RuoloOrganizzazione> ruoloOrganizzazioneList = ruoloOrganizzazioneRepository.findAll();
        assertThat(ruoloOrganizzazioneList).hasSize(databaseSizeBeforeUpdate);
        RuoloOrganizzazione testRuoloOrganizzazione = ruoloOrganizzazioneList.get(ruoloOrganizzazioneList.size() - 1);
        assertThat(testRuoloOrganizzazione.getRuoloInOrg()).isEqualTo(UPDATED_RUOLO_IN_ORG);

        // Validate the RuoloOrganizzazione in Elasticsearch
        verify(mockRuoloOrganizzazioneSearchRepository, times(1)).save(testRuoloOrganizzazione);
    }

    @Test
    @Transactional
    public void updateNonExistingRuoloOrganizzazione() throws Exception {
        int databaseSizeBeforeUpdate = ruoloOrganizzazioneRepository.findAll().size();

        // Create the RuoloOrganizzazione
        RuoloOrganizzazioneDTO ruoloOrganizzazioneDTO = ruoloOrganizzazioneMapper.toDto(ruoloOrganizzazione);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRuoloOrganizzazioneMockMvc.perform(put("/api/ruolo-organizzaziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ruoloOrganizzazioneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RuoloOrganizzazione in the database
        List<RuoloOrganizzazione> ruoloOrganizzazioneList = ruoloOrganizzazioneRepository.findAll();
        assertThat(ruoloOrganizzazioneList).hasSize(databaseSizeBeforeUpdate);

        // Validate the RuoloOrganizzazione in Elasticsearch
        verify(mockRuoloOrganizzazioneSearchRepository, times(0)).save(ruoloOrganizzazione);
    }

    @Test
    @Transactional
    public void deleteRuoloOrganizzazione() throws Exception {
        // Initialize the database
        ruoloOrganizzazioneRepository.saveAndFlush(ruoloOrganizzazione);

        int databaseSizeBeforeDelete = ruoloOrganizzazioneRepository.findAll().size();

        // Delete the ruoloOrganizzazione
        restRuoloOrganizzazioneMockMvc.perform(delete("/api/ruolo-organizzaziones/{id}", ruoloOrganizzazione.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RuoloOrganizzazione> ruoloOrganizzazioneList = ruoloOrganizzazioneRepository.findAll();
        assertThat(ruoloOrganizzazioneList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the RuoloOrganizzazione in Elasticsearch
        verify(mockRuoloOrganizzazioneSearchRepository, times(1)).deleteById(ruoloOrganizzazione.getId());
    }

    @Test
    @Transactional
    public void searchRuoloOrganizzazione() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        ruoloOrganizzazioneRepository.saveAndFlush(ruoloOrganizzazione);
        when(mockRuoloOrganizzazioneSearchRepository.search(queryStringQuery("id:" + ruoloOrganizzazione.getId())))
            .thenReturn(Collections.singletonList(ruoloOrganizzazione));

        // Search the ruoloOrganizzazione
        restRuoloOrganizzazioneMockMvc.perform(get("/api/_search/ruolo-organizzaziones?query=id:" + ruoloOrganizzazione.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ruoloOrganizzazione.getId().intValue())))
            .andExpect(jsonPath("$.[*].ruoloInOrg").value(hasItem(DEFAULT_RUOLO_IN_ORG.intValue())));
    }
}
