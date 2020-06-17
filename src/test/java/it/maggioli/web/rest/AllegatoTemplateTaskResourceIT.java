package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.AllegatoTemplateTask;
import it.maggioli.repository.AllegatoTemplateTaskRepository;
import it.maggioli.repository.search.AllegatoTemplateTaskSearchRepository;
import it.maggioli.service.AllegatoTemplateTaskService;
import it.maggioli.service.dto.AllegatoTemplateTaskDTO;
import it.maggioli.service.mapper.AllegatoTemplateTaskMapper;

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
 * Integration tests for the {@link AllegatoTemplateTaskResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class AllegatoTemplateTaskResourceIT {

    private static final Integer DEFAULT_ID_TEMPLATE_TASK_REF = 8;
    private static final Integer UPDATED_ID_TEMPLATE_TASK_REF = 7;

    private static final Integer DEFAULT_ID_TIPO_ALLEGATO_REF = 1;
    private static final Integer UPDATED_ID_TIPO_ALLEGATO_REF = 2;

    private static final Integer DEFAULT_FORMATO = 1;
    private static final Integer UPDATED_FORMATO = 2;

    private static final Integer DEFAULT_ID_FILE_REF = 1;
    private static final Integer UPDATED_ID_FILE_REF = 2;

    private static final Integer DEFAULT_PUB_PRIV = 1;
    private static final Integer UPDATED_PUB_PRIV = 2;

    @Autowired
    private AllegatoTemplateTaskRepository allegatoTemplateTaskRepository;

    @Autowired
    private AllegatoTemplateTaskMapper allegatoTemplateTaskMapper;

    @Autowired
    private AllegatoTemplateTaskService allegatoTemplateTaskService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.AllegatoTemplateTaskSearchRepositoryMockConfiguration
     */
    @Autowired
    private AllegatoTemplateTaskSearchRepository mockAllegatoTemplateTaskSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAllegatoTemplateTaskMockMvc;

    private AllegatoTemplateTask allegatoTemplateTask;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AllegatoTemplateTask createEntity(EntityManager em) {
        AllegatoTemplateTask allegatoTemplateTask = new AllegatoTemplateTask()
            .idTemplateTaskRef(DEFAULT_ID_TEMPLATE_TASK_REF)
            .idTipoAllegatoRef(DEFAULT_ID_TIPO_ALLEGATO_REF)
            .formato(DEFAULT_FORMATO)
            .idFileRef(DEFAULT_ID_FILE_REF)
            .pubPriv(DEFAULT_PUB_PRIV);
        return allegatoTemplateTask;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AllegatoTemplateTask createUpdatedEntity(EntityManager em) {
        AllegatoTemplateTask allegatoTemplateTask = new AllegatoTemplateTask()
            .idTemplateTaskRef(UPDATED_ID_TEMPLATE_TASK_REF)
            .idTipoAllegatoRef(UPDATED_ID_TIPO_ALLEGATO_REF)
            .formato(UPDATED_FORMATO)
            .idFileRef(UPDATED_ID_FILE_REF)
            .pubPriv(UPDATED_PUB_PRIV);
        return allegatoTemplateTask;
    }

    @BeforeEach
    public void initTest() {
        allegatoTemplateTask = createEntity(em);
    }

    @Test
    @Transactional
    public void createAllegatoTemplateTask() throws Exception {
        int databaseSizeBeforeCreate = allegatoTemplateTaskRepository.findAll().size();
        // Create the AllegatoTemplateTask
        AllegatoTemplateTaskDTO allegatoTemplateTaskDTO = allegatoTemplateTaskMapper.toDto(allegatoTemplateTask);
        restAllegatoTemplateTaskMockMvc.perform(post("/api/allegato-template-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allegatoTemplateTaskDTO)))
            .andExpect(status().isCreated());

        // Validate the AllegatoTemplateTask in the database
        List<AllegatoTemplateTask> allegatoTemplateTaskList = allegatoTemplateTaskRepository.findAll();
        assertThat(allegatoTemplateTaskList).hasSize(databaseSizeBeforeCreate + 1);
        AllegatoTemplateTask testAllegatoTemplateTask = allegatoTemplateTaskList.get(allegatoTemplateTaskList.size() - 1);
        assertThat(testAllegatoTemplateTask.getIdTemplateTaskRef()).isEqualTo(DEFAULT_ID_TEMPLATE_TASK_REF);
        assertThat(testAllegatoTemplateTask.getIdTipoAllegatoRef()).isEqualTo(DEFAULT_ID_TIPO_ALLEGATO_REF);
        assertThat(testAllegatoTemplateTask.getFormato()).isEqualTo(DEFAULT_FORMATO);
        assertThat(testAllegatoTemplateTask.getIdFileRef()).isEqualTo(DEFAULT_ID_FILE_REF);
        assertThat(testAllegatoTemplateTask.getPubPriv()).isEqualTo(DEFAULT_PUB_PRIV);

        // Validate the AllegatoTemplateTask in Elasticsearch
        verify(mockAllegatoTemplateTaskSearchRepository, times(1)).save(testAllegatoTemplateTask);
    }

    @Test
    @Transactional
    public void createAllegatoTemplateTaskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = allegatoTemplateTaskRepository.findAll().size();

        // Create the AllegatoTemplateTask with an existing ID
        allegatoTemplateTask.setId(1L);
        AllegatoTemplateTaskDTO allegatoTemplateTaskDTO = allegatoTemplateTaskMapper.toDto(allegatoTemplateTask);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAllegatoTemplateTaskMockMvc.perform(post("/api/allegato-template-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allegatoTemplateTaskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AllegatoTemplateTask in the database
        List<AllegatoTemplateTask> allegatoTemplateTaskList = allegatoTemplateTaskRepository.findAll();
        assertThat(allegatoTemplateTaskList).hasSize(databaseSizeBeforeCreate);

        // Validate the AllegatoTemplateTask in Elasticsearch
        verify(mockAllegatoTemplateTaskSearchRepository, times(0)).save(allegatoTemplateTask);
    }


    @Test
    @Transactional
    public void checkIdTemplateTaskRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = allegatoTemplateTaskRepository.findAll().size();
        // set the field null
        allegatoTemplateTask.setIdTemplateTaskRef(null);

        // Create the AllegatoTemplateTask, which fails.
        AllegatoTemplateTaskDTO allegatoTemplateTaskDTO = allegatoTemplateTaskMapper.toDto(allegatoTemplateTask);


        restAllegatoTemplateTaskMockMvc.perform(post("/api/allegato-template-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allegatoTemplateTaskDTO)))
            .andExpect(status().isBadRequest());

        List<AllegatoTemplateTask> allegatoTemplateTaskList = allegatoTemplateTaskRepository.findAll();
        assertThat(allegatoTemplateTaskList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAllegatoTemplateTasks() throws Exception {
        // Initialize the database
        allegatoTemplateTaskRepository.saveAndFlush(allegatoTemplateTask);

        // Get all the allegatoTemplateTaskList
        restAllegatoTemplateTaskMockMvc.perform(get("/api/allegato-template-tasks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(allegatoTemplateTask.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTemplateTaskRef").value(hasItem(DEFAULT_ID_TEMPLATE_TASK_REF)))
            .andExpect(jsonPath("$.[*].idTipoAllegatoRef").value(hasItem(DEFAULT_ID_TIPO_ALLEGATO_REF)))
            .andExpect(jsonPath("$.[*].formato").value(hasItem(DEFAULT_FORMATO)))
            .andExpect(jsonPath("$.[*].idFileRef").value(hasItem(DEFAULT_ID_FILE_REF)))
            .andExpect(jsonPath("$.[*].pubPriv").value(hasItem(DEFAULT_PUB_PRIV)));
    }
    
    @Test
    @Transactional
    public void getAllegatoTemplateTask() throws Exception {
        // Initialize the database
        allegatoTemplateTaskRepository.saveAndFlush(allegatoTemplateTask);

        // Get the allegatoTemplateTask
        restAllegatoTemplateTaskMockMvc.perform(get("/api/allegato-template-tasks/{id}", allegatoTemplateTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(allegatoTemplateTask.getId().intValue()))
            .andExpect(jsonPath("$.idTemplateTaskRef").value(DEFAULT_ID_TEMPLATE_TASK_REF))
            .andExpect(jsonPath("$.idTipoAllegatoRef").value(DEFAULT_ID_TIPO_ALLEGATO_REF))
            .andExpect(jsonPath("$.formato").value(DEFAULT_FORMATO))
            .andExpect(jsonPath("$.idFileRef").value(DEFAULT_ID_FILE_REF))
            .andExpect(jsonPath("$.pubPriv").value(DEFAULT_PUB_PRIV));
    }
    @Test
    @Transactional
    public void getNonExistingAllegatoTemplateTask() throws Exception {
        // Get the allegatoTemplateTask
        restAllegatoTemplateTaskMockMvc.perform(get("/api/allegato-template-tasks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAllegatoTemplateTask() throws Exception {
        // Initialize the database
        allegatoTemplateTaskRepository.saveAndFlush(allegatoTemplateTask);

        int databaseSizeBeforeUpdate = allegatoTemplateTaskRepository.findAll().size();

        // Update the allegatoTemplateTask
        AllegatoTemplateTask updatedAllegatoTemplateTask = allegatoTemplateTaskRepository.findById(allegatoTemplateTask.getId()).get();
        // Disconnect from session so that the updates on updatedAllegatoTemplateTask are not directly saved in db
        em.detach(updatedAllegatoTemplateTask);
        updatedAllegatoTemplateTask
            .idTemplateTaskRef(UPDATED_ID_TEMPLATE_TASK_REF)
            .idTipoAllegatoRef(UPDATED_ID_TIPO_ALLEGATO_REF)
            .formato(UPDATED_FORMATO)
            .idFileRef(UPDATED_ID_FILE_REF)
            .pubPriv(UPDATED_PUB_PRIV);
        AllegatoTemplateTaskDTO allegatoTemplateTaskDTO = allegatoTemplateTaskMapper.toDto(updatedAllegatoTemplateTask);

        restAllegatoTemplateTaskMockMvc.perform(put("/api/allegato-template-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allegatoTemplateTaskDTO)))
            .andExpect(status().isOk());

        // Validate the AllegatoTemplateTask in the database
        List<AllegatoTemplateTask> allegatoTemplateTaskList = allegatoTemplateTaskRepository.findAll();
        assertThat(allegatoTemplateTaskList).hasSize(databaseSizeBeforeUpdate);
        AllegatoTemplateTask testAllegatoTemplateTask = allegatoTemplateTaskList.get(allegatoTemplateTaskList.size() - 1);
        assertThat(testAllegatoTemplateTask.getIdTemplateTaskRef()).isEqualTo(UPDATED_ID_TEMPLATE_TASK_REF);
        assertThat(testAllegatoTemplateTask.getIdTipoAllegatoRef()).isEqualTo(UPDATED_ID_TIPO_ALLEGATO_REF);
        assertThat(testAllegatoTemplateTask.getFormato()).isEqualTo(UPDATED_FORMATO);
        assertThat(testAllegatoTemplateTask.getIdFileRef()).isEqualTo(UPDATED_ID_FILE_REF);
        assertThat(testAllegatoTemplateTask.getPubPriv()).isEqualTo(UPDATED_PUB_PRIV);

        // Validate the AllegatoTemplateTask in Elasticsearch
        verify(mockAllegatoTemplateTaskSearchRepository, times(1)).save(testAllegatoTemplateTask);
    }

    @Test
    @Transactional
    public void updateNonExistingAllegatoTemplateTask() throws Exception {
        int databaseSizeBeforeUpdate = allegatoTemplateTaskRepository.findAll().size();

        // Create the AllegatoTemplateTask
        AllegatoTemplateTaskDTO allegatoTemplateTaskDTO = allegatoTemplateTaskMapper.toDto(allegatoTemplateTask);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAllegatoTemplateTaskMockMvc.perform(put("/api/allegato-template-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allegatoTemplateTaskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AllegatoTemplateTask in the database
        List<AllegatoTemplateTask> allegatoTemplateTaskList = allegatoTemplateTaskRepository.findAll();
        assertThat(allegatoTemplateTaskList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AllegatoTemplateTask in Elasticsearch
        verify(mockAllegatoTemplateTaskSearchRepository, times(0)).save(allegatoTemplateTask);
    }

    @Test
    @Transactional
    public void deleteAllegatoTemplateTask() throws Exception {
        // Initialize the database
        allegatoTemplateTaskRepository.saveAndFlush(allegatoTemplateTask);

        int databaseSizeBeforeDelete = allegatoTemplateTaskRepository.findAll().size();

        // Delete the allegatoTemplateTask
        restAllegatoTemplateTaskMockMvc.perform(delete("/api/allegato-template-tasks/{id}", allegatoTemplateTask.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AllegatoTemplateTask> allegatoTemplateTaskList = allegatoTemplateTaskRepository.findAll();
        assertThat(allegatoTemplateTaskList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AllegatoTemplateTask in Elasticsearch
        verify(mockAllegatoTemplateTaskSearchRepository, times(1)).deleteById(allegatoTemplateTask.getId());
    }

    @Test
    @Transactional
    public void searchAllegatoTemplateTask() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        allegatoTemplateTaskRepository.saveAndFlush(allegatoTemplateTask);
        when(mockAllegatoTemplateTaskSearchRepository.search(queryStringQuery("id:" + allegatoTemplateTask.getId())))
            .thenReturn(Collections.singletonList(allegatoTemplateTask));

        // Search the allegatoTemplateTask
        restAllegatoTemplateTaskMockMvc.perform(get("/api/_search/allegato-template-tasks?query=id:" + allegatoTemplateTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(allegatoTemplateTask.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTemplateTaskRef").value(hasItem(DEFAULT_ID_TEMPLATE_TASK_REF)))
            .andExpect(jsonPath("$.[*].idTipoAllegatoRef").value(hasItem(DEFAULT_ID_TIPO_ALLEGATO_REF)))
            .andExpect(jsonPath("$.[*].formato").value(hasItem(DEFAULT_FORMATO)))
            .andExpect(jsonPath("$.[*].idFileRef").value(hasItem(DEFAULT_ID_FILE_REF)))
            .andExpect(jsonPath("$.[*].pubPriv").value(hasItem(DEFAULT_PUB_PRIV)));
    }
}
