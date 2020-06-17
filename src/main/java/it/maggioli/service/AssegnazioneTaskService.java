package it.maggioli.service;

import it.maggioli.domain.AssegnazioneTask;
import it.maggioli.repository.AssegnazioneTaskRepository;
import it.maggioli.repository.search.AssegnazioneTaskSearchRepository;
import it.maggioli.service.dto.AssegnazioneTaskDTO;
import it.maggioli.service.mapper.AssegnazioneTaskMapper;
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
 * Service Implementation for managing {@link AssegnazioneTask}.
 */
@Service
@Transactional
public class AssegnazioneTaskService {

    private final Logger log = LoggerFactory.getLogger(AssegnazioneTaskService.class);

    private final AssegnazioneTaskRepository assegnazioneTaskRepository;

    private final AssegnazioneTaskMapper assegnazioneTaskMapper;

    private final AssegnazioneTaskSearchRepository assegnazioneTaskSearchRepository;

    public AssegnazioneTaskService(AssegnazioneTaskRepository assegnazioneTaskRepository, AssegnazioneTaskMapper assegnazioneTaskMapper, AssegnazioneTaskSearchRepository assegnazioneTaskSearchRepository) {
        this.assegnazioneTaskRepository = assegnazioneTaskRepository;
        this.assegnazioneTaskMapper = assegnazioneTaskMapper;
        this.assegnazioneTaskSearchRepository = assegnazioneTaskSearchRepository;
    }

    /**
     * Save a assegnazioneTask.
     *
     * @param assegnazioneTaskDTO the entity to save.
     * @return the persisted entity.
     */
    public AssegnazioneTaskDTO save(AssegnazioneTaskDTO assegnazioneTaskDTO) {
        log.debug("Request to save AssegnazioneTask : {}", assegnazioneTaskDTO);
        AssegnazioneTask assegnazioneTask = assegnazioneTaskMapper.toEntity(assegnazioneTaskDTO);
        assegnazioneTask = assegnazioneTaskRepository.save(assegnazioneTask);
        AssegnazioneTaskDTO result = assegnazioneTaskMapper.toDto(assegnazioneTask);
        assegnazioneTaskSearchRepository.save(assegnazioneTask);
        return result;
    }

    /**
     * Get all the assegnazioneTasks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AssegnazioneTaskDTO> findAll() {
        log.debug("Request to get all AssegnazioneTasks");
        return assegnazioneTaskRepository.findAll().stream()
            .map(assegnazioneTaskMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the assegnazioneTasks where IdAttivita is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<AssegnazioneTaskDTO> findAllWhereIdAttivitaIsNull() {
        log.debug("Request to get all assegnazioneTasks where IdAttivita is null");
        return StreamSupport
            .stream(assegnazioneTaskRepository.findAll().spliterator(), false)
            .filter(assegnazioneTask -> assegnazioneTask.getIdAttivita() == null)
            .map(assegnazioneTaskMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one assegnazioneTask by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AssegnazioneTaskDTO> findOne(Long id) {
        log.debug("Request to get AssegnazioneTask : {}", id);
        return assegnazioneTaskRepository.findById(id)
            .map(assegnazioneTaskMapper::toDto);
    }

    /**
     * Delete the assegnazioneTask by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AssegnazioneTask : {}", id);
        assegnazioneTaskRepository.deleteById(id);
        assegnazioneTaskSearchRepository.deleteById(id);
    }

    /**
     * Search for the assegnazioneTask corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AssegnazioneTaskDTO> search(String query) {
        log.debug("Request to search AssegnazioneTasks for query {}", query);
        return StreamSupport
            .stream(assegnazioneTaskSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(assegnazioneTaskMapper::toDto)
        .collect(Collectors.toList());
    }
}
