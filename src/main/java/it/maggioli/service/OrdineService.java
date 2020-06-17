package it.maggioli.service;

import it.maggioli.domain.Ordine;
import it.maggioli.repository.OrdineRepository;
import it.maggioli.repository.search.OrdineSearchRepository;
import it.maggioli.service.dto.OrdineDTO;
import it.maggioli.service.mapper.OrdineMapper;
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
 * Service Implementation for managing {@link Ordine}.
 */
@Service
@Transactional
public class OrdineService {

    private final Logger log = LoggerFactory.getLogger(OrdineService.class);

    private final OrdineRepository ordineRepository;

    private final OrdineMapper ordineMapper;

    private final OrdineSearchRepository ordineSearchRepository;

    public OrdineService(OrdineRepository ordineRepository, OrdineMapper ordineMapper, OrdineSearchRepository ordineSearchRepository) {
        this.ordineRepository = ordineRepository;
        this.ordineMapper = ordineMapper;
        this.ordineSearchRepository = ordineSearchRepository;
    }

    /**
     * Save a ordine.
     *
     * @param ordineDTO the entity to save.
     * @return the persisted entity.
     */
    public OrdineDTO save(OrdineDTO ordineDTO) {
        log.debug("Request to save Ordine : {}", ordineDTO);
        Ordine ordine = ordineMapper.toEntity(ordineDTO);
        ordine = ordineRepository.save(ordine);
        OrdineDTO result = ordineMapper.toDto(ordine);
        ordineSearchRepository.save(ordine);
        return result;
    }

    /**
     * Get all the ordines.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OrdineDTO> findAll() {
        log.debug("Request to get all Ordines");
        return ordineRepository.findAll().stream()
            .map(ordineMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one ordine by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrdineDTO> findOne(Long id) {
        log.debug("Request to get Ordine : {}", id);
        return ordineRepository.findById(id)
            .map(ordineMapper::toDto);
    }

    /**
     * Delete the ordine by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Ordine : {}", id);
        ordineRepository.deleteById(id);
        ordineSearchRepository.deleteById(id);
    }

    /**
     * Search for the ordine corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OrdineDTO> search(String query) {
        log.debug("Request to search Ordines for query {}", query);
        return StreamSupport
            .stream(ordineSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(ordineMapper::toDto)
        .collect(Collectors.toList());
    }
}
