package it.maggioli.service;

import it.maggioli.domain.TelefonoPersona;
import it.maggioli.repository.TelefonoPersonaRepository;
import it.maggioli.repository.search.TelefonoPersonaSearchRepository;
import it.maggioli.service.dto.TelefonoPersonaDTO;
import it.maggioli.service.mapper.TelefonoPersonaMapper;
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
 * Service Implementation for managing {@link TelefonoPersona}.
 */
@Service
@Transactional
public class TelefonoPersonaService {

    private final Logger log = LoggerFactory.getLogger(TelefonoPersonaService.class);

    private final TelefonoPersonaRepository telefonoPersonaRepository;

    private final TelefonoPersonaMapper telefonoPersonaMapper;

    private final TelefonoPersonaSearchRepository telefonoPersonaSearchRepository;

    public TelefonoPersonaService(TelefonoPersonaRepository telefonoPersonaRepository, TelefonoPersonaMapper telefonoPersonaMapper, TelefonoPersonaSearchRepository telefonoPersonaSearchRepository) {
        this.telefonoPersonaRepository = telefonoPersonaRepository;
        this.telefonoPersonaMapper = telefonoPersonaMapper;
        this.telefonoPersonaSearchRepository = telefonoPersonaSearchRepository;
    }

    /**
     * Save a telefonoPersona.
     *
     * @param telefonoPersonaDTO the entity to save.
     * @return the persisted entity.
     */
    public TelefonoPersonaDTO save(TelefonoPersonaDTO telefonoPersonaDTO) {
        log.debug("Request to save TelefonoPersona : {}", telefonoPersonaDTO);
        TelefonoPersona telefonoPersona = telefonoPersonaMapper.toEntity(telefonoPersonaDTO);
        telefonoPersona = telefonoPersonaRepository.save(telefonoPersona);
        TelefonoPersonaDTO result = telefonoPersonaMapper.toDto(telefonoPersona);
        telefonoPersonaSearchRepository.save(telefonoPersona);
        return result;
    }

    /**
     * Get all the telefonoPersonas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TelefonoPersonaDTO> findAll() {
        log.debug("Request to get all TelefonoPersonas");
        return telefonoPersonaRepository.findAll().stream()
            .map(telefonoPersonaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one telefonoPersona by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TelefonoPersonaDTO> findOne(Long id) {
        log.debug("Request to get TelefonoPersona : {}", id);
        return telefonoPersonaRepository.findById(id)
            .map(telefonoPersonaMapper::toDto);
    }

    /**
     * Delete the telefonoPersona by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TelefonoPersona : {}", id);
        telefonoPersonaRepository.deleteById(id);
        telefonoPersonaSearchRepository.deleteById(id);
    }

    /**
     * Search for the telefonoPersona corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TelefonoPersonaDTO> search(String query) {
        log.debug("Request to search TelefonoPersonas for query {}", query);
        return StreamSupport
            .stream(telefonoPersonaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(telefonoPersonaMapper::toDto)
        .collect(Collectors.toList());
    }
}
