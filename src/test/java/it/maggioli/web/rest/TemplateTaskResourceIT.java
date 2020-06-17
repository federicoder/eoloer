package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.TemplateTask;
import it.maggioli.repository.TemplateTaskRepository;
import it.maggioli.repository.search.TemplateTaskSearchRepository;
import it.maggioli.service.TemplateTaskService;
import it.maggioli.service.dto.TemplateTaskDTO;
import it.maggioli.service.mapper.TemplateTaskMapper;

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
 * Integration tests for the {@link TemplateTaskResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class TemplateTaskResourceIT {

    private static final Integer DEFAULT_ID_TEMPLATE_TASK = 8;
    private static final Integer UPDATED_ID_TEMPLATE_TASK = 7;

    private static final Integer DEFAULT_ORDINE_ESECUZIONE = 1;
    private static final Integer UPDATED_ORDINE_ESECUZIONE = 2;

    private static final Integer DEFAULT_NOME = 1;
    private static final Integer UPDATED_NOME = 2;

    private static final Integer DEFAULT_NOTE = 1;
    private static final Integer UPDATED_NOTE = 2;

    private static final Integer DEFAULT_PUB_PRIV = 1;
    private static final Integer UPDATED_PUB_PRIV = 2;

    private static final Integer DEFAULT_ID_TEMPLATE_PRATICA = 1;
    private static final Integer UPDATED_ID_TEMPLATE_PRATICA = 2;

    @Autowired
    private TemplateTaskRepository templateTaskRepository;

    @Autowired
    private TemplateTaskMapper templateTaskMapper;

    @Autowired
    private TemplateTaskService templateTaskService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.TemplateTaskSearchRepositoryMockConfiguration
     */
    @Autowired
    private TemplateTaskSearchRepository mockTemplateTaskSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTemplateTaskMockMvc;

    private TemplateTask templateTask;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TemplateTask createEntity(EntityManager em) {
        TemplateTask templateTask = new TemplateTask()
            .idTemplateTask(DEFAULT_ID_TEMPLATE_TASK)
            .ordineEsecuzione(DEFAULT_ORDINE_ESECUZIONE)
            .nome(DEFAULT_NOME)
            .note(DEFAULT_NOTE)
            .pubPriv(DEFAULT_PUB_PRIV)
            .idTemplatePratica(DEFAULT_ID_TEMPLATE_PRATICA);
        return templateTask;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TemplateTask createUpdatedEntity(EntityManager em) {
        TemplateTask templateTask = new TemplateTask()
            .idTemplateTask(UPDATED_ID_TEMPLATE_TASK)
            .ordineEsecuzione(UPDATED_ORDINE_ESECUZIONE)
            .nome(UPDATED_NOME)
            .note(UPDATED_NOTE)
            .pubPriv(UPDATED_PUB_PRIV)
            .idTemplatePratica(UPDATED_ID_TEMPLATE_PRATICA);
        return templateTask;
    }

    @BeforeEach
    public void initTest() {
        templateTask = createEntity(em);
    }

    @Test
    @Transactional
    public void createTemplateTask() throws Exception {
        int databaseSizeBeforeCreate = templateTaskRepository.findAll().size();
        // Create the TemplateTask
        TemplateTaskDTO templateTaskDTO = templateTaskMapper.toDto(templateTask);
        restTemplateTaskMockMvc.perform(post("/api/template-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(templateTaskDTO)))
            .andExpect(status().isCreated());

        // Validate the TemplateTask in the database
        List<TemplateTask> templateTaskList = templateTaskRepository.findAll();
        assertThat(templateTaskList).hasSize(databaseSizeBeforeCreate + 1);
        TemplateTask testTemplateTask = templateTaskList.get(templateTaskList.size() - 1);
        assertThat(testTemplateTask.getIdTemplateTask()).isEqualTo(DEFAULT_ID_TEMPLATE_TASK);
        assertThat(testTemplateTask.getOrdineEsecuzione()).isEqualTo(DEFAULT_ORDINE_ESECUZIONE);
        assertThat(testTemplateTask.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTemplateTask.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testTemplateTask.getPubPriv()).isEqualTo(DEFAULT_PUB_PRIV);
        assertThat(testTemplateTask.getIdTemplatePratica()).isEqualTo(DEFAULT_ID_TEMPLATE_PRATICA);

        // Validate the TemplateTask in Elasticsearch
        verify(mockTemplateTaskSearchRepository, times(1)).save(testTemplateTask);
    }

    @Test
    @Transactional
    public void createTemplateTaskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = templateTaskRepository.findAll().size();

        // Create the TemplateTask with an existing ID
        templateTask.setId(1L);
        TemplateTaskDTO templateTaskDTO = templateTaskMapper.toDto(templateTask);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTemplateTaskMockMvc.perform(post("/api/template-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(templateTaskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TemplateTask in the database
        List<TemplateTask> templateTaskList = templateTaskRepository.findAll();
        assertThat(templateTaskList).hasSize(databaseSizeBeforeCreate);

        // Validate the TemplateTask in Elasticsearch
        verify(mockTemplateTaskSearchRepository, times(0)).save(templateTask);
    }


    @Test
    @Transactional
    public void checkIdTemplateTaskIsRequired() throws Exception {
        int databaseSizeBeforeTest = templateTaskRepository.findAll().size();
        // set the field null
        templateTask.setIdTemplateTask(null);

        // Create the TemplateTask, which fails.
        TemplateTaskDTO templateTaskDTO = templateTaskMapper.toDto(templateTask);


        restTemplateTaskMockMvc.perform(post("/api/template-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(templateTaskDTO)))
            .andExpect(status().isBadRequest());

        List<TemplateTask> templateTaskList = templateTaskRepository.findAll();
        assertThat(templateTaskList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTemplateTasks() throws Exception {
        // Initialize the database
        templateTaskRepository.saveAndFlush(templateTask);

        // Get all the templateTaskList
        restTemplateTaskMockMvc.perform(get("/api/template-tasks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(templateTask.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTemplateTask").value(hasItem(DEFAULT_ID_TEMPLATE_TASK)))
            .andExpect(jsonPath("$.[*].ordineEsecuzione").value(hasItem(DEFAULT_ORDINE_ESECUZIONE)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
            .andExpect(jsonPath("$.[*].pubPriv").value(hasItem(DEFAULT_PUB_PRIV)))
            .andExpect(jsonPath("$.[*].idTemplatePratica").value(hasItem(DEFAULT_ID_TEMPLATE_PRATICA)));
    }
    
    @Test
    @Transactional
    public void getTemplateTask() throws Exception {
        // Initialize the database
        templateTaskRepository.saveAndFlush(templateTask);

        // Get the templateTask
        restTemplateTaskMockMvc.perform(get("/api/template-tasks/{id}", templateTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(templateTask.getId().intValue()))
            .andExpect(jsonPath("$.idTemplateTask").value(DEFAULT_ID_TEMPLATE_TASK))
            .andExpect(jsonPath("$.ordineEsecuzione").value(DEFAULT_ORDINE_ESECUZIONE))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE))
            .andExpect(jsonPath("$.pubPriv").value(DEFAULT_PUB_PRIV))
            .andExpect(jsonPath("$.idTemplatePratica").value(DEFAULT_ID_TEMPLATE_PRATICA));
    }
    @Test
    @Transactional
    public void getNonExistingTemplateTask() throws Exception {
        // Get the templateTask
        restTemplateTaskMockMvc.perform(get("/api/template-tasks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTemplateTask() throws Exception {
        // Initialize the database
        templateTaskRepository.saveAndFlush(templateTask);

        int databaseSizeBeforeUpdate = templateTaskRepository.findAll().size();

        // Update the templateTask
        TemplateTask updatedTemplateTask = templateTaskRepository.findById(templateTask.getId()).get();
        // Disconnect from session so that the updates on updatedTemplateTask are not directly saved in db
        em.detach(updatedTemplateTask);
        updatedTemplateTask
            .idTemplateTask(UPDATED_ID_TEMPLATE_TASK)
            .ordineEsecuzione(UPDATED_ORDINE_ESECUZIONE)
            .nome(UPDATED_NOME)
            .note(UPDATED_NOTE)
            .pubPriv(UPDATED_PUB_PRIV)
            .idTemplatePratica(UPDATED_ID_TEMPLATE_PRATICA);
        TemplateTaskDTO templateTaskDTO = templateTaskMapper.toDto(updatedTemplateTask);

        restTemplateTaskMockMvc.perform(put("/api/template-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(templateTaskDTO)))
            .andExpect(status().isOk());

        // Validate the TemplateTask in the database
        List<TemplateTask> templateTaskList = templateTaskRepository.findAll();
        assertThat(templateTaskList).hasSize(databaseSizeBeforeUpdate);
        TemplateTask testTemplateTask = templateTaskList.get(templateTaskList.size() - 1);
        assertThat(testTemplateTask.getIdTemplateTask()).isEqualTo(UPDATED_ID_TEMPLATE_TASK);
        assertThat(testTemplateTask.getOrdineEsecuzione()).isEqualTo(UPDATED_ORDINE_ESECUZIONE);
        assertThat(testTemplateTask.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTemplateTask.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testTemplateTask.getPubPriv()).isEqualTo(UPDATED_PUB_PRIV);
        assertThat(testTemplateTask.getIdTemplatePratica()).isEqualTo(UPDATED_ID_TEMPLATE_PRATICA);

        // Validate the TemplateTask in Elasticsearch
        verify(mockTemplateTaskSearchRepository, times(1)).save(testTemplateTask);
    }

    @Test
    @Transactional
    public void updateNonExistingTemplateTask() throws Exception {
        int databaseSizeBeforeUpdate = templateTaskRepository.findAll().size();

        // Create the TemplateTask
        TemplateTaskDTO templateTaskDTO = templateTaskMapper.toDto(templateTask);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTemplateTaskMockMvc.perform(put("/api/template-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(templateTaskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TemplateTask in the database
        List<TemplateTask> templateTaskList = templateTaskRepository.findAll();
        assertThat(templateTaskList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TemplateTask in Elasticsearch
        verify(mockTemplateTaskSearchRepository, times(0)).save(templateTask);
    }

    @Test
    @Transactional
    public void deleteTemplateTask() throws Exception {
        // Initialize the database
        templateTaskRepository.saveAndFlush(templateTask);

        int databaseSizeBeforeDelete = templateTaskRepository.findAll().size();

        // Delete the templateTask
        restTemplateTaskMockMvc.perform(delete("/api/template-tasks/{id}", templateTask.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TemplateTask> templateTaskList = templateTaskRepository.findAll();
        assertThat(templateTaskList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TemplateTask in Elasticsearch
        verify(mockTemplateTaskSearchRepository, times(1)).deleteById(templateTask.getId());
    }

    @Test
    @Transactional
    public void searchTemplateTask() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        templateTaskRepository.saveAndFlush(templateTask);
        when(mockTemplateTaskSearchRepository.search(queryStringQuery("id:" + templateTask.getId())))
            .thenReturn(Collections.singletonList(templateTask));

        // Search the templateTask
        restTemplateTaskMockMvc.perform(get("/api/_search/template-tasks?query=id:" + templateTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(templateTask.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTemplateTask").value(hasItem(DEFAULT_ID_TEMPLATE_TASK)))
            .andExpect(jsonPath("$.[*].ordineEsecuzione").value(hasItem(DEFAULT_ORDINE_ESECUZIONE)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
            .andExpect(jsonPath("$.[*].pubPriv").value(hasItem(DEFAULT_PUB_PRIV)))
            .andExpect(jsonPath("$.[*].idTemplatePratica").value(hasItem(DEFAULT_ID_TEMPLATE_PRATICA)));
    }
}
