package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.InvitoPratica;
import it.maggioli.repository.InvitoPraticaRepository;
import it.maggioli.repository.search.InvitoPraticaSearchRepository;
import it.maggioli.service.InvitoPraticaService;
import it.maggioli.service.dto.InvitoPraticaDTO;
import it.maggioli.service.mapper.InvitoPraticaMapper;

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
 * Integration tests for the {@link InvitoPraticaResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class InvitoPraticaResourceIT {

    private static final Long DEFAULT_ID_PRATICA_REF = 1L;
    private static final Long UPDATED_ID_PRATICA_REF = 2L;

    @Autowired
    private InvitoPraticaRepository invitoPraticaRepository;

    @Autowired
    private InvitoPraticaMapper invitoPraticaMapper;

    @Autowired
    private InvitoPraticaService invitoPraticaService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.InvitoPraticaSearchRepositoryMockConfiguration
     */
    @Autowired
    private InvitoPraticaSearchRepository mockInvitoPraticaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInvitoPraticaMockMvc;

    private InvitoPratica invitoPratica;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvitoPratica createEntity(EntityManager em) {
        InvitoPratica invitoPratica = new InvitoPratica()
            .idPraticaRef(DEFAULT_ID_PRATICA_REF);
        return invitoPratica;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvitoPratica createUpdatedEntity(EntityManager em) {
        InvitoPratica invitoPratica = new InvitoPratica()
            .idPraticaRef(UPDATED_ID_PRATICA_REF);
        return invitoPratica;
    }

    @BeforeEach
    public void initTest() {
        invitoPratica = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvitoPratica() throws Exception {
        int databaseSizeBeforeCreate = invitoPraticaRepository.findAll().size();
        // Create the InvitoPratica
        InvitoPraticaDTO invitoPraticaDTO = invitoPraticaMapper.toDto(invitoPratica);
        restInvitoPraticaMockMvc.perform(post("/api/invito-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitoPraticaDTO)))
            .andExpect(status().isCreated());

        // Validate the InvitoPratica in the database
        List<InvitoPratica> invitoPraticaList = invitoPraticaRepository.findAll();
        assertThat(invitoPraticaList).hasSize(databaseSizeBeforeCreate + 1);
        InvitoPratica testInvitoPratica = invitoPraticaList.get(invitoPraticaList.size() - 1);
        assertThat(testInvitoPratica.getIdPraticaRef()).isEqualTo(DEFAULT_ID_PRATICA_REF);

        // Validate the InvitoPratica in Elasticsearch
        verify(mockInvitoPraticaSearchRepository, times(1)).save(testInvitoPratica);
    }

    @Test
    @Transactional
    public void createInvitoPraticaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invitoPraticaRepository.findAll().size();

        // Create the InvitoPratica with an existing ID
        invitoPratica.setId(1L);
        InvitoPraticaDTO invitoPraticaDTO = invitoPraticaMapper.toDto(invitoPratica);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvitoPraticaMockMvc.perform(post("/api/invito-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitoPraticaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvitoPratica in the database
        List<InvitoPratica> invitoPraticaList = invitoPraticaRepository.findAll();
        assertThat(invitoPraticaList).hasSize(databaseSizeBeforeCreate);

        // Validate the InvitoPratica in Elasticsearch
        verify(mockInvitoPraticaSearchRepository, times(0)).save(invitoPratica);
    }


    @Test
    @Transactional
    public void getAllInvitoPraticas() throws Exception {
        // Initialize the database
        invitoPraticaRepository.saveAndFlush(invitoPratica);

        // Get all the invitoPraticaList
        restInvitoPraticaMockMvc.perform(get("/api/invito-praticas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invitoPratica.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPraticaRef").value(hasItem(DEFAULT_ID_PRATICA_REF.intValue())));
    }
    
    @Test
    @Transactional
    public void getInvitoPratica() throws Exception {
        // Initialize the database
        invitoPraticaRepository.saveAndFlush(invitoPratica);

        // Get the invitoPratica
        restInvitoPraticaMockMvc.perform(get("/api/invito-praticas/{id}", invitoPratica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(invitoPratica.getId().intValue()))
            .andExpect(jsonPath("$.idPraticaRef").value(DEFAULT_ID_PRATICA_REF.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingInvitoPratica() throws Exception {
        // Get the invitoPratica
        restInvitoPraticaMockMvc.perform(get("/api/invito-praticas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvitoPratica() throws Exception {
        // Initialize the database
        invitoPraticaRepository.saveAndFlush(invitoPratica);

        int databaseSizeBeforeUpdate = invitoPraticaRepository.findAll().size();

        // Update the invitoPratica
        InvitoPratica updatedInvitoPratica = invitoPraticaRepository.findById(invitoPratica.getId()).get();
        // Disconnect from session so that the updates on updatedInvitoPratica are not directly saved in db
        em.detach(updatedInvitoPratica);
        updatedInvitoPratica
            .idPraticaRef(UPDATED_ID_PRATICA_REF);
        InvitoPraticaDTO invitoPraticaDTO = invitoPraticaMapper.toDto(updatedInvitoPratica);

        restInvitoPraticaMockMvc.perform(put("/api/invito-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitoPraticaDTO)))
            .andExpect(status().isOk());

        // Validate the InvitoPratica in the database
        List<InvitoPratica> invitoPraticaList = invitoPraticaRepository.findAll();
        assertThat(invitoPraticaList).hasSize(databaseSizeBeforeUpdate);
        InvitoPratica testInvitoPratica = invitoPraticaList.get(invitoPraticaList.size() - 1);
        assertThat(testInvitoPratica.getIdPraticaRef()).isEqualTo(UPDATED_ID_PRATICA_REF);

        // Validate the InvitoPratica in Elasticsearch
        verify(mockInvitoPraticaSearchRepository, times(1)).save(testInvitoPratica);
    }

    @Test
    @Transactional
    public void updateNonExistingInvitoPratica() throws Exception {
        int databaseSizeBeforeUpdate = invitoPraticaRepository.findAll().size();

        // Create the InvitoPratica
        InvitoPraticaDTO invitoPraticaDTO = invitoPraticaMapper.toDto(invitoPratica);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvitoPraticaMockMvc.perform(put("/api/invito-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitoPraticaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvitoPratica in the database
        List<InvitoPratica> invitoPraticaList = invitoPraticaRepository.findAll();
        assertThat(invitoPraticaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the InvitoPratica in Elasticsearch
        verify(mockInvitoPraticaSearchRepository, times(0)).save(invitoPratica);
    }

    @Test
    @Transactional
    public void deleteInvitoPratica() throws Exception {
        // Initialize the database
        invitoPraticaRepository.saveAndFlush(invitoPratica);

        int databaseSizeBeforeDelete = invitoPraticaRepository.findAll().size();

        // Delete the invitoPratica
        restInvitoPraticaMockMvc.perform(delete("/api/invito-praticas/{id}", invitoPratica.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvitoPratica> invitoPraticaList = invitoPraticaRepository.findAll();
        assertThat(invitoPraticaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the InvitoPratica in Elasticsearch
        verify(mockInvitoPraticaSearchRepository, times(1)).deleteById(invitoPratica.getId());
    }

    @Test
    @Transactional
    public void searchInvitoPratica() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        invitoPraticaRepository.saveAndFlush(invitoPratica);
        when(mockInvitoPraticaSearchRepository.search(queryStringQuery("id:" + invitoPratica.getId())))
            .thenReturn(Collections.singletonList(invitoPratica));

        // Search the invitoPratica
        restInvitoPraticaMockMvc.perform(get("/api/_search/invito-praticas?query=id:" + invitoPratica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invitoPratica.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPraticaRef").value(hasItem(DEFAULT_ID_PRATICA_REF.intValue())));
    }
}
