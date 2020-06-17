package it.maggioli.web.rest;

import it.maggioli.service.AllegatoTemplateTaskService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.AllegatoTemplateTaskDTO;

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
 * REST controller for managing {@link it.maggioli.domain.AllegatoTemplateTask}.
 */
@RestController
@RequestMapping("/api")
public class AllegatoTemplateTaskResource {

    private final Logger log = LoggerFactory.getLogger(AllegatoTemplateTaskResource.class);

    private static final String ENTITY_NAME = "allegatoTemplateTask";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AllegatoTemplateTaskService allegatoTemplateTaskService;

    public AllegatoTemplateTaskResource(AllegatoTemplateTaskService allegatoTemplateTaskService) {
        this.allegatoTemplateTaskService = allegatoTemplateTaskService;
    }

    /**
     * {@code POST  /allegato-template-tasks} : Create a new allegatoTemplateTask.
     *
     * @param allegatoTemplateTaskDTO the allegatoTemplateTaskDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new allegatoTemplateTaskDTO, or with status {@code 400 (Bad Request)} if the allegatoTemplateTask has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/allegato-template-tasks")
    public ResponseEntity<AllegatoTemplateTaskDTO> createAllegatoTemplateTask(@Valid @RequestBody AllegatoTemplateTaskDTO allegatoTemplateTaskDTO) throws URISyntaxException {
        log.debug("REST request to save AllegatoTemplateTask : {}", allegatoTemplateTaskDTO);
        if (allegatoTemplateTaskDTO.getId() != null) {
            throw new BadRequestAlertException("A new allegatoTemplateTask cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AllegatoTemplateTaskDTO result = allegatoTemplateTaskService.save(allegatoTemplateTaskDTO);
        return ResponseEntity.created(new URI("/api/allegato-template-tasks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /allegato-template-tasks} : Updates an existing allegatoTemplateTask.
     *
     * @param allegatoTemplateTaskDTO the allegatoTemplateTaskDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated allegatoTemplateTaskDTO,
     * or with status {@code 400 (Bad Request)} if the allegatoTemplateTaskDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the allegatoTemplateTaskDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/allegato-template-tasks")
    public ResponseEntity<AllegatoTemplateTaskDTO> updateAllegatoTemplateTask(@Valid @RequestBody AllegatoTemplateTaskDTO allegatoTemplateTaskDTO) throws URISyntaxException {
        log.debug("REST request to update AllegatoTemplateTask : {}", allegatoTemplateTaskDTO);
        if (allegatoTemplateTaskDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AllegatoTemplateTaskDTO result = allegatoTemplateTaskService.save(allegatoTemplateTaskDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, allegatoTemplateTaskDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /allegato-template-tasks} : get all the allegatoTemplateTasks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of allegatoTemplateTasks in body.
     */
    @GetMapping("/allegato-template-tasks")
    public List<AllegatoTemplateTaskDTO> getAllAllegatoTemplateTasks() {
        log.debug("REST request to get all AllegatoTemplateTasks");
        return allegatoTemplateTaskService.findAll();
    }

    /**
     * {@code GET  /allegato-template-tasks/:id} : get the "id" allegatoTemplateTask.
     *
     * @param id the id of the allegatoTemplateTaskDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the allegatoTemplateTaskDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/allegato-template-tasks/{id}")
    public ResponseEntity<AllegatoTemplateTaskDTO> getAllegatoTemplateTask(@PathVariable Long id) {
        log.debug("REST request to get AllegatoTemplateTask : {}", id);
        Optional<AllegatoTemplateTaskDTO> allegatoTemplateTaskDTO = allegatoTemplateTaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(allegatoTemplateTaskDTO);
    }

    /**
     * {@code DELETE  /allegato-template-tasks/:id} : delete the "id" allegatoTemplateTask.
     *
     * @param id the id of the allegatoTemplateTaskDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/allegato-template-tasks/{id}")
    public ResponseEntity<Void> deleteAllegatoTemplateTask(@PathVariable Long id) {
        log.debug("REST request to delete AllegatoTemplateTask : {}", id);
        allegatoTemplateTaskService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/allegato-template-tasks?query=:query} : search for the allegatoTemplateTask corresponding
     * to the query.
     *
     * @param query the query of the allegatoTemplateTask search.
     * @return the result of the search.
     */
    @GetMapping("/_search/allegato-template-tasks")
    public List<AllegatoTemplateTaskDTO> searchAllegatoTemplateTasks(@RequestParam String query) {
        log.debug("REST request to search AllegatoTemplateTasks for query {}", query);
        return allegatoTemplateTaskService.search(query);
    }
}
