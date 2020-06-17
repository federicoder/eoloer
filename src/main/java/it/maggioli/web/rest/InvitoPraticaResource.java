package it.maggioli.web.rest;

import it.maggioli.service.InvitoPraticaService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.InvitoPraticaDTO;

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
 * REST controller for managing {@link it.maggioli.domain.InvitoPratica}.
 */
@RestController
@RequestMapping("/api")
public class InvitoPraticaResource {

    private final Logger log = LoggerFactory.getLogger(InvitoPraticaResource.class);

    private static final String ENTITY_NAME = "invitoPratica";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvitoPraticaService invitoPraticaService;

    public InvitoPraticaResource(InvitoPraticaService invitoPraticaService) {
        this.invitoPraticaService = invitoPraticaService;
    }

    /**
     * {@code POST  /invito-praticas} : Create a new invitoPratica.
     *
     * @param invitoPraticaDTO the invitoPraticaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invitoPraticaDTO, or with status {@code 400 (Bad Request)} if the invitoPratica has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invito-praticas")
    public ResponseEntity<InvitoPraticaDTO> createInvitoPratica(@RequestBody InvitoPraticaDTO invitoPraticaDTO) throws URISyntaxException {
        log.debug("REST request to save InvitoPratica : {}", invitoPraticaDTO);
        if (invitoPraticaDTO.getId() != null) {
            throw new BadRequestAlertException("A new invitoPratica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvitoPraticaDTO result = invitoPraticaService.save(invitoPraticaDTO);
        return ResponseEntity.created(new URI("/api/invito-praticas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /invito-praticas} : Updates an existing invitoPratica.
     *
     * @param invitoPraticaDTO the invitoPraticaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invitoPraticaDTO,
     * or with status {@code 400 (Bad Request)} if the invitoPraticaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invitoPraticaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invito-praticas")
    public ResponseEntity<InvitoPraticaDTO> updateInvitoPratica(@RequestBody InvitoPraticaDTO invitoPraticaDTO) throws URISyntaxException {
        log.debug("REST request to update InvitoPratica : {}", invitoPraticaDTO);
        if (invitoPraticaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvitoPraticaDTO result = invitoPraticaService.save(invitoPraticaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, invitoPraticaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /invito-praticas} : get all the invitoPraticas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invitoPraticas in body.
     */
    @GetMapping("/invito-praticas")
    public List<InvitoPraticaDTO> getAllInvitoPraticas() {
        log.debug("REST request to get all InvitoPraticas");
        return invitoPraticaService.findAll();
    }

    /**
     * {@code GET  /invito-praticas/:id} : get the "id" invitoPratica.
     *
     * @param id the id of the invitoPraticaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invitoPraticaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invito-praticas/{id}")
    public ResponseEntity<InvitoPraticaDTO> getInvitoPratica(@PathVariable Long id) {
        log.debug("REST request to get InvitoPratica : {}", id);
        Optional<InvitoPraticaDTO> invitoPraticaDTO = invitoPraticaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invitoPraticaDTO);
    }

    /**
     * {@code DELETE  /invito-praticas/:id} : delete the "id" invitoPratica.
     *
     * @param id the id of the invitoPraticaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invito-praticas/{id}")
    public ResponseEntity<Void> deleteInvitoPratica(@PathVariable Long id) {
        log.debug("REST request to delete InvitoPratica : {}", id);
        invitoPraticaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/invito-praticas?query=:query} : search for the invitoPratica corresponding
     * to the query.
     *
     * @param query the query of the invitoPratica search.
     * @return the result of the search.
     */
    @GetMapping("/_search/invito-praticas")
    public List<InvitoPraticaDTO> searchInvitoPraticas(@RequestParam String query) {
        log.debug("REST request to search InvitoPraticas for query {}", query);
        return invitoPraticaService.search(query);
    }
}
