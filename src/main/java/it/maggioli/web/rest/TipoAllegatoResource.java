package it.maggioli.web.rest;

import it.maggioli.service.TipoAllegatoService;
import it.maggioli.web.rest.errors.BadRequestAlertException;
import it.maggioli.service.dto.TipoAllegatoDTO;

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
 * REST controller for managing {@link it.maggioli.domain.TipoAllegato}.
 */
@RestController
@RequestMapping("/api")
public class TipoAllegatoResource {

    private final Logger log = LoggerFactory.getLogger(TipoAllegatoResource.class);

    private static final String ENTITY_NAME = "tipoAllegato";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoAllegatoService tipoAllegatoService;

    public TipoAllegatoResource(TipoAllegatoService tipoAllegatoService) {
        this.tipoAllegatoService = tipoAllegatoService;
    }

    /**
     * {@code POST  /tipo-allegatoes} : Create a new tipoAllegato.
     *
     * @param tipoAllegatoDTO the tipoAllegatoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoAllegatoDTO, or with status {@code 400 (Bad Request)} if the tipoAllegato has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-allegatoes")
    public ResponseEntity<TipoAllegatoDTO> createTipoAllegato(@Valid @RequestBody TipoAllegatoDTO tipoAllegatoDTO) throws URISyntaxException {
        log.debug("REST request to save TipoAllegato : {}", tipoAllegatoDTO);
        if (tipoAllegatoDTO.getId() != null) {
            throw new BadRequestAlertException("A new tipoAllegato cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoAllegatoDTO result = tipoAllegatoService.save(tipoAllegatoDTO);
        return ResponseEntity.created(new URI("/api/tipo-allegatoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-allegatoes} : Updates an existing tipoAllegato.
     *
     * @param tipoAllegatoDTO the tipoAllegatoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoAllegatoDTO,
     * or with status {@code 400 (Bad Request)} if the tipoAllegatoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoAllegatoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-allegatoes")
    public ResponseEntity<TipoAllegatoDTO> updateTipoAllegato(@Valid @RequestBody TipoAllegatoDTO tipoAllegatoDTO) throws URISyntaxException {
        log.debug("REST request to update TipoAllegato : {}", tipoAllegatoDTO);
        if (tipoAllegatoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoAllegatoDTO result = tipoAllegatoService.save(tipoAllegatoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoAllegatoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-allegatoes} : get all the tipoAllegatoes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoAllegatoes in body.
     */
    @GetMapping("/tipo-allegatoes")
    public List<TipoAllegatoDTO> getAllTipoAllegatoes() {
        log.debug("REST request to get all TipoAllegatoes");
        return tipoAllegatoService.findAll();
    }

    /**
     * {@code GET  /tipo-allegatoes/:id} : get the "id" tipoAllegato.
     *
     * @param id the id of the tipoAllegatoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoAllegatoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-allegatoes/{id}")
    public ResponseEntity<TipoAllegatoDTO> getTipoAllegato(@PathVariable Long id) {
        log.debug("REST request to get TipoAllegato : {}", id);
        Optional<TipoAllegatoDTO> tipoAllegatoDTO = tipoAllegatoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoAllegatoDTO);
    }

    /**
     * {@code DELETE  /tipo-allegatoes/:id} : delete the "id" tipoAllegato.
     *
     * @param id the id of the tipoAllegatoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-allegatoes/{id}")
    public ResponseEntity<Void> deleteTipoAllegato(@PathVariable Long id) {
        log.debug("REST request to delete TipoAllegato : {}", id);
        tipoAllegatoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/tipo-allegatoes?query=:query} : search for the tipoAllegato corresponding
     * to the query.
     *
     * @param query the query of the tipoAllegato search.
     * @return the result of the search.
     */
    @GetMapping("/_search/tipo-allegatoes")
    public List<TipoAllegatoDTO> searchTipoAllegatoes(@RequestParam String query) {
        log.debug("REST request to search TipoAllegatoes for query {}", query);
        return tipoAllegatoService.search(query);
    }
}
