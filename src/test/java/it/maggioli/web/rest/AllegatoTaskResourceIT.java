package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.AllegatoTask;
import it.maggioli.repository.AllegatoTaskRepository;
import it.maggioli.repository.search.AllegatoTaskSearchRepository;
import it.maggioli.service.AllegatoTaskService;
import it.maggioli.service.dto.AllegatoTaskDTO;
import it.maggioli.service.mapper.AllegatoTaskMapper;

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
 * Integration tests for the {@link AllegatoTaskResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class AllegatoTaskResourceIT {

    private static final Long DEFAULT_ID_TIPO_ALLEGATO_REF = 8L;
    private static final Long UPDATED_ID_TIPO_ALLEGATO_REF = 7L;

    private static final Long DEFAULT_ID_TASK_REF = 8L;
    private static final Long UPDATED_ID_TASK_REF = 7L;

    private static final Long DEFAULT_FORMATO = 1L;
    private static final Long UPDATED_FORMATO = 2L;

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final Long DEFAULT_STATO = 1L;
    private static final Long UPDATED_STATO = 2L;

    private static final Long DEFAULT_PUBBLICO = 1L;
    private static final Long UPDATED_PUBBLICO = 2L;

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_ALLEGATO_MASTER = 1L;
    private static final Long UPDATED_ID_ALLEGATO_MASTER = 2L;

    @Autowired
    private AllegatoTaskRepository allegatoTaskRepository;

    @Autowired
    private AllegatoTaskMapper allegatoTaskMapper;

    @Autowired
    private AllegatoTaskService allegatoTaskService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.AllegatoTaskSearchRepositoryMockConfiguration
     */
    @Autowired
    private AllegatoTaskSearchRepository mockAllegatoTaskSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAllegatoTaskMockMvc;

    private AllegatoTask allegatoTask;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AllegatoTask createEntity(EntityManager em) {
        AllegatoTask allegatoTask = new AllegatoTask()
            .idTipoAllegatoRef(DEFAULT_ID_TIPO_ALLEGATO_REF)
            .idTaskRef(DEFAULT_ID_TASK_REF)
            .formato(DEFAULT_FORMATO)
            .note(DEFAULT_NOTE)
            .stato(DEFAULT_STATO)
            .pubblico(DEFAULT_PUBBLICO)
            .version(DEFAULT_VERSION)
            .idAllegatoMaster(DEFAULT_ID_ALLEGATO_MASTER);
        return allegatoTask;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AllegatoTask createUpdatedEntity(EntityManager em) {
        AllegatoTask allegatoTask = new AllegatoTask()
            .idTipoAllegatoRef(UPDATED_ID_TIPO_ALLEGATO_REF)
            .idTaskRef(UPDATED_ID_TASK_REF)
            .formato(UPDATED_FORMATO)
            .note(UPDATED_NOTE)
            .stato(UPDATED_STATO)
            .pubblico(UPDATED_PUBBLICO)
            .version(UPDATED_VERSION)
            .idAllegatoMaster(UPDATED_ID_ALLEGATO_MASTER);
        return allegatoTask;
    }

    @BeforeEach
    public void initTest() {
        allegatoTask = createEntity(em);
    }

    @Test
    @Transactional
    public void createAllegatoTask() throws Exception {
        int databaseSizeBeforeCreate = allegatoTaskRepository.findAll().size();
        // Create the AllegatoTask
        AllegatoTaskDTO allegatoTaskDTO = allegatoTaskMapper.toDto(allegatoTask);
        restAllegatoTaskMockMvc.perform(post("/api/allegato-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allegatoTaskDTO)))
            .andExpect(status().isCreated());

        // Validate the AllegatoTask in the database
        List<AllegatoTask> allegatoTaskList = allegatoTaskRepository.findAll();
        assertThat(allegatoTaskList).hasSize(databaseSizeBeforeCreate + 1);
        AllegatoTask testAllegatoTask = allegatoTaskList.get(allegatoTaskList.size() - 1);
        assertThat(testAllegatoTask.getIdTipoAllegatoRef()).isEqualTo(DEFAULT_ID_TIPO_ALLEGATO_REF);
        assertThat(testAllegatoTask.getIdTaskRef()).isEqualTo(DEFAULT_ID_TASK_REF);
        assertThat(testAllegatoTask.getFormato()).isEqualTo(DEFAULT_FORMATO);
        assertThat(testAllegatoTask.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testAllegatoTask.getStato()).isEqualTo(DEFAULT_STATO);
        assertThat(testAllegatoTask.getPubblico()).isEqualTo(DEFAULT_PUBBLICO);
        assertThat(testAllegatoTask.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testAllegatoTask.getIdAllegatoMaster()).isEqualTo(DEFAULT_ID_ALLEGATO_MASTER);

        // Validate the AllegatoTask in Elasticsearch
        verify(mockAllegatoTaskSearchRepository, times(1)).save(testAllegatoTask);
    }

    @Test
    @Transactional
    public void createAllegatoTaskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = allegatoTaskRepository.findAll().size();

        // Create the AllegatoTask with an existing ID
        allegatoTask.setId(1L);
        AllegatoTaskDTO allegatoTaskDTO = allegatoTaskMapper.toDto(allegatoTask);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAllegatoTaskMockMvc.perform(post("/api/allegato-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allegatoTaskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AllegatoTask in the database
        List<AllegatoTask> allegatoTaskList = allegatoTaskRepository.findAll();
        assertThat(allegatoTaskList).hasSize(databaseSizeBeforeCreate);

        // Validate the AllegatoTask in Elasticsearch
        verify(mockAllegatoTaskSearchRepository, times(0)).save(allegatoTask);
    }


    @Test
    @Transactional
    public void checkIdAllegatoMasterIsRequired() throws Exception {
        int databaseSizeBeforeTest = allegatoTaskRepository.findAll().size();
        // set the field null
        allegatoTask.setIdAllegatoMaster(null);

        // Create the AllegatoTask, which fails.
        AllegatoTaskDTO allegatoTaskDTO = allegatoTaskMapper.toDto(allegatoTask);


        restAllegatoTaskMockMvc.perform(post("/api/allegato-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allegatoTaskDTO)))
            .andExpect(status().isBadRequest());

        List<AllegatoTask> allegatoTaskList = allegatoTaskRepository.findAll();
        assertThat(allegatoTaskList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAllegatoTasks() throws Exception {
        // Initialize the database
        allegatoTaskRepository.saveAndFlush(allegatoTask);

        // Get all the allegatoTaskList
        restAllegatoTaskMockMvc.perform(get("/api/allegato-tasks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(allegatoTask.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTipoAllegatoRef").value(hasItem(DEFAULT_ID_TIPO_ALLEGATO_REF.intValue())))
            .andExpect(jsonPath("$.[*].idTaskRef").value(hasItem(DEFAULT_ID_TASK_REF.intValue())))
            .andExpect(jsonPath("$.[*].formato").value(hasItem(DEFAULT_FORMATO.intValue())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
            .andExpect(jsonPath("$.[*].stato").value(hasItem(DEFAULT_STATO.intValue())))
            .andExpect(jsonPath("$.[*].pubblico").value(hasItem(DEFAULT_PUBBLICO.intValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].idAllegatoMaster").value(hasItem(DEFAULT_ID_ALLEGATO_MASTER.intValue())));
    }
    
    @Test
    @Transactional
    public void getAllegatoTask() throws Exception {
        // Initialize the database
        allegatoTaskRepository.saveAndFlush(allegatoTask);

        // Get the allegatoTask
        restAllegatoTaskMockMvc.perform(get("/api/allegato-tasks/{id}", allegatoTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(allegatoTask.getId().intValue()))
            .andExpect(jsonPath("$.idTipoAllegatoRef").value(DEFAULT_ID_TIPO_ALLEGATO_REF.intValue()))
            .andExpect(jsonPath("$.idTaskRef").value(DEFAULT_ID_TASK_REF.intValue()))
            .andExpect(jsonPath("$.formato").value(DEFAULT_FORMATO.intValue()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE))
            .andExpect(jsonPath("$.stato").value(DEFAULT_STATO.intValue()))
            .andExpect(jsonPath("$.pubblico").value(DEFAULT_PUBBLICO.intValue()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.idAllegatoMaster").value(DEFAULT_ID_ALLEGATO_MASTER.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingAllegatoTask() throws Exception {
        // Get the allegatoTask
        restAllegatoTaskMockMvc.perform(get("/api/allegato-tasks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAllegatoTask() throws Exception {
        // Initialize the database
        allegatoTaskRepository.saveAndFlush(allegatoTask);

        int databaseSizeBeforeUpdate = allegatoTaskRepository.findAll().size();

        // Update the allegatoTask
        AllegatoTask updatedAllegatoTask = allegatoTaskRepository.findById(allegatoTask.getId()).get();
        // Disconnect from session so that the updates on updatedAllegatoTask are not directly saved in db
        em.detach(updatedAllegatoTask);
        updatedAllegatoTask
            .idTipoAllegatoRef(UPDATED_ID_TIPO_ALLEGATO_REF)
            .idTaskRef(UPDATED_ID_TASK_REF)
            .formato(UPDATED_FORMATO)
            .note(UPDATED_NOTE)
            .stato(UPDATED_STATO)
            .pubblico(UPDATED_PUBBLICO)
            .version(UPDATED_VERSION)
            .idAllegatoMaster(UPDATED_ID_ALLEGATO_MASTER);
        AllegatoTaskDTO allegatoTaskDTO = allegatoTaskMapper.toDto(updatedAllegatoTask);

        restAllegatoTaskMockMvc.perform(put("/api/allegato-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allegatoTaskDTO)))
            .andExpect(status().isOk());

        // Validate the AllegatoTask in the database
        List<AllegatoTask> allegatoTaskList = allegatoTaskRepository.findAll();
        assertThat(allegatoTaskList).hasSize(databaseSizeBeforeUpdate);
        AllegatoTask testAllegatoTask = allegatoTaskList.get(allegatoTaskList.size() - 1);
        assertThat(testAllegatoTask.getIdTipoAllegatoRef()).isEqualTo(UPDATED_ID_TIPO_ALLEGATO_REF);
        assertThat(testAllegatoTask.getIdTaskRef()).isEqualTo(UPDATED_ID_TASK_REF);
        assertThat(testAllegatoTask.getFormato()).isEqualTo(UPDATED_FORMATO);
        assertThat(testAllegatoTask.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testAllegatoTask.getStato()).isEqualTo(UPDATED_STATO);
        assertThat(testAllegatoTask.getPubblico()).isEqualTo(UPDATED_PUBBLICO);
        assertThat(testAllegatoTask.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testAllegatoTask.getIdAllegatoMaster()).isEqualTo(UPDATED_ID_ALLEGATO_MASTER);

        // Validate the AllegatoTask in Elasticsearch
        verify(mockAllegatoTaskSearchRepository, times(1)).save(testAllegatoTask);
    }

    @Test
    @Transactional
    public void updateNonExistingAllegatoTask() throws Exception {
        int databaseSizeBeforeUpdate = allegatoTaskRepository.findAll().size();

        // Create the AllegatoTask
        AllegatoTaskDTO allegatoTaskDTO = allegatoTaskMapper.toDto(allegatoTask);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAllegatoTaskMockMvc.perform(put("/api/allegato-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(allegatoTaskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AllegatoTask in the database
        List<AllegatoTask> allegatoTaskList = allegatoTaskRepository.findAll();
        assertThat(allegatoTaskList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AllegatoTask in Elasticsearch
        verify(mockAllegatoTaskSearchRepository, times(0)).save(allegatoTask);
    }

    @Test
    @Transactional
    public void deleteAllegatoTask() throws Exception {
        // Initialize the database
        allegatoTaskRepository.saveAndFlush(allegatoTask);

        int databaseSizeBeforeDelete = allegatoTaskRepository.findAll().size();

        // Delete the allegatoTask
        restAllegatoTaskMockMvc.perform(delete("/api/allegato-tasks/{id}", allegatoTask.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AllegatoTask> allegatoTaskList = allegatoTaskRepository.findAll();
        assertThat(allegatoTaskList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AllegatoTask in Elasticsearch
        verify(mockAllegatoTaskSearchRepository, times(1)).deleteById(allegatoTask.getId());
    }

    @Test
    @Transactional
    public void searchAllegatoTask() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        allegatoTaskRepository.saveAndFlush(allegatoTask);
        when(mockAllegatoTaskSearchRepository.search(queryStringQuery("id:" + allegatoTask.getId())))
            .thenReturn(Collections.singletonList(allegatoTask));

        // Search the allegatoTask
        restAllegatoTaskMockMvc.perform(get("/api/_search/allegato-tasks?query=id:" + allegatoTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(allegatoTask.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTipoAllegatoRef").value(hasItem(DEFAULT_ID_TIPO_ALLEGATO_REF.intValue())))
            .andExpect(jsonPath("$.[*].idTaskRef").value(hasItem(DEFAULT_ID_TASK_REF.intValue())))
            .andExpect(jsonPath("$.[*].formato").value(hasItem(DEFAULT_FORMATO.intValue())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
            .andExpect(jsonPath("$.[*].stato").value(hasItem(DEFAULT_STATO.intValue())))
            .andExpect(jsonPath("$.[*].pubblico").value(hasItem(DEFAULT_PUBBLICO.intValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].idAllegatoMaster").value(hasItem(DEFAULT_ID_ALLEGATO_MASTER.intValue())));
    }
}
