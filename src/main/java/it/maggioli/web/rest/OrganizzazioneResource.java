package it.maggioli.web.rest;

import it.maggioli.service.OrganizzazioneService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.OrganizzazioneDTO;

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
 * REST controller for managing {@link it.maggioli.domain.Organizzazione}.
 */
@RestController
@RequestMapping("/api")
public class OrganizzazioneResource {

    private final Logger log = LoggerFactory.getLogger(OrganizzazioneResource.class);

    private static final String ENTITY_NAME = "organizzazione";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrganizzazioneService organizzazioneService;

    public OrganizzazioneResource(OrganizzazioneService organizzazioneService) {
        this.organizzazioneService = organizzazioneService;
    }

    /**
     * {@code POST  /organizzaziones} : Create a new organizzazione.
     *
     * @param organizzazioneDTO the organizzazioneDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new organizzazioneDTO, or with status {@code 400 (Bad Request)} if the organizzazione has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/organizzaziones")
    public ResponseEntity<OrganizzazioneDTO> createOrganizzazione(@Valid @RequestBody OrganizzazioneDTO organizzazioneDTO) throws URISyntaxException {
        log.debug("REST request to save Organizzazione : {}", organizzazioneDTO);
        if (organizzazioneDTO.getId() != null) {
            throw new BadRequestAlertException("A new organizzazione cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrganizzazioneDTO result = organizzazioneService.save(organizzazioneDTO);
        return ResponseEntity.created(new URI("/api/organizzaziones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /organizzaziones} : Updates an existing organizzazione.
     *
     * @param organizzazioneDTO the organizzazioneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated organizzazioneDTO,
     * or with status {@code 400 (Bad Request)} if the organizzazioneDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the organizzazioneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/organizzaziones")
    public ResponseEntity<OrganizzazioneDTO> updateOrganizzazione(@Valid @RequestBody OrganizzazioneDTO organizzazioneDTO) throws URISyntaxException {
        log.debug("REST request to update Organizzazione : {}", organizzazioneDTO);
        if (organizzazioneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrganizzazioneDTO result = organizzazioneService.save(organizzazioneDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, organizzazioneDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /organizzaziones} : get all the organizzaziones.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of organizzaziones in body.
     */
    @GetMapping("/organizzaziones")
    public List<OrganizzazioneDTO> getAllOrganizzaziones(@RequestParam(required = false) String filter) {
        if ("id-is-null".equals(filter)) {
            log.debug("REST request to get all Organizzaziones where id is null");
            return organizzazioneService.findAllWhereIdIsNull();
        }
        log.debug("REST request to get all Organizzaziones");
        return organizzazioneService.findAll();
    }

    /**
     * {@code GET  /organizzaziones/:id} : get the "id" organizzazione.
     *
     * @param id the id of the organizzazioneDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the organizzazioneDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/organizzaziones/{id}")
    public ResponseEntity<OrganizzazioneDTO> getOrganizzazione(@PathVariable Long id) {
        log.debug("REST request to get Organizzazione : {}", id);
        Optional<OrganizzazioneDTO> organizzazioneDTO = organizzazioneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(organizzazioneDTO);
    }

    /**
     * {@code DELETE  /organizzaziones/:id} : delete the "id" organizzazione.
     *
     * @param id the id of the organizzazioneDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/organizzaziones/{id}")
    public ResponseEntity<Void> deleteOrganizzazione(@PathVariable Long id) {
        log.debug("REST request to delete Organizzazione : {}", id);
        organizzazioneService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/organizzaziones?query=:query} : search for the organizzazione corresponding
     * to the query.
     *
     * @param query the query of the organizzazione search.
     * @return the result of the search.
     */
    @GetMapping("/_search/organizzaziones")
    public List<OrganizzazioneDTO> searchOrganizzaziones(@RequestParam String query) {
        log.debug("REST request to search Organizzaziones for query {}", query);
        return organizzazioneService.search(query);
    }
}
