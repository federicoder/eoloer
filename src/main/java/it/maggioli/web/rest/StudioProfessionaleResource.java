package it.maggioli.web.rest;

import it.maggioli.service.StudioProfessionaleService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.StudioProfessionaleDTO;

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
 * REST controller for managing {@link it.maggioli.domain.StudioProfessionale}.
 */
@RestController
@RequestMapping("/api")
public class StudioProfessionaleResource {

    private final Logger log = LoggerFactory.getLogger(StudioProfessionaleResource.class);

    private static final String ENTITY_NAME = "studioProfessionale";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StudioProfessionaleService studioProfessionaleService;

    public StudioProfessionaleResource(StudioProfessionaleService studioProfessionaleService) {
        this.studioProfessionaleService = studioProfessionaleService;
    }

    /**
     * {@code POST  /studio-professionales} : Create a new studioProfessionale.
     *
     * @param studioProfessionaleDTO the studioProfessionaleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new studioProfessionaleDTO, or with status {@code 400 (Bad Request)} if the studioProfessionale has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/studio-professionales")
    public ResponseEntity<StudioProfessionaleDTO> createStudioProfessionale(@Valid @RequestBody StudioProfessionaleDTO studioProfessionaleDTO) throws URISyntaxException {
        log.debug("REST request to save StudioProfessionale : {}", studioProfessionaleDTO);
        if (studioProfessionaleDTO.getId() != null) {
            throw new BadRequestAlertException("A new studioProfessionale cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StudioProfessionaleDTO result = studioProfessionaleService.save(studioProfessionaleDTO);
        return ResponseEntity.created(new URI("/api/studio-professionales/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /studio-professionales} : Updates an existing studioProfessionale.
     *
     * @param studioProfessionaleDTO the studioProfessionaleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated studioProfessionaleDTO,
     * or with status {@code 400 (Bad Request)} if the studioProfessionaleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the studioProfessionaleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/studio-professionales")
    public ResponseEntity<StudioProfessionaleDTO> updateStudioProfessionale(@Valid @RequestBody StudioProfessionaleDTO studioProfessionaleDTO) throws URISyntaxException {
        log.debug("REST request to update StudioProfessionale : {}", studioProfessionaleDTO);
        if (studioProfessionaleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StudioProfessionaleDTO result = studioProfessionaleService.save(studioProfessionaleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, studioProfessionaleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /studio-professionales} : get all the studioProfessionales.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of studioProfessionales in body.
     */
    @GetMapping("/studio-professionales")
    public List<StudioProfessionaleDTO> getAllStudioProfessionales(@RequestParam(required = false) String filter) {
        if ("idstudioprofessionale-is-null".equals(filter)) {
            log.debug("REST request to get all StudioProfessionales where idStudioProfessionale is null");
            return studioProfessionaleService.findAllWhereIdStudioProfessionaleIsNull();
        }
        log.debug("REST request to get all StudioProfessionales");
        return studioProfessionaleService.findAll();
    }

    /**
     * {@code GET  /studio-professionales/:id} : get the "id" studioProfessionale.
     *
     * @param id the id of the studioProfessionaleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the studioProfessionaleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/studio-professionales/{id}")
    public ResponseEntity<StudioProfessionaleDTO> getStudioProfessionale(@PathVariable Long id) {
        log.debug("REST request to get StudioProfessionale : {}", id);
        Optional<StudioProfessionaleDTO> studioProfessionaleDTO = studioProfessionaleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(studioProfessionaleDTO);
    }

    /**
     * {@code DELETE  /studio-professionales/:id} : delete the "id" studioProfessionale.
     *
     * @param id the id of the studioProfessionaleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/studio-professionales/{id}")
    public ResponseEntity<Void> deleteStudioProfessionale(@PathVariable Long id) {
        log.debug("REST request to delete StudioProfessionale : {}", id);
        studioProfessionaleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/studio-professionales?query=:query} : search for the studioProfessionale corresponding
     * to the query.
     *
     * @param query the query of the studioProfessionale search.
     * @return the result of the search.
     */
    @GetMapping("/_search/studio-professionales")
    public List<StudioProfessionaleDTO> searchStudioProfessionales(@RequestParam String query) {
        log.debug("REST request to search StudioProfessionales for query {}", query);
        return studioProfessionaleService.search(query);
    }
}
