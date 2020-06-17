package it.maggioli.service;

import it.maggioli.domain.AllegatoTask;
import it.maggioli.repository.AllegatoTaskRepository;
import it.maggioli.repository.search.AllegatoTaskSearchRepository;
import it.maggioli.service.dto.AllegatoTaskDTO;
import it.maggioli.service.mapper.AllegatoTaskMapper;
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
 * Service Implementation for managing {@link AllegatoTask}.
 */
@Service
@Transactional
public class AllegatoTaskService {

    private final Logger log = LoggerFactory.getLogger(AllegatoTaskService.class);

    private final AllegatoTaskRepository allegatoTaskRepository;

    private final AllegatoTaskMapper allegatoTaskMapper;

    private final AllegatoTaskSearchRepository allegatoTaskSearchRepository;

    public AllegatoTaskService(AllegatoTaskRepository allegatoTaskRepository, AllegatoTaskMapper allegatoTaskMapper, AllegatoTaskSearchRepository allegatoTaskSearchRepository) {
        this.allegatoTaskRepository = allegatoTaskRepository;
        this.allegatoTaskMapper = allegatoTaskMapper;
        this.allegatoTaskSearchRepository = allegatoTaskSearchRepository;
    }

    /**
     * Save a allegatoTask.
     *
     * @param allegatoTaskDTO the entity to save.
     * @return the persisted entity.
     */
    public AllegatoTaskDTO save(AllegatoTaskDTO allegatoTaskDTO) {
        log.debug("Request to save AllegatoTask : {}", allegatoTaskDTO);
        AllegatoTask allegatoTask = allegatoTaskMapper.toEntity(allegatoTaskDTO);
        allegatoTask = allegatoTaskRepository.save(allegatoTask);
        AllegatoTaskDTO result = allegatoTaskMapper.toDto(allegatoTask);
        allegatoTaskSearchRepository.save(allegatoTask);
        return result;
    }

    /**
     * Get all the allegatoTasks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AllegatoTaskDTO> findAll() {
        log.debug("Request to get all AllegatoTasks");
        return allegatoTaskRepository.findAll().stream()
            .map(allegatoTaskMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one allegatoTask by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AllegatoTaskDTO> findOne(Long id) {
        log.debug("Request to get AllegatoTask : {}", id);
        return allegatoTaskRepository.findById(id)
            .map(allegatoTaskMapper::toDto);
    }

    /**
     * Delete the allegatoTask by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AllegatoTask : {}", id);
        allegatoTaskRepository.deleteById(id);
        allegatoTaskSearchRepository.deleteById(id);
    }

    /**
     * Search for the allegatoTask corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AllegatoTaskDTO> search(String query) {
        log.debug("Request to search AllegatoTasks for query {}", query);
        return StreamSupport
            .stream(allegatoTaskSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(allegatoTaskMapper::toDto)
        .collect(Collectors.toList());
    }
}
