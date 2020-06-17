package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.Task;
import it.maggioli.repository.TaskRepository;
import it.maggioli.repository.search.TaskSearchRepository;
import it.maggioli.service.TaskService;
import it.maggioli.service.dto.TaskDTO;
import it.maggioli.service.mapper.TaskMapper;

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
 * Integration tests for the {@link TaskResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class TaskResourceIT {

    private static final Integer DEFAULT_ID_PRATICA = 8;
    private static final Integer UPDATED_ID_PRATICA = 7;

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATO = 1;
    private static final Integer UPDATED_STATO = 2;

    private static final Integer DEFAULT_PRIORITARIO = 1;
    private static final Integer UPDATED_PRIORITARIO = 2;

    private static final Integer DEFAULT_PUBBLICO = 1;
    private static final Integer UPDATED_PUBBLICO = 2;

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final Integer DEFAULT_CONDIVISIONE_PRATICA_ID = 1;
    private static final Integer UPDATED_CONDIVISIONE_PRATICA_ID = 2;

    private static final Integer DEFAULT_ASSEGNAZIONE_TASK_ID = 8;
    private static final Integer UPDATED_ASSEGNAZIONE_TASK_ID = 7;

    private static final Integer DEFAULT_INVITO_ID = 8;
    private static final Integer UPDATED_INVITO_ID = 7;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskService taskService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.TaskSearchRepositoryMockConfiguration
     */
    @Autowired
    private TaskSearchRepository mockTaskSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaskMockMvc;

    private Task task;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Task createEntity(EntityManager em) {
        Task task = new Task()
            .idPratica(DEFAULT_ID_PRATICA)
            .nome(DEFAULT_NOME)
            .stato(DEFAULT_STATO)
            .prioritario(DEFAULT_PRIORITARIO)
            .pubblico(DEFAULT_PUBBLICO)
            .version(DEFAULT_VERSION)
            .condivisionePraticaId(DEFAULT_CONDIVISIONE_PRATICA_ID)
            .assegnazioneTaskId(DEFAULT_ASSEGNAZIONE_TASK_ID)
            .invitoId(DEFAULT_INVITO_ID);
        return task;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Task createUpdatedEntity(EntityManager em) {
        Task task = new Task()
            .idPratica(UPDATED_ID_PRATICA)
            .nome(UPDATED_NOME)
            .stato(UPDATED_STATO)
            .prioritario(UPDATED_PRIORITARIO)
            .pubblico(UPDATED_PUBBLICO)
            .version(UPDATED_VERSION)
            .condivisionePraticaId(UPDATED_CONDIVISIONE_PRATICA_ID)
            .assegnazioneTaskId(UPDATED_ASSEGNAZIONE_TASK_ID)
            .invitoId(UPDATED_INVITO_ID);
        return task;
    }

    @BeforeEach
    public void initTest() {
        task = createEntity(em);
    }

    @Test
    @Transactional
    public void createTask() throws Exception {
        int databaseSizeBeforeCreate = taskRepository.findAll().size();
        // Create the Task
        TaskDTO taskDTO = taskMapper.toDto(task);
        restTaskMockMvc.perform(post("/api/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taskDTO)))
            .andExpect(status().isCreated());

        // Validate the Task in the database
        List<Task> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeCreate + 1);
        Task testTask = taskList.get(taskList.size() - 1);
        assertThat(testTask.getIdPratica()).isEqualTo(DEFAULT_ID_PRATICA);
        assertThat(testTask.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTask.getStato()).isEqualTo(DEFAULT_STATO);
        assertThat(testTask.getPrioritario()).isEqualTo(DEFAULT_PRIORITARIO);
        assertThat(testTask.getPubblico()).isEqualTo(DEFAULT_PUBBLICO);
        assertThat(testTask.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testTask.getCondivisionePraticaId()).isEqualTo(DEFAULT_CONDIVISIONE_PRATICA_ID);
        assertThat(testTask.getAssegnazioneTaskId()).isEqualTo(DEFAULT_ASSEGNAZIONE_TASK_ID);
        assertThat(testTask.getInvitoId()).isEqualTo(DEFAULT_INVITO_ID);

        // Validate the Task in Elasticsearch
        verify(mockTaskSearchRepository, times(1)).save(testTask);
    }

    @Test
    @Transactional
    public void createTaskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = taskRepository.findAll().size();

        // Create the Task with an existing ID
        task.setId(1L);
        TaskDTO taskDTO = taskMapper.toDto(task);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaskMockMvc.perform(post("/api/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Task in the database
        List<Task> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeCreate);

        // Validate the Task in Elasticsearch
        verify(mockTaskSearchRepository, times(0)).save(task);
    }


    @Test
    @Transactional
    public void getAllTasks() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get all the taskList
        restTaskMockMvc.perform(get("/api/tasks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(task.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPratica").value(hasItem(DEFAULT_ID_PRATICA)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].stato").value(hasItem(DEFAULT_STATO)))
            .andExpect(jsonPath("$.[*].prioritario").value(hasItem(DEFAULT_PRIORITARIO)))
            .andExpect(jsonPath("$.[*].pubblico").value(hasItem(DEFAULT_PUBBLICO)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].condivisionePraticaId").value(hasItem(DEFAULT_CONDIVISIONE_PRATICA_ID)))
            .andExpect(jsonPath("$.[*].assegnazioneTaskId").value(hasItem(DEFAULT_ASSEGNAZIONE_TASK_ID)))
            .andExpect(jsonPath("$.[*].invitoId").value(hasItem(DEFAULT_INVITO_ID)));
    }
    
    @Test
    @Transactional
    public void getTask() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        // Get the task
        restTaskMockMvc.perform(get("/api/tasks/{id}", task.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(task.getId().intValue()))
            .andExpect(jsonPath("$.idPratica").value(DEFAULT_ID_PRATICA))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.stato").value(DEFAULT_STATO))
            .andExpect(jsonPath("$.prioritario").value(DEFAULT_PRIORITARIO))
            .andExpect(jsonPath("$.pubblico").value(DEFAULT_PUBBLICO))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.condivisionePraticaId").value(DEFAULT_CONDIVISIONE_PRATICA_ID))
            .andExpect(jsonPath("$.assegnazioneTaskId").value(DEFAULT_ASSEGNAZIONE_TASK_ID))
            .andExpect(jsonPath("$.invitoId").value(DEFAULT_INVITO_ID));
    }
    @Test
    @Transactional
    public void getNonExistingTask() throws Exception {
        // Get the task
        restTaskMockMvc.perform(get("/api/tasks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTask() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        int databaseSizeBeforeUpdate = taskRepository.findAll().size();

        // Update the task
        Task updatedTask = taskRepository.findById(task.getId()).get();
        // Disconnect from session so that the updates on updatedTask are not directly saved in db
        em.detach(updatedTask);
        updatedTask
            .idPratica(UPDATED_ID_PRATICA)
            .nome(UPDATED_NOME)
            .stato(UPDATED_STATO)
            .prioritario(UPDATED_PRIORITARIO)
            .pubblico(UPDATED_PUBBLICO)
            .version(UPDATED_VERSION)
            .condivisionePraticaId(UPDATED_CONDIVISIONE_PRATICA_ID)
            .assegnazioneTaskId(UPDATED_ASSEGNAZIONE_TASK_ID)
            .invitoId(UPDATED_INVITO_ID);
        TaskDTO taskDTO = taskMapper.toDto(updatedTask);

        restTaskMockMvc.perform(put("/api/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taskDTO)))
            .andExpect(status().isOk());

        // Validate the Task in the database
        List<Task> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeUpdate);
        Task testTask = taskList.get(taskList.size() - 1);
        assertThat(testTask.getIdPratica()).isEqualTo(UPDATED_ID_PRATICA);
        assertThat(testTask.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTask.getStato()).isEqualTo(UPDATED_STATO);
        assertThat(testTask.getPrioritario()).isEqualTo(UPDATED_PRIORITARIO);
        assertThat(testTask.getPubblico()).isEqualTo(UPDATED_PUBBLICO);
        assertThat(testTask.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testTask.getCondivisionePraticaId()).isEqualTo(UPDATED_CONDIVISIONE_PRATICA_ID);
        assertThat(testTask.getAssegnazioneTaskId()).isEqualTo(UPDATED_ASSEGNAZIONE_TASK_ID);
        assertThat(testTask.getInvitoId()).isEqualTo(UPDATED_INVITO_ID);

        // Validate the Task in Elasticsearch
        verify(mockTaskSearchRepository, times(1)).save(testTask);
    }

    @Test
    @Transactional
    public void updateNonExistingTask() throws Exception {
        int databaseSizeBeforeUpdate = taskRepository.findAll().size();

        // Create the Task
        TaskDTO taskDTO = taskMapper.toDto(task);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaskMockMvc.perform(put("/api/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Task in the database
        List<Task> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Task in Elasticsearch
        verify(mockTaskSearchRepository, times(0)).save(task);
    }

    @Test
    @Transactional
    public void deleteTask() throws Exception {
        // Initialize the database
        taskRepository.saveAndFlush(task);

        int databaseSizeBeforeDelete = taskRepository.findAll().size();

        // Delete the task
        restTaskMockMvc.perform(delete("/api/tasks/{id}", task.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Task> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Task in Elasticsearch
        verify(mockTaskSearchRepository, times(1)).deleteById(task.getId());
    }

    @Test
    @Transactional
    public void searchTask() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        taskRepository.saveAndFlush(task);
        when(mockTaskSearchRepository.search(queryStringQuery("id:" + task.getId())))
            .thenReturn(Collections.singletonList(task));

        // Search the task
        restTaskMockMvc.perform(get("/api/_search/tasks?query=id:" + task.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(task.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPratica").value(hasItem(DEFAULT_ID_PRATICA)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].stato").value(hasItem(DEFAULT_STATO)))
            .andExpect(jsonPath("$.[*].prioritario").value(hasItem(DEFAULT_PRIORITARIO)))
            .andExpect(jsonPath("$.[*].pubblico").value(hasItem(DEFAULT_PUBBLICO)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].condivisionePraticaId").value(hasItem(DEFAULT_CONDIVISIONE_PRATICA_ID)))
            .andExpect(jsonPath("$.[*].assegnazioneTaskId").value(hasItem(DEFAULT_ASSEGNAZIONE_TASK_ID)))
            .andExpect(jsonPath("$.[*].invitoId").value(hasItem(DEFAULT_INVITO_ID)));
    }
}
