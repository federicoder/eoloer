package it.maggioli.web.rest;

import it.maggioli.service.RappresentanzaPraticaService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.RappresentanzaPraticaDTO;

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
 * REST controller for managing {@link it.maggioli.domain.RappresentanzaPratica}.
 */
@RestController
@RequestMapping("/api")
public class RappresentanzaPraticaResource {

    private final Logger log = LoggerFactory.getLogger(RappresentanzaPraticaResource.class);

    private static final String ENTITY_NAME = "rappresentanzaPratica";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RappresentanzaPraticaService rappresentanzaPraticaService;

    public RappresentanzaPraticaResource(RappresentanzaPraticaService rappresentanzaPraticaService) {
        this.rappresentanzaPraticaService = rappresentanzaPraticaService;
    }

    /**
     * {@code POST  /rappresentanza-praticas} : Create a new rappresentanzaPratica.
     *
     * @param rappresentanzaPraticaDTO the rappresentanzaPraticaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rappresentanzaPraticaDTO, or with status {@code 400 (Bad Request)} if the rappresentanzaPratica has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rappresentanza-praticas")
    public ResponseEntity<RappresentanzaPraticaDTO> createRappresentanzaPratica(@Valid @RequestBody RappresentanzaPraticaDTO rappresentanzaPraticaDTO) throws URISyntaxException {
        log.debug("REST request to save RappresentanzaPratica : {}", rappresentanzaPraticaDTO);
        if (rappresentanzaPraticaDTO.getId() != null) {
            throw new BadRequestAlertException("A new rappresentanzaPratica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RappresentanzaPraticaDTO result = rappresentanzaPraticaService.save(rappresentanzaPraticaDTO);
        return ResponseEntity.created(new URI("/api/rappresentanza-praticas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rappresentanza-praticas} : Updates an existing rappresentanzaPratica.
     *
     * @param rappresentanzaPraticaDTO the rappresentanzaPraticaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rappresentanzaPraticaDTO,
     * or with status {@code 400 (Bad Request)} if the rappresentanzaPraticaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rappresentanzaPraticaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rappresentanza-praticas")
    public ResponseEntity<RappresentanzaPraticaDTO> updateRappresentanzaPratica(@Valid @RequestBody RappresentanzaPraticaDTO rappresentanzaPraticaDTO) throws URISyntaxException {
        log.debug("REST request to update RappresentanzaPratica : {}", rappresentanzaPraticaDTO);
        if (rappresentanzaPraticaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RappresentanzaPraticaDTO result = rappresentanzaPraticaService.save(rappresentanzaPraticaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rappresentanzaPraticaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /rappresentanza-praticas} : get all the rappresentanzaPraticas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rappresentanzaPraticas in body.
     */
    @GetMapping("/rappresentanza-praticas")
    public List<RappresentanzaPraticaDTO> getAllRappresentanzaPraticas() {
        log.debug("REST request to get all RappresentanzaPraticas");
        return rappresentanzaPraticaService.findAll();
    }

    /**
     * {@code GET  /rappresentanza-praticas/:id} : get the "id" rappresentanzaPratica.
     *
     * @param id the id of the rappresentanzaPraticaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rappresentanzaPraticaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rappresentanza-praticas/{id}")
    public ResponseEntity<RappresentanzaPraticaDTO> getRappresentanzaPratica(@PathVariable Long id) {
        log.debug("REST request to get RappresentanzaPratica : {}", id);
        Optional<RappresentanzaPraticaDTO> rappresentanzaPraticaDTO = rappresentanzaPraticaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rappresentanzaPraticaDTO);
    }

    /**
     * {@code DELETE  /rappresentanza-praticas/:id} : delete the "id" rappresentanzaPratica.
     *
     * @param id the id of the rappresentanzaPraticaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rappresentanza-praticas/{id}")
    public ResponseEntity<Void> deleteRappresentanzaPratica(@PathVariable Long id) {
        log.debug("REST request to delete RappresentanzaPratica : {}", id);
        rappresentanzaPraticaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/rappresentanza-praticas?query=:query} : search for the rappresentanzaPratica corresponding
     * to the query.
     *
     * @param query the query of the rappresentanzaPratica search.
     * @return the result of the search.
     */
    @GetMapping("/_search/rappresentanza-praticas")
    public List<RappresentanzaPraticaDTO> searchRappresentanzaPraticas(@RequestParam String query) {
        log.debug("REST request to search RappresentanzaPraticas for query {}", query);
        return rappresentanzaPraticaService.search(query);
    }
}
