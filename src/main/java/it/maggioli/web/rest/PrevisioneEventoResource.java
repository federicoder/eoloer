package it.maggioli.web.rest;

import it.maggioli.service.PrevisioneEventoService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.PrevisioneEventoDTO;

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
 * REST controller for managing {@link it.maggioli.domain.PrevisioneEvento}.
 */
@RestController
@RequestMapping("/api")
public class PrevisioneEventoResource {

    private final Logger log = LoggerFactory.getLogger(PrevisioneEventoResource.class);

    private static final String ENTITY_NAME = "previsioneEvento";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrevisioneEventoService previsioneEventoService;

    public PrevisioneEventoResource(PrevisioneEventoService previsioneEventoService) {
        this.previsioneEventoService = previsioneEventoService;
    }

    /**
     * {@code POST  /previsione-eventos} : Create a new previsioneEvento.
     *
     * @param previsioneEventoDTO the previsioneEventoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new previsioneEventoDTO, or with status {@code 400 (Bad Request)} if the previsioneEvento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/previsione-eventos")
    public ResponseEntity<PrevisioneEventoDTO> createPrevisioneEvento(@Valid @RequestBody PrevisioneEventoDTO previsioneEventoDTO) throws URISyntaxException {
        log.debug("REST request to save PrevisioneEvento : {}", previsioneEventoDTO);
        if (previsioneEventoDTO.getId() != null) {
            throw new BadRequestAlertException("A new previsioneEvento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrevisioneEventoDTO result = previsioneEventoService.save(previsioneEventoDTO);
        return ResponseEntity.created(new URI("/api/previsione-eventos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /previsione-eventos} : Updates an existing previsioneEvento.
     *
     * @param previsioneEventoDTO the previsioneEventoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated previsioneEventoDTO,
     * or with status {@code 400 (Bad Request)} if the previsioneEventoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the previsioneEventoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/previsione-eventos")
    public ResponseEntity<PrevisioneEventoDTO> updatePrevisioneEvento(@Valid @RequestBody PrevisioneEventoDTO previsioneEventoDTO) throws URISyntaxException {
        log.debug("REST request to update PrevisioneEvento : {}", previsioneEventoDTO);
        if (previsioneEventoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PrevisioneEventoDTO result = previsioneEventoService.save(previsioneEventoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, previsioneEventoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /previsione-eventos} : get all the previsioneEventos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of previsioneEventos in body.
     */
    @GetMapping("/previsione-eventos")
    public List<PrevisioneEventoDTO> getAllPrevisioneEventos() {
        log.debug("REST request to get all PrevisioneEventos");
        return previsioneEventoService.findAll();
    }

    /**
     * {@code GET  /previsione-eventos/:id} : get the "id" previsioneEvento.
     *
     * @param id the id of the previsioneEventoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the previsioneEventoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/previsione-eventos/{id}")
    public ResponseEntity<PrevisioneEventoDTO> getPrevisioneEvento(@PathVariable Long id) {
        log.debug("REST request to get PrevisioneEvento : {}", id);
        Optional<PrevisioneEventoDTO> previsioneEventoDTO = previsioneEventoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(previsioneEventoDTO);
    }

    /**
     * {@code DELETE  /previsione-eventos/:id} : delete the "id" previsioneEvento.
     *
     * @param id the id of the previsioneEventoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/previsione-eventos/{id}")
    public ResponseEntity<Void> deletePrevisioneEvento(@PathVariable Long id) {
        log.debug("REST request to delete PrevisioneEvento : {}", id);
        previsioneEventoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/previsione-eventos?query=:query} : search for the previsioneEvento corresponding
     * to the query.
     *
     * @param query the query of the previsioneEvento search.
     * @return the result of the search.
     */
    @GetMapping("/_search/previsione-eventos")
    public List<PrevisioneEventoDTO> searchPrevisioneEventos(@RequestParam String query) {
        log.debug("REST request to search PrevisioneEventos for query {}", query);
        return previsioneEventoService.search(query);
    }
}
