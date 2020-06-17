package it.maggioli.service;

import it.maggioli.domain.DatiContabili;
import it.maggioli.repository.DatiContabiliRepository;
import it.maggioli.repository.search.DatiContabiliSearchRepository;
import it.maggioli.service.dto.DatiContabiliDTO;
import it.maggioli.service.mapper.DatiContabiliMapper;
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
 * Service Implementation for managing {@link DatiContabili}.
 */
@Service
@Transactional
public class DatiContabiliService {

    private final Logger log = LoggerFactory.getLogger(DatiContabiliService.class);

    private final DatiContabiliRepository datiContabiliRepository;

    private final DatiContabiliMapper datiContabiliMapper;

    private final DatiContabiliSearchRepository datiContabiliSearchRepository;

    public DatiContabiliService(DatiContabiliRepository datiContabiliRepository, DatiContabiliMapper datiContabiliMapper, DatiContabiliSearchRepository datiContabiliSearchRepository) {
        this.datiContabiliRepository = datiContabiliRepository;
        this.datiContabiliMapper = datiContabiliMapper;
        this.datiContabiliSearchRepository = datiContabiliSearchRepository;
    }

    /**
     * Save a datiContabili.
     *
     * @param datiContabiliDTO the entity to save.
     * @return the persisted entity.
     */
    public DatiContabiliDTO save(DatiContabiliDTO datiContabiliDTO) {
        log.debug("Request to save DatiContabili : {}", datiContabiliDTO);
        DatiContabili datiContabili = datiContabiliMapper.toEntity(datiContabiliDTO);
        datiContabili = datiContabiliRepository.save(datiContabili);
        DatiContabiliDTO result = datiContabiliMapper.toDto(datiContabili);
        datiContabiliSearchRepository.save(datiContabili);
        return result;
    }

    /**
     * Get all the datiContabilis.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DatiContabiliDTO> findAll() {
        log.debug("Request to get all DatiContabilis");
        return datiContabiliRepository.findAll().stream()
            .map(datiContabiliMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one datiContabili by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DatiContabiliDTO> findOne(Long id) {
        log.debug("Request to get DatiContabili : {}", id);
        return datiContabiliRepository.findById(id)
            .map(datiContabiliMapper::toDto);
    }

    /**
     * Delete the datiContabili by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DatiContabili : {}", id);
        datiContabiliRepository.deleteById(id);
        datiContabiliSearchRepository.deleteById(id);
    }

    /**
     * Search for the datiContabili corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DatiContabiliDTO> search(String query) {
        log.debug("Request to search DatiContabilis for query {}", query);
        return StreamSupport
            .stream(datiContabiliSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(datiContabiliMapper::toDto)
        .collect(Collectors.toList());
    }
}
