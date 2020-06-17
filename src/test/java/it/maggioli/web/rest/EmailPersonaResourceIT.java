package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.EmailPersona;
import it.maggioli.repository.EmailPersonaRepository;
import it.maggioli.repository.search.EmailPersonaSearchRepository;
import it.maggioli.service.EmailPersonaService;
import it.maggioli.service.dto.EmailPersonaDTO;
import it.maggioli.service.mapper.EmailPersonaMapper;

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
 * Integration tests for the {@link EmailPersonaResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class EmailPersonaResourceIT {

    private static final Long DEFAULT_ID_PERSONA_REF = 1L;
    private static final Long UPDATED_ID_PERSONA_REF = 2L;

    private static final Long DEFAULT_ETICHETTA = 1L;
    private static final Long UPDATED_ETICHETTA = 2L;

    private static final Long DEFAULT_NUMERO = 1L;
    private static final Long UPDATED_NUMERO = 2L;

    @Autowired
    private EmailPersonaRepository emailPersonaRepository;

    @Autowired
    private EmailPersonaMapper emailPersonaMapper;

    @Autowired
    private EmailPersonaService emailPersonaService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.EmailPersonaSearchRepositoryMockConfiguration
     */
    @Autowired
    private EmailPersonaSearchRepository mockEmailPersonaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmailPersonaMockMvc;

    private EmailPersona emailPersona;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmailPersona createEntity(EntityManager em) {
        EmailPersona emailPersona = new EmailPersona()
            .idPersonaRef(DEFAULT_ID_PERSONA_REF)
            .etichetta(DEFAULT_ETICHETTA)
            .numero(DEFAULT_NUMERO);
        return emailPersona;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmailPersona createUpdatedEntity(EntityManager em) {
        EmailPersona emailPersona = new EmailPersona()
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .etichetta(UPDATED_ETICHETTA)
            .numero(UPDATED_NUMERO);
        return emailPersona;
    }

    @BeforeEach
    public void initTest() {
        emailPersona = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmailPersona() throws Exception {
        int databaseSizeBeforeCreate = emailPersonaRepository.findAll().size();
        // Create the EmailPersona
        EmailPersonaDTO emailPersonaDTO = emailPersonaMapper.toDto(emailPersona);
        restEmailPersonaMockMvc.perform(post("/api/email-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emailPersonaDTO)))
            .andExpect(status().isCreated());

        // Validate the EmailPersona in the database
        List<EmailPersona> emailPersonaList = emailPersonaRepository.findAll();
        assertThat(emailPersonaList).hasSize(databaseSizeBeforeCreate + 1);
        EmailPersona testEmailPersona = emailPersonaList.get(emailPersonaList.size() - 1);
        assertThat(testEmailPersona.getIdPersonaRef()).isEqualTo(DEFAULT_ID_PERSONA_REF);
        assertThat(testEmailPersona.getEtichetta()).isEqualTo(DEFAULT_ETICHETTA);
        assertThat(testEmailPersona.getNumero()).isEqualTo(DEFAULT_NUMERO);

        // Validate the EmailPersona in Elasticsearch
        verify(mockEmailPersonaSearchRepository, times(1)).save(testEmailPersona);
    }

    @Test
    @Transactional
    public void createEmailPersonaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emailPersonaRepository.findAll().size();

        // Create the EmailPersona with an existing ID
        emailPersona.setId(1L);
        EmailPersonaDTO emailPersonaDTO = emailPersonaMapper.toDto(emailPersona);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmailPersonaMockMvc.perform(post("/api/email-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emailPersonaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmailPersona in the database
        List<EmailPersona> emailPersonaList = emailPersonaRepository.findAll();
        assertThat(emailPersonaList).hasSize(databaseSizeBeforeCreate);

        // Validate the EmailPersona in Elasticsearch
        verify(mockEmailPersonaSearchRepository, times(0)).save(emailPersona);
    }


    @Test
    @Transactional
    public void checkIdPersonaRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = emailPersonaRepository.findAll().size();
        // set the field null
        emailPersona.setIdPersonaRef(null);

        // Create the EmailPersona, which fails.
        EmailPersonaDTO emailPersonaDTO = emailPersonaMapper.toDto(emailPersona);


        restEmailPersonaMockMvc.perform(post("/api/email-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emailPersonaDTO)))
            .andExpect(status().isBadRequest());

        List<EmailPersona> emailPersonaList = emailPersonaRepository.findAll();
        assertThat(emailPersonaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEmailPersonas() throws Exception {
        // Initialize the database
        emailPersonaRepository.saveAndFlush(emailPersona);

        // Get all the emailPersonaList
        restEmailPersonaMockMvc.perform(get("/api/email-personas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emailPersona.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF.intValue())))
            .andExpect(jsonPath("$.[*].etichetta").value(hasItem(DEFAULT_ETICHETTA.intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.intValue())));
    }
    
    @Test
    @Transactional
    public void getEmailPersona() throws Exception {
        // Initialize the database
        emailPersonaRepository.saveAndFlush(emailPersona);

        // Get the emailPersona
        restEmailPersonaMockMvc.perform(get("/api/email-personas/{id}", emailPersona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(emailPersona.getId().intValue()))
            .andExpect(jsonPath("$.idPersonaRef").value(DEFAULT_ID_PERSONA_REF.intValue()))
            .andExpect(jsonPath("$.etichetta").value(DEFAULT_ETICHETTA.intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingEmailPersona() throws Exception {
        // Get the emailPersona
        restEmailPersonaMockMvc.perform(get("/api/email-personas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmailPersona() throws Exception {
        // Initialize the database
        emailPersonaRepository.saveAndFlush(emailPersona);

        int databaseSizeBeforeUpdate = emailPersonaRepository.findAll().size();

        // Update the emailPersona
        EmailPersona updatedEmailPersona = emailPersonaRepository.findById(emailPersona.getId()).get();
        // Disconnect from session so that the updates on updatedEmailPersona are not directly saved in db
        em.detach(updatedEmailPersona);
        updatedEmailPersona
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .etichetta(UPDATED_ETICHETTA)
            .numero(UPDATED_NUMERO);
        EmailPersonaDTO emailPersonaDTO = emailPersonaMapper.toDto(updatedEmailPersona);

        restEmailPersonaMockMvc.perform(put("/api/email-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emailPersonaDTO)))
            .andExpect(status().isOk());

        // Validate the EmailPersona in the database
        List<EmailPersona> emailPersonaList = emailPersonaRepository.findAll();
        assertThat(emailPersonaList).hasSize(databaseSizeBeforeUpdate);
        EmailPersona testEmailPersona = emailPersonaList.get(emailPersonaList.size() - 1);
        assertThat(testEmailPersona.getIdPersonaRef()).isEqualTo(UPDATED_ID_PERSONA_REF);
        assertThat(testEmailPersona.getEtichetta()).isEqualTo(UPDATED_ETICHETTA);
        assertThat(testEmailPersona.getNumero()).isEqualTo(UPDATED_NUMERO);

        // Validate the EmailPersona in Elasticsearch
        verify(mockEmailPersonaSearchRepository, times(1)).save(testEmailPersona);
    }

    @Test
    @Transactional
    public void updateNonExistingEmailPersona() throws Exception {
        int databaseSizeBeforeUpdate = emailPersonaRepository.findAll().size();

        // Create the EmailPersona
        EmailPersonaDTO emailPersonaDTO = emailPersonaMapper.toDto(emailPersona);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmailPersonaMockMvc.perform(put("/api/email-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emailPersonaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EmailPersona in the database
        List<EmailPersona> emailPersonaList = emailPersonaRepository.findAll();
        assertThat(emailPersonaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the EmailPersona in Elasticsearch
        verify(mockEmailPersonaSearchRepository, times(0)).save(emailPersona);
    }

    @Test
    @Transactional
    public void deleteEmailPersona() throws Exception {
        // Initialize the database
        emailPersonaRepository.saveAndFlush(emailPersona);

        int databaseSizeBeforeDelete = emailPersonaRepository.findAll().size();

        // Delete the emailPersona
        restEmailPersonaMockMvc.perform(delete("/api/email-personas/{id}", emailPersona.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EmailPersona> emailPersonaList = emailPersonaRepository.findAll();
        assertThat(emailPersonaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the EmailPersona in Elasticsearch
        verify(mockEmailPersonaSearchRepository, times(1)).deleteById(emailPersona.getId());
    }

    @Test
    @Transactional
    public void searchEmailPersona() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        emailPersonaRepository.saveAndFlush(emailPersona);
        when(mockEmailPersonaSearchRepository.search(queryStringQuery("id:" + emailPersona.getId())))
            .thenReturn(Collections.singletonList(emailPersona));

        // Search the emailPersona
        restEmailPersonaMockMvc.perform(get("/api/_search/email-personas?query=id:" + emailPersona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emailPersona.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF.intValue())))
            .andExpect(jsonPath("$.[*].etichetta").value(hasItem(DEFAULT_ETICHETTA.intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.intValue())));
    }
}
