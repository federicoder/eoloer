package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.PrevisioneTask;
import it.maggioli.repository.PrevisioneTaskRepository;
import it.maggioli.repository.search.PrevisioneTaskSearchRepository;
import it.maggioli.service.PrevisioneTaskService;
import it.maggioli.service.dto.PrevisioneTaskDTO;
import it.maggioli.service.mapper.PrevisioneTaskMapper;

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
 * Integration tests for the {@link PrevisioneTaskResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class PrevisioneTaskResourceIT {

    private static final Integer DEFAULT_ID_TASK = 8;
    private static final Integer UPDATED_ID_TASK = 7;

    private static final Integer DEFAULT_QNT_ORDINE = 8;
    private static final Integer UPDATED_QNT_ORDINE = 7;

    private static final Integer DEFAULT_PRC_PREVISIONE = 1;
    private static final Integer UPDATED_PRC_PREVISIONE = 0;

    private static final Integer DEFAULT_CHECK_LIST = 1;
    private static final Integer UPDATED_CHECK_LIST = 2;

    private static final Integer DEFAULT_ID_TASK_MILESTONE = 1;
    private static final Integer UPDATED_ID_TASK_MILESTONE = 2;

    private static final String DEFAULT_TIPO_TASK = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_TASK = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    @Autowired
    private PrevisioneTaskRepository previsioneTaskRepository;

    @Autowired
    private PrevisioneTaskMapper previsioneTaskMapper;

    @Autowired
    private PrevisioneTaskService previsioneTaskService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.PrevisioneTaskSearchRepositoryMockConfiguration
     */
    @Autowired
    private PrevisioneTaskSearchRepository mockPrevisioneTaskSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrevisioneTaskMockMvc;

    private PrevisioneTask previsioneTask;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrevisioneTask createEntity(EntityManager em) {
        PrevisioneTask previsioneTask = new PrevisioneTask()
            .idTask(DEFAULT_ID_TASK)
            .qntOrdine(DEFAULT_QNT_ORDINE)
            .prcPrevisione(DEFAULT_PRC_PREVISIONE)
            .checkList(DEFAULT_CHECK_LIST)
            .idTaskMilestone(DEFAULT_ID_TASK_MILESTONE)
            .tipoTask(DEFAULT_TIPO_TASK)
            .version(DEFAULT_VERSION);
        return previsioneTask;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PrevisioneTask createUpdatedEntity(EntityManager em) {
        PrevisioneTask previsioneTask = new PrevisioneTask()
            .idTask(UPDATED_ID_TASK)
            .qntOrdine(UPDATED_QNT_ORDINE)
            .prcPrevisione(UPDATED_PRC_PREVISIONE)
            .checkList(UPDATED_CHECK_LIST)
            .idTaskMilestone(UPDATED_ID_TASK_MILESTONE)
            .tipoTask(UPDATED_TIPO_TASK)
            .version(UPDATED_VERSION);
        return previsioneTask;
    }

    @BeforeEach
    public void initTest() {
        previsioneTask = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrevisioneTask() throws Exception {
        int databaseSizeBeforeCreate = previsioneTaskRepository.findAll().size();
        // Create the PrevisioneTask
        PrevisioneTaskDTO previsioneTaskDTO = previsioneTaskMapper.toDto(previsioneTask);
        restPrevisioneTaskMockMvc.perform(post("/api/previsione-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsioneTaskDTO)))
            .andExpect(status().isCreated());

        // Validate the PrevisioneTask in the database
        List<PrevisioneTask> previsioneTaskList = previsioneTaskRepository.findAll();
        assertThat(previsioneTaskList).hasSize(databaseSizeBeforeCreate + 1);
        PrevisioneTask testPrevisioneTask = previsioneTaskList.get(previsioneTaskList.size() - 1);
        assertThat(testPrevisioneTask.getIdTask()).isEqualTo(DEFAULT_ID_TASK);
        assertThat(testPrevisioneTask.getQntOrdine()).isEqualTo(DEFAULT_QNT_ORDINE);
        assertThat(testPrevisioneTask.getPrcPrevisione()).isEqualTo(DEFAULT_PRC_PREVISIONE);
        assertThat(testPrevisioneTask.getCheckList()).isEqualTo(DEFAULT_CHECK_LIST);
        assertThat(testPrevisioneTask.getIdTaskMilestone()).isEqualTo(DEFAULT_ID_TASK_MILESTONE);
        assertThat(testPrevisioneTask.getTipoTask()).isEqualTo(DEFAULT_TIPO_TASK);
        assertThat(testPrevisioneTask.getVersion()).isEqualTo(DEFAULT_VERSION);

        // Validate the PrevisioneTask in Elasticsearch
        verify(mockPrevisioneTaskSearchRepository, times(1)).save(testPrevisioneTask);
    }

    @Test
    @Transactional
    public void createPrevisioneTaskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = previsioneTaskRepository.findAll().size();

        // Create the PrevisioneTask with an existing ID
        previsioneTask.setId(1L);
        PrevisioneTaskDTO previsioneTaskDTO = previsioneTaskMapper.toDto(previsioneTask);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrevisioneTaskMockMvc.perform(post("/api/previsione-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsioneTaskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PrevisioneTask in the database
        List<PrevisioneTask> previsioneTaskList = previsioneTaskRepository.findAll();
        assertThat(previsioneTaskList).hasSize(databaseSizeBeforeCreate);

        // Validate the PrevisioneTask in Elasticsearch
        verify(mockPrevisioneTaskSearchRepository, times(0)).save(previsioneTask);
    }


    @Test
    @Transactional
    public void checkIdTaskIsRequired() throws Exception {
        int databaseSizeBeforeTest = previsioneTaskRepository.findAll().size();
        // set the field null
        previsioneTask.setIdTask(null);

        // Create the PrevisioneTask, which fails.
        PrevisioneTaskDTO previsioneTaskDTO = previsioneTaskMapper.toDto(previsioneTask);


        restPrevisioneTaskMockMvc.perform(post("/api/previsione-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsioneTaskDTO)))
            .andExpect(status().isBadRequest());

        List<PrevisioneTask> previsioneTaskList = previsioneTaskRepository.findAll();
        assertThat(previsioneTaskList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPrevisioneTasks() throws Exception {
        // Initialize the database
        previsioneTaskRepository.saveAndFlush(previsioneTask);

        // Get all the previsioneTaskList
        restPrevisioneTaskMockMvc.perform(get("/api/previsione-tasks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(previsioneTask.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTask").value(hasItem(DEFAULT_ID_TASK)))
            .andExpect(jsonPath("$.[*].qntOrdine").value(hasItem(DEFAULT_QNT_ORDINE)))
            .andExpect(jsonPath("$.[*].prcPrevisione").value(hasItem(DEFAULT_PRC_PREVISIONE)))
            .andExpect(jsonPath("$.[*].checkList").value(hasItem(DEFAULT_CHECK_LIST)))
            .andExpect(jsonPath("$.[*].idTaskMilestone").value(hasItem(DEFAULT_ID_TASK_MILESTONE)))
            .andExpect(jsonPath("$.[*].tipoTask").value(hasItem(DEFAULT_TIPO_TASK)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }
    
    @Test
    @Transactional
    public void getPrevisioneTask() throws Exception {
        // Initialize the database
        previsioneTaskRepository.saveAndFlush(previsioneTask);

        // Get the previsioneTask
        restPrevisioneTaskMockMvc.perform(get("/api/previsione-tasks/{id}", previsioneTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(previsioneTask.getId().intValue()))
            .andExpect(jsonPath("$.idTask").value(DEFAULT_ID_TASK))
            .andExpect(jsonPath("$.qntOrdine").value(DEFAULT_QNT_ORDINE))
            .andExpect(jsonPath("$.prcPrevisione").value(DEFAULT_PRC_PREVISIONE))
            .andExpect(jsonPath("$.checkList").value(DEFAULT_CHECK_LIST))
            .andExpect(jsonPath("$.idTaskMilestone").value(DEFAULT_ID_TASK_MILESTONE))
            .andExpect(jsonPath("$.tipoTask").value(DEFAULT_TIPO_TASK))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION));
    }
    @Test
    @Transactional
    public void getNonExistingPrevisioneTask() throws Exception {
        // Get the previsioneTask
        restPrevisioneTaskMockMvc.perform(get("/api/previsione-tasks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrevisioneTask() throws Exception {
        // Initialize the database
        previsioneTaskRepository.saveAndFlush(previsioneTask);

        int databaseSizeBeforeUpdate = previsioneTaskRepository.findAll().size();

        // Update the previsioneTask
        PrevisioneTask updatedPrevisioneTask = previsioneTaskRepository.findById(previsioneTask.getId()).get();
        // Disconnect from session so that the updates on updatedPrevisioneTask are not directly saved in db
        em.detach(updatedPrevisioneTask);
        updatedPrevisioneTask
            .idTask(UPDATED_ID_TASK)
            .qntOrdine(UPDATED_QNT_ORDINE)
            .prcPrevisione(UPDATED_PRC_PREVISIONE)
            .checkList(UPDATED_CHECK_LIST)
            .idTaskMilestone(UPDATED_ID_TASK_MILESTONE)
            .tipoTask(UPDATED_TIPO_TASK)
            .version(UPDATED_VERSION);
        PrevisioneTaskDTO previsioneTaskDTO = previsioneTaskMapper.toDto(updatedPrevisioneTask);

        restPrevisioneTaskMockMvc.perform(put("/api/previsione-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsioneTaskDTO)))
            .andExpect(status().isOk());

        // Validate the PrevisioneTask in the database
        List<PrevisioneTask> previsioneTaskList = previsioneTaskRepository.findAll();
        assertThat(previsioneTaskList).hasSize(databaseSizeBeforeUpdate);
        PrevisioneTask testPrevisioneTask = previsioneTaskList.get(previsioneTaskList.size() - 1);
        assertThat(testPrevisioneTask.getIdTask()).isEqualTo(UPDATED_ID_TASK);
        assertThat(testPrevisioneTask.getQntOrdine()).isEqualTo(UPDATED_QNT_ORDINE);
        assertThat(testPrevisioneTask.getPrcPrevisione()).isEqualTo(UPDATED_PRC_PREVISIONE);
        assertThat(testPrevisioneTask.getCheckList()).isEqualTo(UPDATED_CHECK_LIST);
        assertThat(testPrevisioneTask.getIdTaskMilestone()).isEqualTo(UPDATED_ID_TASK_MILESTONE);
        assertThat(testPrevisioneTask.getTipoTask()).isEqualTo(UPDATED_TIPO_TASK);
        assertThat(testPrevisioneTask.getVersion()).isEqualTo(UPDATED_VERSION);

        // Validate the PrevisioneTask in Elasticsearch
        verify(mockPrevisioneTaskSearchRepository, times(1)).save(testPrevisioneTask);
    }

    @Test
    @Transactional
    public void updateNonExistingPrevisioneTask() throws Exception {
        int databaseSizeBeforeUpdate = previsioneTaskRepository.findAll().size();

        // Create the PrevisioneTask
        PrevisioneTaskDTO previsioneTaskDTO = previsioneTaskMapper.toDto(previsioneTask);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrevisioneTaskMockMvc.perform(put("/api/previsione-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(previsioneTaskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PrevisioneTask in the database
        List<PrevisioneTask> previsioneTaskList = previsioneTaskRepository.findAll();
        assertThat(previsioneTaskList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PrevisioneTask in Elasticsearch
        verify(mockPrevisioneTaskSearchRepository, times(0)).save(previsioneTask);
    }

    @Test
    @Transactional
    public void deletePrevisioneTask() throws Exception {
        // Initialize the database
        previsioneTaskRepository.saveAndFlush(previsioneTask);

        int databaseSizeBeforeDelete = previsioneTaskRepository.findAll().size();

        // Delete the previsioneTask
        restPrevisioneTaskMockMvc.perform(delete("/api/previsione-tasks/{id}", previsioneTask.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PrevisioneTask> previsioneTaskList = previsioneTaskRepository.findAll();
        assertThat(previsioneTaskList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PrevisioneTask in Elasticsearch
        verify(mockPrevisioneTaskSearchRepository, times(1)).deleteById(previsioneTask.getId());
    }

    @Test
    @Transactional
    public void searchPrevisioneTask() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        previsioneTaskRepository.saveAndFlush(previsioneTask);
        when(mockPrevisioneTaskSearchRepository.search(queryStringQuery("id:" + previsioneTask.getId())))
            .thenReturn(Collections.singletonList(previsioneTask));

        // Search the previsioneTask
        restPrevisioneTaskMockMvc.perform(get("/api/_search/previsione-tasks?query=id:" + previsioneTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(previsioneTask.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTask").value(hasItem(DEFAULT_ID_TASK)))
            .andExpect(jsonPath("$.[*].qntOrdine").value(hasItem(DEFAULT_QNT_ORDINE)))
            .andExpect(jsonPath("$.[*].prcPrevisione").value(hasItem(DEFAULT_PRC_PREVISIONE)))
            .andExpect(jsonPath("$.[*].checkList").value(hasItem(DEFAULT_CHECK_LIST)))
            .andExpect(jsonPath("$.[*].idTaskMilestone").value(hasItem(DEFAULT_ID_TASK_MILESTONE)))
            .andExpect(jsonPath("$.[*].tipoTask").value(hasItem(DEFAULT_TIPO_TASK)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }
}
