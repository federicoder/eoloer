package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.NotePersona;
import it.maggioli.repository.NotePersonaRepository;
import it.maggioli.repository.search.NotePersonaSearchRepository;
import it.maggioli.service.NotePersonaService;
import it.maggioli.service.dto.NotePersonaDTO;
import it.maggioli.service.mapper.NotePersonaMapper;

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
 * Integration tests for the {@link NotePersonaResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class NotePersonaResourceIT {

    private static final Integer DEFAULT_ID_PERSONA_REF = 1;
    private static final Integer UPDATED_ID_PERSONA_REF = 2;

    private static final Integer DEFAULT_ID_NOTE_PERSONA = 1;
    private static final Integer UPDATED_ID_NOTE_PERSONA = 2;

    private static final String DEFAULT_TESTO = "AAAAAAAAAA";
    private static final String UPDATED_TESTO = "BBBBBBBBBB";

    @Autowired
    private NotePersonaRepository notePersonaRepository;

    @Autowired
    private NotePersonaMapper notePersonaMapper;

    @Autowired
    private NotePersonaService notePersonaService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.NotePersonaSearchRepositoryMockConfiguration
     */
    @Autowired
    private NotePersonaSearchRepository mockNotePersonaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNotePersonaMockMvc;

    private NotePersona notePersona;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotePersona createEntity(EntityManager em) {
        NotePersona notePersona = new NotePersona()
            .idPersonaRef(DEFAULT_ID_PERSONA_REF)
            .idNotePersona(DEFAULT_ID_NOTE_PERSONA)
            .testo(DEFAULT_TESTO);
        return notePersona;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NotePersona createUpdatedEntity(EntityManager em) {
        NotePersona notePersona = new NotePersona()
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .idNotePersona(UPDATED_ID_NOTE_PERSONA)
            .testo(UPDATED_TESTO);
        return notePersona;
    }

    @BeforeEach
    public void initTest() {
        notePersona = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotePersona() throws Exception {
        int databaseSizeBeforeCreate = notePersonaRepository.findAll().size();
        // Create the NotePersona
        NotePersonaDTO notePersonaDTO = notePersonaMapper.toDto(notePersona);
        restNotePersonaMockMvc.perform(post("/api/note-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notePersonaDTO)))
            .andExpect(status().isCreated());

        // Validate the NotePersona in the database
        List<NotePersona> notePersonaList = notePersonaRepository.findAll();
        assertThat(notePersonaList).hasSize(databaseSizeBeforeCreate + 1);
        NotePersona testNotePersona = notePersonaList.get(notePersonaList.size() - 1);
        assertThat(testNotePersona.getIdPersonaRef()).isEqualTo(DEFAULT_ID_PERSONA_REF);
        assertThat(testNotePersona.getIdNotePersona()).isEqualTo(DEFAULT_ID_NOTE_PERSONA);
        assertThat(testNotePersona.getTesto()).isEqualTo(DEFAULT_TESTO);

        // Validate the NotePersona in Elasticsearch
        verify(mockNotePersonaSearchRepository, times(1)).save(testNotePersona);
    }

    @Test
    @Transactional
    public void createNotePersonaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notePersonaRepository.findAll().size();

        // Create the NotePersona with an existing ID
        notePersona.setId(1L);
        NotePersonaDTO notePersonaDTO = notePersonaMapper.toDto(notePersona);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotePersonaMockMvc.perform(post("/api/note-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notePersonaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotePersona in the database
        List<NotePersona> notePersonaList = notePersonaRepository.findAll();
        assertThat(notePersonaList).hasSize(databaseSizeBeforeCreate);

        // Validate the NotePersona in Elasticsearch
        verify(mockNotePersonaSearchRepository, times(0)).save(notePersona);
    }


    @Test
    @Transactional
    public void checkIdPersonaRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = notePersonaRepository.findAll().size();
        // set the field null
        notePersona.setIdPersonaRef(null);

        // Create the NotePersona, which fails.
        NotePersonaDTO notePersonaDTO = notePersonaMapper.toDto(notePersona);


        restNotePersonaMockMvc.perform(post("/api/note-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notePersonaDTO)))
            .andExpect(status().isBadRequest());

        List<NotePersona> notePersonaList = notePersonaRepository.findAll();
        assertThat(notePersonaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdNotePersonaIsRequired() throws Exception {
        int databaseSizeBeforeTest = notePersonaRepository.findAll().size();
        // set the field null
        notePersona.setIdNotePersona(null);

        // Create the NotePersona, which fails.
        NotePersonaDTO notePersonaDTO = notePersonaMapper.toDto(notePersona);


        restNotePersonaMockMvc.perform(post("/api/note-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notePersonaDTO)))
            .andExpect(status().isBadRequest());

        List<NotePersona> notePersonaList = notePersonaRepository.findAll();
        assertThat(notePersonaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNotePersonas() throws Exception {
        // Initialize the database
        notePersonaRepository.saveAndFlush(notePersona);

        // Get all the notePersonaList
        restNotePersonaMockMvc.perform(get("/api/note-personas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notePersona.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF)))
            .andExpect(jsonPath("$.[*].idNotePersona").value(hasItem(DEFAULT_ID_NOTE_PERSONA)))
            .andExpect(jsonPath("$.[*].testo").value(hasItem(DEFAULT_TESTO)));
    }
    
    @Test
    @Transactional
    public void getNotePersona() throws Exception {
        // Initialize the database
        notePersonaRepository.saveAndFlush(notePersona);

        // Get the notePersona
        restNotePersonaMockMvc.perform(get("/api/note-personas/{id}", notePersona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notePersona.getId().intValue()))
            .andExpect(jsonPath("$.idPersonaRef").value(DEFAULT_ID_PERSONA_REF))
            .andExpect(jsonPath("$.idNotePersona").value(DEFAULT_ID_NOTE_PERSONA))
            .andExpect(jsonPath("$.testo").value(DEFAULT_TESTO));
    }
    @Test
    @Transactional
    public void getNonExistingNotePersona() throws Exception {
        // Get the notePersona
        restNotePersonaMockMvc.perform(get("/api/note-personas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotePersona() throws Exception {
        // Initialize the database
        notePersonaRepository.saveAndFlush(notePersona);

        int databaseSizeBeforeUpdate = notePersonaRepository.findAll().size();

        // Update the notePersona
        NotePersona updatedNotePersona = notePersonaRepository.findById(notePersona.getId()).get();
        // Disconnect from session so that the updates on updatedNotePersona are not directly saved in db
        em.detach(updatedNotePersona);
        updatedNotePersona
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .idNotePersona(UPDATED_ID_NOTE_PERSONA)
            .testo(UPDATED_TESTO);
        NotePersonaDTO notePersonaDTO = notePersonaMapper.toDto(updatedNotePersona);

        restNotePersonaMockMvc.perform(put("/api/note-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notePersonaDTO)))
            .andExpect(status().isOk());

        // Validate the NotePersona in the database
        List<NotePersona> notePersonaList = notePersonaRepository.findAll();
        assertThat(notePersonaList).hasSize(databaseSizeBeforeUpdate);
        NotePersona testNotePersona = notePersonaList.get(notePersonaList.size() - 1);
        assertThat(testNotePersona.getIdPersonaRef()).isEqualTo(UPDATED_ID_PERSONA_REF);
        assertThat(testNotePersona.getIdNotePersona()).isEqualTo(UPDATED_ID_NOTE_PERSONA);
        assertThat(testNotePersona.getTesto()).isEqualTo(UPDATED_TESTO);

        // Validate the NotePersona in Elasticsearch
        verify(mockNotePersonaSearchRepository, times(1)).save(testNotePersona);
    }

    @Test
    @Transactional
    public void updateNonExistingNotePersona() throws Exception {
        int databaseSizeBeforeUpdate = notePersonaRepository.findAll().size();

        // Create the NotePersona
        NotePersonaDTO notePersonaDTO = notePersonaMapper.toDto(notePersona);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotePersonaMockMvc.perform(put("/api/note-personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notePersonaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NotePersona in the database
        List<NotePersona> notePersonaList = notePersonaRepository.findAll();
        assertThat(notePersonaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the NotePersona in Elasticsearch
        verify(mockNotePersonaSearchRepository, times(0)).save(notePersona);
    }

    @Test
    @Transactional
    public void deleteNotePersona() throws Exception {
        // Initialize the database
        notePersonaRepository.saveAndFlush(notePersona);

        int databaseSizeBeforeDelete = notePersonaRepository.findAll().size();

        // Delete the notePersona
        restNotePersonaMockMvc.perform(delete("/api/note-personas/{id}", notePersona.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NotePersona> notePersonaList = notePersonaRepository.findAll();
        assertThat(notePersonaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the NotePersona in Elasticsearch
        verify(mockNotePersonaSearchRepository, times(1)).deleteById(notePersona.getId());
    }

    @Test
    @Transactional
    public void searchNotePersona() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        notePersonaRepository.saveAndFlush(notePersona);
        when(mockNotePersonaSearchRepository.search(queryStringQuery("id:" + notePersona.getId())))
            .thenReturn(Collections.singletonList(notePersona));

        // Search the notePersona
        restNotePersonaMockMvc.perform(get("/api/_search/note-personas?query=id:" + notePersona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notePersona.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF)))
            .andExpect(jsonPath("$.[*].idNotePersona").value(hasItem(DEFAULT_ID_NOTE_PERSONA)))
            .andExpect(jsonPath("$.[*].testo").value(hasItem(DEFAULT_TESTO)));
    }
}
