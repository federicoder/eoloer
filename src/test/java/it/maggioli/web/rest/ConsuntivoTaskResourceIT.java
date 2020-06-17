package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.ConsuntivoTask;
import it.maggioli.repository.ConsuntivoTaskRepository;
import it.maggioli.repository.search.ConsuntivoTaskSearchRepository;
import it.maggioli.service.ConsuntivoTaskService;
import it.maggioli.service.dto.ConsuntivoTaskDTO;
import it.maggioli.service.mapper.ConsuntivoTaskMapper;

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
 * Integration tests for the {@link ConsuntivoTaskResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ConsuntivoTaskResourceIT {

    private static final Integer DEFAULT_ID_TASK_REF = 8;
    private static final Integer UPDATED_ID_TASK_REF = 7;

    private static final String DEFAULT_DATA_INIZIO = "AAAAAAAAAA";
    private static final String UPDATED_DATA_INIZIO = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_FINE = "AAAAAAAAAA";
    private static final String UPDATED_DATA_FINE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TIME_LINE = 1;
    private static final Integer UPDATED_TIME_LINE = 2;

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    @Autowired
    private ConsuntivoTaskRepository consuntivoTaskRepository;

    @Autowired
    private ConsuntivoTaskMapper consuntivoTaskMapper;

    @Autowired
    private ConsuntivoTaskService consuntivoTaskService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.ConsuntivoTaskSearchRepositoryMockConfiguration
     */
    @Autowired
    private ConsuntivoTaskSearchRepository mockConsuntivoTaskSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restConsuntivoTaskMockMvc;

    private ConsuntivoTask consuntivoTask;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConsuntivoTask createEntity(EntityManager em) {
        ConsuntivoTask consuntivoTask = new ConsuntivoTask()
            .idTaskRef(DEFAULT_ID_TASK_REF)
            .dataInizio(DEFAULT_DATA_INIZIO)
            .dataFine(DEFAULT_DATA_FINE)
            .timeLine(DEFAULT_TIME_LINE)
            .version(DEFAULT_VERSION);
        return consuntivoTask;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConsuntivoTask createUpdatedEntity(EntityManager em) {
        ConsuntivoTask consuntivoTask = new ConsuntivoTask()
            .idTaskRef(UPDATED_ID_TASK_REF)
            .dataInizio(UPDATED_DATA_INIZIO)
            .dataFine(UPDATED_DATA_FINE)
            .timeLine(UPDATED_TIME_LINE)
            .version(UPDATED_VERSION);
        return consuntivoTask;
    }

    @BeforeEach
    public void initTest() {
        consuntivoTask = createEntity(em);
    }

    @Test
    @Transactional
    public void createConsuntivoTask() throws Exception {
        int databaseSizeBeforeCreate = consuntivoTaskRepository.findAll().size();
        // Create the ConsuntivoTask
        ConsuntivoTaskDTO consuntivoTaskDTO = consuntivoTaskMapper.toDto(consuntivoTask);
        restConsuntivoTaskMockMvc.perform(post("/api/consuntivo-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(consuntivoTaskDTO)))
            .andExpect(status().isCreated());

        // Validate the ConsuntivoTask in the database
        List<ConsuntivoTask> consuntivoTaskList = consuntivoTaskRepository.findAll();
        assertThat(consuntivoTaskList).hasSize(databaseSizeBeforeCreate + 1);
        ConsuntivoTask testConsuntivoTask = consuntivoTaskList.get(consuntivoTaskList.size() - 1);
        assertThat(testConsuntivoTask.getIdTaskRef()).isEqualTo(DEFAULT_ID_TASK_REF);
        assertThat(testConsuntivoTask.getDataInizio()).isEqualTo(DEFAULT_DATA_INIZIO);
        assertThat(testConsuntivoTask.getDataFine()).isEqualTo(DEFAULT_DATA_FINE);
        assertThat(testConsuntivoTask.getTimeLine()).isEqualTo(DEFAULT_TIME_LINE);
        assertThat(testConsuntivoTask.getVersion()).isEqualTo(DEFAULT_VERSION);

        // Validate the ConsuntivoTask in Elasticsearch
        verify(mockConsuntivoTaskSearchRepository, times(1)).save(testConsuntivoTask);
    }

    @Test
    @Transactional
    public void createConsuntivoTaskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = consuntivoTaskRepository.findAll().size();

        // Create the ConsuntivoTask with an existing ID
        consuntivoTask.setId(1L);
        ConsuntivoTaskDTO consuntivoTaskDTO = consuntivoTaskMapper.toDto(consuntivoTask);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConsuntivoTaskMockMvc.perform(post("/api/consuntivo-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(consuntivoTaskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConsuntivoTask in the database
        List<ConsuntivoTask> consuntivoTaskList = consuntivoTaskRepository.findAll();
        assertThat(consuntivoTaskList).hasSize(databaseSizeBeforeCreate);

        // Validate the ConsuntivoTask in Elasticsearch
        verify(mockConsuntivoTaskSearchRepository, times(0)).save(consuntivoTask);
    }


    @Test
    @Transactional
    public void checkIdTaskRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = consuntivoTaskRepository.findAll().size();
        // set the field null
        consuntivoTask.setIdTaskRef(null);

        // Create the ConsuntivoTask, which fails.
        ConsuntivoTaskDTO consuntivoTaskDTO = consuntivoTaskMapper.toDto(consuntivoTask);


        restConsuntivoTaskMockMvc.perform(post("/api/consuntivo-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(consuntivoTaskDTO)))
            .andExpect(status().isBadRequest());

        List<ConsuntivoTask> consuntivoTaskList = consuntivoTaskRepository.findAll();
        assertThat(consuntivoTaskList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllConsuntivoTasks() throws Exception {
        // Initialize the database
        consuntivoTaskRepository.saveAndFlush(consuntivoTask);

        // Get all the consuntivoTaskList
        restConsuntivoTaskMockMvc.perform(get("/api/consuntivo-tasks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(consuntivoTask.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTaskRef").value(hasItem(DEFAULT_ID_TASK_REF)))
            .andExpect(jsonPath("$.[*].dataInizio").value(hasItem(DEFAULT_DATA_INIZIO)))
            .andExpect(jsonPath("$.[*].dataFine").value(hasItem(DEFAULT_DATA_FINE)))
            .andExpect(jsonPath("$.[*].timeLine").value(hasItem(DEFAULT_TIME_LINE)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }
    
    @Test
    @Transactional
    public void getConsuntivoTask() throws Exception {
        // Initialize the database
        consuntivoTaskRepository.saveAndFlush(consuntivoTask);

        // Get the consuntivoTask
        restConsuntivoTaskMockMvc.perform(get("/api/consuntivo-tasks/{id}", consuntivoTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(consuntivoTask.getId().intValue()))
            .andExpect(jsonPath("$.idTaskRef").value(DEFAULT_ID_TASK_REF))
            .andExpect(jsonPath("$.dataInizio").value(DEFAULT_DATA_INIZIO))
            .andExpect(jsonPath("$.dataFine").value(DEFAULT_DATA_FINE))
            .andExpect(jsonPath("$.timeLine").value(DEFAULT_TIME_LINE))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION));
    }
    @Test
    @Transactional
    public void getNonExistingConsuntivoTask() throws Exception {
        // Get the consuntivoTask
        restConsuntivoTaskMockMvc.perform(get("/api/consuntivo-tasks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConsuntivoTask() throws Exception {
        // Initialize the database
        consuntivoTaskRepository.saveAndFlush(consuntivoTask);

        int databaseSizeBeforeUpdate = consuntivoTaskRepository.findAll().size();

        // Update the consuntivoTask
        ConsuntivoTask updatedConsuntivoTask = consuntivoTaskRepository.findById(consuntivoTask.getId()).get();
        // Disconnect from session so that the updates on updatedConsuntivoTask are not directly saved in db
        em.detach(updatedConsuntivoTask);
        updatedConsuntivoTask
            .idTaskRef(UPDATED_ID_TASK_REF)
            .dataInizio(UPDATED_DATA_INIZIO)
            .dataFine(UPDATED_DATA_FINE)
            .timeLine(UPDATED_TIME_LINE)
            .version(UPDATED_VERSION);
        ConsuntivoTaskDTO consuntivoTaskDTO = consuntivoTaskMapper.toDto(updatedConsuntivoTask);

        restConsuntivoTaskMockMvc.perform(put("/api/consuntivo-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(consuntivoTaskDTO)))
            .andExpect(status().isOk());

        // Validate the ConsuntivoTask in the database
        List<ConsuntivoTask> consuntivoTaskList = consuntivoTaskRepository.findAll();
        assertThat(consuntivoTaskList).hasSize(databaseSizeBeforeUpdate);
        ConsuntivoTask testConsuntivoTask = consuntivoTaskList.get(consuntivoTaskList.size() - 1);
        assertThat(testConsuntivoTask.getIdTaskRef()).isEqualTo(UPDATED_ID_TASK_REF);
        assertThat(testConsuntivoTask.getDataInizio()).isEqualTo(UPDATED_DATA_INIZIO);
        assertThat(testConsuntivoTask.getDataFine()).isEqualTo(UPDATED_DATA_FINE);
        assertThat(testConsuntivoTask.getTimeLine()).isEqualTo(UPDATED_TIME_LINE);
        assertThat(testConsuntivoTask.getVersion()).isEqualTo(UPDATED_VERSION);

        // Validate the ConsuntivoTask in Elasticsearch
        verify(mockConsuntivoTaskSearchRepository, times(1)).save(testConsuntivoTask);
    }

    @Test
    @Transactional
    public void updateNonExistingConsuntivoTask() throws Exception {
        int databaseSizeBeforeUpdate = consuntivoTaskRepository.findAll().size();

        // Create the ConsuntivoTask
        ConsuntivoTaskDTO consuntivoTaskDTO = consuntivoTaskMapper.toDto(consuntivoTask);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConsuntivoTaskMockMvc.perform(put("/api/consuntivo-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(consuntivoTaskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConsuntivoTask in the database
        List<ConsuntivoTask> consuntivoTaskList = consuntivoTaskRepository.findAll();
        assertThat(consuntivoTaskList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ConsuntivoTask in Elasticsearch
        verify(mockConsuntivoTaskSearchRepository, times(0)).save(consuntivoTask);
    }

    @Test
    @Transactional
    public void deleteConsuntivoTask() throws Exception {
        // Initialize the database
        consuntivoTaskRepository.saveAndFlush(consuntivoTask);

        int databaseSizeBeforeDelete = consuntivoTaskRepository.findAll().size();

        // Delete the consuntivoTask
        restConsuntivoTaskMockMvc.perform(delete("/api/consuntivo-tasks/{id}", consuntivoTask.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ConsuntivoTask> consuntivoTaskList = consuntivoTaskRepository.findAll();
        assertThat(consuntivoTaskList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ConsuntivoTask in Elasticsearch
        verify(mockConsuntivoTaskSearchRepository, times(1)).deleteById(consuntivoTask.getId());
    }

    @Test
    @Transactional
    public void searchConsuntivoTask() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        consuntivoTaskRepository.saveAndFlush(consuntivoTask);
        when(mockConsuntivoTaskSearchRepository.search(queryStringQuery("id:" + consuntivoTask.getId())))
            .thenReturn(Collections.singletonList(consuntivoTask));

        // Search the consuntivoTask
        restConsuntivoTaskMockMvc.perform(get("/api/_search/consuntivo-tasks?query=id:" + consuntivoTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(consuntivoTask.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTaskRef").value(hasItem(DEFAULT_ID_TASK_REF)))
            .andExpect(jsonPath("$.[*].dataInizio").value(hasItem(DEFAULT_DATA_INIZIO)))
            .andExpect(jsonPath("$.[*].dataFine").value(hasItem(DEFAULT_DATA_FINE)))
            .andExpect(jsonPath("$.[*].timeLine").value(hasItem(DEFAULT_TIME_LINE)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }
}
