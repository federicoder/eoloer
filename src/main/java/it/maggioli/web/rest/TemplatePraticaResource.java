package it.maggioli.web.rest;

import it.maggioli.service.TemplatePraticaService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.TemplatePraticaDTO;

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
 * REST controller for managing {@link it.maggioli.domain.TemplatePratica}.
 */
@RestController
@RequestMapping("/api")
public class TemplatePraticaResource {

    private final Logger log = LoggerFactory.getLogger(TemplatePraticaResource.class);

    private static final String ENTITY_NAME = "templatePratica";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TemplatePraticaService templatePraticaService;

    public TemplatePraticaResource(TemplatePraticaService templatePraticaService) {
        this.templatePraticaService = templatePraticaService;
    }

    /**
     * {@code POST  /template-praticas} : Create a new templatePratica.
     *
     * @param templatePraticaDTO the templatePraticaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new templatePraticaDTO, or with status {@code 400 (Bad Request)} if the templatePratica has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/template-praticas")
    public ResponseEntity<TemplatePraticaDTO> createTemplatePratica(@Valid @RequestBody TemplatePraticaDTO templatePraticaDTO) throws URISyntaxException {
        log.debug("REST request to save TemplatePratica : {}", templatePraticaDTO);
        if (templatePraticaDTO.getId() != null) {
            throw new BadRequestAlertException("A new templatePratica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TemplatePraticaDTO result = templatePraticaService.save(templatePraticaDTO);
        return ResponseEntity.created(new URI("/api/template-praticas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /template-praticas} : Updates an existing templatePratica.
     *
     * @param templatePraticaDTO the templatePraticaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated templatePraticaDTO,
     * or with status {@code 400 (Bad Request)} if the templatePraticaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the templatePraticaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/template-praticas")
    public ResponseEntity<TemplatePraticaDTO> updateTemplatePratica(@Valid @RequestBody TemplatePraticaDTO templatePraticaDTO) throws URISyntaxException {
        log.debug("REST request to update TemplatePratica : {}", templatePraticaDTO);
        if (templatePraticaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TemplatePraticaDTO result = templatePraticaService.save(templatePraticaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, templatePraticaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /template-praticas} : get all the templatePraticas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of templatePraticas in body.
     */
    @GetMapping("/template-praticas")
    public List<TemplatePraticaDTO> getAllTemplatePraticas() {
        log.debug("REST request to get all TemplatePraticas");
        return templatePraticaService.findAll();
    }

    /**
     * {@code GET  /template-praticas/:id} : get the "id" templatePratica.
     *
     * @param id the id of the templatePraticaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the templatePraticaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/template-praticas/{id}")
    public ResponseEntity<TemplatePraticaDTO> getTemplatePratica(@PathVariable Long id) {
        log.debug("REST request to get TemplatePratica : {}", id);
        Optional<TemplatePraticaDTO> templatePraticaDTO = templatePraticaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(templatePraticaDTO);
    }

    /**
     * {@code DELETE  /template-praticas/:id} : delete the "id" templatePratica.
     *
     * @param id the id of the templatePraticaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/template-praticas/{id}")
    public ResponseEntity<Void> deleteTemplatePratica(@PathVariable Long id) {
        log.debug("REST request to delete TemplatePratica : {}", id);
        templatePraticaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/template-praticas?query=:query} : search for the templatePratica corresponding
     * to the query.
     *
     * @param query the query of the templatePratica search.
     * @return the result of the search.
     */
    @GetMapping("/_search/template-praticas")
    public List<TemplatePraticaDTO> searchTemplatePraticas(@RequestParam String query) {
        log.debug("REST request to search TemplatePraticas for query {}", query);
        return templatePraticaService.search(query);
    }
}
