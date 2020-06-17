package it.maggioli.service;

import it.maggioli.domain.PrevisioneAttivita;
import it.maggioli.repository.PrevisioneAttivitaRepository;
import it.maggioli.repository.search.PrevisioneAttivitaSearchRepository;
import it.maggioli.service.dto.PrevisioneAttivitaDTO;
import it.maggioli.service.mapper.PrevisioneAttivitaMapper;
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
 * Service Implementation for managing {@link PrevisioneAttivita}.
 */
@Service
@Transactional
public class PrevisioneAttivitaService {

    private final Logger log = LoggerFactory.getLogger(PrevisioneAttivitaService.class);

    private final PrevisioneAttivitaRepository previsioneAttivitaRepository;

    private final PrevisioneAttivitaMapper previsioneAttivitaMapper;

    private final PrevisioneAttivitaSearchRepository previsioneAttivitaSearchRepository;

    public PrevisioneAttivitaService(PrevisioneAttivitaRepository previsioneAttivitaRepository, PrevisioneAttivitaMapper previsioneAttivitaMapper, PrevisioneAttivitaSearchRepository previsioneAttivitaSearchRepository) {
        this.previsioneAttivitaRepository = previsioneAttivitaRepository;
        this.previsioneAttivitaMapper = previsioneAttivitaMapper;
        this.previsioneAttivitaSearchRepository = previsioneAttivitaSearchRepository;
    }

    /**
     * Save a previsioneAttivita.
     *
     * @param previsioneAttivitaDTO the entity to save.
     * @return the persisted entity.
     */
    public PrevisioneAttivitaDTO save(PrevisioneAttivitaDTO previsioneAttivitaDTO) {
        log.debug("Request to save PrevisioneAttivita : {}", previsioneAttivitaDTO);
        PrevisioneAttivita previsioneAttivita = previsioneAttivitaMapper.toEntity(previsioneAttivitaDTO);
        previsioneAttivita = previsioneAttivitaRepository.save(previsioneAttivita);
        PrevisioneAttivitaDTO result = previsioneAttivitaMapper.toDto(previsioneAttivita);
        previsioneAttivitaSearchRepository.save(previsioneAttivita);
        return result;
    }

    /**
     * Get all the previsioneAttivitas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PrevisioneAttivitaDTO> findAll() {
        log.debug("Request to get all PrevisioneAttivitas");
        return previsioneAttivitaRepository.findAll().stream()
            .map(previsioneAttivitaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one previsioneAttivita by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PrevisioneAttivitaDTO> findOne(Long id) {
        log.debug("Request to get PrevisioneAttivita : {}", id);
        return previsioneAttivitaRepository.findById(id)
            .map(previsioneAttivitaMapper::toDto);
    }

    /**
     * Delete the previsioneAttivita by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PrevisioneAttivita : {}", id);
        previsioneAttivitaRepository.deleteById(id);
        previsioneAttivitaSearchRepository.deleteById(id);
    }

    /**
     * Search for the previsioneAttivita corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PrevisioneAttivitaDTO> search(String query) {
        log.debug("Request to search PrevisioneAttivitas for query {}", query);
        return StreamSupport
            .stream(previsioneAttivitaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(previsioneAttivitaMapper::toDto)
        .collect(Collectors.toList());
    }
}
