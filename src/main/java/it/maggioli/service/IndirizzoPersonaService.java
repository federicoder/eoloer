package it.maggioli.service;

import it.maggioli.domain.IndirizzoPersona;
import it.maggioli.repository.IndirizzoPersonaRepository;
import it.maggioli.repository.search.IndirizzoPersonaSearchRepository;
import it.maggioli.service.dto.IndirizzoPersonaDTO;
import it.maggioli.service.mapper.IndirizzoPersonaMapper;
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
 * Service Implementation for managing {@link IndirizzoPersona}.
 */
@Service
@Transactional
public class IndirizzoPersonaService {

    private final Logger log = LoggerFactory.getLogger(IndirizzoPersonaService.class);

    private final IndirizzoPersonaRepository indirizzoPersonaRepository;

    private final IndirizzoPersonaMapper indirizzoPersonaMapper;

    private final IndirizzoPersonaSearchRepository indirizzoPersonaSearchRepository;

    public IndirizzoPersonaService(IndirizzoPersonaRepository indirizzoPersonaRepository, IndirizzoPersonaMapper indirizzoPersonaMapper, IndirizzoPersonaSearchRepository indirizzoPersonaSearchRepository) {
        this.indirizzoPersonaRepository = indirizzoPersonaRepository;
        this.indirizzoPersonaMapper = indirizzoPersonaMapper;
        this.indirizzoPersonaSearchRepository = indirizzoPersonaSearchRepository;
    }

    /**
     * Save a indirizzoPersona.
     *
     * @param indirizzoPersonaDTO the entity to save.
     * @return the persisted entity.
     */
    public IndirizzoPersonaDTO save(IndirizzoPersonaDTO indirizzoPersonaDTO) {
        log.debug("Request to save IndirizzoPersona : {}", indirizzoPersonaDTO);
        IndirizzoPersona indirizzoPersona = indirizzoPersonaMapper.toEntity(indirizzoPersonaDTO);
        indirizzoPersona = indirizzoPersonaRepository.save(indirizzoPersona);
        IndirizzoPersonaDTO result = indirizzoPersonaMapper.toDto(indirizzoPersona);
        indirizzoPersonaSearchRepository.save(indirizzoPersona);
        return result;
    }

    /**
     * Get all the indirizzoPersonas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<IndirizzoPersonaDTO> findAll() {
        log.debug("Request to get all IndirizzoPersonas");
        return indirizzoPersonaRepository.findAll().stream()
            .map(indirizzoPersonaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the indirizzoPersonas where IdPersonaRef is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<IndirizzoPersonaDTO> findAllWhereIdPersonaRefIsNull() {
        log.debug("Request to get all indirizzoPersonas where IdPersonaRef is null");
        return StreamSupport
            .stream(indirizzoPersonaRepository.findAll().spliterator(), false)
            .filter(indirizzoPersona -> indirizzoPersona.getIdPersonaRef() == null)
            .map(indirizzoPersonaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one indirizzoPersona by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<IndirizzoPersonaDTO> findOne(Long id) {
        log.debug("Request to get IndirizzoPersona : {}", id);
        return indirizzoPersonaRepository.findById(id)
            .map(indirizzoPersonaMapper::toDto);
    }

    /**
     * Delete the indirizzoPersona by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete IndirizzoPersona : {}", id);
        indirizzoPersonaRepository.deleteById(id);
        indirizzoPersonaSearchRepository.deleteById(id);
    }

    /**
     * Search for the indirizzoPersona corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<IndirizzoPersonaDTO> search(String query) {
        log.debug("Request to search IndirizzoPersonas for query {}", query);
        return StreamSupport
            .stream(indirizzoPersonaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(indirizzoPersonaMapper::toDto)
        .collect(Collectors.toList());
    }
}
