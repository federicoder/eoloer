package it.maggioli.service;

import it.maggioli.domain.ConsuntivoTask;
import it.maggioli.repository.ConsuntivoTaskRepository;
import it.maggioli.repository.search.ConsuntivoTaskSearchRepository;
import it.maggioli.service.dto.ConsuntivoTaskDTO;
import it.maggioli.service.mapper.ConsuntivoTaskMapper;
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
 * Service Implementation for managing {@link ConsuntivoTask}.
 */
@Service
@Transactional
public class ConsuntivoTaskService {

    private final Logger log = LoggerFactory.getLogger(ConsuntivoTaskService.class);

    private final ConsuntivoTaskRepository consuntivoTaskRepository;

    private final ConsuntivoTaskMapper consuntivoTaskMapper;

    private final ConsuntivoTaskSearchRepository consuntivoTaskSearchRepository;

    public ConsuntivoTaskService(ConsuntivoTaskRepository consuntivoTaskRepository, ConsuntivoTaskMapper consuntivoTaskMapper, ConsuntivoTaskSearchRepository consuntivoTaskSearchRepository) {
        this.consuntivoTaskRepository = consuntivoTaskRepository;
        this.consuntivoTaskMapper = consuntivoTaskMapper;
        this.consuntivoTaskSearchRepository = consuntivoTaskSearchRepository;
    }

    /**
     * Save a consuntivoTask.
     *
     * @param consuntivoTaskDTO the entity to save.
     * @return the persisted entity.
     */
    public ConsuntivoTaskDTO save(ConsuntivoTaskDTO consuntivoTaskDTO) {
        log.debug("Request to save ConsuntivoTask : {}", consuntivoTaskDTO);
        ConsuntivoTask consuntivoTask = consuntivoTaskMapper.toEntity(consuntivoTaskDTO);
        consuntivoTask = consuntivoTaskRepository.save(consuntivoTask);
        ConsuntivoTaskDTO result = consuntivoTaskMapper.toDto(consuntivoTask);
        consuntivoTaskSearchRepository.save(consuntivoTask);
        return result;
    }

    /**
     * Get all the consuntivoTasks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ConsuntivoTaskDTO> findAll() {
        log.debug("Request to get all ConsuntivoTasks");
        return consuntivoTaskRepository.findAll().stream()
            .map(consuntivoTaskMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the consuntivoTasks where IdTask is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<ConsuntivoTaskDTO> findAllWhereIdTaskIsNull() {
        log.debug("Request to get all consuntivoTasks where IdTask is null");
        return StreamSupport
            .stream(consuntivoTaskRepository.findAll().spliterator(), false)
            .filter(consuntivoTask -> consuntivoTask.getIdTask() == null)
            .map(consuntivoTaskMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one consuntivoTask by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ConsuntivoTaskDTO> findOne(Long id) {
        log.debug("Request to get ConsuntivoTask : {}", id);
        return consuntivoTaskRepository.findById(id)
            .map(consuntivoTaskMapper::toDto);
    }

    /**
     * Delete the consuntivoTask by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ConsuntivoTask : {}", id);
        consuntivoTaskRepository.deleteById(id);
        consuntivoTaskSearchRepository.deleteById(id);
    }

    /**
     * Search for the consuntivoTask corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ConsuntivoTaskDTO> search(String query) {
        log.debug("Request to search ConsuntivoTasks for query {}", query);
        return StreamSupport
            .stream(consuntivoTaskSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(consuntivoTaskMapper::toDto)
        .collect(Collectors.toList());
    }
}
