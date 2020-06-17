package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.Invitato;
import it.maggioli.repository.InvitatoRepository;
import it.maggioli.repository.search.InvitatoSearchRepository;
import it.maggioli.service.InvitatoService;
import it.maggioli.service.dto.InvitatoDTO;
import it.maggioli.service.mapper.InvitatoMapper;

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
 * Integration tests for the {@link InvitatoResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class InvitatoResourceIT {

    private static final Integer DEFAULT_ID_INVITATO = 1;
    private static final Integer UPDATED_ID_INVITATO = 2;

    private static final Integer DEFAULT_ID_INVITO_REF = 8;
    private static final Integer UPDATED_ID_INVITO_REF = 7;

    private static final String DEFAULT_TOKEN_INVITO = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN_INVITO = "BBBBBBBBBB";

    private static final Integer DEFAULT_CANALE_PRIMARIO_INVITO = 1;
    private static final Integer UPDATED_CANALE_PRIMARIO_INVITO = 2;

    private static final Integer DEFAULT_CANALE_BACKUP_INVITO = 1;
    private static final Integer UPDATED_CANALE_BACKUP_INVITO = 2;

    private static final Integer DEFAULT_STATO_INVITO = 1;
    private static final Integer UPDATED_STATO_INVITO = 2;

    private static final Integer DEFAULT_ID_USER_INVITATO = 1;
    private static final Integer UPDATED_ID_USER_INVITATO = 2;

    private static final Integer DEFAULT_ID_PERSONA_INVITATA = 1;
    private static final Integer UPDATED_ID_PERSONA_INVITATA = 2;

    private static final String DEFAULT_NOME_USER_INVITATO = "AAAAAAAAAA";
    private static final String UPDATED_NOME_USER_INVITATO = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_RISPOSTA_INVITO = "AAAAAAAAAA";
    private static final String UPDATED_DATA_RISPOSTA_INVITO = "BBBBBBBBBB";

    private static final Integer DEFAULT_RUOLO_INVITATO = 1;
    private static final Integer UPDATED_RUOLO_INVITATO = 2;

    private static final Integer DEFAULT_IND_INVITATI = 1;
    private static final Integer UPDATED_IND_INVITATI = 2;

    @Autowired
    private InvitatoRepository invitatoRepository;

    @Autowired
    private InvitatoMapper invitatoMapper;

    @Autowired
    private InvitatoService invitatoService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.InvitatoSearchRepositoryMockConfiguration
     */
    @Autowired
    private InvitatoSearchRepository mockInvitatoSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInvitatoMockMvc;

    private Invitato invitato;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Invitato createEntity(EntityManager em) {
        Invitato invitato = new Invitato()
            .idInvitato(DEFAULT_ID_INVITATO)
            .idInvitoRef(DEFAULT_ID_INVITO_REF)
            .tokenInvito(DEFAULT_TOKEN_INVITO)
            .canalePrimarioInvito(DEFAULT_CANALE_PRIMARIO_INVITO)
            .canaleBackupInvito(DEFAULT_CANALE_BACKUP_INVITO)
            .statoInvito(DEFAULT_STATO_INVITO)
            .idUserInvitato(DEFAULT_ID_USER_INVITATO)
            .idPersonaInvitata(DEFAULT_ID_PERSONA_INVITATA)
            .nomeUserInvitato(DEFAULT_NOME_USER_INVITATO)
            .dataRispostaInvito(DEFAULT_DATA_RISPOSTA_INVITO)
            .ruoloInvitato(DEFAULT_RUOLO_INVITATO)
            .indInvitati(DEFAULT_IND_INVITATI);
        return invitato;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Invitato createUpdatedEntity(EntityManager em) {
        Invitato invitato = new Invitato()
            .idInvitato(UPDATED_ID_INVITATO)
            .idInvitoRef(UPDATED_ID_INVITO_REF)
            .tokenInvito(UPDATED_TOKEN_INVITO)
            .canalePrimarioInvito(UPDATED_CANALE_PRIMARIO_INVITO)
            .canaleBackupInvito(UPDATED_CANALE_BACKUP_INVITO)
            .statoInvito(UPDATED_STATO_INVITO)
            .idUserInvitato(UPDATED_ID_USER_INVITATO)
            .idPersonaInvitata(UPDATED_ID_PERSONA_INVITATA)
            .nomeUserInvitato(UPDATED_NOME_USER_INVITATO)
            .dataRispostaInvito(UPDATED_DATA_RISPOSTA_INVITO)
            .ruoloInvitato(UPDATED_RUOLO_INVITATO)
            .indInvitati(UPDATED_IND_INVITATI);
        return invitato;
    }

    @BeforeEach
    public void initTest() {
        invitato = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvitato() throws Exception {
        int databaseSizeBeforeCreate = invitatoRepository.findAll().size();
        // Create the Invitato
        InvitatoDTO invitatoDTO = invitatoMapper.toDto(invitato);
        restInvitatoMockMvc.perform(post("/api/invitatoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitatoDTO)))
            .andExpect(status().isCreated());

        // Validate the Invitato in the database
        List<Invitato> invitatoList = invitatoRepository.findAll();
        assertThat(invitatoList).hasSize(databaseSizeBeforeCreate + 1);
        Invitato testInvitato = invitatoList.get(invitatoList.size() - 1);
        assertThat(testInvitato.getIdInvitato()).isEqualTo(DEFAULT_ID_INVITATO);
        assertThat(testInvitato.getIdInvitoRef()).isEqualTo(DEFAULT_ID_INVITO_REF);
        assertThat(testInvitato.getTokenInvito()).isEqualTo(DEFAULT_TOKEN_INVITO);
        assertThat(testInvitato.getCanalePrimarioInvito()).isEqualTo(DEFAULT_CANALE_PRIMARIO_INVITO);
        assertThat(testInvitato.getCanaleBackupInvito()).isEqualTo(DEFAULT_CANALE_BACKUP_INVITO);
        assertThat(testInvitato.getStatoInvito()).isEqualTo(DEFAULT_STATO_INVITO);
        assertThat(testInvitato.getIdUserInvitato()).isEqualTo(DEFAULT_ID_USER_INVITATO);
        assertThat(testInvitato.getIdPersonaInvitata()).isEqualTo(DEFAULT_ID_PERSONA_INVITATA);
        assertThat(testInvitato.getNomeUserInvitato()).isEqualTo(DEFAULT_NOME_USER_INVITATO);
        assertThat(testInvitato.getDataRispostaInvito()).isEqualTo(DEFAULT_DATA_RISPOSTA_INVITO);
        assertThat(testInvitato.getRuoloInvitato()).isEqualTo(DEFAULT_RUOLO_INVITATO);
        assertThat(testInvitato.getIndInvitati()).isEqualTo(DEFAULT_IND_INVITATI);

        // Validate the Invitato in Elasticsearch
        verify(mockInvitatoSearchRepository, times(1)).save(testInvitato);
    }

    @Test
    @Transactional
    public void createInvitatoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invitatoRepository.findAll().size();

        // Create the Invitato with an existing ID
        invitato.setId(1L);
        InvitatoDTO invitatoDTO = invitatoMapper.toDto(invitato);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvitatoMockMvc.perform(post("/api/invitatoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Invitato in the database
        List<Invitato> invitatoList = invitatoRepository.findAll();
        assertThat(invitatoList).hasSize(databaseSizeBeforeCreate);

        // Validate the Invitato in Elasticsearch
        verify(mockInvitatoSearchRepository, times(0)).save(invitato);
    }


    @Test
    @Transactional
    public void checkIdInvitatoIsRequired() throws Exception {
        int databaseSizeBeforeTest = invitatoRepository.findAll().size();
        // set the field null
        invitato.setIdInvitato(null);

        // Create the Invitato, which fails.
        InvitatoDTO invitatoDTO = invitatoMapper.toDto(invitato);


        restInvitatoMockMvc.perform(post("/api/invitatoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitatoDTO)))
            .andExpect(status().isBadRequest());

        List<Invitato> invitatoList = invitatoRepository.findAll();
        assertThat(invitatoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInvitatoes() throws Exception {
        // Initialize the database
        invitatoRepository.saveAndFlush(invitato);

        // Get all the invitatoList
        restInvitatoMockMvc.perform(get("/api/invitatoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invitato.getId().intValue())))
            .andExpect(jsonPath("$.[*].idInvitato").value(hasItem(DEFAULT_ID_INVITATO)))
            .andExpect(jsonPath("$.[*].idInvitoRef").value(hasItem(DEFAULT_ID_INVITO_REF)))
            .andExpect(jsonPath("$.[*].tokenInvito").value(hasItem(DEFAULT_TOKEN_INVITO)))
            .andExpect(jsonPath("$.[*].canalePrimarioInvito").value(hasItem(DEFAULT_CANALE_PRIMARIO_INVITO)))
            .andExpect(jsonPath("$.[*].canaleBackupInvito").value(hasItem(DEFAULT_CANALE_BACKUP_INVITO)))
            .andExpect(jsonPath("$.[*].statoInvito").value(hasItem(DEFAULT_STATO_INVITO)))
            .andExpect(jsonPath("$.[*].idUserInvitato").value(hasItem(DEFAULT_ID_USER_INVITATO)))
            .andExpect(jsonPath("$.[*].idPersonaInvitata").value(hasItem(DEFAULT_ID_PERSONA_INVITATA)))
            .andExpect(jsonPath("$.[*].nomeUserInvitato").value(hasItem(DEFAULT_NOME_USER_INVITATO)))
            .andExpect(jsonPath("$.[*].dataRispostaInvito").value(hasItem(DEFAULT_DATA_RISPOSTA_INVITO)))
            .andExpect(jsonPath("$.[*].ruoloInvitato").value(hasItem(DEFAULT_RUOLO_INVITATO)))
            .andExpect(jsonPath("$.[*].indInvitati").value(hasItem(DEFAULT_IND_INVITATI)));
    }
    
    @Test
    @Transactional
    public void getInvitato() throws Exception {
        // Initialize the database
        invitatoRepository.saveAndFlush(invitato);

        // Get the invitato
        restInvitatoMockMvc.perform(get("/api/invitatoes/{id}", invitato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(invitato.getId().intValue()))
            .andExpect(jsonPath("$.idInvitato").value(DEFAULT_ID_INVITATO))
            .andExpect(jsonPath("$.idInvitoRef").value(DEFAULT_ID_INVITO_REF))
            .andExpect(jsonPath("$.tokenInvito").value(DEFAULT_TOKEN_INVITO))
            .andExpect(jsonPath("$.canalePrimarioInvito").value(DEFAULT_CANALE_PRIMARIO_INVITO))
            .andExpect(jsonPath("$.canaleBackupInvito").value(DEFAULT_CANALE_BACKUP_INVITO))
            .andExpect(jsonPath("$.statoInvito").value(DEFAULT_STATO_INVITO))
            .andExpect(jsonPath("$.idUserInvitato").value(DEFAULT_ID_USER_INVITATO))
            .andExpect(jsonPath("$.idPersonaInvitata").value(DEFAULT_ID_PERSONA_INVITATA))
            .andExpect(jsonPath("$.nomeUserInvitato").value(DEFAULT_NOME_USER_INVITATO))
            .andExpect(jsonPath("$.dataRispostaInvito").value(DEFAULT_DATA_RISPOSTA_INVITO))
            .andExpect(jsonPath("$.ruoloInvitato").value(DEFAULT_RUOLO_INVITATO))
            .andExpect(jsonPath("$.indInvitati").value(DEFAULT_IND_INVITATI));
    }
    @Test
    @Transactional
    public void getNonExistingInvitato() throws Exception {
        // Get the invitato
        restInvitatoMockMvc.perform(get("/api/invitatoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvitato() throws Exception {
        // Initialize the database
        invitatoRepository.saveAndFlush(invitato);

        int databaseSizeBeforeUpdate = invitatoRepository.findAll().size();

        // Update the invitato
        Invitato updatedInvitato = invitatoRepository.findById(invitato.getId()).get();
        // Disconnect from session so that the updates on updatedInvitato are not directly saved in db
        em.detach(updatedInvitato);
        updatedInvitato
            .idInvitato(UPDATED_ID_INVITATO)
            .idInvitoRef(UPDATED_ID_INVITO_REF)
            .tokenInvito(UPDATED_TOKEN_INVITO)
            .canalePrimarioInvito(UPDATED_CANALE_PRIMARIO_INVITO)
            .canaleBackupInvito(UPDATED_CANALE_BACKUP_INVITO)
            .statoInvito(UPDATED_STATO_INVITO)
            .idUserInvitato(UPDATED_ID_USER_INVITATO)
            .idPersonaInvitata(UPDATED_ID_PERSONA_INVITATA)
            .nomeUserInvitato(UPDATED_NOME_USER_INVITATO)
            .dataRispostaInvito(UPDATED_DATA_RISPOSTA_INVITO)
            .ruoloInvitato(UPDATED_RUOLO_INVITATO)
            .indInvitati(UPDATED_IND_INVITATI);
        InvitatoDTO invitatoDTO = invitatoMapper.toDto(updatedInvitato);

        restInvitatoMockMvc.perform(put("/api/invitatoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitatoDTO)))
            .andExpect(status().isOk());

        // Validate the Invitato in the database
        List<Invitato> invitatoList = invitatoRepository.findAll();
        assertThat(invitatoList).hasSize(databaseSizeBeforeUpdate);
        Invitato testInvitato = invitatoList.get(invitatoList.size() - 1);
        assertThat(testInvitato.getIdInvitato()).isEqualTo(UPDATED_ID_INVITATO);
        assertThat(testInvitato.getIdInvitoRef()).isEqualTo(UPDATED_ID_INVITO_REF);
        assertThat(testInvitato.getTokenInvito()).isEqualTo(UPDATED_TOKEN_INVITO);
        assertThat(testInvitato.getCanalePrimarioInvito()).isEqualTo(UPDATED_CANALE_PRIMARIO_INVITO);
        assertThat(testInvitato.getCanaleBackupInvito()).isEqualTo(UPDATED_CANALE_BACKUP_INVITO);
        assertThat(testInvitato.getStatoInvito()).isEqualTo(UPDATED_STATO_INVITO);
        assertThat(testInvitato.getIdUserInvitato()).isEqualTo(UPDATED_ID_USER_INVITATO);
        assertThat(testInvitato.getIdPersonaInvitata()).isEqualTo(UPDATED_ID_PERSONA_INVITATA);
        assertThat(testInvitato.getNomeUserInvitato()).isEqualTo(UPDATED_NOME_USER_INVITATO);
        assertThat(testInvitato.getDataRispostaInvito()).isEqualTo(UPDATED_DATA_RISPOSTA_INVITO);
        assertThat(testInvitato.getRuoloInvitato()).isEqualTo(UPDATED_RUOLO_INVITATO);
        assertThat(testInvitato.getIndInvitati()).isEqualTo(UPDATED_IND_INVITATI);

        // Validate the Invitato in Elasticsearch
        verify(mockInvitatoSearchRepository, times(1)).save(testInvitato);
    }

    @Test
    @Transactional
    public void updateNonExistingInvitato() throws Exception {
        int databaseSizeBeforeUpdate = invitatoRepository.findAll().size();

        // Create the Invitato
        InvitatoDTO invitatoDTO = invitatoMapper.toDto(invitato);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvitatoMockMvc.perform(put("/api/invitatoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Invitato in the database
        List<Invitato> invitatoList = invitatoRepository.findAll();
        assertThat(invitatoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Invitato in Elasticsearch
        verify(mockInvitatoSearchRepository, times(0)).save(invitato);
    }

    @Test
    @Transactional
    public void deleteInvitato() throws Exception {
        // Initialize the database
        invitatoRepository.saveAndFlush(invitato);

        int databaseSizeBeforeDelete = invitatoRepository.findAll().size();

        // Delete the invitato
        restInvitatoMockMvc.perform(delete("/api/invitatoes/{id}", invitato.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Invitato> invitatoList = invitatoRepository.findAll();
        assertThat(invitatoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Invitato in Elasticsearch
        verify(mockInvitatoSearchRepository, times(1)).deleteById(invitato.getId());
    }

    @Test
    @Transactional
    public void searchInvitato() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        invitatoRepository.saveAndFlush(invitato);
        when(mockInvitatoSearchRepository.search(queryStringQuery("id:" + invitato.getId())))
            .thenReturn(Collections.singletonList(invitato));

        // Search the invitato
        restInvitatoMockMvc.perform(get("/api/_search/invitatoes?query=id:" + invitato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invitato.getId().intValue())))
            .andExpect(jsonPath("$.[*].idInvitato").value(hasItem(DEFAULT_ID_INVITATO)))
            .andExpect(jsonPath("$.[*].idInvitoRef").value(hasItem(DEFAULT_ID_INVITO_REF)))
            .andExpect(jsonPath("$.[*].tokenInvito").value(hasItem(DEFAULT_TOKEN_INVITO)))
            .andExpect(jsonPath("$.[*].canalePrimarioInvito").value(hasItem(DEFAULT_CANALE_PRIMARIO_INVITO)))
            .andExpect(jsonPath("$.[*].canaleBackupInvito").value(hasItem(DEFAULT_CANALE_BACKUP_INVITO)))
            .andExpect(jsonPath("$.[*].statoInvito").value(hasItem(DEFAULT_STATO_INVITO)))
            .andExpect(jsonPath("$.[*].idUserInvitato").value(hasItem(DEFAULT_ID_USER_INVITATO)))
            .andExpect(jsonPath("$.[*].idPersonaInvitata").value(hasItem(DEFAULT_ID_PERSONA_INVITATA)))
            .andExpect(jsonPath("$.[*].nomeUserInvitato").value(hasItem(DEFAULT_NOME_USER_INVITATO)))
            .andExpect(jsonPath("$.[*].dataRispostaInvito").value(hasItem(DEFAULT_DATA_RISPOSTA_INVITO)))
            .andExpect(jsonPath("$.[*].ruoloInvitato").value(hasItem(DEFAULT_RUOLO_INVITATO)))
            .andExpect(jsonPath("$.[*].indInvitati").value(hasItem(DEFAULT_IND_INVITATI)));
    }
}
