package it.maggioli.service;

import it.maggioli.domain.RuoloOrganizzazione;
import it.maggioli.repository.RuoloOrganizzazioneRepository;
import it.maggioli.repository.search.RuoloOrganizzazioneSearchRepository;
import it.maggioli.service.dto.RuoloOrganizzazioneDTO;
import it.maggioli.service.mapper.RuoloOrganizzazioneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link RuoloOrganizzazione}.
 */
@Service
@Transactional
public class RuoloOrganizzazioneService {

    private final Logger log = LoggerFactory.getLogger(RuoloOrganizzazioneService.class);

    private final RuoloOrganizzazioneRepository ruoloOrganizzazioneRepository;

    private final RuoloOrganizzazioneMapper ruoloOrganizzazioneMapper;

    private final RuoloOrganizzazioneSearchRepository ruoloOrganizzazioneSearchRepository;

    public RuoloOrganizzazioneService(RuoloOrganizzazioneRepository ruoloOrganizzazioneRepository, RuoloOrganizzazioneMapper ruoloOrganizzazioneMapper, RuoloOrganizzazioneSearchRepository ruoloOrganizzazioneSearchRepository) {
        this.ruoloOrganizzazioneRepository = ruoloOrganizzazioneRepository;
        this.ruoloOrganizzazioneMapper = ruoloOrganizzazioneMapper;
        this.ruoloOrganizzazioneSearchRepository = ruoloOrganizzazioneSearchRepository;
    }

    /**
     * Save a ruoloOrganizzazione.
     *
     * @param ruoloOrganizzazioneDTO the entity to save.
     * @return the persisted entity.
     */
    public RuoloOrganizzazioneDTO save(RuoloOrganizzazioneDTO ruoloOrganizzazioneDTO) {
        log.debug("Request to save RuoloOrganizzazione : {}", ruoloOrganizzazioneDTO);
        RuoloOrganizzazione ruoloOrganizzazione = ruoloOrganizzazioneMapper.toEntity(ruoloOrganizzazioneDTO);
        ruoloOrganizzazione = ruoloOrganizzazioneRepository.save(ruoloOrganizzazione);
        RuoloOrganizzazioneDTO result = ruoloOrganizzazioneMapper.toDto(ruoloOrganizzazione);
        ruoloOrganizzazioneSearchRepository.save(ruoloOrganizzazione);
        return result;
    }

    /**
     * Get all the ruoloOrganizzaziones.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RuoloOrganizzazioneDTO> findAll() {
        log.debug("Request to get all RuoloOrganizzaziones");
        return ruoloOrganizzazioneRepository.findAll().stream()
            .map(ruoloOrganizzazioneMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one ruoloOrganizzazione by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RuoloOrganizzazioneDTO> findOne(Long id) {
        log.debug("Request to get RuoloOrganizzazione : {}", id);
        return ruoloOrganizzazioneRepository.findById(id)
            .map(ruoloOrganizzazioneMapper::toDto);
    }

    /**
     * Delete the ruoloOrganizzazione by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RuoloOrganizzazione : {}", id);
        ruoloOrganizzazioneRepository.deleteById(id);
        ruoloOrganizzazioneSearchRepository.deleteById(id);
    }

    /**
     * Search for the ruoloOrganizzazione corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RuoloOrganizzazioneDTO> search(String query) {
        log.debug("Request to search RuoloOrganizzaziones for query {}", query);
        return StreamSupport
            .stream(ruoloOrganizzazioneSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(ruoloOrganizzazioneMapper::toDto)
        .collect(Collectors.toList());
    }
}
