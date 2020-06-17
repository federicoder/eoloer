package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.TagPersona;
import it.maggioli.repository.TagPersonaRepository;
import it.maggioli.repository.search.TagPersonaSearchRepository;
import it.maggioli.service.TagPersonaService;
import it.maggioli.service.dto.TagPersonaDTO;
import it.maggioli.service.mapper.TagPersonaMapper;

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
 * Integration tests for the {@link TagPersonaResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class TagPersonaResourceIT {

    private static final Long DEFAULT_ID_PERSONA_REF = 1L;
    private static final Long UPDATED_ID_PERSONA_REF = 2L;

    private static final Long DEFAULT_TAG = 1L;
    private static final Long UPDATED_TAG = 2L;

    @Autowired
    private TagPersonaRepository tagPersonaRepository;

    @Autowired
    private TagPersonaMapper tagPersonaMapper;

    @Autowired
    private TagPersonaService tagPersonaService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.TagPersonaSearchRepositoryMockConfiguration
     */
    @Autowired
    private TagPersonaSearchRepository mockTagPersonaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTagPersonaMockMvc;

    private TagPersona tagPersona;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagPersona createEntity(EntityManager em) {
        TagPersona tagPersona = new TagPersona()
            .idPersonaRef(DEFAULT_ID_PERSONA_REF)
            .tag(DEFAULT_TAG);
        return tagPersona;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TagPersona createUpdatedEntity(EntityManager em) {
        TagPersona tagPersona = new TagPersona()
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .tag(UPDATED_TAG);
        return tagPersona;
    }

    @BeforeEach
    public void initTest() {
        tagPersona = createEntity(em);
    }

    @Test
    @Transactional
    public void createTagPersona() throws Exception {
        int databaseSizeBeforeCreate = tagPersonaRepository.findAll().size();
        // Create the TagPersona
        TagPersonaDTO tagPersonaDTO = tagPersonaMapper.toDto(tagPersona);
        restTagPersonaMockMvc.perform(post("/api/tag-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tagPersonaDTO)))
            .andExpect(status().isCreated());

        // Validate the TagPersona in the database
        List<TagPersona> tagPersonaList = tagPersonaRepository.findAll();
        assertThat(tagPersonaList).hasSize(databaseSizeBeforeCreate + 1);
        TagPersona testTagPersona = tagPersonaList.get(tagPersonaList.size() - 1);
        assertThat(testTagPersona.getIdPersonaRef()).isEqualTo(DEFAULT_ID_PERSONA_REF);
        assertThat(testTagPersona.getTag()).isEqualTo(DEFAULT_TAG);

        // Validate the TagPersona in Elasticsearch
        verify(mockTagPersonaSearchRepository, times(1)).save(testTagPersona);
    }

    @Test
    @Transactional
    public void createTagPersonaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tagPersonaRepository.findAll().size();

        // Create the TagPersona with an existing ID
        tagPersona.setId(1L);
        TagPersonaDTO tagPersonaDTO = tagPersonaMapper.toDto(tagPersona);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTagPersonaMockMvc.perform(post("/api/tag-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tagPersonaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TagPersona in the database
        List<TagPersona> tagPersonaList = tagPersonaRepository.findAll();
        assertThat(tagPersonaList).hasSize(databaseSizeBeforeCreate);

        // Validate the TagPersona in Elasticsearch
        verify(mockTagPersonaSearchRepository, times(0)).save(tagPersona);
    }


    @Test
    @Transactional
    public void checkIdPersonaRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = tagPersonaRepository.findAll().size();
        // set the field null
        tagPersona.setIdPersonaRef(null);

        // Create the TagPersona, which fails.
        TagPersonaDTO tagPersonaDTO = tagPersonaMapper.toDto(tagPersona);


        restTagPersonaMockMvc.perform(post("/api/tag-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tagPersonaDTO)))
            .andExpect(status().isBadRequest());

        List<TagPersona> tagPersonaList = tagPersonaRepository.findAll();
        assertThat(tagPersonaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTagPersonas() throws Exception {
        // Initialize the database
        tagPersonaRepository.saveAndFlush(tagPersona);

        // Get all the tagPersonaList
        restTagPersonaMockMvc.perform(get("/api/tag-personas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tagPersona.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF.intValue())))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG.intValue())));
    }
    
    @Test
    @Transactional
    public void getTagPersona() throws Exception {
        // Initialize the database
        tagPersonaRepository.saveAndFlush(tagPersona);

        // Get the tagPersona
        restTagPersonaMockMvc.perform(get("/api/tag-personas/{id}", tagPersona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tagPersona.getId().intValue()))
            .andExpect(jsonPath("$.idPersonaRef").value(DEFAULT_ID_PERSONA_REF.intValue()))
            .andExpect(jsonPath("$.tag").value(DEFAULT_TAG.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTagPersona() throws Exception {
        // Get the tagPersona
        restTagPersonaMockMvc.perform(get("/api/tag-personas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTagPersona() throws Exception {
        // Initialize the database
        tagPersonaRepository.saveAndFlush(tagPersona);

        int databaseSizeBeforeUpdate = tagPersonaRepository.findAll().size();

        // Update the tagPersona
        TagPersona updatedTagPersona = tagPersonaRepository.findById(tagPersona.getId()).get();
        // Disconnect from session so that the updates on updatedTagPersona are not directly saved in db
        em.detach(updatedTagPersona);
        updatedTagPersona
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .tag(UPDATED_TAG);
        TagPersonaDTO tagPersonaDTO = tagPersonaMapper.toDto(updatedTagPersona);

        restTagPersonaMockMvc.perform(put("/api/tag-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tagPersonaDTO)))
            .andExpect(status().isOk());

        // Validate the TagPersona in the database
        List<TagPersona> tagPersonaList = tagPersonaRepository.findAll();
        assertThat(tagPersonaList).hasSize(databaseSizeBeforeUpdate);
        TagPersona testTagPersona = tagPersonaList.get(tagPersonaList.size() - 1);
        assertThat(testTagPersona.getIdPersonaRef()).isEqualTo(UPDATED_ID_PERSONA_REF);
        assertThat(testTagPersona.getTag()).isEqualTo(UPDATED_TAG);

        // Validate the TagPersona in Elasticsearch
        verify(mockTagPersonaSearchRepository, times(1)).save(testTagPersona);
    }

    @Test
    @Transactional
    public void updateNonExistingTagPersona() throws Exception {
        int databaseSizeBeforeUpdate = tagPersonaRepository.findAll().size();

        // Create the TagPersona
        TagPersonaDTO tagPersonaDTO = tagPersonaMapper.toDto(tagPersona);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTagPersonaMockMvc.perform(put("/api/tag-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tagPersonaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TagPersona in the database
        List<TagPersona> tagPersonaList = tagPersonaRepository.findAll();
        assertThat(tagPersonaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TagPersona in Elasticsearch
        verify(mockTagPersonaSearchRepository, times(0)).save(tagPersona);
    }

    @Test
    @Transactional
    public void deleteTagPersona() throws Exception {
        // Initialize the database
        tagPersonaRepository.saveAndFlush(tagPersona);

        int databaseSizeBeforeDelete = tagPersonaRepository.findAll().size();

        // Delete the tagPersona
        restTagPersonaMockMvc.perform(delete("/api/tag-personas/{id}", tagPersona.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TagPersona> tagPersonaList = tagPersonaRepository.findAll();
        assertThat(tagPersonaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TagPersona in Elasticsearch
        verify(mockTagPersonaSearchRepository, times(1)).deleteById(tagPersona.getId());
    }

    @Test
    @Transactional
    public void searchTagPersona() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        tagPersonaRepository.saveAndFlush(tagPersona);
        when(mockTagPersonaSearchRepository.search(queryStringQuery("id:" + tagPersona.getId())))
            .thenReturn(Collections.singletonList(tagPersona));

        // Search the tagPersona
        restTagPersonaMockMvc.perform(get("/api/_search/tag-personas?query=id:" + tagPersona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tagPersona.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF.intValue())))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG.intValue())));
    }
}
