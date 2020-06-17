package it.maggioli.web.rest;

import it.maggioli.service.InvitatoService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.InvitatoDTO;

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
 * REST controller for managing {@link it.maggioli.domain.Invitato}.
 */
@RestController
@RequestMapping("/api")
public class InvitatoResource {

    private final Logger log = LoggerFactory.getLogger(InvitatoResource.class);

    private static final String ENTITY_NAME = "invitato";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvitatoService invitatoService;

    public InvitatoResource(InvitatoService invitatoService) {
        this.invitatoService = invitatoService;
    }

    /**
     * {@code POST  /invitatoes} : Create a new invitato.
     *
     * @param invitatoDTO the invitatoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invitatoDTO, or with status {@code 400 (Bad Request)} if the invitato has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invitatoes")
    public ResponseEntity<InvitatoDTO> createInvitato(@Valid @RequestBody InvitatoDTO invitatoDTO) throws URISyntaxException {
        log.debug("REST request to save Invitato : {}", invitatoDTO);
        if (invitatoDTO.getId() != null) {
            throw new BadRequestAlertException("A new invitato cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvitatoDTO result = invitatoService.save(invitatoDTO);
        return ResponseEntity.created(new URI("/api/invitatoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /invitatoes} : Updates an existing invitato.
     *
     * @param invitatoDTO the invitatoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invitatoDTO,
     * or with status {@code 400 (Bad Request)} if the invitatoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invitatoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invitatoes")
    public ResponseEntity<InvitatoDTO> updateInvitato(@Valid @RequestBody InvitatoDTO invitatoDTO) throws URISyntaxException {
        log.debug("REST request to update Invitato : {}", invitatoDTO);
        if (invitatoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvitatoDTO result = invitatoService.save(invitatoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, invitatoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /invitatoes} : get all the invitatoes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invitatoes in body.
     */
    @GetMapping("/invitatoes")
    public List<InvitatoDTO> getAllInvitatoes() {
        log.debug("REST request to get all Invitatoes");
        return invitatoService.findAll();
    }

    /**
     * {@code GET  /invitatoes/:id} : get the "id" invitato.
     *
     * @param id the id of the invitatoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invitatoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invitatoes/{id}")
    public ResponseEntity<InvitatoDTO> getInvitato(@PathVariable Long id) {
        log.debug("REST request to get Invitato : {}", id);
        Optional<InvitatoDTO> invitatoDTO = invitatoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invitatoDTO);
    }

    /**
     * {@code DELETE  /invitatoes/:id} : delete the "id" invitato.
     *
     * @param id the id of the invitatoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invitatoes/{id}")
    public ResponseEntity<Void> deleteInvitato(@PathVariable Long id) {
        log.debug("REST request to delete Invitato : {}", id);
        invitatoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/invitatoes?query=:query} : search for the invitato corresponding
     * to the query.
     *
     * @param query the query of the invitato search.
     * @return the result of the search.
     */
    @GetMapping("/_search/invitatoes")
    public List<InvitatoDTO> searchInvitatoes(@RequestParam String query) {
        log.debug("REST request to search Invitatoes for query {}", query);
        return invitatoService.search(query);
    }
}
