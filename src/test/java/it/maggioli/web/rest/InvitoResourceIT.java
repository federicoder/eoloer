package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.Invito;
import it.maggioli.repository.InvitoRepository;
import it.maggioli.repository.search.InvitoSearchRepository;
import it.maggioli.service.InvitoService;
import it.maggioli.service.dto.InvitoDTO;
import it.maggioli.service.mapper.InvitoMapper;

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
 * Integration tests for the {@link InvitoResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class InvitoResourceIT {

    private static final Integer DEFAULT_ID_STUDIO_PROFESSIONALE = 8;
    private static final Integer UPDATED_ID_STUDIO_PROFESSIONALE = 7;

    private static final String DEFAULT_DATA_INVITO = "AAAAAAAAAA";
    private static final String UPDATED_DATA_INVITO = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_USER_INVITANTE = 1;
    private static final Integer UPDATED_ID_USER_INVITANTE = 2;

    private static final String DEFAULT_NOME_USER_INVITANTE = "AAAAAAAAAA";
    private static final String UPDATED_NOME_USER_INVITANTE = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_SCADENZA_INVITO = "AAAAAAAAAA";
    private static final String UPDATED_DATA_SCADENZA_INVITO = "BBBBBBBBBB";

    private static final String DEFAULT_TESTO_INVITO = "AAAAAAAAAA";
    private static final String UPDATED_TESTO_INVITO = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_PRATICA = 1;
    private static final Integer UPDATED_ID_PRATICA = 2;

    private static final Integer DEFAULT_ID_ATTIVITA = 1;
    private static final Integer UPDATED_ID_ATTIVITA = 2;

    private static final String DEFAULT_LUOGO_FISICO = "AAAAAAAAAA";
    private static final String UPDATED_LUOGO_FISICO = "BBBBBBBBBB";

    private static final String DEFAULT_INDICAZIONI_LUOGO = "AAAAAAAAAA";
    private static final String UPDATED_INDICAZIONI_LUOGO = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_INIZIO = "AAAAAAAAAA";
    private static final String UPDATED_DATA_INIZIO = "BBBBBBBBBB";

    private static final String DEFAULT_ORA_INIZIO = "AAAAAAAAAA";
    private static final String UPDATED_ORA_INIZIO = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_FINE = "AAAAAAAAAA";
    private static final String UPDATED_DATA_FINE = "BBBBBBBBBB";

    private static final String DEFAULT_ORA_FINE = "AAAAAAAAAA";
    private static final String UPDATED_ORA_FINE = "BBBBBBBBBB";

    private static final String DEFAULT_URL_STANZA_VIRTUALE = "AAAAAAAAAA";
    private static final String UPDATED_URL_STANZA_VIRTUALE = "BBBBBBBBBB";

    private static final String DEFAULT_DISCRIMINATOR = "AAAAAAAAAA";
    private static final String UPDATED_DISCRIMINATOR = "BBBBBBBBBB";

    @Autowired
    private InvitoRepository invitoRepository;

    @Autowired
    private InvitoMapper invitoMapper;

    @Autowired
    private InvitoService invitoService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.InvitoSearchRepositoryMockConfiguration
     */
    @Autowired
    private InvitoSearchRepository mockInvitoSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInvitoMockMvc;

    private Invito invito;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Invito createEntity(EntityManager em) {
        Invito invito = new Invito()
            .idStudioProfessionale(DEFAULT_ID_STUDIO_PROFESSIONALE)
            .dataInvito(DEFAULT_DATA_INVITO)
            .idUserInvitante(DEFAULT_ID_USER_INVITANTE)
            .nomeUserInvitante(DEFAULT_NOME_USER_INVITANTE)
            .dataScadenzaInvito(DEFAULT_DATA_SCADENZA_INVITO)
            .testoInvito(DEFAULT_TESTO_INVITO)
            .idPratica(DEFAULT_ID_PRATICA)
            .idAttivita(DEFAULT_ID_ATTIVITA)
            .luogoFisico(DEFAULT_LUOGO_FISICO)
            .indicazioniLuogo(DEFAULT_INDICAZIONI_LUOGO)
            .dataInizio(DEFAULT_DATA_INIZIO)
            .oraInizio(DEFAULT_ORA_INIZIO)
            .dataFine(DEFAULT_DATA_FINE)
            .oraFine(DEFAULT_ORA_FINE)
            .urlStanzaVirtuale(DEFAULT_URL_STANZA_VIRTUALE)
            .discriminator(DEFAULT_DISCRIMINATOR);
        return invito;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Invito createUpdatedEntity(EntityManager em) {
        Invito invito = new Invito()
            .idStudioProfessionale(UPDATED_ID_STUDIO_PROFESSIONALE)
            .dataInvito(UPDATED_DATA_INVITO)
            .idUserInvitante(UPDATED_ID_USER_INVITANTE)
            .nomeUserInvitante(UPDATED_NOME_USER_INVITANTE)
            .dataScadenzaInvito(UPDATED_DATA_SCADENZA_INVITO)
            .testoInvito(UPDATED_TESTO_INVITO)
            .idPratica(UPDATED_ID_PRATICA)
            .idAttivita(UPDATED_ID_ATTIVITA)
            .luogoFisico(UPDATED_LUOGO_FISICO)
            .indicazioniLuogo(UPDATED_INDICAZIONI_LUOGO)
            .dataInizio(UPDATED_DATA_INIZIO)
            .oraInizio(UPDATED_ORA_INIZIO)
            .dataFine(UPDATED_DATA_FINE)
            .oraFine(UPDATED_ORA_FINE)
            .urlStanzaVirtuale(UPDATED_URL_STANZA_VIRTUALE)
            .discriminator(UPDATED_DISCRIMINATOR);
        return invito;
    }

    @BeforeEach
    public void initTest() {
        invito = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvito() throws Exception {
        int databaseSizeBeforeCreate = invitoRepository.findAll().size();
        // Create the Invito
        InvitoDTO invitoDTO = invitoMapper.toDto(invito);
        restInvitoMockMvc.perform(post("/api/invitos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitoDTO)))
            .andExpect(status().isCreated());

        // Validate the Invito in the database
        List<Invito> invitoList = invitoRepository.findAll();
        assertThat(invitoList).hasSize(databaseSizeBeforeCreate + 1);
        Invito testInvito = invitoList.get(invitoList.size() - 1);
        assertThat(testInvito.getIdStudioProfessionale()).isEqualTo(DEFAULT_ID_STUDIO_PROFESSIONALE);
        assertThat(testInvito.getDataInvito()).isEqualTo(DEFAULT_DATA_INVITO);
        assertThat(testInvito.getIdUserInvitante()).isEqualTo(DEFAULT_ID_USER_INVITANTE);
        assertThat(testInvito.getNomeUserInvitante()).isEqualTo(DEFAULT_NOME_USER_INVITANTE);
        assertThat(testInvito.getDataScadenzaInvito()).isEqualTo(DEFAULT_DATA_SCADENZA_INVITO);
        assertThat(testInvito.getTestoInvito()).isEqualTo(DEFAULT_TESTO_INVITO);
        assertThat(testInvito.getIdPratica()).isEqualTo(DEFAULT_ID_PRATICA);
        assertThat(testInvito.getIdAttivita()).isEqualTo(DEFAULT_ID_ATTIVITA);
        assertThat(testInvito.getLuogoFisico()).isEqualTo(DEFAULT_LUOGO_FISICO);
        assertThat(testInvito.getIndicazioniLuogo()).isEqualTo(DEFAULT_INDICAZIONI_LUOGO);
        assertThat(testInvito.getDataInizio()).isEqualTo(DEFAULT_DATA_INIZIO);
        assertThat(testInvito.getOraInizio()).isEqualTo(DEFAULT_ORA_INIZIO);
        assertThat(testInvito.getDataFine()).isEqualTo(DEFAULT_DATA_FINE);
        assertThat(testInvito.getOraFine()).isEqualTo(DEFAULT_ORA_FINE);
        assertThat(testInvito.getUrlStanzaVirtuale()).isEqualTo(DEFAULT_URL_STANZA_VIRTUALE);
        assertThat(testInvito.getDiscriminator()).isEqualTo(DEFAULT_DISCRIMINATOR);

        // Validate the Invito in Elasticsearch
        verify(mockInvitoSearchRepository, times(1)).save(testInvito);
    }

    @Test
    @Transactional
    public void createInvitoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invitoRepository.findAll().size();

        // Create the Invito with an existing ID
        invito.setId(1L);
        InvitoDTO invitoDTO = invitoMapper.toDto(invito);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvitoMockMvc.perform(post("/api/invitos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Invito in the database
        List<Invito> invitoList = invitoRepository.findAll();
        assertThat(invitoList).hasSize(databaseSizeBeforeCreate);

        // Validate the Invito in Elasticsearch
        verify(mockInvitoSearchRepository, times(0)).save(invito);
    }


    @Test
    @Transactional
    public void getAllInvitos() throws Exception {
        // Initialize the database
        invitoRepository.saveAndFlush(invito);

        // Get all the invitoList
        restInvitoMockMvc.perform(get("/api/invitos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invito.getId().intValue())))
            .andExpect(jsonPath("$.[*].idStudioProfessionale").value(hasItem(DEFAULT_ID_STUDIO_PROFESSIONALE)))
            .andExpect(jsonPath("$.[*].dataInvito").value(hasItem(DEFAULT_DATA_INVITO)))
            .andExpect(jsonPath("$.[*].idUserInvitante").value(hasItem(DEFAULT_ID_USER_INVITANTE)))
            .andExpect(jsonPath("$.[*].nomeUserInvitante").value(hasItem(DEFAULT_NOME_USER_INVITANTE)))
            .andExpect(jsonPath("$.[*].dataScadenzaInvito").value(hasItem(DEFAULT_DATA_SCADENZA_INVITO)))
            .andExpect(jsonPath("$.[*].testoInvito").value(hasItem(DEFAULT_TESTO_INVITO)))
            .andExpect(jsonPath("$.[*].idPratica").value(hasItem(DEFAULT_ID_PRATICA)))
            .andExpect(jsonPath("$.[*].idAttivita").value(hasItem(DEFAULT_ID_ATTIVITA)))
            .andExpect(jsonPath("$.[*].luogoFisico").value(hasItem(DEFAULT_LUOGO_FISICO)))
            .andExpect(jsonPath("$.[*].indicazioniLuogo").value(hasItem(DEFAULT_INDICAZIONI_LUOGO)))
            .andExpect(jsonPath("$.[*].dataInizio").value(hasItem(DEFAULT_DATA_INIZIO)))
            .andExpect(jsonPath("$.[*].oraInizio").value(hasItem(DEFAULT_ORA_INIZIO)))
            .andExpect(jsonPath("$.[*].dataFine").value(hasItem(DEFAULT_DATA_FINE)))
            .andExpect(jsonPath("$.[*].oraFine").value(hasItem(DEFAULT_ORA_FINE)))
            .andExpect(jsonPath("$.[*].urlStanzaVirtuale").value(hasItem(DEFAULT_URL_STANZA_VIRTUALE)))
            .andExpect(jsonPath("$.[*].discriminator").value(hasItem(DEFAULT_DISCRIMINATOR)));
    }
    
    @Test
    @Transactional
    public void getInvito() throws Exception {
        // Initialize the database
        invitoRepository.saveAndFlush(invito);

        // Get the invito
        restInvitoMockMvc.perform(get("/api/invitos/{id}", invito.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(invito.getId().intValue()))
            .andExpect(jsonPath("$.idStudioProfessionale").value(DEFAULT_ID_STUDIO_PROFESSIONALE))
            .andExpect(jsonPath("$.dataInvito").value(DEFAULT_DATA_INVITO))
            .andExpect(jsonPath("$.idUserInvitante").value(DEFAULT_ID_USER_INVITANTE))
            .andExpect(jsonPath("$.nomeUserInvitante").value(DEFAULT_NOME_USER_INVITANTE))
            .andExpect(jsonPath("$.dataScadenzaInvito").value(DEFAULT_DATA_SCADENZA_INVITO))
            .andExpect(jsonPath("$.testoInvito").value(DEFAULT_TESTO_INVITO))
            .andExpect(jsonPath("$.idPratica").value(DEFAULT_ID_PRATICA))
            .andExpect(jsonPath("$.idAttivita").value(DEFAULT_ID_ATTIVITA))
            .andExpect(jsonPath("$.luogoFisico").value(DEFAULT_LUOGO_FISICO))
            .andExpect(jsonPath("$.indicazioniLuogo").value(DEFAULT_INDICAZIONI_LUOGO))
            .andExpect(jsonPath("$.dataInizio").value(DEFAULT_DATA_INIZIO))
            .andExpect(jsonPath("$.oraInizio").value(DEFAULT_ORA_INIZIO))
            .andExpect(jsonPath("$.dataFine").value(DEFAULT_DATA_FINE))
            .andExpect(jsonPath("$.oraFine").value(DEFAULT_ORA_FINE))
            .andExpect(jsonPath("$.urlStanzaVirtuale").value(DEFAULT_URL_STANZA_VIRTUALE))
            .andExpect(jsonPath("$.discriminator").value(DEFAULT_DISCRIMINATOR));
    }
    @Test
    @Transactional
    public void getNonExistingInvito() throws Exception {
        // Get the invito
        restInvitoMockMvc.perform(get("/api/invitos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvito() throws Exception {
        // Initialize the database
        invitoRepository.saveAndFlush(invito);

        int databaseSizeBeforeUpdate = invitoRepository.findAll().size();

        // Update the invito
        Invito updatedInvito = invitoRepository.findById(invito.getId()).get();
        // Disconnect from session so that the updates on updatedInvito are not directly saved in db
        em.detach(updatedInvito);
        updatedInvito
            .idStudioProfessionale(UPDATED_ID_STUDIO_PROFESSIONALE)
            .dataInvito(UPDATED_DATA_INVITO)
            .idUserInvitante(UPDATED_ID_USER_INVITANTE)
            .nomeUserInvitante(UPDATED_NOME_USER_INVITANTE)
            .dataScadenzaInvito(UPDATED_DATA_SCADENZA_INVITO)
            .testoInvito(UPDATED_TESTO_INVITO)
            .idPratica(UPDATED_ID_PRATICA)
            .idAttivita(UPDATED_ID_ATTIVITA)
            .luogoFisico(UPDATED_LUOGO_FISICO)
            .indicazioniLuogo(UPDATED_INDICAZIONI_LUOGO)
            .dataInizio(UPDATED_DATA_INIZIO)
            .oraInizio(UPDATED_ORA_INIZIO)
            .dataFine(UPDATED_DATA_FINE)
            .oraFine(UPDATED_ORA_FINE)
            .urlStanzaVirtuale(UPDATED_URL_STANZA_VIRTUALE)
            .discriminator(UPDATED_DISCRIMINATOR);
        InvitoDTO invitoDTO = invitoMapper.toDto(updatedInvito);

        restInvitoMockMvc.perform(put("/api/invitos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitoDTO)))
            .andExpect(status().isOk());

        // Validate the Invito in the database
        List<Invito> invitoList = invitoRepository.findAll();
        assertThat(invitoList).hasSize(databaseSizeBeforeUpdate);
        Invito testInvito = invitoList.get(invitoList.size() - 1);
        assertThat(testInvito.getIdStudioProfessionale()).isEqualTo(UPDATED_ID_STUDIO_PROFESSIONALE);
        assertThat(testInvito.getDataInvito()).isEqualTo(UPDATED_DATA_INVITO);
        assertThat(testInvito.getIdUserInvitante()).isEqualTo(UPDATED_ID_USER_INVITANTE);
        assertThat(testInvito.getNomeUserInvitante()).isEqualTo(UPDATED_NOME_USER_INVITANTE);
        assertThat(testInvito.getDataScadenzaInvito()).isEqualTo(UPDATED_DATA_SCADENZA_INVITO);
        assertThat(testInvito.getTestoInvito()).isEqualTo(UPDATED_TESTO_INVITO);
        assertThat(testInvito.getIdPratica()).isEqualTo(UPDATED_ID_PRATICA);
        assertThat(testInvito.getIdAttivita()).isEqualTo(UPDATED_ID_ATTIVITA);
        assertThat(testInvito.getLuogoFisico()).isEqualTo(UPDATED_LUOGO_FISICO);
        assertThat(testInvito.getIndicazioniLuogo()).isEqualTo(UPDATED_INDICAZIONI_LUOGO);
        assertThat(testInvito.getDataInizio()).isEqualTo(UPDATED_DATA_INIZIO);
        assertThat(testInvito.getOraInizio()).isEqualTo(UPDATED_ORA_INIZIO);
        assertThat(testInvito.getDataFine()).isEqualTo(UPDATED_DATA_FINE);
        assertThat(testInvito.getOraFine()).isEqualTo(UPDATED_ORA_FINE);
        assertThat(testInvito.getUrlStanzaVirtuale()).isEqualTo(UPDATED_URL_STANZA_VIRTUALE);
        assertThat(testInvito.getDiscriminator()).isEqualTo(UPDATED_DISCRIMINATOR);

        // Validate the Invito in Elasticsearch
        verify(mockInvitoSearchRepository, times(1)).save(testInvito);
    }

    @Test
    @Transactional
    public void updateNonExistingInvito() throws Exception {
        int databaseSizeBeforeUpdate = invitoRepository.findAll().size();

        // Create the Invito
        InvitoDTO invitoDTO = invitoMapper.toDto(invito);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvitoMockMvc.perform(put("/api/invitos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Invito in the database
        List<Invito> invitoList = invitoRepository.findAll();
        assertThat(invitoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Invito in Elasticsearch
        verify(mockInvitoSearchRepository, times(0)).save(invito);
    }

    @Test
    @Transactional
    public void deleteInvito() throws Exception {
        // Initialize the database
        invitoRepository.saveAndFlush(invito);

        int databaseSizeBeforeDelete = invitoRepository.findAll().size();

        // Delete the invito
        restInvitoMockMvc.perform(delete("/api/invitos/{id}", invito.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Invito> invitoList = invitoRepository.findAll();
        assertThat(invitoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Invito in Elasticsearch
        verify(mockInvitoSearchRepository, times(1)).deleteById(invito.getId());
    }

    @Test
    @Transactional
    public void searchInvito() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        invitoRepository.saveAndFlush(invito);
        when(mockInvitoSearchRepository.search(queryStringQuery("id:" + invito.getId())))
            .thenReturn(Collections.singletonList(invito));

        // Search the invito
        restInvitoMockMvc.perform(get("/api/_search/invitos?query=id:" + invito.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invito.getId().intValue())))
            .andExpect(jsonPath("$.[*].idStudioProfessionale").value(hasItem(DEFAULT_ID_STUDIO_PROFESSIONALE)))
            .andExpect(jsonPath("$.[*].dataInvito").value(hasItem(DEFAULT_DATA_INVITO)))
            .andExpect(jsonPath("$.[*].idUserInvitante").value(hasItem(DEFAULT_ID_USER_INVITANTE)))
            .andExpect(jsonPath("$.[*].nomeUserInvitante").value(hasItem(DEFAULT_NOME_USER_INVITANTE)))
            .andExpect(jsonPath("$.[*].dataScadenzaInvito").value(hasItem(DEFAULT_DATA_SCADENZA_INVITO)))
            .andExpect(jsonPath("$.[*].testoInvito").value(hasItem(DEFAULT_TESTO_INVITO)))
            .andExpect(jsonPath("$.[*].idPratica").value(hasItem(DEFAULT_ID_PRATICA)))
            .andExpect(jsonPath("$.[*].idAttivita").value(hasItem(DEFAULT_ID_ATTIVITA)))
            .andExpect(jsonPath("$.[*].luogoFisico").value(hasItem(DEFAULT_LUOGO_FISICO)))
            .andExpect(jsonPath("$.[*].indicazioniLuogo").value(hasItem(DEFAULT_INDICAZIONI_LUOGO)))
            .andExpect(jsonPath("$.[*].dataInizio").value(hasItem(DEFAULT_DATA_INIZIO)))
            .andExpect(jsonPath("$.[*].oraInizio").value(hasItem(DEFAULT_ORA_INIZIO)))
            .andExpect(jsonPath("$.[*].dataFine").value(hasItem(DEFAULT_DATA_FINE)))
            .andExpect(jsonPath("$.[*].oraFine").value(hasItem(DEFAULT_ORA_FINE)))
            .andExpect(jsonPath("$.[*].urlStanzaVirtuale").value(hasItem(DEFAULT_URL_STANZA_VIRTUALE)))
            .andExpect(jsonPath("$.[*].discriminator").value(hasItem(DEFAULT_DISCRIMINATOR)));
    }
}
