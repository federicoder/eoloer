package it.maggioli.service;

import it.maggioli.domain.NotaTask;
import it.maggioli.repository.NotaTaskRepository;
import it.maggioli.repository.search.NotaTaskSearchRepository;
import it.maggioli.service.dto.NotaTaskDTO;
import it.maggioli.service.mapper.NotaTaskMapper;
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
 * Service Implementation for managing {@link NotaTask}.
 */
@Service
@Transactional
public class NotaTaskService {

    private final Logger log = LoggerFactory.getLogger(NotaTaskService.class);

    private final NotaTaskRepository notaTaskRepository;

    private final NotaTaskMapper notaTaskMapper;

    private final NotaTaskSearchRepository notaTaskSearchRepository;

    public NotaTaskService(NotaTaskRepository notaTaskRepository, NotaTaskMapper notaTaskMapper, NotaTaskSearchRepository notaTaskSearchRepository) {
        this.notaTaskRepository = notaTaskRepository;
        this.notaTaskMapper = notaTaskMapper;
        this.notaTaskSearchRepository = notaTaskSearchRepository;
    }

    /**
     * Save a notaTask.
     *
     * @param notaTaskDTO the entity to save.
     * @return the persisted entity.
     */
    public NotaTaskDTO save(NotaTaskDTO notaTaskDTO) {
        log.debug("Request to save NotaTask : {}", notaTaskDTO);
        NotaTask notaTask = notaTaskMapper.toEntity(notaTaskDTO);
        notaTask = notaTaskRepository.save(notaTask);
        NotaTaskDTO result = notaTaskMapper.toDto(notaTask);
        notaTaskSearchRepository.save(notaTask);
        return result;
    }

    /**
     * Get all the notaTasks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NotaTaskDTO> findAll() {
        log.debug("Request to get all NotaTasks");
        return notaTaskRepository.findAll().stream()
            .map(notaTaskMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one notaTask by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NotaTaskDTO> findOne(Long id) {
        log.debug("Request to get NotaTask : {}", id);
        return notaTaskRepository.findById(id)
            .map(notaTaskMapper::toDto);
    }

    /**
     * Delete the notaTask by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NotaTask : {}", id);
        notaTaskRepository.deleteById(id);
        notaTaskSearchRepository.deleteById(id);
    }

    /**
     * Search for the notaTask corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NotaTaskDTO> search(String query) {
        log.debug("Request to search NotaTasks for query {}", query);
        return StreamSupport
            .stream(notaTaskSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(notaTaskMapper::toDto)
        .collect(Collectors.toList());
    }
}
