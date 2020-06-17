package it.maggioli.web.rest;

import it.maggioli.service.PrevisioneTaskService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.PrevisioneTaskDTO;

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
 * REST controller for managing {@link it.maggioli.domain.PrevisioneTask}.
 */
@RestController
@RequestMapping("/api")
public class PrevisioneTaskResource {

    private final Logger log = LoggerFactory.getLogger(PrevisioneTaskResource.class);

    private static final String ENTITY_NAME = "previsioneTask";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrevisioneTaskService previsioneTaskService;

    public PrevisioneTaskResource(PrevisioneTaskService previsioneTaskService) {
        this.previsioneTaskService = previsioneTaskService;
    }

    /**
     * {@code POST  /previsione-tasks} : Create a new previsioneTask.
     *
     * @param previsioneTaskDTO the previsioneTaskDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new previsioneTaskDTO, or with status {@code 400 (Bad Request)} if the previsioneTask has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/previsione-tasks")
    public ResponseEntity<PrevisioneTaskDTO> createPrevisioneTask(@Valid @RequestBody PrevisioneTaskDTO previsioneTaskDTO) throws URISyntaxException {
        log.debug("REST request to save PrevisioneTask : {}", previsioneTaskDTO);
        if (previsioneTaskDTO.getId() != null) {
            throw new BadRequestAlertException("A new previsioneTask cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrevisioneTaskDTO result = previsioneTaskService.save(previsioneTaskDTO);
        return ResponseEntity.created(new URI("/api/previsione-tasks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /previsione-tasks} : Updates an existing previsioneTask.
     *
     * @param previsioneTaskDTO the previsioneTaskDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated previsioneTaskDTO,
     * or with status {@code 400 (Bad Request)} if the previsioneTaskDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the previsioneTaskDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/previsione-tasks")
    public ResponseEntity<PrevisioneTaskDTO> updatePrevisioneTask(@Valid @RequestBody PrevisioneTaskDTO previsioneTaskDTO) throws URISyntaxException {
        log.debug("REST request to update PrevisioneTask : {}", previsioneTaskDTO);
        if (previsioneTaskDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PrevisioneTaskDTO result = previsioneTaskService.save(previsioneTaskDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, previsioneTaskDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /previsione-tasks} : get all the previsioneTasks.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of previsioneTasks in body.
     */
    @GetMapping("/previsione-tasks")
    public List<PrevisioneTaskDTO> getAllPrevisioneTasks(@RequestParam(required = false) String filter) {
        if ("idtask-is-null".equals(filter)) {
            log.debug("REST request to get all PrevisioneTasks where idTask is null");
            return previsioneTaskService.findAllWhereIdTaskIsNull();
        }
        if ("idtask-is-null".equals(filter)) {
            log.debug("REST request to get all PrevisioneTasks where idTask is null");
            return previsioneTaskService.findAllWhereIdTaskIsNull();
        }
        if ("idtask-is-null".equals(filter)) {
            log.debug("REST request to get all PrevisioneTasks where idTask is null");
            return previsioneTaskService.findAllWhereIdTaskIsNull();
        }
        log.debug("REST request to get all PrevisioneTasks");
        return previsioneTaskService.findAll();
    }

    /**
     * {@code GET  /previsione-tasks/:id} : get the "id" previsioneTask.
     *
     * @param id the id of the previsioneTaskDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the previsioneTaskDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/previsione-tasks/{id}")
    public ResponseEntity<PrevisioneTaskDTO> getPrevisioneTask(@PathVariable Long id) {
        log.debug("REST request to get PrevisioneTask : {}", id);
        Optional<PrevisioneTaskDTO> previsioneTaskDTO = previsioneTaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(previsioneTaskDTO);
    }

    /**
     * {@code DELETE  /previsione-tasks/:id} : delete the "id" previsioneTask.
     *
     * @param id the id of the previsioneTaskDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/previsione-tasks/{id}")
    public ResponseEntity<Void> deletePrevisioneTask(@PathVariable Long id) {
        log.debug("REST request to delete PrevisioneTask : {}", id);
        previsioneTaskService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/previsione-tasks?query=:query} : search for the previsioneTask corresponding
     * to the query.
     *
     * @param query the query of the previsioneTask search.
     * @return the result of the search.
     */
    @GetMapping("/_search/previsione-tasks")
    public List<PrevisioneTaskDTO> searchPrevisioneTasks(@RequestParam String query) {
        log.debug("REST request to search PrevisioneTasks for query {}", query);
        return previsioneTaskService.search(query);
    }
}
