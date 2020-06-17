package it.maggioli.service;

import it.maggioli.domain.RisorseDisponibili;
import it.maggioli.repository.RisorseDisponibiliRepository;
import it.maggioli.repository.search.RisorseDisponibiliSearchRepository;
import it.maggioli.service.dto.RisorseDisponibiliDTO;
import it.maggioli.service.mapper.RisorseDisponibiliMapper;
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
 * Service Implementation for managing {@link RisorseDisponibili}.
 */
@Service
@Transactional
public class RisorseDisponibiliService {

    private final Logger log = LoggerFactory.getLogger(RisorseDisponibiliService.class);

    private final RisorseDisponibiliRepository risorseDisponibiliRepository;

    private final RisorseDisponibiliMapper risorseDisponibiliMapper;

    private final RisorseDisponibiliSearchRepository risorseDisponibiliSearchRepository;

    public RisorseDisponibiliService(RisorseDisponibiliRepository risorseDisponibiliRepository, RisorseDisponibiliMapper risorseDisponibiliMapper, RisorseDisponibiliSearchRepository risorseDisponibiliSearchRepository) {
        this.risorseDisponibiliRepository = risorseDisponibiliRepository;
        this.risorseDisponibiliMapper = risorseDisponibiliMapper;
        this.risorseDisponibiliSearchRepository = risorseDisponibiliSearchRepository;
    }

    /**
     * Save a risorseDisponibili.
     *
     * @param risorseDisponibiliDTO the entity to save.
     * @return the persisted entity.
     */
    public RisorseDisponibiliDTO save(RisorseDisponibiliDTO risorseDisponibiliDTO) {
        log.debug("Request to save RisorseDisponibili : {}", risorseDisponibiliDTO);
        RisorseDisponibili risorseDisponibili = risorseDisponibiliMapper.toEntity(risorseDisponibiliDTO);
        risorseDisponibili = risorseDisponibiliRepository.save(risorseDisponibili);
        RisorseDisponibiliDTO result = risorseDisponibiliMapper.toDto(risorseDisponibili);
        risorseDisponibiliSearchRepository.save(risorseDisponibili);
        return result;
    }

    /**
     * Get all the risorseDisponibilis.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RisorseDisponibiliDTO> findAll() {
        log.debug("Request to get all RisorseDisponibilis");
        return risorseDisponibiliRepository.findAll().stream()
            .map(risorseDisponibiliMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one risorseDisponibili by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RisorseDisponibiliDTO> findOne(Long id) {
        log.debug("Request to get RisorseDisponibili : {}", id);
        return risorseDisponibiliRepository.findById(id)
            .map(risorseDisponibiliMapper::toDto);
    }

    /**
     * Delete the risorseDisponibili by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RisorseDisponibili : {}", id);
        risorseDisponibiliRepository.deleteById(id);
        risorseDisponibiliSearchRepository.deleteById(id);
    }

    /**
     * Search for the risorseDisponibili corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RisorseDisponibiliDTO> search(String query) {
        log.debug("Request to search RisorseDisponibilis for query {}", query);
        return StreamSupport
            .stream(risorseDisponibiliSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(risorseDisponibiliMapper::toDto)
        .collect(Collectors.toList());
    }
}
