package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.PersonaFisica;
import it.maggioli.repository.PersonaFisicaRepository;
import it.maggioli.repository.search.PersonaFisicaSearchRepository;
import it.maggioli.service.PersonaFisicaService;
import it.maggioli.service.dto.PersonaFisicaDTO;
import it.maggioli.service.mapper.PersonaFisicaMapper;

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
 * Integration tests for the {@link PersonaFisicaResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class PersonaFisicaResourceIT {

    private static final Integer DEFAULT_ID_PERSONA_FISICA = 1;
    private static final Integer UPDATED_ID_PERSONA_FISICA = 2;

    private static final Integer DEFAULT_ID_PERSONA_REF = 1;
    private static final Integer UPDATED_ID_PERSONA_REF = 2;

    private static final Integer DEFAULT_ID_RUOLO_PERSONA_REF = 1;
    private static final Integer UPDATED_ID_RUOLO_PERSONA_REF = 2;

    private static final String DEFAULT_TITOLO = "AAAAAAAAAA";
    private static final String UPDATED_TITOLO = "BBBBBBBBBB";

    private static final String DEFAULT_COGNOME = "AAAAAAAAAA";
    private static final String UPDATED_COGNOME = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_DI_NASCITA = "AAAAAAAAAA";
    private static final String UPDATED_DATA_DI_NASCITA = "BBBBBBBBBB";

    private static final String DEFAULT_LUOGO_DI_NASCITA = "AAAAAAAAAA";
    private static final String UPDATED_LUOGO_DI_NASCITA = "BBBBBBBBBB";

    private static final String DEFAULT_PROFESSIONE = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSIONE = "BBBBBBBBBB";

    @Autowired
    private PersonaFisicaRepository personaFisicaRepository;

    @Autowired
    private PersonaFisicaMapper personaFisicaMapper;

    @Autowired
    private PersonaFisicaService personaFisicaService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.PersonaFisicaSearchRepositoryMockConfiguration
     */
    @Autowired
    private PersonaFisicaSearchRepository mockPersonaFisicaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersonaFisicaMockMvc;

    private PersonaFisica personaFisica;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonaFisica createEntity(EntityManager em) {
        PersonaFisica personaFisica = new PersonaFisica()
            .idPersonaFisica(DEFAULT_ID_PERSONA_FISICA)
            .idPersonaRef(DEFAULT_ID_PERSONA_REF)
            .idRuoloPersonaRef(DEFAULT_ID_RUOLO_PERSONA_REF)
            .titolo(DEFAULT_TITOLO)
            .cognome(DEFAULT_COGNOME)
            .nome(DEFAULT_NOME)
            .dataDiNascita(DEFAULT_DATA_DI_NASCITA)
            .luogoDiNascita(DEFAULT_LUOGO_DI_NASCITA)
            .professione(DEFAULT_PROFESSIONE);
        return personaFisica;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PersonaFisica createUpdatedEntity(EntityManager em) {
        PersonaFisica personaFisica = new PersonaFisica()
            .idPersonaFisica(UPDATED_ID_PERSONA_FISICA)
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .idRuoloPersonaRef(UPDATED_ID_RUOLO_PERSONA_REF)
            .titolo(UPDATED_TITOLO)
            .cognome(UPDATED_COGNOME)
            .nome(UPDATED_NOME)
            .dataDiNascita(UPDATED_DATA_DI_NASCITA)
            .luogoDiNascita(UPDATED_LUOGO_DI_NASCITA)
            .professione(UPDATED_PROFESSIONE);
        return personaFisica;
    }

    @BeforeEach
    public void initTest() {
        personaFisica = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonaFisica() throws Exception {
        int databaseSizeBeforeCreate = personaFisicaRepository.findAll().size();
        // Create the PersonaFisica
        PersonaFisicaDTO personaFisicaDTO = personaFisicaMapper.toDto(personaFisica);
        restPersonaFisicaMockMvc.perform(post("/api/persona-fisicas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaFisicaDTO)))
            .andExpect(status().isCreated());

        // Validate the PersonaFisica in the database
        List<PersonaFisica> personaFisicaList = personaFisicaRepository.findAll();
        assertThat(personaFisicaList).hasSize(databaseSizeBeforeCreate + 1);
        PersonaFisica testPersonaFisica = personaFisicaList.get(personaFisicaList.size() - 1);
        assertThat(testPersonaFisica.getIdPersonaFisica()).isEqualTo(DEFAULT_ID_PERSONA_FISICA);
        assertThat(testPersonaFisica.getIdPersonaRef()).isEqualTo(DEFAULT_ID_PERSONA_REF);
        assertThat(testPersonaFisica.getIdRuoloPersonaRef()).isEqualTo(DEFAULT_ID_RUOLO_PERSONA_REF);
        assertThat(testPersonaFisica.getTitolo()).isEqualTo(DEFAULT_TITOLO);
        assertThat(testPersonaFisica.getCognome()).isEqualTo(DEFAULT_COGNOME);
        assertThat(testPersonaFisica.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testPersonaFisica.getDataDiNascita()).isEqualTo(DEFAULT_DATA_DI_NASCITA);
        assertThat(testPersonaFisica.getLuogoDiNascita()).isEqualTo(DEFAULT_LUOGO_DI_NASCITA);
        assertThat(testPersonaFisica.getProfessione()).isEqualTo(DEFAULT_PROFESSIONE);

        // Validate the PersonaFisica in Elasticsearch
        verify(mockPersonaFisicaSearchRepository, times(1)).save(testPersonaFisica);
    }

    @Test
    @Transactional
    public void createPersonaFisicaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personaFisicaRepository.findAll().size();

        // Create the PersonaFisica with an existing ID
        personaFisica.setId(1L);
        PersonaFisicaDTO personaFisicaDTO = personaFisicaMapper.toDto(personaFisica);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonaFisicaMockMvc.perform(post("/api/persona-fisicas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaFisicaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PersonaFisica in the database
        List<PersonaFisica> personaFisicaList = personaFisicaRepository.findAll();
        assertThat(personaFisicaList).hasSize(databaseSizeBeforeCreate);

        // Validate the PersonaFisica in Elasticsearch
        verify(mockPersonaFisicaSearchRepository, times(0)).save(personaFisica);
    }


    @Test
    @Transactional
    public void checkIdPersonaFisicaIsRequired() throws Exception {
        int databaseSizeBeforeTest = personaFisicaRepository.findAll().size();
        // set the field null
        personaFisica.setIdPersonaFisica(null);

        // Create the PersonaFisica, which fails.
        PersonaFisicaDTO personaFisicaDTO = personaFisicaMapper.toDto(personaFisica);


        restPersonaFisicaMockMvc.perform(post("/api/persona-fisicas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaFisicaDTO)))
            .andExpect(status().isBadRequest());

        List<PersonaFisica> personaFisicaList = personaFisicaRepository.findAll();
        assertThat(personaFisicaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdPersonaRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = personaFisicaRepository.findAll().size();
        // set the field null
        personaFisica.setIdPersonaRef(null);

        // Create the PersonaFisica, which fails.
        PersonaFisicaDTO personaFisicaDTO = personaFisicaMapper.toDto(personaFisica);


        restPersonaFisicaMockMvc.perform(post("/api/persona-fisicas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaFisicaDTO)))
            .andExpect(status().isBadRequest());

        List<PersonaFisica> personaFisicaList = personaFisicaRepository.findAll();
        assertThat(personaFisicaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPersonaFisicas() throws Exception {
        // Initialize the database
        personaFisicaRepository.saveAndFlush(personaFisica);

        // Get all the personaFisicaList
        restPersonaFisicaMockMvc.perform(get("/api/persona-fisicas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personaFisica.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPersonaFisica").value(hasItem(DEFAULT_ID_PERSONA_FISICA)))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF)))
            .andExpect(jsonPath("$.[*].idRuoloPersonaRef").value(hasItem(DEFAULT_ID_RUOLO_PERSONA_REF)))
            .andExpect(jsonPath("$.[*].titolo").value(hasItem(DEFAULT_TITOLO)))
            .andExpect(jsonPath("$.[*].cognome").value(hasItem(DEFAULT_COGNOME)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].dataDiNascita").value(hasItem(DEFAULT_DATA_DI_NASCITA)))
            .andExpect(jsonPath("$.[*].luogoDiNascita").value(hasItem(DEFAULT_LUOGO_DI_NASCITA)))
            .andExpect(jsonPath("$.[*].professione").value(hasItem(DEFAULT_PROFESSIONE)));
    }
    
    @Test
    @Transactional
    public void getPersonaFisica() throws Exception {
        // Initialize the database
        personaFisicaRepository.saveAndFlush(personaFisica);

        // Get the personaFisica
        restPersonaFisicaMockMvc.perform(get("/api/persona-fisicas/{id}", personaFisica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(personaFisica.getId().intValue()))
            .andExpect(jsonPath("$.idPersonaFisica").value(DEFAULT_ID_PERSONA_FISICA))
            .andExpect(jsonPath("$.idPersonaRef").value(DEFAULT_ID_PERSONA_REF))
            .andExpect(jsonPath("$.idRuoloPersonaRef").value(DEFAULT_ID_RUOLO_PERSONA_REF))
            .andExpect(jsonPath("$.titolo").value(DEFAULT_TITOLO))
            .andExpect(jsonPath("$.cognome").value(DEFAULT_COGNOME))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.dataDiNascita").value(DEFAULT_DATA_DI_NASCITA))
            .andExpect(jsonPath("$.luogoDiNascita").value(DEFAULT_LUOGO_DI_NASCITA))
            .andExpect(jsonPath("$.professione").value(DEFAULT_PROFESSIONE));
    }
    @Test
    @Transactional
    public void getNonExistingPersonaFisica() throws Exception {
        // Get the personaFisica
        restPersonaFisicaMockMvc.perform(get("/api/persona-fisicas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonaFisica() throws Exception {
        // Initialize the database
        personaFisicaRepository.saveAndFlush(personaFisica);

        int databaseSizeBeforeUpdate = personaFisicaRepository.findAll().size();

        // Update the personaFisica
        PersonaFisica updatedPersonaFisica = personaFisicaRepository.findById(personaFisica.getId()).get();
        // Disconnect from session so that the updates on updatedPersonaFisica are not directly saved in db
        em.detach(updatedPersonaFisica);
        updatedPersonaFisica
            .idPersonaFisica(UPDATED_ID_PERSONA_FISICA)
            .idPersonaRef(UPDATED_ID_PERSONA_REF)
            .idRuoloPersonaRef(UPDATED_ID_RUOLO_PERSONA_REF)
            .titolo(UPDATED_TITOLO)
            .cognome(UPDATED_COGNOME)
            .nome(UPDATED_NOME)
            .dataDiNascita(UPDATED_DATA_DI_NASCITA)
            .luogoDiNascita(UPDATED_LUOGO_DI_NASCITA)
            .professione(UPDATED_PROFESSIONE);
        PersonaFisicaDTO personaFisicaDTO = personaFisicaMapper.toDto(updatedPersonaFisica);

        restPersonaFisicaMockMvc.perform(put("/api/persona-fisicas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaFisicaDTO)))
            .andExpect(status().isOk());

        // Validate the PersonaFisica in the database
        List<PersonaFisica> personaFisicaList = personaFisicaRepository.findAll();
        assertThat(personaFisicaList).hasSize(databaseSizeBeforeUpdate);
        PersonaFisica testPersonaFisica = personaFisicaList.get(personaFisicaList.size() - 1);
        assertThat(testPersonaFisica.getIdPersonaFisica()).isEqualTo(UPDATED_ID_PERSONA_FISICA);
        assertThat(testPersonaFisica.getIdPersonaRef()).isEqualTo(UPDATED_ID_PERSONA_REF);
        assertThat(testPersonaFisica.getIdRuoloPersonaRef()).isEqualTo(UPDATED_ID_RUOLO_PERSONA_REF);
        assertThat(testPersonaFisica.getTitolo()).isEqualTo(UPDATED_TITOLO);
        assertThat(testPersonaFisica.getCognome()).isEqualTo(UPDATED_COGNOME);
        assertThat(testPersonaFisica.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testPersonaFisica.getDataDiNascita()).isEqualTo(UPDATED_DATA_DI_NASCITA);
        assertThat(testPersonaFisica.getLuogoDiNascita()).isEqualTo(UPDATED_LUOGO_DI_NASCITA);
        assertThat(testPersonaFisica.getProfessione()).isEqualTo(UPDATED_PROFESSIONE);

        // Validate the PersonaFisica in Elasticsearch
        verify(mockPersonaFisicaSearchRepository, times(1)).save(testPersonaFisica);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonaFisica() throws Exception {
        int databaseSizeBeforeUpdate = personaFisicaRepository.findAll().size();

        // Create the PersonaFisica
        PersonaFisicaDTO personaFisicaDTO = personaFisicaMapper.toDto(personaFisica);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonaFisicaMockMvc.perform(put("/api/persona-fisicas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaFisicaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PersonaFisica in the database
        List<PersonaFisica> personaFisicaList = personaFisicaRepository.findAll();
        assertThat(personaFisicaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PersonaFisica in Elasticsearch
        verify(mockPersonaFisicaSearchRepository, times(0)).save(personaFisica);
    }

    @Test
    @Transactional
    public void deletePersonaFisica() throws Exception {
        // Initialize the database
        personaFisicaRepository.saveAndFlush(personaFisica);

        int databaseSizeBeforeDelete = personaFisicaRepository.findAll().size();

        // Delete the personaFisica
        restPersonaFisicaMockMvc.perform(delete("/api/persona-fisicas/{id}", personaFisica.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PersonaFisica> personaFisicaList = personaFisicaRepository.findAll();
        assertThat(personaFisicaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PersonaFisica in Elasticsearch
        verify(mockPersonaFisicaSearchRepository, times(1)).deleteById(personaFisica.getId());
    }

    @Test
    @Transactional
    public void searchPersonaFisica() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        personaFisicaRepository.saveAndFlush(personaFisica);
        when(mockPersonaFisicaSearchRepository.search(queryStringQuery("id:" + personaFisica.getId())))
            .thenReturn(Collections.singletonList(personaFisica));

        // Search the personaFisica
        restPersonaFisicaMockMvc.perform(get("/api/_search/persona-fisicas?query=id:" + personaFisica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personaFisica.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPersonaFisica").value(hasItem(DEFAULT_ID_PERSONA_FISICA)))
            .andExpect(jsonPath("$.[*].idPersonaRef").value(hasItem(DEFAULT_ID_PERSONA_REF)))
            .andExpect(jsonPath("$.[*].idRuoloPersonaRef").value(hasItem(DEFAULT_ID_RUOLO_PERSONA_REF)))
            .andExpect(jsonPath("$.[*].titolo").value(hasItem(DEFAULT_TITOLO)))
            .andExpect(jsonPath("$.[*].cognome").value(hasItem(DEFAULT_COGNOME)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].dataDiNascita").value(hasItem(DEFAULT_DATA_DI_NASCITA)))
            .andExpect(jsonPath("$.[*].luogoDiNascita").value(hasItem(DEFAULT_LUOGO_DI_NASCITA)))
            .andExpect(jsonPath("$.[*].professione").value(hasItem(DEFAULT_PROFESSIONE)));
    }
}
