package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.StudioProfessionale;
import it.maggioli.repository.StudioProfessionaleRepository;
import it.maggioli.repository.search.StudioProfessionaleSearchRepository;
import it.maggioli.service.StudioProfessionaleService;
import it.maggioli.service.dto.StudioProfessionaleDTO;
import it.maggioli.service.mapper.StudioProfessionaleMapper;

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
 * Integration tests for the {@link StudioProfessionaleResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class StudioProfessionaleResourceIT {

    private static final Integer DEFAULT_ID_USER_AMMINISTRATORE = 1;
    private static final Integer UPDATED_ID_USER_AMMINISTRATORE = 2;

    @Autowired
    private StudioProfessionaleRepository studioProfessionaleRepository;

    @Autowired
    private StudioProfessionaleMapper studioProfessionaleMapper;

    @Autowired
    private StudioProfessionaleService studioProfessionaleService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.StudioProfessionaleSearchRepositoryMockConfiguration
     */
    @Autowired
    private StudioProfessionaleSearchRepository mockStudioProfessionaleSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStudioProfessionaleMockMvc;

    private StudioProfessionale studioProfessionale;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StudioProfessionale createEntity(EntityManager em) {
        StudioProfessionale studioProfessionale = new StudioProfessionale()
            .idUserAmministratore(DEFAULT_ID_USER_AMMINISTRATORE);
        return studioProfessionale;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StudioProfessionale createUpdatedEntity(EntityManager em) {
        StudioProfessionale studioProfessionale = new StudioProfessionale()
            .idUserAmministratore(UPDATED_ID_USER_AMMINISTRATORE);
        return studioProfessionale;
    }

    @BeforeEach
    public void initTest() {
        studioProfessionale = createEntity(em);
    }

    @Test
    @Transactional
    public void createStudioProfessionale() throws Exception {
        int databaseSizeBeforeCreate = studioProfessionaleRepository.findAll().size();
        // Create the StudioProfessionale
        StudioProfessionaleDTO studioProfessionaleDTO = studioProfessionaleMapper.toDto(studioProfessionale);
        restStudioProfessionaleMockMvc.perform(post("/api/studio-professionales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(studioProfessionaleDTO)))
            .andExpect(status().isCreated());

        // Validate the StudioProfessionale in the database
        List<StudioProfessionale> studioProfessionaleList = studioProfessionaleRepository.findAll();
        assertThat(studioProfessionaleList).hasSize(databaseSizeBeforeCreate + 1);
        StudioProfessionale testStudioProfessionale = studioProfessionaleList.get(studioProfessionaleList.size() - 1);
        assertThat(testStudioProfessionale.getIdUserAmministratore()).isEqualTo(DEFAULT_ID_USER_AMMINISTRATORE);

        // Validate the StudioProfessionale in Elasticsearch
        verify(mockStudioProfessionaleSearchRepository, times(1)).save(testStudioProfessionale);
    }

    @Test
    @Transactional
    public void createStudioProfessionaleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = studioProfessionaleRepository.findAll().size();

        // Create the StudioProfessionale with an existing ID
        studioProfessionale.setId(1L);
        StudioProfessionaleDTO studioProfessionaleDTO = studioProfessionaleMapper.toDto(studioProfessionale);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudioProfessionaleMockMvc.perform(post("/api/studio-professionales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(studioProfessionaleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StudioProfessionale in the database
        List<StudioProfessionale> studioProfessionaleList = studioProfessionaleRepository.findAll();
        assertThat(studioProfessionaleList).hasSize(databaseSizeBeforeCreate);

        // Validate the StudioProfessionale in Elasticsearch
        verify(mockStudioProfessionaleSearchRepository, times(0)).save(studioProfessionale);
    }


    @Test
    @Transactional
    public void checkIdUserAmministratoreIsRequired() throws Exception {
        int databaseSizeBeforeTest = studioProfessionaleRepository.findAll().size();
        // set the field null
        studioProfessionale.setIdUserAmministratore(null);

        // Create the StudioProfessionale, which fails.
        StudioProfessionaleDTO studioProfessionaleDTO = studioProfessionaleMapper.toDto(studioProfessionale);


        restStudioProfessionaleMockMvc.perform(post("/api/studio-professionales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(studioProfessionaleDTO)))
            .andExpect(status().isBadRequest());

        List<StudioProfessionale> studioProfessionaleList = studioProfessionaleRepository.findAll();
        assertThat(studioProfessionaleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStudioProfessionales() throws Exception {
        // Initialize the database
        studioProfessionaleRepository.saveAndFlush(studioProfessionale);

        // Get all the studioProfessionaleList
        restStudioProfessionaleMockMvc.perform(get("/api/studio-professionales?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(studioProfessionale.getId().intValue())))
            .andExpect(jsonPath("$.[*].idUserAmministratore").value(hasItem(DEFAULT_ID_USER_AMMINISTRATORE)));
    }
    
    @Test
    @Transactional
    public void getStudioProfessionale() throws Exception {
        // Initialize the database
        studioProfessionaleRepository.saveAndFlush(studioProfessionale);

        // Get the studioProfessionale
        restStudioProfessionaleMockMvc.perform(get("/api/studio-professionales/{id}", studioProfessionale.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(studioProfessionale.getId().intValue()))
            .andExpect(jsonPath("$.idUserAmministratore").value(DEFAULT_ID_USER_AMMINISTRATORE));
    }
    @Test
    @Transactional
    public void getNonExistingStudioProfessionale() throws Exception {
        // Get the studioProfessionale
        restStudioProfessionaleMockMvc.perform(get("/api/studio-professionales/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStudioProfessionale() throws Exception {
        // Initialize the database
        studioProfessionaleRepository.saveAndFlush(studioProfessionale);

        int databaseSizeBeforeUpdate = studioProfessionaleRepository.findAll().size();

        // Update the studioProfessionale
        StudioProfessionale updatedStudioProfessionale = studioProfessionaleRepository.findById(studioProfessionale.getId()).get();
        // Disconnect from session so that the updates on updatedStudioProfessionale are not directly saved in db
        em.detach(updatedStudioProfessionale);
        updatedStudioProfessionale
            .idUserAmministratore(UPDATED_ID_USER_AMMINISTRATORE);
        StudioProfessionaleDTO studioProfessionaleDTO = studioProfessionaleMapper.toDto(updatedStudioProfessionale);

        restStudioProfessionaleMockMvc.perform(put("/api/studio-professionales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(studioProfessionaleDTO)))
            .andExpect(status().isOk());

        // Validate the StudioProfessionale in the database
        List<StudioProfessionale> studioProfessionaleList = studioProfessionaleRepository.findAll();
        assertThat(studioProfessionaleList).hasSize(databaseSizeBeforeUpdate);
        StudioProfessionale testStudioProfessionale = studioProfessionaleList.get(studioProfessionaleList.size() - 1);
        assertThat(testStudioProfessionale.getIdUserAmministratore()).isEqualTo(UPDATED_ID_USER_AMMINISTRATORE);

        // Validate the StudioProfessionale in Elasticsearch
        verify(mockStudioProfessionaleSearchRepository, times(1)).save(testStudioProfessionale);
    }

    @Test
    @Transactional
    public void updateNonExistingStudioProfessionale() throws Exception {
        int databaseSizeBeforeUpdate = studioProfessionaleRepository.findAll().size();

        // Create the StudioProfessionale
        StudioProfessionaleDTO studioProfessionaleDTO = studioProfessionaleMapper.toDto(studioProfessionale);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudioProfessionaleMockMvc.perform(put("/api/studio-professionales")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(studioProfessionaleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StudioProfessionale in the database
        List<StudioProfessionale> studioProfessionaleList = studioProfessionaleRepository.findAll();
        assertThat(studioProfessionaleList).hasSize(databaseSizeBeforeUpdate);

        // Validate the StudioProfessionale in Elasticsearch
        verify(mockStudioProfessionaleSearchRepository, times(0)).save(studioProfessionale);
    }

    @Test
    @Transactional
    public void deleteStudioProfessionale() throws Exception {
        // Initialize the database
        studioProfessionaleRepository.saveAndFlush(studioProfessionale);

        int databaseSizeBeforeDelete = studioProfessionaleRepository.findAll().size();

        // Delete the studioProfessionale
        restStudioProfessionaleMockMvc.perform(delete("/api/studio-professionales/{id}", studioProfessionale.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StudioProfessionale> studioProfessionaleList = studioProfessionaleRepository.findAll();
        assertThat(studioProfessionaleList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the StudioProfessionale in Elasticsearch
        verify(mockStudioProfessionaleSearchRepository, times(1)).deleteById(studioProfessionale.getId());
    }

    @Test
    @Transactional
    public void searchStudioProfessionale() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        studioProfessionaleRepository.saveAndFlush(studioProfessionale);
        when(mockStudioProfessionaleSearchRepository.search(queryStringQuery("id:" + studioProfessionale.getId())))
            .thenReturn(Collections.singletonList(studioProfessionale));

        // Search the studioProfessionale
        restStudioProfessionaleMockMvc.perform(get("/api/_search/studio-professionales?query=id:" + studioProfessionale.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(studioProfessionale.getId().intValue())))
            .andExpect(jsonPath("$.[*].idUserAmministratore").value(hasItem(DEFAULT_ID_USER_AMMINISTRATORE)));
    }
}
