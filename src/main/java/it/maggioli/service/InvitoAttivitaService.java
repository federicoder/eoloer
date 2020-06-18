package it.maggioli.service;

import it.maggioli.domain.InvitoAttivita;
import it.maggioli.repository.InvitoAttivitaRepository;
import it.maggioli.repository.search.InvitoAttivitaSearchRepository;
import it.maggioli.service.dto.InvitoAttivitaDTO;
import it.maggioli.service.mapper.InvitoAttivitaMapper;
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
 * Service Implementation for managing {@link InvitoAttivita}.
 */
@Service
@Transactional
public class InvitoAttivitaService {

    private final Logger log = LoggerFactory.getLogger(InvitoAttivitaService.class);

    private final InvitoAttivitaRepository invitoAttivitaRepository;

    private final InvitoAttivitaMapper invitoAttivitaMapper;

    private final InvitoAttivitaSearchRepository invitoAttivitaSearchRepository;

    public InvitoAttivitaService(InvitoAttivitaRepository invitoAttivitaRepository, InvitoAttivitaMapper invitoAttivitaMapper, InvitoAttivitaSearchRepository invitoAttivitaSearchRepository) {
        this.invitoAttivitaRepository = invitoAttivitaRepository;
        this.invitoAttivitaMapper = invitoAttivitaMapper;
        this.invitoAttivitaSearchRepository = invitoAttivitaSearchRepository;
    }

    /**
     * Save a invitoAttivita.
     *
     * @param invitoAttivitaDTO the entity to save.
     * @return the persisted entity.
     */
    public InvitoAttivitaDTO save(InvitoAttivitaDTO invitoAttivitaDTO) {
        log.debug("Request to save InvitoAttivita : {}", invitoAttivitaDTO);
        InvitoAttivita invitoAttivita = invitoAttivitaMapper.toEntity(invitoAttivitaDTO);
        invitoAttivita = invitoAttivitaRepository.save(invitoAttivita);
        InvitoAttivitaDTO result = invitoAttivitaMapper.toDto(invitoAttivita);
        invitoAttivitaSearchRepository.save(invitoAttivita);
        return result;
    }

    /**
     * Get all the invitoAttivitas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InvitoAttivitaDTO> findAll() {
        log.debug("Request to get all InvitoAttivitas");
        return invitoAttivitaRepository.findAll().stream()
            .map(invitoAttivitaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one invitoAttivita by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InvitoAttivitaDTO> findOne(Long id) {
        log.debug("Request to get InvitoAttivita : {}", id);
        return invitoAttivitaRepository.findById(id)
            .map(invitoAttivitaMapper::toDto);
    }

    /**
     * Delete the invitoAttivita by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InvitoAttivita : {}", id);
        invitoAttivitaRepository.deleteById(id);
        invitoAttivitaSearchRepository.deleteById(id);
    }

    /**
     * Search for the invitoAttivita corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InvitoAttivitaDTO> search(String query) {
        log.debug("Request to search InvitoAttivitas for query {}", query);
        return StreamSupport
            .stream(invitoAttivitaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(invitoAttivitaMapper::toDto)
        .collect(Collectors.toList());
    }
}
