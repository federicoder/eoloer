package it.maggioli.service;

import it.maggioli.domain.TemplateTask;
import it.maggioli.repository.TemplateTaskRepository;
import it.maggioli.repository.search.TemplateTaskSearchRepository;
import it.maggioli.service.dto.TemplateTaskDTO;
import it.maggioli.service.mapper.TemplateTaskMapper;
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
 * Service Implementation for managing {@link TemplateTask}.
 */
@Service
@Transactional
public class TemplateTaskService {

    private final Logger log = LoggerFactory.getLogger(TemplateTaskService.class);

    private final TemplateTaskRepository templateTaskRepository;

    private final TemplateTaskMapper templateTaskMapper;

    private final TemplateTaskSearchRepository templateTaskSearchRepository;

    public TemplateTaskService(TemplateTaskRepository templateTaskRepository, TemplateTaskMapper templateTaskMapper, TemplateTaskSearchRepository templateTaskSearchRepository) {
        this.templateTaskRepository = templateTaskRepository;
        this.templateTaskMapper = templateTaskMapper;
        this.templateTaskSearchRepository = templateTaskSearchRepository;
    }

    /**
     * Save a templateTask.
     *
     * @param templateTaskDTO the entity to save.
     * @return the persisted entity.
     */
    public TemplateTaskDTO save(TemplateTaskDTO templateTaskDTO) {
        log.debug("Request to save TemplateTask : {}", templateTaskDTO);
        TemplateTask templateTask = templateTaskMapper.toEntity(templateTaskDTO);
        templateTask = templateTaskRepository.save(templateTask);
        TemplateTaskDTO result = templateTaskMapper.toDto(templateTask);
        templateTaskSearchRepository.save(templateTask);
        return result;
    }

    /**
     * Get all the templateTasks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TemplateTaskDTO> findAll() {
        log.debug("Request to get all TemplateTasks");
        return templateTaskRepository.findAll().stream()
            .map(templateTaskMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one templateTask by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TemplateTaskDTO> findOne(Long id) {
        log.debug("Request to get TemplateTask : {}", id);
        return templateTaskRepository.findById(id)
            .map(templateTaskMapper::toDto);
    }

    /**
     * Delete the templateTask by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TemplateTask : {}", id);
        templateTaskRepository.deleteById(id);
        templateTaskSearchRepository.deleteById(id);
    }

    /**
     * Search for the templateTask corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TemplateTaskDTO> search(String query) {
        log.debug("Request to search TemplateTasks for query {}", query);
        return StreamSupport
            .stream(templateTaskSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(templateTaskMapper::toDto)
        .collect(Collectors.toList());
    }
}
