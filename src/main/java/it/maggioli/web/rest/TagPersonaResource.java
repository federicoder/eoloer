package it.maggioli.web.rest;

import it.maggioli.service.TagPersonaService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.TagPersonaDTO;

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
 * REST controller for managing {@link it.maggioli.domain.TagPersona}.
 */
@RestController
@RequestMapping("/api")
public class TagPersonaResource {

    private final Logger log = LoggerFactory.getLogger(TagPersonaResource.class);

    private static final String ENTITY_NAME = "tagPersona";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TagPersonaService tagPersonaService;

    public TagPersonaResource(TagPersonaService tagPersonaService) {
        this.tagPersonaService = tagPersonaService;
    }

    /**
     * {@code POST  /tag-personas} : Create a new tagPersona.
     *
     * @param tagPersonaDTO the tagPersonaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tagPersonaDTO, or with status {@code 400 (Bad Request)} if the tagPersona has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tag-personas")
    public ResponseEntity<TagPersonaDTO> createTagPersona(@Valid @RequestBody TagPersonaDTO tagPersonaDTO) throws URISyntaxException {
        log.debug("REST request to save TagPersona : {}", tagPersonaDTO);
        if (tagPersonaDTO.getId() != null) {
            throw new BadRequestAlertException("A new tagPersona cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TagPersonaDTO result = tagPersonaService.save(tagPersonaDTO);
        return ResponseEntity.created(new URI("/api/tag-personas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tag-personas} : Updates an existing tagPersona.
     *
     * @param tagPersonaDTO the tagPersonaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tagPersonaDTO,
     * or with status {@code 400 (Bad Request)} if the tagPersonaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tagPersonaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tag-personas")
    public ResponseEntity<TagPersonaDTO> updateTagPersona(@Valid @RequestBody TagPersonaDTO tagPersonaDTO) throws URISyntaxException {
        log.debug("REST request to update TagPersona : {}", tagPersonaDTO);
        if (tagPersonaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TagPersonaDTO result = tagPersonaService.save(tagPersonaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tagPersonaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tag-personas} : get all the tagPersonas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tagPersonas in body.
     */
    @GetMapping("/tag-personas")
    public List<TagPersonaDTO> getAllTagPersonas() {
        log.debug("REST request to get all TagPersonas");
        return tagPersonaService.findAll();
    }

    /**
     * {@code GET  /tag-personas/:id} : get the "id" tagPersona.
     *
     * @param id the id of the tagPersonaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tagPersonaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tag-personas/{id}")
    public ResponseEntity<TagPersonaDTO> getTagPersona(@PathVariable Long id) {
        log.debug("REST request to get TagPersona : {}", id);
        Optional<TagPersonaDTO> tagPersonaDTO = tagPersonaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tagPersonaDTO);
    }

    /**
     * {@code DELETE  /tag-personas/:id} : delete the "id" tagPersona.
     *
     * @param id the id of the tagPersonaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tag-personas/{id}")
    public ResponseEntity<Void> deleteTagPersona(@PathVariable Long id) {
        log.debug("REST request to delete TagPersona : {}", id);
        tagPersonaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/tag-personas?query=:query} : search for the tagPersona corresponding
     * to the query.
     *
     * @param query the query of the tagPersona search.
     * @return the result of the search.
     */
    @GetMapping("/_search/tag-personas")
    public List<TagPersonaDTO> searchTagPersonas(@RequestParam String query) {
        log.debug("REST request to search TagPersonas for query {}", query);
        return tagPersonaService.search(query);
    }
}
