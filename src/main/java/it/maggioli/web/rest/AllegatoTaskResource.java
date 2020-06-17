package it.maggioli.web.rest;

import it.maggioli.service.AllegatoTaskService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.AllegatoTaskDTO;

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
 * REST controller for managing {@link it.maggioli.domain.AllegatoTask}.
 */
@RestController
@RequestMapping("/api")
public class AllegatoTaskResource {

    private final Logger log = LoggerFactory.getLogger(AllegatoTaskResource.class);

    private static final String ENTITY_NAME = "allegatoTask";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AllegatoTaskService allegatoTaskService;

    public AllegatoTaskResource(AllegatoTaskService allegatoTaskService) {
        this.allegatoTaskService = allegatoTaskService;
    }

    /**
     * {@code POST  /allegato-tasks} : Create a new allegatoTask.
     *
     * @param allegatoTaskDTO the allegatoTaskDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new allegatoTaskDTO, or with status {@code 400 (Bad Request)} if the allegatoTask has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/allegato-tasks")
    public ResponseEntity<AllegatoTaskDTO> createAllegatoTask(@Valid @RequestBody AllegatoTaskDTO allegatoTaskDTO) throws URISyntaxException {
        log.debug("REST request to save AllegatoTask : {}", allegatoTaskDTO);
        if (allegatoTaskDTO.getId() != null) {
            throw new BadRequestAlertException("A new allegatoTask cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AllegatoTaskDTO result = allegatoTaskService.save(allegatoTaskDTO);
        return ResponseEntity.created(new URI("/api/allegato-tasks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /allegato-tasks} : Updates an existing allegatoTask.
     *
     * @param allegatoTaskDTO the allegatoTaskDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated allegatoTaskDTO,
     * or with status {@code 400 (Bad Request)} if the allegatoTaskDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the allegatoTaskDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/allegato-tasks")
    public ResponseEntity<AllegatoTaskDTO> updateAllegatoTask(@Valid @RequestBody AllegatoTaskDTO allegatoTaskDTO) throws URISyntaxException {
        log.debug("REST request to update AllegatoTask : {}", allegatoTaskDTO);
        if (allegatoTaskDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AllegatoTaskDTO result = allegatoTaskService.save(allegatoTaskDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, allegatoTaskDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /allegato-tasks} : get all the allegatoTasks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of allegatoTasks in body.
     */
    @GetMapping("/allegato-tasks")
    public List<AllegatoTaskDTO> getAllAllegatoTasks() {
        log.debug("REST request to get all AllegatoTasks");
        return allegatoTaskService.findAll();
    }

    /**
     * {@code GET  /allegato-tasks/:id} : get the "id" allegatoTask.
     *
     * @param id the id of the allegatoTaskDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the allegatoTaskDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/allegato-tasks/{id}")
    public ResponseEntity<AllegatoTaskDTO> getAllegatoTask(@PathVariable Long id) {
        log.debug("REST request to get AllegatoTask : {}", id);
        Optional<AllegatoTaskDTO> allegatoTaskDTO = allegatoTaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(allegatoTaskDTO);
    }

    /**
     * {@code DELETE  /allegato-tasks/:id} : delete the "id" allegatoTask.
     *
     * @param id the id of the allegatoTaskDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/allegato-tasks/{id}")
    public ResponseEntity<Void> deleteAllegatoTask(@PathVariable Long id) {
        log.debug("REST request to delete AllegatoTask : {}", id);
        allegatoTaskService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/allegato-tasks?query=:query} : search for the allegatoTask corresponding
     * to the query.
     *
     * @param query the query of the allegatoTask search.
     * @return the result of the search.
     */
    @GetMapping("/_search/allegato-tasks")
    public List<AllegatoTaskDTO> searchAllegatoTasks(@RequestParam String query) {
        log.debug("REST request to search AllegatoTasks for query {}", query);
        return allegatoTaskService.search(query);
    }
}
