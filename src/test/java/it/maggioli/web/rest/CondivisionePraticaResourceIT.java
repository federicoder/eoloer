package it.maggioli.web.rest;

import it.maggioli.EoloprjApp;
import it.maggioli.domain.CondivisionePratica;
import it.maggioli.repository.CondivisionePraticaRepository;
import it.maggioli.repository.search.CondivisionePraticaSearchRepository;
import it.maggioli.service.CondivisionePraticaService;
import it.maggioli.service.dto.CondivisionePraticaDTO;
import it.maggioli.service.mapper.CondivisionePraticaMapper;

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
 * Integration tests for the {@link CondivisionePraticaResource} REST controller.
 */
@SpringBootTest(classes = EoloprjApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class CondivisionePraticaResourceIT {

    private static final Integer DEFAULT_ID_USER_AMMESSO = 8;
    private static final Integer UPDATED_ID_USER_AMMESSO = 7;

    private static final Integer DEFAULT_RUOLO = 1;
    private static final Integer UPDATED_RUOLO = 2;

    private static final Integer DEFAULT_ID_USER_CONCEDENTE = 1;
    private static final Integer UPDATED_ID_USER_CONCEDENTE = 2;

    private static final Integer DEFAULT_STATO_INVITO = 1;
    private static final Integer UPDATED_STATO_INVITO = 2;

    private static final Integer DEFAULT_ID_PRATICA = 1;
    private static final Integer UPDATED_ID_PRATICA = 2;

    @Autowired
    private CondivisionePraticaRepository condivisionePraticaRepository;

    @Autowired
    private CondivisionePraticaMapper condivisionePraticaMapper;

    @Autowired
    private CondivisionePraticaService condivisionePraticaService;

    /**
     * This repository is mocked in the it.maggioli.repository.search test package.
     *
     * @see it.maggioli.repository.search.CondivisionePraticaSearchRepositoryMockConfiguration
     */
    @Autowired
    private CondivisionePraticaSearchRepository mockCondivisionePraticaSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCondivisionePraticaMockMvc;

    private CondivisionePratica condivisionePratica;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CondivisionePratica createEntity(EntityManager em) {
        CondivisionePratica condivisionePratica = new CondivisionePratica()
            .idUserAmmesso(DEFAULT_ID_USER_AMMESSO)
            .ruolo(DEFAULT_RUOLO)
            .idUserConcedente(DEFAULT_ID_USER_CONCEDENTE)
            .statoInvito(DEFAULT_STATO_INVITO)
            .idPratica(DEFAULT_ID_PRATICA);
        return condivisionePratica;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CondivisionePratica createUpdatedEntity(EntityManager em) {
        CondivisionePratica condivisionePratica = new CondivisionePratica()
            .idUserAmmesso(UPDATED_ID_USER_AMMESSO)
            .ruolo(UPDATED_RUOLO)
            .idUserConcedente(UPDATED_ID_USER_CONCEDENTE)
            .statoInvito(UPDATED_STATO_INVITO)
            .idPratica(UPDATED_ID_PRATICA);
        return condivisionePratica;
    }

    @BeforeEach
    public void initTest() {
        condivisionePratica = createEntity(em);
    }

    @Test
    @Transactional
    public void createCondivisionePratica() throws Exception {
        int databaseSizeBeforeCreate = condivisionePraticaRepository.findAll().size();
        // Create the CondivisionePratica
        CondivisionePraticaDTO condivisionePraticaDTO = condivisionePraticaMapper.toDto(condivisionePratica);
        restCondivisionePraticaMockMvc.perform(post("/api/condivisione-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(condivisionePraticaDTO)))
            .andExpect(status().isCreated());

        // Validate the CondivisionePratica in the database
        List<CondivisionePratica> condivisionePraticaList = condivisionePraticaRepository.findAll();
        assertThat(condivisionePraticaList).hasSize(databaseSizeBeforeCreate + 1);
        CondivisionePratica testCondivisionePratica = condivisionePraticaList.get(condivisionePraticaList.size() - 1);
        assertThat(testCondivisionePratica.getIdUserAmmesso()).isEqualTo(DEFAULT_ID_USER_AMMESSO);
        assertThat(testCondivisionePratica.getRuolo()).isEqualTo(DEFAULT_RUOLO);
        assertThat(testCondivisionePratica.getIdUserConcedente()).isEqualTo(DEFAULT_ID_USER_CONCEDENTE);
        assertThat(testCondivisionePratica.getStatoInvito()).isEqualTo(DEFAULT_STATO_INVITO);
        assertThat(testCondivisionePratica.getIdPratica()).isEqualTo(DEFAULT_ID_PRATICA);

        // Validate the CondivisionePratica in Elasticsearch
        verify(mockCondivisionePraticaSearchRepository, times(1)).save(testCondivisionePratica);
    }

    @Test
    @Transactional
    public void createCondivisionePraticaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = condivisionePraticaRepository.findAll().size();

        // Create the CondivisionePratica with an existing ID
        condivisionePratica.setId(1L);
        CondivisionePraticaDTO condivisionePraticaDTO = condivisionePraticaMapper.toDto(condivisionePratica);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCondivisionePraticaMockMvc.perform(post("/api/condivisione-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(condivisionePraticaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CondivisionePratica in the database
        List<CondivisionePratica> condivisionePraticaList = condivisionePraticaRepository.findAll();
        assertThat(condivisionePraticaList).hasSize(databaseSizeBeforeCreate);

        // Validate the CondivisionePratica in Elasticsearch
        verify(mockCondivisionePraticaSearchRepository, times(0)).save(condivisionePratica);
    }


    @Test
    @Transactional
    public void getAllCondivisionePraticas() throws Exception {
        // Initialize the database
        condivisionePraticaRepository.saveAndFlush(condivisionePratica);

        // Get all the condivisionePraticaList
        restCondivisionePraticaMockMvc.perform(get("/api/condivisione-praticas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(condivisionePratica.getId().intValue())))
            .andExpect(jsonPath("$.[*].idUserAmmesso").value(hasItem(DEFAULT_ID_USER_AMMESSO)))
            .andExpect(jsonPath("$.[*].ruolo").value(hasItem(DEFAULT_RUOLO)))
            .andExpect(jsonPath("$.[*].idUserConcedente").value(hasItem(DEFAULT_ID_USER_CONCEDENTE)))
            .andExpect(jsonPath("$.[*].statoInvito").value(hasItem(DEFAULT_STATO_INVITO)))
            .andExpect(jsonPath("$.[*].idPratica").value(hasItem(DEFAULT_ID_PRATICA)));
    }
    
    @Test
    @Transactional
    public void getCondivisionePratica() throws Exception {
        // Initialize the database
        condivisionePraticaRepository.saveAndFlush(condivisionePratica);

        // Get the condivisionePratica
        restCondivisionePraticaMockMvc.perform(get("/api/condivisione-praticas/{id}", condivisionePratica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(condivisionePratica.getId().intValue()))
            .andExpect(jsonPath("$.idUserAmmesso").value(DEFAULT_ID_USER_AMMESSO))
            .andExpect(jsonPath("$.ruolo").value(DEFAULT_RUOLO))
            .andExpect(jsonPath("$.idUserConcedente").value(DEFAULT_ID_USER_CONCEDENTE))
            .andExpect(jsonPath("$.statoInvito").value(DEFAULT_STATO_INVITO))
            .andExpect(jsonPath("$.idPratica").value(DEFAULT_ID_PRATICA));
    }
    @Test
    @Transactional
    public void getNonExistingCondivisionePratica() throws Exception {
        // Get the condivisionePratica
        restCondivisionePraticaMockMvc.perform(get("/api/condivisione-praticas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCondivisionePratica() throws Exception {
        // Initialize the database
        condivisionePraticaRepository.saveAndFlush(condivisionePratica);

        int databaseSizeBeforeUpdate = condivisionePraticaRepository.findAll().size();

        // Update the condivisionePratica
        CondivisionePratica updatedCondivisionePratica = condivisionePraticaRepository.findById(condivisionePratica.getId()).get();
        // Disconnect from session so that the updates on updatedCondivisionePratica are not directly saved in db
        em.detach(updatedCondivisionePratica);
        updatedCondivisionePratica
            .idUserAmmesso(UPDATED_ID_USER_AMMESSO)
            .ruolo(UPDATED_RUOLO)
            .idUserConcedente(UPDATED_ID_USER_CONCEDENTE)
            .statoInvito(UPDATED_STATO_INVITO)
            .idPratica(UPDATED_ID_PRATICA);
        CondivisionePraticaDTO condivisionePraticaDTO = condivisionePraticaMapper.toDto(updatedCondivisionePratica);

        restCondivisionePraticaMockMvc.perform(put("/api/condivisione-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(condivisionePraticaDTO)))
            .andExpect(status().isOk());

        // Validate the CondivisionePratica in the database
        List<CondivisionePratica> condivisionePraticaList = condivisionePraticaRepository.findAll();
        assertThat(condivisionePraticaList).hasSize(databaseSizeBeforeUpdate);
        CondivisionePratica testCondivisionePratica = condivisionePraticaList.get(condivisionePraticaList.size() - 1);
        assertThat(testCondivisionePratica.getIdUserAmmesso()).isEqualTo(UPDATED_ID_USER_AMMESSO);
        assertThat(testCondivisionePratica.getRuolo()).isEqualTo(UPDATED_RUOLO);
        assertThat(testCondivisionePratica.getIdUserConcedente()).isEqualTo(UPDATED_ID_USER_CONCEDENTE);
        assertThat(testCondivisionePratica.getStatoInvito()).isEqualTo(UPDATED_STATO_INVITO);
        assertThat(testCondivisionePratica.getIdPratica()).isEqualTo(UPDATED_ID_PRATICA);

        // Validate the CondivisionePratica in Elasticsearch
        verify(mockCondivisionePraticaSearchRepository, times(1)).save(testCondivisionePratica);
    }

    @Test
    @Transactional
    public void updateNonExistingCondivisionePratica() throws Exception {
        int databaseSizeBeforeUpdate = condivisionePraticaRepository.findAll().size();

        // Create the CondivisionePratica
        CondivisionePraticaDTO condivisionePraticaDTO = condivisionePraticaMapper.toDto(condivisionePratica);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCondivisionePraticaMockMvc.perform(put("/api/condivisione-praticas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(condivisionePraticaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CondivisionePratica in the database
        List<CondivisionePratica> condivisionePraticaList = condivisionePraticaRepository.findAll();
        assertThat(condivisionePraticaList).hasSize(databaseSizeBeforeUpdate);

        // Validate the CondivisionePratica in Elasticsearch
        verify(mockCondivisionePraticaSearchRepository, times(0)).save(condivisionePratica);
    }

    @Test
    @Transactional
    public void deleteCondivisionePratica() throws Exception {
        // Initialize the database
        condivisionePraticaRepository.saveAndFlush(condivisionePratica);

        int databaseSizeBeforeDelete = condivisionePraticaRepository.findAll().size();

        // Delete the condivisionePratica
        restCondivisionePraticaMockMvc.perform(delete("/api/condivisione-praticas/{id}", condivisionePratica.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CondivisionePratica> condivisionePraticaList = condivisionePraticaRepository.findAll();
        assertThat(condivisionePraticaList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the CondivisionePratica in Elasticsearch
        verify(mockCondivisionePraticaSearchRepository, times(1)).deleteById(condivisionePratica.getId());
    }

    @Test
    @Transactional
    public void searchCondivisionePratica() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        condivisionePraticaRepository.saveAndFlush(condivisionePratica);
        when(mockCondivisionePraticaSearchRepository.search(queryStringQuery("id:" + condivisionePratica.getId())))
            .thenReturn(Collections.singletonList(condivisionePratica));

        // Search the condivisionePratica
        restCondivisionePraticaMockMvc.perform(get("/api/_search/condivisione-praticas?query=id:" + condivisionePratica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(condivisionePratica.getId().intValue())))
            .andExpect(jsonPath("$.[*].idUserAmmesso").value(hasItem(DEFAULT_ID_USER_AMMESSO)))
            .andExpect(jsonPath("$.[*].ruolo").value(hasItem(DEFAULT_RUOLO)))
            .andExpect(jsonPath("$.[*].idUserConcedente").value(hasItem(DEFAULT_ID_USER_CONCEDENTE)))
            .andExpect(jsonPath("$.[*].statoInvito").value(hasItem(DEFAULT_STATO_INVITO)))
            .andExpect(jsonPath("$.[*].idPratica").value(hasItem(DEFAULT_ID_PRATICA)));
    }
}
