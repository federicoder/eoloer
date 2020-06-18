package it.maggioli.web.rest;

import it.maggioli.service.AssegnazioneTaskService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.AssegnazioneTaskDTO;

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
 * REST controller for managing {@link it.maggioli.domain.AssegnazioneTask}.
 */
@RestController
@RequestMapping("/api")
public class AssegnazioneTaskResource {

    private final Logger log = LoggerFactory.getLogger(AssegnazioneTaskResource.class);

    private static final String ENTITY_NAME = "assegnazioneTask";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AssegnazioneTaskService assegnazioneTaskService;

    public AssegnazioneTaskResource(AssegnazioneTaskService assegnazioneTaskService) {
        this.assegnazioneTaskService = assegnazioneTaskService;
    }

    /**
     * {@code POST  /assegnazione-tasks} : Create a new assegnazioneTask.
     *
     * @param assegnazioneTaskDTO the assegnazioneTaskDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new assegnazioneTaskDTO, or with status {@code 400 (Bad Request)} if the assegnazioneTask has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/assegnazione-tasks")
    public ResponseEntity<AssegnazioneTaskDTO> createAssegnazioneTask(@Valid @RequestBody AssegnazioneTaskDTO assegnazioneTaskDTO) throws URISyntaxException {
        log.debug("REST request to save AssegnazioneTask : {}", assegnazioneTaskDTO);
        if (assegnazioneTaskDTO.getId() != null) {
            throw new BadRequestAlertException("A new assegnazioneTask cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AssegnazioneTaskDTO result = assegnazioneTaskService.save(assegnazioneTaskDTO);
        return ResponseEntity.created(new URI("/api/assegnazione-tasks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /assegnazione-tasks} : Updates an existing assegnazioneTask.
     *
     * @param assegnazioneTaskDTO the assegnazioneTaskDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated assegnazioneTaskDTO,
     * or with status {@code 400 (Bad Request)} if the assegnazioneTaskDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the assegnazioneTaskDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/assegnazione-tasks")
    public ResponseEntity<AssegnazioneTaskDTO> updateAssegnazioneTask(@Valid @RequestBody AssegnazioneTaskDTO assegnazioneTaskDTO) throws URISyntaxException {
        log.debug("REST request to update AssegnazioneTask : {}", assegnazioneTaskDTO);
        if (assegnazioneTaskDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AssegnazioneTaskDTO result = assegnazioneTaskService.save(assegnazioneTaskDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, assegnazioneTaskDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /assegnazione-tasks} : get all the assegnazioneTasks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of assegnazioneTasks in body.
     */
    @GetMapping("/assegnazione-tasks")
    public List<AssegnazioneTaskDTO> getAllAssegnazioneTasks() {
        log.debug("REST request to get all AssegnazioneTasks");
        return assegnazioneTaskService.findAll();
    }

    /**
     * {@code GET  /assegnazione-tasks/:id} : get the "id" assegnazioneTask.
     *
     * @param id the id of the assegnazioneTaskDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the assegnazioneTaskDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/assegnazione-tasks/{id}")
    public ResponseEntity<AssegnazioneTaskDTO> getAssegnazioneTask(@PathVariable Long id) {
        log.debug("REST request to get AssegnazioneTask : {}", id);
        Optional<AssegnazioneTaskDTO> assegnazioneTaskDTO = assegnazioneTaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(assegnazioneTaskDTO);
    }

    /**
     * {@code DELETE  /assegnazione-tasks/:id} : delete the "id" assegnazioneTask.
     *
     * @param id the id of the assegnazioneTaskDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/assegnazione-tasks/{id}")
    public ResponseEntity<Void> deleteAssegnazioneTask(@PathVariable Long id) {
        log.debug("REST request to delete AssegnazioneTask : {}", id);
        assegnazioneTaskService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/assegnazione-tasks?query=:query} : search for the assegnazioneTask corresponding
     * to the query.
     *
     * @param query the query of the assegnazioneTask search.
     * @return the result of the search.
     */
    @GetMapping("/_search/assegnazione-tasks")
    public List<AssegnazioneTaskDTO> searchAssegnazioneTasks(@RequestParam String query) {
        log.debug("REST request to search AssegnazioneTasks for query {}", query);
        return assegnazioneTaskService.search(query);
    }
}
