package it.maggioli.service;

import it.maggioli.domain.RappresentanzaPratica;
import it.maggioli.repository.RappresentanzaPraticaRepository;
import it.maggioli.repository.search.RappresentanzaPraticaSearchRepository;
import it.maggioli.service.dto.RappresentanzaPraticaDTO;
import it.maggioli.service.mapper.RappresentanzaPraticaMapper;
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
 * Service Implementation for managing {@link RappresentanzaPratica}.
 */
@Service
@Transactional
public class RappresentanzaPraticaService {

    private final Logger log = LoggerFactory.getLogger(RappresentanzaPraticaService.class);

    private final RappresentanzaPraticaRepository rappresentanzaPraticaRepository;

    private final RappresentanzaPraticaMapper rappresentanzaPraticaMapper;

    private final RappresentanzaPraticaSearchRepository rappresentanzaPraticaSearchRepository;

    public RappresentanzaPraticaService(RappresentanzaPraticaRepository rappresentanzaPraticaRepository, RappresentanzaPraticaMapper rappresentanzaPraticaMapper, RappresentanzaPraticaSearchRepository rappresentanzaPraticaSearchRepository) {
        this.rappresentanzaPraticaRepository = rappresentanzaPraticaRepository;
        this.rappresentanzaPraticaMapper = rappresentanzaPraticaMapper;
        this.rappresentanzaPraticaSearchRepository = rappresentanzaPraticaSearchRepository;
    }

    /**
     * Save a rappresentanzaPratica.
     *
     * @param rappresentanzaPraticaDTO the entity to save.
     * @return the persisted entity.
     */
    public RappresentanzaPraticaDTO save(RappresentanzaPraticaDTO rappresentanzaPraticaDTO) {
        log.debug("Request to save RappresentanzaPratica : {}", rappresentanzaPraticaDTO);
        RappresentanzaPratica rappresentanzaPratica = rappresentanzaPraticaMapper.toEntity(rappresentanzaPraticaDTO);
        rappresentanzaPratica = rappresentanzaPraticaRepository.save(rappresentanzaPratica);
        RappresentanzaPraticaDTO result = rappresentanzaPraticaMapper.toDto(rappresentanzaPratica);
        rappresentanzaPraticaSearchRepository.save(rappresentanzaPratica);
        return result;
    }

    /**
     * Get all the rappresentanzaPraticas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RappresentanzaPraticaDTO> findAll() {
        log.debug("Request to get all RappresentanzaPraticas");
        return rappresentanzaPraticaRepository.findAll().stream()
            .map(rappresentanzaPraticaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the rappresentanzaPraticas where IdRuoloPersona is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<RappresentanzaPraticaDTO> findAllWhereIdRuoloPersonaIsNull() {
        log.debug("Request to get all rappresentanzaPraticas where IdRuoloPersona is null");
        return StreamSupport
            .stream(rappresentanzaPraticaRepository.findAll().spliterator(), false)
            .filter(rappresentanzaPratica -> rappresentanzaPratica.getIdRuoloPersona() == null)
            .map(rappresentanzaPraticaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the rappresentanzaPraticas where IdRuoloPersona is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<RappresentanzaPraticaDTO> findAllWhereIdRuoloPersonaIsNull() {
        log.debug("Request to get all rappresentanzaPraticas where IdRuoloPersona is null");
        return StreamSupport
            .stream(rappresentanzaPraticaRepository.findAll().spliterator(), false)
            .filter(rappresentanzaPratica -> rappresentanzaPratica.getIdRuoloPersona() == null)
            .map(rappresentanzaPraticaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one rappresentanzaPratica by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RappresentanzaPraticaDTO> findOne(Long id) {
        log.debug("Request to get RappresentanzaPratica : {}", id);
        return rappresentanzaPraticaRepository.findById(id)
            .map(rappresentanzaPraticaMapper::toDto);
    }

    /**
     * Delete the rappresentanzaPratica by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RappresentanzaPratica : {}", id);
        rappresentanzaPraticaRepository.deleteById(id);
        rappresentanzaPraticaSearchRepository.deleteById(id);
    }

    /**
     * Search for the rappresentanzaPratica corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RappresentanzaPraticaDTO> search(String query) {
        log.debug("Request to search RappresentanzaPraticas for query {}", query);
        return StreamSupport
            .stream(rappresentanzaPraticaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(rappresentanzaPraticaMapper::toDto)
        .collect(Collectors.toList());
    }
}
