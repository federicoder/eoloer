package it.maggioli.web.rest;

import it.maggioli.service.RisorseDisponibiliService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.RisorseDisponibiliDTO;

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
 * REST controller for managing {@link it.maggioli.domain.RisorseDisponibili}.
 */
@RestController
@RequestMapping("/api")
public class RisorseDisponibiliResource {

    private final Logger log = LoggerFactory.getLogger(RisorseDisponibiliResource.class);

    private static final String ENTITY_NAME = "risorseDisponibili";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RisorseDisponibiliService risorseDisponibiliService;

    public RisorseDisponibiliResource(RisorseDisponibiliService risorseDisponibiliService) {
        this.risorseDisponibiliService = risorseDisponibiliService;
    }

    /**
     * {@code POST  /risorse-disponibilis} : Create a new risorseDisponibili.
     *
     * @param risorseDisponibiliDTO the risorseDisponibiliDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new risorseDisponibiliDTO, or with status {@code 400 (Bad Request)} if the risorseDisponibili has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/risorse-disponibilis")
    public ResponseEntity<RisorseDisponibiliDTO> createRisorseDisponibili(@Valid @RequestBody RisorseDisponibiliDTO risorseDisponibiliDTO) throws URISyntaxException {
        log.debug("REST request to save RisorseDisponibili : {}", risorseDisponibiliDTO);
        if (risorseDisponibiliDTO.getId() != null) {
            throw new BadRequestAlertException("A new risorseDisponibili cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RisorseDisponibiliDTO result = risorseDisponibiliService.save(risorseDisponibiliDTO);
        return ResponseEntity.created(new URI("/api/risorse-disponibilis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /risorse-disponibilis} : Updates an existing risorseDisponibili.
     *
     * @param risorseDisponibiliDTO the risorseDisponibiliDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated risorseDisponibiliDTO,
     * or with status {@code 400 (Bad Request)} if the risorseDisponibiliDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the risorseDisponibiliDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/risorse-disponibilis")
    public ResponseEntity<RisorseDisponibiliDTO> updateRisorseDisponibili(@Valid @RequestBody RisorseDisponibiliDTO risorseDisponibiliDTO) throws URISyntaxException {
        log.debug("REST request to update RisorseDisponibili : {}", risorseDisponibiliDTO);
        if (risorseDisponibiliDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RisorseDisponibiliDTO result = risorseDisponibiliService.save(risorseDisponibiliDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, risorseDisponibiliDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /risorse-disponibilis} : get all the risorseDisponibilis.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of risorseDisponibilis in body.
     */
    @GetMapping("/risorse-disponibilis")
    public List<RisorseDisponibiliDTO> getAllRisorseDisponibilis() {
        log.debug("REST request to get all RisorseDisponibilis");
        return risorseDisponibiliService.findAll();
    }

    /**
     * {@code GET  /risorse-disponibilis/:id} : get the "id" risorseDisponibili.
     *
     * @param id the id of the risorseDisponibiliDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the risorseDisponibiliDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/risorse-disponibilis/{id}")
    public ResponseEntity<RisorseDisponibiliDTO> getRisorseDisponibili(@PathVariable Long id) {
        log.debug("REST request to get RisorseDisponibili : {}", id);
        Optional<RisorseDisponibiliDTO> risorseDisponibiliDTO = risorseDisponibiliService.findOne(id);
        return ResponseUtil.wrapOrNotFound(risorseDisponibiliDTO);
    }

    /**
     * {@code DELETE  /risorse-disponibilis/:id} : delete the "id" risorseDisponibili.
     *
     * @param id the id of the risorseDisponibiliDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/risorse-disponibilis/{id}")
    public ResponseEntity<Void> deleteRisorseDisponibili(@PathVariable Long id) {
        log.debug("REST request to delete RisorseDisponibili : {}", id);
        risorseDisponibiliService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/risorse-disponibilis?query=:query} : search for the risorseDisponibili corresponding
     * to the query.
     *
     * @param query the query of the risorseDisponibili search.
     * @return the result of the search.
     */
    @GetMapping("/_search/risorse-disponibilis")
    public List<RisorseDisponibiliDTO> searchRisorseDisponibilis(@RequestParam String query) {
        log.debug("REST request to search RisorseDisponibilis for query {}", query);
        return risorseDisponibiliService.search(query);
    }
}
