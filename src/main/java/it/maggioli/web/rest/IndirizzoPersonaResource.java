package it.maggioli.web.rest;

import it.maggioli.service.IndirizzoPersonaService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.IndirizzoPersonaDTO;

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
 * REST controller for managing {@link it.maggioli.domain.IndirizzoPersona}.
 */
@RestController
@RequestMapping("/api")
public class IndirizzoPersonaResource {

    private final Logger log = LoggerFactory.getLogger(IndirizzoPersonaResource.class);

    private static final String ENTITY_NAME = "indirizzoPersona";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IndirizzoPersonaService indirizzoPersonaService;

    public IndirizzoPersonaResource(IndirizzoPersonaService indirizzoPersonaService) {
        this.indirizzoPersonaService = indirizzoPersonaService;
    }

    /**
     * {@code POST  /indirizzo-personas} : Create a new indirizzoPersona.
     *
     * @param indirizzoPersonaDTO the indirizzoPersonaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new indirizzoPersonaDTO, or with status {@code 400 (Bad Request)} if the indirizzoPersona has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/indirizzo-personas")
    public ResponseEntity<IndirizzoPersonaDTO> createIndirizzoPersona(@Valid @RequestBody IndirizzoPersonaDTO indirizzoPersonaDTO) throws URISyntaxException {
        log.debug("REST request to save IndirizzoPersona : {}", indirizzoPersonaDTO);
        if (indirizzoPersonaDTO.getId() != null) {
            throw new BadRequestAlertException("A new indirizzoPersona cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IndirizzoPersonaDTO result = indirizzoPersonaService.save(indirizzoPersonaDTO);
        return ResponseEntity.created(new URI("/api/indirizzo-personas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /indirizzo-personas} : Updates an existing indirizzoPersona.
     *
     * @param indirizzoPersonaDTO the indirizzoPersonaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated indirizzoPersonaDTO,
     * or with status {@code 400 (Bad Request)} if the indirizzoPersonaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the indirizzoPersonaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/indirizzo-personas")
    public ResponseEntity<IndirizzoPersonaDTO> updateIndirizzoPersona(@Valid @RequestBody IndirizzoPersonaDTO indirizzoPersonaDTO) throws URISyntaxException {
        log.debug("REST request to update IndirizzoPersona : {}", indirizzoPersonaDTO);
        if (indirizzoPersonaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IndirizzoPersonaDTO result = indirizzoPersonaService.save(indirizzoPersonaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, indirizzoPersonaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /indirizzo-personas} : get all the indirizzoPersonas.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of indirizzoPersonas in body.
     */
    @GetMapping("/indirizzo-personas")
    public List<IndirizzoPersonaDTO> getAllIndirizzoPersonas(@RequestParam(required = false) String filter) {
        if ("idpersonaref-is-null".equals(filter)) {
            log.debug("REST request to get all IndirizzoPersonas where idPersonaRef is null");
            return indirizzoPersonaService.findAllWhereIdPersonaRefIsNull();
        }
        log.debug("REST request to get all IndirizzoPersonas");
        return indirizzoPersonaService.findAll();
    }

    /**
     * {@code GET  /indirizzo-personas/:id} : get the "id" indirizzoPersona.
     *
     * @param id the id of the indirizzoPersonaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the indirizzoPersonaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/indirizzo-personas/{id}")
    public ResponseEntity<IndirizzoPersonaDTO> getIndirizzoPersona(@PathVariable Long id) {
        log.debug("REST request to get IndirizzoPersona : {}", id);
        Optional<IndirizzoPersonaDTO> indirizzoPersonaDTO = indirizzoPersonaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(indirizzoPersonaDTO);
    }

    /**
     * {@code DELETE  /indirizzo-personas/:id} : delete the "id" indirizzoPersona.
     *
     * @param id the id of the indirizzoPersonaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/indirizzo-personas/{id}")
    public ResponseEntity<Void> deleteIndirizzoPersona(@PathVariable Long id) {
        log.debug("REST request to delete IndirizzoPersona : {}", id);
        indirizzoPersonaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/indirizzo-personas?query=:query} : search for the indirizzoPersona corresponding
     * to the query.
     *
     * @param query the query of the indirizzoPersona search.
     * @return the result of the search.
     */
    @GetMapping("/_search/indirizzo-personas")
    public List<IndirizzoPersonaDTO> searchIndirizzoPersonas(@RequestParam String query) {
        log.debug("REST request to search IndirizzoPersonas for query {}", query);
        return indirizzoPersonaService.search(query);
    }
}
