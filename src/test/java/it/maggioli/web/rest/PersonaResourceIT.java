package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.Persona;
import it.maggioli.repository.PersonaRepository;
import it.maggioli.repository.search.PersonaSearchRepository;
import it.maggioli.service.PersonaService;
import it.maggioli.service.dto.PersonaDTO;
import it.maggioli.service.mapper.PersonaMapper;

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
 * Integration tests for the {@link PersonaResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class PersonaResourceIT {

    private static final Long DEFAULT_ID_STUDIO_PROFESSIONALE_REF = 8L;
    private static final Long UPDATED_ID_STUDIO_PROFESSIONALE_REF = 7L;

    private static final String DEFAULT_CODICE_FISCALE = "AAAAAAAAAA";
    private static final String UPDATED_CODICE_FISCALE = "BBBBBBBBBB";

    private static final String DEFAULT_AREA_DI_INTERESSE = "AAAAAAAAAA";
    private static final String UPDATED_AREA_DI_INTERESSE = "BBBBBBBBBB";

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

    private static final Long DEFAULT_TIPO = 1L;
    private static final Long UPDATED_TIPO = 2L;

    private static final String DEFAULT_DISCRIMINATOR = "AAAAAAAAAA";
    private static final String UPDATED_DISCRIMINATOR = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_RUOLO_PERSONA_REF = 1L;
    private static final Long UPDATED_ID_RUOLO_PERSONA_REF = 2L;

    private static final Long DEFAULT_TIPO_RUOLO_UTENTE = 1L;
    private static final Long UPDATED_TIPO_RUOLO_UTENTE = 2L;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaMapper personaMapper;

    @Autowired
    private PersonaService personaService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.PersonaSearchRepositoryMockConfiguration
     */
    @Autowired
    private PersonaSearchRepository mockPersonaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersonaMockMvc;

    private Persona persona;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Persona createEntity(EntityManager em) {
        Persona persona = new Persona()
            .idStudioProfessionaleRef(DEFAULT_ID_STUDIO_PROFESSIONALE_REF)
            .codiceFiscale(DEFAULT_CODICE_FISCALE)
            .areaDiInteresse(DEFAULT_AREA_DI_INTERESSE)
            .titolo(DEFAULT_TITOLO)
            .cognome(DEFAULT_COGNOME)
            .nome(DEFAULT_NOME)
            .dataDiNascita(DEFAULT_DATA_DI_NASCITA)
            .luogoDiNascita(DEFAULT_LUOGO_DI_NASCITA)
            .professione(DEFAULT_PROFESSIONE)
            .tipo(DEFAULT_TIPO)
            .discriminator(DEFAULT_DISCRIMINATOR)
            .idRuoloPersonaRef(DEFAULT_ID_RUOLO_PERSONA_REF)
            .tipoRuoloUtente(DEFAULT_TIPO_RUOLO_UTENTE);
        return persona;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Persona createUpdatedEntity(EntityManager em) {
        Persona persona = new Persona()
            .idStudioProfessionaleRef(UPDATED_ID_STUDIO_PROFESSIONALE_REF)
            .codiceFiscale(UPDATED_CODICE_FISCALE)
            .areaDiInteresse(UPDATED_AREA_DI_INTERESSE)
            .titolo(UPDATED_TITOLO)
            .cognome(UPDATED_COGNOME)
            .nome(UPDATED_NOME)
            .dataDiNascita(UPDATED_DATA_DI_NASCITA)
            .luogoDiNascita(UPDATED_LUOGO_DI_NASCITA)
            .professione(UPDATED_PROFESSIONE)
            .tipo(UPDATED_TIPO)
            .discriminator(UPDATED_DISCRIMINATOR)
            .idRuoloPersonaRef(UPDATED_ID_RUOLO_PERSONA_REF)
            .tipoRuoloUtente(UPDATED_TIPO_RUOLO_UTENTE);
        return persona;
    }

    @BeforeEach
    public void initTest() {
        persona = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersona() throws Exception {
        int databaseSizeBeforeCreate = personaRepository.findAll().size();
        // Create the Persona
        PersonaDTO personaDTO = personaMapper.toDto(persona);
        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaDTO)))
            .andExpect(status().isCreated());

        // Validate the Persona in the database
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeCreate + 1);
        Persona testPersona = personaList.get(personaList.size() - 1);
        assertThat(testPersona.getIdStudioProfessionaleRef()).isEqualTo(DEFAULT_ID_STUDIO_PROFESSIONALE_REF);
        assertThat(testPersona.getCodiceFiscale()).isEqualTo(DEFAULT_CODICE_FISCALE);
        assertThat(testPersona.getAreaDiInteresse()).isEqualTo(DEFAULT_AREA_DI_INTERESSE);
        assertThat(testPersona.getTitolo()).isEqualTo(DEFAULT_TITOLO);
        assertThat(testPersona.getCognome()).isEqualTo(DEFAULT_COGNOME);
        assertThat(testPersona.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testPersona.getDataDiNascita()).isEqualTo(DEFAULT_DATA_DI_NASCITA);
        assertThat(testPersona.getLuogoDiNascita()).isEqualTo(DEFAULT_LUOGO_DI_NASCITA);
        assertThat(testPersona.getProfessione()).isEqualTo(DEFAULT_PROFESSIONE);
        assertThat(testPersona.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testPersona.getDiscriminator()).isEqualTo(DEFAULT_DISCRIMINATOR);
        assertThat(testPersona.getIdRuoloPersonaRef()).isEqualTo(DEFAULT_ID_RUOLO_PERSONA_REF);
        assertThat(testPersona.getTipoRuoloUtente()).isEqualTo(DEFAULT_TIPO_RUOLO_UTENTE);

        // Validate the Persona in Elasticsearch
        verify(mockPersonaSearchRepository, times(1)).save(testPersona);
    }

    @Test
    @Transactional
    public void createPersonaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personaRepository.findAll().size();

        // Create the Persona with an existing ID
        persona.setId(1L);
        PersonaDTO personaDTO = personaMapper.toDto(persona);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Persona in the database
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeCreate);

        // Validate the Persona in Elasticsearch
        verify(mockPersonaSearchRepository, times(0)).save(persona);
    }


    @Test
    @Transactional
    public void getAllPersonas() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList
        restPersonaMockMvc.perform(get("/api/personas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(persona.getId().intValue())))
            .andExpect(jsonPath("$.[*].idStudioProfessionaleRef").value(hasItem(DEFAULT_ID_STUDIO_PROFESSIONALE_REF.intValue())))
            .andExpect(jsonPath("$.[*].codiceFiscale").value(hasItem(DEFAULT_CODICE_FISCALE)))
            .andExpect(jsonPath("$.[*].areaDiInteresse").value(hasItem(DEFAULT_AREA_DI_INTERESSE)))
            .andExpect(jsonPath("$.[*].titolo").value(hasItem(DEFAULT_TITOLO)))
            .andExpect(jsonPath("$.[*].cognome").value(hasItem(DEFAULT_COGNOME)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].dataDiNascita").value(hasItem(DEFAULT_DATA_DI_NASCITA)))
            .andExpect(jsonPath("$.[*].luogoDiNascita").value(hasItem(DEFAULT_LUOGO_DI_NASCITA)))
            .andExpect(jsonPath("$.[*].professione").value(hasItem(DEFAULT_PROFESSIONE)))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.intValue())))
            .andExpect(jsonPath("$.[*].discriminator").value(hasItem(DEFAULT_DISCRIMINATOR)))
            .andExpect(jsonPath("$.[*].idRuoloPersonaRef").value(hasItem(DEFAULT_ID_RUOLO_PERSONA_REF.intValue())))
            .andExpect(jsonPath("$.[*].tipoRuoloUtente").value(hasItem(DEFAULT_TIPO_RUOLO_UTENTE.intValue())));
    }
    
    @Test
    @Transactional
    public void getPersona() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get the persona
        restPersonaMockMvc.perform(get("/api/personas/{id}", persona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(persona.getId().intValue()))
            .andExpect(jsonPath("$.idStudioProfessionaleRef").value(DEFAULT_ID_STUDIO_PROFESSIONALE_REF.intValue()))
            .andExpect(jsonPath("$.codiceFiscale").value(DEFAULT_CODICE_FISCALE))
            .andExpect(jsonPath("$.areaDiInteresse").value(DEFAULT_AREA_DI_INTERESSE))
            .andExpect(jsonPath("$.titolo").value(DEFAULT_TITOLO))
            .andExpect(jsonPath("$.cognome").value(DEFAULT_COGNOME))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.dataDiNascita").value(DEFAULT_DATA_DI_NASCITA))
            .andExpect(jsonPath("$.luogoDiNascita").value(DEFAULT_LUOGO_DI_NASCITA))
            .andExpect(jsonPath("$.professione").value(DEFAULT_PROFESSIONE))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.intValue()))
            .andExpect(jsonPath("$.discriminator").value(DEFAULT_DISCRIMINATOR))
            .andExpect(jsonPath("$.idRuoloPersonaRef").value(DEFAULT_ID_RUOLO_PERSONA_REF.intValue()))
            .andExpect(jsonPath("$.tipoRuoloUtente").value(DEFAULT_TIPO_RUOLO_UTENTE.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPersona() throws Exception {
        // Get the persona
        restPersonaMockMvc.perform(get("/api/personas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersona() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        int databaseSizeBeforeUpdate = personaRepository.findAll().size();

        // Update the persona
        Persona updatedPersona = personaRepository.findById(persona.getId()).get();
        // Disconnect from session so that the updates on updatedPersona are not directly saved in db
        em.detach(updatedPersona);
        updatedPersona
            .idStudioProfessionaleRef(UPDATED_ID_STUDIO_PROFESSIONALE_REF)
            .codiceFiscale(UPDATED_CODICE_FISCALE)
            .areaDiInteresse(UPDATED_AREA_DI_INTERESSE)
            .titolo(UPDATED_TITOLO)
            .cognome(UPDATED_COGNOME)
            .nome(UPDATED_NOME)
            .dataDiNascita(UPDATED_DATA_DI_NASCITA)
            .luogoDiNascita(UPDATED_LUOGO_DI_NASCITA)
            .professione(UPDATED_PROFESSIONE)
            .tipo(UPDATED_TIPO)
            .discriminator(UPDATED_DISCRIMINATOR)
            .idRuoloPersonaRef(UPDATED_ID_RUOLO_PERSONA_REF)
            .tipoRuoloUtente(UPDATED_TIPO_RUOLO_UTENTE);
        PersonaDTO personaDTO = personaMapper.toDto(updatedPersona);

        restPersonaMockMvc.perform(put("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaDTO)))
            .andExpect(status().isOk());

        // Validate the Persona in the database
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeUpdate);
        Persona testPersona = personaList.get(personaList.size() - 1);
        assertThat(testPersona.getIdStudioProfessionaleRef()).isEqualTo(UPDATED_ID_STUDIO_PROFESSIONALE_REF);
        assertThat(testPersona.getCodiceFiscale()).isEqualTo(UPDATED_CODICE_FISCALE);
        assertThat(testPersona.getAreaDiInteresse()).isEqualTo(UPDATED_AREA_DI_INTERESSE);
        assertThat(testPersona.getTitolo()).isEqualTo(UPDATED_TITOLO);
        assertThat(testPersona.getCognome()).isEqualTo(UPDATED_COGNOME);
        assertThat(testPersona.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testPersona.getDataDiNascita()).isEqualTo(UPDATED_DATA_DI_NASCITA);
        assertThat(testPersona.getLuogoDiNascita()).isEqualTo(UPDATED_LUOGO_DI_NASCITA);
        assertThat(testPersona.getProfessione()).isEqualTo(UPDATED_PROFESSIONE);
        assertThat(testPersona.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testPersona.getDiscriminator()).isEqualTo(UPDATED_DISCRIMINATOR);
        assertThat(testPersona.getIdRuoloPersonaRef()).isEqualTo(UPDATED_ID_RUOLO_PERSONA_REF);
        assertThat(testPersona.getTipoRuoloUtente()).isEqualTo(UPDATED_TIPO_RUOLO_UTENTE);

        // Validate the Persona in Elasticsearch
        verify(mockPersonaSearchRepository, times(1)).save(testPersona);
    }

    @Test
    @Transactional
    public void updateNonExistingPersona() throws Exception {
        int databaseSizeBeforeUpdate = personaRepository.findAll().size();

        // Create the Persona
        PersonaDTO personaDTO = personaMapper.toDto(persona);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonaMockMvc.perform(put("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Persona in the database
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Persona in Elasticsearch
        verify(mockPersonaSearchRepository, times(0)).save(persona);
    }

    @Test
    @Transactional
    public void deletePersona() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        int databaseSizeBeforeDelete = personaRepository.findAll().size();

        // Delete the persona
        restPersonaMockMvc.perform(delete("/api/personas/{id}", persona.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Persona in Elasticsearch
        verify(mockPersonaSearchRepository, times(1)).deleteById(persona.getId());
    }

    @Test
    @Transactional
    public void searchPersona() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        personaRepository.saveAndFlush(persona);
        when(mockPersonaSearchRepository.search(queryStringQuery("id:" + persona.getId())))
            .thenReturn(Collections.singletonList(persona));

        // Search the persona
        restPersonaMockMvc.perform(get("/api/_search/personas?query=id:" + persona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(persona.getId().intValue())))
            .andExpect(jsonPath("$.[*].idStudioProfessionaleRef").value(hasItem(DEFAULT_ID_STUDIO_PROFESSIONALE_REF.intValue())))
            .andExpect(jsonPath("$.[*].codiceFiscale").value(hasItem(DEFAULT_CODICE_FISCALE)))
            .andExpect(jsonPath("$.[*].areaDiInteresse").value(hasItem(DEFAULT_AREA_DI_INTERESSE)))
            .andExpect(jsonPath("$.[*].titolo").value(hasItem(DEFAULT_TITOLO)))
            .andExpect(jsonPath("$.[*].cognome").value(hasItem(DEFAULT_COGNOME)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].dataDiNascita").value(hasItem(DEFAULT_DATA_DI_NASCITA)))
            .andExpect(jsonPath("$.[*].luogoDiNascita").value(hasItem(DEFAULT_LUOGO_DI_NASCITA)))
            .andExpect(jsonPath("$.[*].professione").value(hasItem(DEFAULT_PROFESSIONE)))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.intValue())))
            .andExpect(jsonPath("$.[*].discriminator").value(hasItem(DEFAULT_DISCRIMINATOR)))
            .andExpect(jsonPath("$.[*].idRuoloPersonaRef").value(hasItem(DEFAULT_ID_RUOLO_PERSONA_REF.intValue())))
            .andExpect(jsonPath("$.[*].tipoRuoloUtente").value(hasItem(DEFAULT_TIPO_RUOLO_UTENTE.intValue())));
    }
}
