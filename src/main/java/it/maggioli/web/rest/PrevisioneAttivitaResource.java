package it.maggioli.web.rest;

import it.maggioli.service.PrevisioneAttivitaService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.PrevisioneAttivitaDTO;

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
 * REST controller for managing {@link it.maggioli.domain.PrevisioneAttivita}.
 */
@RestController
@RequestMapping("/api")
public class PrevisioneAttivitaResource {

    private final Logger log = LoggerFactory.getLogger(PrevisioneAttivitaResource.class);

    private static final String ENTITY_NAME = "previsioneAttivita";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrevisioneAttivitaService previsioneAttivitaService;

    public PrevisioneAttivitaResource(PrevisioneAttivitaService previsioneAttivitaService) {
        this.previsioneAttivitaService = previsioneAttivitaService;
    }

    /**
     * {@code POST  /previsione-attivitas} : Create a new previsioneAttivita.
     *
     * @param previsioneAttivitaDTO the previsioneAttivitaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new previsioneAttivitaDTO, or with status {@code 400 (Bad Request)} if the previsioneAttivita has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/previsione-attivitas")
    public ResponseEntity<PrevisioneAttivitaDTO> createPrevisioneAttivita(@Valid @RequestBody PrevisioneAttivitaDTO previsioneAttivitaDTO) throws URISyntaxException {
        log.debug("REST request to save PrevisioneAttivita : {}", previsioneAttivitaDTO);
        if (previsioneAttivitaDTO.getId() != null) {
            throw new BadRequestAlertException("A new previsioneAttivita cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrevisioneAttivitaDTO result = previsioneAttivitaService.save(previsioneAttivitaDTO);
        return ResponseEntity.created(new URI("/api/previsione-attivitas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /previsione-attivitas} : Updates an existing previsioneAttivita.
     *
     * @param previsioneAttivitaDTO the previsioneAttivitaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated previsioneAttivitaDTO,
     * or with status {@code 400 (Bad Request)} if the previsioneAttivitaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the previsioneAttivitaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/previsione-attivitas")
    public ResponseEntity<PrevisioneAttivitaDTO> updatePrevisioneAttivita(@Valid @RequestBody PrevisioneAttivitaDTO previsioneAttivitaDTO) throws URISyntaxException {
        log.debug("REST request to update PrevisioneAttivita : {}", previsioneAttivitaDTO);
        if (previsioneAttivitaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PrevisioneAttivitaDTO result = previsioneAttivitaService.save(previsioneAttivitaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, previsioneAttivitaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /previsione-attivitas} : get all the previsioneAttivitas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of previsioneAttivitas in body.
     */
    @GetMapping("/previsione-attivitas")
    public List<PrevisioneAttivitaDTO> getAllPrevisioneAttivitas() {
        log.debug("REST request to get all PrevisioneAttivitas");
        return previsioneAttivitaService.findAll();
    }

    /**
     * {@code GET  /previsione-attivitas/:id} : get the "id" previsioneAttivita.
     *
     * @param id the id of the previsioneAttivitaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the previsioneAttivitaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/previsione-attivitas/{id}")
    public ResponseEntity<PrevisioneAttivitaDTO> getPrevisioneAttivita(@PathVariable Long id) {
        log.debug("REST request to get PrevisioneAttivita : {}", id);
        Optional<PrevisioneAttivitaDTO> previsioneAttivitaDTO = previsioneAttivitaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(previsioneAttivitaDTO);
    }

    /**
     * {@code DELETE  /previsione-attivitas/:id} : delete the "id" previsioneAttivita.
     *
     * @param id the id of the previsioneAttivitaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/previsione-attivitas/{id}")
    public ResponseEntity<Void> deletePrevisioneAttivita(@PathVariable Long id) {
        log.debug("REST request to delete PrevisioneAttivita : {}", id);
        previsioneAttivitaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/previsione-attivitas?query=:query} : search for the previsioneAttivita corresponding
     * to the query.
     *
     * @param query the query of the previsioneAttivita search.
     * @return the result of the search.
     */
    @GetMapping("/_search/previsione-attivitas")
    public List<PrevisioneAttivitaDTO> searchPrevisioneAttivitas(@RequestParam String query) {
        log.debug("REST request to search PrevisioneAttivitas for query {}", query);
        return previsioneAttivitaService.search(query);
    }
}
