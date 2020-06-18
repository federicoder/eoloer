package it.maggioli.web.rest;

import it.maggioli.service.InvitoAttivitaService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.InvitoAttivitaDTO;

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
 * REST controller for managing {@link it.maggioli.domain.InvitoAttivita}.
 */
@RestController
@RequestMapping("/api")
public class InvitoAttivitaResource {

    private final Logger log = LoggerFactory.getLogger(InvitoAttivitaResource.class);

    private static final String ENTITY_NAME = "invitoAttivita";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvitoAttivitaService invitoAttivitaService;

    public InvitoAttivitaResource(InvitoAttivitaService invitoAttivitaService) {
        this.invitoAttivitaService = invitoAttivitaService;
    }

    /**
     * {@code POST  /invito-attivitas} : Create a new invitoAttivita.
     *
     * @param invitoAttivitaDTO the invitoAttivitaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invitoAttivitaDTO, or with status {@code 400 (Bad Request)} if the invitoAttivita has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invito-attivitas")
    public ResponseEntity<InvitoAttivitaDTO> createInvitoAttivita(@RequestBody InvitoAttivitaDTO invitoAttivitaDTO) throws URISyntaxException {
        log.debug("REST request to save InvitoAttivita : {}", invitoAttivitaDTO);
        if (invitoAttivitaDTO.getId() != null) {
            throw new BadRequestAlertException("A new invitoAttivita cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvitoAttivitaDTO result = invitoAttivitaService.save(invitoAttivitaDTO);
        return ResponseEntity.created(new URI("/api/invito-attivitas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /invito-attivitas} : Updates an existing invitoAttivita.
     *
     * @param invitoAttivitaDTO the invitoAttivitaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invitoAttivitaDTO,
     * or with status {@code 400 (Bad Request)} if the invitoAttivitaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invitoAttivitaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invito-attivitas")
    public ResponseEntity<InvitoAttivitaDTO> updateInvitoAttivita(@RequestBody InvitoAttivitaDTO invitoAttivitaDTO) throws URISyntaxException {
        log.debug("REST request to update InvitoAttivita : {}", invitoAttivitaDTO);
        if (invitoAttivitaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvitoAttivitaDTO result = invitoAttivitaService.save(invitoAttivitaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, invitoAttivitaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /invito-attivitas} : get all the invitoAttivitas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invitoAttivitas in body.
     */
    @GetMapping("/invito-attivitas")
    public List<InvitoAttivitaDTO> getAllInvitoAttivitas() {
        log.debug("REST request to get all InvitoAttivitas");
        return invitoAttivitaService.findAll();
    }

    /**
     * {@code GET  /invito-attivitas/:id} : get the "id" invitoAttivita.
     *
     * @param id the id of the invitoAttivitaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invitoAttivitaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invito-attivitas/{id}")
    public ResponseEntity<InvitoAttivitaDTO> getInvitoAttivita(@PathVariable Long id) {
        log.debug("REST request to get InvitoAttivita : {}", id);
        Optional<InvitoAttivitaDTO> invitoAttivitaDTO = invitoAttivitaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invitoAttivitaDTO);
    }

    /**
     * {@code DELETE  /invito-attivitas/:id} : delete the "id" invitoAttivita.
     *
     * @param id the id of the invitoAttivitaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invito-attivitas/{id}")
    public ResponseEntity<Void> deleteInvitoAttivita(@PathVariable Long id) {
        log.debug("REST request to delete InvitoAttivita : {}", id);
        invitoAttivitaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/invito-attivitas?query=:query} : search for the invitoAttivita corresponding
     * to the query.
     *
     * @param query the query of the invitoAttivita search.
     * @return the result of the search.
     */
    @GetMapping("/_search/invito-attivitas")
    public List<InvitoAttivitaDTO> searchInvitoAttivitas(@RequestParam String query) {
        log.debug("REST request to search InvitoAttivitas for query {}", query);
        return invitoAttivitaService.search(query);
    }
}
