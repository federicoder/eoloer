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

    private static final Long DEFAULT_ID_PRATICA_REF = 8L;
    private static final Long UPDATED_ID_PRATICA_REF = 7L;

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Long DEFAULT_STATO = 1L;
    private static final Long UPDATED_STATO = 2L;

    private static final Long DEFAULT_PRIORITARIO = 1L;
    private static final Long UPDATED_PRIORITARIO = 2L;

    private static final Long DEFAULT_PUBBLICO = 1L;
    private static final Long UPDATED_PUBBLICO = 2L;

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_CONDIVISIONE_PRATICA_REF = 1L;
    private static final Long UPDATED_ID_CONDIVISIONE_PRATICA_REF = 2L;

    private static final Long DEFAULT_ID_ASSEGNAZIONE_TASK_REF = 8L;
    private static final Long UPDATED_ID_ASSEGNAZIONE_TASK_REF = 7L;

    private static final Long DEFAULT_ID_INVITO_REF = 8L;
    private static final Long UPDATED_ID_INVITO_REF = 7L;

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
            .idPraticaRef(DEFAULT_ID_PRATICA_REF)
            .nome(DEFAULT_NOME)
            .stato(DEFAULT_STATO)
            .prioritario(DEFAULT_PRIORITARIO)
            .pubblico(DEFAULT_PUBBLICO)
            .version(DEFAULT_VERSION)
            .idCondivisionePraticaRef(DEFAULT_ID_CONDIVISIONE_PRATICA_REF)
            .idAssegnazioneTaskRef(DEFAULT_ID_ASSEGNAZIONE_TASK_REF)
            .idInvitoRef(DEFAULT_ID_INVITO_REF);
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
            .idPraticaRef(UPDATED_ID_PRATICA_REF)
            .nome(UPDATED_NOME)
            .stato(UPDATED_STATO)
            .prioritario(UPDATED_PRIORITARIO)
            .pubblico(UPDATED_PUBBLICO)
            .version(UPDATED_VERSION)
            .idCondivisionePraticaRef(UPDATED_ID_CONDIVISIONE_PRATICA_REF)
            .idAssegnazioneTaskRef(UPDATED_ID_ASSEGNAZIONE_TASK_REF)
            .idInvitoRef(UPDATED_ID_INVITO_REF);
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
        assertThat(testTask.getIdPraticaRef()).isEqualTo(DEFAULT_ID_PRATICA_REF);
        assertThat(testTask.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTask.getStato()).isEqualTo(DEFAULT_STATO);
        assertThat(testTask.getPrioritario()).isEqualTo(DEFAULT_PRIORITARIO);
        assertThat(testTask.getPubblico()).isEqualTo(DEFAULT_PUBBLICO);
        assertThat(testTask.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testTask.getIdCondivisionePraticaRef()).isEqualTo(DEFAULT_ID_CONDIVISIONE_PRATICA_REF);
        assertThat(testTask.getIdAssegnazioneTaskRef()).isEqualTo(DEFAULT_ID_ASSEGNAZIONE_TASK_REF);
        assertThat(testTask.getIdInvitoRef()).isEqualTo(DEFAULT_ID_INVITO_REF);

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
            .andExpect(jsonPath("$.[*].idPraticaRef").value(hasItem(DEFAULT_ID_PRATICA_REF.intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].stato").value(hasItem(DEFAULT_STATO.intValue())))
            .andExpect(jsonPath("$.[*].prioritario").value(hasItem(DEFAULT_PRIORITARIO.intValue())))
            .andExpect(jsonPath("$.[*].pubblico").value(hasItem(DEFAULT_PUBBLICO.intValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].idCondivisionePraticaRef").value(hasItem(DEFAULT_ID_CONDIVISIONE_PRATICA_REF.intValue())))
            .andExpect(jsonPath("$.[*].idAssegnazioneTaskRef").value(hasItem(DEFAULT_ID_ASSEGNAZIONE_TASK_REF.intValue())))
            .andExpect(jsonPath("$.[*].idInvitoRef").value(hasItem(DEFAULT_ID_INVITO_REF.intValue())));
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
            .andExpect(jsonPath("$.idPraticaRef").value(DEFAULT_ID_PRATICA_REF.intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.stato").value(DEFAULT_STATO.intValue()))
            .andExpect(jsonPath("$.prioritario").value(DEFAULT_PRIORITARIO.intValue()))
            .andExpect(jsonPath("$.pubblico").value(DEFAULT_PUBBLICO.intValue()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.idCondivisionePraticaRef").value(DEFAULT_ID_CONDIVISIONE_PRATICA_REF.intValue()))
            .andExpect(jsonPath("$.idAssegnazioneTaskRef").value(DEFAULT_ID_ASSEGNAZIONE_TASK_REF.intValue()))
            .andExpect(jsonPath("$.idInvitoRef").value(DEFAULT_ID_INVITO_REF.intValue()));
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
            .idPraticaRef(UPDATED_ID_PRATICA_REF)
            .nome(UPDATED_NOME)
            .stato(UPDATED_STATO)
            .prioritario(UPDATED_PRIORITARIO)
            .pubblico(UPDATED_PUBBLICO)
            .version(UPDATED_VERSION)
            .idCondivisionePraticaRef(UPDATED_ID_CONDIVISIONE_PRATICA_REF)
            .idAssegnazioneTaskRef(UPDATED_ID_ASSEGNAZIONE_TASK_REF)
            .idInvitoRef(UPDATED_ID_INVITO_REF);
        TaskDTO taskDTO = taskMapper.toDto(updatedTask);

        restTaskMockMvc.perform(put("/api/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taskDTO)))
            .andExpect(status().isOk());

        // Validate the Task in the database
        List<Task> taskList = taskRepository.findAll();
        assertThat(taskList).hasSize(databaseSizeBeforeUpdate);
        Task testTask = taskList.get(taskList.size() - 1);
        assertThat(testTask.getIdPraticaRef()).isEqualTo(UPDATED_ID_PRATICA_REF);
        assertThat(testTask.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTask.getStato()).isEqualTo(UPDATED_STATO);
        assertThat(testTask.getPrioritario()).isEqualTo(UPDATED_PRIORITARIO);
        assertThat(testTask.getPubblico()).isEqualTo(UPDATED_PUBBLICO);
        assertThat(testTask.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testTask.getIdCondivisionePraticaRef()).isEqualTo(UPDATED_ID_CONDIVISIONE_PRATICA_REF);
        assertThat(testTask.getIdAssegnazioneTaskRef()).isEqualTo(UPDATED_ID_ASSEGNAZIONE_TASK_REF);
        assertThat(testTask.getIdInvitoRef()).isEqualTo(UPDATED_ID_INVITO_REF);

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
            .andExpect(jsonPath("$.[*].idPraticaRef").value(hasItem(DEFAULT_ID_PRATICA_REF.intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].stato").value(hasItem(DEFAULT_STATO.intValue())))
            .andExpect(jsonPath("$.[*].prioritario").value(hasItem(DEFAULT_PRIORITARIO.intValue())))
            .andExpect(jsonPath("$.[*].pubblico").value(hasItem(DEFAULT_PUBBLICO.intValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].idCondivisionePraticaRef").value(hasItem(DEFAULT_ID_CONDIVISIONE_PRATICA_REF.intValue())))
            .andExpect(jsonPath("$.[*].idAssegnazioneTaskRef").value(hasItem(DEFAULT_ID_ASSEGNAZIONE_TASK_REF.intValue())))
            .andExpect(jsonPath("$.[*].idInvitoRef").value(hasItem(DEFAULT_ID_INVITO_REF.intValue())));
    }
}
