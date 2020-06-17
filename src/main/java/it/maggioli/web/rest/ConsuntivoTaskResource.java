package it.maggioli.web.rest;

import it.maggioli.service.ConsuntivoTaskService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.ConsuntivoTaskDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link it.maggioli.domain.ConsuntivoTask}.
 */
@RestController
@RequestMapping("/api")
public class ConsuntivoTaskResource {

    private final Logger log = LoggerFactory.getLogger(ConsuntivoTaskResource.class);

    private static final String ENTITY_NAME = "consuntivoTask";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConsuntivoTaskService consuntivoTaskService;

    public ConsuntivoTaskResource(ConsuntivoTaskService consuntivoTaskService) {
        this.consuntivoTaskService = consuntivoTaskService;
    }

    /**
     * {@code POST  /consuntivo-tasks} : Create a new consuntivoTask.
     *
     * @param consuntivoTaskDTO the consuntivoTaskDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new consuntivoTaskDTO, or with status {@code 400 (Bad Request)} if the consuntivoTask has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/consuntivo-tasks")
    public ResponseEntity<ConsuntivoTaskDTO> createConsuntivoTask(@Valid @RequestBody ConsuntivoTaskDTO consuntivoTaskDTO) throws URISyntaxException {
        log.debug("REST request to save ConsuntivoTask : {}", consuntivoTaskDTO);
        if (consuntivoTaskDTO.getId() != null) {
            throw new BadRequestAlertException("A new consuntivoTask cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConsuntivoTaskDTO result = consuntivoTaskService.save(consuntivoTaskDTO);
        return ResponseEntity.created(new URI("/api/consuntivo-tasks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /consuntivo-tasks} : Updates an existing consuntivoTask.
     *
     * @param consuntivoTaskDTO the consuntivoTaskDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated consuntivoTaskDTO,
     * or with status {@code 400 (Bad Request)} if the consuntivoTaskDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the consuntivoTaskDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/consuntivo-tasks")
    public ResponseEntity<ConsuntivoTaskDTO> updateConsuntivoTask(@Valid @RequestBody ConsuntivoTaskDTO consuntivoTaskDTO) throws URISyntaxException {
        log.debug("REST request to update ConsuntivoTask : {}", consuntivoTaskDTO);
        if (consuntivoTaskDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConsuntivoTaskDTO result = consuntivoTaskService.save(consuntivoTaskDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, consuntivoTaskDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /consuntivo-tasks} : get all the consuntivoTasks.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of consuntivoTasks in body.
     */
    @GetMapping("/consuntivo-tasks")
    public List<ConsuntivoTaskDTO> getAllConsuntivoTasks(@RequestParam(required = false) String filter) {
        if ("idtask-is-null".equals(filter)) {
            log.debug("REST request to get all ConsuntivoTasks where idTask is null");
            return consuntivoTaskService.findAllWhereIdTaskIsNull();
        }
        log.debug("REST request to get all ConsuntivoTasks");
        return consuntivoTaskService.findAll();
    }

    /**
     * {@code GET  /consuntivo-tasks/:id} : get the "id" consuntivoTask.
     *
     * @param id the id of the consuntivoTaskDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the consuntivoTaskDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/consuntivo-tasks/{id}")
    public ResponseEntity<ConsuntivoTaskDTO> getConsuntivoTask(@PathVariable Long id) {
        log.debug("REST request to get ConsuntivoTask : {}", id);
        Optional<ConsuntivoTaskDTO> consuntivoTaskDTO = consuntivoTaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(consuntivoTaskDTO);
    }

    /**
     * {@code DELETE  /consuntivo-tasks/:id} : delete the "id" consuntivoTask.
     *
     * @param id the id of the consuntivoTaskDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/consuntivo-tasks/{id}")
    public ResponseEntity<Void> deleteConsuntivoTask(@PathVariable Long id) {
        log.debug("REST request to delete ConsuntivoTask : {}", id);
        consuntivoTaskService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/consuntivo-tasks?query=:query} : search for the consuntivoTask corresponding
     * to the query.
     *
     * @param query the query of the consuntivoTask search.
     * @return the result of the search.
     */
    @GetMapping("/_search/consuntivo-tasks")
    public List<ConsuntivoTaskDTO> searchConsuntivoTasks(@RequestParam String query) {
        log.debug("REST request to search ConsuntivoTasks for query {}", query);
        return consuntivoTaskService.search(query);
    }
}
