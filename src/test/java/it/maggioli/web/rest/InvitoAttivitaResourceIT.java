package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.InvitoAttivita;
import it.maggioli.repository.InvitoAttivitaRepository;
import it.maggioli.repository.search.InvitoAttivitaSearchRepository;
import it.maggioli.service.InvitoAttivitaService;
import it.maggioli.service.dto.InvitoAttivitaDTO;
import it.maggioli.service.mapper.InvitoAttivitaMapper;

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
 * Integration tests for the {@link InvitoAttivitaResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class InvitoAttivitaResourceIT {

    private static final Integer DEFAULT_ID_TASK_REF = 1;
    private static final Integer UPDATED_ID_TASK_REF = 2;

    @Autowired
    private InvitoAttivitaRepository invitoAttivitaRepository;

    @Autowired
    private InvitoAttivitaMapper invitoAttivitaMapper;

    @Autowired
    private InvitoAttivitaService invitoAttivitaService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.InvitoAttivitaSearchRepositoryMockConfiguration
     */
    @Autowired
    private InvitoAttivitaSearchRepository mockInvitoAttivitaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInvitoAttivitaMockMvc;

    private InvitoAttivita invitoAttivita;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvitoAttivita createEntity(EntityManager em) {
        InvitoAttivita invitoAttivita = new InvitoAttivita()
            .idTaskRef(DEFAULT_ID_TASK_REF);
        return invitoAttivita;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvitoAttivita createUpdatedEntity(EntityManager em) {
        InvitoAttivita invitoAttivita = new InvitoAttivita()
            .idTaskRef(UPDATED_ID_TASK_REF);
        return invitoAttivita;
    }

    @BeforeEach
    public void initTest() {
        invitoAttivita = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvitoAttivita() throws Exception {
        int databaseSizeBeforeCreate = invitoAttivitaRepository.findAll().size();
        // Create the InvitoAttivita
        InvitoAttivitaDTO invitoAttivitaDTO = invitoAttivitaMapper.toDto(invitoAttivita);
        restInvitoAttivitaMockMvc.perform(post("/api/invito-attivitas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitoAttivitaDTO)))
            .andExpect(status().isCreated());

        // Validate the InvitoAttivita in the database
        List<InvitoAttivita> invitoAttivitaList = invitoAttivitaRepository.findAll();
        assertThat(invitoAttivitaList).hasSize(databaseSizeBeforeCreate + 1);
        InvitoAttivita testInvitoAttivita = invitoAttivitaList.get(invitoAttivitaList.size() - 1);
        assertThat(testInvitoAttivita.getIdTaskRef()).isEqualTo(DEFAULT_ID_TASK_REF);

        // Validate the InvitoAttivita in Elasticsearch
        verify(mockInvitoAttivitaSearchRepository, times(1)).save(testInvitoAttivita);
    }

    @Test
    @Transactional
    public void createInvitoAttivitaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invitoAttivitaRepository.findAll().size();

        // Create the InvitoAttivita with an existing ID
        invitoAttivita.setId(1L);
        InvitoAttivitaDTO invitoAttivitaDTO = invitoAttivitaMapper.toDto(invitoAttivita);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvitoAttivitaMockMvc.perform(post("/api/invito-attivitas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitoAttivitaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvitoAttivita in the database
        List<InvitoAttivita> invitoAttivitaList = invitoAttivitaRepository.findAll();
        assertThat(invitoAttivitaList).hasSize(databaseSizeBeforeCreate);

        // Validate the InvitoAttivita in Elasticsearch
        verify(mockInvitoAttivitaSearchRepository, times(0)).save(invitoAttivita);
    }


    @Test
    @Transactional
    public void getAllInvitoAttivitas() throws Exception {
        // Initialize the database
        invitoAttivitaRepository.saveAndFlush(invitoAttivita);

        // Get all the invitoAttivitaList
        restInvitoAttivitaMockMvc.perform(get("/api/invito-attivitas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invitoAttivita.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTaskRef").value(hasItem(DEFAULT_ID_TASK_REF)));
    }
    
    @Test
    @Transactional
    public void getInvitoAttivita() throws Exception {
        // Initialize the database
        invitoAttivitaRepository.saveAndFlush(invitoAttivita);

        // Get the invitoAttivita
        restInvitoAttivitaMockMvc.perform(get("/api/invito-attivitas/{id}", invitoAttivita.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(invitoAttivita.getId().intValue()))
            .andExpect(jsonPath("$.idTaskRef").value(DEFAULT_ID_TASK_REF));
    }
    @Test
    @Transactional
    public void getNonExistingInvitoAttivita() throws Exception {
        // Get the invitoAttivita
        restInvitoAttivitaMockMvc.perform(get("/api/invito-attivitas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvitoAttivita() throws Exception {
        // Initialize the database
        invitoAttivitaRepository.saveAndFlush(invitoAttivita);

        int databaseSizeBeforeUpdate = invitoAttivitaRepository.findAll().size();

        // Update the invitoAttivita
        InvitoAttivita updatedInvitoAttivita = invitoAttivitaRepository.findById(invitoAttivita.getId()).get();
        // Disconnect from session so that the updates on updatedInvitoAttivita are not directly saved in db
        em.detach(updatedInvitoAttivita);
        updatedInvitoAttivita
            .idTaskRef(UPDATED_ID_TASK_REF);
        InvitoAttivitaDTO invitoAttivitaDTO = invitoAttivitaMapper.toDto(updatedInvitoAttivita);

        restInvitoAttivitaMockMvc.perform(put("/api/invito-attivitas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitoAttivitaDTO)))
            .andExpect(status().isOk());

        // Validate the InvitoAttivita in the database
        List<InvitoAttivita> invitoAttivitaList = invitoAttivitaRepository.findAll();
        assertThat(invitoAttivitaList).hasSize(databaseSizeBeforeUpdate);
        InvitoAttivita testInvitoAttivita = invitoAttivitaList.get(invitoAttivitaList.size() - 1);
        assertThat(testInvitoAttivita.getIdTaskRef()).isEqualTo(UPDATED_ID_TASK_REF);

        // Validate the InvitoAttivita in Elasticsearch
        verify(mockInvitoAttivitaSearchRepository, times(1)).save(testInvitoAttivita);
    }

    @Test
    @Transactional
    public void updateNonExistingInvitoAttivita() throws Exception {
        int databaseSizeBeforeUpdate = invitoAttivitaRepository.findAll().size();

        // Create the InvitoAttivita
        InvitoAttivitaDTO invitoAttivitaDTO = invitoAttivitaMapper.toDto(invitoAttivita);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvitoAttivitaMockMvc.perform(put("/api/invito-attivitas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitoAttivitaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvitoAttivita in the database
        List<InvitoAttivita> invitoAttivitaList = invitoAttivitaRepository.findAll();
        assertThat(invitoAttivitaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the InvitoAttivita in Elasticsearch
        verify(mockInvitoAttivitaSearchRepository, times(0)).save(invitoAttivita);
    }

    @Test
    @Transactional
    public void deleteInvitoAttivita() throws Exception {
        // Initialize the database
        invitoAttivitaRepository.saveAndFlush(invitoAttivita);

        int databaseSizeBeforeDelete = invitoAttivitaRepository.findAll().size();

        // Delete the invitoAttivita
        restInvitoAttivitaMockMvc.perform(delete("/api/invito-attivitas/{id}", invitoAttivita.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvitoAttivita> invitoAttivitaList = invitoAttivitaRepository.findAll();
        assertThat(invitoAttivitaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the InvitoAttivita in Elasticsearch
        verify(mockInvitoAttivitaSearchRepository, times(1)).deleteById(invitoAttivita.getId());
    }

    @Test
    @Transactional
    public void searchInvitoAttivita() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        invitoAttivitaRepository.saveAndFlush(invitoAttivita);
        when(mockInvitoAttivitaSearchRepository.search(queryStringQuery("id:" + invitoAttivita.getId())))
            .thenReturn(Collections.singletonList(invitoAttivita));

        // Search the invitoAttivita
        restInvitoAttivitaMockMvc.perform(get("/api/_search/invito-attivitas?query=id:" + invitoAttivita.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invitoAttivita.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTaskRef").value(hasItem(DEFAULT_ID_TASK_REF)));
    }
}
