package it.maggioli.web.rest;

import it.maggioli.service.RuoloOrganizzazioneService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.RuoloOrganizzazioneDTO;

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
 * REST controller for managing {@link it.maggioli.domain.RuoloOrganizzazione}.
 */
@RestController
@RequestMapping("/api")
public class RuoloOrganizzazioneResource {

    private final Logger log = LoggerFactory.getLogger(RuoloOrganizzazioneResource.class);

    private static final String ENTITY_NAME = "ruoloOrganizzazione";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RuoloOrganizzazioneService ruoloOrganizzazioneService;

    public RuoloOrganizzazioneResource(RuoloOrganizzazioneService ruoloOrganizzazioneService) {
        this.ruoloOrganizzazioneService = ruoloOrganizzazioneService;
    }

    /**
     * {@code POST  /ruolo-organizzaziones} : Create a new ruoloOrganizzazione.
     *
     * @param ruoloOrganizzazioneDTO the ruoloOrganizzazioneDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ruoloOrganizzazioneDTO, or with status {@code 400 (Bad Request)} if the ruoloOrganizzazione has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ruolo-organizzaziones")
    public ResponseEntity<RuoloOrganizzazioneDTO> createRuoloOrganizzazione(@RequestBody RuoloOrganizzazioneDTO ruoloOrganizzazioneDTO) throws URISyntaxException {
        log.debug("REST request to save RuoloOrganizzazione : {}", ruoloOrganizzazioneDTO);
        if (ruoloOrganizzazioneDTO.getId() != null) {
            throw new BadRequestAlertException("A new ruoloOrganizzazione cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RuoloOrganizzazioneDTO result = ruoloOrganizzazioneService.save(ruoloOrganizzazioneDTO);
        return ResponseEntity.created(new URI("/api/ruolo-organizzaziones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ruolo-organizzaziones} : Updates an existing ruoloOrganizzazione.
     *
     * @param ruoloOrganizzazioneDTO the ruoloOrganizzazioneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ruoloOrganizzazioneDTO,
     * or with status {@code 400 (Bad Request)} if the ruoloOrganizzazioneDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ruoloOrganizzazioneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ruolo-organizzaziones")
    public ResponseEntity<RuoloOrganizzazioneDTO> updateRuoloOrganizzazione(@RequestBody RuoloOrganizzazioneDTO ruoloOrganizzazioneDTO) throws URISyntaxException {
        log.debug("REST request to update RuoloOrganizzazione : {}", ruoloOrganizzazioneDTO);
        if (ruoloOrganizzazioneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RuoloOrganizzazioneDTO result = ruoloOrganizzazioneService.save(ruoloOrganizzazioneDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ruoloOrganizzazioneDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ruolo-organizzaziones} : get all the ruoloOrganizzaziones.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ruoloOrganizzaziones in body.
     */
    @GetMapping("/ruolo-organizzaziones")
    public List<RuoloOrganizzazioneDTO> getAllRuoloOrganizzaziones() {
        log.debug("REST request to get all RuoloOrganizzaziones");
        return ruoloOrganizzazioneService.findAll();
    }

    /**
     * {@code GET  /ruolo-organizzaziones/:id} : get the "id" ruoloOrganizzazione.
     *
     * @param id the id of the ruoloOrganizzazioneDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ruoloOrganizzazioneDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ruolo-organizzaziones/{id}")
    public ResponseEntity<RuoloOrganizzazioneDTO> getRuoloOrganizzazione(@PathVariable Long id) {
        log.debug("REST request to get RuoloOrganizzazione : {}", id);
        Optional<RuoloOrganizzazioneDTO> ruoloOrganizzazioneDTO = ruoloOrganizzazioneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ruoloOrganizzazioneDTO);
    }

    /**
     * {@code DELETE  /ruolo-organizzaziones/:id} : delete the "id" ruoloOrganizzazione.
     *
     * @param id the id of the ruoloOrganizzazioneDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ruolo-organizzaziones/{id}")
    public ResponseEntity<Void> deleteRuoloOrganizzazione(@PathVariable Long id) {
        log.debug("REST request to delete RuoloOrganizzazione : {}", id);
        ruoloOrganizzazioneService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/ruolo-organizzaziones?query=:query} : search for the ruoloOrganizzazione corresponding
     * to the query.
     *
     * @param query the query of the ruoloOrganizzazione search.
     * @return the result of the search.
     */
    @GetMapping("/_search/ruolo-organizzaziones")
    public List<RuoloOrganizzazioneDTO> searchRuoloOrganizzaziones(@RequestParam String query) {
        log.debug("REST request to search RuoloOrganizzaziones for query {}", query);
        return ruoloOrganizzazioneService.search(query);
    }
}
