package it.maggioli.web.rest;

import it.maggioli.service.LineaOrdineService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.LineaOrdineDTO;

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
 * REST controller for managing {@link it.maggioli.domain.LineaOrdine}.
 */
@RestController
@RequestMapping("/api")
public class LineaOrdineResource {

    private final Logger log = LoggerFactory.getLogger(LineaOrdineResource.class);

    private static final String ENTITY_NAME = "lineaOrdine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LineaOrdineService lineaOrdineService;

    public LineaOrdineResource(LineaOrdineService lineaOrdineService) {
        this.lineaOrdineService = lineaOrdineService;
    }

    /**
     * {@code POST  /linea-ordines} : Create a new lineaOrdine.
     *
     * @param lineaOrdineDTO the lineaOrdineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lineaOrdineDTO, or with status {@code 400 (Bad Request)} if the lineaOrdine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/linea-ordines")
    public ResponseEntity<LineaOrdineDTO> createLineaOrdine(@Valid @RequestBody LineaOrdineDTO lineaOrdineDTO) throws URISyntaxException {
        log.debug("REST request to save LineaOrdine : {}", lineaOrdineDTO);
        if (lineaOrdineDTO.getId() != null) {
            throw new BadRequestAlertException("A new lineaOrdine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LineaOrdineDTO result = lineaOrdineService.save(lineaOrdineDTO);
        return ResponseEntity.created(new URI("/api/linea-ordines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /linea-ordines} : Updates an existing lineaOrdine.
     *
     * @param lineaOrdineDTO the lineaOrdineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lineaOrdineDTO,
     * or with status {@code 400 (Bad Request)} if the lineaOrdineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lineaOrdineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/linea-ordines")
    public ResponseEntity<LineaOrdineDTO> updateLineaOrdine(@Valid @RequestBody LineaOrdineDTO lineaOrdineDTO) throws URISyntaxException {
        log.debug("REST request to update LineaOrdine : {}", lineaOrdineDTO);
        if (lineaOrdineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LineaOrdineDTO result = lineaOrdineService.save(lineaOrdineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lineaOrdineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /linea-ordines} : get all the lineaOrdines.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lineaOrdines in body.
     */
    @GetMapping("/linea-ordines")
    public List<LineaOrdineDTO> getAllLineaOrdines() {
        log.debug("REST request to get all LineaOrdines");
        return lineaOrdineService.findAll();
    }

    /**
     * {@code GET  /linea-ordines/:id} : get the "id" lineaOrdine.
     *
     * @param id the id of the lineaOrdineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lineaOrdineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/linea-ordines/{id}")
    public ResponseEntity<LineaOrdineDTO> getLineaOrdine(@PathVariable Long id) {
        log.debug("REST request to get LineaOrdine : {}", id);
        Optional<LineaOrdineDTO> lineaOrdineDTO = lineaOrdineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lineaOrdineDTO);
    }

    /**
     * {@code DELETE  /linea-ordines/:id} : delete the "id" lineaOrdine.
     *
     * @param id the id of the lineaOrdineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/linea-ordines/{id}")
    public ResponseEntity<Void> deleteLineaOrdine(@PathVariable Long id) {
        log.debug("REST request to delete LineaOrdine : {}", id);
        lineaOrdineService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/linea-ordines?query=:query} : search for the lineaOrdine corresponding
     * to the query.
     *
     * @param query the query of the lineaOrdine search.
     * @return the result of the search.
     */
    @GetMapping("/_search/linea-ordines")
    public List<LineaOrdineDTO> searchLineaOrdines(@RequestParam String query) {
        log.debug("REST request to search LineaOrdines for query {}", query);
        return lineaOrdineService.search(query);
    }
}
