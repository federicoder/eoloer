package it.maggioli.web.rest;

import it.maggioli.service.NotaPraticaService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.NotaPraticaDTO;

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
 * REST controller for managing {@link it.maggioli.domain.NotaPratica}.
 */
@RestController
@RequestMapping("/api")
public class NotaPraticaResource {

    private final Logger log = LoggerFactory.getLogger(NotaPraticaResource.class);

    private static final String ENTITY_NAME = "notaPratica";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotaPraticaService notaPraticaService;

    public NotaPraticaResource(NotaPraticaService notaPraticaService) {
        this.notaPraticaService = notaPraticaService;
    }

    /**
     * {@code POST  /nota-praticas} : Create a new notaPratica.
     *
     * @param notaPraticaDTO the notaPraticaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notaPraticaDTO, or with status {@code 400 (Bad Request)} if the notaPratica has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nota-praticas")
    public ResponseEntity<NotaPraticaDTO> createNotaPratica(@Valid @RequestBody NotaPraticaDTO notaPraticaDTO) throws URISyntaxException {
        log.debug("REST request to save NotaPratica : {}", notaPraticaDTO);
        if (notaPraticaDTO.getId() != null) {
            throw new BadRequestAlertException("A new notaPratica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotaPraticaDTO result = notaPraticaService.save(notaPraticaDTO);
        return ResponseEntity.created(new URI("/api/nota-praticas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nota-praticas} : Updates an existing notaPratica.
     *
     * @param notaPraticaDTO the notaPraticaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notaPraticaDTO,
     * or with status {@code 400 (Bad Request)} if the notaPraticaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notaPraticaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nota-praticas")
    public ResponseEntity<NotaPraticaDTO> updateNotaPratica(@Valid @RequestBody NotaPraticaDTO notaPraticaDTO) throws URISyntaxException {
        log.debug("REST request to update NotaPratica : {}", notaPraticaDTO);
        if (notaPraticaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NotaPraticaDTO result = notaPraticaService.save(notaPraticaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, notaPraticaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nota-praticas} : get all the notaPraticas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notaPraticas in body.
     */
    @GetMapping("/nota-praticas")
    public List<NotaPraticaDTO> getAllNotaPraticas() {
        log.debug("REST request to get all NotaPraticas");
        return notaPraticaService.findAll();
    }

    /**
     * {@code GET  /nota-praticas/:id} : get the "id" notaPratica.
     *
     * @param id the id of the notaPraticaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notaPraticaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nota-praticas/{id}")
    public ResponseEntity<NotaPraticaDTO> getNotaPratica(@PathVariable Long id) {
        log.debug("REST request to get NotaPratica : {}", id);
        Optional<NotaPraticaDTO> notaPraticaDTO = notaPraticaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notaPraticaDTO);
    }

    /**
     * {@code DELETE  /nota-praticas/:id} : delete the "id" notaPratica.
     *
     * @param id the id of the notaPraticaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nota-praticas/{id}")
    public ResponseEntity<Void> deleteNotaPratica(@PathVariable Long id) {
        log.debug("REST request to delete NotaPratica : {}", id);
        notaPraticaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/nota-praticas?query=:query} : search for the notaPratica corresponding
     * to the query.
     *
     * @param query the query of the notaPratica search.
     * @return the result of the search.
     */
    @GetMapping("/_search/nota-praticas")
    public List<NotaPraticaDTO> searchNotaPraticas(@RequestParam String query) {
        log.debug("REST request to search NotaPraticas for query {}", query);
        return notaPraticaService.search(query);
    }
}
