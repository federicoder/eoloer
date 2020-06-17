package it.maggioli.web.rest;

import it.maggioli.service.DatiContabiliService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.DatiContabiliDTO;

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
 * REST controller for managing {@link it.maggioli.domain.DatiContabili}.
 */
@RestController
@RequestMapping("/api")
public class DatiContabiliResource {

    private final Logger log = LoggerFactory.getLogger(DatiContabiliResource.class);

    private static final String ENTITY_NAME = "datiContabili";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DatiContabiliService datiContabiliService;

    public DatiContabiliResource(DatiContabiliService datiContabiliService) {
        this.datiContabiliService = datiContabiliService;
    }

    /**
     * {@code POST  /dati-contabilis} : Create a new datiContabili.
     *
     * @param datiContabiliDTO the datiContabiliDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new datiContabiliDTO, or with status {@code 400 (Bad Request)} if the datiContabili has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dati-contabilis")
    public ResponseEntity<DatiContabiliDTO> createDatiContabili(@Valid @RequestBody DatiContabiliDTO datiContabiliDTO) throws URISyntaxException {
        log.debug("REST request to save DatiContabili : {}", datiContabiliDTO);
        if (datiContabiliDTO.getId() != null) {
            throw new BadRequestAlertException("A new datiContabili cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DatiContabiliDTO result = datiContabiliService.save(datiContabiliDTO);
        return ResponseEntity.created(new URI("/api/dati-contabilis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dati-contabilis} : Updates an existing datiContabili.
     *
     * @param datiContabiliDTO the datiContabiliDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated datiContabiliDTO,
     * or with status {@code 400 (Bad Request)} if the datiContabiliDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the datiContabiliDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dati-contabilis")
    public ResponseEntity<DatiContabiliDTO> updateDatiContabili(@Valid @RequestBody DatiContabiliDTO datiContabiliDTO) throws URISyntaxException {
        log.debug("REST request to update DatiContabili : {}", datiContabiliDTO);
        if (datiContabiliDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DatiContabiliDTO result = datiContabiliService.save(datiContabiliDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, datiContabiliDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dati-contabilis} : get all the datiContabilis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of datiContabilis in body.
     */
    @GetMapping("/dati-contabilis")
    public List<DatiContabiliDTO> getAllDatiContabilis() {
        log.debug("REST request to get all DatiContabilis");
        return datiContabiliService.findAll();
    }

    /**
     * {@code GET  /dati-contabilis/:id} : get the "id" datiContabili.
     *
     * @param id the id of the datiContabiliDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the datiContabiliDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dati-contabilis/{id}")
    public ResponseEntity<DatiContabiliDTO> getDatiContabili(@PathVariable Long id) {
        log.debug("REST request to get DatiContabili : {}", id);
        Optional<DatiContabiliDTO> datiContabiliDTO = datiContabiliService.findOne(id);
        return ResponseUtil.wrapOrNotFound(datiContabiliDTO);
    }

    /**
     * {@code DELETE  /dati-contabilis/:id} : delete the "id" datiContabili.
     *
     * @param id the id of the datiContabiliDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dati-contabilis/{id}")
    public ResponseEntity<Void> deleteDatiContabili(@PathVariable Long id) {
        log.debug("REST request to delete DatiContabili : {}", id);
        datiContabiliService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/dati-contabilis?query=:query} : search for the datiContabili corresponding
     * to the query.
     *
     * @param query the query of the datiContabili search.
     * @return the result of the search.
     */
    @GetMapping("/_search/dati-contabilis")
    public List<DatiContabiliDTO> searchDatiContabilis(@RequestParam String query) {
        log.debug("REST request to search DatiContabilis for query {}", query);
        return datiContabiliService.search(query);
    }
}
