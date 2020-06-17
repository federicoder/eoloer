package it.maggioli.web.rest;

import it.maggioli.service.TemplateTaskService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.TemplateTaskDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link it.maggioli.domain.TemplateTask}.
 */
@RestController
@RequestMapping("/api")
public class TemplateTaskResource {

    private final Logger log = LoggerFactory.getLogger(TemplateTaskResource.class);

    private static final String ENTITY_NAME = "templateTask";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TemplateTaskService templateTaskService;

    public TemplateTaskResource(TemplateTaskService templateTaskService) {
        this.templateTaskService = templateTaskService;
    }

    /**
     * {@code POST  /template-tasks} : Create a new templateTask.
     *
     * @param templateTaskDTO the templateTaskDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new templateTaskDTO, or with status {@code 400 (Bad Request)} if the templateTask has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/template-tasks")
    public ResponseEntity<TemplateTaskDTO> createTemplateTask(@RequestBody TemplateTaskDTO templateTaskDTO) throws URISyntaxException {
        log.debug("REST request to save TemplateTask : {}", templateTaskDTO);
        if (templateTaskDTO.getId() != null) {
            throw new BadRequestAlertException("A new templateTask cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TemplateTaskDTO result = templateTaskService.save(templateTaskDTO);
        return ResponseEntity.created(new URI("/api/template-tasks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /template-tasks} : Updates an existing templateTask.
     *
     * @param templateTaskDTO the templateTaskDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated templateTaskDTO,
     * or with status {@code 400 (Bad Request)} if the templateTaskDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the templateTaskDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/template-tasks")
    public ResponseEntity<TemplateTaskDTO> updateTemplateTask(@RequestBody TemplateTaskDTO templateTaskDTO) throws URISyntaxException {
        log.debug("REST request to update TemplateTask : {}", templateTaskDTO);
        if (templateTaskDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TemplateTaskDTO result = templateTaskService.save(templateTaskDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, templateTaskDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /template-tasks} : get all the templateTasks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of templateTasks in body.
     */
    @GetMapping("/template-tasks")
    public List<TemplateTaskDTO> getAllTemplateTasks() {
        log.debug("REST request to get all TemplateTasks");
        return templateTaskService.findAll();
    }

    /**
     * {@code GET  /template-tasks/:id} : get the "id" templateTask.
     *
     * @param id the id of the templateTaskDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the templateTaskDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/template-tasks/{id}")
    public ResponseEntity<TemplateTaskDTO> getTemplateTask(@PathVariable Long id) {
        log.debug("REST request to get TemplateTask : {}", id);
        Optional<TemplateTaskDTO> templateTaskDTO = templateTaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(templateTaskDTO);
    }

    /**
     * {@code DELETE  /template-tasks/:id} : delete the "id" templateTask.
     *
     * @param id the id of the templateTaskDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/template-tasks/{id}")
    public ResponseEntity<Void> deleteTemplateTask(@PathVariable Long id) {
        log.debug("REST request to delete TemplateTask : {}", id);
        templateTaskService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/template-tasks?query=:query} : search for the templateTask corresponding
     * to the query.
     *
     * @param query the query of the templateTask search.
     * @return the result of the search.
     */
    @GetMapping("/_search/template-tasks")
    public List<TemplateTaskDTO> searchTemplateTasks(@RequestParam String query) {
        log.debug("REST request to search TemplateTasks for query {}", query);
        return templateTaskService.search(query);
    }
}
