package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.NotaTask;
import it.maggioli.repository.NotaTaskRepository;
import it.maggioli.repository.search.NotaTaskSearchRepository;
import it.maggioli.service.NotaTaskService;
import it.maggioli.service.dto.NotaTaskDTO;
import it.maggioli.service.mapper.NotaTaskMapper;

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
 * Integration tests for the {@link NotaTaskResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class NotaTaskResourceIT {

    private static final Integer DEFAULT_ID_TASK = 8;
    private static final Integer UPDATED_ID_TASK = 7;

    private static final String DEFAULT_DATA = "AAAAAAAAAA";
    private static final String UPDATED_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_NOTA = "AAAAAAAAAA";
    private static final String UPDATED_NOTA = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    @Autowired
    private NotaTaskRepository notaTaskRepository;

    @Autowired
    private NotaTaskMapper notaTaskMapper;

    @Autowired
    private NotaTaskService notaTaskService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.NotaTaskSearchRepositoryMockConfiguration
     */
    @Autowired
    private NotaTaskSearchRepository mockNotaTaskSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNotaTaskMockMvc;

    private NotaTask notaTask;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotaTask createEntity(EntityManager em) {
        NotaTask notaTask = new NotaTask()
            .idTask(DEFAULT_ID_TASK)
            .data(DEFAULT_DATA)
            .nota(DEFAULT_NOTA)
            .version(DEFAULT_VERSION);
        return notaTask;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotaTask createUpdatedEntity(EntityManager em) {
        NotaTask notaTask = new NotaTask()
            .idTask(UPDATED_ID_TASK)
            .data(UPDATED_DATA)
            .nota(UPDATED_NOTA)
            .version(UPDATED_VERSION);
        return notaTask;
    }

    @BeforeEach
    public void initTest() {
        notaTask = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotaTask() throws Exception {
        int databaseSizeBeforeCreate = notaTaskRepository.findAll().size();
        // Create the NotaTask
        NotaTaskDTO notaTaskDTO = notaTaskMapper.toDto(notaTask);
        restNotaTaskMockMvc.perform(post("/api/nota-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notaTaskDTO)))
            .andExpect(status().isCreated());

        // Validate the NotaTask in the database
        List<NotaTask> notaTaskList = notaTaskRepository.findAll();
        assertThat(notaTaskList).hasSize(databaseSizeBeforeCreate + 1);
        NotaTask testNotaTask = notaTaskList.get(notaTaskList.size() - 1);
        assertThat(testNotaTask.getIdTask()).isEqualTo(DEFAULT_ID_TASK);
        assertThat(testNotaTask.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testNotaTask.getNota()).isEqualTo(DEFAULT_NOTA);
        assertThat(testNotaTask.getVersion()).isEqualTo(DEFAULT_VERSION);

        // Validate the NotaTask in Elasticsearch
        verify(mockNotaTaskSearchRepository, times(1)).save(testNotaTask);
    }

    @Test
    @Transactional
    public void createNotaTaskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notaTaskRepository.findAll().size();

        // Create the NotaTask with an existing ID
        notaTask.setId(1L);
        NotaTaskDTO notaTaskDTO = notaTaskMapper.toDto(notaTask);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotaTaskMockMvc.perform(post("/api/nota-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notaTaskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotaTask in the database
        List<NotaTask> notaTaskList = notaTaskRepository.findAll();
        assertThat(notaTaskList).hasSize(databaseSizeBeforeCreate);

        // Validate the NotaTask in Elasticsearch
        verify(mockNotaTaskSearchRepository, times(0)).save(notaTask);
    }


    @Test
    @Transactional
    public void getAllNotaTasks() throws Exception {
        // Initialize the database
        notaTaskRepository.saveAndFlush(notaTask);

        // Get all the notaTaskList
        restNotaTaskMockMvc.perform(get("/api/nota-tasks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notaTask.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTask").value(hasItem(DEFAULT_ID_TASK)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA)))
            .andExpect(jsonPath("$.[*].nota").value(hasItem(DEFAULT_NOTA)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }
    
    @Test
    @Transactional
    public void getNotaTask() throws Exception {
        // Initialize the database
        notaTaskRepository.saveAndFlush(notaTask);

        // Get the notaTask
        restNotaTaskMockMvc.perform(get("/api/nota-tasks/{id}", notaTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notaTask.getId().intValue()))
            .andExpect(jsonPath("$.idTask").value(DEFAULT_ID_TASK))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA))
            .andExpect(jsonPath("$.nota").value(DEFAULT_NOTA))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION));
    }
    @Test
    @Transactional
    public void getNonExistingNotaTask() throws Exception {
        // Get the notaTask
        restNotaTaskMockMvc.perform(get("/api/nota-tasks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotaTask() throws Exception {
        // Initialize the database
        notaTaskRepository.saveAndFlush(notaTask);

        int databaseSizeBeforeUpdate = notaTaskRepository.findAll().size();

        // Update the notaTask
        NotaTask updatedNotaTask = notaTaskRepository.findById(notaTask.getId()).get();
        // Disconnect from session so that the updates on updatedNotaTask are not directly saved in db
        em.detach(updatedNotaTask);
        updatedNotaTask
            .idTask(UPDATED_ID_TASK)
            .data(UPDATED_DATA)
            .nota(UPDATED_NOTA)
            .version(UPDATED_VERSION);
        NotaTaskDTO notaTaskDTO = notaTaskMapper.toDto(updatedNotaTask);

        restNotaTaskMockMvc.perform(put("/api/nota-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notaTaskDTO)))
            .andExpect(status().isOk());

        // Validate the NotaTask in the database
        List<NotaTask> notaTaskList = notaTaskRepository.findAll();
        assertThat(notaTaskList).hasSize(databaseSizeBeforeUpdate);
        NotaTask testNotaTask = notaTaskList.get(notaTaskList.size() - 1);
        assertThat(testNotaTask.getIdTask()).isEqualTo(UPDATED_ID_TASK);
        assertThat(testNotaTask.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testNotaTask.getNota()).isEqualTo(UPDATED_NOTA);
        assertThat(testNotaTask.getVersion()).isEqualTo(UPDATED_VERSION);

        // Validate the NotaTask in Elasticsearch
        verify(mockNotaTaskSearchRepository, times(1)).save(testNotaTask);
    }

    @Test
    @Transactional
    public void updateNonExistingNotaTask() throws Exception {
        int databaseSizeBeforeUpdate = notaTaskRepository.findAll().size();

        // Create the NotaTask
        NotaTaskDTO notaTaskDTO = notaTaskMapper.toDto(notaTask);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotaTaskMockMvc.perform(put("/api/nota-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notaTaskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotaTask in the database
        List<NotaTask> notaTaskList = notaTaskRepository.findAll();
        assertThat(notaTaskList).hasSize(databaseSizeBeforeUpdate);

        // Validate the NotaTask in Elasticsearch
        verify(mockNotaTaskSearchRepository, times(0)).save(notaTask);
    }

    @Test
    @Transactional
    public void deleteNotaTask() throws Exception {
        // Initialize the database
        notaTaskRepository.saveAndFlush(notaTask);

        int databaseSizeBeforeDelete = notaTaskRepository.findAll().size();

        // Delete the notaTask
        restNotaTaskMockMvc.perform(delete("/api/nota-tasks/{id}", notaTask.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NotaTask> notaTaskList = notaTaskRepository.findAll();
        assertThat(notaTaskList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the NotaTask in Elasticsearch
        verify(mockNotaTaskSearchRepository, times(1)).deleteById(notaTask.getId());
    }

    @Test
    @Transactional
    public void searchNotaTask() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        notaTaskRepository.saveAndFlush(notaTask);
        when(mockNotaTaskSearchRepository.search(queryStringQuery("id:" + notaTask.getId())))
            .thenReturn(Collections.singletonList(notaTask));

        // Search the notaTask
        restNotaTaskMockMvc.perform(get("/api/_search/nota-tasks?query=id:" + notaTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notaTask.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTask").value(hasItem(DEFAULT_ID_TASK)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA)))
            .andExpect(jsonPath("$.[*].nota").value(hasItem(DEFAULT_NOTA)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }
}
