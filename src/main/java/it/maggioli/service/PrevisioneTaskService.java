package it.maggioli.service;

import it.maggioli.domain.PrevisioneTask;
import it.maggioli.repository.PrevisioneTaskRepository;
import it.maggioli.repository.search.PrevisioneTaskSearchRepository;
import it.maggioli.service.dto.PrevisioneTaskDTO;
import it.maggioli.service.mapper.PrevisioneTaskMapper;
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
 * Service Implementation for managing {@link PrevisioneTask}.
 */
@Service
@Transactional
public class PrevisioneTaskService {

    private final Logger log = LoggerFactory.getLogger(PrevisioneTaskService.class);

    private final PrevisioneTaskRepository previsioneTaskRepository;

    private final PrevisioneTaskMapper previsioneTaskMapper;

    private final PrevisioneTaskSearchRepository previsioneTaskSearchRepository;

    public PrevisioneTaskService(PrevisioneTaskRepository previsioneTaskRepository, PrevisioneTaskMapper previsioneTaskMapper, PrevisioneTaskSearchRepository previsioneTaskSearchRepository) {
        this.previsioneTaskRepository = previsioneTaskRepository;
        this.previsioneTaskMapper = previsioneTaskMapper;
        this.previsioneTaskSearchRepository = previsioneTaskSearchRepository;
    }

    /**
     * Save a previsioneTask.
     *
     * @param previsioneTaskDTO the entity to save.
     * @return the persisted entity.
     */
    public PrevisioneTaskDTO save(PrevisioneTaskDTO previsioneTaskDTO) {
        log.debug("Request to save PrevisioneTask : {}", previsioneTaskDTO);
        PrevisioneTask previsioneTask = previsioneTaskMapper.toEntity(previsioneTaskDTO);
        previsioneTask = previsioneTaskRepository.save(previsioneTask);
        PrevisioneTaskDTO result = previsioneTaskMapper.toDto(previsioneTask);
        previsioneTaskSearchRepository.save(previsioneTask);
        return result;
    }

    /**
     * Get all the previsioneTasks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PrevisioneTaskDTO> findAll() {
        log.debug("Request to get all PrevisioneTasks");
        return previsioneTaskRepository.findAll().stream()
            .map(previsioneTaskMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the previsioneTasks where IdTaskRef is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<PrevisioneTaskDTO> findAllWhereIdTaskRefIsNull() {
        log.debug("Request to get all previsioneTasks where IdTaskRef is null");
        return StreamSupport
            .stream(previsioneTaskRepository.findAll().spliterator(), false)
            .filter(previsioneTask -> previsioneTask.getIdTaskRef() == null)
            .map(previsioneTaskMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the previsioneTasks where IdTaskRef is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<PrevisioneTaskDTO> findAllWhereIdTaskRefIsNull() {
        log.debug("Request to get all previsioneTasks where IdTaskRef is null");
        return StreamSupport
            .stream(previsioneTaskRepository.findAll().spliterator(), false)
            .filter(previsioneTask -> previsioneTask.getIdTaskRef() == null)
            .map(previsioneTaskMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the previsioneTasks where IdTaskRef is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<PrevisioneTaskDTO> findAllWhereIdTaskRefIsNull() {
        log.debug("Request to get all previsioneTasks where IdTaskRef is null");
        return StreamSupport
            .stream(previsioneTaskRepository.findAll().spliterator(), false)
            .filter(previsioneTask -> previsioneTask.getIdTaskRef() == null)
            .map(previsioneTaskMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one previsioneTask by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PrevisioneTaskDTO> findOne(Long id) {
        log.debug("Request to get PrevisioneTask : {}", id);
        return previsioneTaskRepository.findById(id)
            .map(previsioneTaskMapper::toDto);
    }

    /**
     * Delete the previsioneTask by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PrevisioneTask : {}", id);
        previsioneTaskRepository.deleteById(id);
        previsioneTaskSearchRepository.deleteById(id);
    }

    /**
     * Search for the previsioneTask corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PrevisioneTaskDTO> search(String query) {
        log.debug("Request to search PrevisioneTasks for query {}", query);
        return StreamSupport
            .stream(previsioneTaskSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(previsioneTaskMapper::toDto)
        .collect(Collectors.toList());
    }
}
