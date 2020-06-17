package it.maggioli.web.rest;

import it.maggioli.service.TelefonoPersonaService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.TelefonoPersonaDTO;

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
 * REST controller for managing {@link it.maggioli.domain.TelefonoPersona}.
 */
@RestController
@RequestMapping("/api")
public class TelefonoPersonaResource {

    private final Logger log = LoggerFactory.getLogger(TelefonoPersonaResource.class);

    private static final String ENTITY_NAME = "telefonoPersona";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TelefonoPersonaService telefonoPersonaService;

    public TelefonoPersonaResource(TelefonoPersonaService telefonoPersonaService) {
        this.telefonoPersonaService = telefonoPersonaService;
    }

    /**
     * {@code POST  /telefono-personas} : Create a new telefonoPersona.
     *
     * @param telefonoPersonaDTO the telefonoPersonaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new telefonoPersonaDTO, or with status {@code 400 (Bad Request)} if the telefonoPersona has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/telefono-personas")
    public ResponseEntity<TelefonoPersonaDTO> createTelefonoPersona(@Valid @RequestBody TelefonoPersonaDTO telefonoPersonaDTO) throws URISyntaxException {
        log.debug("REST request to save TelefonoPersona : {}", telefonoPersonaDTO);
        if (telefonoPersonaDTO.getId() != null) {
            throw new BadRequestAlertException("A new telefonoPersona cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TelefonoPersonaDTO result = telefonoPersonaService.save(telefonoPersonaDTO);
        return ResponseEntity.created(new URI("/api/telefono-personas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /telefono-personas} : Updates an existing telefonoPersona.
     *
     * @param telefonoPersonaDTO the telefonoPersonaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated telefonoPersonaDTO,
     * or with status {@code 400 (Bad Request)} if the telefonoPersonaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the telefonoPersonaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/telefono-personas")
    public ResponseEntity<TelefonoPersonaDTO> updateTelefonoPersona(@Valid @RequestBody TelefonoPersonaDTO telefonoPersonaDTO) throws URISyntaxException {
        log.debug("REST request to update TelefonoPersona : {}", telefonoPersonaDTO);
        if (telefonoPersonaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TelefonoPersonaDTO result = telefonoPersonaService.save(telefonoPersonaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, telefonoPersonaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /telefono-personas} : get all the telefonoPersonas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of telefonoPersonas in body.
     */
    @GetMapping("/telefono-personas")
    public List<TelefonoPersonaDTO> getAllTelefonoPersonas() {
        log.debug("REST request to get all TelefonoPersonas");
        return telefonoPersonaService.findAll();
    }

    /**
     * {@code GET  /telefono-personas/:id} : get the "id" telefonoPersona.
     *
     * @param id the id of the telefonoPersonaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the telefonoPersonaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/telefono-personas/{id}")
    public ResponseEntity<TelefonoPersonaDTO> getTelefonoPersona(@PathVariable Long id) {
        log.debug("REST request to get TelefonoPersona : {}", id);
        Optional<TelefonoPersonaDTO> telefonoPersonaDTO = telefonoPersonaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(telefonoPersonaDTO);
    }

    /**
     * {@code DELETE  /telefono-personas/:id} : delete the "id" telefonoPersona.
     *
     * @param id the id of the telefonoPersonaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/telefono-personas/{id}")
    public ResponseEntity<Void> deleteTelefonoPersona(@PathVariable Long id) {
        log.debug("REST request to delete TelefonoPersona : {}", id);
        telefonoPersonaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/telefono-personas?query=:query} : search for the telefonoPersona corresponding
     * to the query.
     *
     * @param query the query of the telefonoPersona search.
     * @return the result of the search.
     */
    @GetMapping("/_search/telefono-personas")
    public List<TelefonoPersonaDTO> searchTelefonoPersonas(@RequestParam String query) {
        log.debug("REST request to search TelefonoPersonas for query {}", query);
        return telefonoPersonaService.search(query);
    }
}
