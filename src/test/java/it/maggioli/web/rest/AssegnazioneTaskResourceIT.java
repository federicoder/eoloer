package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.AssegnazioneTask;
import it.maggioli.repository.AssegnazioneTaskRepository;
import it.maggioli.repository.search.AssegnazioneTaskSearchRepository;
import it.maggioli.service.AssegnazioneTaskService;
import it.maggioli.service.dto.AssegnazioneTaskDTO;
import it.maggioli.service.mapper.AssegnazioneTaskMapper;

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
 * Integration tests for the {@link AssegnazioneTaskResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class AssegnazioneTaskResourceIT {

    private static final Integer DEFAULT_ID_ATTIVITA = 8;
    private static final Integer UPDATED_ID_ATTIVITA = 7;

    private static final Integer DEFAULT_ID_USER_AMMESSO = 8;
    private static final Integer UPDATED_ID_USER_AMMESSO = 7;

    private static final Integer DEFAULT_RUOLO = 1;
    private static final Integer UPDATED_RUOLO = 2;

    private static final Integer DEFAULT_ID_USER_CONCEDENTE = 1;
    private static final Integer UPDATED_ID_USER_CONCEDENTE = 2;

    private static final Integer DEFAULT_STATO_ASSEGNAZIONE = 1;
    private static final Integer UPDATED_STATO_ASSEGNAZIONE = 2;

    @Autowired
    private AssegnazioneTaskRepository assegnazioneTaskRepository;

    @Autowired
    private AssegnazioneTaskMapper assegnazioneTaskMapper;

    @Autowired
    private AssegnazioneTaskService assegnazioneTaskService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.AssegnazioneTaskSearchRepositoryMockConfiguration
     */
    @Autowired
    private AssegnazioneTaskSearchRepository mockAssegnazioneTaskSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAssegnazioneTaskMockMvc;

    private AssegnazioneTask assegnazioneTask;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AssegnazioneTask createEntity(EntityManager em) {
        AssegnazioneTask assegnazioneTask = new AssegnazioneTask()
            .idAttivita(DEFAULT_ID_ATTIVITA)
            .idUserAmmesso(DEFAULT_ID_USER_AMMESSO)
            .ruolo(DEFAULT_RUOLO)
            .idUserConcedente(DEFAULT_ID_USER_CONCEDENTE)
            .statoAssegnazione(DEFAULT_STATO_ASSEGNAZIONE);
        return assegnazioneTask;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AssegnazioneTask createUpdatedEntity(EntityManager em) {
        AssegnazioneTask assegnazioneTask = new AssegnazioneTask()
            .idAttivita(UPDATED_ID_ATTIVITA)
            .idUserAmmesso(UPDATED_ID_USER_AMMESSO)
            .ruolo(UPDATED_RUOLO)
            .idUserConcedente(UPDATED_ID_USER_CONCEDENTE)
            .statoAssegnazione(UPDATED_STATO_ASSEGNAZIONE);
        return assegnazioneTask;
    }

    @BeforeEach
    public void initTest() {
        assegnazioneTask = createEntity(em);
    }

    @Test
    @Transactional
    public void createAssegnazioneTask() throws Exception {
        int databaseSizeBeforeCreate = assegnazioneTaskRepository.findAll().size();
        // Create the AssegnazioneTask
        AssegnazioneTaskDTO assegnazioneTaskDTO = assegnazioneTaskMapper.toDto(assegnazioneTask);
        restAssegnazioneTaskMockMvc.perform(post("/api/assegnazione-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(assegnazioneTaskDTO)))
            .andExpect(status().isCreated());

        // Validate the AssegnazioneTask in the database
        List<AssegnazioneTask> assegnazioneTaskList = assegnazioneTaskRepository.findAll();
        assertThat(assegnazioneTaskList).hasSize(databaseSizeBeforeCreate + 1);
        AssegnazioneTask testAssegnazioneTask = assegnazioneTaskList.get(assegnazioneTaskList.size() - 1);
        assertThat(testAssegnazioneTask.getIdAttivita()).isEqualTo(DEFAULT_ID_ATTIVITA);
        assertThat(testAssegnazioneTask.getIdUserAmmesso()).isEqualTo(DEFAULT_ID_USER_AMMESSO);
        assertThat(testAssegnazioneTask.getRuolo()).isEqualTo(DEFAULT_RUOLO);
        assertThat(testAssegnazioneTask.getIdUserConcedente()).isEqualTo(DEFAULT_ID_USER_CONCEDENTE);
        assertThat(testAssegnazioneTask.getStatoAssegnazione()).isEqualTo(DEFAULT_STATO_ASSEGNAZIONE);

        // Validate the AssegnazioneTask in Elasticsearch
        verify(mockAssegnazioneTaskSearchRepository, times(1)).save(testAssegnazioneTask);
    }

    @Test
    @Transactional
    public void createAssegnazioneTaskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = assegnazioneTaskRepository.findAll().size();

        // Create the AssegnazioneTask with an existing ID
        assegnazioneTask.setId(1L);
        AssegnazioneTaskDTO assegnazioneTaskDTO = assegnazioneTaskMapper.toDto(assegnazioneTask);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssegnazioneTaskMockMvc.perform(post("/api/assegnazione-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(assegnazioneTaskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AssegnazioneTask in the database
        List<AssegnazioneTask> assegnazioneTaskList = assegnazioneTaskRepository.findAll();
        assertThat(assegnazioneTaskList).hasSize(databaseSizeBeforeCreate);

        // Validate the AssegnazioneTask in Elasticsearch
        verify(mockAssegnazioneTaskSearchRepository, times(0)).save(assegnazioneTask);
    }


    @Test
    @Transactional
    public void getAllAssegnazioneTasks() throws Exception {
        // Initialize the database
        assegnazioneTaskRepository.saveAndFlush(assegnazioneTask);

        // Get all the assegnazioneTaskList
        restAssegnazioneTaskMockMvc.perform(get("/api/assegnazione-tasks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(assegnazioneTask.getId().intValue())))
            .andExpect(jsonPath("$.[*].idAttivita").value(hasItem(DEFAULT_ID_ATTIVITA)))
            .andExpect(jsonPath("$.[*].idUserAmmesso").value(hasItem(DEFAULT_ID_USER_AMMESSO)))
            .andExpect(jsonPath("$.[*].ruolo").value(hasItem(DEFAULT_RUOLO)))
            .andExpect(jsonPath("$.[*].idUserConcedente").value(hasItem(DEFAULT_ID_USER_CONCEDENTE)))
            .andExpect(jsonPath("$.[*].statoAssegnazione").value(hasItem(DEFAULT_STATO_ASSEGNAZIONE)));
    }
    
    @Test
    @Transactional
    public void getAssegnazioneTask() throws Exception {
        // Initialize the database
        assegnazioneTaskRepository.saveAndFlush(assegnazioneTask);

        // Get the assegnazioneTask
        restAssegnazioneTaskMockMvc.perform(get("/api/assegnazione-tasks/{id}", assegnazioneTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(assegnazioneTask.getId().intValue()))
            .andExpect(jsonPath("$.idAttivita").value(DEFAULT_ID_ATTIVITA))
            .andExpect(jsonPath("$.idUserAmmesso").value(DEFAULT_ID_USER_AMMESSO))
            .andExpect(jsonPath("$.ruolo").value(DEFAULT_RUOLO))
            .andExpect(jsonPath("$.idUserConcedente").value(DEFAULT_ID_USER_CONCEDENTE))
            .andExpect(jsonPath("$.statoAssegnazione").value(DEFAULT_STATO_ASSEGNAZIONE));
    }
    @Test
    @Transactional
    public void getNonExistingAssegnazioneTask() throws Exception {
        // Get the assegnazioneTask
        restAssegnazioneTaskMockMvc.perform(get("/api/assegnazione-tasks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAssegnazioneTask() throws Exception {
        // Initialize the database
        assegnazioneTaskRepository.saveAndFlush(assegnazioneTask);

        int databaseSizeBeforeUpdate = assegnazioneTaskRepository.findAll().size();

        // Update the assegnazioneTask
        AssegnazioneTask updatedAssegnazioneTask = assegnazioneTaskRepository.findById(assegnazioneTask.getId()).get();
        // Disconnect from session so that the updates on updatedAssegnazioneTask are not directly saved in db
        em.detach(updatedAssegnazioneTask);
        updatedAssegnazioneTask
            .idAttivita(UPDATED_ID_ATTIVITA)
            .idUserAmmesso(UPDATED_ID_USER_AMMESSO)
            .ruolo(UPDATED_RUOLO)
            .idUserConcedente(UPDATED_ID_USER_CONCEDENTE)
            .statoAssegnazione(UPDATED_STATO_ASSEGNAZIONE);
        AssegnazioneTaskDTO assegnazioneTaskDTO = assegnazioneTaskMapper.toDto(updatedAssegnazioneTask);

        restAssegnazioneTaskMockMvc.perform(put("/api/assegnazione-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(assegnazioneTaskDTO)))
            .andExpect(status().isOk());

        // Validate the AssegnazioneTask in the database
        List<AssegnazioneTask> assegnazioneTaskList = assegnazioneTaskRepository.findAll();
        assertThat(assegnazioneTaskList).hasSize(databaseSizeBeforeUpdate);
        AssegnazioneTask testAssegnazioneTask = assegnazioneTaskList.get(assegnazioneTaskList.size() - 1);
        assertThat(testAssegnazioneTask.getIdAttivita()).isEqualTo(UPDATED_ID_ATTIVITA);
        assertThat(testAssegnazioneTask.getIdUserAmmesso()).isEqualTo(UPDATED_ID_USER_AMMESSO);
        assertThat(testAssegnazioneTask.getRuolo()).isEqualTo(UPDATED_RUOLO);
        assertThat(testAssegnazioneTask.getIdUserConcedente()).isEqualTo(UPDATED_ID_USER_CONCEDENTE);
        assertThat(testAssegnazioneTask.getStatoAssegnazione()).isEqualTo(UPDATED_STATO_ASSEGNAZIONE);

        // Validate the AssegnazioneTask in Elasticsearch
        verify(mockAssegnazioneTaskSearchRepository, times(1)).save(testAssegnazioneTask);
    }

    @Test
    @Transactional
    public void updateNonExistingAssegnazioneTask() throws Exception {
        int databaseSizeBeforeUpdate = assegnazioneTaskRepository.findAll().size();

        // Create the AssegnazioneTask
        AssegnazioneTaskDTO assegnazioneTaskDTO = assegnazioneTaskMapper.toDto(assegnazioneTask);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAssegnazioneTaskMockMvc.perform(put("/api/assegnazione-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(assegnazioneTaskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AssegnazioneTask in the database
        List<AssegnazioneTask> assegnazioneTaskList = assegnazioneTaskRepository.findAll();
        assertThat(assegnazioneTaskList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AssegnazioneTask in Elasticsearch
        verify(mockAssegnazioneTaskSearchRepository, times(0)).save(assegnazioneTask);
    }

    @Test
    @Transactional
    public void deleteAssegnazioneTask() throws Exception {
        // Initialize the database
        assegnazioneTaskRepository.saveAndFlush(assegnazioneTask);

        int databaseSizeBeforeDelete = assegnazioneTaskRepository.findAll().size();

        // Delete the assegnazioneTask
        restAssegnazioneTaskMockMvc.perform(delete("/api/assegnazione-tasks/{id}", assegnazioneTask.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AssegnazioneTask> assegnazioneTaskList = assegnazioneTaskRepository.findAll();
        assertThat(assegnazioneTaskList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AssegnazioneTask in Elasticsearch
        verify(mockAssegnazioneTaskSearchRepository, times(1)).deleteById(assegnazioneTask.getId());
    }

    @Test
    @Transactional
    public void searchAssegnazioneTask() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        assegnazioneTaskRepository.saveAndFlush(assegnazioneTask);
        when(mockAssegnazioneTaskSearchRepository.search(queryStringQuery("id:" + assegnazioneTask.getId())))
            .thenReturn(Collections.singletonList(assegnazioneTask));

        // Search the assegnazioneTask
        restAssegnazioneTaskMockMvc.perform(get("/api/_search/assegnazione-tasks?query=id:" + assegnazioneTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(assegnazioneTask.getId().intValue())))
            .andExpect(jsonPath("$.[*].idAttivita").value(hasItem(DEFAULT_ID_ATTIVITA)))
            .andExpect(jsonPath("$.[*].idUserAmmesso").value(hasItem(DEFAULT_ID_USER_AMMESSO)))
            .andExpect(jsonPath("$.[*].ruolo").value(hasItem(DEFAULT_RUOLO)))
            .andExpect(jsonPath("$.[*].idUserConcedente").value(hasItem(DEFAULT_ID_USER_CONCEDENTE)))
            .andExpect(jsonPath("$.[*].statoAssegnazione").value(hasItem(DEFAULT_STATO_ASSEGNAZIONE)));
    }
}
