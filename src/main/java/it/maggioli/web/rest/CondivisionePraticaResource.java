package it.maggioli.web.rest;

import it.maggioli.service.CondivisionePraticaService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.CondivisionePraticaDTO;

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
 * REST controller for managing {@link it.maggioli.domain.CondivisionePratica}.
 */
@RestController
@RequestMapping("/api")
public class CondivisionePraticaResource {

    private final Logger log = LoggerFactory.getLogger(CondivisionePraticaResource.class);

    private static final String ENTITY_NAME = "condivisionePratica";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CondivisionePraticaService condivisionePraticaService;

    public CondivisionePraticaResource(CondivisionePraticaService condivisionePraticaService) {
        this.condivisionePraticaService = condivisionePraticaService;
    }

    /**
     * {@code POST  /condivisione-praticas} : Create a new condivisionePratica.
     *
     * @param condivisionePraticaDTO the condivisionePraticaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new condivisionePraticaDTO, or with status {@code 400 (Bad Request)} if the condivisionePratica has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/condivisione-praticas")
    public ResponseEntity<CondivisionePraticaDTO> createCondivisionePratica(@Valid @RequestBody CondivisionePraticaDTO condivisionePraticaDTO) throws URISyntaxException {
        log.debug("REST request to save CondivisionePratica : {}", condivisionePraticaDTO);
        if (condivisionePraticaDTO.getId() != null) {
            throw new BadRequestAlertException("A new condivisionePratica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CondivisionePraticaDTO result = condivisionePraticaService.save(condivisionePraticaDTO);
        return ResponseEntity.created(new URI("/api/condivisione-praticas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /condivisione-praticas} : Updates an existing condivisionePratica.
     *
     * @param condivisionePraticaDTO the condivisionePraticaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated condivisionePraticaDTO,
     * or with status {@code 400 (Bad Request)} if the condivisionePraticaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the condivisionePraticaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/condivisione-praticas")
    public ResponseEntity<CondivisionePraticaDTO> updateCondivisionePratica(@Valid @RequestBody CondivisionePraticaDTO condivisionePraticaDTO) throws URISyntaxException {
        log.debug("REST request to update CondivisionePratica : {}", condivisionePraticaDTO);
        if (condivisionePraticaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CondivisionePraticaDTO result = condivisionePraticaService.save(condivisionePraticaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, condivisionePraticaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /condivisione-praticas} : get all the condivisionePraticas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of condivisionePraticas in body.
     */
    @GetMapping("/condivisione-praticas")
    public List<CondivisionePraticaDTO> getAllCondivisionePraticas() {
        log.debug("REST request to get all CondivisionePraticas");
        return condivisionePraticaService.findAll();
    }

    /**
     * {@code GET  /condivisione-praticas/:id} : get the "id" condivisionePratica.
     *
     * @param id the id of the condivisionePraticaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the condivisionePraticaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/condivisione-praticas/{id}")
    public ResponseEntity<CondivisionePraticaDTO> getCondivisionePratica(@PathVariable Long id) {
        log.debug("REST request to get CondivisionePratica : {}", id);
        Optional<CondivisionePraticaDTO> condivisionePraticaDTO = condivisionePraticaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(condivisionePraticaDTO);
    }

    /**
     * {@code DELETE  /condivisione-praticas/:id} : delete the "id" condivisionePratica.
     *
     * @param id the id of the condivisionePraticaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/condivisione-praticas/{id}")
    public ResponseEntity<Void> deleteCondivisionePratica(@PathVariable Long id) {
        log.debug("REST request to delete CondivisionePratica : {}", id);
        condivisionePraticaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/condivisione-praticas?query=:query} : search for the condivisionePratica corresponding
     * to the query.
     *
     * @param query the query of the condivisionePratica search.
     * @return the result of the search.
     */
    @GetMapping("/_search/condivisione-praticas")
    public List<CondivisionePraticaDTO> searchCondivisionePraticas(@RequestParam String query) {
        log.debug("REST request to search CondivisionePraticas for query {}", query);
        return condivisionePraticaService.search(query);
    }
}
