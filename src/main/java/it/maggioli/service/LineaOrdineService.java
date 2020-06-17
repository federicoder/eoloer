package it.maggioli.service;

import it.maggioli.domain.LineaOrdine;
import it.maggioli.repository.LineaOrdineRepository;
import it.maggioli.repository.search.LineaOrdineSearchRepository;
import it.maggioli.service.dto.LineaOrdineDTO;
import it.maggioli.service.mapper.LineaOrdineMapper;
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
 * Service Implementation for managing {@link LineaOrdine}.
 */
@Service
@Transactional
public class LineaOrdineService {

    private final Logger log = LoggerFactory.getLogger(LineaOrdineService.class);

    private final LineaOrdineRepository lineaOrdineRepository;

    private final LineaOrdineMapper lineaOrdineMapper;

    private final LineaOrdineSearchRepository lineaOrdineSearchRepository;

    public LineaOrdineService(LineaOrdineRepository lineaOrdineRepository, LineaOrdineMapper lineaOrdineMapper, LineaOrdineSearchRepository lineaOrdineSearchRepository) {
        this.lineaOrdineRepository = lineaOrdineRepository;
        this.lineaOrdineMapper = lineaOrdineMapper;
        this.lineaOrdineSearchRepository = lineaOrdineSearchRepository;
    }

    /**
     * Save a lineaOrdine.
     *
     * @param lineaOrdineDTO the entity to save.
     * @return the persisted entity.
     */
    public LineaOrdineDTO save(LineaOrdineDTO lineaOrdineDTO) {
        log.debug("Request to save LineaOrdine : {}", lineaOrdineDTO);
        LineaOrdine lineaOrdine = lineaOrdineMapper.toEntity(lineaOrdineDTO);
        lineaOrdine = lineaOrdineRepository.save(lineaOrdine);
        LineaOrdineDTO result = lineaOrdineMapper.toDto(lineaOrdine);
        lineaOrdineSearchRepository.save(lineaOrdine);
        return result;
    }

    /**
     * Get all the lineaOrdines.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<LineaOrdineDTO> findAll() {
        log.debug("Request to get all LineaOrdines");
        return lineaOrdineRepository.findAll().stream()
            .map(lineaOrdineMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the lineaOrdines where IdProdottoRef is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<LineaOrdineDTO> findAllWhereIdProdottoRefIsNull() {
        log.debug("Request to get all lineaOrdines where IdProdottoRef is null");
        return StreamSupport
            .stream(lineaOrdineRepository.findAll().spliterator(), false)
            .filter(lineaOrdine -> lineaOrdine.getIdProdottoRef() == null)
            .map(lineaOrdineMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one lineaOrdine by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LineaOrdineDTO> findOne(Long id) {
        log.debug("Request to get LineaOrdine : {}", id);
        return lineaOrdineRepository.findById(id)
            .map(lineaOrdineMapper::toDto);
    }

    /**
     * Delete the lineaOrdine by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LineaOrdine : {}", id);
        lineaOrdineRepository.deleteById(id);
        lineaOrdineSearchRepository.deleteById(id);
    }

    /**
     * Search for the lineaOrdine corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<LineaOrdineDTO> search(String query) {
        log.debug("Request to search LineaOrdines for query {}", query);
        return StreamSupport
            .stream(lineaOrdineSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(lineaOrdineMapper::toDto)
        .collect(Collectors.toList());
    }
}
