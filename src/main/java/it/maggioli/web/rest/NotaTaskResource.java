package it.maggioli.web.rest;

import it.maggioli.service.NotaTaskService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.NotaTaskDTO;

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
 * REST controller for managing {@link it.maggioli.domain.NotaTask}.
 */
@RestController
@RequestMapping("/api")
public class NotaTaskResource {

    private final Logger log = LoggerFactory.getLogger(NotaTaskResource.class);

    private static final String ENTITY_NAME = "notaTask";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotaTaskService notaTaskService;

    public NotaTaskResource(NotaTaskService notaTaskService) {
        this.notaTaskService = notaTaskService;
    }

    /**
     * {@code POST  /nota-tasks} : Create a new notaTask.
     *
     * @param notaTaskDTO the notaTaskDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notaTaskDTO, or with status {@code 400 (Bad Request)} if the notaTask has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nota-tasks")
    public ResponseEntity<NotaTaskDTO> createNotaTask(@Valid @RequestBody NotaTaskDTO notaTaskDTO) throws URISyntaxException {
        log.debug("REST request to save NotaTask : {}", notaTaskDTO);
        if (notaTaskDTO.getId() != null) {
            throw new BadRequestAlertException("A new notaTask cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotaTaskDTO result = notaTaskService.save(notaTaskDTO);
        return ResponseEntity.created(new URI("/api/nota-tasks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nota-tasks} : Updates an existing notaTask.
     *
     * @param notaTaskDTO the notaTaskDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notaTaskDTO,
     * or with status {@code 400 (Bad Request)} if the notaTaskDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notaTaskDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nota-tasks")
    public ResponseEntity<NotaTaskDTO> updateNotaTask(@Valid @RequestBody NotaTaskDTO notaTaskDTO) throws URISyntaxException {
        log.debug("REST request to update NotaTask : {}", notaTaskDTO);
        if (notaTaskDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NotaTaskDTO result = notaTaskService.save(notaTaskDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notaTaskDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nota-tasks} : get all the notaTasks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notaTasks in body.
     */
    @GetMapping("/nota-tasks")
    public List<NotaTaskDTO> getAllNotaTasks() {
        log.debug("REST request to get all NotaTasks");
        return notaTaskService.findAll();
    }

    /**
     * {@code GET  /nota-tasks/:id} : get the "id" notaTask.
     *
     * @param id the id of the notaTaskDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notaTaskDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nota-tasks/{id}")
    public ResponseEntity<NotaTaskDTO> getNotaTask(@PathVariable Long id) {
        log.debug("REST request to get NotaTask : {}", id);
        Optional<NotaTaskDTO> notaTaskDTO = notaTaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notaTaskDTO);
    }

    /**
     * {@code DELETE  /nota-tasks/:id} : delete the "id" notaTask.
     *
     * @param id the id of the notaTaskDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nota-tasks/{id}")
    public ResponseEntity<Void> deleteNotaTask(@PathVariable Long id) {
        log.debug("REST request to delete NotaTask : {}", id);
        notaTaskService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/nota-tasks?query=:query} : search for the notaTask corresponding
     * to the query.
     *
     * @param query the query of the notaTask search.
     * @return the result of the search.
     */
    @GetMapping("/_search/nota-tasks")
    public List<NotaTaskDTO> searchNotaTasks(@RequestParam String query) {
        log.debug("REST request to search NotaTasks for query {}", query);
        return notaTaskService.search(query);
    }
}
