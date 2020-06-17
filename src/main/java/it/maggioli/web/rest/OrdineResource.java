package it.maggioli.web.rest;

import it.maggioli.service.OrdineService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.OrdineDTO;

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
 * REST controller for managing {@link it.maggioli.domain.Ordine}.
 */
@RestController
@RequestMapping("/api")
public class OrdineResource {

    private final Logger log = LoggerFactory.getLogger(OrdineResource.class);

    private static final String ENTITY_NAME = "ordine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrdineService ordineService;

    public OrdineResource(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    /**
     * {@code POST  /ordines} : Create a new ordine.
     *
     * @param ordineDTO the ordineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ordineDTO, or with status {@code 400 (Bad Request)} if the ordine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ordines")
    public ResponseEntity<OrdineDTO> createOrdine(@Valid @RequestBody OrdineDTO ordineDTO) throws URISyntaxException {
        log.debug("REST request to save Ordine : {}", ordineDTO);
        if (ordineDTO.getId() != null) {
            throw new BadRequestAlertException("A new ordine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrdineDTO result = ordineService.save(ordineDTO);
        return ResponseEntity.created(new URI("/api/ordines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ordines} : Updates an existing ordine.
     *
     * @param ordineDTO the ordineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ordineDTO,
     * or with status {@code 400 (Bad Request)} if the ordineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ordineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ordines")
    public ResponseEntity<OrdineDTO> updateOrdine(@Valid @RequestBody OrdineDTO ordineDTO) throws URISyntaxException {
        log.debug("REST request to update Ordine : {}", ordineDTO);
        if (ordineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrdineDTO result = ordineService.save(ordineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ordineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ordines} : get all the ordines.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ordines in body.
     */
    @GetMapping("/ordines")
    public List<OrdineDTO> getAllOrdines() {
        log.debug("REST request to get all Ordines");
        return ordineService.findAll();
    }

    /**
     * {@code GET  /ordines/:id} : get the "id" ordine.
     *
     * @param id the id of the ordineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ordineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ordines/{id}")
    public ResponseEntity<OrdineDTO> getOrdine(@PathVariable Long id) {
        log.debug("REST request to get Ordine : {}", id);
        Optional<OrdineDTO> ordineDTO = ordineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ordineDTO);
    }

    /**
     * {@code DELETE  /ordines/:id} : delete the "id" ordine.
     *
     * @param id the id of the ordineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ordines/{id}")
    public ResponseEntity<Void> deleteOrdine(@PathVariable Long id) {
        log.debug("REST request to delete Ordine : {}", id);
        ordineService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/ordines?query=:query} : search for the ordine corresponding
     * to the query.
     *
     * @param query the query of the ordine search.
     * @return the result of the search.
     */
    @GetMapping("/_search/ordines")
    public List<OrdineDTO> searchOrdines(@RequestParam String query) {
        log.debug("REST request to search Ordines for query {}", query);
        return ordineService.search(query);
    }
}
