package it.maggioli.service;

import it.maggioli.domain.AllegatoTemplateTask;
import it.maggioli.repository.AllegatoTemplateTaskRepository;
import it.maggioli.repository.search.AllegatoTemplateTaskSearchRepository;
import it.maggioli.service.dto.AllegatoTemplateTaskDTO;
import it.maggioli.service.mapper.AllegatoTemplateTaskMapper;
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
 * Service Implementation for managing {@link AllegatoTemplateTask}.
 */
@Service
@Transactional
public class AllegatoTemplateTaskService {

    private final Logger log = LoggerFactory.getLogger(AllegatoTemplateTaskService.class);

    private final AllegatoTemplateTaskRepository allegatoTemplateTaskRepository;

    private final AllegatoTemplateTaskMapper allegatoTemplateTaskMapper;

    private final AllegatoTemplateTaskSearchRepository allegatoTemplateTaskSearchRepository;

    public AllegatoTemplateTaskService(AllegatoTemplateTaskRepository allegatoTemplateTaskRepository, AllegatoTemplateTaskMapper allegatoTemplateTaskMapper, AllegatoTemplateTaskSearchRepository allegatoTemplateTaskSearchRepository) {
        this.allegatoTemplateTaskRepository = allegatoTemplateTaskRepository;
        this.allegatoTemplateTaskMapper = allegatoTemplateTaskMapper;
        this.allegatoTemplateTaskSearchRepository = allegatoTemplateTaskSearchRepository;
    }

    /**
     * Save a allegatoTemplateTask.
     *
     * @param allegatoTemplateTaskDTO the entity to save.
     * @return the persisted entity.
     */
    public AllegatoTemplateTaskDTO save(AllegatoTemplateTaskDTO allegatoTemplateTaskDTO) {
        log.debug("Request to save AllegatoTemplateTask : {}", allegatoTemplateTaskDTO);
        AllegatoTemplateTask allegatoTemplateTask = allegatoTemplateTaskMapper.toEntity(allegatoTemplateTaskDTO);
        allegatoTemplateTask = allegatoTemplateTaskRepository.save(allegatoTemplateTask);
        AllegatoTemplateTaskDTO result = allegatoTemplateTaskMapper.toDto(allegatoTemplateTask);
        allegatoTemplateTaskSearchRepository.save(allegatoTemplateTask);
        return result;
    }

    /**
     * Get all the allegatoTemplateTasks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AllegatoTemplateTaskDTO> findAll() {
        log.debug("Request to get all AllegatoTemplateTasks");
        return allegatoTemplateTaskRepository.findAll().stream()
            .map(allegatoTemplateTaskMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one allegatoTemplateTask by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AllegatoTemplateTaskDTO> findOne(Long id) {
        log.debug("Request to get AllegatoTemplateTask : {}", id);
        return allegatoTemplateTaskRepository.findById(id)
            .map(allegatoTemplateTaskMapper::toDto);
    }

    /**
     * Delete the allegatoTemplateTask by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AllegatoTemplateTask : {}", id);
        allegatoTemplateTaskRepository.deleteById(id);
        allegatoTemplateTaskSearchRepository.deleteById(id);
    }

    /**
     * Search for the allegatoTemplateTask corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AllegatoTemplateTaskDTO> search(String query) {
        log.debug("Request to search AllegatoTemplateTasks for query {}", query);
        return StreamSupport
            .stream(allegatoTemplateTaskSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(allegatoTemplateTaskMapper::toDto)
        .collect(Collectors.toList());
    }
}
