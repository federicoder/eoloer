package it.maggioli.service;

import it.maggioli.domain.PrevisioneEvento;
import it.maggioli.repository.PrevisioneEventoRepository;
import it.maggioli.repository.search.PrevisioneEventoSearchRepository;
import it.maggioli.service.dto.PrevisioneEventoDTO;
import it.maggioli.service.mapper.PrevisioneEventoMapper;
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
 * Service Implementation for managing {@link PrevisioneEvento}.
 */
@Service
@Transactional
public class PrevisioneEventoService {

    private final Logger log = LoggerFactory.getLogger(PrevisioneEventoService.class);

    private final PrevisioneEventoRepository previsioneEventoRepository;

    private final PrevisioneEventoMapper previsioneEventoMapper;

    private final PrevisioneEventoSearchRepository previsioneEventoSearchRepository;

    public PrevisioneEventoService(PrevisioneEventoRepository previsioneEventoRepository, PrevisioneEventoMapper previsioneEventoMapper, PrevisioneEventoSearchRepository previsioneEventoSearchRepository) {
        this.previsioneEventoRepository = previsioneEventoRepository;
        this.previsioneEventoMapper = previsioneEventoMapper;
        this.previsioneEventoSearchRepository = previsioneEventoSearchRepository;
    }

    /**
     * Save a previsioneEvento.
     *
     * @param previsioneEventoDTO the entity to save.
     * @return the persisted entity.
     */
    public PrevisioneEventoDTO save(PrevisioneEventoDTO previsioneEventoDTO) {
        log.debug("Request to save PrevisioneEvento : {}", previsioneEventoDTO);
        PrevisioneEvento previsioneEvento = previsioneEventoMapper.toEntity(previsioneEventoDTO);
        previsioneEvento = previsioneEventoRepository.save(previsioneEvento);
        PrevisioneEventoDTO result = previsioneEventoMapper.toDto(previsioneEvento);
        previsioneEventoSearchRepository.save(previsioneEvento);
        return result;
    }

    /**
     * Get all the previsioneEventos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PrevisioneEventoDTO> findAll() {
        log.debug("Request to get all PrevisioneEventos");
        return previsioneEventoRepository.findAll().stream()
            .map(previsioneEventoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one previsioneEvento by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PrevisioneEventoDTO> findOne(Long id) {
        log.debug("Request to get PrevisioneEvento : {}", id);
        return previsioneEventoRepository.findById(id)
            .map(previsioneEventoMapper::toDto);
    }

    /**
     * Delete the previsioneEvento by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PrevisioneEvento : {}", id);
        previsioneEventoRepository.deleteById(id);
        previsioneEventoSearchRepository.deleteById(id);
    }

    /**
     * Search for the previsioneEvento corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PrevisioneEventoDTO> search(String query) {
        log.debug("Request to search PrevisioneEventos for query {}", query);
        return StreamSupport
            .stream(previsioneEventoSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(previsioneEventoMapper::toDto)
        .collect(Collectors.toList());
    }
}
