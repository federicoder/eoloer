package it.maggioli.web.rest;

import it.maggioli.service.InvitoEventoService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.InvitoEventoDTO;

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
 * REST controller for managing {@link it.maggioli.domain.InvitoEvento}.
 */
@RestController
@RequestMapping("/api")
public class InvitoEventoResource {

    private final Logger log = LoggerFactory.getLogger(InvitoEventoResource.class);

    private static final String ENTITY_NAME = "invitoEvento";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvitoEventoService invitoEventoService;

    public InvitoEventoResource(InvitoEventoService invitoEventoService) {
        this.invitoEventoService = invitoEventoService;
    }

    /**
     * {@code POST  /invito-eventos} : Create a new invitoEvento.
     *
     * @param invitoEventoDTO the invitoEventoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invitoEventoDTO, or with status {@code 400 (Bad Request)} if the invitoEvento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invito-eventos")
    public ResponseEntity<InvitoEventoDTO> createInvitoEvento(@RequestBody InvitoEventoDTO invitoEventoDTO) throws URISyntaxException {
        log.debug("REST request to save InvitoEvento : {}", invitoEventoDTO);
        if (invitoEventoDTO.getId() != null) {
            throw new BadRequestAlertException("A new invitoEvento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvitoEventoDTO result = invitoEventoService.save(invitoEventoDTO);
        return ResponseEntity.created(new URI("/api/invito-eventos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /invito-eventos} : Updates an existing invitoEvento.
     *
     * @param invitoEventoDTO the invitoEventoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invitoEventoDTO,
     * or with status {@code 400 (Bad Request)} if the invitoEventoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invitoEventoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invito-eventos")
    public ResponseEntity<InvitoEventoDTO> updateInvitoEvento(@RequestBody InvitoEventoDTO invitoEventoDTO) throws URISyntaxException {
        log.debug("REST request to update InvitoEvento : {}", invitoEventoDTO);
        if (invitoEventoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvitoEventoDTO result = invitoEventoService.save(invitoEventoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, invitoEventoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /invito-eventos} : get all the invitoEventos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invitoEventos in body.
     */
    @GetMapping("/invito-eventos")
    public List<InvitoEventoDTO> getAllInvitoEventos() {
        log.debug("REST request to get all InvitoEventos");
        return invitoEventoService.findAll();
    }

    /**
     * {@code GET  /invito-eventos/:id} : get the "id" invitoEvento.
     *
     * @param id the id of the invitoEventoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invitoEventoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invito-eventos/{id}")
    public ResponseEntity<InvitoEventoDTO> getInvitoEvento(@PathVariable Long id) {
        log.debug("REST request to get InvitoEvento : {}", id);
        Optional<InvitoEventoDTO> invitoEventoDTO = invitoEventoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invitoEventoDTO);
    }

    /**
     * {@code DELETE  /invito-eventos/:id} : delete the "id" invitoEvento.
     *
     * @param id the id of the invitoEventoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invito-eventos/{id}")
    public ResponseEntity<Void> deleteInvitoEvento(@PathVariable Long id) {
        log.debug("REST request to delete InvitoEvento : {}", id);
        invitoEventoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/invito-eventos?query=:query} : search for the invitoEvento corresponding
     * to the query.
     *
     * @param query the query of the invitoEvento search.
     * @return the result of the search.
     */
    @GetMapping("/_search/invito-eventos")
    public List<InvitoEventoDTO> searchInvitoEventos(@RequestParam String query) {
        log.debug("REST request to search InvitoEventos for query {}", query);
        return invitoEventoService.search(query);
    }
}
