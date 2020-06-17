package it.maggioli.service;

import it.maggioli.domain.CondivisionePratica;
import it.maggioli.repository.CondivisionePraticaRepository;
import it.maggioli.repository.search.CondivisionePraticaSearchRepository;
import it.maggioli.service.dto.CondivisionePraticaDTO;
import it.maggioli.service.mapper.CondivisionePraticaMapper;
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
 * Service Implementation for managing {@link CondivisionePratica}.
 */
@Service
@Transactional
public class CondivisionePraticaService {

    private final Logger log = LoggerFactory.getLogger(CondivisionePraticaService.class);

    private final CondivisionePraticaRepository condivisionePraticaRepository;

    private final CondivisionePraticaMapper condivisionePraticaMapper;

    private final CondivisionePraticaSearchRepository condivisionePraticaSearchRepository;

    public CondivisionePraticaService(CondivisionePraticaRepository condivisionePraticaRepository, CondivisionePraticaMapper condivisionePraticaMapper, CondivisionePraticaSearchRepository condivisionePraticaSearchRepository) {
        this.condivisionePraticaRepository = condivisionePraticaRepository;
        this.condivisionePraticaMapper = condivisionePraticaMapper;
        this.condivisionePraticaSearchRepository = condivisionePraticaSearchRepository;
    }

    /**
     * Save a condivisionePratica.
     *
     * @param condivisionePraticaDTO the entity to save.
     * @return the persisted entity.
     */
    public CondivisionePraticaDTO save(CondivisionePraticaDTO condivisionePraticaDTO) {
        log.debug("Request to save CondivisionePratica : {}", condivisionePraticaDTO);
        CondivisionePratica condivisionePratica = condivisionePraticaMapper.toEntity(condivisionePraticaDTO);
        condivisionePratica = condivisionePraticaRepository.save(condivisionePratica);
        CondivisionePraticaDTO result = condivisionePraticaMapper.toDto(condivisionePratica);
        condivisionePraticaSearchRepository.save(condivisionePratica);
        return result;
    }

    /**
     * Get all the condivisionePraticas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CondivisionePraticaDTO> findAll() {
        log.debug("Request to get all CondivisionePraticas");
        return condivisionePraticaRepository.findAll().stream()
            .map(condivisionePraticaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one condivisionePratica by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CondivisionePraticaDTO> findOne(Long id) {
        log.debug("Request to get CondivisionePratica : {}", id);
        return condivisionePraticaRepository.findById(id)
            .map(condivisionePraticaMapper::toDto);
    }

    /**
     * Delete the condivisionePratica by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CondivisionePratica : {}", id);
        condivisionePraticaRepository.deleteById(id);
        condivisionePraticaSearchRepository.deleteById(id);
    }

    /**
     * Search for the condivisionePratica corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CondivisionePraticaDTO> search(String query) {
        log.debug("Request to search CondivisionePraticas for query {}", query);
        return StreamSupport
            .stream(condivisionePraticaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(condivisionePraticaMapper::toDto)
        .collect(Collectors.toList());
    }
}
