package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.InvitoEvento;
import it.maggioli.repository.InvitoEventoRepository;
import it.maggioli.repository.search.InvitoEventoSearchRepository;
import it.maggioli.service.InvitoEventoService;
import it.maggioli.service.dto.InvitoEventoDTO;
import it.maggioli.service.mapper.InvitoEventoMapper;

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
 * Integration tests for the {@link InvitoEventoResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class InvitoEventoResourceIT {

    private static final Integer DEFAULT_ID_TASK_REF = 1;
    private static final Integer UPDATED_ID_TASK_REF = 2;

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

    @Autowired
    private InvitoEventoRepository invitoEventoRepository;

    @Autowired
    private InvitoEventoMapper invitoEventoMapper;

    @Autowired
    private InvitoEventoService invitoEventoService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.InvitoEventoSearchRepositoryMockConfiguration
     */
    @Autowired
    private InvitoEventoSearchRepository mockInvitoEventoSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInvitoEventoMockMvc;

    private InvitoEvento invitoEvento;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvitoEvento createEntity(EntityManager em) {
        InvitoEvento invitoEvento = new InvitoEvento()
            .idTaskRef(DEFAULT_ID_TASK_REF)
            .luogoFisico(DEFAULT_LUOGO_FISICO)
            .indicazioniLuogo(DEFAULT_INDICAZIONI_LUOGO)
            .dataInizio(DEFAULT_DATA_INIZIO)
            .oraInizio(DEFAULT_ORA_INIZIO)
            .dataFine(DEFAULT_DATA_FINE)
            .oraFine(DEFAULT_ORA_FINE)
            .urlStanzaVirtuale(DEFAULT_URL_STANZA_VIRTUALE);
        return invitoEvento;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvitoEvento createUpdatedEntity(EntityManager em) {
        InvitoEvento invitoEvento = new InvitoEvento()
            .idTaskRef(UPDATED_ID_TASK_REF)
            .luogoFisico(UPDATED_LUOGO_FISICO)
            .indicazioniLuogo(UPDATED_INDICAZIONI_LUOGO)
            .dataInizio(UPDATED_DATA_INIZIO)
            .oraInizio(UPDATED_ORA_INIZIO)
            .dataFine(UPDATED_DATA_FINE)
            .oraFine(UPDATED_ORA_FINE)
            .urlStanzaVirtuale(UPDATED_URL_STANZA_VIRTUALE);
        return invitoEvento;
    }

    @BeforeEach
    public void initTest() {
        invitoEvento = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvitoEvento() throws Exception {
        int databaseSizeBeforeCreate = invitoEventoRepository.findAll().size();
        // Create the InvitoEvento
        InvitoEventoDTO invitoEventoDTO = invitoEventoMapper.toDto(invitoEvento);
        restInvitoEventoMockMvc.perform(post("/api/invito-eventos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitoEventoDTO)))
            .andExpect(status().isCreated());

        // Validate the InvitoEvento in the database
        List<InvitoEvento> invitoEventoList = invitoEventoRepository.findAll();
        assertThat(invitoEventoList).hasSize(databaseSizeBeforeCreate + 1);
        InvitoEvento testInvitoEvento = invitoEventoList.get(invitoEventoList.size() - 1);
        assertThat(testInvitoEvento.getIdTaskRef()).isEqualTo(DEFAULT_ID_TASK_REF);
        assertThat(testInvitoEvento.getLuogoFisico()).isEqualTo(DEFAULT_LUOGO_FISICO);
        assertThat(testInvitoEvento.getIndicazioniLuogo()).isEqualTo(DEFAULT_INDICAZIONI_LUOGO);
        assertThat(testInvitoEvento.getDataInizio()).isEqualTo(DEFAULT_DATA_INIZIO);
        assertThat(testInvitoEvento.getOraInizio()).isEqualTo(DEFAULT_ORA_INIZIO);
        assertThat(testInvitoEvento.getDataFine()).isEqualTo(DEFAULT_DATA_FINE);
        assertThat(testInvitoEvento.getOraFine()).isEqualTo(DEFAULT_ORA_FINE);
        assertThat(testInvitoEvento.getUrlStanzaVirtuale()).isEqualTo(DEFAULT_URL_STANZA_VIRTUALE);

        // Validate the InvitoEvento in Elasticsearch
        verify(mockInvitoEventoSearchRepository, times(1)).save(testInvitoEvento);
    }

    @Test
    @Transactional
    public void createInvitoEventoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = invitoEventoRepository.findAll().size();

        // Create the InvitoEvento with an existing ID
        invitoEvento.setId(1L);
        InvitoEventoDTO invitoEventoDTO = invitoEventoMapper.toDto(invitoEvento);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvitoEventoMockMvc.perform(post("/api/invito-eventos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitoEventoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvitoEvento in the database
        List<InvitoEvento> invitoEventoList = invitoEventoRepository.findAll();
        assertThat(invitoEventoList).hasSize(databaseSizeBeforeCreate);

        // Validate the InvitoEvento in Elasticsearch
        verify(mockInvitoEventoSearchRepository, times(0)).save(invitoEvento);
    }


    @Test
    @Transactional
    public void getAllInvitoEventos() throws Exception {
        // Initialize the database
        invitoEventoRepository.saveAndFlush(invitoEvento);

        // Get all the invitoEventoList
        restInvitoEventoMockMvc.perform(get("/api/invito-eventos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invitoEvento.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTaskRef").value(hasItem(DEFAULT_ID_TASK_REF)))
            .andExpect(jsonPath("$.[*].luogoFisico").value(hasItem(DEFAULT_LUOGO_FISICO)))
            .andExpect(jsonPath("$.[*].indicazioniLuogo").value(hasItem(DEFAULT_INDICAZIONI_LUOGO)))
            .andExpect(jsonPath("$.[*].dataInizio").value(hasItem(DEFAULT_DATA_INIZIO)))
            .andExpect(jsonPath("$.[*].oraInizio").value(hasItem(DEFAULT_ORA_INIZIO)))
            .andExpect(jsonPath("$.[*].dataFine").value(hasItem(DEFAULT_DATA_FINE)))
            .andExpect(jsonPath("$.[*].oraFine").value(hasItem(DEFAULT_ORA_FINE)))
            .andExpect(jsonPath("$.[*].urlStanzaVirtuale").value(hasItem(DEFAULT_URL_STANZA_VIRTUALE)));
    }
    
    @Test
    @Transactional
    public void getInvitoEvento() throws Exception {
        // Initialize the database
        invitoEventoRepository.saveAndFlush(invitoEvento);

        // Get the invitoEvento
        restInvitoEventoMockMvc.perform(get("/api/invito-eventos/{id}", invitoEvento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(invitoEvento.getId().intValue()))
            .andExpect(jsonPath("$.idTaskRef").value(DEFAULT_ID_TASK_REF))
            .andExpect(jsonPath("$.luogoFisico").value(DEFAULT_LUOGO_FISICO))
            .andExpect(jsonPath("$.indicazioniLuogo").value(DEFAULT_INDICAZIONI_LUOGO))
            .andExpect(jsonPath("$.dataInizio").value(DEFAULT_DATA_INIZIO))
            .andExpect(jsonPath("$.oraInizio").value(DEFAULT_ORA_INIZIO))
            .andExpect(jsonPath("$.dataFine").value(DEFAULT_DATA_FINE))
            .andExpect(jsonPath("$.oraFine").value(DEFAULT_ORA_FINE))
            .andExpect(jsonPath("$.urlStanzaVirtuale").value(DEFAULT_URL_STANZA_VIRTUALE));
    }
    @Test
    @Transactional
    public void getNonExistingInvitoEvento() throws Exception {
        // Get the invitoEvento
        restInvitoEventoMockMvc.perform(get("/api/invito-eventos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvitoEvento() throws Exception {
        // Initialize the database
        invitoEventoRepository.saveAndFlush(invitoEvento);

        int databaseSizeBeforeUpdate = invitoEventoRepository.findAll().size();

        // Update the invitoEvento
        InvitoEvento updatedInvitoEvento = invitoEventoRepository.findById(invitoEvento.getId()).get();
        // Disconnect from session so that the updates on updatedInvitoEvento are not directly saved in db
        em.detach(updatedInvitoEvento);
        updatedInvitoEvento
            .idTaskRef(UPDATED_ID_TASK_REF)
            .luogoFisico(UPDATED_LUOGO_FISICO)
            .indicazioniLuogo(UPDATED_INDICAZIONI_LUOGO)
            .dataInizio(UPDATED_DATA_INIZIO)
            .oraInizio(UPDATED_ORA_INIZIO)
            .dataFine(UPDATED_DATA_FINE)
            .oraFine(UPDATED_ORA_FINE)
            .urlStanzaVirtuale(UPDATED_URL_STANZA_VIRTUALE);
        InvitoEventoDTO invitoEventoDTO = invitoEventoMapper.toDto(updatedInvitoEvento);

        restInvitoEventoMockMvc.perform(put("/api/invito-eventos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitoEventoDTO)))
            .andExpect(status().isOk());

        // Validate the InvitoEvento in the database
        List<InvitoEvento> invitoEventoList = invitoEventoRepository.findAll();
        assertThat(invitoEventoList).hasSize(databaseSizeBeforeUpdate);
        InvitoEvento testInvitoEvento = invitoEventoList.get(invitoEventoList.size() - 1);
        assertThat(testInvitoEvento.getIdTaskRef()).isEqualTo(UPDATED_ID_TASK_REF);
        assertThat(testInvitoEvento.getLuogoFisico()).isEqualTo(UPDATED_LUOGO_FISICO);
        assertThat(testInvitoEvento.getIndicazioniLuogo()).isEqualTo(UPDATED_INDICAZIONI_LUOGO);
        assertThat(testInvitoEvento.getDataInizio()).isEqualTo(UPDATED_DATA_INIZIO);
        assertThat(testInvitoEvento.getOraInizio()).isEqualTo(UPDATED_ORA_INIZIO);
        assertThat(testInvitoEvento.getDataFine()).isEqualTo(UPDATED_DATA_FINE);
        assertThat(testInvitoEvento.getOraFine()).isEqualTo(UPDATED_ORA_FINE);
        assertThat(testInvitoEvento.getUrlStanzaVirtuale()).isEqualTo(UPDATED_URL_STANZA_VIRTUALE);

        // Validate the InvitoEvento in Elasticsearch
        verify(mockInvitoEventoSearchRepository, times(1)).save(testInvitoEvento);
    }

    @Test
    @Transactional
    public void updateNonExistingInvitoEvento() throws Exception {
        int databaseSizeBeforeUpdate = invitoEventoRepository.findAll().size();

        // Create the InvitoEvento
        InvitoEventoDTO invitoEventoDTO = invitoEventoMapper.toDto(invitoEvento);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvitoEventoMockMvc.perform(put("/api/invito-eventos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(invitoEventoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvitoEvento in the database
        List<InvitoEvento> invitoEventoList = invitoEventoRepository.findAll();
        assertThat(invitoEventoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the InvitoEvento in Elasticsearch
        verify(mockInvitoEventoSearchRepository, times(0)).save(invitoEvento);
    }

    @Test
    @Transactional
    public void deleteInvitoEvento() throws Exception {
        // Initialize the database
        invitoEventoRepository.saveAndFlush(invitoEvento);

        int databaseSizeBeforeDelete = invitoEventoRepository.findAll().size();

        // Delete the invitoEvento
        restInvitoEventoMockMvc.perform(delete("/api/invito-eventos/{id}", invitoEvento.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvitoEvento> invitoEventoList = invitoEventoRepository.findAll();
        assertThat(invitoEventoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the InvitoEvento in Elasticsearch
        verify(mockInvitoEventoSearchRepository, times(1)).deleteById(invitoEvento.getId());
    }

    @Test
    @Transactional
    public void searchInvitoEvento() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        invitoEventoRepository.saveAndFlush(invitoEvento);
        when(mockInvitoEventoSearchRepository.search(queryStringQuery("id:" + invitoEvento.getId())))
            .thenReturn(Collections.singletonList(invitoEvento));

        // Search the invitoEvento
        restInvitoEventoMockMvc.perform(get("/api/_search/invito-eventos?query=id:" + invitoEvento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invitoEvento.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTaskRef").value(hasItem(DEFAULT_ID_TASK_REF)))
            .andExpect(jsonPath("$.[*].luogoFisico").value(hasItem(DEFAULT_LUOGO_FISICO)))
            .andExpect(jsonPath("$.[*].indicazioniLuogo").value(hasItem(DEFAULT_INDICAZIONI_LUOGO)))
            .andExpect(jsonPath("$.[*].dataInizio").value(hasItem(DEFAULT_DATA_INIZIO)))
            .andExpect(jsonPath("$.[*].oraInizio").value(hasItem(DEFAULT_ORA_INIZIO)))
            .andExpect(jsonPath("$.[*].dataFine").value(hasItem(DEFAULT_DATA_FINE)))
            .andExpect(jsonPath("$.[*].oraFine").value(hasItem(DEFAULT_ORA_FINE)))
            .andExpect(jsonPath("$.[*].urlStanzaVirtuale").value(hasItem(DEFAULT_URL_STANZA_VIRTUALE)));
    }
}
